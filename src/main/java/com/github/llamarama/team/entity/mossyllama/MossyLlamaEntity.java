package com.github.llamarama.team.entity.mossyllama;

import com.github.llamarama.team.entity.woolyllama.WoollyLlamaEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.world.World;

public class MossyLlamaEntity extends WoollyLlamaEntity {

    public MossyLlamaEntity(EntityType<? extends WoollyLlamaEntity> entityType, World world) {
        super(entityType, world);
    }

}
