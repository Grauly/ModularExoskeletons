package grauly;

import grauly.block.AllBlocks;
import grauly.item.AllItems;
import grauly.screen.AllScreenHandlers;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ModularExos implements ModInitializer {
	public static final String MODID = "modularexos";
	public static final Logger LOGGER = LoggerFactory.getLogger(MODID);
	public static final ItemGroup EXO_GROUP = FabricItemGroupBuilder
			.create(new Identifier(ModularExos.MODID,"exo_group"))
			.icon(() -> new ItemStack(AllItems.EXO_ITEM))
			.build();

	@Override
	public void onInitialize() {
		new AllBlocks().register();
		new AllItems().register();
		new AllScreenHandlers().register();
		AllItems.EXO_ITEM.addAllowedModule(AllItems.EX_MODULE);
	}
}
