package com.github.llamarama.team.entity.ai.goal;

import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.passive.LlamaEntity;
import net.minecraft.util.math.Vec3d;

import java.util.EnumSet;
import java.util.HashSet;
import java.util.Set;

@SuppressWarnings("unchecked")
public class CaravanGoal<T extends LlamaEntity> extends Goal {

    private final T entity;
    private final double speed;

    public CaravanGoal(T entity, double speed) {
        this.entity = entity;
        this.speed = speed;
        this.setControls(EnumSet.of(Control.MOVE));
    }

    @Override
    public boolean canStart() {
        if (!this.entity.isLeashed() && !this.entity.isFollowing()) {
            Set<Entity> near = new HashSet<>(this.entity.world.getOtherEntities(this.entity, this.entity.getBoundingBox().expand(16.0d), entity -> entity instanceof LlamaEntity));

            T current;

            for (Entity entity : near) {
                current = (T) entity;

                if (current.isLeashed() && !current.hasFollower() && current != this.entity) {
                    this.entity.follow(current);
                    return true;

                } else if (!current.isLeashed() && current.isFollowing() && !current.hasFollower() && this.canFollow(current, 1)) {
                    this.entity.follow(current);
                    return true;
                }
            }
        }

        return false;

    }

    @Override
    public void tick() {
        if (this.entity.getFollowing() != null && this.entity.isFollowing() && !this.entity.isLeashed()) {
            Vec3d currentPos = this.entity.getPos();

            Vec3d followingPos = this.entity.getFollowing().getPos();

            Vec3d crossProduct = followingPos
                    .add(currentPos.multiply(-1d))
                    .multiply(this.entity.distanceTo(this.entity.getFollowing()) - 2.0d);

            this.entity.getNavigation().startMovingTo(
                    currentPos.getX() + crossProduct.getX(),
                    currentPos.getY() + crossProduct.getY(),
                    currentPos.getZ() + crossProduct.getZ(), this.speed);
        }
    }

    @Override
    public void stop() {
        this.entity.stopFollowing();
    }

    public boolean canFollow(T reference, int length) {
        if (length > 8) {
            return false;
        } else if (reference.isFollowing()) {
            if (reference.getFollowing() != null && reference.getFollowing().isLeashed()) {
                return true;
            } else {
                length++;
                return this.canFollow((T) reference.getFollowing(), length);
            }
        } else {
            return false;
        }
    }

}
