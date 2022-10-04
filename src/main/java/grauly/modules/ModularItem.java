package grauly.modules;

import grauly.ModularExos;
import grauly.util.Constants;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;

import java.util.ArrayList;

public interface ModularItem<M extends Module> {

    default ArrayList<M> getInstalledModules(ItemStack stack) {
        ArrayList<M> modules = new ArrayList<>();
        ArrayList<ItemStack> stackModuleList = deserializeModules(stack);
        for (ItemStack moduleStack : stackModuleList) {
            if (moduleStack.getItem() instanceof Module module) {
                try {
                    modules.add((M) module);
                } catch (ClassCastException e) {
                    ModularExos.LOGGER.warn("Invalid Module Read, ignoring it.");
                }
            }
        }
        return modules;
    }

    default void serializeModules(ArrayList<ItemStack> modules, ItemStack stack) {
        NbtList moduleList = new NbtList();

        for (ItemStack module : modules) {
            NbtCompound compound = new NbtCompound();
            module.writeNbt(compound);
            moduleList.add(compound);
        }
        stack.getOrCreateNbt().put(Constants.MODULES_LIST_KEY, moduleList);
    }

    default ArrayList<ItemStack> deserializeModules(ItemStack stack) {
        if (!stack.hasNbt()) {
            return new ArrayList<>();
        }
        var root = stack.getNbt();
        if (!root.contains(Constants.MODULES_LIST_KEY)) {
            return new ArrayList<>();
        }
        ArrayList<ItemStack> stackModuleList = new ArrayList<>();
        NbtList nbtModuleList = root.getList(Constants.MODULES_LIST_KEY, 10);
        for (NbtElement moduleStack : nbtModuleList) {
            if (moduleStack instanceof NbtCompound moduleCompound) {
                stackModuleList.add(ItemStack.fromNbt(moduleCompound));
            }
        }
        return stackModuleList;
    }
}
