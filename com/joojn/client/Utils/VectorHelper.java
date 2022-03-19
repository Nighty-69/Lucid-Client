package com.joojn.client.Utils;

import javax.vecmath.Vector2f;
import javax.vecmath.Vector3d;

import net.minecraft.util.MathHelper;

public class VectorHelper {

    public static Vector3d fromPitchYawVector(Vector2f p_189984_0_)
    {
        return fromPitchYaw(p_189984_0_.x, p_189984_0_.y);
    }

    /**
     * returns a Vector3d from given pitch and yaw degrees
     */
    public static Vector3d fromPitchYaw(float p_189986_0_, float p_189986_1_)
    {
        float f = MathHelper.cos(-p_189986_1_ * 0.017453292F - (float)Math.PI);
        float f1 = MathHelper.sin(-p_189986_1_ * 0.017453292F - (float)Math.PI);
        float f2 = -MathHelper.cos(-p_189986_0_ * 0.017453292F);
        float f3 = MathHelper.sin(-p_189986_0_ * 0.017453292F);
        return new Vector3d((double)(f1 * f2), (double)f3, (double)(f * f2));
    }


    public static double lengthSquared(Vector3d vec)
    {
        return vec.x * vec.x + vec.y * vec.y + vec.z * vec.z;
    }

    public static Vector3d scaleVec(Vector3d vec, double p_186678_1_)
    {
        return new Vector3d(vec.x * p_186678_1_, vec.y * p_186678_1_, vec.z * p_186678_1_);
    }

    public static Vector3d addVector(Vector3d vec, Vector3d vec2)
    {
        return new Vector3d(vec.x + vec2.x, vec.y + vec2.y, vec.z + vec2.z);
    }

    public static Vector3d addVector(Vector3d vec, double x, double y, double z)
    {
        return new Vector3d(vec.x + x, vec.y + y, vec.z + z);
    }


    public static Vector3d subtractVector(Vector3d vec, Vector3d vec2)
    {
        return subtractVector(vec, vec2.x, vec2.y, vec2.z);
    }

    public static Vector3d subtractVector(Vector3d vec, double x, double y, double z)
    {
        return addVector(vec, -x, -y, -z);
    }
    public static Vector3d crossProductVec(Vector3d vec, Vector3d vec2)
    {
        return new Vector3d(vec.y * vec2.z - vec.z * vec2.y, vec.z * vec2.x - vec.x * vec2.z, vec.x * vec2.y - vec.y * vec2.x);
    }
}
