package grauly.util;

import grauly.ModularExos;
import grauly.modules.base.ModularItem;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

/**
 * yeah, it basically does nothing besides giving access to these util methods
 */
public interface RegistryHelper {

    default Identifier id(String id) {
        return new Identifier(ModularExos.MODID,id);
    }
    default void item(String id, Item item) {
        Registry.register(Registry.ITEM, id(id), item);
    }

    void register();
}
