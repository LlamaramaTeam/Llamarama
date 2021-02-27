package com.github.llamarama.team.item;

import net.minecraft.entity.ItemSteerable;
import net.minecraft.entity.passive.LlamaEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.util.Hand;
import net.minecraft.util.TypedActionResult;
import net.minecraft.world.World;

public class HayOnAStickItem extends Item {

    public HayOnAStickItem(Settings settings) {
        super(settings);
    }

    @Override
    public TypedActionResult<ItemStack> use(World world, PlayerEntity user, Hand hand) {
        ItemStack handItem = user.getStackInHand(hand);

        if (!world.isClient()) {
            if (user.hasVehicle() && user.getVehicle() instanceof LlamaEntity && user.getVehicle() instanceof ItemSteerable) {
                ItemSteerable riding = (ItemSteerable) user.getVehicle();

                if (riding.consumeOnAStickItem()) {
                    handItem.damage(5, user, (playerEntity) -> playerEntity.sendToolBreakStatus(hand));
                    return TypedActionResult.success(handItem);
                }

                return TypedActionResult.success(user.getStackInHand(hand));
            }
        } else {
            return TypedActionResult.pass(user.getStackInHand(hand));
        }

        return TypedActionResult.pass(handItem);
    }

}
