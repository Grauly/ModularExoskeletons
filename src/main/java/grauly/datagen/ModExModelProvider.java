package grauly.datagen;

import grauly.block.AllBlocks;
import grauly.item.AllItems;
import net.fabricmc.fabric.api.datagen.v1.FabricDataGenerator;
import net.fabricmc.fabric.api.datagen.v1.provider.FabricModelProvider;
import net.minecraft.data.client.BlockStateModelGenerator;
import net.minecraft.data.client.ItemModelGenerator;
import net.minecraft.data.client.Models;
import net.minecraft.item.Item;

import java.util.ArrayList;
import java.util.List;

class ModExModelProvider extends FabricModelProvider {

    public ModExModelProvider(FabricDataGenerator dataGenerator) {
        super(dataGenerator);
    }

    @Override
    public void generateBlockStateModels(BlockStateModelGenerator blockStateModelGenerator) {
        blockStateModelGenerator.registerSimpleState(AllBlocks.MODIFICATION_TABLE);
        blockStateModelGenerator.registerSimpleState(AllBlocks.EXO_MODIFICATION_TABLE);
    }

    @Override
    public void generateItemModels(ItemModelGenerator itemModelGenerator) {
        var itemFields = new ArrayList<>(List.of(AllItems.class.getFields()));
        itemFields.forEach(f -> {
            try {
                var item = f.get(null);
                itemModelGenerator.register((Item) item, Models.GENERATED);
                System.out.printf("Registered: %s\r\n", f.getName());
            } catch (IllegalAccessException e) {
                throw new RuntimeException(e);
            }
        });
    }
}
