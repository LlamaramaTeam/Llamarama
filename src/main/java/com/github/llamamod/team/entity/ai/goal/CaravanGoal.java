package com.github.llamamod.team.entity.ai.goal;

import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.passive.LlamaEntity;
import net.minecraft.util.math.Vec3d;

import java.util.EnumSet;
import java.util.List;

public class CaravanGoal<T extends LlamaEntity> extends Goal {

    private final T entity;
    private final double speed;

    public CaravanGoal(T entity, double speed) {
        this.entity = entity;
        this.speed = speed;
        this.setControls(EnumSet.of(Control.LOOK));
    }

    @Override
    public boolean canStart() {
/*        if (!this.entity.getEntityWorld().isClient()) {
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
        */
        return true;
    }

    @Override
    public void tick() {
        List<Entity> nearEntities = this.entity.world.getOtherEntities(this.entity,
                this.entity.getBoundingBox().expand(16.0f), (entity) -> entity instanceof LlamaEntity);

        T current;

        Vec3d currentPos;

        for (Entity e : nearEntities) {
            current = (T) e;

            currentPos = current.getPos().add(this.entity.getPos().negate());

            if (current.isLeashed() && !current.hasFollower()){
                this.entity.getNavigation().startMovingTo(currentPos.x, currentPos.y, currentPos.z, this.speed);
                break;
            } else if (!current.isLeashed() && current.isFollowing() && !current.hasFollower()){
                this.entity.getNavigation().startMovingTo(currentPos.x, currentPos.y, currentPos.z, this.speed);
                break;
            }
        }
    }

    @Override
    public boolean shouldContinue() {
        return super.shouldContinue();
    }

    @Override
    public void stop() {
        this.entity.stopFollowing();
    }

}
