package io.github.llamarama.team.common.item;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.SpawnEggItem;

public class ModSpawnEggItem extends SpawnEggItem {

    public ModSpawnEggItem(SpawnEggData data, Settings settings) {
        super(data.type(), data.primaryColor(), data.secondaryColor(), settings);
    }

    public record SpawnEggData(EntityType<? extends MobEntity> type, int primaryColor, int secondaryColor) {

        public ModSpawnEggItem create(Settings settings) {
            return new ModSpawnEggItem(this, settings);
        }

    }

}
