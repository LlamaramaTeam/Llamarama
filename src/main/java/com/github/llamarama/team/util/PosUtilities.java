package com.github.llamarama.team.util;

import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;

public interface PosUtilities {

    static boolean checkForNoVelocity(Vec3d vec3d) {
        return Math.abs(vec3d.getX()) != 0d && Math.abs(vec3d.getY()) != 0d;
    }

    static double getDistanceFrom(Vec3d from, Vec3d to) {
        final double x2 = Math.pow(from.getX(), 2);
        final double y2 = Math.pow(from.getY(), 2);
        final double z2 = Math.pow(from.getZ(), 2);

        final double sum = x2 + y2 + z2;

        final double x2To = Math.pow(to.getX(), 2);
        final double y2To = Math.pow(to.getY(), 2);
        final double z2To = Math.pow(to.getZ(), 2);

        final double sumTo = x2To + y2To + z2To;

        return MathHelper.abs(MathHelper.sqrt(sumTo) - MathHelper.sqrt(sum));
    }

}
