package grauly.modules.base;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;

public interface Module {
    int getSlotCost();
    int getEnergyUpkeepCost();
    int getEnergyGeneration();
    int getUseCost();
    String getDescription();
    void tick(ItemStack stack, LivingEntity entity);
}
