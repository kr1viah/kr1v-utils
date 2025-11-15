package kr1v.kr1vUtils.client.mixinclasses;

import kr1v.kr1vUtils.client.config.configs.Render;

public class PositionOffsetter {
	public static double[] worldOffsetFromPlayerAngles(double yawDeg, double pitchDeg) {
		double yaw  = Math.toRadians(yawDeg);
		double pitch = Math.toRadians(pitchDeg);

		double localRight = Render.OFFSET_X.getDoubleValue();
		double localUp = Render.OFFSET_Y.getDoubleValue();
		double localForward = Render.OFFSET_Z.getDoubleValue();

		// forward vector
		double fx = -Math.sin(yaw) * Math.cos(pitch);
		double fy = -Math.sin(pitch);
		double fz =  Math.cos(yaw) * Math.cos(pitch);

		// right vector (horizontal, before normalization)
		double rx =  Math.cos(yaw);
		double ry =  0;
		double rz =  Math.sin(yaw);
		double rlen = Math.sqrt(rx*rx + ry*ry + rz*rz);
		rx /= rlen; rz /= rlen; // ry is already 0

		// up vector = cross(forward, right)
		double ux = fy * rz - fz * ry; // fy*rz - fz*0
		double uy = fz * rx - fx * rz;
		double uz = fx * ry - fy * rx; // fx*0 - fy*rx
		double ulen = Math.sqrt(ux*ux + uy*uy + uz*uz);
		ux /= ulen; uy /= ulen; uz /= ulen;

		// world delta
		double dx = rx * localRight + ux * localUp + fx * localForward;
		double dy = ry * localRight + uy * localUp + fy * localForward;
		double dz = rz * localRight + uz * localUp + fz * localForward;

		return new double[] { dx, dy, dz };
	}
}
