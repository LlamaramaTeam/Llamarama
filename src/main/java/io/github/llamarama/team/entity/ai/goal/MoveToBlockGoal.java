package io.github.llamarama.team.entity.ai.goal;

import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.entity.ai.goal.MoveToTargetPosGoal;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldView;

public class MoveToBlockGoal extends MoveToTargetPosGoal {

    private final BlockState targetBlock;

    public MoveToBlockGoal(PathAwareEntity mob, BlockState targetBlock, double speed, int range) {
        super(mob, speed, range, 4);
        this.targetBlock = targetBlock;
    }

    @Override
    protected boolean isTargetPos(WorldView world, BlockPos pos) {
        return world.getBlockState(pos) == this.targetBlock &&
                this.mob.world.getBlockState(this.mob.getBlockPos().down()).getBlock().equals(Blocks.GRASS);
    }

}
