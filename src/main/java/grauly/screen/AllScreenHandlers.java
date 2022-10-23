package grauly.screen;

import grauly.screen.exomodification.ExoModificationTableScreenHandler;
import grauly.screen.modification.ModificationTableScreenHandler;
import grauly.util.RegistryHelper;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.registry.Registry;

public class AllScreenHandlers implements RegistryHelper {

    public static final ScreenHandlerType<ModificationTableScreenHandler> MODIFICATION_TABLE_SCREEN_HANDLER = new ScreenHandlerType<>(ModificationTableScreenHandler::new);
    public static final ScreenHandlerType<ExoModificationTableScreenHandler> EXO_MODIFICATION_TABLE_SCREEN_HANDLER = new ScreenHandlerType<>(ExoModificationTableScreenHandler::new);

    @Override
    public void register() {
        Registry.register(Registry.SCREEN_HANDLER,id("modification_table"),MODIFICATION_TABLE_SCREEN_HANDLER);
        Registry.register(Registry.SCREEN_HANDLER,id("exo_modification_table"),EXO_MODIFICATION_TABLE_SCREEN_HANDLER);
    }
}
