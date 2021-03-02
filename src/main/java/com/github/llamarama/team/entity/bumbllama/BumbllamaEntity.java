package com.github.llamarama.team.entity.bumbllama;

import com.github.llamarama.team.entity.woolyllama.WoollyLlamaEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.world.World;

public class BumbllamaEntity extends WoollyLlamaEntity {

    public BumbllamaEntity(EntityType<? extends WoollyLlamaEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected ItemStack getShearedItem() {
        return new ItemStack(Items.HONEYCOMB, 2);
    }

}
