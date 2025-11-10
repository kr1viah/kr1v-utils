package kr1v.kr1vUtils.client.mixinclasses.compatbreakers;

import it.unimi.dsi.fastutil.longs.Long2IntArrayMap;
import net.minecraft.util.math.Vec3i;

public class ChunkPosUtils {
    private static final Long2IntArrayMap idX = new Long2IntArrayMap();
    private static final Long2IntArrayMap idY = new Long2IntArrayMap();
    private static final Long2IntArrayMap idZ = new Long2IntArrayMap();

    public static Vec3i vec3i(long key) {
        if (isPacked(key)) {
            return new Vec3i(unpackX(key), unpackY(key), unpackZ(key));
        } else {
            return new Vec3i(xFromKey(key), yFromKey(key), zFromKey(key));
        }
    }

    public static int getX(long key) {
        if (isPacked(key)) {
            return unpackX(key);
        } else {
            return idX.get(key);
        }
    }
    public static int getY(long key) {
        if (isPacked(key)) {
            return unpackY(key);
        } else {
            return idY.get(key);
        }
    }
    public static int getZ(long key) {
        if (isPacked(key)) {
            return unpackZ(key);
        } else {
            return idZ.get(key);
        }
    }

    public static int highestIterations = 0;

    // returns true if packed, false if hashed
    private static boolean isPacked(long key) {
        return (key & 1) == 1;
    }

    private static boolean fitsIn21Bits(int x, int y, int z) {
        final int min = -(1 << 20);    // -1_048_576
        final int max =  (1 << 20) - 1; //  1_048_575
        return x >= min && x <= max
                && y >= min && y <= max
                && z >= min && z <= max;
    }

    public static long getKeyForXYZ(int x, int y, int z) {
        if (fitsIn21Bits(x, y, z)) {
            return pack(x, y, z);
        }
        long key = hash(x, y, z);

        int iterations = 0;

        while (true) {
            if (idX.containsKey(key)) {
                int storedX = idX.get(key);
                int storedY = idY.get(key);
                int storedZ = idZ.get(key);

                if (storedX == x && storedY == y && storedZ == z) {
                    return key;
                }
                iterations++;

                key = hash((int) (x + key), (int) (y + key), (int) (z + key));
                continue;
            }

            if (iterations > highestIterations) {
                highestIterations = iterations;
                System.out.println("New highest: " + highestIterations);
            }

            idX.put(key, x);
            idY.put(key, y);
            idZ.put(key, z);
            return key;
        }
    }

    public static int xFromKey(long key) {
        return idX.get(key);
    }
    public static int yFromKey(long key) {
        return idY.get(key);
    }
    public static int zFromKey(long key) {
        return idZ.get(key);
    }

    public static long pack(int x, int y, int z) {
        long mask = 0x1FFFFFL;
        long lx = ((long) x & mask) << 43;
        long ly = ((long) y & mask) << 22;
        long lz = ((long) z & mask) << 1;
        return lx | ly | lz | 1L;
    }

    public static int unpackX(long packed) {
        final long mask = 0x1FFFFFL;
        int raw = (int) ((packed >>> 43) & mask);
        int signBit = 1 << 20;
        if ((raw & signBit) != 0) {
            raw |= -(1 << 21);
        }
        return raw;
    }

    public static int unpackY(long packed) {
        final long mask = 0x1FFFFFL;
        int raw = (int) ((packed >>> 22) & mask);
        int signBit = 1 << 20;
        if ((raw & signBit) != 0) {
            raw |= -(1 << 21);
        }
        return raw;
    }

    public static int unpackZ(long packed) {
        final long mask = 0x1FFFFFL;
        int raw = (int) ((packed >>> 1) & mask);
        int signBit = 1 << 20;
        if ((raw & signBit) != 0) {
            raw |= -(1 << 21);
        }
        return raw;
    }

    // keeps lowest bit 0
    private static long hash(int x, int y, int z) {
        long h = 0x9E3779B97F4A7C15L;
        h ^= ((long) x & 0xFFFFFFFFL) + 0x9E3779B97F4A7C15L + (h << 6) + (h >> 2);
        h ^= ((long) y & 0xFFFFFFFFL) + 0x9E3779B97F4A7C15L + (h << 6) + (h >> 2);
        h ^= ((long) z & 0xFFFFFFFFL) + 0x9E3779B97F4A7C15L + (h << 6) + (h >> 2);
        h &= ~1L;
        return h;
    }
}
