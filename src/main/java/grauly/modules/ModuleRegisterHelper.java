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
            a.addAllowedModule(AllItems.EX_MODULE);
        });
    }
}
