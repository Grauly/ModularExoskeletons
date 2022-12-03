package grauly.item.exomodules.power;

import grauly.item.exomodules.ExoModuleItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Pair;

import java.util.ArrayList;

public class CompactPowerPlantModule extends ExoModuleItem {

    public CompactPowerPlantModule(Settings settings) {
        super(settings);
    }

    @Override
    public int getSlotCost() {
        return 1;
    }

    @Override
    public int getEnergyUpkeepCost() {
        return 0;
    }

    @Override
    public int getEnergyGeneration() {
        return 3;
    }

    @Override
    public int getUseCost() {
        return 0;
    }

    @Override
    public String getDescription() {
        return Text.translatable("modularexos.module.power.description").getString();
    }

    @Override
    public void moduleTick(ItemStack stack, LivingEntity entity) {

    }

    @Override
    public void onEquip(ItemStack modularItem, LivingEntity equipped) {

    }

    @Override
    public void onUnEquip(ItemStack modularItem, LivingEntity equipped) {

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
    public ArrayList<Pair<EntityAttribute, EntityAttributeModifier>> getAttributeModifiers() {
        return super.getAttributeModifiers();
    }
}
