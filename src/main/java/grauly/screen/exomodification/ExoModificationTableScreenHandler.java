package grauly.screen.exomodification;

import dev.emi.trinkets.api.TrinketsApi;
import grauly.modules.base.ModularItem;
import grauly.screen.AllScreenHandlers;
import grauly.screen.slots.ModuleSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.slot.Slot;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;

public class ExoModificationTableScreenHandler extends ScreenHandler {

    protected final ArrayList<ModuleSlot> moduleInputSlots = new ArrayList<>();
    protected ExoModificationTablePropertyDelegate statsPropertyDelegate = new ExoModificationTablePropertyDelegate();
    protected ItemStack currentModifyingStack = ItemStack.EMPTY;
    protected Inventory moduleInputInventory = new SimpleInventory(15) {
        @Override
        public void markDirty() {
            super.markDirty();
            ExoModificationTableScreenHandler.this.onModulesChanged();
        }
    };

    public ExoModificationTableScreenHandler(int syncId, PlayerInventory inventory) {
        this(AllScreenHandlers.EXO_MODIFICATION_TABLE_SCREEN_HANDLER, syncId, inventory);
    }

    protected ExoModificationTableScreenHandler(@Nullable ScreenHandlerType<?> type, int syncId, PlayerInventory playerInventory) {
        super(type, syncId);

        registerProperties();
        buildSlots(playerInventory);
        setEditingStack(-1);
    }


    protected void registerProperties() {
        this.addProperties(statsPropertyDelegate);
    }

    /**
     * Resulting Layout:
     * 0 - 14   modules
     * 15 - 41  inventory
     * 42 - 50  hotbar
     * 51 - 54  armor
     * 55       exo trinket
     *
     * @param playerInventory
     */
    protected void buildSlots(PlayerInventory playerInventory) {
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
        for (int i = 0; i < 4; i++) {
            this.addSlot(new Slot(playerInventory, 39 - i, -27, 8 + i * 18));
        }
        //exo slot
        Inventory inventory = new SimpleInventory(1);
        var optionalTrinketComponent = TrinketsApi.getTrinketComponent(playerInventory.player);
        if (optionalTrinketComponent.isPresent()) {
            var trinketComponent = optionalTrinketComponent.get();
            var chest = trinketComponent.getInventory().get("chest");
            if (chest != null) {
                var exo = chest.get("exo");
                inventory = exo;
            }
        }
        this.addSlot(new Slot(inventory, 0, -27, 8 + 4 * 18));
    }

    protected void updateStats() {
        int totalPowerGen = 0;
        int totalPowerUse = 0;
        for (int i = 0; i < 5; i++) {
            var slotIndex = 56 - (5 - i);
            var stack = this.getSlot(slotIndex).getStack();
            if(stack.getItem() instanceof ModularItem<?> modularItem) {
                totalPowerGen += modularItem.getTotalGeneratedEnergy(stack);
                totalPowerUse += modularItem.getUsedEnergy(stack);
            }
        }
        statsPropertyDelegate.setMaxEnergy(totalPowerGen);
        statsPropertyDelegate.setCurrentEnergy(totalPowerUse);
        if(currentModifyingStack.getItem() instanceof ModularItem<?> modularItem) {
            statsPropertyDelegate.setCurrentCapacity(modularItem.getUsedCapacity(currentModifyingStack));
            statsPropertyDelegate.setMaxCapacity(modularItem.getMaxCapacity());
        } else {
            statsPropertyDelegate.setMaxCapacity(0);
            statsPropertyDelegate.setCurrentCapacity(0);
        }
    }

    protected void onModulesChanged() {
        if(currentModifyingStack.equals(ItemStack.EMPTY)) {
            return;
        }
        if(currentModifyingStack.getItem() instanceof ModularItem<?> modularItem) {
            ArrayList<ItemStack> modules = new ArrayList<>();
            for (int i = 0; i < 15; i++) {
                modules.add(moduleInputInventory.getStack(i));
            }
            modularItem.serializeModules(modules,currentModifyingStack);
            updateStats();
        }
    }

    protected void prepareEditing() {
        moduleInputInventory.clear();
        moduleInputSlots.forEach(s -> s.updateFilter(new ArrayList<>()));
        if(currentModifyingStack.equals(ItemStack.EMPTY)) {
            return;
        }
        if(currentModifyingStack.getItem() instanceof ModularItem<?> modularItem) {
            var modules = modularItem.deserializeModules(currentModifyingStack);
            for (int i = 0; i < Math.min(15,modules.size()); i++) {
                moduleInputInventory.setStack(i,modules.get(i));
                moduleInputSlots.forEach(s -> s.updateFilter(modularItem.getAllowedModules()));
            }
        }
    }
    public void setEditingStack(int slot) {
        statsPropertyDelegate.setSelectedSlot(slot);
        if (slot != -1) {
            var slotIndex = 56 - (5 - slot);
            if(this.getSlot(slotIndex).hasStack()) {
                currentModifyingStack =  this.getSlot(slotIndex).getStack();
                prepareEditing();
                return;
            }
        }
        currentModifyingStack = ItemStack.EMPTY;
    }

    @Override
    public ItemStack transferSlot(PlayerEntity player, int index) {
        return ItemStack.EMPTY;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return true;
    }

}
