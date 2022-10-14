package grauly.screen.modification;

import grauly.modules.base.ModularItem;
import grauly.screen.AllScreenHandlers;
import grauly.screen.slots.ModularItemSlot;
import grauly.screen.slots.ModuleSlot;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.inventory.CraftingResultInventory;
import net.minecraft.inventory.Inventory;
import net.minecraft.inventory.SimpleInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.screen.slot.Slot;
import org.jetbrains.annotations.Nullable;

import java.lang.reflect.Array;
import java.util.ArrayList;

public class ModificationTableScreenHandler extends ScreenHandler {

    protected CraftingResultInventory output = new CraftingResultInventory();
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
    private final ScreenHandlerType<?> type;
    private final ArrayList<ModuleSlot> moduleInputSlots = new ArrayList<>();

    public ModificationTableScreenHandler(int syncId, PlayerInventory inventory) {
        this(AllScreenHandlers.MODIFICATION_TABLE_SCREEN_HANDLER, syncId, inventory);
    }

    protected ModificationTableScreenHandler(@Nullable ScreenHandlerType<?> type, int syncId, PlayerInventory playerInventory) {
        super(type, syncId);
        this.type = type;
        //construct Slots
        this.addSlot(new ModularItemSlot(modularItemInputInventory,0,26,34));
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                var moduleSlot = new ModuleSlot(moduleInputInventory,j + i * 5,71 + j*18, 16 + i*18);
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



    @Override
    public ItemStack transferSlot(PlayerEntity player, int index) {
        var stack = player.getInventory().getStack(index);
        return ItemStack.EMPTY;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return true;
    }

    @Override
    public ScreenHandlerType<?> getType() {
        return type;
    }

    public void onModulesChanged(Inventory inventory) {
        var modularItemInputStack = modularItemInputInventory.getStack(0);
        if(!modularItemInputStack.equals(ItemStack.EMPTY)) {
            var item = modularItemInputStack.getItem();
            if(item instanceof ModularItem<?> modularItem) {
                ArrayList<ItemStack> modules = new ArrayList<>();
                for (int i = 0; i < 15; i++) {
                    var moduleStack = inventory.getStack(i);
                    if(!moduleStack.equals(ItemStack.EMPTY)) {
                        modules.add(moduleStack);
                    }
                }
                modularItem.serializeModules(modules,modularItemInputStack);
            }
        }
    }

    public void onModularItemChanged(Inventory inventory) {
        var stack = inventory.getStack(0);
        if(stack.equals(ItemStack.EMPTY)) {
            moduleInputInventory.clear();
            moduleInputSlots.forEach(m -> {
                m.updateFilter(new ArrayList<>());
            });
        } else {
            var item = stack.getItem();
            if(item instanceof ModularItem<?> modularItem) {
                moduleInputSlots.forEach(m -> {
                    m.updateFilter(modularItem.getAllowedModules());
                });
                ArrayList<ItemStack> deserializedModules = modularItem.deserializeModules(stack);
                int length = deserializedModules.size();
                if(length > 15) {
                    length = 15;
                }
                for (int i = 0; i < length; i++) {
                    moduleInputInventory.setStack(i,deserializedModules.get(i));
                }
            }
        }
    }
}
