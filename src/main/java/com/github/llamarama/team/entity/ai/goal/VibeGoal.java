package com.github.llamarama.team.entity.ai.goal;

import com.github.llamarama.team.Llamarama;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.JukeboxBlock;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.JukeboxBlockEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.item.MusicDiscItem;
import net.minecraft.util.math.BlockPos;

import java.util.EnumSet;

public class VibeGoal extends Goal {

    private final MobEntity entity;
    private BlockPos targetPos;

    public VibeGoal(MobEntity entity) {
        this.entity = entity;
        this.setControls(EnumSet.of(Control.LOOK, Control.MOVE));
    }

    @Override
    public void tick() {
        /*Llamarama.LOGGER.info("Found jukebox!!!");*/

        BlockEntity jukeboxTile = this.entity.getEntityWorld().getBlockEntity(this.targetPos);

        if (jukeboxTile instanceof JukeboxBlockEntity && ((JukeboxBlockEntity) jukeboxTile).getRecord().getItem() instanceof MusicDiscItem) {
            this.entity.setHeadYaw(this.entity.getHeadYaw() - 2);
            Llamarama.LOGGER.info("Found jukebox with disc");
        }
    }

    @Override
    public boolean canStart() {

        BlockPos currentPos;

        BlockState currentBlock;

        for (int i = (int) this.entity.getPos().getX() - 4; i <= 4; i++) {
            for (int j = (int) this.entity.getPos().getY() - 4; j <= 4; j++) {
                for (int k = (int) this.entity.getPos().getZ() - 4; k <= 4; k++) {
                    currentBlock = this.entity.world.getBlockState(currentPos = new BlockPos(i, k, j));

                    /*Llamarama.LOGGER.info("Spam");*/

                    if (currentBlock.getBlock() == Blocks.JUKEBOX) {
                        this.targetPos = currentPos;

                        Llamarama.LOGGER.info("Returned from canStart! Found jukebox at " + this.targetPos.toShortString());
                        return true;
                    }
                }
            }
        }

        return false;
    }

    @Override
    public boolean shouldContinue() {
        return this.entity.getEntityWorld().getBlockState(this.targetPos).getBlock() instanceof JukeboxBlock;
    }

}
