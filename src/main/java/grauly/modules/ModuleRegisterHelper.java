package grauly.modules;

import grauly.armor.ExoArmorItem;
import grauly.item.AllItems;
import grauly.util.RegistryHelper;

import java.util.Arrays;

public class ModuleRegisterHelper implements RegistryHelper {

    @Override
    public void register() {
        var armor = new ExoArmorItem[]{AllItems.EXO_HELMET, AllItems.EXO_CHESTPLATE, AllItems.EXO_LEGGING, AllItems.EXO_BOOTS};
        Arrays.stream(armor).forEach(a -> {
            a.addAllowedModule(AllItems.EXO_TIER_ONE_ARMOR_MODULE);
            a.addAllowedModule(AllItems.EXO_TIER_TWO_ARMOR_MODULE);
            a.addAllowedModule(AllItems.EXO_TIER_THREE_ARMOR_MODULE);
        });
        AllItems.EXO_ITEM.addAllowedModule(AllItems.SIMPLE_POWER_PLANT_MODULE);
        AllItems.EXO_ITEM.addAllowedModule(AllItems.COMPACT_POWER_PLANT_MODULE);
        AllItems.EXO_ITEM.addAllowedModule(AllItems.EXPENSIVE_POWER_PLANT_MODULE);
    }
}
