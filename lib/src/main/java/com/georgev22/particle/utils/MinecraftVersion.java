package com.georgev22.particle.utils;

import org.bukkit.Bukkit;
import org.jetbrains.annotations.NotNull;

import java.util.Objects;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * Represents a Minecraft server version using a numeric format (major.minor.patch).
 */
public final class MinecraftVersion implements Comparable<MinecraftVersion> {

    private static final Pattern VERSION_PATTERN =
            Pattern.compile("^(\\d+)(?:\\.(\\d+))?(?:\\.(\\d+))?");

    private final int major;
    private final int minor;
    private final int patch;

    /**
     * The current server version, parsed once during class initialization.
     */
    private static final MinecraftVersion CURRENT;

    static {
        CURRENT = parse(Bukkit.getServer().getBukkitVersion());
    }

    /**
     * Constructs a new {@link MinecraftVersion}.
     *
     * @param major the major version (e.g. 1 or 26)
     * @param minor the minor version (e.g. 21 or 1)
     * @param patch the patch version (e.g. 4 or 11)
     */
    public MinecraftVersion(int major, int minor, int patch) {
        this.major = major;
        this.minor = minor;
        this.patch = patch;
    }

    /**
     * Returns the current Minecraft server version.
     *
     * @return the parsed server version
     */
    public static MinecraftVersion getCurrent() {
        return CURRENT;
    }

    /**
     * Parses a Bukkit version string into a {@link MinecraftVersion}.
     * <p>
     * Examples of supported formats:
     * <ul>
     *     <li>{@code 1.21.4-R0.1-SNAPSHOT}</li>
     *     <li>{@code 26.1.1}</li>
     * </ul>
     *
     * @param bukkitVersion the raw version string from Bukkit
     * @return a parsed {@link MinecraftVersion}, or {@code 0.0.0} if parsing fails
     */
    public static @NotNull MinecraftVersion parse(String bukkitVersion) {
        Matcher matcher = VERSION_PATTERN.matcher(bukkitVersion);

        if (!matcher.find()) {
            return new MinecraftVersion(0, 0, 0);
        }

        int major = Integer.parseInt(matcher.group(1));
        int minor = matcher.group(2) != null ? Integer.parseInt(matcher.group(2)) : 0;
        int patch = matcher.group(3) != null ? Integer.parseInt(matcher.group(3)) : 0;

        return new MinecraftVersion(major, minor, patch);
    }

    /**
     * Compares this version to another version.
     *
     * @param other the other version
     * @return a negative value if lower, positive if higher, 0 if equal
     */
    @Override
    public int compareTo(MinecraftVersion other) {
        if (this.major != other.major) {
            return Integer.compare(this.major, other.major);
        }
        if (this.minor != other.minor) {
            return Integer.compare(this.minor, other.minor);
        }
        return Integer.compare(this.patch, other.patch);
    }

    /**
     * Checks if this version is greater than or equal to the given version.
     * Patch is ignored (assumes 0).
     *
     * @param major the major version
     * @param minor the minor version
     * @return {@code true} if this version is >= given version
     */
    public boolean isAtLeast(int major, int minor) {
        return compareTo(new MinecraftVersion(major, minor, 0)) >= 0;
    }

    /**
     * Checks if this version is greater than or equal to the given version.
     *
     * @param major the major version
     * @param minor the minor version
     * @param patch the patch version
     * @return {@code true} if this version is >= given version
     */
    public boolean isAtLeast(int major, int minor, int patch) {
        return compareTo(new MinecraftVersion(major, minor, patch)) >= 0;
    }

    /**
     * Checks if this version is strictly lower than the given version.
     * Patch is ignored (assumes 0).
     *
     * @param major the major version
     * @param minor the minor version
     * @return {@code true} if this version is < given version
     */
    public boolean isBelow(int major, int minor) {
        return isBelow(major, minor, 0);
    }


    /**
     * Checks if this version is strictly lower than the given version.
     *
     * @param major the major version
     * @param minor the minor version
     * @param patch the patch version
     * @return {@code true} if this version is < given version
     */
    public boolean isBelow(int major, int minor, int patch) {
        return compareTo(new MinecraftVersion(major, minor, patch)) < 0;
    }

    /**
     * Checks if this version is within a range:
     * {@code [min, max)} (inclusive lower bound, exclusive upper bound).
     *
     * @param minMajor minimum major version
     * @param minMinor minimum minor version
     * @param maxMajor maximum major version
     * @param maxMinor maximum minor version
     * @return {@code true} if within the specified range
     */
    public boolean isBetween(
            int minMajor, int minMinor,
            int maxMajor, int maxMinor
    ) {
        return isAtLeast(minMajor, minMinor)
                && isBelow(maxMajor, maxMinor);
    }

    /**
     * Checks if this version is equal to the given version.
     * Patch is ignored (assumes 0).
     *
     * @param major the major version
     * @param minor the minor version
     * @return {@code true} if this version is equal to given version
     */
    public boolean isEqual(int major, int minor) {
        return isEqual(major, minor, 0);
    }

    /**
     * Checks if this version is equal to the given version.
     *
     * @param major the major version
     * @param minor the minor version
     * @param patch the patch version
     * @return {@code true} if this version is equal to given version
     */
    public boolean isEqual(int major, int minor, int patch) {
        return this.major == major && this.minor == minor && this.patch == patch;
    }

    /**
     * Checks if this version is strictly greater than the given version.
     * Patch is ignored (assumes 0).
     *
     * @param major the major version
     * @param minor the minor version
     * @return {@code true} if this version is > given version
     */
    public boolean isAbove(int major, int minor) {
        return isAbove(major, minor, 0);
    }

    /**
     * Checks if this version is strictly greater than the given version.
     *
     * @param major the major version
     * @param minor the minor version
     * @param patch the patch version
     * @return {@code true} if this version is > given version
     */
    public boolean isAbove(int major, int minor, int patch) {
        return compareTo(new MinecraftVersion(major, minor, patch)) > 0;
    }

    /**
     * @return the major version
     */
    public int getMajor() {
        return major;
    }

    /**
     * @return the minor version
     */
    public int getMinor() {
        return minor;
    }

    /**
     * @return the patch version
     */
    public int getPatch() {
        return patch;
    }

    /**
     * Returns the version in {@code major.minor.patch} format.
     *
     * @return string representation of this version
     */
    @Override
    public String toString() {
        return major + "." + minor + "." + patch;
    }

    /**
     * Checks if this version is equal to another object.
     *
     * @param obj the object to compare with
     * @return {@code true} if the object is a MinecraftVersion with equal major, minor, and patch values
     */
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;

        MinecraftVersion that = (MinecraftVersion) obj;

        return this.major == that.major
                && this.minor == that.minor
                && this.patch == that.patch;
    }

    /**
     * Returns a hash code for this version.
     *
     * @return hash code based on major, minor, and patch values
     */
    @Override
    public int hashCode() {
        return Objects.hash(major, minor, patch);
    }
}