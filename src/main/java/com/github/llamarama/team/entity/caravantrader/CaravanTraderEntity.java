package com.github.llamarama.team.entity.caravantrader;

import com.github.llamarama.team.entity.ModEntityTypes;
import com.github.llamarama.team.item.ModItems;
import com.github.llamarama.team.util.TradeUtil;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ExperienceOrbEntity;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.attribute.DefaultAttributeContainer;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.mob.*;
import net.minecraft.entity.passive.MerchantEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.text.LiteralText;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.village.TradeOffer;
import net.minecraft.village.TradeOfferList;
import net.minecraft.village.TradeOffers;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;

public class CaravanTraderEntity extends MerchantEntity {

    public CaravanTraderEntity(EntityType<? extends MerchantEntity> entityType, World world) {
        super(entityType, world);
    }

    public static DefaultAttributeContainer.Builder createAttributes() {
        return MobEntity.createMobAttributes().add(EntityAttributes.GENERIC_MOVEMENT_SPEED, 1.2d).add(EntityAttributes.GENERIC_FOLLOW_RANGE, 10).add(EntityAttributes.GENERIC_MAX_HEALTH, 20);
    }

    @Override
    protected void afterUsing(TradeOffer offer) {
        if (offer.shouldRewardPlayerExperience()) {
            int spawn = 3 + this.random.nextInt(5);

            this.world.spawnEntity(new ExperienceOrbEntity(this.world, this.getX(), this.getY() + 0.5f, this.getZ(), spawn));
        }
    }

    @Override
    protected void initGoals() {
        super.initGoals();
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new EscapeDangerGoal(this, this.getMovementSpeed() * 2));
        this.goalSelector.add(2, new FleeEntityGoal<>(this, ZombieEntity.class, 20, this.getMovementSpeed(), this.getMovementSpeed() * 2));
        this.goalSelector.add(2, new FleeEntityGoal<>(this, HuskEntity.class, 20, this.getMovementSpeed(), this.getMovementSpeed() * 2));
        this.goalSelector.add(2, new FleeEntityGoal<>(this, DrownedEntity.class, 20, this.getMovementSpeed(), this.getMovementSpeed() * 2));
        this.goalSelector.add(2, new FleeEntityGoal<>(this, ZoglinEntity.class, 20, this.getMovementSpeed(), this.getMovementSpeed() * 2));
        this.goalSelector.add(2, new FleeEntityGoal<>(this, PillagerEntity.class, 20, this.getMovementSpeed(), this.getMovementSpeed() * 2));
        this.goalSelector.add(2, new FleeEntityGoal<>(this, EvokerEntity.class, 20, this.getMovementSpeed(), this.getMovementSpeed() * 2));
        this.goalSelector.add(2, new FleeEntityGoal<>(this, IllusionerEntity.class, 20, this.getMovementSpeed(), this.getMovementSpeed() * 2));
        this.goalSelector.add(2, new FleeEntityGoal<>(this, VindicatorEntity.class, 20, this.getMovementSpeed(), this.getMovementSpeed() * 2));
        this.goalSelector.add(2, new FleeEntityGoal<>(this, VexEntity.class, 20, this.getMovementSpeed(), this.getMovementSpeed() * 2));
        this.goalSelector.add(2, new FleeEntityGoal<>(this, RavagerEntity.class, 20, this.getMovementSpeed(), this.getMovementSpeed() * 2));
        this.goalSelector.add(3, new WanderAroundGoal(this, this.getMovementSpeed()));
        this.goalSelector.add(4, new LookAtCustomerGoal(this));
        this.goalSelector.add(5, new StopAndLookAtEntityGoal(this, PlayerEntity.class, 3.0f, 1.0f));
        this.goalSelector.add(6, new LookAtEntityGoal(this, MobEntity.class, 4));
    }

    @Override
    protected ActionResult interactMob(PlayerEntity player, Hand hand) {
        if (player.getStackInHand(hand).getItem() != ModItems.CARAVAN_TRADER_SPAWN_EGG) {
            this.setCurrentCustomer(player);
            this.sendOffers(player, new LiteralText("Caravan Trader"), 1);

        }
        return ActionResult.SUCCESS;
    }

    /**
     * TODO: Actually add good pools.
     */
    @Override
    protected void fillRecipes() {
        TradeOffers.Factory[] factory = TradeUtil.FACTORY;

        TradeOfferList tradeOfferList = this.getOffers();
        this.fillRecipesFromPool(tradeOfferList, factory, 5);
        int i = this.random.nextInt(factory.length);
        TradeOffers.Factory out = factory[i];
        TradeOffer tradeOffer = out.create(this, this.random);
        if (tradeOffer != null && !tradeOfferList.contains(tradeOffer)) {
            tradeOfferList.add(tradeOffer);
        }
    }

    @Nullable
    @Override
    public PassiveEntity createChild(ServerWorld world, PassiveEntity entity) {
        return ModEntityTypes.get().CARAVAN_TRADER.create(world);
    }

    @Override
    public boolean isLeveledMerchant() {
        return false;
    }

}
