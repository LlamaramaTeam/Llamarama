package io.github.llamarama.team.common.event;

import io.github.llamarama.team.common.register.ModItems;
import io.github.llamarama.team.common.util.IdBuilder;
import net.fabricmc.fabric.api.loot.v2.LootTableEvents;
import net.fabricmc.fabric.api.loot.v2.LootTableSource;
import net.minecraft.loot.LootManager;
import net.minecraft.loot.LootPool;
import net.minecraft.loot.LootTable;
import net.minecraft.loot.condition.EntityPropertiesLootCondition;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.FurnaceSmeltLootFunction;
import net.minecraft.loot.function.LootingEnchantLootFunction;
import net.minecraft.loot.provider.number.ConstantLootNumberProvider;
import net.minecraft.loot.provider.number.UniformLootNumberProvider;
import net.minecraft.predicate.entity.EntityFlagsPredicate;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;

public final class EventHandler implements LootTableEvents.Modify {
    @Override
    public void modifyLootTable(ResourceManager resourceManager, LootManager lootManager, Identifier id, LootTable.Builder tableBuilder, LootTableSource source) {
        if (IdBuilder.vanillaOf("entities/llama").equals(id)) {
            LootPool.Builder pool =
                LootPool.builder()
                    .rolls(ConstantLootNumberProvider.create(1))
                    .with(ItemEntry.builder(ModItems.RAW_LLAMA_MEAT).build())
                    .apply(FurnaceSmeltLootFunction.builder()
                        .conditionally(EntityPropertiesLootCondition
                            .builder(LootContext.EntityTarget.THIS,
                                EntityPredicate.Builder.create()
                                    .flags(EntityFlagsPredicate.Builder.create()
                                        .onFire(true).build())))
                        .build())
                    .apply(LootingEnchantLootFunction.builder(UniformLootNumberProvider.create(1, 2))
                        .build());

            tableBuilder.pool(pool);
        }
    }
}
