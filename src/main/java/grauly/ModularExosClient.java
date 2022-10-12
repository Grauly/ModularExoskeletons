package grauly;

import grauly.screen.AllHandledScreens;
import net.fabricmc.api.ClientModInitializer;

public class ModularExosClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        new AllHandledScreens().register();
    }
}
