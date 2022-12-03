package grauly.item.exomodules.utilities;

import grauly.item.exomodules.ExoModuleItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Pair;

import java.util.ArrayList;

public class ExoSpeedModule extends ExoModuleItem {

    public ExoSpeedModule(Settings settings) {
        super(settings);
    }

    @Override
    public int getSlotCost() {
        return 2;
    }

    @Override
    public int getEnergyUpkeepCost() {
        return 2;
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

        return Text.translatable("modularexos.module.speed.description").getString();
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
        var list = super.getAttributeModifiers();
        list.add(new Pair<>(EntityAttributes.GENERIC_MOVEMENT_SPEED,new EntityAttributeModifier("modularexos:exo_speed_boost",0.05, EntityAttributeModifier.Operation.ADDITION)));
        return list;
    }
}
