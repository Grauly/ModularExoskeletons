package grauly.screen;

import grauly.screen.modification.ModificationTableScreen;
import grauly.util.RegistryHelper;
import net.fabricmc.fabric.api.client.screenhandler.v1.ScreenRegistry;
import net.minecraft.client.gui.screen.ingame.HandledScreens;

public class AllHandledScreens implements RegistryHelper {

    @Override
    public void register() {
        HandledScreens.register(AllScreenHandlers.MODIFICATION_TABLE_SCREEN_HANDLER, ModificationTableScreen::new);
    }
}
