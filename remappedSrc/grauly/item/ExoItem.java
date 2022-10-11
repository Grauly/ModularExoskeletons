package grauly.item;

import dev.emi.trinkets.api.TrinketItem;
import grauly.modules.ExoModule;
import grauly.modules.ModularItem;
import java.util.ArrayList;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class ExoItem extends TrinketItem implements ModularItem<ExoModule> {

    public ExoItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        var stack = user.getStackInHand(hand);
        if(user.isSneaking()) {
            var newModule = AllItems.EX_MODULE.getDefaultStack();
            var list = this.deserializeModules(stack);
            list.add(newModule);
            this.serializeModules(list,stack);
        } else {
            this.getInstalledModules(stack).forEach(m -> {
                System.out.println(m.getDescription());
            });
        }
        return super.use(world, user, hand);
    }
}
