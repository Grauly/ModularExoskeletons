package grauly.screen.modification;

import grauly.modules.base.ModularItem;
import grauly.modules.base.Module;
import grauly.screen.AllScreenHandlers;
import grauly.screen.slots.ModularItemSlot;
import grauly.screen.slots.ModuleSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.slot.Slot;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class ModificationTableScreenHandler extends ScreenHandler {

    protected final ScreenHandlerType<?> type;
    protected final ArrayList<ModuleSlot> moduleInputSlots = new ArrayList<>();
    protected PropertyDelegate statsPropertyDelegate = new ModificationTablePropertyDelegate();

    public ModificationTableScreenHandler(int syncId, PlayerInventory inventory) {
        this(AllScreenHandlers.MODIFICATION_TABLE_SCREEN_HANDLER, syncId, inventory);
    }

    protected ModificationTableScreenHandler(@Nullable ScreenHandlerType<?> type, int syncId, PlayerInventory playerInventory) {
        super(type, syncId);
        this.type = type;

        registerProperties();
        buildSlots(playerInventory);
    }


    protected Inventory moduleInputInventory = new SimpleInventory(15) {
        @Override
        public void markDirty() {
            super.markDirty();
            ModificationTableScreenHandler.this.onModulesChanged(this);
        }
    };

    protected Inventory modularItemInputInventory = new SimpleInventory(1) {
        @Override
        public void markDirty() {
            super.markDirty();
            ModificationTableScreenHandler.this.onModularItemChanged(this);
        }
    };

    protected void registerProperties() {
        this.addProperties(statsPropertyDelegate);
    }

    /**
     * resulting layout:
     * 0:          Modular Item
     * 1 - 15:     Module Slot
     * 16 - 42:    Inventory Slots
     * 43 - 51:    Hotbar Slots
     */
    protected void buildSlots(PlayerInventory playerInventory) {
        //construct Slots
        this.addSlot(new ModularItemSlot(modularItemInputInventory, 0, 26, 34));
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                var moduleSlot = new ModuleSlot(moduleInputInventory, j + i * 5, 71 + j * 18, 16 + i * 18, statsPropertyDelegate);
                moduleInputSlots.add(moduleSlot);
                this.addSlot(moduleSlot);
            }
        }

        //player inventory
        for (int i = 0; i < 3; ++i) {
            for (int j = 0; j < 9; ++j) {
                this.addSlot(new Slot(playerInventory, j + i * 9 + 9, 8 + j * 18, 84 + i * 18));
            }
        }
        for (int i = 0; i < 9; ++i) {
            this.addSlot(new Slot(playerInventory, i, 8 + i * 18, 142));
        }
    }

    public void onModulesChanged(Inventory inventory) {
        var modularItemInputStack = modularItemInputInventory.getStack(0);
        if (!modularItemInputStack.equals(ItemStack.EMPTY)) {
            var item = modularItemInputStack.getItem();
            if (item instanceof ModularItem<?> modularItem) {
                ArrayList<ItemStack> modules = new ArrayList<>();
                for (int i = 0; i < 15; i++) {
                    var moduleStack = inventory.getStack(i);
                    if (!moduleStack.equals(ItemStack.EMPTY)) {
                        modules.add(moduleStack);
                    }
                }
                modularItem.serializeModules(modules, modularItemInputStack);
                updateStats(modularItemInputStack);
            }
        }
    }

    public void onModularItemChanged(Inventory inventory) {
        var stack = inventory.getStack(0);
        if (stack.equals(ItemStack.EMPTY)) {
            moduleInputInventory.clear();
            moduleInputSlots.forEach(m -> {
                m.updateFilter(new ArrayList<>());
            });
        } else {
            var item = stack.getItem();
            if (item instanceof ModularItem<?> modularItem) {
                moduleInputSlots.forEach(m -> {
                    m.updateFilter(modularItem.getAllowedModules());
                });
                ArrayList<ItemStack> deserializedModules = modularItem.deserializeModules(stack);
                for (int i = 0; i < Math.min(15,deserializedModules.size()); i++) {
                    moduleInputInventory.setStack(i, deserializedModules.get(i));
                }
                modularItem.recalculateStats(stack);
                updateStats(stack);
            }
        }
    }

    @Override
    public ItemStack transferSlot(PlayerEntity player, int index) {
        Slot slot = this.slots.get(index);
        if (slot.hasStack()) {
            var stackInSlot = slot.getStack();
            var backupStack = stackInSlot.copy();
            if (index >= 0 && index <= 15) {
                //from table to inventory
                if (!this.insertItem(stackInSlot, 16, 52, false)) {
                    return ItemStack.EMPTY;
                }
            } else if (index >= 16 && index <= 51) {
                //from inventory to table
                if (stackInSlot.getItem() instanceof ModularItem<?>) {
                    if (!this.insertItem(stackInSlot, 0, 1, false)) {
                        return ItemStack.EMPTY;
                    }
                } else if (stackInSlot.getItem() instanceof Module) {
                    if (!this.insertItem(stackInSlot, 1, 15, false)) {
                        return ItemStack.EMPTY;
                    }
                }
            }
            if (stackInSlot.isEmpty()) {
                slot.setStack(ItemStack.EMPTY);
            } else {
                slot.markDirty();
            }
            if (stackInSlot.getCount() == backupStack.getCount()) {
                return ItemStack.EMPTY;
            }
            slot.onTakeItem(player, stackInSlot);
            return stackInSlot;
        }
        return ItemStack.EMPTY;
    }

    protected void updateStats(ItemStack stack) {
        if (stack.getItem() instanceof ModularItem<?> modularItem) {
            setCurrentCapacity(modularItem.getUsedCapacity(stack));
            setCurrentEnergy(modularItem.getUsedEnergy(stack));
            setMaxCapacity(modularItem.getMaxCapacity());
            setMaxEnergy(modularItem.getTotalGeneratedEnergy(stack));
        }
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return true;
    }

    @Override
    public ScreenHandlerType<?> getType() {
        return type;
    }

    public int getCurrentCapacity() {
        return statsPropertyDelegate.get(0);
    }

    protected void setCurrentCapacity(int capacity) {
        statsPropertyDelegate.set(0, capacity);
    }

    public int getMaxCapacity() {
        return statsPropertyDelegate.get(1);
    }

    protected void setMaxCapacity(int maxCapacity) {
        statsPropertyDelegate.set(1, maxCapacity);
    }

    public int getCurrentEnergy() {
        return statsPropertyDelegate.get(2);
    }

    protected void setCurrentEnergy(int energy) {
        statsPropertyDelegate.set(2, energy);
    }

    public int getMaxEnergy() {
        return statsPropertyDelegate.get(3);
    }

    protected void setMaxEnergy(int maxEnergy) {
        statsPropertyDelegate.set(3, maxEnergy);
    }






}
