package com.github.llamarama.team.entity.ai.goal;

import com.github.llamarama.team.entity.bumbllama.BumbleLlamaEntity;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.entity.player.PlayerEntity;

import java.util.*;
import java.util.stream.Collectors;

public class BeeHelpBumbleLlamaGoal extends Goal {

    private final BeeEntity beeEntity;
    private Set<BumbleLlamaEntity> nearLlamas;

    public BeeHelpBumbleLlamaGoal(BeeEntity entity) {
        this.beeEntity = entity;
        this.setControls(EnumSet.of(Control.TARGET, Control.MOVE));
    }

    @Override
    public boolean canStart() {
        List<Entity> near = this.beeEntity.world.getEntitiesByClass(BumbleLlamaEntity.class, this.beeEntity.getBoundingBox().expand(7), (entity) -> entity instanceof BumbleLlamaEntity && ((BumbleLlamaEntity) entity).getAttacker() != null);

        this.nearLlamas = near.stream().map((entity) -> (BumbleLlamaEntity) entity).collect(Collectors.toSet());

        return this.nearLlamas.size() != 0;
    }

    @Override
    public boolean shouldContinue() {
        return this.beeEntity.isAlive();
    }

    @Override
    public void stop() {
        super.stop();
        this.nearLlamas.clear();
    }

    @Override
    public void start() {
        super.start();
        Set<Entity> nearBees = new HashSet<>(this.beeEntity.world.getEntitiesByClass(BeeEntity.class, this.beeEntity.getBoundingBox().expand(7), Objects::nonNull));

        for (BumbleLlamaEntity bumbleLlamaEntity : this.nearLlamas) {
            LivingEntity attacker = bumbleLlamaEntity.getAttacker();

            if (attacker instanceof PlayerEntity && !((PlayerEntity) attacker).abilities.creativeMode) {
                nearBees.stream().map((entity) -> (BeeEntity) entity).forEach((beeEntity) -> {

                    beeEntity.setAngryAt(attacker.getUuid());
                    beeEntity.setTarget(attacker);
                    beeEntity.chooseRandomAngerTime();
                });

                break;
            }
        }

    }

}
