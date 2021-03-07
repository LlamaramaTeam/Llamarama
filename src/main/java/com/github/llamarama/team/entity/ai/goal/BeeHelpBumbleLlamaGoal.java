package com.github.llamarama.team.entity.ai.goal;

import com.github.llamarama.team.entity.bumbllama.BumbleLlamaEntity;
import com.github.llamarama.team.util.PosUtilities;
import com.google.common.collect.Lists;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.passive.BeeEntity;

import java.util.EnumSet;
import java.util.List;
import java.util.stream.Collectors;

public class BeeHelpBumbleLlamaGoal extends Goal {

    private final BeeEntity beeEntity;
    private List<BumbleLlamaEntity> nearLlamas;
    private boolean canStart;

    public BeeHelpBumbleLlamaGoal(BeeEntity entity) {
        this.beeEntity = entity;
        this.nearLlamas = Lists.newArrayList();
        this.setControls(EnumSet.of(Control.TARGET, Control.MOVE));
    }

    @Override
    public boolean canStart() {
        List<Entity> near = this.beeEntity.world.getEntitiesByClass(BumbleLlamaEntity.class, this.beeEntity.getBoundingBox().expand(7), (entity) -> entity instanceof BumbleLlamaEntity && ((BumbleLlamaEntity) entity).getAttacker() != null);

        this.nearLlamas = near.stream().map((entity) -> (BumbleLlamaEntity) entity).collect(Collectors.toList());

        return this.nearLlamas.size() != 0;
    }

    @Override
    public boolean shouldContinue() {
        return this.beeEntity.isAlive() && this.canStart;
    }

    @Override
    public void stop() {
        super.stop();
        this.nearLlamas = Lists.newArrayList();
        this.canStart = false;
    }

    @Override
    public void tick() {
        super.tick();
        List<Entity> nearBees = this.beeEntity.world.getEntitiesByClass(BeeEntity.class, this.beeEntity.getBoundingBox().expand(7), (entity) -> entity instanceof BeeEntity);

        this.nearLlamas.forEach((bumbleLlamaEntity) -> {
            LivingEntity attacker = bumbleLlamaEntity.getAttacker();

            if (attacker != null) {
                nearBees.stream().map((entity) -> (BeeEntity) entity).forEach((beeEntity) -> {
                    double distance = PosUtilities.getDistanceFrom(beeEntity.getPos(), attacker.getPos());

                    if (distance < 1) {
                        beeEntity.tryAttack(attacker);
                    } else {
                        beeEntity.setTarget(attacker);
                        beeEntity.setAngryAt(attacker.getUuid());
                        beeEntity.chooseRandomAngerTime();
                    }
                });
            }
        });

    }

}
