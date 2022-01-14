package com.github.llamarama.team.util;

import net.minecraft.util.math.BlockPos;
import net.minecraft.util.math.MathHelper;
import net.minecraft.util.math.Vec3d;
import net.minecraft.util.math.Vec3i;
import net.minecraft.world.Heightmap;
import net.minecraft.world.World;

import java.util.Random;

public interface PosUtilities {

    static boolean checkForNoVelocity(Vec3d vec3d) {
        return Math.abs(vec3d.getX()) != 0d && Math.abs(vec3d.getY()) != 0d && Math.abs(vec3d.getZ()) != 0d;
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

        return Math.abs(Math.sqrt(x * x + y * y + z * z));
    }

    static float getDistanceFrom(BlockPos from, BlockPos to) {
        float xFrom = from.getX() + 0.5f;
        float yFrom = from.getY() + 0.5f;
        float zFrom = from.getZ() + 0.5f;

        float xTo = to.getX() + 0.5f;
        float yTo = to.getY() + 0.5f;
        float zTo = to.getZ() + 0.5f;

        float x = xTo - xFrom;
        float y = yTo - yFrom;
        float z = zTo - zFrom;

        return MathHelper.abs(MathHelper.sqrt(x * x + y * y + z * z));
    }

    static BlockPos getRandomPosInArea(World world, BlockPos center, int radius, boolean keepYIntact) {
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

        return out.toImmutable();
    }

    static boolean arePositionsEqual(Vec3i first, Vec3i second) {
        return first.getX() == second.getX() && first.getY() == second.getY() && first.getZ() == second.getZ();
    }

}
