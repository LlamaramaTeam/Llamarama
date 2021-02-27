package com.github.llamarama.team.mixins;

import com.github.llamarama.team.entity.ai.goal.CaravanGoal;
import com.github.llamarama.team.entity.ai.goal.VibeGoal;
import com.github.llamarama.team.item.ModItems;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ItemSteerable;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.SaddledComponent;
import net.minecraft.entity.ai.RangedAttackMob;
import net.minecraft.entity.ai.goal.FormCaravanGoal;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.passive.AbstractDonkeyEntity;
import net.minecraft.entity.passive.LlamaEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.nbt.CompoundTag;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.stream.Stream;

@SuppressWarnings("UnresolvedMixinReference")
@Mixin(LlamaEntity.class)
public abstract class MixinLlamaEntity extends AbstractDonkeyEntity implements RangedAttackMob, ItemSteerable {

    private static TrackedData<Boolean> CARPETED;
    private static TrackedData<Integer> BOOST_TIME;
    private SaddledComponent saddledComponent;

    protected MixinLlamaEntity(EntityType<? extends AbstractDonkeyEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void onStaticInit(CallbackInfo ci) {
        BOOST_TIME = DataTracker.registerData(LlamaEntity.class, TrackedDataHandlerRegistry.INTEGER);
        CARPETED = DataTracker.registerData(LlamaEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    }

    @Inject(method = "<init>", at = @At("TAIL"))
    public void onInit(EntityType<? extends LlamaEntity> entityType, World world, CallbackInfo ci) {
        this.saddledComponent = new SaddledComponent(this.getDataTracker(), BOOST_TIME, CARPETED);
    }

    @Shadow
    @Nullable
    public abstract DyeColor getCarpetColor();

    @Inject(method = "initGoals()V", at = @At("TAIL"))
    public void onInitGoals(CallbackInfo ci) {
        this.goalSelector.remove(new FormCaravanGoal(((LlamaEntity) (Object) this), 2.0999999046325684d));
        this.goalSelector.add(2, new CaravanGoal<>(((LlamaEntity) (Object) this), 2.99d));
        this.goalSelector.add(5, new VibeGoal(((LlamaEntity) (Object) this)));
    }

    @Inject(method = "initDataTracker()V", at = @At("TAIL"))
    public void onInitDataTracker(CallbackInfo ci) {
        this.dataTracker.startTracking(CARPETED, false);
        this.dataTracker.startTracking(BOOST_TIME, 0);
    }

    @Inject(method = "writeCustomDataToTag(Lnet/minecraft/nbt/CompoundTag;)V", at = @At("RETURN"))
    public void onWriteCustomDataToTag(CompoundTag tag, CallbackInfo ci) {
        saddledComponent.toTag(tag);
    }

    @Inject(method = "readCustomDataFromTag(Lnet/minecraft/nbt/CompoundTag;)V", at = @At("RETURN"))
    public void onReadCustomDataFromTag(CompoundTag tag, CallbackInfo ci) {
        saddledComponent.fromTag(tag);
    }

    @Override
    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        if (player.getStackInHand(hand).getItem() == Items.BUCKET) {
            return ActionResult.PASS;
        } else {
            return super.interactMob(player, hand);
        }
    }

    @Override
    public void travel(Vec3d movementInput) {
        this.travel(this, this.saddledComponent, movementInput);
    }

    @Override
    public boolean consumeOnAStickItem() {
        return this.saddledComponent.boost(this.getRandom());
    }

    @Override
    public void setMovementInput(Vec3d movementInput) {
        super.travel(movementInput);
    }

    @Override
    public float getSaddledSpeed() {
        return ((float) (EntityAttributes.GENERIC_MOVEMENT_SPEED.getDefaultValue()) * 2.5f);
    }

    @Inject(method = "canBeControlledByRider", at = @At("HEAD"), cancellable = true)
    public void onCanBeControlledByRider(CallbackInfoReturnable<Boolean> returnValue) {
        LivingEntity rider = (LivingEntity) this.getPrimaryPassenger();

        boolean out = rider != null && this.isSaddled() && Stream.of(rider.getStackInHand(Hand.MAIN_HAND), rider.getStackInHand(Hand.OFF_HAND)).anyMatch((itemStack) -> itemStack.getItem() == ModItems.HAY_ON_A_STICK);

        returnValue.setReturnValue(out);
    }

    @Override
    public boolean isSaddled() {
        return this.getCarpetColor() != null;
    }

    @Override
    public void onTrackedDataSet(TrackedData<?> data) {
        if (data.equals(BOOST_TIME)) {
            this.saddledComponent.boost();
        }

        super.onTrackedDataSet(data);
    }

}
