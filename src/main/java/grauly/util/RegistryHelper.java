package grauly.util;

import grauly.ModularExos;
import net.minecraft.util.Identifier;

/**
 * yeah, it basically does nothing besides giving access to these util methods
 */
public interface RegistryHelper {

    default Identifier id(String id) {
        return new Identifier(ModularExos.MODID,id);
    }

    void register();
}
