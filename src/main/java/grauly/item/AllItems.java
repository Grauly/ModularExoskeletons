package grauly.item;

import grauly.ModularExos;
import grauly.armor.ExoArmorItem;
import grauly.armor.ExoArmorMaterial;
import grauly.block.AllBlocks;
import grauly.item.exomodules.armor.ExoTierOneArmorModule;
import grauly.item.exomodules.armor.ExoTierThreeArmorModule;
import grauly.item.exomodules.armor.ExoTierTwoArmorModule;
import grauly.item.exomodules.power.CompactPowerPlantModule;
import grauly.item.exomodules.power.ExpensivePowerPlantModule;
import grauly.item.exomodules.power.SimplePowerPlantModule;
import grauly.util.RegistryHelper;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;


public class AllItems implements RegistryHelper {
    private static final ArmorMaterial EXO_MATERIAL = new ExoArmorMaterial();
    private static final Item.Settings settings = new Item.Settings().group(ModularExos.EXO_GROUP).maxCount(1);
    public static final ExoItem EXO_ITEM = new ExoItem(settings);
    public static final ExoArmorItem EXO_HELMET = new ExoArmorItem(EXO_MATERIAL, EquipmentSlot.HEAD, settings);
    public static final ExoArmorItem EXO_CHESTPLATE = new ExoArmorItem(EXO_MATERIAL, EquipmentSlot.CHEST, settings);
    public static final ExoArmorItem EXO_LEGGING = new ExoArmorItem(EXO_MATERIAL, EquipmentSlot.LEGS, settings);
    public static final ExoArmorItem EXO_BOOTS = new ExoArmorItem(EXO_MATERIAL, EquipmentSlot.FEET, settings);
    public static final ExampleModule EX_MODULE = new ExampleModule(settings);
    public static final SimplePowerPlantModule SIMPLE_POWER_PLANT_MODULE = new SimplePowerPlantModule(settings);
    public static final CompactPowerPlantModule COMPACT_POWER_PLANT_MODULE = new CompactPowerPlantModule(settings);
    public static final ExpensivePowerPlantModule EXPENSIVE_POWER_PLANT_MODULE = new ExpensivePowerPlantModule(settings);
    public static final ExoTierOneArmorModule EXO_TIER_ONE_ARMOR_MODULE = new ExoTierOneArmorModule(settings);
    public static final ExoTierTwoArmorModule EXO_TIER_TWO_ARMOR_MODULE = new ExoTierTwoArmorModule(settings);
    public static final ExoTierThreeArmorModule EXO_TIER_THREE_ARMOR_MODULE = new ExoTierThreeArmorModule(settings);

    public void register() {
        item("exo_item", EXO_ITEM);
        item("exo_helmet", EXO_HELMET);
        item("exo_chestplate", EXO_CHESTPLATE);
        item("exo_leggings", EXO_LEGGING);
        item("exo_boots", EXO_BOOTS);
        item("example_module", EX_MODULE);

        item("simple_power_plant_module", SIMPLE_POWER_PLANT_MODULE);
        item("compact_power_plant_module", COMPACT_POWER_PLANT_MODULE);
        item("expensive_power_plant_module", EXPENSIVE_POWER_PLANT_MODULE);

        item("basic_armor_module", EXO_TIER_ONE_ARMOR_MODULE);
        item("advanced_armor_module", EXO_TIER_TWO_ARMOR_MODULE);
        item("powered_armor_module", EXO_TIER_THREE_ARMOR_MODULE);

        item("modification_table", new BlockItem(AllBlocks.MODIFICATION_TABLE, settings));
        item("exo_modification_table", new BlockItem(AllBlocks.EXO_MODIFICATION_TABLE, settings));
    }
}
