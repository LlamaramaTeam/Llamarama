package io.github.llamarama.team.mixin;

import io.github.llamarama.team.common.entity.ai.goal.CaravanGoal;
import io.github.llamarama.team.common.entity.ai.goal.VibeGoal;
import io.github.llamarama.team.common.register.ModItems;
import io.github.llamarama.team.common.util.duck.BoostTimeProvider;
import io.github.llamarama.team.common.util.duck.SaddledComponentProvider;
import net.minecraft.entity.*;
import net.minecraft.entity.ai.RangedAttackMob;
import net.minecraft.entity.ai.goal.FormCaravanGoal;
import net.minecraft.entity.attribute.EntityAttributes;
import net.minecraft.entity.data.DataTracker;
import net.minecraft.entity.data.TrackedData;
import net.minecraft.entity.data.TrackedDataHandlerRegistry;
import net.minecraft.entity.passive.AbstractDonkeyEntity;
import net.minecraft.entity.passive.LlamaEntity;
import net.minecraft.nbt.NbtCompound;
import net.minecraft.util.Hand;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Implements;
import org.spongepowered.asm.mixin.Interface;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

import java.util.Arrays;

@Mixin(LlamaEntity.class)
@Implements({
    @Interface(iface = ItemSteerable.class, prefix = "impl$"),
    @Interface(iface = SaddledComponentProvider.class, prefix = "saddle$"),
    @Interface(iface = BoostTimeProvider.class, prefix = "boost$")
})
public abstract class MixinLlamaEntity extends AbstractDonkeyEntity implements RangedAttackMob {

    private static TrackedData<Boolean> LLAMARAMA$CARPETED;
    private static TrackedData<Integer> LLAMARAMA$BOOST_TIME;
    private SaddledComponent llamarama$saddledComponent;

    protected MixinLlamaEntity(EntityType<? extends AbstractDonkeyEntity> entityType, World world) {
        super(entityType, world);
    }

    @SuppressWarnings("WrongEntityDataParameterClass")
    @Inject(method = "<clinit>", at = @At("TAIL"))
    private static void onStaticInit(CallbackInfo ci) {
        LLAMARAMA$BOOST_TIME = DataTracker.registerData(LlamaEntity.class, TrackedDataHandlerRegistry.INTEGER);
        LLAMARAMA$CARPETED = DataTracker.registerData(LlamaEntity.class, TrackedDataHandlerRegistry.BOOLEAN);
    }

    @Inject(method = "<init>(Lnet/minecraft/entity/EntityType;Lnet/minecraft/world/World;)V", at = @At("TAIL"))
    public void onInit(EntityType<? extends LlamaEntity> entityType, World world, CallbackInfo ci) {
        this.llamarama$saddledComponent = new SaddledComponent(this.getDataTracker(), LLAMARAMA$BOOST_TIME, LLAMARAMA$CARPETED);
    }

    @Inject(method = "initGoals()V", at = @At("TAIL"))
    public void onInitGoals(CallbackInfo ci) {
        this.goalSelector.remove(new FormCaravanGoal(((LlamaEntity) (Object) this), 2.01d));
        this.goalSelector.add(2, new CaravanGoal<>(((LlamaEntity) (Object) this), 2.99d));
        this.goalSelector.add(5, new VibeGoal(((LlamaEntity) (Object) this)));
    }

    @Inject(method = "initDataTracker()V", at = @At(value = "HEAD"))
    public void onInitDataTracker(CallbackInfo ci) {
        this.dataTracker.startTracking(LLAMARAMA$CARPETED, false);
        this.dataTracker.startTracking(LLAMARAMA$BOOST_TIME, 0);
    }

    @Inject(method = "writeCustomDataToNbt", at = @At("RETURN"))
    public void onWriteCustomDataToTag(NbtCompound tag, CallbackInfo ci) {
        this.llamarama$saddledComponent.writeNbt(tag);
    }

    @Inject(method = "readCustomDataFromNbt", at = @At("RETURN"))
    public void onReadCustomDataFromTag(NbtCompound tag, CallbackInfo ci) {
        this.llamarama$saddledComponent.readNbt(tag);
    }

    @Inject(method = "getPrimaryPassenger()Lnet/minecraft/entity/LivingEntity;", at = @At("HEAD"), cancellable = true)
    public void onCanBeControlledByRider(CallbackInfoReturnable<LivingEntity> cir) {
        if (this.isSaddled()) {
            Entity firstPassenger = this.getFirstPassenger();
            if (firstPassenger instanceof LivingEntity living && Arrays.stream(Hand.values()).map(living::getStackInHand).anyMatch(it -> it.isOf(ModItems.HAY_ON_A_STICK))) {
                cir.setReturnValue(living);
            }
        }
    }

    @Override // We have to use override here even though it's not good because we use both this$travel and super$travel
    public void travel(Vec3d movementInput) {
        ((ItemSteerable) this).travel(this, this.llamarama$saddledComponent, movementInput);
    }

    public boolean impl$consumeOnAStickItem() {
        return this.llamarama$saddledComponent.boost(this.getRandom());
    }

    public void impl$setMovementInput(Vec3d movementInput) {
        super.travel(movementInput);
    }

    public float impl$getSaddledSpeed() {
        return ((float) (EntityAttributes.GENERIC_MOVEMENT_SPEED.getDefaultValue()) * 2.5f);
    }

    public SaddledComponent saddle$getSaddledComponent() {
        return this.llamarama$saddledComponent;
    }

    public TrackedData<Integer> boost$getBoostTime() {
        return LLAMARAMA$BOOST_TIME;
    }

}
