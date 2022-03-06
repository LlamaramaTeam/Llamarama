package io.github.llamarama.team.common.util.events;

import net.fabricmc.fabric.api.biome.v1.BiomeSelectionContext;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;

import java.util.function.Predicate;

@FunctionalInterface
public interface SpawnEventListener {

    void addSpawns(Predicate<BiomeSelectionContext> biomeSelector, SpawnGroup spawnGroup, EntityType<?> entityType, int weight, int minGroupSize, int maxGroupSize);

}
