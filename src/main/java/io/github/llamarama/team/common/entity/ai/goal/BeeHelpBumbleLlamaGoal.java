package io.github.llamarama.team.common.entity.ai.goal;

import io.github.llamarama.team.common.entity.bumbllama.BumbleLlamaEntity;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.ai.goal.Goal;
import net.minecraft.entity.passive.BeeEntity;
import net.minecraft.entity.player.PlayerEntity;

import java.util.*;

public class BeeHelpBumbleLlamaGoal extends Goal {

    private final BeeEntity beeEntity;
    private List<BumbleLlamaEntity> nearLlamas;

    public BeeHelpBumbleLlamaGoal(BeeEntity entity) {
        this.beeEntity = entity;
        this.setControls(EnumSet.of(Control.TARGET, Control.MOVE));
    }

    @Override
    public boolean canStart() {
        this.nearLlamas = this.beeEntity.world.getEntitiesByClass(BumbleLlamaEntity.class,
            this.beeEntity.getBoundingBox().expand(10),
            bumbleLlamaEntity -> bumbleLlamaEntity.getAttacker() != null);

        return this.nearLlamas.size() != 0;
    }

    @Override
    public boolean shouldContinue() {
        return this.beeEntity.isAlive();
    }

    @Override
    public void stop() {
        this.nearLlamas.clear();
    }

    @Override
    public void start() {
        Set<BeeEntity> nearBees = new HashSet<>(this.beeEntity.world.getEntitiesByClass(BeeEntity.class,
            this.beeEntity.getBoundingBox().expand(7), Objects::nonNull));

        for (BumbleLlamaEntity bumbleLlamaEntity : this.nearLlamas) {
            LivingEntity attacker = bumbleLlamaEntity.getAttacker();

            if (attacker instanceof PlayerEntity player && !player.getAbilities().creativeMode) {
                nearBees.forEach((beeEntity) -> {

                    beeEntity.setAngryAt(attacker.getUuid());
                    beeEntity.setTarget(attacker);
                    beeEntity.chooseRandomAngerTime();
                });

                break;
            }
        }

    }

}
