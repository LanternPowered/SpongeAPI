/*
 * This file is part of SpongeAPI, licensed under the MIT License (MIT).
 *
 * Copyright (c) SpongePowered <https://www.spongepowered.org>
 * Copyright (c) contributors
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.spongepowered.api.data.manipulator.immutable.block;

import org.spongepowered.api.block.BlockTypes;
import org.spongepowered.api.data.key.Keys;
import org.spongepowered.api.data.manipulator.ImmutableDataManipulator;
import org.spongepowered.api.data.manipulator.mutable.block.BigMushroomPoresData;
import org.spongepowered.api.data.value.immutable.ImmutableSetValue;
import org.spongepowered.api.data.value.immutable.ImmutableValue;
import org.spongepowered.api.util.Direction;

/**
 * Represents data related to {@link BlockTypes#BROWN_MUSHROOM_BLOCK} and
 * {@link BlockTypes#RED_MUSHROOM_BLOCK}.
 *
 * @see Keys#BIG_MUSHROOM_PORES
 */
public interface ImmutableBigMushroomPoresData extends ImmutableDataManipulator<ImmutableBigMushroomPoresData, BigMushroomPoresData> {

    /**
     * Gets the {@link ImmutableSetValue} for the currently {@link Direction} sides
     * with pores.
     *
     * @return The immutable set value for the sides with pores
     * @see Keys#BIG_MUSHROOM_PORES
     */
    ImmutableSetValue<Direction> sides();

    /**
     * Gets the {@link ImmutableValue} for whether the {@link Direction#NORTH}
     * side has pores.
     *
     * @return The value for the northern side
     * @see Keys#BIG_MUSHROOM_PORES_NORTH
     */
    ImmutableValue<Boolean> north();

    /**
     * Gets the {@link ImmutableValue} for whether the {@link Direction#SOUTH}
     * side has pores.
     *
     * @return The value for the southern side
     * @see Keys#BIG_MUSHROOM_PORES_SOUTH
     */
    ImmutableValue<Boolean> south();

    /**
     * Gets the {@link ImmutableValue} for whether the {@link Direction#EAST}
     * side has pores.
     *
     * @return The value for the eastern side
     * @see Keys#BIG_MUSHROOM_PORES_EAST
     */
    ImmutableValue<Boolean> east();

    /**
     * Gets the {@link ImmutableValue} for whether the {@link Direction#WEST}
     * side has pores.
     *
     * @return The value for the western side
     * @see Keys#BIG_MUSHROOM_PORES_WEST
     */
    ImmutableValue<Boolean> west();

    /**
     * Gets the {@link ImmutableValue} for whether the {@link Direction#UP} (top)
     * side has pores.
     *
     * @return The value for the up/top side
     * @see Keys#BIG_MUSHROOM_PORES_UP
     */
    ImmutableValue<Boolean> up();

    /**
     * Gets the {@link ImmutableValue} for whether the {@link Direction#DOWN} (bottom)
     * side has pores.
     *
     * @return The value for the down/bottom side
     * @see Keys#BIG_MUSHROOM_PORES_DOWN
     */
    ImmutableValue<Boolean> down();

}
