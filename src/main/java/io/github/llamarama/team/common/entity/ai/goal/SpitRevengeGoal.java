package io.github.llamarama.team.common.entity.ai.goal;

import io.github.llamarama.team.mixin.AccessorLlamaEntity;
import net.minecraft.entity.ai.goal.RevengeGoal;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.passive.LlamaEntity;

public class SpitRevengeGoal extends RevengeGoal {

    public SpitRevengeGoal(PathAwareEntity mob, Class<?>... noRevengeTypes) {
        super(mob, noRevengeTypes);
    }

    @Override
    public boolean shouldContinue() {
        if (this.mob instanceof LlamaEntity llamaEntity) {
            if (((AccessorLlamaEntity) llamaEntity).getSpit()) {
                ((AccessorLlamaEntity) llamaEntity).invokeSetSpit(false);
                return false;
            }
        }
        return super.shouldContinue();
    }

}
