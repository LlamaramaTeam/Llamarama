package com.github.llamarama.team.entity.ai.goal;

import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.FollowTargetGoal;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.WolfEntity;

public class MobChaseGoal<T extends MobEntity, E extends LivingEntity> extends FollowTargetGoal<E> {

    public MobChaseGoal(T llama, Class<E> entityClass) {
        super(llama, entityClass, 16, false, true, (livingEntity) -> {
            if (livingEntity instanceof WolfEntity wolfEntity) {
                return !wolfEntity.isTamed();
            } else {
                return true;
            }
        });
    }

    protected double getFollowRange() {
        return super.getFollowRange() * 0.25D;
    }

}
