package grauly.screen.slots;

import grauly.modules.base.Module;
import net.minecraft.inventory.Inventory;
import net.minecraft.item.ItemStack;
import net.minecraft.screen.PropertyDelegate;
import net.minecraft.screen.slot.Slot;

import java.util.ArrayList;

public class ModuleSlot extends Slot {

    protected ArrayList<Module> allowedModules = new ArrayList<>();
    protected PropertyDelegate modularProperties;

    public ModuleSlot(Inventory inventory, int index, int x, int y, PropertyDelegate delegate) {
        super(inventory, index, x, y);
        this.modularProperties = delegate;
    }

    public void updateFilter(ArrayList<? extends Module> allowedModules) {
        this.allowedModules = new ArrayList<>(allowedModules);
    }

    @Override
    public boolean canInsert(ItemStack stack) {
        if(!(stack.getItem() instanceof Module)) {
            return false;
        }
        if(!allowedModules.contains(stack.getItem())) {
            return false;
        }
        Module module = (Module) stack.getItem();
        if(module.getSlotCost() + getCurrentCapacity() > getMaxCapacity()) {
            return false;
        }
        if(module.getEnergyUpkeepCost() + getCurrentEnergy() > getMaxEnergy()) {
            return false;
        }
        return true;
    }
    protected int getCurrentCapacity() {
        return modularProperties.get(0);
    }

    protected int getMaxCapacity() {
        return modularProperties.get(1);
    }

    protected int getCurrentEnergy() {
        return modularProperties.get(2);
    }

    protected int getMaxEnergy() {
        return modularProperties.get(3);
    }

}
