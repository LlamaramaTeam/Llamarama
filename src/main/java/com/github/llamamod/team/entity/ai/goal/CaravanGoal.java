package com.github.llamamod.team.entity.ai.goal;

import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.passive.LlamaEntity;

public class CaravanGoal<T extends LlamaEntity> extends Goal {

    private final T entity;
    private final double speed;

    public CaravanGoal(T entity, double speed) {
        this.entity = entity;
        this.speed = speed;
    }

    @Override
    public boolean canStart() {
        return false;
    }

}
