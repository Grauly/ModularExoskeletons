package grauly.screen;

import grauly.screen.modification.ModificationTableScreenHandler;
import grauly.util.RegistryHelper;
import net.minecraft.screen.ScreenHandlerType;
import net.minecraft.util.registry.Registry;

public class AllScreenHandlers implements RegistryHelper {

    public static final ScreenHandlerType<ModificationTableScreenHandler> MODIFICATION_TABLE_SCREEN_HANDLER = new ScreenHandlerType<>(ModificationTableScreenHandler::new);

    @Override
    public void register() {
        Registry.register(Registry.SCREEN_HANDLER,id("modification_table"),MODIFICATION_TABLE_SCREEN_HANDLER);
    }
}
