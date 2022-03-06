package io.github.llamarama.team.mixins;

import io.github.llamarama.team.common.item.ModItems;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.passive.LlamaEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.BucketItem;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(BucketItem.class)
public abstract class MixinBucketItem extends Item {

    public MixinBucketItem(Settings settings) {
        super(settings);
    }

    @Override
    public ActionResult useOnEntity(ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {

        ItemStack out;

        if (entity instanceof LlamaEntity && !entity.isBaby()) {

            out = ModItems.LLAMA_MILK.getDefaultStack();

            if (!user.getAbilities().creativeMode) {
                user.giveItemStack(out);
            } else if (!user.getInventory().contains(out)) {
                user.giveItemStack(out);
            }

            if (!user.getAbilities().creativeMode) {
                stack.decrement(1);
            }


            return ActionResult.success(user.getEntityWorld().isClient);
        } else {
            return super.useOnEntity(stack, user, entity, hand);
        }
    }

}
