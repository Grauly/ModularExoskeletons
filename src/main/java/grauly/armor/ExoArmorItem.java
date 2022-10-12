package grauly.armor;

import grauly.modules.exo.ExoModule;
import grauly.modules.base.ModularItem;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.item.ArmorItem;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.ItemStack;
import net.minecraft.world.World;

import java.util.ArrayList;

public class ExoArmorItem extends ArmorItem implements ModularItem<ExoModule> {

    protected final ArrayList<ExoModule> allowedModulesList = new ArrayList<>();

    public ExoArmorItem(ArmorMaterial material, EquipmentSlot slot, Settings settings) {
        super(material, slot, settings);
    }


    @Override
    public void inventoryTick(ItemStack stack, World world, Entity entity, int slot, boolean selected) {
        super.inventoryTick(stack, world, entity, slot, selected);
        if(!(slot >= 100 && slot <= 103)) {
            return;
        }
        int armor = 0;
        int toughness = 0;
        var installedModules = this.getInstalledModules(stack);
        for(ExoModule module : installedModules) {
            armor += module.getArmor();
            toughness += module.getToughness();
        }
        stack.addAttributeModifier(EntityAttributes.GENERIC_ARMOR,new EntityAttributeModifier("Modular Armor",armor, EntityAttributeModifier.Operation.ADDITION),this.slot);
        stack.addAttributeModifier(EntityAttributes.GENERIC_ARMOR_TOUGHNESS, new EntityAttributeModifier("Modular Toughness",toughness, EntityAttributeModifier.Operation.ADDITION),this.slot);
    }

    @Override
    public ArrayList<ExoModule> getAllowedModules() {
        return allowedModulesList;
    }

    @Override
    public void addAllowedModule(ExoModule module) {
        allowedModulesList.add(module);
    }
}
