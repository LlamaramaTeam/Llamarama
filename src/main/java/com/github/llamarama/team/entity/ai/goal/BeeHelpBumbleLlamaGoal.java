package com.github.llamarama.team.entity.ai.goal;

import com.github.llamarama.team.entity.bumbllama.BumbleLlamaEntity;
import com.google.common.collect.Lists;
import net.minecraft.entity.Entity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.effect.StatusEffectInstance;
import net.minecraft.entity.effect.StatusEffects;
import net.minecraft.entity.passive.BeeEntity;

import java.util.EnumSet;
import java.util.List;

public class BeeHelpBumbleLlamaGoal extends Goal {

    private final BeeEntity beeEntity;
    private final List<BumbleLlamaEntity> nearLlamas;

    public BeeHelpBumbleLlamaGoal(BeeEntity entity) {
        this.beeEntity = entity;
        this.nearLlamas = Lists.newArrayList();
        this.setControls(EnumSet.of(Control.TARGET, Control.MOVE));
    }

    @Override
    public boolean canStart() {
        List<Entity> near = this.beeEntity.world.getEntitiesByClass(BumbleLlamaEntity.class, this.beeEntity.getBoundingBox().expand(7), (entity) -> entity instanceof BumbleLlamaEntity && ((BumbleLlamaEntity) entity).isAngry());

        near.forEach((entity) -> this.nearLlamas.add((BumbleLlamaEntity) entity));
        return near.size() != 0;
    }

    @Override
    public void start() {
        List<Entity> nearBees = this.beeEntity.world.getEntitiesByClass(BeeEntity.class, this.beeEntity.getBoundingBox().expand(7), (entity) -> entity instanceof BeeEntity);

        nearBees.forEach((entity) -> {
            if (entity instanceof BeeEntity) {
                LivingEntity attacker = this.nearLlamas.get(this.nearLlamas.size() - 1).getAttacker();
                ((BeeEntity) entity).addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 10, 1));

                if (attacker != null) {
                    attacker.addStatusEffect(new StatusEffectInstance(StatusEffects.GLOWING, 10, 1));
                }
            }
        });

        super.start();
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
    public void tick() {
        super.tick();

    }

}
