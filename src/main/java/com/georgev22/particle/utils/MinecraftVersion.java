package com.georgev22.particle.utils;

import org.bukkit.Bukkit;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;

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
    UNKNOWN(new SubVersionRange("UNKNOWN", 0, 0)),
    ;

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
        } catch (Exception e) {
            currentVersion = UNKNOWN;
        }
    }

    private final SubVersionRange subVersionRange;

    MinecraftVersion(SubVersionRange subVersionRange) {
        this.subVersionRange = subVersionRange;
    }

    /**
     * Returns the current minecraft server version.
     *
     * @return the current minecraft server version.
     */
    public static MinecraftVersion getCurrentVersion() {
        return currentVersion;
    }

    /**
     * Get the version number of the Minecraft server.
     *
     * @return The version number of the Minecraft server.
     */
    public static int getVersionNumber() {
        return versionNumber;
    }

    /**
     * Get the release number of the Minecraft server.
     *
     * @return The release number of the Minecraft server.
     */
    public static int getReleaseNumber() {
        return releaseNumber;
    }

    @Contract(pure = true)
    public static @NotNull String getCurrentVersionName() {
        return currentVersion.name();
    }

    @Contract(pure = true)
    public static @NotNull String getCurrentVersionNameVtoLowerCase() {
        return currentVersion.name().replace("V", "v");
    }

    /**
     * Check if the version is above or equal.
     *
     * @param minecraftVersion The {@link MinecraftVersion} to be checked.
     * @return if the minecraft version is above or equal.
     */
    public boolean isAboveOrEqual(@NotNull MinecraftVersion minecraftVersion) {
        return this.ordinal() >= minecraftVersion.ordinal();
    }

    /**
     * Check if the version is above.
     *
     * @param minecraftVersion The {@link MinecraftVersion} to be checked.
     * @return if the minecraft version is above.
     */
    public boolean isAbove(@NotNull MinecraftVersion minecraftVersion) {
        return this.ordinal() > minecraftVersion.ordinal();
    }

    /**
     * Check if the version is below or equal.
     *
     * @param minecraftVersion The {@link MinecraftVersion} to be checked.
     * @return if the minecraft version is below or equal.
     */
    public boolean isBelowOrEqual(@NotNull MinecraftVersion minecraftVersion) {
        return this.ordinal() <= minecraftVersion.ordinal();
    }

    /**
     * Check if the version is below.
     *
     * @param minecraftVersion The {@link MinecraftVersion} to be checked.
     * @return if the minecraft version is below.
     */
    public boolean isBelow(@NotNull MinecraftVersion minecraftVersion) {
        return this.ordinal() < minecraftVersion.ordinal();
    }

    /**
     * Check if the version is equal.
     *
     * @param minecraftVersion The {@link MinecraftVersion} to be checked.
     * @return if the minecraft version is equal.
     */
    public boolean isEqual(@NotNull MinecraftVersion minecraftVersion) {
        return this.ordinal() == minecraftVersion.ordinal();
    }

    /**
     * Get the sub version range.
     *
     * @return The sub version range.
     **/
    public SubVersionRange getSubVersionRange() {
        return subVersionRange;
    }

    public static class SubVersionRange {
        private final String version;
        private final int start;
        private final int end;

        SubVersionRange(String version, int patch) {
            this(version, patch, patch);
        }

        SubVersionRange(String version, int start, int end) {
            this.version = version;
            this.start = start;
            this.end = end;
        }

        public String getVersion() {
            return version;
        }

        public int getStart() {
            return start;
        }

        public int getEnd() {
            return end;
        }
    }
}