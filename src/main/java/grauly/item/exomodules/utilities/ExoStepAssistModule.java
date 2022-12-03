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
        /*if(!entity.world.isClient()) {
            entity.sendMessage(Text.of(String.valueOf(entity.stepHeight)));
        }*/
    }

    @Override
    public void onEquip(ItemStack modularItem, LivingEntity equipped) {
        if(!equipped.world.isClient()) {
            changeStepHeight(equipped,1.1f);
        }
    }

    @Override
    public void onUnEquip(ItemStack modularItem, LivingEntity equipped) {
        if(!equipped.world.isClient()) {
            //client default
            changeStepHeight(equipped, 0.6f);
            //server default
            equipped.stepHeight = 1.0f;
        }
    }

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
