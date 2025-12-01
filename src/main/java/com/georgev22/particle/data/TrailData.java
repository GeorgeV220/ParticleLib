/*
 * MIT License
 *
 * Copyright (c) 2022 ByteZ1337
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in all
 * copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
 * SOFTWARE.
 */

package com.georgev22.particle.data;

import com.georgev22.particle.ParticleConstants;
import com.georgev22.particle.ParticleEffect;
import com.georgev22.particle.utils.MinecraftVersion;
import com.georgev22.particle.utils.ReflectionUtils;
import lombok.Getter;
import org.bukkit.Location;

import java.awt.*;

/**
 * This class holds all data that is needed by the client to display a {@link ParticleEffect#TRAIL}
 * particle. The required information is: The target {@link Location}, and the color of the particle, and the
 * duration of the particle.
 * <p>
 * Minecraft only supports full block coordinates for the start and destination location. So any
 * particle will spawn at the center of a block
 *
 * @author Nicholas V
 * @see ParticleEffect#TRAIL
 */
@Getter
public final class TrailData extends ParticleData {

    /**
     * The target of the particles to be displayed.
     */
    private final Location target;

    /**
     * The color of the particle.
     */
    private final Color color;

    /**
     * The duration of the particle.
     */
    private final int duration;


    /**
     * Constructs a new {@link TrailData} instance.
     *
     * @param target The target of the particles to be displayed.
     * @param color  The color of the particle.
     */
    public TrailData(Location target, Color color, int duration) {
        this.target = target;
        this.color = color;
        this.duration = duration;
    }

    /**
     * Constructs a new {@link TrailData} instance.
     *
     * @param target The target of the particles to be displayed.
     * @param color  The color of the particle.
     */
    public TrailData(Location target, Color color) {
        this.target = target;
        this.color = color;
        duration = -1;
    }

    /**
     * Creates a new TrailParticleOption instance with the data of the current {@link TrailData} instance.
     * <p>
     * Please note that this class is not supported in any versions before 1.21.3 and could lead to errors
     * if used in legacy versions. Additionally, this class is called TargetColorParticleOption in version 1.21.3
     * and TrailParticleOption in version 1.21.4 and above.
     *
     * @return a new TrailDataParticleOption instance with the data of the current {@link TrailData} instance.
     */
    @Override
    public Object toNMSData() {
        if (ReflectionUtils.MINECRAFT_VERSION.isBelow(MinecraftVersion.V1_21_R2) || getEffect() != ParticleEffect.TRAIL)
            return null;
        try {
            Object vec3D = ReflectionUtils.createVec3D(target.getX(), target.getY(), target.getZ());

            if (ReflectionUtils.MINECRAFT_VERSION.isBelow(MinecraftVersion.V1_21_R3)) {
                return ParticleConstants.TRAIL_PARTICLE_OPTION_CONSTRUCTOR.newInstance(vec3D, color.getRGB());
            }
            return ParticleConstants.TRAIL_PARTICLE_OPTION_CONSTRUCTOR.newInstance(vec3D, color.getRGB(), duration);

        } catch (Exception ex) {
            return null;
        }
    }
}
