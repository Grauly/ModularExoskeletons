package grauly.item.exomodules.utilities;

import grauly.item.exomodules.ExoModuleItem;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;

public class ExoJumpModule extends ExoModuleItem {

    private static final StatusEffectInstance JUMP_BOOST = new StatusEffectInstance(StatusEffects.JUMP_BOOST, 2 * 20, 0, false, false, true);

    public ExoJumpModule(Settings settings) {
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
        return Text.translatable("modularexos.module.jump.description").getString();
    }

    @Override
    public void moduleTick(ItemStack stack, LivingEntity entity) {
        if (entity.hasStatusEffect(StatusEffects.JUMP_BOOST)) {
            if (entity.getStatusEffect(StatusEffects.JUMP_BOOST).getAmplifier() > 1) {
                return;
            }
        }
        entity.addStatusEffect(JUMP_BOOST);
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
}
