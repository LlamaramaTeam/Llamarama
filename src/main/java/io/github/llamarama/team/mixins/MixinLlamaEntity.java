package io.github.llamarama.team.mixins;

import io.github.llamarama.team.entity.ai.goal.CaravanGoal;
import io.github.llamarama.team.entity.ai.goal.VibeGoal;
import io.github.llamarama.team.item.ModItems;
import io.github.llamarama.team.item.tag.ModItemTags;
import io.github.llamarama.team.util.TagUtil;
import io.github.llamarama.team.util.annotation.InterfaceImplementation;
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
import net.minecraft.nbt.NbtCompound;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.ActionResult;
import net.minecraft.util.DyeColor;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.jetbrains.annotations.Nullable;
import org.spongepowered.asm.mixin.Implements;
import org.spongepowered.asm.mixin.Interface;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.stream.Stream;

@Mixin(LlamaEntity.class)
@Implements(@Interface(iface = ItemSteerable.class, prefix = "impl$"))
public abstract class MixinLlamaEntity extends AbstractDonkeyEntity implements RangedAttackMob, ItemSteerable {

    private static TrackedData<Boolean> CARPETED;
    private static TrackedData<Integer> BOOST_TIME;
    private SaddledComponent saddledComponent;

    @SuppressWarnings("unused")
    protected MixinLlamaEntity(EntityType<? extends AbstractDonkeyEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void onStaticInit(CallbackInfo ci) {
        BOOST_TIME = DataTracker.registerData(LlamaEntity.class, TrackedDataHandlerRegistry.INTEGER);
        CARPETED = DataTracker.registerData(LlamaEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    }

    @Inject(method = "<init>(Lnet/minecraft/entity/EntityType;Lnet/minecraft/world/World;)V", at = @At("TAIL"))
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

    @Inject(method = "initDataTracker()V", at = @At(value = "HEAD"))
    public void onInitDataTracker(CallbackInfo ci) {
        this.dataTracker.startTracking(CARPETED, false);
        this.dataTracker.startTracking(BOOST_TIME, 0);
    }

    @Inject(method = "writeCustomDataToNbt", at = @At("RETURN"))
    public void onWriteCustomDataToTag(NbtCompound tag, CallbackInfo ci) {
        saddledComponent.writeNbt(tag);
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("RETURN"))
    public void onReadCustomDataFromTag(NbtCompound tag, CallbackInfo ci) {
        saddledComponent.readNbt(tag);
    }

    @Override
    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        if (player.getStackInHand(hand).getItem() == Items.BUCKET) {
            return ActionResult.PASS;
        } else if (player.getStackInHand(hand).getItem() == Items.NETHERITE_INGOT && !this.world.isClient) {
            player.giveItemStack(
                TagUtil.getRandomItem(ModItemTags.LLAMA_DISCS, this.random)
                    .orElseGet(ModItems.LLAMARAMA::getDefaultStack)
            );
            player.getStackInHand(hand).decrement(1);
            this.world.playSoundFromEntity(null, this, SoundEvents.ENTITY_LLAMA_EAT, SoundCategory.NEUTRAL, 1.0f, 1.0f);

            return ActionResult.success(player.getEntityWorld().isClient());
        } else {
            return super.interactMob(player, hand);
        }
    }

    public void travel(Vec3d movementInput) {
        this.travel(this, this.saddledComponent, movementInput);
    }

    @InterfaceImplementation
    public boolean impl$consumeOnAStickItem() {
        return this.saddledComponent.boost(this.getRandom());
    }

    @InterfaceImplementation
    public void impl$setMovementInput(Vec3d movementInput) {
        super.travel(movementInput);
    }

    @InterfaceImplementation
    public float impl$getSaddledSpeed() {
        return ((float) (EntityAttributes.GENERIC_MOVEMENT_SPEED.getDefaultValue()) * 2.5f);
    }

    @Inject(method = "canBeControlledByRider()Z", at = @At("HEAD"), cancellable = true)
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
