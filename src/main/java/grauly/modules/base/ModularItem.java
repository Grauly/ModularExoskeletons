package grauly.modules.base;

import grauly.ModularExos;
import grauly.util.Constants;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.nbt.NbtElement;
import net.minecraft.nbt.NbtList;

import java.util.ArrayList;
import java.util.UUID;

public interface ModularItem<M extends Module> {

    ArrayList<M> getAllowedModules();

    void addAllowedModule(M module);

    void recalculateStats(ItemStack stack);

    default int getRemainingCapacity(ItemStack stack) {
        ArrayList<M> modules = this.getInstalledModules(stack);
        int usedCapacity = 0;
        for (M module : modules) {
            usedCapacity += module.getSlotCost();
        }
        return getMaxCapacity() - usedCapacity;
    }

    int getMaxCapacity();

    default ArrayList<M> getInstalledModules(ItemStack stack) {
        ArrayList<M> modules = new ArrayList<>();
        ArrayList<ItemStack> stackModuleList = deserializeModules(stack);
        for (ItemStack moduleStack : stackModuleList) {
            if (moduleStack.getItem() instanceof Module module) {
                try {
                    modules.add((M) module);
                } catch (ClassCastException e) {
                    ModularExos.LOGGER.debug("Invalid Module Read, ignoring it.");
                }
            }
        }
        return modules;
    }

    default void serializeModules(ArrayList<ItemStack> modules, ItemStack stack) {
        serializeModules(modules,stack,UUID.randomUUID());
    }

    default void serializeModules(ArrayList<ItemStack> modules, ItemStack stack, UUID assemblyUUID) {
        NbtList moduleList = new NbtList();

        for (ItemStack module : modules) {
            NbtCompound compound = new NbtCompound();
            module.writeNbt(compound);
            moduleList.add(compound);
        }
        NbtCompound UUIDCompound = new NbtCompound();
        UUIDCompound.putUuid(Constants.ASSEMBLY_KEY,assemblyUUID);
        stack.getOrCreateNbt().put(Constants.MODULES_LIST_KEY, moduleList);
        stack.getOrCreateNbt().put(Constants.MODULE_INSTALLATION, UUIDCompound);
    }

    default UUID getAssemblyUUID(ItemStack stack) {
        if(stack.hasNbt()) {
            var nbt = stack.getNbt();
            var installDetails = nbt.getCompound(Constants.MODULE_INSTALLATION);
            return installDetails.getUuid(Constants.ASSEMBLY_KEY);
        }
        return null;
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
