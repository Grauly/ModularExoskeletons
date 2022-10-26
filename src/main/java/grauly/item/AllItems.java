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
import net.fabricmc.fabric.api.client.rendering.v1.ArmorRenderer;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.registry.Registry;


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
        Registry.register(Registry.ITEM, id("exo_item"), EXO_ITEM);
        Registry.register(Registry.ITEM, id("exo_helmet"), EXO_HELMET);
        Registry.register(Registry.ITEM, id("exo_chestplate"), EXO_CHESTPLATE);
        Registry.register(Registry.ITEM, id("exo_leggings"), EXO_LEGGING);
        Registry.register(Registry.ITEM, id("exo_boots"), EXO_BOOTS);
        Registry.register(Registry.ITEM, id("example_module"), EX_MODULE);

        Registry.register(Registry.ITEM, id("simple_power_plant_module"), SIMPLE_POWER_PLANT_MODULE);
        Registry.register(Registry.ITEM, id("compact_power_plant_module"), COMPACT_POWER_PLANT_MODULE);
        Registry.register(Registry.ITEM, id("expensive_power_plant_module"), EXPENSIVE_POWER_PLANT_MODULE);

        Registry.register(Registry.ITEM, id("basic_armor_module"), EXO_TIER_ONE_ARMOR_MODULE);
        Registry.register(Registry.ITEM, id("advanced_armor_module"), EXO_TIER_TWO_ARMOR_MODULE);
        Registry.register(Registry.ITEM, id("powered_armor_module"), EXO_TIER_THREE_ARMOR_MODULE);

        Registry.register(Registry.ITEM, id("modification_table"), new BlockItem(AllBlocks.MODIFICATION_TABLE, settings));
        Registry.register(Registry.ITEM, id("exo_modification_table"), new BlockItem(AllBlocks.EXO_MODIFICATION_TABLE, settings));
    }
}
