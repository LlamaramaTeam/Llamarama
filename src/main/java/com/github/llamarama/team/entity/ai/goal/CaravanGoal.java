package com.github.llamarama.team.entity.ai.goal;

import com.github.llamarama.team.Llamarama;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.passive.LlamaEntity;
import net.minecraft.util.math.Vec3d;

import java.util.EnumSet;
import java.util.List;

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
        List<Entity> near = this.entity.world.getOtherEntities(this.entity, this.entity.getBoundingBox().expand(16.0d), entity -> entity instanceof LlamaEntity);

        T current;

        for (Entity entity : near) {
            current = (T) entity;


            if (current.isLeashed() && !current.hasFollower() && current != this.entity) {
                Llamarama.LOGGER.info("Llama " + current.getUuidAsString() + " is leashed and doesn't have a follower!!");

                this.entity.follow(current);
                return true;
            } else if (!current.isLeashed() && current.isFollowing() && !current.hasFollower()) {
                this.entity.follow(current);
                return true;
            }
        }

        return false;

    }

    @Override
    public void tick() {
        if (this.entity.isFollowing() && !this.entity.isLeashed()) {
            Vec3d vec = this.entity.getPos();

            assert this.entity.getFollowing() != null;

            Vec3d newVec = this.entity.getFollowing().getPos().add(vec.negate());

            Vec3d getToLoc = this.entity.getPos().add(newVec);

            this.entity.getNavigation().startMovingTo(getToLoc.getX(), getToLoc.getX(), getToLoc.getZ(), this.speed);
        }
    }

    @Override
    public boolean shouldContinue() {
        return this.entity.getFollowing() != null && this.entity.isFollowing() && this.entity.getFollowing().isAlive();
    }

    @Override
    public boolean canStop() {
        return true;
    }

    @Override
    public void stop() {
        this.entity.stopFollowing();
    }

}
