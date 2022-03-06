package io.github.llamarama.team.common.item;

import net.minecraft.entity.ItemSteerable;
import net.minecraft.entity.passive.LlamaEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.Items;
import net.minecraft.stat.Stats;
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
            if (user.hasVehicle() && user.getVehicle() instanceof LlamaEntity &&
                user.getVehicle() instanceof ItemSteerable riding) {

                if (riding.consumeOnAStickItem()) {
                    handItem.damage(5, user, (playerEntity) -> playerEntity.sendToolBreakStatus(hand));

                    if (handItem.isEmpty()) {
                        ItemStack out = Items.FISHING_ROD.getDefaultStack();
                        out.setNbt(handItem.getNbt());

                        return TypedActionResult.success(out);
                    }
                    user.incrementStat(Stats.USED.getOrCreateStat(this));
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
