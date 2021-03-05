package com.github.llamarama.team.entity.ai.goal;

import com.github.llamarama.team.Llamarama;
import com.github.llamarama.team.util.PosUtilities;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.util.math.MathHelper;

import java.util.EnumSet;
import java.util.List;
import java.util.Random;

public class FollowEntityGoal extends Goal {

    private final MobEntity mobEntity;
    private final Class<? extends LivingEntity> targetClass;
    private MobEntity target;
    private boolean currentlyFollowing;

    public FollowEntityGoal(MobEntity mobEntity, Class<? extends MobEntity> targetClass) {
        this.mobEntity = mobEntity;
        this.targetClass = targetClass;
        this.currentlyFollowing = false;
        this.target = null;
        this.setControls(EnumSet.of(Control.MOVE, Control.TARGET));
    }

    @Override
    public boolean canStart() {
        if (!this.currentlyFollowing) {
            List<Entity> nearEntities = this.mobEntity.getEntityWorld().getEntitiesByClass(this.targetClass, this.mobEntity.getBoundingBox().expand(10.0d), (entity) -> entity.getClass() == this.targetClass);

            nearEntities.forEach((entity) -> {
                if (this.target == null) {
                    this.target = (MobEntity) entity;
                    this.currentlyFollowing = true;
                }
            });
        }

        return this.currentlyFollowing;
    }

    @Override
    public boolean shouldContinue() {
        double distanceTo = PosUtilities.getDistanceFrom(this.mobEntity.getPos(), this.target.getPos());

        Llamarama.LOGGER.info(distanceTo);
        return distanceTo > 3;
    }

    @Override
    public void tick() {
        super.tick();

        this.target.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 5, 1));

        Random random = this.mobEntity.getRandom();

        double extraX = MathHelper.nextDouble(random, -1d, 1d);
        double extraY = MathHelper.nextDouble(random, 1d, 1.6d);
        double extraZ = MathHelper.nextDouble(random, -1d, 1d);

        double nextX = this.target.getX() + extraX;
        double nextY = this.target.getY() + extraY;
        double nextZ = this.target.getZ() + extraZ;

        this.mobEntity.getNavigation().startMovingTo(nextX, nextY, nextZ, 1.4d);
    }

    @Override
    public void stop() {
        super.stop();
        this.target = null;
        this.currentlyFollowing = false;
    }

    @Override
    public boolean canStop() {
        double distanceTo = PosUtilities.getDistanceFrom(this.mobEntity.getPos(), this.target.getPos());

        return !(distanceTo < 3);
    }

}
