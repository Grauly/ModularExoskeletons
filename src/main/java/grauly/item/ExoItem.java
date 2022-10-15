package grauly.item;

import com.google.common.collect.Multimap;
import dev.emi.trinkets.api.SlotAttributes;
import dev.emi.trinkets.api.SlotReference;
import dev.emi.trinkets.api.TrinketItem;
import grauly.modules.base.ModularItem;
import grauly.modules.exo.ExoModule;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

import java.util.ArrayList;
import java.util.UUID;

public class ExoItem extends TrinketItem implements ModularItem<ExoModule> {

    protected static final UUID SPRINT_SPEED_MODIFIER_UUID = UUID.fromString("b0373195-b96a-4271-852e-d5f05d46f345");
    protected static final EntityAttributeModifier SPRINT_SPEED_MODIFIER = new EntityAttributeModifier(SPRINT_SPEED_MODIFIER_UUID, "modularexos:sprintspeedbonus", 0.03f, EntityAttributeModifier.Operation.ADDITION);
    protected static UUID TEST_UUID;
    protected final ArrayList<ExoModule> allowedModuleList = new ArrayList<>();

    public ExoItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        return super.use(world, user, hand);
    }

    @Override
    public ArrayList<ExoModule> getAllowedModules() {
        return allowedModuleList;
    }

    @Override
    public void addAllowedModule(ExoModule module) {
        allowedModuleList.add(module);
    }

    @Override
    public void recalculateStats(ItemStack stack) {

    }

    @Override
    public void onUnequip(ItemStack stack, SlotReference slot, LivingEntity entity) {
        super.onUnequip(stack, slot, entity);
        var attributeInstance = entity.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED);
        if (attributeInstance != null) {
            if (attributeInstance.hasModifier(SPRINT_SPEED_MODIFIER)) {
                attributeInstance.removeModifier(SPRINT_SPEED_MODIFIER);
            }
        }
    }

    @Override
    public void tick(ItemStack stack, SlotReference slot, LivingEntity entity) {
        var attributeInstance = entity.getAttributeInstance(EntityAttributes.GENERIC_MOVEMENT_SPEED);
        if (attributeInstance != null)
            if (entity.isSprinting()) {
                if (!attributeInstance.hasModifier(SPRINT_SPEED_MODIFIER)) {
                    attributeInstance.addTemporaryModifier(SPRINT_SPEED_MODIFIER);
                }
            } else {
                if (attributeInstance.hasModifier(SPRINT_SPEED_MODIFIER)) {
                    attributeInstance.removeModifier(SPRINT_SPEED_MODIFIER);
                }
            }
        super.tick(stack, slot, entity);
    }

    @Override
    public Multimap<EntityAttribute, EntityAttributeModifier> getModifiers(ItemStack stack, SlotReference slot, LivingEntity entity, UUID uuid) {
        var modifiers = super.getModifiers(stack, slot, entity, uuid);
        SlotAttributes.addSlotModifier(modifiers, "chest/back", uuid, 1, EntityAttributeModifier.Operation.ADDITION);
        TEST_UUID = uuid;
        return modifiers;
    }
}
