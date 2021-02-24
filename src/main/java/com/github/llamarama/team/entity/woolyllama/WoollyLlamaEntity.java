package com.github.llamarama.team.entity.woolyllama;

import com.github.llamarama.team.block.ModBlocks;
import com.github.llamarama.team.entity.ModEntityTypes;
import com.github.llamarama.team.entity.ai.goal.*;
import com.github.llamarama.team.item.ModItems;
import com.github.llamarama.team.mixins.AccessorLlamaEntity;
import net.minecraft.block.Blocks;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.goal.*;
import net.minecraft.entity.damage.DamageSource;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.passive.LlamaEntity;
import net.minecraft.entity.passive.PassiveEntity;
import net.minecraft.entity.passive.WolfEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.recipe.Ingredient;
import net.minecraft.server.world.ServerWorld;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;

public class WoollyLlamaEntity extends LlamaEntity implements Shearable, ItemSteerable {

    private static final TrackedData<Boolean> SHEARED = DataTracker.registerData(WoollyLlamaEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    private static final TrackedData<Boolean> CARPETED = DataTracker.registerData(WoollyLlamaEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    private static final TrackedData<Integer> BOOST_TIME = DataTracker.registerData(WoollyLlamaEntity.class, TrackedDataHandlerRegistry.INTEGER);
    private final SaddledComponent saddledComponent;
    private int WOOL_TIMER;


    public WoollyLlamaEntity(EntityType<? extends WoollyLlamaEntity> entityType, World world) {
        super(entityType, world);
        this.WOOL_TIMER = 20 * 60 * 5;

        this.saddledComponent = new SaddledComponent(this.getDataTracker(), BOOST_TIME, CARPETED);
    }

    @Override
    protected void initGoals() {
        this.goalSelector.add(0, new SwimGoal(this));
        this.goalSelector.add(1, new HorseBondWithPlayerGoal(this, 1.2D));
        this.goalSelector.add(2, new CaravanGoal<>(this, 2.0999999046325684D));
        this.goalSelector.add(3, new VibeGoal(this));
        this.goalSelector.add(3, new ProjectileAttackGoal(this, 1.25D, 40, 20.0F));
        this.goalSelector.add(3, new EscapeDangerGoal(this, 1.2D));
        this.goalSelector.add(3, new MoveToBlockGoal(this, Blocks.GRASS_BLOCK.getDefaultState(), this.getMovementSpeed() + 0.25d, 16));
        this.goalSelector.add(4, new AnimalMateGoal(this, 1.0D));
        this.goalSelector.add(5, new TemptGoal(this, 2.99d, false, Ingredient.ofItems(Blocks.HAY_BLOCK.asItem())));
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
        this.dataTracker.startTracking(BOOST_TIME, 0);
        this.dataTracker.startTracking(CARPETED, false);
    }

    @Override
    public void writeCustomDataToTag(CompoundTag tag) {
        super.writeCustomDataToTag(tag);
        tag.putBoolean("Sheared", this.getSheared());
        tag.putInt("WoolTimer", this.getWoolTimer());
        this.saddledComponent.toTag(tag);
    }

    @Override
    public void readCustomDataFromTag(CompoundTag tag) {
        super.readCustomDataFromTag(tag);
        this.setSheared(tag.getBoolean("Sheared"));
        this.setWoolTimer(tag.getInt("WoolTimer"));
        this.saddledComponent.fromTag(tag);
    }

    @Override
    public void sheared(SoundCategory shearedSoundCategory) {
        if (!this.world.isClient()) {
            this.setSheared(true);
            this.setWoolTimer(this.random.nextInt(20 * 60 * 20));

            this.world.playSoundFromEntity(null, this, SoundEvents.BLOCK_BEEHIVE_SHEAR, SoundCategory.NEUTRAL, 1.0f, 1.0f);

            for (int i = 0; i < this.getShearedItem().getCount(); i++) {
                this.dropItem(this.getShearedItem().getItem());
            }
        }
    }

    @Override
    public boolean isShearable() {
        return !this.getSheared() && !this.isBaby();
    }

    protected ItemStack getShearedItem() {
        return new ItemStack(ModBlocks.LLAMA_WOOL.asItem(), this.world.random.nextInt(3) + 1);
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

            player.getStackInHand(hand).damage(1, player, (playerEntity -> playerEntity.sendToolBreakStatus(hand)));

            return ActionResult.success(this.world.isClient());

        } else {
            return ActionResult.PASS;
        }

    }

    protected WoollyLlamaEntity getChild() {
        return !this.world.isClient() ? ModEntityTypes.WOOLLY_LLAMA.create(this.world) : null;
    }

    @Override
    public WoollyLlamaEntity createChild(ServerWorld serverWorld, PassiveEntity mate) {
        WoollyLlamaEntity child = this.getChild();

        this.setChildAttributes(mate, child);

        WoollyLlamaEntity actualMate = (WoollyLlamaEntity) mate;

        int i = this.random.nextInt(Math.max(this.getStrength(), actualMate.getStrength())) + 1;

        child.setStrength(i);

        return child;
    }

    public void setStrength(int strength) {
        this.dataTracker.set(((AccessorLlamaEntity) this).getStrength(), strength);
    }

    public boolean getSheared() {
        return this.dataTracker.get(SHEARED);
    }

    protected void setSheared(boolean newVal) {
        this.dataTracker.set(SHEARED, newVal);
    }

    public int getWoolTimer() {
        return this.WOOL_TIMER;
    }

    protected void setWoolTimer(int newVal) {
        this.WOOL_TIMER = newVal;
    }

    @Override
    public void tick() {
        super.tick();

        if (this.getWoolTimer() > 0 && this.getSheared() && this.world.getBlockState(this.getBlockPos().down()) == Blocks.GRASS_BLOCK.getDefaultState()) {
            this.WOOL_TIMER--;
        } else if (this.getWoolTimer() == 0 && this.getSheared()) {
            this.setSheared(false);
            this.world.playSoundFromEntity(null, this, SoundEvents.BLOCK_WOOL_PLACE, SoundCategory.NEUTRAL, 1.0F, 1.0F);
        }
    }

    @Override
    protected boolean receiveFood(PlayerEntity player, ItemStack itemStack) {
        // Copied directly from llama. Changed names a bit.
        int growBoost = 0;
        int temper = 0;
        float healHealth = 0.0F;
        boolean loves = false;
        Item itemInHand = itemStack.getItem();

        if (itemInHand == Items.WHEAT) {
            growBoost = 10;
            temper = 3;
            healHealth = 2.0F;
        } else if (itemInHand == Items.HAY_BLOCK) {
            growBoost = 90;
            temper = 6;
            healHealth = 10.0F;
            if (this.isTame() && this.getBreedingAge() == 0 && this.canEat()) {
                loves = true;
                this.lovePlayer(player);
            }
        }

        if (this.getHealth() < this.getMaxHealth() && healHealth > 0.0F) {
            this.heal(healHealth);
            loves = true;
        }

        if (this.isBaby() && growBoost > 0) {
            this.world.addParticle(ParticleTypes.HAPPY_VILLAGER, this.getParticleX(1.0D), this.getRandomBodyY() + 0.5D, this.getParticleZ(1.0D), 0.0D, 0.0D, 0.0D);
            if (!this.world.isClient) {
                this.growUp(growBoost);
            }

            loves = true;
        }

        if (temper > 0 && (loves || !this.isTame()) && this.getTemper() < this.getMaxTemper()) {
            loves = true;
            if (!this.world.isClient) {
                this.addTemper(temper);
            }
        }

        if (loves && !this.isSilent() && this.world.isClient) {
            this.world.playSound(null, this.getX(), this.getY(), this.getZ(), this.getEatSound(), this.getSoundCategory(), 1.0F, 1.0F);
        }

        return loves;
    }

    @Override
    public void onDeath(DamageSource source) {
        if (!this.getSheared()) {
            for (int i = 0; i < this.random.nextInt(3); i++) {
                this.dropItem(ModBlocks.LLAMA_WOOL.asItem());
            }
        }

        super.onDeath(source);
    }

    @Override
    public boolean consumeOnAStickItem() {
        return true;
    }

    @Override
    public void setMovementInput(Vec3d movementInput) {
        super.travel(movementInput);
    }

    @Override
    public float getSaddledSpeed() {
        return this.getMovementSpeed() * 2.5f;
    }

    @Override
    public boolean isSaddled() {
        return this.getCarpetColor() != null;
    }

    @Override
    public boolean canBeControlledByRider() {
        LivingEntity rider = (LivingEntity) this.getPrimaryPassenger();

        return rider != null && (this.isSaddled() && rider.getStackInHand(Hand.MAIN_HAND).getItem() == ModItems.HAY_ON_A_STICK || rider.getStackInHand(Hand.OFF_HAND).getItem() == ModItems.HAY_ON_A_STICK);
    }

    @Override
    public void onTrackedDataSet(TrackedData<?> data) {
        if (BOOST_TIME.equals(data)) {
            this.saddledComponent.boost();
        }

        super.onTrackedDataSet(data);
    }

    @Override
    public void travel(Vec3d movementInput) {
        this.travel(this, this.saddledComponent, movementInput);
    }

}
