package grauly.modules.exo;

import grauly.modules.base.Module;
import net.minecraft.entity.attribute.EntityAttribute;
import net.minecraft.entity.attribute.EntityAttributeModifier;
import net.minecraft.util.Pair;

import java.util.ArrayList;

public interface ExoModule extends Module {
    int getArmor();
    int getToughness();
    ArrayList<Pair<EntityAttribute, EntityAttributeModifier>> getAttributeModifiers();
}
