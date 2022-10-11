package grauly.item;

import grauly.modules.ExoModule;
import net.minecraft.item.Item;

public class ExampleModule extends Item implements ExoModule {

    public ExampleModule(Settings settings) {
        super(settings);
    }

    @Override
    public int getArmor() {
        return 0;
    }

    @Override
    public int getToughness() {
        return 0;
    }

    @Override
    public int getSlotCost() {
        return 0;
    }

    @Override
    public int getUpkeepCost() {
        return 0;
    }

    @Override
    public int getUseCost() {
        return 0;
    }

    @Override
    public String getDescription() {
        return "Hello!";
    }

    @Override
    public void tick() {

    }
}
