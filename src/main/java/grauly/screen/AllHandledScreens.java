package grauly.screen;

import grauly.screen.exomodification.ExoModificationTableScreen;
import grauly.screen.modification.ModificationTableScreen;
import grauly.util.RegistryHelper;
import net.minecraft.client.gui.screen.ingame.HandledScreens;

public class AllHandledScreens implements RegistryHelper {

    @Override
    public void register() {
        HandledScreens.register(AllScreenHandlers.MODIFICATION_TABLE_SCREEN_HANDLER, ModificationTableScreen::new);
        HandledScreens.register(AllScreenHandlers.EXO_MODIFICATION_TABLE_SCREEN_HANDLER, ExoModificationTableScreen::new);
    }
}
