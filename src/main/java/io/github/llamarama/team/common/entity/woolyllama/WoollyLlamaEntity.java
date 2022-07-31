package io.github.llamarama.team.common.entity.woolyllama;

import io.github.llamarama.team.common.entity.ai.goal.*;
import io.github.llamarama.team.common.item.ModSpawnEggItem;
import io.github.llamarama.team.common.register.ModBlocks;
import io.github.llamarama.team.common.register.ModEntityTypes;
import io.github.llamarama.team.mixin.AccessorLlamaEntity;
import net.minecraft.block.Blocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.Shearable;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.passive.LlamaEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.recipe.Ingredient;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;

public class WoollyLlamaEntity extends LlamaEntity implements Shearable {

    private static final TrackedData<Boolean> SHEARED =
        DataTracker.registerData(WoollyLlamaEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    protected int woolTimer;

    public WoollyLlamaEntity(EntityType<? extends WoollyLlamaEntity> entityType, World world) {
        super(entityType, world);
        this.woolTimer = 20 * 60 * 10;
    }

    public static ModSpawnEggItem.SpawnEggData createSpawnEggData() {
        return new ModSpawnEggItem.SpawnEggData(ModEntityTypes.WOOLLY_LLAMA, 0xFDD185, 0xE9AE48);
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound tag) {
        super.writeCustomDataToNbt(tag);
        tag.putBoolean("Sheared", this.getSheared());
        tag.putInt("WoolTimer", this.getWoolTimer());
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound tag) {
        super.readCustomDataFromNbt(tag);
        this.setSheared(tag.getBoolean("Sheared"));
        this.setWoolTimer(tag.getInt("WoolTimer"));
    }

    @Override
    public void sheared(SoundCategory shearedSoundCategory) {
        if (!this.world.isClient()) {
            this.setSheared(true);
            this.setWoolTimer(this.random.nextInt(20 * 60 * 20));

            this.world.playSoundFromEntity(null, this, SoundEvents.BLOCK_BEEHIVE_SHEAR, shearedSoundCategory, 1.0f, 1.0f);

            for (int i = 0; i < this.getShearedItem().getCount(); i++) {
                this.dropItem(this.getShearedItem().getItem());
            }
        }
    }

    @Override
    public boolean isShearable() {
        return !this.getSheared() && !this.isBaby() && !this.getShearedItem().isEmpty();
    }

    @Override
    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        if (player.getStackInHand(hand).getItem() != Items.SHEARS) {
            if (this.isBreedingItem(player.getStackInHand(hand))) {
                this.world.playSoundFromEntity(null, this, SoundEvents.ENTITY_LLAMA_EAT, SoundCategory.NEUTRAL, 1.0f, 2.0f);
            }

            return super.interactMob(player, hand);
        } else if (this.isShearable()) {
            this.sheared(SoundCategory.NEUTRAL);
            player.getStackInHand(hand).damage(1, player,
                playerEntity -> playerEntity.sendToolBreakStatus(hand)
            );
            return ActionResult.success(this.world.isClient());
        } else {
            return ActionResult.PASS;
        }
    }

    @Override
    public WoollyLlamaEntity createChild(ServerWorld serverWorld, PassiveEntity mate) {
        WoollyLlamaEntity child = this.getChild();
        WoollyLlamaEntity actualMate = (WoollyLlamaEntity) mate;
        int i = this.random.nextInt(Math.max(this.getStrength(), actualMate.getStrength())) + 1;

        this.setChildAttributes(mate, child);
        if (child != null) {
            child.setStrength(i);
        }
        return child;
    }

    public void setStrength(int strength) {
        this.dataTracker.set(AccessorLlamaEntity.getStrength(), strength);
    }

    public boolean getSheared() {
        return this.dataTracker.get(SHEARED);
    }

    protected void setSheared(boolean newVal) {
        this.dataTracker.set(SHEARED, newVal);
    }

    public int getWoolTimer() {
        return this.woolTimer;
    }

    protected void setWoolTimer(int newVal) {
        this.woolTimer = newVal;
    }

    @Override
    public void tick() {
        super.tick();

        this.shearedTick();
    }

    @Override
    public void onDeath(DamageSource source) {
        if (!this.getSheared()) {
            for (int i = 0; i < this.random.nextInt(3); i++) {
                this.dropItem(this.getShearedItem().getItem());
            }
        }

        super.onDeath(source);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new HorseBondWithPlayerGoal(this, 1.2D));
        this.goalSelector.add(2, new CaravanGoal<>(this, 2.0999999046325684D));
        this.goalSelector.add(3, new ProjectileAttackGoal(this, 1.25D, 40, 20.0F));
        this.goalSelector.add(3, new EscapeDangerGoal(this, 1.2D));
        this.goalSelector.add(3, new MoveToBlockGoal(this, Blocks.GRASS_BLOCK.getDefaultState(), 0.25d, 16));
        this.goalSelector.add(4, new AnimalMateGoal(this, 1.0D));
        this.goalSelector.add(5, new TemptGoal(this, 2.99d, Ingredient.ofItems(Blocks.HAY_BLOCK.asItem()), false));
        this.goalSelector.add(5, new VibeGoal(this));
        this.goalSelector.add(5, new FollowParentGoal(this, 1.0D));
        this.goalSelector.add(6, new WanderAroundFarGoal(this, 0.7D));
        this.goalSelector.add(7, new LookAtEntityGoal(this, PlayerEntity.class, 6.0F));
        this.goalSelector.add(8, new LookAroundGoal(this));
        this.targetSelector.add(1, new SpitRevengeGoal(this));
        this.targetSelector.add(2, new MobChaseGoal<>(this, WolfEntity.class));
    }

    @Override
    protected void initDataTracker() {
        super.initDataTracker();
        this.dataTracker.startTracking(SHEARED, false);
    }

    @NotNull
    protected ItemStack getShearedItem() {
        return new ItemStack(ModBlocks.LLAMA_WOOL.asItem(), this.world.random.nextInt(3) + 1);
    }

    @Nullable
    protected WoollyLlamaEntity getChild() {
        return ModEntityTypes.WOOLLY_LLAMA.create(this.world);
    }

    protected void shearedTick() {
        if (this.getWoolTimer() > 0 && this.getSheared() && this.world.getBlockState(this.getBlockPos().down()) == Blocks.GRASS_BLOCK.getDefaultState()) {
            this.woolTimer--;
        } else if (this.getWoolTimer() == 0 && this.getSheared()) {
            this.setSheared(false);
            this.world.playSoundFromEntity(null, this, SoundEvents.BLOCK_WOOL_PLACE, SoundCategory.NEUTRAL, 1.0F, 1.0F);
        }
    }

}
