package io.github.llamarama.team.common.item;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.SpawnEggItem;

public class ModSpawnEggItem extends SpawnEggItem {

    public ModSpawnEggItem(EntityType<? extends MobEntity> type, SpawnEggData data, Settings settings) {
        super(type, data.primaryColor(), data.secondaryColor(), settings);
    }

    public record SpawnEggData(int primaryColor, int secondaryColor) {
        public ModSpawnEggItem create(EntityType<? extends MobEntity> type, Settings settings) {
            return new ModSpawnEggItem(type, this, settings);
        }
    }

}
