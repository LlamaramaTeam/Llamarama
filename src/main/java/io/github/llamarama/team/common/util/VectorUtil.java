package io.github.llamarama.team.common.util;

import org.joml.AxisAngle4f;
import org.joml.Quaternionf;
import org.joml.Vector3f;

public final class VectorUtil {
    public static final Vector3f NEGATIVE_X = new Vector3f(-1.0f, 0.0f, 0.0f);
    public static final Vector3f POSITIVE_X = new Vector3f(1.0f, 0.0f, 0.0f);
    public static final Vector3f NEGATIVE_Y = new Vector3f(0.0f, -1.0f, 0.0f);
    public static final Vector3f POSITIVE_Y = new Vector3f(0.0f, 1.0f, 0.0f);
    public static final Vector3f NEGATIVE_Z = new Vector3f(0.0f, 0.0f, -1.0f);
    public static final Vector3f POSITIVE_Z = new Vector3f(0.0f, 0.0f, 1.0f);
    public static final Vector3f ZERO = new Vector3f(0.0f, 0.0f, 0.0f);


    public static Quaternionf getDegreesQuaternion(Vector3f vector3f, float angle) {
        return new Quaternionf(new AxisAngle4f(angle, vector3f));
    }

}
