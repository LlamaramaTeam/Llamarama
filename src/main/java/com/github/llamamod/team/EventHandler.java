package com.github.llamamod.team;

import com.github.llamamod.team.item.ModItems;
import com.github.llamamod.team.util.IDBuilder;
import net.fabricmc.fabric.api.loot.v1.FabricLootPoolBuilder;
import net.fabricmc.fabric.api.loot.v1.FabricLootSupplierBuilder;
import net.fabricmc.fabric.api.loot.v1.event.LootTableLoadingCallback;
import net.minecraft.loot.ConstantLootTableRange;
import net.minecraft.loot.LootManager;
import net.minecraft.loot.condition.EntityPropertiesLootCondition;
import net.minecraft.loot.context.LootContext;
import net.minecraft.loot.entry.ItemEntry;
import net.minecraft.loot.function.FurnaceSmeltLootFunction;
import net.minecraft.predicate.entity.EntityFlagsPredicate;
import net.minecraft.predicate.entity.EntityPredicate;
import net.minecraft.resource.ResourceManager;
import net.minecraft.util.Identifier;

public final class EventHandler {

    private static EventHandler eventHandler;

    private EventHandler() {

    }

    public static EventHandler getInstance() {
        if (eventHandler == null) {
            eventHandler = new EventHandler();
        }
        return eventHandler;
    }

    public void lootTableListener(ResourceManager resourceManager, LootManager lootManager, Identifier identifier, FabricLootSupplierBuilder fabricLootSupplierBuilder, LootTableLoadingCallback.LootTableSetter lootTableSetter) {
        if (IDBuilder.vanillaOf("entities/llama").equals(identifier)) {

            FabricLootPoolBuilder pool = FabricLootPoolBuilder.builder()
                    .rolls(ConstantLootTableRange.create(1))
                    .withEntry(ItemEntry.builder(ModItems.RAW_LLAMA_MEAT).build())
                    .withFunction(FurnaceSmeltLootFunction.builder()
                            .conditionally(EntityPropertiesLootCondition
                                    .builder(LootContext.EntityTarget.THIS, EntityPredicate.Builder.create()
                                            .flags(EntityFlagsPredicate.Builder.create().onFire(true).build())))
                            .build());

            fabricLootSupplierBuilder.withPool(pool.build());
        }
    }

}
