package com.github.llamarama.team.util;

import net.minecraft.util.math.Vec3d;

public interface PosUtilities {

    static boolean checkForNoVelocity(Vec3d vec3d) {
        return Math.abs(vec3d.getX()) != 0d && Math.abs(vec3d.getY()) != 0d;
    }

}
