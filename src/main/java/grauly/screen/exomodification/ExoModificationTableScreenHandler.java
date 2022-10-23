package grauly.screen.exomodification;

import grauly.screen.AllScreenHandlers;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.entity.player.PlayerInventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.ScreenHandler;
import net.minecraft.screen.ScreenHandlerType;
import org.jetbrains.annotations.Nullable;

public class ExoModificationTableScreenHandler extends ScreenHandler {

    protected final ScreenHandlerType<?> type;

    public ExoModificationTableScreenHandler(int syncID, PlayerInventory inventory) {
        this(AllScreenHandlers.EXO_MODIFICATION_TABLE_SCREEN_HANDLER,syncID);
    }

    protected ExoModificationTableScreenHandler(@Nullable ScreenHandlerType<?> type, int syncId) {
        super(type, syncId);
        this.type = type;
    }

    @Override
    public ItemStack transferSlot(PlayerEntity player, int index) {
        return null;
    }

    @Override
    public boolean canUse(PlayerEntity player) {
        return false;
    }

    @Override
    public ScreenHandlerType<?> getType() {
        return type;
    }
}
