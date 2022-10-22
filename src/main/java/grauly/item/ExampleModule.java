package grauly.item;

import grauly.modules.exo.ExoModule;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;

public class ExampleModule extends Item implements ExoModule {

    public ExampleModule(Settings settings) {
        super(settings);
    }

    @Override
    public int getArmor() {
        return 1;
    }

    @Override
    public int getToughness() {
        return 1;
    }

    @Override
    public int getSlotCost() {
        return 1;
    }

    @Override
    public int getEnergyUpkeepCost() {
        return 5;
    }

    @Override
    public int getEnergyGeneration() {
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
    public void tick(ItemStack stack, LivingEntity entity) {

    }


}
