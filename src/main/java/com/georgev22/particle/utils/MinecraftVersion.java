package com.georgev22.particle.utils;

import org.bukkit.Bukkit;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

/**
 * Represents supported Minecraft server versions and provides
 * utilities for version detection and comparison.
 *
 * <p>This enum resolves the running server version at runtime
 * and allows safe version comparisons.</p>
 */
public enum MinecraftVersion {

    V_1_7_R1(new SubVersionRange("1.7", 2, 4)),
    V_1_7_R2(new SubVersionRange("1.7", 5, 7)),
    V_1_7_R3(new SubVersionRange("1.7", 8, 9)),
    V_1_7_R4(new SubVersionRange("1.7", 10)),
    V1_8_R1(new SubVersionRange("1.8", 0, 1)),
    V1_8_R2(new SubVersionRange("1.8", 3, 3)),
    V1_8_R3(new SubVersionRange("1.8", 4, 9)),
    V1_9_R1(new SubVersionRange("1.9", 0, 2)),
    V1_9_R2(new SubVersionRange("1.9", 4)),
    V1_10_R1(new SubVersionRange("1.10", 0, 2)),
    V1_11_R1(new SubVersionRange("1.11", 0, 2)),
    V1_12_R1(new SubVersionRange("1.12", 0, 2)),
    V1_13_R1(new SubVersionRange("1.13", 0, 1)),
    V1_13_R2(new SubVersionRange("1.13", 2)),
    V1_14_R1(new SubVersionRange("1.14", 0, 4)),
    V1_15_R1(new SubVersionRange("1.15", 0, 2)),
    V1_16_R1(new SubVersionRange("1.16", 0, 1)),
    V1_16_R2(new SubVersionRange("1.16", 2, 3)),
    V1_16_R3(new SubVersionRange("1.16", 4, 5)),
    V1_17_R1(new SubVersionRange("1.17", 0, 1)),
    V1_18_R1(new SubVersionRange("1.18", 0, 1)),
    V1_18_R2(new SubVersionRange("1.18", 2)),
    V1_19_R1(new SubVersionRange("1.19", 0, 2)),
    V1_19_R2(new SubVersionRange("1.19", 3)),
    V1_19_R3(new SubVersionRange("1.19", 4)),
    V1_20_R1(new SubVersionRange("1.20", 0, 1)),
    V1_20_R2(new SubVersionRange("1.20", 2)),
    V1_20_R3(new SubVersionRange("1.20", 3, 4)),
    V1_20_R4(new SubVersionRange("1.20", 5, 6)),
    V1_21_R1(new SubVersionRange("1.21", 0, 1)),
    V1_21_R2(new SubVersionRange("1.21", 2, 3)),
    V1_21_R3(new SubVersionRange("1.21", 4)),
    V1_21_R4(new SubVersionRange("1.21", 5)),
    V1_21_R5(new SubVersionRange("1.21", 6, 8)),
    V1_21_R6(new SubVersionRange("1.21", 9, 10)),

    /**
     * Fallback value when no version could be detected.
     */
    UNKNOWN(new SubVersionRange("UNKNOWN", 0, 0));

    private static MinecraftVersion currentVersion;

    private static int versionNumber, releaseNumber;

    static {
        try {
            String bukkitVersion = Bukkit.getServer().getBukkitVersion();
            String[] versionParts = bukkitVersion.split("-")[0].split("\\.");

            if (versionParts.length >= 2) {
                int majorVersion = Integer.parseInt(versionParts[0]);
                int minorVersion = Integer.parseInt(versionParts[1]);
                int patchVersion = versionParts.length >= 3 ? Integer.parseInt(versionParts[2]) : 0;
                for (MinecraftVersion version : MinecraftVersion.values()) {
                    if (version.subVersionRange.version.equals(majorVersion + "." + minorVersion) &&
                            patchVersion >= version.subVersionRange.start && patchVersion <= version.subVersionRange.end) {
                        currentVersion = version;
                        versionNumber = Integer.parseInt(currentVersion.name().split("_")[1]);
                        releaseNumber = Integer.parseInt(currentVersion.name().split("R")[1]);
                        break;
                    }
                }
            }

            if (currentVersion == null) {
                currentVersion = UNKNOWN;
            }
        } catch (Exception ignored) {
            currentVersion = UNKNOWN;
        }
    }

    private final SubVersionRange subVersionRange;

    MinecraftVersion(SubVersionRange subVersionRange) {
        this.subVersionRange = subVersionRange;
    }

    /**
     * Returns the detected Minecraft server version.
     *
     * @return the current version or {@link #UNKNOWN}
     */
    public static MinecraftVersion getCurrentVersion() {
        return currentVersion;
    }

    /**
     * Returns the numeric minor Minecraft version.
     *
     * @return the minor version number
     */
    public static int getVersionNumber() {
        return versionNumber;
    }

    /**
     * Returns the internal release number.
     *
     * @return the release number
     */
    public static int getReleaseNumber() {
        return releaseNumber;
    }

    /**
     * Returns the enum name of the current version.
     *
     * @return the version enum name
     */
    @Contract(pure = true)
    public static @NotNull String getCurrentVersionName() {
        return currentVersion.name();
    }

    /**
     * Returns the enum name using lowercase 'v'.
     *
     * @return the version name with lowercase 'v'
     */
    @Contract(pure = true)
    public static @NotNull String getCurrentVersionNameVtoLowerCase() {
        return currentVersion.name().replace("V", "v");
    }

    /**
     * Checks if this version is newer than or equal to another.
     *
     * @param minecraftVersion the version to compare against
     * @return {@code true} if this version is newer or equal
     */
    public boolean isAboveOrEqual(@NotNull MinecraftVersion minecraftVersion) {
        return this.ordinal() >= minecraftVersion.ordinal();
    }

    /**
     * Checks if this version is newer than another.
     *
     * @param minecraftVersion the version to compare against
     * @return {@code true} if this version is newer
     */
    public boolean isAbove(@NotNull MinecraftVersion minecraftVersion) {
        return this.ordinal() > minecraftVersion.ordinal();
    }

    /**
     * Checks if this version is older than or equal to another.
     *
     * @param minecraftVersion the version to compare against
     * @return {@code true} if this version is older or equal
     */
    public boolean isBelowOrEqual(@NotNull MinecraftVersion minecraftVersion) {
        return this.ordinal() <= minecraftVersion.ordinal();
    }

    /**
     * Checks if this version is older than another.
     *
     * @param minecraftVersion the version to compare against
     * @return {@code true} if this version is older
     */
    public boolean isBelow(@NotNull MinecraftVersion minecraftVersion) {
        return this.ordinal() < minecraftVersion.ordinal();
    }

    /**
     * Checks if this version is equal to another.
     *
     * @param minecraftVersion the version to compare against
     * @return {@code true} if both versions are equal
     */
    public boolean isEqual(@NotNull MinecraftVersion minecraftVersion) {
        return this.ordinal() == minecraftVersion.ordinal();
    }

    /**
     * Returns the version range metadata.
     *
     * @return the sub version range
     */
    public SubVersionRange getSubVersionRange() {
        return subVersionRange;
    }

    /**
     * Holds patch range information for a Minecraft version.
     */
    public static class SubVersionRange {

        /**
         * Version string such as "1.20".
         */
        private final String version;

        /**
         * Lowest supported patch number.
         */
        private final int start;

        /**
         * Highest supported patch number.
         */
        private final int end;

        /**
         * Creates a fixed single-patch version range.
         *
         * @param version the major.minor version
         * @param patch   the patch number
         */
        SubVersionRange(String version, int patch) {
            this(version, patch, patch);
        }

        /**
         * Creates a ranged version definition.
         *
         * @param version the major.minor version
         * @param start   the starting patch
         * @param end     the ending patch
         */
        SubVersionRange(String version, int start, int end) {
            this.version = version;
            this.start = start;
            this.end = end;
        }

        /**
         * Returns the version string.
         *
         * @return the major.minor version string
         */
        public String getVersion() {
            return version;
        }

        /**
         * Returns the lowest patch in range.
         *
         * @return the starting patch number
         */
        public int getStart() {
            return start;
        }

        /**
         * Returns the highest patch in range.
         *
         * @return the ending patch number
         */
        public int getEnd() {
            return end;
        }
    }
}
