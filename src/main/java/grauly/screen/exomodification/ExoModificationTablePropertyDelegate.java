package grauly.screen.exomodification;

import net.minecraft.screen.PropertyDelegate;

/**
 * wraps an array.
 * Intended assignments:
 * 0: Current Capacity
 * 1: Max Capacity
 * 2: Current Energy
 * 3: Max Energy
 * 4: Selected Slot
 */
public class ExoModificationTablePropertyDelegate implements PropertyDelegate {

    int[] array = new int[5];

    @Override
    public int get(int index) {
        return array[index];
    }

    @Override
    public void set(int index, int value) {
        array[index] = value;
    }

    @Override
    public int size() {
        return 5;
    }

    public void setCurrentCapacity(int currentCapacity) {
        set(0,currentCapacity);
    }

    public void setMaxCapacity(int maxCapacity) {
        set(1,maxCapacity);
    }

    public void setCurrentEnergy(int currentEnergy) {
        set(2,currentEnergy);
    }

    public void setMaxEnergy(int maxEnergy) {
        set(3,maxEnergy);
    }

    public void setSelectedSlot(int slot) {
        set(4,slot);
    }

    public int getCurrentCapacity() {
        return get(0);
    }

    public int getMaxCapacity() {
        return get(1);
    }

    public int getCurrentEnergy() {
        return get(2);
    }

    public int getMaxEnergy() {
        return get(3);
    }

    public int getSelectedSlot() {
        return get(4);
    }
}
