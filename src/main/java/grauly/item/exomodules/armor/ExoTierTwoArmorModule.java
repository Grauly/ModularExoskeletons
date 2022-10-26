package grauly.item.exomodules.armor;

import grauly.item.exomodules.ExoModuleItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Pair;

import java.util.ArrayList;

public class ExoTierTwoArmorModule extends ExoModuleItem {

    public ExoTierTwoArmorModule(Settings settings) {
        super(settings);
    }

    @Override
    public int getSlotCost() {
        return 4;
    }

    @Override
    public int getEnergyUpkeepCost() {
        return 0;
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
        return Text.translatable("modularexos.module.armor.description").getString();
    }

    @Override
    public void moduleTick(ItemStack stack, LivingEntity entity) {

    }

    @Override
    public int getArmor() {
        return 3;
    }

    @Override
    public int getToughness() {
        return 1;
    }

    @Override
    public ArrayList<Pair<EntityAttribute, EntityAttributeModifier>> getAttributeModifiers() {
        return super.getAttributeModifiers();
    }
}
