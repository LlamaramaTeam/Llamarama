package com.github.llamarama.team.util;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.world.Heightmap;
import net.minecraft.world.World;

import java.util.Random;

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

    static BlockPos.Mutable getRandomPosInArea(World world, BlockPos center, int radius, boolean keepYIntact) {
        Random random = world.getRandom();

        int extraX = random.nextInt(radius * 2);
        int extraZ = random.nextInt(radius * 2);

        int alteredX = center.getX() + extraX - radius;
        int alteredZ = center.getZ() + extraZ - radius;

        BlockPos.Mutable out = new BlockPos.Mutable(alteredX, 0, alteredZ);

        if (keepYIntact) {
            out.setY(center.getY());
        } else {
            int topY = world.getTopPosition(Heightmap.Type.MOTION_BLOCKING, out).getY();
            if (topY > 0) {
                out.setY(topY);
            }
        }

        return out;
    }

}
