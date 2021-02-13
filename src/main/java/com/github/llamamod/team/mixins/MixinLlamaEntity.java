package com.github.llamamod.team.mixins;

import com.github.llamamod.team.block.ModBlocks;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.ai.RangedAttackMob;
import net.minecraft.entity.passive.AbstractDonkeyEntity;
import net.minecraft.entity.passive.LlamaEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Items;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;

@Mixin(LlamaEntity.class)
public abstract class MixinLlamaEntity extends AbstractDonkeyEntity implements RangedAttackMob {

    protected MixinLlamaEntity(EntityType<? extends AbstractDonkeyEntity> entityType, World world) {
        super(entityType, world);
    }

    @Override
    public ActionResult interactMob(PlayerEntity player, Hand hand) {
        if (player.getStackInHand(hand).getItem() == Items.SHEARS) {
            this.dropItem(ModBlocks.LLAMA_WOOL.getItem());
            player.getStackInHand(hand).damage(1, player, (playerEntity -> playerEntity.sendToolBreakStatus(hand)));

            return ActionResult.SUCCESS;
        }

        return ActionResult.PASS;
    }

}
