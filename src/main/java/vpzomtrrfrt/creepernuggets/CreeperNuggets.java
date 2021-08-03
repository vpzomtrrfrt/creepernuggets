package vpzomtrrfrt.creepernuggets;

import net.fabricmc.api.ModInitializer;
import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.entity.EntityType;
import net.minecraft.loot.UniformLootTableRange;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.util.Identifier;
import net.minecraft.util.registry.Registry;

public class CreeperNuggets implements ModInitializer {
    public static final String MODID = "creepernuggets";

    private static final Identifier CREEPER_LOOT_TABLE = EntityType.CREEPER.getLootTableId();

    @Override
    public void onInitialize() {
        Registry.register(Registry.ITEM, new Identifier(MODID, "creeper_nugget"), ItemCreeperNugget.INSTANCE);

        LootTableLoadingCallback.EVENT.register((resourceManager, manager, id, supplier, setter) -> {
            if(CREEPER_LOOT_TABLE.equals(id)) {
                supplier.pool(
                        FabricLootPoolBuilder.builder()
                                .rolls(UniformLootTableRange.between(0, 3))
                                .with(ItemEntry.builder(ItemCreeperNugget.INSTANCE))
                );
            }
        });
    }
}
