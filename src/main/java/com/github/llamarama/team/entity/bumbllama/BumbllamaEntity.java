package com.github.llamarama.team.entity.bumbllama;

import com.github.llamarama.team.entity.ModEntityTypes;
import com.github.llamarama.team.entity.woolyllama.WoollyLlamaEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.text.LiteralText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;

public class BumbllamaEntity extends WoollyLlamaEntity {

    public BumbllamaEntity(EntityType<? extends WoollyLlamaEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    protected ItemStack getShearedItem() {
        return new ItemStack(Items.HONEYCOMB, 2);
    }

    @Override
    protected boolean canStartRiding(Entity entity) {
        return false;
    }

    @Override
    public boolean startRiding(Entity entity, boolean force) {
        return false;
    }

    @Override
    public boolean startRiding(Entity entity) {
        return false;
    }

    @Override
    public boolean canBeControlledByRider() {
        return false;
    }

    @Override
    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        return super.interactMob(player, hand);
    }

    @Override
    protected void putPlayerOnBack(PlayerEntity player) {
        player.sendMessage(new LiteralText("Thine shall not ride such rare and beautiful creature"), true);
    }

    @Override
    protected BumbllamaEntity getChild() {
        return !this.world.isClient ? ModEntityTypes.BUMBLLAMA.create(this.world) : null;
    }

}
