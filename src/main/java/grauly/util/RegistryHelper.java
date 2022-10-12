package grauly.util;

import grauly.ModularExos;
import net.minecraft.util.Identifier;

public interface RegistryHelper {

    default Identifier id(String id) {
        return new Identifier(ModularExos.MODID,id);
    }

    void register();
}
