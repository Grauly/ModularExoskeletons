package grauly.screen.exomodification;

import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketsApi;
import grauly.item.ExoItem;
import grauly.screen.AllScreenHandlers;
import grauly.screen.slots.LockedSlot;
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

    protected ExoModificationTablePropertyDelegate statsPropertyDelegate = new ExoModificationTablePropertyDelegate();
    protected ItemStack currentModifyingStack = ItemStack.EMPTY;
    protected final ArrayList<ModuleSlot> moduleInputSlots = new ArrayList<>();

    protected Inventory moduleInputInventory = new SimpleInventory(15) {
        @Override
        public void markDirty() {
            super.markDirty();
            ExoModificationTableScreenHandler.this.onModulesChanged(this);
        }
    };

    public ExoModificationTableScreenHandler(int syncId, PlayerInventory inventory) {
        this(AllScreenHandlers.EXO_MODIFICATION_TABLE_SCREEN_HANDLER, syncId, inventory);
    }

    protected ExoModificationTableScreenHandler(@Nullable ScreenHandlerType<?> type, int syncId, PlayerInventory playerInventory) {
        super(type, syncId);

        registerProperties();
        buildSlots(playerInventory);
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
            this.addSlot(new Slot(playerInventory, 39 - i, -27, 8 + i*18));
        }
        //exo slot
        Inventory inventory = new SimpleInventory(1);
        var optionalTrinketComponent = TrinketsApi.getTrinketComponent(playerInventory.player);
        if(optionalTrinketComponent.isPresent()) {
            var trinketComponent = optionalTrinketComponent.get();
            var chest = trinketComponent.getInventory().get("chest");
            if(chest != null) {
                var exo = chest.get("exo");
                inventory = exo;
            }
        }
        this.addSlot(new Slot(inventory,0,-27, 8 + 4*18));
    }

    protected void onModulesChanged(Inventory inventory) {

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
