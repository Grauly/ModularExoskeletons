package grauly.item.exomodules;

import grauly.modules.exo.ExoModule;
import net.minecraft.client.item.TooltipContext;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.Pair;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.ArrayList;
import java.util.List;

public abstract class ExoModuleItem extends Item implements ExoModule {

    public ExoModuleItem(Settings settings) {
        super(settings);
    }

    @Override
    public void appendTooltip(ItemStack stack, @Nullable World world, List<Text> tooltip, TooltipContext context) {
        super.appendTooltip(stack, world, tooltip, context);
        tooltip.add(Text.translatable("modularexos.module.capacity_cost").append(String.valueOf(this.getSlotCost())));
        tooltip.add(Text.translatable("modularexos.module.energy_cost").append(String.valueOf(this.getEnergyUpkeepCost())));
        if (this.getEnergyGeneration() > 0) {
            tooltip.add(Text.translatable("modularexos.module.energy_generation").append(String.valueOf(this.getEnergyGeneration())));
        }
        if (this.getArmor() > 0) {
            tooltip.add(Text.translatable("modularexos.module.armor").append(String.valueOf(this.getArmor())));
        }
        if (this.getToughness() > 0) {
            tooltip.add(Text.translatable("modularexos.module.toughness").append(String.valueOf(this.getToughness())));
        }
        tooltip.add(Text.of(this.getDescription()));
    }

    @Override
    public ArrayList<Pair<EntityAttribute, EntityAttributeModifier>> getAttributeModifiers() {
        ArrayList<Pair<EntityAttribute, EntityAttributeModifier>> modifiers = new ArrayList<>();
        if(getArmor() > 0) {
            modifiers.add(new Pair<>(EntityAttributes.GENERIC_ARMOR,new EntityAttributeModifier("modularexos:exo_armor_addition",getArmor(), EntityAttributeModifier.Operation.ADDITION)));
        }
        if(getToughness() > 0) {
            modifiers.add(new Pair<>(EntityAttributes.GENERIC_ARMOR_TOUGHNESS,new EntityAttributeModifier("modularexos:exo_toughness_addition",getToughness(), EntityAttributeModifier.Operation.ADDITION)));
        }
        return modifiers;
    }
}
