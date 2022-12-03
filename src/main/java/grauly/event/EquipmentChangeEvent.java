package grauly.event;

import grauly.armor.ExoArmorItem;
import net.fabricmc.fabric.api.event.lifecycle.v1.ServerEntityEvents;
import net.minecraft.entity.EquipmentSlot;
import net.minecraft.entity.LivingEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;

public class EquipmentChangeEvent implements ServerEntityEvents.EquipmentChange {
    @Override
    public void onChange(LivingEntity livingEntity, EquipmentSlot equipmentSlot, ItemStack previousStack, ItemStack currentStack) {
        if((!equipmentSlot.equals(EquipmentSlot.MAINHAND)) && (!equipmentSlot.equals(EquipmentSlot.OFFHAND))) {
            if (previousStack.getItem() instanceof ExoArmorItem exo && currentStack.isEmpty()) {
                exo.onUnEqip(previousStack, livingEntity);
            } else if (previousStack.isEmpty() && currentStack.getItem() instanceof ExoArmorItem exo) {
                exo.onEquip(currentStack, livingEntity);
            }
        }
    }
}
