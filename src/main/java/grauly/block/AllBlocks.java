package grauly.block;

import grauly.ModularExos;
import grauly.util.RegistryHelper;
import net.fabricmc.fabric.api.object.builder.v1.block.FabricBlockSettings;
import net.minecraft.block.Block;
import net.minecraft.block.Material;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;


public class AllBlocks implements RegistryHelper {

    public static final Block MODIFICATION_TABLE = new ModificationTableBlock(FabricBlockSettings.of(Material.METAL).strength(4, 50).requiresTool());

    public void register() {
        Registry.register(Registry.BLOCK, id("modification_table"), MODIFICATION_TABLE);
    }
}
