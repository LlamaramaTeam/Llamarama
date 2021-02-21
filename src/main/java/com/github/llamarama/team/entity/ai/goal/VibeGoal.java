package com.github.llamarama.team.entity.ai.goal;

import com.github.llamarama.team.item.ModItems;
import net.minecraft.block.BlockState;
import net.minecraft.block.Blocks;
import net.minecraft.block.entity.BlockEntity;
import net.minecraft.block.entity.JukeboxBlockEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.Vec3d;

import java.util.EnumSet;

public class VibeGoal extends Goal {

    private final MobEntity entity;
    private Vec3d targetPosVector;
    private BlockPos targetPos;
    private BlockEntity jukeboxTile;
    private float extraY = 0f;
    private boolean reducing = true;

    public VibeGoal(MobEntity entity) {
        this.entity = entity;
        this.setControls(EnumSet.of(Control.LOOK, Control.MOVE));
    }

    @Override
    public void tick() {

        this.jukeboxTile = this.entity.getEntityWorld().getBlockEntity(this.targetPos);

        if (this.jukeboxTile instanceof JukeboxBlockEntity && (((JukeboxBlockEntity) this.jukeboxTile).getRecord().getItem() == ModItems.LLAMARAMA || ((JukeboxBlockEntity) this.jukeboxTile).getRecord().getItem() == ModItems.LLAMAJAMA)) {

            if (this.extraY <= 0.5f) {
                this.reducing = false;
            } else if (this.extraY > 2.0f) {
                this.reducing = true;
            }

            if (this.reducing) {
                this.extraY -= 0.3f;
            } else {
                this.extraY += 0.4f;
            }

            this.entity.getLookControl().lookAt(this.targetPosVector.getX(), this.targetPosVector.getY() + this.extraY, this.targetPosVector.getZ());

        } else {
            this.extraY = 0f;
        }
    }

    @Override
    public boolean canStart() {

        BlockPos.Mutable currentPos = new BlockPos.Mutable(this.entity.getPos().getX(), this.entity.getPos().getY(), this.entity.getZ());

        BlockState currentBlock;

        for (int i = (int) this.entity.getPos().getX() - 4; i <= this.entity.getPos().getX() + 4; i++) {
            for (int j = (int) this.entity.getPos().getY() - 4; j <= this.entity.getPos().getY() + 4; j++) {
                for (int k = (int) this.entity.getPos().getZ() - 4; k <= this.entity.getPos().getZ() + 4; k++) {
                    currentBlock = this.entity.world.getBlockState(currentPos.set(i, j, k));

                    if (currentBlock.getBlock() == Blocks.JUKEBOX) {
                        this.targetPosVector = new Vec3d(currentPos.getX(), currentPos.getY(), currentPos.getZ());
                        this.targetPos = currentPos.toImmutable();
                        this.jukeboxTile = this.entity.getEntityWorld().getBlockEntity(this.targetPos);

                        return true;
                    }
                }
            }
        }

        return false;
    }

    @Override
    public boolean shouldContinue() {
        return this.entity.getEntityWorld().getBlockState(this.targetPos).getBlock() == Blocks.JUKEBOX;
    }

}
