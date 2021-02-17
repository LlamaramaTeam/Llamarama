package com.github.llamamod.team.entity.ai.goal;

import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.passive.LlamaEntity;
import net.minecraft.util.math.Box;

import java.util.List;

public class CaravanGoal<T extends LlamaEntity> extends Goal {

    private final T entity;
    private final double speed;

    public CaravanGoal(T entity, double speed) {
        this.entity = entity;
        this.speed = speed;
    }

    @Override
    public boolean canStart() {
        if (!this.entity.getEntityWorld().isClient()) {
            Box range = new Box(this.entity.getPos().add(5, 5, 5), this.entity.getPos().add(-5, -5, -5));

            List<Entity> entitiesNear = this.entity.getEntityWorld().getOtherEntities(this.entity, range, entity -> entity instanceof LlamaEntity);

            if (entitiesNear.size() != 0) {
                T current;

                T lastTarget;

                for (Entity entity : entitiesNear) {
                    current = (T) entity;

                    if (current.isLeashed() && !current.isFollowing()) {
                        return true;
                    } else if (!current.isLeashed() && current.isFollowing()) {
                        return true;
                    } else if (current.hasFollower()) {
                        current = (T) current.getFollowing();

                        lastTarget = current;
                    } else {
                        return false;
                    }
                }
            }
        }

        return false;
    }

}
