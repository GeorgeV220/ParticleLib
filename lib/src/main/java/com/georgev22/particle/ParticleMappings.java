package com.georgev22.particle;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import org.jetbrains.annotations.Contract;
import org.jetbrains.annotations.NotNull;
import org.jetbrains.annotations.Nullable;
import org.jetbrains.annotations.UnmodifiableView;

import java.io.InputStreamReader;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

import static com.georgev22.particle.utils.ReflectionUtils.*;
import static com.georgev22.particle.utils.ReflectionUtils.MINECRAFT_VERSION;

/**
 * Maps classes, methods and fields to their respective names for different versions of Minecraft.
 */
public class ParticleMappings {

    /**
     * Map containing all mappings needed by ParticleLib.
     */
    private static final Map<String, String> mappings = new HashMap<>();

    static {

        try (InputStreamReader reader =
                     new InputStreamReader(Objects.requireNonNull(getResourceStreamSafe("mappings.json")))) {
            //noinspection deprecation - Outdated gson is used in pre 1.18 versions
            JsonArray array = new JsonParser().parse(reader).getAsJsonArray();

            for (int i = 0; i < array.size(); ++i) {
                JsonObject object = array.get(i).getAsJsonObject();
                processMapping(object);
            }

        } catch (Exception ex) {
            throw new RuntimeException("Could not load mappings", ex);
        }
    }

    /**
     * Processes a mapping JsonObject and adds it if it matches current MC version.
     */
    private static void processMapping(@NotNull JsonObject object) {
        if (!isInRange(
                object.get("min").getAsString(),
                object.get("max").getAsString())) {
            return;
        }

        String name = object.get("name").getAsString();
        JsonArray mappingsArray = object.get("mappings").getAsJsonArray();

        String bestMatch = null;
        int lastFrom = -1;

        for (int i = 0; i < mappingsArray.size(); ++i) {
            JsonObject entry = mappingsArray.get(i).getAsJsonObject();

            String from = entry.get("from").getAsString();
            int fromInt = toPackedVersion(from);

            if (getCurrentPackedVersion() >= fromInt && fromInt > lastFrom) {
                bestMatch = entry.get("value").getAsString();
                lastFrom = fromInt;
            }
        }

        if (bestMatch != null) {
            mappings.put(name, bestMatch);
        }
    }

    /**
     * Check if version fits min/max range from json.
     */
    private static boolean isInRange(String min, String max) {
        int current = getCurrentPackedVersion();
        int minInt = toPackedVersion(min);
        int maxInt = toPackedVersion(max);

        return current >= minInt && current <= maxInt;
    }

    /**
     * Packs the current version into a safe int.
     * <p>
     * Old format:
     * 1.21.10 -> (21 << 8) | 10
     * <p>
     * New format:
     * 26.1.1 -> (26 << 8) | 1
     */
    private static int getCurrentPackedVersion() {
        int feature;
        int patch;

        if (MINECRAFT_VERSION.getMajor() == 1) {
            feature = MINECRAFT_VERSION.getMinor();
            patch = MINECRAFT_VERSION.getPatch();
        } else {
            feature = MINECRAFT_VERSION.getMajor();
            patch = MINECRAFT_VERSION.getMinor();
        }

        return (feature << 8) | patch;
    }

    /**
     * Converts version string to packed int.
     * <p>
     * Supports:
     * 1.21.10
     * 21.3
     * 26
     * 26.1
     * 26.1.1
     */
    private static int toPackedVersion(@NotNull String version) {
        String[] parts = version.split("\\.");

        int feature;
        int patch;

        if (parts.length >= 3 && parts[0].equals("1")) {
            feature = Integer.parseInt(parts[1]);
            patch = Integer.parseInt(parts[2]);
        } else {
            feature = Integer.parseInt(parts[0]);
            patch = parts.length > 1
                    ? Integer.parseInt(parts[1])
                    : 0;
        }

        return (feature << 8) | patch;
    }

    /**
     * Gets the mapped {@link Class} for the given name.
     *
     * @param name the name of the class
     * @return the mapped {@link Class}
     */
    @Nullable
    public static Class<?> getMappedClass(String name) {
        String mapped = mappings.get(name);
        if (mapped == null) return null;
        return getNMSClass(mapped);
    }

    /**
     * Gets the mapped {@link Method} for the given name.
     *
     * @param targetClass    the class to get the method from
     * @param name           the name of the method
     * @param parameterTypes the parameter types of the method
     * @return the mapped {@link Method}
     */
    @Nullable
    public static Method getMappedMethod(Class<?> targetClass, String name, Class<?>... parameterTypes) {
        String mapped = mappings.get(name);
        if (mapped == null) return null;
        return getMethodOrNull(targetClass, mapped, parameterTypes);
    }

    /**
     * Gets the mapped {@link Field} for the given name.
     *
     * @param targetClass the class to get the field from
     * @param name        the name of the field
     * @param declared    whether to get the declared field or not
     * @return the mapped {@link Field}
     */
    @Nullable
    public static Field getMappedField(Class<?> targetClass, String name, boolean declared) {
        String mapped = mappings.get(name);
        if (mapped == null) return null;
        return getFieldOrNull(targetClass, mapped, declared);
    }

    /**
     * Gets an unmodifiable view of the mappings.
     *
     * @return an unmodifiable view of the mappings
     */
    @Contract(pure = true)
    public static @NotNull @UnmodifiableView Map<String, String> getMappings() {
        return Collections.unmodifiableMap(mappings);
    }
}
