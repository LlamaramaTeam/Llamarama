package io.github.llamarama.team.common.register;

import java.util.HashMap;
import java.util.Map;
import java.util.function.Supplier;

import io.github.llamarama.team.common.entity.bumbllama.BumbleLlamaEntity;
import io.github.llamarama.team.common.entity.caravantrader.CaravanTraderEntity;
import io.github.llamarama.team.common.entity.mossyllama.MossyLlamaEntity;
import io.github.llamarama.team.common.entity.sandyllama.SandyLlamaEntity;
import io.github.llamarama.team.common.entity.woolyllama.WoollyLlamaEntity;
import io.github.llamarama.team.common.util.IdBuilder;
import net.fabricmc.fabric.api.object.builder.v1.entity.FabricEntityTypeBuilder;

import net.minecraft.entity.EntityDimensions;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.SpawnGroup;
import net.minecraft.entity.SpawnRestriction;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.world.Heightmap;

public final class ModEntityTypes {

    private static final Map<String, EntityType<?>> REGISTRY = new HashMap<>();

    public static final EntityType<WoollyLlamaEntity> WOOLLY_LLAMA = register(
        WoollyLlamaEntity::new,
        SpawnGroup.CREATURE,
        0.9f, 1.87f, false,
        WoollyLlamaEntity::createLlamaAttributes,
        new SpawnRestrictionData<>(
            SpawnRestriction.Location.ON_GROUND,
            Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
            AnimalEntity::isValidNaturalSpawn
        ),
        "woolly_llama"
    );
    public static final EntityType<BumbleLlamaEntity> BUMBLE_LLAMA = register(
        BumbleLlamaEntity::new,
        SpawnGroup.CREATURE,
        0.9f, 1.87f, false,
        BumbleLlamaEntity::createLlamaAttributes,
        new SpawnRestrictionData<>(
            SpawnRestriction.Location.ON_GROUND,
            Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
            AnimalEntity::isValidNaturalSpawn
        ),
        "bumble_llama"
    );
    public static final EntityType<CaravanTraderEntity> CARAVAN_TRADER = register(
        CaravanTraderEntity::new,
        SpawnGroup.MISC,
        0.6f, 1.95f, true,
        CaravanTraderEntity::createAttributes,
        new SpawnRestrictionData<>(
            SpawnRestriction.Location.ON_GROUND,
            Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
            MobEntity::canMobSpawn
        ),
        "caravan_trader"
    );
    public static final EntityType<MossyLlamaEntity> MOSSY_LLAMA = register(
        MossyLlamaEntity::new,
        SpawnGroup.CREATURE,
        0.9f, 1.87f, false,
        MossyLlamaEntity::createLlamaAttributes,
        new SpawnRestrictionData<>(
            SpawnRestriction.Location.ON_GROUND,
            Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
            MossyLlamaEntity::canSpawn
        ),
        "mossy_llama"
    );
    public static final EntityType<SandyLlamaEntity> SANDY_LLAMA = register(
        SandyLlamaEntity::new,
        SpawnGroup.CREATURE,
        0.9f, 1.87f, false,
        SandyLlamaEntity::createLlamaAttributes,
        new SpawnRestrictionData<>(
            SpawnRestriction.Location.ON_GROUND,
            Heightmap.Type.MOTION_BLOCKING_NO_LEAVES,
            MobEntity::canMobSpawn
        ),
        "sandy_llama"
    );

    private ModEntityTypes() { }

    private static <T extends MobEntity> EntityType<T> register(EntityType.EntityFactory<T> factory,
                                                                SpawnGroup group, float width, float height,
                                                                boolean fixed,
                                                                Supplier<DefaultAttributeContainer.Builder> attributes,
                                                                SpawnRestrictionData<T> spawnRestriction,
                                                                String id) {
        EntityType<T> type = FabricEntityTypeBuilder.Living.createMob()
            .spawnGroup(group)
            .entityFactory(factory)
            .spawnRestriction(spawnRestriction.location(), spawnRestriction.type(), spawnRestriction.predicate())
            .dimensions(new EntityDimensions(width, height, fixed))
            .defaultAttributes(attributes)
            .build();
        REGISTRY.put(id, type);
        return type;
    }

    public static void init() {
        REGISTRY.forEach((s, entityType) -> Registry.register(Registries.ENTITY_TYPE, IdBuilder.of(s), entityType));
    }

    public record SpawnRestrictionData<T extends MobEntity>(SpawnRestriction.Location location,
                                                            Heightmap.Type type,
                                                            SpawnRestriction.SpawnPredicate<T> predicate) { }

}
