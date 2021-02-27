package com.github.llamarama.team.mixins;

import com.github.llamarama.team.item.ModItems;
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

        if (entity instanceof LlamaEntity) {

            out = ModItems.LLAMA_MILK.getDefaultStack();

            if (!user.abilities.creativeMode) {
                user.giveItemStack(out);
            } else if (!user.inventory.contains(out)) {
                user.giveItemStack(out);
            }

            if (!user.abilities.creativeMode) {
                stack.decrement(1);
            }


            return ActionResult.success(user.getEntityWorld().isClient);
        } else {
            return super.useOnEntity(stack, user, entity, hand);
        }
    }

}
