package com.github.llamarama.team.entity.ai.goal;

import com.github.llamarama.team.entity.bumbllama.BumbleLlamaEntity;
import com.github.llamarama.team.mixins.InvokerBeeEntity;
import com.github.llamarama.team.util.PosUtilities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.util.math.MathHelper;

import java.util.EnumSet;
import java.util.List;
import java.util.Random;

public class BeeFollowBumbleLlamaGoal extends Goal {

    private final BeeEntity beeEntity;
    private MobEntity target;
    private boolean currentlyFollowing;

    public BeeFollowBumbleLlamaGoal(BeeEntity beeEntity) {
        this.beeEntity = beeEntity;
        this.currentlyFollowing = false;
        this.target = null;
        this.setControls(EnumSet.of(Control.MOVE, Control.TARGET));
    }

    @Override
    public boolean canStart() {
        if (!this.currentlyFollowing && !this.beeEntity.hasNectar() && !this.beeEntity.hasAngerTime()) {
            List<Entity> nearEntities = this.beeEntity.getEntityWorld().getEntitiesByClass(BumbleLlamaEntity.class, this.beeEntity.getBoundingBox().expand(10.0d), (entity) -> entity.getClass() == BumbleLlamaEntity.class);

            nearEntities.forEach((entity) -> {
                double distanceTo = PosUtilities.getDistanceFrom(this.beeEntity.getPos(), entity.getPos());

                if (this.target == null && distanceTo > 3) {
                    this.target = (MobEntity) entity;
                    this.currentlyFollowing = true;
                }
            });
        }

        return this.currentlyFollowing;
    }

    @Override
    public boolean shouldContinue() {
        double distanceTo = PosUtilities.getDistanceFrom(this.beeEntity.getPos(), this.target.getPos());

        return distanceTo > 3 && !this.beeEntity.isLeashed() && !this.beeEntity.hasNectar();
    }

    @Override
    public void tick() {
        super.tick();

        double distance = PosUtilities.getDistanceFrom(this.beeEntity.getPos(), this.target.getPos());
        Random random = this.beeEntity.getRandom();

        if (distance < 5) {
            ((InvokerBeeEntity) this.beeEntity).invokeSetHasNectar(random.nextInt(2400) == 1);
        } else if (!this.beeEntity.isLeashed() && distance > 3) {

            double extraX = MathHelper.nextDouble(random, -1d, 1d);
            double extraY = MathHelper.nextDouble(random, 1d, 1.87d);
            double extraZ = MathHelper.nextDouble(random, -1d, 1d);

            double nextX = this.target.getX() + extraX;
            double nextY = this.target.getY() + extraY;
            double nextZ = this.target.getZ() + extraZ;

            this.beeEntity.getNavigation().startMovingTo(nextX, nextY, nextZ, 1.4d);
        }
    }

    @Override
    public void stop() {
        super.stop();
        this.target = null;
        this.currentlyFollowing = false;
    }


}
