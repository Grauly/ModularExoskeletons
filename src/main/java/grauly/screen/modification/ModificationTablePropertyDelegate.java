package grauly.screen.modification;

import net.minecraft.screen.PropertyDelegate;

/**
 * wraps an array.
 * Intended assignments:
 * 0: Current Capacity
 * 1: Max Capacity
 * 2: Current Energy
 * 3: Max Energy
 */
public class ModificationTablePropertyDelegate implements PropertyDelegate {

    int[] values = new int[4];

    @Override
    public int get(int index) {
        return values[index];
    }

    @Override
    public void set(int index, int value) {
        values[index] = value;
    }

    @Override
    public int size() {
        return 4;
    }
}
