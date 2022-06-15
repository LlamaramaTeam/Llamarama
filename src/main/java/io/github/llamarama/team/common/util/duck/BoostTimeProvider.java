package io.github.llamarama.team.common.util.duck;

import net.minecraft.entity.data.TrackedData;

public interface BoostTimeProvider {

    TrackedData<Integer> getBoostTime();

}
