package io.github.llamarama.team.mixin;

import net.minecraft.entity.EntityType;
import net.minecraft.entity.JumpingMount;
import net.minecraft.entity.RideableInventory;
import net.minecraft.entity.Saddleable;
import net.minecraft.entity.passive.AbstractHorseEntity;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.LlamaEntity;
import net.minecraft.inventory.InventoryChangedListener;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractHorseEntity.class)
public abstract class MixinAbstractHorseEntity extends AnimalEntity implements InventoryChangedListener, RideableInventory, JumpingMount, Saddleable {

    protected MixinAbstractHorseEntity(EntityType<? extends AnimalEntity> entityType, World world) {
        super(entityType, world);
    }

//    @Inject(method = "travel", at = @At("HEAD"), cancellable = true)
//    private void customLlamaTravel(Vec3d movementInput, CallbackInfo ci) {
//        var this$ = (AbstractHorseEntity)(Object) this;
//        if (this$ instanceof LlamaEntity llama) {
//            ((ItemSteerable) llama).travel(this, ((SaddledComponentProvider) this$).getSaddledComponent(), movementInput);
//            ci.cancel();
//        }
//    }

    @Inject(method = "isSaddled", at = @At("HEAD"), cancellable = true)
    private void llamaIsSaddled(CallbackInfoReturnable<Boolean> cir) {
        var this$ = (AbstractHorseEntity) (Object) this;
        if (this$ instanceof LlamaEntity llama) {
            cir.setReturnValue(llama.getCarpetColor() != null);
        }
    }

}
