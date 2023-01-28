package grauly.item.exomodules.utilities;

import grauly.item.exomodules.ExoModuleItem;
import grauly.util.Constants;
import net.fabricmc.fabric.api.networking.v1.PacketByteBufs;
import net.fabricmc.fabric.api.networking.v1.ServerPlayNetworking;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.network.PacketByteBuf;
import net.minecraft.server.network.ServerPlayerEntity;
import net.minecraft.text.Text;

public class ExoStepAssistModule extends ExoModuleItem {

    private static final float STEP_ASSIST_HEIGHT = 1.1f;
    private static final float STEP_ASSIST_CLIENT_DEFAULT = 0.6f;
    private static final float STEP_ASSIST_SERVER_DEFAULT = 1.0f;

    public ExoStepAssistModule(Settings settings) {
        super(settings);
    }

    @Override
    public int getSlotCost() {
        return 1;
    }

    @Override
    public int getEnergyUpkeepCost() {
        return 1;
    }

    @Override
    public int getEnergyGeneration() {
        return 0;
    }

    @Override
    public int getUseCost() {
        return 0;
    }

    @Override
    public String getDescription() {
        return Text.translatable("modularexos.module.stepassist.description").getString();
    }

    @Override
    public void moduleTick(ItemStack stack, LivingEntity entity) {
        if(!entity.world.isClient()) {
            if(entity.stepHeight != STEP_ASSIST_HEIGHT) {
                changeStepHeight(entity,STEP_ASSIST_HEIGHT);
            }
        } else {
            entity.stepHeight = STEP_ASSIST_HEIGHT;
        }
    }

    @Override
    public void onEquip(ItemStack modularItem, LivingEntity equipped) {
        if(!equipped.world.isClient()) {
            changeStepHeight(equipped,STEP_ASSIST_HEIGHT);
        }
    }

    @Override
    public void onUnEquip(ItemStack modularItem, LivingEntity equipped) {
        if(!equipped.world.isClient()) {
            //client default
            changeStepHeight(equipped, STEP_ASSIST_CLIENT_DEFAULT);
            //server default
            equipped.stepHeight = STEP_ASSIST_SERVER_DEFAULT;
        }
    }

    //change step height on both client and server
    private void changeStepHeight(LivingEntity entity, float height) {
        if(!entity.world.isClient()) {
            entity.stepHeight = height;
            if(entity instanceof PlayerEntity player) {
                var buffer = PacketByteBufs.create();
                buffer.writeFloat(height);
                ServerPlayNetworking.send((ServerPlayerEntity) player, Constants.STEP_HEIGHT_CHANNEL, buffer);
            }
        }
    }

    @Override
    public int getArmor() {
        return 0;
    }

    @Override
    public int getToughness() {
        return 0;
    }
}
