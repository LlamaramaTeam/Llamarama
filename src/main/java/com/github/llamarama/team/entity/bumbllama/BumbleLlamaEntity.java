package com.github.llamarama.team.entity.bumbllama;

import com.github.llamarama.team.entity.ModEntityTypes;
import com.github.llamarama.team.entity.woolyllama.WoollyLlamaEntity;
import com.github.llamarama.team.util.PosUtilities;
import net.minecraft.block.BlockState;
import net.minecraft.block.Fertilizable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.tag.ItemTags;
import net.minecraft.text.LiteralText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;

import java.util.List;

public class BumbleLlamaEntity extends WoollyLlamaEntity {

    public BumbleLlamaEntity(EntityType<? extends WoollyLlamaEntity> entityType, World world) {
        super(entityType, world);
        this.setSheared(true);
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
        final ItemStack using = player.getStackInHand(hand);

        if (using.getItem() == Items.GLASS_BOTTLE && !this.getSheared() && !this.world.isClient) {
            ItemUsage.method_30012(using, player, Items.HONEY_BOTTLE.getDefaultStack());
            this.setSheared(true);

            return ActionResult.PASS;
        } else if (using.getItem() == Items.BONE_MEAL && !this.world.isClient) {
            using.decrement(1);

            final List<? extends Item> FLOWERS = ItemTags.FLOWERS.values();

            int targetItemIndex = this.random.nextInt(FLOWERS.size());

            this.dropItem(FLOWERS.get(targetItemIndex), 1);

            return ActionResult.PASS;
        } else {
            return super.interactMob(player, hand);
        }
    }

    @Override
    protected void putPlayerOnBack(PlayerEntity player) {
        player.sendMessage(new LiteralText("Thine shall not ride such rare and beautiful creature."), true);
    }

    @Override
    protected BumbleLlamaEntity getChild() {
        return !this.world.isClient ? ModEntityTypes.BUMBLE_LLAMA.create(this.world) : null;
    }

    @Override
    public boolean isTrader() {
        return false;
    }

    @Override
    public void tick() {
        super.tick();

        BlockPos down = this.getBlockPos().down();
        BlockState stateForBoneMeal = this.world.getBlockState(down);

        boolean isChosen = this.random.nextInt(384) == 0;

        if (isChosen && !this.world.isClient && stateForBoneMeal.getBlock() instanceof Fertilizable && PosUtilities.checkForNoVelocity(this.getVelocity())) {
            ((Fertilizable) stateForBoneMeal.getBlock()).grow((ServerWorld) this.world, this.random, down, stateForBoneMeal);

            ((ServerWorld) this.world).spawnParticles(ParticleTypes.HAPPY_VILLAGER, this.getX(), this.getY(), this.getZ(), 20, 2d, 2d, 2d, 0.0d);
        }
    }

}
