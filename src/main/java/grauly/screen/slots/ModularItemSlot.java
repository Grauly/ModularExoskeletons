package grauly.screen.slots;

import grauly.modules.base.ModularItem;
import grauly.modules.base.Module;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;

public class ModularItemSlot extends Slot {
    public ModularItemSlot(Inventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
    }

    @Override
    public boolean canInsert(ItemStack stack) {
        return stack.getItem() instanceof ModularItem<?>;
    }
}
