package com.github.llamarama.team.entity.ai.goal;

import com.github.llamarama.team.entity.woolyllama.WoollyLlamaEntity;
import com.github.llamarama.team.mixins.AccessorLlamaEntity;
import net.minecraft.entity.ai.goal.RevengeGoal;
import net.minecraft.entity.mob.PathAwareEntity;
import net.minecraft.entity.passive.LlamaEntity;

public class SpitRevengeGoal extends RevengeGoal {

    public SpitRevengeGoal(PathAwareEntity mob, Class<?>... noRevengeTypes) {
        super(mob, noRevengeTypes);
    }

    @Override
    public boolean shouldContinue() {
        if (this.mob instanceof LlamaEntity) {
            WoollyLlamaEntity llamaEntity = (WoollyLlamaEntity) this.mob;
            if (((AccessorLlamaEntity) llamaEntity).spit()) {
                ((AccessorLlamaEntity) llamaEntity).invokeSetSpit(false);
                return false;
            }
        }
        return super.shouldContinue();
    }

}
