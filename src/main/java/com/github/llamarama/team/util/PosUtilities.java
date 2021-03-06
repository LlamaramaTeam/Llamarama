package com.github.llamarama.team.util;

import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public interface PosUtilities {

    static boolean checkForNoVelocity(Vec3d vec3d) {
        return Math.abs(vec3d.getX()) != 0d && Math.abs(vec3d.getY()) != 0d;
    }

    static double getDistanceFrom(Vec3d from, Vec3d to) {
        double xFrom, yFrom, zFrom;
        double xTo, yTo, zTo;
        xFrom = from.getX();
        yFrom = from.getY();
        zFrom = from.getZ();

        xTo = to.getX();
        yTo = to.getY();
        zTo = to.getZ();

        final double x = xTo - xFrom;
        final double y = yTo - yFrom;
        final double z = zTo - zFrom;

        return Math.abs(MathHelper.sqrt(x * x + y * y + z * z));
    }

}
