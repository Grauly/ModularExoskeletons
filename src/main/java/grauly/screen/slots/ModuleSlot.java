package grauly.screen.slots;

import grauly.modules.base.Module;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.slot.Slot;

import java.util.ArrayList;

public class ModuleSlot extends Slot {

    protected ArrayList<Module> allowedModules = new ArrayList<>();

    public ModuleSlot(Inventory inventory, int index, int x, int y) {
        super(inventory, index, x, y);
    }

    public void updateFilter(ArrayList<? extends Module> allowedModules) {
        this.allowedModules = new ArrayList<>(allowedModules);
    }

    @Override
    public boolean canInsert(ItemStack stack) {
        if(stack.getItem() instanceof Module) {
            return allowedModules.contains(stack.getItem());
        }
        return false;
    }
}
