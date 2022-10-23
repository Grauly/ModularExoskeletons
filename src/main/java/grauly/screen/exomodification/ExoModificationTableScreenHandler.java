package grauly.screen.exomodification;

import grauly.screen.AllScreenHandlers;
import grauly.screen.modification.ModificationTableScreenHandler;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandlerType;
import org.jetbrains.annotations.Nullable;

public class ExoModificationTableScreenHandler extends ModificationTableScreenHandler {

    protected ExoModificationTablePropertyDelegate statsDelegate = new ExoModificationTablePropertyDelegate();

    public ExoModificationTableScreenHandler(int syncId, PlayerInventory inventory) {
        this(AllScreenHandlers.EXO_MODIFICATION_TABLE_SCREEN_HANDLER, syncId, inventory);
    }

    protected ExoModificationTableScreenHandler(@Nullable ScreenHandlerType<?> type, int syncId, PlayerInventory playerInventory) {
        super(type, syncId, playerInventory);
    }

    @Override
    protected void registerProperties() {
        this.addProperties(statsPropertyDelegate);
    }

    @Override
    public ItemStack transferSlot(PlayerEntity player, int index) {
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
}
