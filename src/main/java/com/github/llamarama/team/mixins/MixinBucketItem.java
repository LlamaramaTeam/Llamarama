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
        if (entity instanceof LlamaEntity) {
            ItemStack out = new ItemStack(ModItems.LLAMA_MILK);

            if (!user.abilities.creativeMode) {
                user.getStackInHand(hand).decrement(1);
            }

            if (!user.abilities.creativeMode || !user.inventory.contains(out)) {
                user.giveItemStack(out);
            }

            return ActionResult.SUCCESS;

        } else {
            return ActionResult.PASS;
        }
    }

}
