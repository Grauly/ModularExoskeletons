package grauly.item;

import grauly.ModularExos;
import grauly.armor.ExoArmorMaterial;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.item.*;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;


public class AllItems {

    //https://fabricmc.net/wiki/tutorial:armor
    private static final ArmorMaterial EXO_MATERIAL = new ExoArmorMaterial();
    private static final Item.Settings settings = new Item.Settings().group(ModularExos.EXO_GROUP);
    public static final Item EXO_ITEM = new ExoItem(settings);
    public static final Item EXO_HELMET = new ArmorItem(EXO_MATERIAL, EquipmentSlot.HEAD, settings);
    public static final Item EXO_CHESTPLATE = new ArmorItem(EXO_MATERIAL, EquipmentSlot.CHEST, settings);
    public static final Item EXO_LEGGING = new ArmorItem(EXO_MATERIAL, EquipmentSlot.LEGS, settings);
    public static final Item EXO_BOOTS = new ArmorItem(EXO_MATERIAL, EquipmentSlot.FEET, settings);
    public static final Item EX_MODULE = new ExampleModule(settings);

    public static void register() {
        Registry.register(Registry.ITEM,new Identifier(ModularExos.MODID,"exo_item"), EXO_ITEM);
        Registry.register(Registry.ITEM,new Identifier(ModularExos.MODID,"exo_helmet"),EXO_HELMET);
        Registry.register(Registry.ITEM,new Identifier(ModularExos.MODID,"exo_chestplate"),EXO_CHESTPLATE);
        Registry.register(Registry.ITEM,new Identifier(ModularExos.MODID,"exo_leggings"),EXO_LEGGING);
        Registry.register(Registry.ITEM,new Identifier(ModularExos.MODID,"exo_boots"),EXO_BOOTS);
        Registry.register(Registry.ITEM,new Identifier(ModularExos.MODID,"example_module"),EX_MODULE);
    }
}
