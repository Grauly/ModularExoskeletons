package grauly.modules.base;

import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;

public interface Module {
    int getSlotCost();
    int getEnergyUpkeepCost();
    int getEnergyGeneration();
    int getUseCost();
    String getDescription();
    void moduleTick(ItemStack modularItem, LivingEntity entity);
    void onEquip(ItemStack modularItem, LivingEntity equipped);
    void onUnEquip(ItemStack modularItem, LivingEntity equipped);
}
