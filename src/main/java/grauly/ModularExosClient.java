package grauly;

import grauly.screen.AllHandledScreens;
import grauly.util.Constants;
import net.fabricmc.api.ClientModInitializer;
import net.fabricmc.fabric.api.client.networking.v1.ClientPlayNetworking;
import net.minecraft.text.Text;

public class ModularExosClient implements ClientModInitializer {

    @Override
    public void onInitializeClient() {
        new AllHandledScreens().register();

        //Step Height Networking
        ClientPlayNetworking.registerGlobalReceiver(Constants.STEP_HEIGHT_CHANNEL, (client, handler, buf, responseSender) -> {
            var val = buf.readFloat();
            if(client.player != null) {
                client.player.stepHeight = val;
            }
        });
    }
}
