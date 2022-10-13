package grauly.item;

import grauly.ModularExos;
import grauly.armor.ExoArmorItem;
import grauly.armor.ExoArmorMaterial;
import grauly.block.AllBlocks;
import grauly.util.RegistryHelper;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.ArmorMaterial;
import net.minecraft.item.BlockItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;


public class AllItems implements RegistryHelper {
    private static final ArmorMaterial EXO_MATERIAL = new ExoArmorMaterial();
    private static final Item.Settings settings = new Item.Settings().group(ModularExos.EXO_GROUP);
    public static final ExoItem EXO_ITEM = new ExoItem(settings);
    public static final ExoArmorItem EXO_HELMET = new ExoArmorItem(EXO_MATERIAL, EquipmentSlot.HEAD, settings);
    public static final ExoArmorItem EXO_CHESTPLATE = new ExoArmorItem(EXO_MATERIAL, EquipmentSlot.CHEST, settings);
    public static final ExoArmorItem EXO_LEGGING = new ExoArmorItem(EXO_MATERIAL, EquipmentSlot.LEGS, settings);
    public static final ExoArmorItem EXO_BOOTS = new ExoArmorItem(EXO_MATERIAL, EquipmentSlot.FEET, settings);
    public static final ExampleModule EX_MODULE = new ExampleModule(settings);

    public void register() {
        Registry.register(Registry.ITEM, id("exo_item"), EXO_ITEM);
        Registry.register(Registry.ITEM, id("exo_helmet"), EXO_HELMET);
        Registry.register(Registry.ITEM, id("exo_chestplate"), EXO_CHESTPLATE);
        Registry.register(Registry.ITEM, id("exo_leggings"), EXO_LEGGING);
        Registry.register(Registry.ITEM, id("exo_boots"), EXO_BOOTS);
        Registry.register(Registry.ITEM, id("example_module"), EX_MODULE);

        Registry.register(Registry.ITEM, id("modification_table"), new BlockItem(AllBlocks.MODIFICATION_TABLE, settings));
    }
}
