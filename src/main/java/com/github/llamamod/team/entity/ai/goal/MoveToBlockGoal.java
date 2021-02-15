package com.github.llamamod.team.entity.ai.goal;

import net.minecraft.block.BlockState;
import net.minecraft.entity.ai.goal.MoveToTargetPosGoal;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.WorldView;

public class MoveToBlockGoal extends MoveToTargetPosGoal {

    private final BlockState targetBlock;

    public MoveToBlockGoal(PathAwareEntity mob, BlockState targetBlock, double speed, int range) {
        super(mob, speed, range);
        this.targetBlock = targetBlock;
    }

    @Override
    protected boolean isTargetPos(WorldView world, BlockPos pos) {
        return world.getBlockState(pos) == this.targetBlock;
    }

}
