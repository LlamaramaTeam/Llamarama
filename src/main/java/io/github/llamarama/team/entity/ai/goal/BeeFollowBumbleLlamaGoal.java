package io.github.llamarama.team.entity.ai.goal;

import io.github.llamarama.team.entity.bumbllama.BumbleLlamaEntity;
import io.github.llamarama.team.util.PosUtilities;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.mob.MobEntity;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.util.math.MathHelper;

import java.util.*;

public class BeeFollowBumbleLlamaGoal extends Goal {

    private final BeeEntity beeEntity;
    private MobEntity target;
    private boolean currentlyFollowing;

    public BeeFollowBumbleLlamaGoal(BeeEntity beeEntity) {
        this.beeEntity = beeEntity;
        this.currentlyFollowing = false;
        this.target = null;
        this.setControls(EnumSet.of(Control.MOVE, Control.TARGET, Control.LOOK));
    }

    @Override
    public boolean canStart() {
        if (!this.currentlyFollowing && !this.beeEntity.hasNectar() && !this.beeEntity.hasAngerTime()) {
            Set<BumbleLlamaEntity> nearEntities =
                    new HashSet<>(this.beeEntity.getEntityWorld().getEntitiesByClass(BumbleLlamaEntity.class,
                            this.beeEntity.getBoundingBox().expand(10.0d),
                            Objects::nonNull));

            nearEntities.forEach((bumbleLlamaEntity) -> {
                double distanceTo = PosUtilities.getDistanceFrom(this.beeEntity.getPos(), bumbleLlamaEntity.getPos());

                if (this.target == null && distanceTo > 3) {
                    this.target = bumbleLlamaEntity;
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
        double distance = PosUtilities.getDistanceFrom(this.beeEntity.getPos(), this.target.getPos());
        Random random = this.beeEntity.getRandom();

        if (distance < 5) {
            this.beeEntity.setHasNectar(random.nextInt(500) == 0);
        } else if (!this.beeEntity.isLeashed() && distance > 3) {

            double extraX = MathHelper.nextDouble(random, -1d, 1d);
            double extraY = MathHelper.nextDouble(random, 1d, 1.87d);
            double extraZ = MathHelper.nextDouble(random, -1d, 1d);

            double nextX = this.target.getX() + extraX;
            double nextY = this.target.getY() + extraY;
            double nextZ = this.target.getZ() + extraZ;

            this.beeEntity.getNavigation().startMovingTo(nextX, nextY, nextZ, 1.4d);
            this.beeEntity.getLookControl().lookAt(this.target);
        }
    }

    @Override
    public void stop() {
        this.target = null;
        this.currentlyFollowing = false;
    }


}
