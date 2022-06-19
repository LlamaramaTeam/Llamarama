package io.github.llamarama.team.common.entity.bumbllama;

import io.github.llamarama.team.Llamarama;
import io.github.llamarama.team.common.entity.woolyllama.WoollyLlamaEntity;
import io.github.llamarama.team.common.register.ModEntityTypes;
import io.github.llamarama.team.common.util.Constants;
import io.github.llamarama.team.common.util.PosUtilities;
import net.minecraft.block.BlockState;
import net.minecraft.block.Fertilizable;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.item.Items;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.tag.ItemTags;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.registry.Registry;
import net.minecraft.util.registry.RegistryEntry;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

import java.util.List;

public class BumbleLlamaEntity extends WoollyLlamaEntity {

    private static final List<Item> FLOWER_LIST = Registry.ITEM.getOrCreateEntryList(ItemTags.FLOWERS)
        .stream()
        .map(RegistryEntry::value)
        .filter(it -> it != Items.WITHER_ROSE)
        .toList();

    public BumbleLlamaEntity(EntityType<? extends WoollyLlamaEntity> entityType, World world) {
        super(entityType, world);
        this.setSheared(true);
    }

    @Override
    public boolean startRiding(Entity entity, boolean force) {
        return false;
    }

    @Nullable
    @Override
    public LivingEntity getPrimaryPassenger() {
        return null;
    }

    @Override
    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        ItemStack using = player.getStackInHand(hand);

        if (using.getItem() == Items.GLASS_BOTTLE && !this.getSheared() && !this.world.isClient) {
            ItemStack out =
                ItemUsage.exchangeStack(player.getStackInHand(hand), player, Items.HONEY_BOTTLE.getDefaultStack());
            player.setStackInHand(hand, out);

            this.setSheared(true);

            return ActionResult.success(this.world.isClient);
        } else if (using.getItem() == Items.BONE_MEAL && !this.world.isClient) {
            using.decrement(1);

            int targetItemIndex = this.random.nextInt(FLOWER_LIST.size());
            this.dropItem(FLOWER_LIST.get(targetItemIndex), 1);

            return ActionResult.PASS;
        } else {
            return super.interactMob(player, hand);
        }
    }

    @Override
    public boolean isBreedingItem(ItemStack stack) {
        return false;
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
        if (isChosen && !world.isClient) {
            this.tryGrowPlant(down, stateForBoneMeal);
        }
    }

    @Override
    public void shearedTick() {
        if (this.woolTimer > 0) {
            this.woolTimer--;
        } else if (this.getSheared() && this.woolTimer == 0) {
            this.setSheared(false);

            this.world.playSoundFromEntity(null, this, SoundEvents.BLOCK_BEEHIVE_WORK, SoundCategory.NEUTRAL, 1.0f, 1.0f);
        }
    }

    @Override
    protected @NotNull ItemStack getShearedItem() {
        return new ItemStack(Items.HONEYCOMB, 3);
    }

    @Override
    protected boolean canStartRiding(Entity entity) {
        return false;
    }

    @Override
    protected void putPlayerOnBack(PlayerEntity player) {
        player.sendMessage(Constants.CANNOT_RIDE_TEXT, true);
    }

    @Override
    protected BumbleLlamaEntity getChild() {
        return ModEntityTypes.BUMBLE_LLAMA.create(this.world);
    }

    private void tryGrowPlant(BlockPos down, @NotNull BlockState stateForBoneMeal) {
        if (stateForBoneMeal.getBlock() instanceof Fertilizable fertilizable &&
            PosUtilities.checkForNoVelocity(this.getVelocity()) &&
            fertilizable.isFertilizable(world, down, stateForBoneMeal, world.isClient) &&
            fertilizable.canGrow(world, this.getRandom(), down, stateForBoneMeal)) {

            // Mods that implement the Fertilizable interface in invalid ways, will cause this code to crash with
            //  an IllegalArgumentException. We safely handle that case to prevent crashes however the issue should
            //  still be resolved by the respective mod author.
            try {
                fertilizable.grow((ServerWorld) this.world, this.random, down, stateForBoneMeal);
                ((ServerWorld) this.world).spawnParticles(
                    ParticleTypes.HAPPY_VILLAGER, this.getX(), this.getY(), this.getZ(),
                    20, 2d, 2d, 2d, 0.0d
                );
            } catch (IllegalArgumentException e) {
                Llamarama.getLogger().warn("""
                        The block {} has an invalid implementation of the Fertilizable interface!
                        Please report that as an issue to the mod's issue/bug tracker!
                        {}
                    """, stateForBoneMeal.getBlock(), e);
            }
        }
    }

}
