package grauly.screen.modification;

import grauly.modules.base.ModularItem;
import grauly.modules.base.Module;
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

public class ModificationTableScreenHandler extends ScreenHandler {

    protected CraftingResultInventory output = new CraftingResultInventory();
    protected Inventory moduleInput = new SimpleInventory(15) {

        @Override
        public void markDirty() {
            super.markDirty();
            ModificationTableScreenHandler.this.onModulesChanged(this);
        }
    };
    protected Inventory modularItemInput = new SimpleInventory(1) {

        @Override
        public void markDirty() {
            super.markDirty();
            ModificationTableScreenHandler.this.onModularItemChanged(this);
        }
    };

    private final ScreenHandlerType<?> type;

    public ModificationTableScreenHandler(int syncId, PlayerInventory inventory) {
        this(AllScreenHandlers.MODIFICATION_TABLE_SCREEN_HANDLER, syncId, inventory);
    }

    protected ModificationTableScreenHandler(@Nullable ScreenHandlerType<?> type, int syncId, PlayerInventory playerInventory) {
        super(type, syncId);
        this.type = type;
        //construct Slots
        this.addSlot(new ModularItemSlot(modularItemInput,0,26,34));
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 5; j++) {
                this.addSlot(new ModuleSlot(moduleInput,j + i * 5,71 + j*18, 16 + i*18));
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

    }

    public void onModularItemChanged(Inventory inventory) {

    }
}
