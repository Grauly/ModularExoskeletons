package grauly;

import grauly.block.AllBlocks;
import grauly.item.AllItems;
import grauly.modules.ModuleRegisterHelper;
import grauly.screen.AllScreenHandlers;
import grauly.screen.exomodification.ExoModificationPlayChannelHandler;
import grauly.util.Constants;
import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.client.itemgroup.FabricItemGroupBuilder;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Identifier;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ModularExos implements ModInitializer {
	public static final String MODID = "modularexos";
	public static final Logger LOGGER = LoggerFactory.getLogger(MODID);
	public static final ItemGroup EXO_GROUP = FabricItemGroupBuilder
			.create(new Identifier(ModularExos.MODID,"main"))
			.icon(() -> new ItemStack(AllItems.EXO_ITEM))
			.build();

	@Override
	public void onInitialize() {
		new AllBlocks().register();
		new AllItems().register();
		new AllScreenHandlers().register();
		new ModuleRegisterHelper().register();

		ServerPlayNetworking.registerGlobalReceiver(Constants.SLOT_SELECT_CHANNEL, new ExoModificationPlayChannelHandler());
	}
}
