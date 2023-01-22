package io.github.llamarama.team.common.entity.caravantrader;

import io.github.llamarama.team.common.item.ModSpawnEggItem;
import io.github.llamarama.team.common.register.ModEntityTypes;
import io.github.llamarama.team.common.register.ModItems;
import io.github.llamarama.team.common.util.TradeUtil;
import io.github.llamarama.team.mixin.AccessorLlamaEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.mob.*;
import net.minecraft.entity.passive.LlamaEntity;
import net.minecraft.entity.passive.MerchantEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.VillagerEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvent;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOfferList;
import net.minecraft.village.TradeOffers;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

import java.util.UUID;

public class CaravanTraderEntity extends MerchantEntity {

    private boolean hasLlama;
    private int lifespan;
    private boolean traded;
    private UUID attachedLlama;

    public CaravanTraderEntity(EntityType<? extends MerchantEntity> entityType, World world) {
        super(entityType, world);
        this.hasLlama = false;
        this.traded = false;
        this.lifespan = 20 * 60 * 40;
        // this.lifespan = 1000; // WARNING: THIS IS MEANT TO BE USED ONLY WHILE TESTING
    }

    public static ModSpawnEggItem.SpawnEggData createSpawnEggData() {
        return new ModSpawnEggItem.SpawnEggData(ModEntityTypes.CARAVAN_TRADER, 0x7B857F, 0x6E3302);
    }

    public static DefaultAttributeContainer.Builder createAttributes() {
        return VillagerEntity.createVillagerAttributes();
    }

    @Override
    public boolean canImmediatelyDespawn(double distanceSquared) {
        return false;
    }

    @Nullable
    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return null;
    }

    @Override
    public boolean isLeveledMerchant() {
        return false;
    }

    @Override
    public SoundEvent getYesSound() {
        return SoundEvents.ENTITY_WANDERING_TRADER_YES;
    }

    @Override
    public SoundEvent getEatSound(ItemStack stack) {
        return SoundEvents.ENTITY_GENERIC_EAT;
    }

    @Override
    public SoundCategory getSoundCategory() {
        return SoundCategory.NEUTRAL;
    }

    public void setCurrentLlama(LlamaEntity llama) {
        if (!this.hasLlama) {
            this.hasLlama = true;
            llama.attachLeash(this, true);
            this.attachedLlama = llama.getUuid();
        }
    }

    @Override
    public void writeCustomDataToNbt(NbtCompound nbt) {
        super.writeCustomDataToNbt(nbt);
        nbt.putBoolean("Traded", this.traded);
        if (!this.traded) {
            nbt.putInt("Lifespan", this.lifespan);
        }

        if (this.attachedLlama != null) {
            nbt.putUuid("Attached", this.attachedLlama);
        }
    }

    @Override
    public void readCustomDataFromNbt(NbtCompound nbt) {
        super.readCustomDataFromNbt(nbt);
        this.traded = nbt.getBoolean("Traded");

        if (nbt.contains("Lifespan")) {
            this.lifespan = nbt.getInt("Lifespan");
        }

        if (nbt.contains("Attached")) {
            this.attachedLlama = nbt.getUuid("Attached");
        }
    }

    @Override
    public void tickMovement() {
        if (!this.world.isClient && !this.hasCustomer() && !this.traded) {
           if (this.lifespan-- <= 0) {
                this.killCaravan();
            }
        }

        super.tickMovement();
    }

    @Override
    protected void afterUsing(TradeOffer offer) {
        if (offer.shouldRewardPlayerExperience()) {
            int spawn = offer.getMerchantExperience();

            this.world.spawnEntity(new ExperienceOrbEntity(
                this.world,
                this.getX(),
                this.getY() + 0.5f,
                this.getZ(),
                spawn)
            );
        }

        this.world.addParticle(
            ParticleTypes.HAPPY_VILLAGER,
            this.getX(), this.getY(), this.getZ(),
            0.2f, 0.2f, 0.2f
        );

        this.traded = true;
    }

    @Override
    protected void initGoals() {
        super.initGoals();
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new StopFollowingCustomerGoal(this));
        this.goalSelector.add(1, new FleeEntityGoal<>(this, ZombieEntity.class, 8.0F, 0.5D, 0.5D));
        this.goalSelector.add(1, new FleeEntityGoal<>(this, EvokerEntity.class, 12.0F, 0.5D, 0.5D));
        this.goalSelector.add(1, new FleeEntityGoal<>(this, VindicatorEntity.class, 8.0F, 0.5D, 0.5D));
        this.goalSelector.add(1, new FleeEntityGoal<>(this, VexEntity.class, 8.0F, 0.5D, 0.5D));
        this.goalSelector.add(1, new FleeEntityGoal<>(this, PillagerEntity.class, 15.0F, 0.5D, 0.5D));
        this.goalSelector.add(1, new FleeEntityGoal<>(this, IllusionerEntity.class, 12.0F, 0.5D, 0.5D));
        this.goalSelector.add(1, new FleeEntityGoal<>(this, ZoglinEntity.class, 10.0F, 0.5D, 0.5D));
        this.goalSelector.add(1, new FleeEntityGoal<>(this, RavagerEntity.class, 12.0f, 0.5d, 0.4d));
        this.goalSelector.add(1, new EscapeDangerGoal(this, 0.5D));
        this.goalSelector.add(1, new LookAtCustomerGoal(this));
        this.goalSelector.add(4, new GoToWalkTargetGoal(this, 0.35D));
        this.goalSelector.add(8, new WanderAroundFarGoal(this, 0.35D));
        this.goalSelector.add(9, new StopAndLookAtEntityGoal(this, PlayerEntity.class, 3.0F, 1.0F));
        this.goalSelector.add(10, new LookAtEntityGoal(this, MobEntity.class, 8.0F));
    }

    @Override
    protected ActionResult interactMob(PlayerEntity player, Hand hand) {
        if (player.getStackInHand(hand).getItem() != ModItems.CARAVAN_TRADER_SPAWN_EGG) {
            this.setCustomer(player);
            this.sendOffers(player, this.getName(), 1);
        }

        return ActionResult.SUCCESS;
    }

    @Override
    protected void fillRecipes() {
        TradeOffers.Factory[] factory = TradeUtil.FACTORY;

        TradeOfferList tradeOfferList = this.getOffers();
        this.fillRecipesFromPool(tradeOfferList, factory, 5);
        int i = this.random.nextInt(factory.length);
        TradeOffers.Factory out = factory[i];
        TradeOffer tradeOffer = out.create(this, this.random);
        if (tradeOffer != null && tradeOfferList.stream().noneMatch((offer) -> offer.getSellItem().getItem() == tradeOffer.getSellItem().getItem())) {
            tradeOfferList.add(tradeOffer);
        }
    }

    @Override
    protected SoundEvent getTradingSound(boolean sold) {
        return sold ? SoundEvents.ENTITY_WANDERING_TRADER_YES : SoundEvents.ENTITY_WANDERING_TRADER_NO;
    }

    @Nullable
    @Override
    protected SoundEvent getAmbientSound() {
        return SoundEvents.ENTITY_WANDERING_TRADER_AMBIENT;
    }

    @Nullable
    @Override
    protected SoundEvent getHurtSound(DamageSource source) {
        return SoundEvents.ENTITY_WANDERING_TRADER_HURT;
    }

    @Nullable
    @Override
    protected SoundEvent getDeathSound() {
        return SoundEvents.ENTITY_WANDERING_TRADER_DEATH;
    }

    @Override
    protected SoundEvent getDrinkSound(ItemStack stack) {
        return SoundEvents.ENTITY_GENERIC_DRINK;
    }

    @Override
    protected SoundEvent getSwimSound() {
        return SoundEvents.ENTITY_GENERIC_SWIM;
    }

    @Override
    protected SoundEvent getSplashSound() {
        return SoundEvents.ENTITY_GENERIC_SPLASH;
    }

    private void killCaravan() {
        if (this.attachedLlama != null) {
            var currentLlama = ((ServerWorld) this.world).getEntity(this.attachedLlama);

            while (currentLlama != null) {
                var tmp = ((AccessorLlamaEntity) currentLlama).getFollower();
                currentLlama.discard();
                currentLlama = tmp;
            }
        }

        this.discard();
    }

}
