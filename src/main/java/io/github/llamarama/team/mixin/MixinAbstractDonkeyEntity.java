package io.github.llamarama.team.mixin;

import io.github.llamarama.team.common.register.ModItems;
import io.github.llamarama.team.common.tag.ModItemTags;
import io.github.llamarama.team.common.util.TagUtil;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.AbstractDonkeyEntity;
import net.minecraft.entity.passive.AbstractHorseEntity;
import net.minecraft.entity.passive.LlamaEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.ItemStack;
import net.minecraft.item.ItemUsage;
import net.minecraft.item.Items;
import net.minecraft.sound.SoundCategory;
import net.minecraft.sound.SoundEvents;
import net.minecraft.stat.Stats;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

@Mixin(AbstractDonkeyEntity.class)
public abstract class MixinAbstractDonkeyEntity extends AbstractHorseEntity {

    protected MixinAbstractDonkeyEntity(EntityType<? extends AbstractHorseEntity> entityType, World world) {
        super(entityType, world);
    }

    @Inject(method = "interactMob", at = @At("HEAD"), cancellable = true)
    private void overrideForLlamas(PlayerEntity player, Hand hand, CallbackInfoReturnable<ActionResult> cir) {
        var this$ = (AbstractDonkeyEntity) (Object) this;
        if (this$ instanceof LlamaEntity) {
            ItemStack stackInHand = player.getStackInHand(hand);
            if (stackInHand.isOf(Items.BUCKET) && !this$.isBaby()) {
                var stack = ItemUsage.exchangeStack(stackInHand, player, ModItems.LLAMA_MILK.getDefaultStack());
                player.setStackInHand(hand, stack);
                world.playSound(
                    player,
                    this.getX(), this.getY(), this.getZ(),
                    SoundEvents.ENTITY_COW_MILK,
                    SoundCategory.NEUTRAL, 1.0f, 1.0f
                );
                player.incrementStat(Stats.USED.getOrCreateStat(Items.BUCKET));
                cir.setReturnValue(ActionResult.SUCCESS);
            } else if (stackInHand.getItem() == Items.NETHERITE_INGOT && !this.world.isClient) {
                player.giveItemStack(TagUtil.getRandomItem(ModItemTags.LLAMA_DISCS, this.random)
                    .orElseGet(ModItems.LLAMARAMA::getDefaultStack));
                player.getStackInHand(hand).decrement(1);
                this.world.playSoundFromEntity(null, this, SoundEvents.ENTITY_LLAMA_EAT, SoundCategory.NEUTRAL, 1.0f, 1.0f);

                cir.setReturnValue(ActionResult.success(this.world.isClient));
            }
        }
    }

}
