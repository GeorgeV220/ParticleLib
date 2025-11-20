package com.georgev22.particle.utils;

@FunctionalInterface
public interface MinecraftVersionFunction<R> {

    /**
     * Applies this function to the given argument.
     *
     * @param value the function argument
     * @return the function result
     */
    R apply(MinecraftVersion value);
}
