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
package org.spongepowered.api.data.key;

import com.flowpowered.math.vector.Vector3d;
import com.flowpowered.math.vector.Vector3i;
import org.spongepowered.api.block.BlockState;
import org.spongepowered.api.block.BlockType;
import org.spongepowered.api.block.BlockTypes;
import org.spongepowered.api.block.tileentity.Banner;
import org.spongepowered.api.block.tileentity.CommandBlock;
import org.spongepowered.api.block.tileentity.EndGateway;
import org.spongepowered.api.block.tileentity.MobSpawner;
import org.spongepowered.api.block.tileentity.Piston;
import org.spongepowered.api.block.tileentity.Sign;
import org.spongepowered.api.block.tileentity.Structure;
import org.spongepowered.api.block.tileentity.TileEntity;
import org.spongepowered.api.block.tileentity.carrier.Beacon;
import org.spongepowered.api.block.tileentity.carrier.BrewingStand;
import org.spongepowered.api.block.tileentity.carrier.Furnace;
import org.spongepowered.api.block.tileentity.carrier.Hopper;
import org.spongepowered.api.block.tileentity.carrier.TileEntityCarrier;
import org.spongepowered.api.data.manipulator.mutable.*;
import org.spongepowered.api.data.meta.PatternLayer;
import org.spongepowered.api.data.property.Properties;
import org.spongepowered.api.data.type.Art;
import org.spongepowered.api.data.type.BodyPart;
import org.spongepowered.api.data.type.BodyParts;
import org.spongepowered.api.data.type.Career;
import org.spongepowered.api.data.type.ChestAttachmentType;
import org.spongepowered.api.data.type.ComparatorType;
import org.spongepowered.api.data.type.DyeColor;
import org.spongepowered.api.data.type.FoxType;
import org.spongepowered.api.data.type.HandPreference;
import org.spongepowered.api.data.type.Hinge;
import org.spongepowered.api.data.type.HorseColor;
import org.spongepowered.api.data.type.HorseStyle;
import org.spongepowered.api.data.type.InstrumentType;
import org.spongepowered.api.data.type.LlamaVariant;
import org.spongepowered.api.data.type.NotePitch;
import org.spongepowered.api.data.type.CatType;
import org.spongepowered.api.data.type.ParrotVariant;
import org.spongepowered.api.data.type.PickupRule;
import org.spongepowered.api.data.type.PortionType;
import org.spongepowered.api.data.type.RabbitType;
import org.spongepowered.api.data.type.RailDirection;
import org.spongepowered.api.data.type.SlabPortion;
import org.spongepowered.api.data.type.StairShape;
import org.spongepowered.api.data.type.StructureMode;
import org.spongepowered.api.data.type.Surface;
import org.spongepowered.api.data.type.WoodType;
import org.spongepowered.api.data.type.WireAttachmentType;
import org.spongepowered.api.data.value.BoundedValue;
import org.spongepowered.api.data.value.ListValue;
import org.spongepowered.api.data.value.MapValue;
import org.spongepowered.api.data.value.OptionalValue;
import org.spongepowered.api.data.value.SetValue;
import org.spongepowered.api.data.value.Value;
import org.spongepowered.api.data.value.WeightedCollectionValue;
import org.spongepowered.api.effect.particle.ParticleType;
import org.spongepowered.api.effect.potion.PotionEffect;
import org.spongepowered.api.effect.potion.PotionEffectType;
import org.spongepowered.api.entity.AreaEffectCloud;
import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.entity.EntityArchetype;
import org.spongepowered.api.entity.EntitySnapshot;
import org.spongepowered.api.entity.EntityType;
import org.spongepowered.api.entity.ExperienceOrb;
import org.spongepowered.api.entity.FallingBlock;
import org.spongepowered.api.entity.Item;
import org.spongepowered.api.entity.ShulkerBullet;
import org.spongepowered.api.entity.living.animal.Cat;
import org.spongepowered.api.entity.living.animal.Fox;
import org.spongepowered.api.entity.projectile.FireworkRocket;
import org.spongepowered.api.entity.vehicle.Boat;
import org.spongepowered.api.item.potion.PotionType;
import org.spongepowered.api.entity.explosive.Explosive;
import org.spongepowered.api.entity.explosive.FusedExplosive;
import org.spongepowered.api.entity.hanging.ItemFrame;
import org.spongepowered.api.entity.hanging.Painting;
import org.spongepowered.api.entity.living.Ageable;
import org.spongepowered.api.entity.living.Agent;
import org.spongepowered.api.entity.living.ArmorStand;
import org.spongepowered.api.entity.living.Bat;
import org.spongepowered.api.entity.living.Humanoid;
import org.spongepowered.api.entity.living.Living;
import org.spongepowered.api.entity.living.Villager;
import org.spongepowered.api.entity.living.animal.Animal;
import org.spongepowered.api.entity.living.animal.Horse;
import org.spongepowered.api.entity.living.animal.Llama;
import org.spongepowered.api.entity.living.animal.Ocelot;
import org.spongepowered.api.entity.living.animal.Parrot;
import org.spongepowered.api.entity.living.animal.Pig;
import org.spongepowered.api.entity.living.animal.Rabbit;
import org.spongepowered.api.entity.living.animal.Sheep;
import org.spongepowered.api.entity.living.animal.Wolf;
import org.spongepowered.api.entity.living.golem.IronGolem;
import org.spongepowered.api.entity.living.monster.Blaze;
import org.spongepowered.api.entity.living.monster.Creeper;
import org.spongepowered.api.entity.living.monster.Enderman;
import org.spongepowered.api.entity.living.monster.Endermite;
import org.spongepowered.api.entity.living.monster.Slime;
import org.spongepowered.api.entity.living.monster.Vindicator;
import org.spongepowered.api.entity.living.monster.ZombiePigman;
import org.spongepowered.api.entity.living.player.Player;
import org.spongepowered.api.entity.living.player.User;
import org.spongepowered.api.entity.living.player.gamemode.GameMode;
import org.spongepowered.api.entity.projectile.DamagingProjectile;
import org.spongepowered.api.entity.projectile.EyeOfEnder;
import org.spongepowered.api.entity.projectile.arrow.Arrow;
import org.spongepowered.api.entity.projectile.explosive.fireball.Fireball;
import org.spongepowered.api.entity.vehicle.minecart.CommandBlockMinecart;
import org.spongepowered.api.entity.vehicle.minecart.Minecart;
import org.spongepowered.api.fluid.FluidStackSnapshot;
import org.spongepowered.api.data.manipulator.mutable.FluidItemData;
import org.spongepowered.api.data.manipulator.mutable.FluidTankData;
import org.spongepowered.api.item.FireworkEffect;
import org.spongepowered.api.item.ItemTypes;
import org.spongepowered.api.item.enchantment.Enchantment;
import org.spongepowered.api.item.inventory.ItemStack;
import org.spongepowered.api.item.inventory.ItemStackSnapshot;
import org.spongepowered.api.item.merchant.TradeOffer;
import org.spongepowered.api.profile.GameProfile;
import org.spongepowered.api.profile.property.ProfileProperty;
import org.spongepowered.api.statistic.Statistic;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.util.Axis;
import org.spongepowered.api.util.Color;
import org.spongepowered.api.util.Direction;
import org.spongepowered.api.util.RespawnLocation;
import org.spongepowered.api.util.generator.dummy.DummyObjectProvider;
import org.spongepowered.api.util.rotation.Rotation;
import org.spongepowered.api.util.weighted.WeightedSerializableObject;
import org.spongepowered.api.world.explosion.Explosion;
import org.spongepowered.api.world.weather.Weather;

import java.time.Duration;
import java.time.Instant;
import java.util.List;
import java.util.UUID;

/**
 * An enumeration of known {@link Key}s used throughout the API.
 */
@SuppressWarnings({"unused", "WeakerAccess"})
public final class Keys {

    // SORTFIELDS:ON

    /**
     * Represents the {@link Key} for the absorption amount of any
     * {@link Living} entity.
     *
     * @see AbsorptionData#absorption()
     */
    public static final Key<Value<Double>> ABSORPTION = DummyObjectProvider.createExtendedFor(Key.class, "ABSORPTION");

    /**
     * Represents the {@link Key} for the item a {@link Living} is using.
     * For example a player eating a food or blocking with a shield.
     *
     * <p>If there is no item, the snapshot will be empty. You can check this
     * with {@link ItemStackSnapshot#isEmpty()}.</p>
     *
     * @see ActiveItemData#activeItem()
     */
    public static final Key<Value<ItemStackSnapshot>> ACTIVE_ITEM = DummyObjectProvider.createExtendedFor(Key.class, "ACTIVE_ITEM");

    /**
     * Represents the {@link Key} for the "affecting spawning" state of
     * {@link Player}s.
     *
     * <p>A {@link Player} who does not affect spawning will be treated as a
     * spectator in regards to spawning. A {@link MobSpawner} will not be
     * activated by his presence and mobs around him may naturally despawn
     * if there is no other Player around who affects spawning.</p>
     *
     * @see AffectsSpawningData#affectsSpawning()
     */
    public static final Key<Value<Boolean>> AFFECTS_SPAWNING = DummyObjectProvider.createExtendedFor(Key.class,"AFFECTS_SPAWNING");

    /**
     * Represents the {@link Key} for the age of any {@link Ageable} creature
     * in ticks.
     *
     * @see AgeableData#age()
     */
    public static final Key<BoundedValue<Integer>> AGE = DummyObjectProvider.createExtendedFor(Key.class,"AGE");

    /**
     * Represents the {@link Key} for whether an {@link Agent}s AI is enabled.
     *
     * @see AgentData#aiEnabled()
     */
    public static final Key<Value<Boolean>> AI_ENABLED = DummyObjectProvider.createExtendedFor(Key.class,"AI_ENABLED");

    /**
     * Represents the {@link Key} for how angry an {@link Entity} is. This
     * applies mostly to {@link ZombiePigman}.
     *
     * <p>Unlike {@link #ANGRY}, the aggressiveness represented by this key may
     * fade over time and the entity will become peaceful again once its anger
     * reaches its minimum.</p>
     *
     * @see AngerableData#angerLevel()
     */
    public static final Key<BoundedValue<Integer>> ANGER = DummyObjectProvider.createExtendedFor(Key.class,"ANGER");

    /**
     * Represents the {@link Key} for whether an {@link Entity} is currently
     * aggressive. This mostly applies to wolves.
     *
     * @see AggressiveData#aggressive()
     */
    public static final Key<Value<Boolean>> ANGRY = DummyObjectProvider.createExtendedFor(Key.class,"ANGRY");

    /**
     * Represents the {@link Key} for the age (in ticks) of an
     * {@link AreaEffectCloud} created by a lingering potion.
     *
     * @see AreaEffectCloudData#age()
     */
    public static final Key<BoundedValue<Integer>> AREA_EFFECT_CLOUD_AGE = DummyObjectProvider.createExtendedFor(Key.class,"AREA_EFFECT_CLOUD_AGE");

    /**
     * Represents the {@link Key} for the color of an {@link AreaEffectCloud}
     * created by a lingering potion.
     *
     * @see AreaEffectCloudData#color()
     */
    public static final Key<Value<Color>> AREA_EFFECT_CLOUD_COLOR = DummyObjectProvider.createExtendedFor(Key.class,"AREA_EFFECT_CLOUD_COLOR");

    /**
     * Represents the {@link Key} for the maximum age (in ticks) of an
     * {@link AreaEffectCloud} created by a lingering potion.
     *
     * @see AreaEffectCloudData#duration()
     */
    public static final Key<BoundedValue<Integer>> AREA_EFFECT_CLOUD_DURATION = DummyObjectProvider.createExtendedFor(Key.class,"AREA_EFFECT_CLOUD_DURATION");

    /**
     * Represents the {@link Key} for the amount of ticks the duration of an
     * {@link AreaEffectCloud} is increased or reduced when it applies its
     * effect.
     *
     * @see AreaEffectCloudData#durationOnUse()
     */
    public static final Key<BoundedValue<Integer>> AREA_EFFECT_CLOUD_DURATION_ON_USE = DummyObjectProvider.createExtendedFor(Key.class,"AREA_EFFECT_CLOUD_DURATION_ON_USE");

    /**
     * Represents the {@link Key} for the particle type of an
     * {@link AreaEffectCloud} created by a lingering potion.
     *
     * @see AreaEffectCloudData#particleType()
     */
    public static final Key<Value<ParticleType>> AREA_EFFECT_CLOUD_PARTICLE_TYPE = DummyObjectProvider.createExtendedFor(Key.class,"AREA_EFFECT_CLOUD_PARTICLE_TYPE");

    /**
     * Represents the {@link Key} for the radius of an {@link AreaEffectCloud}.
     *
     * @see AreaEffectCloudData#radius()
     */
    public static final Key<BoundedValue<Double>> AREA_EFFECT_CLOUD_RADIUS = DummyObjectProvider.createExtendedFor(Key.class,"AREA_EFFECT_CLOUD_RADIUS");

    /**
     * Represents the {@link Key} for the amount the radius of an
     * {@link AreaEffectCloud} grows or shrinks each time it applies its
     * effect.
     *
     * @see AreaEffectCloudData#radiusOnUse()
     */
    public static final Key<BoundedValue<Double>> AREA_EFFECT_CLOUD_RADIUS_ON_USE = DummyObjectProvider.createExtendedFor(Key.class,"AREA_EFFECT_CLOUD_RADIUS_ON_USE");

    /**
     * Represents the {@link Key} for the amount the radius of an
     * {@link AreaEffectCloud} grows or shrinks per tick.
     *
     * @see AreaEffectCloudData#radiusOnUse()
     */
    public static final Key<BoundedValue<Double>> AREA_EFFECT_CLOUD_RADIUS_PER_TICK = DummyObjectProvider.createExtendedFor(Key.class,"AREA_EFFECT_CLOUD_RADIUS_PER_TICK");

    /**
     * Represents the {@link Key} for the delay (in ticks) after which an
     * {@link AreaEffectCloud} will reapply its effect on a previously
     * affected {@link Entity}.
     *
     * @see AreaEffectCloudData#applicationDelay()
     */
    public static final Key<BoundedValue<Integer>> AREA_EFFECT_CLOUD_REAPPLICATION_DELAY = DummyObjectProvider.createExtendedFor(Key.class,"AREA_EFFECT_CLOUD_REAPPLICATION_DELAY");

    /**
     * Represents the {@link Key} for the duration in ticks after which an
     * {@link AreaEffectCloud} will begin to apply its effect to entities.
     *
     * @see AreaEffectCloudData#waitTime()
     */
    public static final Key<BoundedValue<Integer>> AREA_EFFECT_CLOUD_WAIT_TIME = DummyObjectProvider.createExtendedFor(Key.class,"AREA_EFFECT_CLOUD_WAIT_TIME");

    /**
     * Represents the {@link Key} for whether an {@link ArmorStand}'s arms are
     * visible.
     *
     * @see ArmorStandData#arms()
     */
    public static final Key<Value<Boolean>> ARMOR_STAND_HAS_ARMS = DummyObjectProvider.createExtendedFor(Key.class,"ARMOR_STAND_HAS_ARMS");

    /**
     * Represents the {@link Key} for whether an {@link ArmorStand} has a
     * visible base plate.
     *
     * @see ArmorStandData#basePlate()
     */
    public static final Key<Value<Boolean>> ARMOR_STAND_HAS_BASE_PLATE = DummyObjectProvider.createExtendedFor(Key.class,"ARMOR_STAND_HAS_BASE_PLATE");

    /**
     * Represents the {@link Key} for whether an {@link ArmorStand} is small.
     *
     * @see ArmorStandData#small()
     */
    public static final Key<Value<Boolean>> ARMOR_STAND_IS_SMALL = DummyObjectProvider.createExtendedFor(Key.class,"ARMOR_STAND_IS_SMALL");

    /**
     * Represents the {@link Key} for whether an {@link ArmorStand} has a
     * significantly smaller collision box in order to act as a marker.
     *
     * @see ArmorStandData#marker()
     */
    public static final Key<Value<Boolean>> ARMOR_STAND_MARKER = DummyObjectProvider.createExtendedFor(Key.class,"ARMOR_STAND_MARKER");

    /**
     * Represents the {@link Key} for the type of {@link Art} shown by
     * (usually) a {@link Painting}.
     *
     * @see ArtData
     */
    public static final Key<Value<Art>> ART = DummyObjectProvider.createExtendedFor(Key.class,"ART");

    /**
     * Represents the {@link Key} for representing whether a {@link BlockState}
     * is "attached" to another block.
     *
     * @see AttachedData#attached()
     */
    public static final Key<Value<Boolean>> ATTACHED = DummyObjectProvider.createExtendedFor(Key.class,"ATTACHED");

    /**
     * Represents the {@link Key} for the attachment {@link Surface}
     * of a button or lever.
     *
     * @see SurfaceAttachmentData
     */
    public static final Key<Value<Surface>> ATTACHMENT_SURFACE = DummyObjectProvider.createExtendedFor(Key.class,"ATTACHMENT_SURFACE");

    /**
     * Represents the {@link Key} for the damage dealt by a
     * {@link DamagingProjectile}, e.g. an {@link Arrow}.
     *
     * @see DamagingData#damage()
     */
    public static final Key<BoundedValue<Double>> ATTACK_DAMAGE = DummyObjectProvider.createExtendedFor(Key.class,"ATTACK_DAMAGE");

    /**
     * Represents the {@link Key} for representing the {@link Axis} direction
     * of a {@link BlockState}.
     *
     * @see AxisData
     */
    public static final Key<Value<Axis>> AXIS = DummyObjectProvider.createExtendedFor(Key.class,"AXIS");

    /**
     * Represents the {@link Key} for a {@link Banner}'s base {@link DyeColor}.
     *
     * @see BannerData#baseColor()
     */
    public static final Key<Value<DyeColor>> BANNER_BASE_COLOR = DummyObjectProvider.createExtendedFor(Key.class,"BANNER_BASE_COLOR");

    /**
     * Represents the {@link Key} for a {@link Banner}'s patterns.
     *
     * @see BannerData#patternsList()
     */
    public static final Key<ListValue<PatternLayer>> BANNER_PATTERNS = DummyObjectProvider.createExtendedFor(Key.class,"BANNER_PATTERNS");

    /**
     * Represents the {@link Key} for the width of the physical form of an
     * {@link Entity}.
     *
     * <p>Together with {@link #HEIGHT} this defines the size of an
     * {@link Entity}.</p>
     *
     * @see EntitySizeData#base()
     */
    public static final Key<BoundedValue<Float>> BASE_SIZE = DummyObjectProvider.createExtendedFor(Key.class,"BASE_SIZE");

    /**
     * Represents the {@link Key} for the base vehicle a passenger is riding
     * at the moment. This may be different from {@link Keys#VEHICLE} as the
     * vehicle an {@link Entity} is riding may itself be the passenger of
     * another vehicle.
     *
     * @see VehicleData#baseVehicle()
     */
    public static final Key<Value<EntitySnapshot>> BASE_VEHICLE = DummyObjectProvider.createExtendedFor(Key.class,"BASE_VEHICLE");

    /**
     * Represents the {@link Key} for a {@link Beacon}'s primary effect.
     *
     * @see BeaconData#primaryEffect()
     */
    public static final Key<OptionalValue<PotionEffectType>> BEACON_PRIMARY_EFFECT = DummyObjectProvider.createExtendedFor(Key.class,"BEACON_PRIMARY_EFFECT");

    /**
     * Represents the {@link Key} for a {@link Beacon}'s secondary effect.
     *
     * @see BeaconData#secondaryEffect()
     */
    public static final Key<OptionalValue<PotionEffectType>> BEACON_SECONDARY_EFFECT = DummyObjectProvider.createExtendedFor(Key.class,"BEACON_SECONDARY_EFFECT");

    /**
     * Represents the {@link Key} for the pore sides
     * of a {@link BlockTypes#BROWN_MUSHROOM_BLOCK} or
     * {@link BlockTypes#RED_MUSHROOM_BLOCK} {@link BlockState}.
     *
     * @see BigMushroomPoresData#sides()
     */
    public static final Key<SetValue<Direction>> BIG_MUSHROOM_PORES = DummyObjectProvider.createExtendedFor(Key.class,"BIG_MUSHROOM_PORES");

    /**
     * Represents the {@link Key} for the {@link Direction#DOWN}
     * pores side of a {@link BlockTypes#BROWN_MUSHROOM_BLOCK} or
     * {@link BlockTypes#RED_MUSHROOM_BLOCK} {@link BlockState}.
     *
     * @see BigMushroomPoresData#down()
     */
    public static final Key<Value<Boolean>> BIG_MUSHROOM_PORES_DOWN = DummyObjectProvider.createExtendedFor(Key.class,"BIG_MUSHROOM_PORES_DOWN");

    /**
     * Represents the {@link Key} for the {@link Direction#EAST}
     * pores side of a {@link BlockTypes#BROWN_MUSHROOM_BLOCK} or
     * {@link BlockTypes#RED_MUSHROOM_BLOCK} {@link BlockState}.
     *
     * @see BigMushroomPoresData#east()
     */
    public static final Key<Value<Boolean>> BIG_MUSHROOM_PORES_EAST = DummyObjectProvider.createExtendedFor(Key.class,"BIG_MUSHROOM_PORES_EAST");

    /**
     * Represents the {@link Key} for the {@link Direction#NORTH}
     * pores side of a {@link BlockTypes#BROWN_MUSHROOM_BLOCK} or
     * {@link BlockTypes#RED_MUSHROOM_BLOCK} {@link BlockState}.
     *
     * @see BigMushroomPoresData#north()
     */
    public static final Key<Value<Boolean>> BIG_MUSHROOM_PORES_NORTH = DummyObjectProvider.createExtendedFor(Key.class,"BIG_MUSHROOM_PORES_NORTH");

    /**
     * Represents the {@link Key} for the {@link Direction#SOUTH}
     * pores side of a {@link BlockTypes#BROWN_MUSHROOM_BLOCK} or
     * {@link BlockTypes#RED_MUSHROOM_BLOCK} {@link BlockState}.
     *
     * @see BigMushroomPoresData#south()
     */
    public static final Key<Value<Boolean>> BIG_MUSHROOM_PORES_SOUTH = DummyObjectProvider.createExtendedFor(Key.class,"BIG_MUSHROOM_PORES_SOUTH");

    /**
     * Represents the {@link Key} for the {@link Direction#UP}
     * pores side of a {@link BlockTypes#BROWN_MUSHROOM_BLOCK} or
     * {@link BlockTypes#RED_MUSHROOM_BLOCK} {@link BlockState}.
     *
     * @see BigMushroomPoresData#up()
     */
    public static final Key<Value<Boolean>> BIG_MUSHROOM_PORES_UP = DummyObjectProvider.createExtendedFor(Key.class,"BIG_MUSHROOM_PORES_UP");

    /**
     * Represents the {@link Key} for the {@link Direction#WEST}
     * pores side of a {@link BlockTypes#BROWN_MUSHROOM_BLOCK} or
     * {@link BlockTypes#RED_MUSHROOM_BLOCK} {@link BlockState}.
     *
     * @see BigMushroomPoresData#west()
     */
    public static final Key<Value<Boolean>> BIG_MUSHROOM_PORES_WEST = DummyObjectProvider.createExtendedFor(Key.class,"BIG_MUSHROOM_PORES_WEST");

    /**
     * Represents the {@link Key} for the rotation of specific body parts.
     *
     * <p>This value provides a mapping, effectively combining the data
     * referenced by {@link #HEAD_ROTATION}, {@link #CHEST_ROTATION},
     * {@link #RIGHT_ARM_ROTATION}, {@link #LEFT_ARM_ROTATION},
     * {@link #RIGHT_LEG_ROTATION}, and {@link #LEFT_LEG_ROTATION}.</p>
     *
     * @see BodyPartRotationalData#partRotation()
     */
    public static final Key<MapValue<BodyPart, Vector3d>> BODY_ROTATIONS = DummyObjectProvider.createExtendedFor(Key.class,"BODY_ROTATIONS");

    /**
     * Represents the {@link Key} for the author of a
     * {@link ItemTypes#WRITTEN_BOOK}.
     *
     * @see AuthorData#author()
     */
    public static final Key<Value<Text>> BOOK_AUTHOR = DummyObjectProvider.createExtendedFor(Key.class,"BOOK_AUTHOR");

    /**
     * Represents the {@link Key} for the content of a
     * {@link ItemTypes#WRITTEN_BOOK}.
     *
     * <p>Use {@link Keys#PLAIN_BOOK_PAGES} if you wish to inspect the contents
     * of a {@link ItemTypes#WRITABLE_BOOK}.</p>
     *
     * @see PagedData#pages()
     */
    public static final Key<ListValue<Text>> BOOK_PAGES = DummyObjectProvider.createExtendedFor(Key.class,"BOOK_PAGES");

    /**
     * Represents the {@link Key} for the {@link BlockType}s able to be broken
     * by an item.
     *
     * @see BreakableData#breakable()
     */
    public static final Key<SetValue<BlockType>> BREAKABLE_BLOCK_TYPES = DummyObjectProvider.createExtendedFor(Key.class,"BREAKABLE_BLOCK_TYPES");

    /**
     * Represents the {@link Key} for whether an {@link Entity} can breed.
     *
     * @see BreedableData#breedable()
     */
    public static final Key<Value<Boolean>> CAN_BREED = DummyObjectProvider.createExtendedFor(Key.class,"CAN_BREED");

    /**
     * Represents the {@link Key} for whether a {@link FallingBlock} can drop
     * as an item.
     *
     * @see FallingBlockData#canDropAsItem()
     */
    public static final Key<Value<Boolean>> CAN_DROP_AS_ITEM = DummyObjectProvider.createExtendedFor(Key.class,"CAN_DROP_AS_ITEM");

    /**
     * Represents the {@link Key} for whether a {@link Humanoid} can fly.
     *
     * <p>For a {@link Player} this means he is able to toggle flight mode by
     * double-tapping his jump button.</p>
     *
     * @see FlyingAbilityData#canFly()
     */
    public static final Key<Value<Boolean>> CAN_FLY = DummyObjectProvider.createExtendedFor(Key.class,"CAN_FLY");

    /**
     * Represents the {@link Key} for whether a {@link Living} entity may
     * change blocks. This mostly applies to {@link Enderman} or
     * {@link Creeper}s, but also to some projectiles like {@link Fireball}s.
     *
     * @see GriefingData#canGrief()
     */
    public static final Key<Value<Boolean>> CAN_GRIEF = DummyObjectProvider.createExtendedFor(Key.class,"CAN_GRIEF");

    /**
     * Represents the {@link Key} for whether a {@link FallingBlock} will place
     * itself upon landing.
     *
     * @see FallingBlockData#canPlaceAsBlock()
     */
    public static final Key<Value<Boolean>> CAN_PLACE_AS_BLOCK = DummyObjectProvider.createExtendedFor(Key.class,"CAN_PLACE_AS_BLOCK");

    /**
     * Represents the {@link Key} for the {@link Villager}'s {@link Career}.
     *
     * @see CareerData
     */
    public static final Key<Value<Career>> CAREER = DummyObjectProvider.createExtendedFor(Key.class,"CAREER");

    /**
     * Represents the {@link Key} for the type of a {@link Cat}.
     *
     * @see CatData#type()
     */
    public static final Key<Value<CatType>> CAT_TYPE = DummyObjectProvider.createExtendedFor(Key.class,"CAT_TYPE");

    /**
     * Represents the {@link Key} for the attachment of a {@link BlockTypes#CHEST}
     * or {@link BlockTypes#TRAPPED_CHEST}.
     *
     * @see ChestAttachmentData
     */
    public static final Key<Value<ChestAttachmentType>> CHEST_ATTACHMENT = DummyObjectProvider.createExtendedFor(Key.class,"CHEST_ATTACHMENT");

    /**
     * Represents the {@link Key} for the rotation of the
     * {@link BodyParts#CHEST}.
     *
     * @see BodyPartRotationalData#bodyRotation()
     */
    public static final Key<Value<Vector3d>> CHEST_ROTATION = DummyObjectProvider.createExtendedFor(Key.class,"CHEST_ROTATION");

    /**
     * Represents the {@link Key} for the {@link Color} of an
     * {@link ItemStack}.
     *
     * @see ColoredData#color()
     */
    public static final Key<Value<Color>> COLOR = DummyObjectProvider.createExtendedFor(Key.class,"COLOR");

    /**
     * Represents a key for the stored command, mostly related to
     * {@link CommandBlock}s and {@link CommandBlockMinecart}s.
     *
     * @see CommandData#storedCommand()
     */
    public static final Key<Value<String>> COMMAND = DummyObjectProvider.createExtendedFor(Key.class,"COMMAND");

    /**
     * Represents the {@link Key} for representing the {@link ComparatorType}
     * of a {@link BlockState}.
     *
     * @see ComparatorData
     */
    public static final Key<Value<ComparatorType>> COMPARATOR_TYPE = DummyObjectProvider.createExtendedFor(Key.class,"COMPARATOR_TYPE");

    /**
     * Represents the {@link Key} for representing the connected directions
     * of a {@link BlockState}.
     *
     * @see ConnectedDirectionData#connectedDirections()
     */
    public static final Key<SetValue<Direction>> CONNECTED_DIRECTIONS = DummyObjectProvider.createExtendedFor(Key.class,"CONNECTED_DIRECTIONS");

    /**
     * Represents the {@link Key} for representing the "connected to the east"
     * of a {@link BlockState}.
     *
     * @see ConnectedDirectionData#connectedEast()
     */
    public static final Key<Value<Boolean>> CONNECTED_EAST = DummyObjectProvider.createExtendedFor(Key.class,"CONNECTED_EAST");

    /**
     * Represents the {@link Key} for representing the "connected to the north"
     * state of a {@link BlockState}.
     *
     * @see ConnectedDirectionData#connectedNorth()
     */
    public static final Key<Value<Boolean>> CONNECTED_NORTH = DummyObjectProvider.createExtendedFor(Key.class,"CONNECTED_NORTH");

    /**
     * Represents the {@link Key} for representing the "connected to the south"
     * state of a {@link BlockState}.
     *
     * @see ConnectedDirectionData#connectedSouth()
     */
    public static final Key<Value<Boolean>> CONNECTED_SOUTH = DummyObjectProvider.createExtendedFor(Key.class,"CONNECTED_SOUTH");

    /**
     * Represents the {@link Key} for representing the "connected to the west"
     * state of a {@link BlockState}.
     *
     * @see ConnectedDirectionData#connectedWest()
     */
    public static final Key<Value<Boolean>> CONNECTED_WEST = DummyObjectProvider.createExtendedFor(Key.class,"CONNECTED_WEST");

    /**
     * Represents the {@link Key} for the amount of experience points stored
     * by an {@link ExperienceOrb}.
     *
     * @see ExpOrbData#experience()
     */
    public static final Key<BoundedValue<Integer>> CONTAINED_EXPERIENCE = DummyObjectProvider.createExtendedFor(Key.class,"CONTAINED_EXPERIENCE");

    /**
     * Represents the {@link Key} for the amount of ticks a {@link Hopper} has
     * to cool down before transferring the next item.
     *
     * @see CooldownData#cooldown()
     */
    public static final Key<BoundedValue<Integer>> COOLDOWN = DummyObjectProvider.createExtendedFor(Key.class,"COOLDOWN");

    /**
     * Represents the {@link Key} for whether a {@link Creeper} is charged.
     *
     * @see ChargedData#charged()
     */
    public static final Key<Value<Boolean>> CREEPER_CHARGED = DummyObjectProvider.createExtendedFor(Key.class,"CREEPER_CHARGED");

    /**
     * Represents the {@link Key} for whether the next attack of an
     * {@link Entity} will be a critical hit.
     *
     * @see CriticalHitData#criticalHit()
     */
    public static final Key<Value<Boolean>> CRITICAL_HIT = DummyObjectProvider.createExtendedFor(Key.class,"CRITICAL_HIT");

    /**
     * Represents the {@link Key} for whether a custom name is visible on an
     * {@link Entity}.
     *
     * @see CustomNameVisibleData#customNameVisible()
     */
    public static final Key<Value<Boolean>> CUSTOM_NAME_VISIBLE = DummyObjectProvider.createExtendedFor(Key.class,"CUSTOM_NAME_VISIBLE");

    /**
     * Represents the {@link Key} for the damage dealt towards entities of a
     * specific {@link EntityType}. Types not present in this mapping will be
     * dealt damage to according to {@link #ATTACK_DAMAGE}.
     *
     * @see DamagingData#damageForEntity()
     */
    public static final Key<MapValue<EntityType, Double>> DAMAGE_ENTITY_MAP = DummyObjectProvider.createExtendedFor(Key.class,"DAMAGE_ENTITY_MAP");

    /**
     * Represents the {@link Key} for representing at which distance a {@link BlockState}
     * will decay. This usually applies to leaves, for example {@link BlockTypes#OAK_LEAVES}.
     *
     * @see DecayableData#distance()
     */
    public static final Key<BoundedValue<Integer>> DECAY_DISTANCE = DummyObjectProvider.createExtendedFor(Key.class,"DECAY_DISTANCE");

    /**
     * Represents the {@link Key} for the delay on a redstone repeater.
     *
     * @see DelayableData#delay()
     */
    public static final Key<BoundedValue<Integer>> DELAY = DummyObjectProvider.createExtendedFor(Key.class,"DELAY");

    /**
     * Represents the {@link Key} for representing the despawn delay
     * of an {@link Item}.
     *
     * @see DespawnDelayData#delay()
     */
    public static final Key<BoundedValue<Integer>> DESPAWN_DELAY = DummyObjectProvider.createExtendedFor(Key.class,"DESPAWN_DELAY");

    /**
     * Represents the {@link Key} for representing the {@link Direction}
     * of a {@link BlockState}.
     *
     * @see DirectionalData#direction()
     */
    public static final Key<Value<Direction>> DIRECTION = DummyObjectProvider.createExtendedFor(Key.class,"DIRECTION");

    /**
     * Represents the {@link Key} for representing the "disarmed" state
     * of a {@link BlockState}. This usually applies to
     * {@link BlockTypes#TRIPWIRE}s and {@link BlockTypes#TRIPWIRE_HOOK}s.
     *
     * @see DisarmedData#disarmed()
     */
    public static final Key<Value<Boolean>> DISARMED = DummyObjectProvider.createExtendedFor(Key.class,"DISARMED");

    /**
     * Represents the {@link Key} for the display name of an {@link Entity},
     * {@link ItemStack} or {@link TileEntity}.
     *
     * <p>On a {@link ItemTypes#WRITTEN_BOOK} item this will also set the title
     * of the book.</p>
     *
     * @see DisplayNameData#displayName()
     */
    public static final Key<Value<Text>> DISPLAY_NAME = DummyObjectProvider.createExtendedFor(Key.class,"DISPLAY_NAME");

    /**
     * Represents the {@link Key} for representing the dominant {@link HandPreference}
     * of a {@link Living} entity.
     *
     * <p><em>NOTE:</em> Does not apply to {@link Player}s as their
     * {@link HandPreference} can not be changed server-side.
     * See {@link Properties#DOMINANT_HAND}.</p>
     *
     * @see DominantHandData#dominantHand()
     */
    public static final Key<Value<HandPreference>> DOMINANT_HAND = DummyObjectProvider.createExtendedFor(Key.class,"DOMINANT_HAND");

    /**
     * Represents the {@link Key} for the color of a dyeable block, item or
     * entity.
     *
     * @see DyeableData
     */
    public static final Key<Value<DyeColor>> DYE_COLOR = DummyObjectProvider.createExtendedFor(Key.class,"DYE_COLOR");

    /**
     * Represents the {@link Key} for representing the age of
     * an {@link EndGateway}.
     *
     * @see EndGatewayData#age()
     */
    public static final Key<Value<Long>> END_GATEWAY_AGE = DummyObjectProvider.createExtendedFor(Key.class,"END_GATEWAY_AGE");

    /**
     * Represents the {@link Key} for representing the teleport cooldown of
     * an {@link EndGateway}.
     *
     * @see EndGatewayData#teleportCooldown()
     */
    public static final Key<Value<Integer>> END_GATEWAY_TELEPORT_COOLDOWN = DummyObjectProvider.createExtendedFor(Key.class,"END_GATEWAY_TELEPORT_COOLDOWN");

    /**
     * Represents the {@link Key} for representing if the exact teleport location
     * should be used with a {@link EndGateway}.
     *
     * @see EndGatewayData#exactTeleport()
     */
    public static final Key<Value<Boolean>> EXACT_TELEPORT = DummyObjectProvider.createExtendedFor(Key.class,"EXACT_TELEPORT");

    /**
     * Represents the {@link Key} for the current level of exhaustion of a
     * {@link Humanoid}.
     *
     * <p>Exhaustion will <em>decrease</em> on activities like walking, running
     * or jumping. Once it reaches 0, the {@link #SATURATION} will decrease and
     * the exhaustion level will be reset to its maximum.</p>
     *
     * @see FoodData#exhaustion()
     */
    public static final Key<BoundedValue<Double>> EXHAUSTION = DummyObjectProvider.createExtendedFor(Key.class,"EXHAUSTION");

    /**
     * Represents the {@link Key} for representing the exit
     * portal {@link Vector3i location} of an {@link EndGateway}.
     *
     * @see EndGatewayData#exitPosition()
     */
    public static final Key<Value<Vector3i>> EXIT_POSITION = DummyObjectProvider.createExtendedFor(Key.class,"EXIT_PORTAL");

    /**
     * Represents the {@link Key} for the total experience a {@link Player}
     * requires to advance from his current level to the next one.
     *
     * @see ExperienceHolderData#getExperienceBetweenLevels()
     */
    public static final Key<BoundedValue<Integer>> EXPERIENCE_FROM_START_OF_LEVEL = DummyObjectProvider.createExtendedFor(Key.class,"EXPERIENCE_FROM_START_OF_LEVEL");

    /**
     * Represents the {@link Key} for the current level a {@link Player} has.
     *
     * @see ExperienceHolderData#level()
     */
    public static final Key<BoundedValue<Integer>> EXPERIENCE_LEVEL = DummyObjectProvider.createExtendedFor(Key.class,"EXPERIENCE_LEVEL");

    /**
     * Represents the {@link Key} for the amount of experience a {@link Player}
     * has collected towards the next level.
     *
     * @see ExperienceHolderData#experienceSinceLevel()
     */
    public static final Key<BoundedValue<Integer>> EXPERIENCE_SINCE_LEVEL = DummyObjectProvider.createExtendedFor(Key.class,"EXPERIENCE_SINCE_LEVEL");

    /**
     * Represents the {@link Key} for how long an entity or
     * {@link Weather} will last before expiring.
     *
     * <p>Usually applies to {@link Weather}, {@link Endermite}s or {@link Item}s.</p>
     *
     * @see ExpirableData#expireDuration()
     */
    public static final Key<Value<Duration>> EXPIRATION_DURATION = DummyObjectProvider.createExtendedFor(Key.class,"EXPIRATION_DURATION");

    /**
     * Represents the {@link Key} for the radius of the {@link Explosion} to
     * be created by detonating an {@link Explosive}.
     *
     * <p>May be absent if the explosion radius is unknown because it is either
     * determined randomly at the time of the explosion or computed from the
     * context in which the {@link Explosive} explodes.</p>
     *
     * @see ExplosionRadiusData#explosionRadius()
     */
    public static final Key<OptionalValue<Integer>> EXPLOSION_RADIUS = DummyObjectProvider.createExtendedFor(Key.class,"EXPLOSION_RADIUS");

    /**
     * Represents the {@link Key} for representing whether a {@link Piston} is
     * currently extended.
     *
     * @see ExtendedData#extended()
     */
    public static final Key<Value<Boolean>> EXTENDED = DummyObjectProvider.createExtendedFor(Key.class,"EXTENDED");

    /**
     * Represents the {@link Key} for whether a {@link FallingBlock} will
     * damage an {@link Entity} it lands on.
     *
     * @see FallingBlockData#canHurtEntities()
     */
    public static final Key<Value<Boolean>> FALLING_BLOCK_CAN_HURT_ENTITIES = DummyObjectProvider.createExtendedFor(Key.class,"FALLING_BLOCK_CAN_HURT_ENTITIES");

    /**
     * Represents the {@link Key} for the {@link BlockState} of a
     * {@link FallingBlock}.
     *
     * @see FallingBlockData#blockState()
     */
    public static final Key<Value<BlockState>> FALLING_BLOCK_STATE = DummyObjectProvider.createExtendedFor(Key.class,"FALLING_BLOCK_STATE");

    /**
     * Represents the {@link Key} for how much damage a {@link FallingBlock}
     * deals to {@link Living} entities it hits.
     *
     * <p>This damage is capped by {@link #MAX_FALL_DAMAGE}.</p>
     *
     * @see FallingBlockData#fallDamagePerBlock()
     */
    public static final Key<BoundedValue<Double>> FALL_DAMAGE_PER_BLOCK = DummyObjectProvider.createExtendedFor(Key.class,"FALL_DAMAGE_PER_BLOCK");

    /**
     * Represents the {@link Key} for representing the distance an entity has
     * fallen.
     *
     * @see FallDistanceData#fallDistance()
     */
    public static final Key<BoundedValue<Float>> FALL_DISTANCE = DummyObjectProvider.createExtendedFor(Key.class,"FALL_DISTANCE");

    /**
     * Represents the {@link Key} for the amount of ticks a
     * {@link FallingBlock} has been falling for.
     *
     * @see FallingBlockData#fallTime()
     */
    public static final Key<Value<Integer>> FALL_TIME = DummyObjectProvider.createExtendedFor(Key.class,"FALL_TIME");

    /**
     * Represents the {@link Key} for representing the "filled" state
     * of a {@link BlockState}.
     *
     * <p>Usually applies to {@link BlockTypes#END_PORTAL_FRAME}s.</p>
     *
     * @see FilledData#filled()
     */
    public static final Key<Value<Boolean>> FILLED = DummyObjectProvider.createExtendedFor(Key.class,"FILLED");

    /**
     * Represents the {@link Key} for the {@link FireworkEffect}s of a
     * {@link ItemTypes#FIREWORK_STAR}, {@link ItemTypes#FIREWORK_ROCKET} or a
     * {@link FireworkRocket}.
     *
     * @see FireworkEffectData#effects()
     */
    public static final Key<ListValue<FireworkEffect>> FIREWORK_EFFECTS = DummyObjectProvider.createExtendedFor(Key.class,"FIREWORK_EFFECTS");

    /**
     * Represents the {@link Key} for the flight duration of a firework.
     *
     * <p>The duration is tiered and will stay partially random. A rocket will
     * fly for roughly {@code modifier * 10 + (random number from 0 to 13)}
     * ticks in Vanilla Minecraft.</p>
     *
     * @see FireworkRocketData#flightModifier()
     */
    public static final Key<BoundedValue<Integer>> FIREWORK_FLIGHT_MODIFIER = DummyObjectProvider.createExtendedFor(Key.class,"FIREWORK_FLIGHT_MODIFIER");

    /**
     * Represents the {@link Key} for the delay in ticks until the
     * {@link Entity} will be damaged by the fire.
     *
     * @see IgniteableData#fireDelay()
     */
    public static final Key<BoundedValue<Integer>> FIRE_DAMAGE_DELAY = DummyObjectProvider.createExtendedFor(Key.class,"FIRE_DAMAGE_DELAY");

    /**
     * Represents the {@link Key} for the amount of ticks an
     * {@link Entity} is still burning.
     *
     * @see IgniteableData#fireTicks()
     */
    public static final Key<BoundedValue<Integer>> FIRE_TICKS = DummyObjectProvider.createExtendedFor(Key.class,"FIRE_TICKS");

    /**
     * Represents the {@link Key} for the time a {@link Player} first played
     * on the Server.
     *
     * @see JoinData#firstPlayed()
     */
    public static final Key<Value<Instant>> FIRST_DATE_PLAYED = DummyObjectProvider.createExtendedFor(Key.class,"FIRST_DATE_PLAYED");

    /**
     * Represents the {@link Key} for representing the
     * {@link FluidStackSnapshot} contained within an item container. Item
     * containers may include buckets and other mod added items.
     *
     * @see FluidItemData#fluid()
     */
    public static final Key<Value<FluidStackSnapshot>> FLUID_ITEM_STACK = DummyObjectProvider.createExtendedFor(Key.class,"FLUID_ITEM_STACK");

    /**
     * Represents the {@link Key} for representing the "fluid level" state
     * of a {@link BlockState}.
     *
     * @see FluidLevelData#level()
     */
    public static final Key<BoundedValue<Integer>> FLUID_LEVEL = DummyObjectProvider.createExtendedFor(Key.class,"FLUID_LEVEL");

    /**
     * Represents the {@link Key} for representing the directional tank
     * information.
     *
     * @see FluidTankData#fluids()
     */
    public static final Key<MapValue<Direction, List<FluidStackSnapshot>>> FLUID_TANK_CONTENTS = DummyObjectProvider.createExtendedFor(Key.class,"FLUID_TANK_CONTENTS");

    /**
     * Represents the {@link Key} for the speed at which an entity flies.
     *
     * @see MovementSpeedData#flySpeed()
     */
    public static final Key<Value<Double>> FLYING_SPEED = DummyObjectProvider.createExtendedFor(Key.class,"FLYING_SPEED");

    /**
     * Represents the {@link Key} for the food level of a {@link Humanoid}.
     *
     * @see FoodData#foodLevel()
     */
    public static final Key<BoundedValue<Integer>> FOOD_LEVEL = DummyObjectProvider.createExtendedFor(Key.class,"FOOD_LEVEL");

    /**
     * Represents the {@link Key} for the type of a {@link Fox}.
     *
     * @see FoxData#type()
     */
    public static final Key<Value<FoxType>> FOX_TYPE = DummyObjectProvider.createExtendedFor(Key.class,"FOX_TYPE");

    /**
     * Represents the {@link Key} for the time a {@link FusedExplosive}'s fuse
     * will burn before the explosion.
     *
     * @see FuseData#fuseDuration()
     */
    public static final Key<Value<Integer>> FUSE_DURATION = DummyObjectProvider.createExtendedFor(Key.class,"FUSE_DURATION");

    /**
     * Represents the {@link Key} for the {@link GameMode} a {@link Humanoid}
     * has.
     *
     * @see GameModeData
     */
    public static final Key<Value<GameMode>> GAME_MODE = DummyObjectProvider.createExtendedFor(Key.class,"GAME_MODE");

    /**
     * Represents the {@link Key} for the generation of a
     * {@link ItemTypes#WRITTEN_BOOK}. Depending on the book's generation
     * it may be impossible to copy it.
     *
     * @see GenerationData#generation()
     */
    public static final Key<BoundedValue<Integer>> GENERATION = DummyObjectProvider.createExtendedFor(Key.class,"GENERATION");

    /**
     * Represents the {@link Key} for representing whether an entity has a
     * glowing outline.
     *
     * @see GlowingData#glowing()
     */
    public static final Key<Value<Boolean>> GLOWING = DummyObjectProvider.createExtendedFor(Key.class,"GLOWING");

    /**
     * Represents the {@link Key} for representing the "growth stage" state
     * of a {@link BlockState}.
     *
     * @see GrowthData#growthStage()
     */
    public static final Key<BoundedValue<Integer>> GROWTH_STAGE = DummyObjectProvider.createExtendedFor(Key.class,"GROWTH_STAGE");

    /**
     * Represents the {@link Key} for whether an {@link Entity} is affected by
     * gravity.
     *
     * @see GravityData#gravity()
     */
    public static final Key<Value<Boolean>> HAS_GRAVITY = DummyObjectProvider.createExtendedFor(Key.class,"HAS_GRAVITY");

    /**
     * Represents the {@link Key} for the direction an entities head is
     * rotated to.
     *
     * @see BodyPartRotationalData#headDirection()
     */
    public static final Key<Value<Vector3d>> HEAD_ROTATION = DummyObjectProvider.createExtendedFor(Key.class,"HEAD_ROTATION");

    /**
     * Represents the {@link Key} for a {@link Living}'s current health.
     *
     * @see HealthData#health()
     */
    public static final Key<BoundedValue<Double>> HEALTH = DummyObjectProvider.createExtendedFor(Key.class,"HEALTH");

    /**
     * Represents the {@link Key} for how much health a half-heart on a
     * {@link Player}'s GUI will stand for.
     *
     * @see HealthScalingData#healthScale()
     */
    public static final Key<BoundedValue<Double>> HEALTH_SCALE = DummyObjectProvider.createExtendedFor(Key.class,"HEALTH_SCALE");

    /**
     * Represents the {@link Key} for the height of the physical form of an
     * {@link Entity}.
     *
     * <p>Together with {@link #BASE_SIZE} this defines the size of an
     * {@link Entity}.</p>
     *
     * @see EntitySizeData#height()
     */
    public static final Key<BoundedValue<Float>> HEIGHT = DummyObjectProvider.createExtendedFor(Key.class,"HEIGHT");

    /**
     * Represents the {@link Key} for representing the "attributes hidden"
     * state of an {@link ItemStack}.
     *
     * @see HideData#hideAttributes()
     */
    public static final Key<Value<Boolean>> HIDE_ATTRIBUTES = DummyObjectProvider.createExtendedFor(Key.class,"HIDE_ATTRIBUTES");

    /**
     * Represents the {@link Key} for representing the "can destroy hidden"
     * state of an {@link ItemStack}.
     *
     * @see HideData#hideCanDestroy()
     */
    public static final Key<Value<Boolean>> HIDE_CAN_DESTROY = DummyObjectProvider.createExtendedFor(Key.class,"HIDE_CAN_DESTROY");

    /**
     * Represents the {@link Key} for representing the "can place hidden"
     * state of an {@link ItemStack}.
     *
     * @see HideData#hideCanPlace()
     */
    public static final Key<Value<Boolean>> HIDE_CAN_PLACE = DummyObjectProvider.createExtendedFor(Key.class,"HIDE_CAN_PLACE");

    /**
     * Represents the {@link Key} for representing the "enchantments hidden"
     * state of an {@link ItemStack}.
     *
     * @see HideData#hideEnchantments()
     */
    public static final Key<Value<Boolean>> HIDE_ENCHANTMENTS = DummyObjectProvider.createExtendedFor(Key.class,"HIDE_ENCHANTMENTS");

    /**
     * Represents the {@link Key} for representing the "miscellaneous hidden"
     * state of an {@link ItemStack}.
     *
     * @see HideData#hideMiscellaneous()
     */
    public static final Key<Value<Boolean>> HIDE_MISCELLANEOUS = DummyObjectProvider.createExtendedFor(Key.class,"HIDE_MISCELLANEOUS");

    /**
     * Represents the {@link Key} for representing the "unbreakable hidden"
     * state of an {@link ItemStack}.
     *
     * @see HideData#hideUnbreakable()
     */
    public static final Key<Value<Boolean>> HIDE_UNBREAKABLE = DummyObjectProvider.createExtendedFor(Key.class,"HIDE_UNBREAKABLE");

    /**
     * Represents the {@link Key} for representing the {@link Hinge}
     * of a {@link BlockState}.
     *
     * @see HingeData
     */
    public static final Key<Value<Hinge>> HINGE_POSITION = DummyObjectProvider.createExtendedFor(Key.class,"HINGE_POSITION");

    /**
     * Represents the {@link Key} for the color of a {@link Horse}.
     *
     * @see HorseData#color()
     */
    public static final Key<Value<HorseColor>> HORSE_COLOR = DummyObjectProvider.createExtendedFor(Key.class,"HORSE_COLOR");

    /**
     * Represents the {@link Key} for the style of a {@link Horse}.
     *
     * @see HorseData#style()
     */
    public static final Key<Value<HorseStyle>> HORSE_STYLE = DummyObjectProvider.createExtendedFor(Key.class,"HORSE_STYLE");

    /**
     * Represents the {@link Key} for whether an {@link Item} will not despawn
     * for an infinite time.
     *
     * @see DespawnDelayData#infinite()
     */
    public static final Key<Value<Boolean>> INFINITE_DESPAWN_DELAY = DummyObjectProvider.createExtendedFor(Key.class,"INFINITE_DESPAWN_DELAY");

    /**
     * Represents the {@link Key} for representing the "is infinite" state
     * of the pickup delay of an {@link Item}.
     *
     * @see PickupDelayData#infinite()
     */
    public static final Key<Value<Boolean>> INFINITE_PICKUP_DELAY = DummyObjectProvider.createExtendedFor(Key.class,"INFINITE_PICKUP_DELAY");

    /**
     * Represents the {@link Key} for the {@link InstrumentType}
     * of a {@link BlockTypes#NOTE_BLOCK}.
     *
     * @see InstrumentData
     */
    public static final Key<Value<InstrumentType>> INSTRUMENT = DummyObjectProvider.createExtendedFor(Key.class, "INSTRUMENT");

    /**
     * Represents the {@link Key} for the "inverted" state of
     * an {@link BlockTypes#DAYLIGHT_DETECTOR}.
     *
     * @see InvertibleData#inverted()
     */
    public static final Key<Value<Boolean>> INVERTED = DummyObjectProvider.createExtendedFor(Key.class,"INVERTED");

    /**
     * Represents the {@link Key} for representing the "vanish" state
     * of an {@link Entity}. This will only simply render the entity as
     * vanish, but not prevent any entity updates being sent to clients.
     * To fully "vanish" an {@link Entity}, use {@link #VANISH}.
     *
     * @see InvisibilityData#invisible()
     */
    public static final Key<Value<Boolean>> INVISIBLE = DummyObjectProvider.createExtendedFor(Key.class,"INVISIBLE");

    /**
     * Represents the {@link Key} for the amount of ticks an {@link Entity}
     * will remain invulnerable for.
     *
     * @see InvulnerabilityData#invulnerableTicks()
     */
    public static final Key<BoundedValue<Integer>> INVULNERABILITY_TICKS = DummyObjectProvider.createExtendedFor(Key.class,"INVULNERABILITY_TICKS");

    /**
     * Represents the {@link Key} for representing if an {@link Entity}
     * is invulnerable or not.
     *
     * <p>This does not protect from the void, players in creative mode,
     * and manual killing like the /kill command.</p>
     *
     * @see InvulnerabilityData#invulnerable()
     */
    public static final Key<Value<Boolean>> INVULNERABLE = DummyObjectProvider.createExtendedFor(Key.class,"INVULNERABLE");

    /**
     * Represents the {@link Key} for representing the "in-wall" state of
     * fences, for example {@link BlockTypes#OAK_FENCE}.
     *
     * @see InWallData#inWall()
     */
    public static final Key<Value<Boolean>> IN_WALL = DummyObjectProvider.createExtendedFor(Key.class,"IN_WALL");

    /**
     * Represents the {@link Key} for the state whether a {@link Ageable}
     * entity is considered an "adult" and may affect breeding capabilities.
     */
    public static final Key<Value<Boolean>> IS_ADULT = DummyObjectProvider.createExtendedFor(Key.class,"IS_ADULT");

    /**
     * Represents the {@link Key} for whether a {@link Blaze} is currently
     * burning.
     *
     * <p>Unlike {@link #MAX_BURN_TIME}, the burning effect will not damage
     * the burning entity.</p>
     *
     * @see FlammableData#flammable()
     */
    public static final Key<Value<Boolean>> IS_AFLAME = DummyObjectProvider.createExtendedFor(Key.class,"IS_AFLAME");

    /**
     * Represents the {@link Key} for whether a {@link Player} is flying with an
     * {@link ItemTypes#ELYTRA}.
     *
     * @see ElytraFlyingData#elytraFlying()
     */
    public static final Key<Value<Boolean>> IS_ELYTRA_FLYING = DummyObjectProvider.createExtendedFor(Key.class, "IS_ELYTRA_FLYING");

    /**
     * Represents the {@link Key} for whether an {@link Entity} is flying.
     *
     * <p>This key only tells whether an entity is flying at the moment. On a
     * {@link Player} it does not necessarily mean that the player may toggle
     * freely between flying and walking. To check whether a player may switch
     * his flying state, check {@link #CAN_FLY}.</p>
     *
     * @see FlyingData#flying()
     */
    public static final Key<Value<Boolean>> IS_FLYING = DummyObjectProvider.createExtendedFor(Key.class,"IS_FLYING");

    /**
     * Gets the {@link Value} for whether this mob is exhibiting
     * "johnny" behavior.
     *
     * <p>In vanilla this currently only applies to {@link Vindicator}s.</p>
     *
     * @see <a href="https://minecraft.gamepedia.com/Vindicator#Behavior">
     *     The Minecraft Wiki for more information about "johnny" behavior</a>
     * @see JohnnyData#johnny()
     */
    public static final Key<Value<Boolean>> IS_JOHNNY = DummyObjectProvider.createExtendedFor(Key.class, "IS_JOHNNY");

    /**
     * Represents the {@link Key} for whether a {@link Villager} is playing.
     *
     * <p>In Vanilla, this only applies to villagers that are considered
     * "babies" according to {@link #AGE}.</p>
     *
     * @see PlayingData#playing()
     */
    public static final Key<Value<Boolean>> IS_PLAYING = DummyObjectProvider.createExtendedFor(Key.class,"IS_PLAYING");

    /**
     * Represents the {@link Key} for whether an {@link Enderman} is screaming.
     *
     * @see ScreamingData#screaming()
     */
    public static final Key<Value<Boolean>> IS_SCREAMING = DummyObjectProvider.createExtendedFor(Key.class,"IS_SCREAMING");

    /**
     * Represents the {@link Key} for whether a {@link Sheep} is sheared.
     *
     * @see ShearedData#sheared()
     */
    public static final Key<Value<Boolean>> IS_SHEARED = DummyObjectProvider.createExtendedFor(Key.class,"IS_SHEARED");

    /**
     * Represents the {@link Key} for whether an {@link Entity} is silent.
     *
     * <p>A silent entity will not emit sounds or make noises.</p>
     *
     * @see SilentData#silent()
     */
    public static final Key<Value<Boolean>> IS_SILENT = DummyObjectProvider.createExtendedFor(Key.class,"IS_SILENT");

    /**
     * Represents the {@link Key} for whether a {@link Wolf} or {@link Ocelot}
     * is sitting.
     *
     * @see SittingData#sitting()
     */
    public static final Key<Value<Boolean>> IS_SITTING = DummyObjectProvider.createExtendedFor(Key.class,"IS_SITTING");

    /**
     * Represents the {@link Key} for whether a {@link Bat} or {@link Player}
     * is sleeping.
     *
     * <p>If a player is considered sleeping as per this data value, he does
     * not need to be in bed in order for the other players to be able to
     * advance through the night by going to bed.</p>
     *
     * @see SleepingData#sleeping()
     */
    public static final Key<Value<Boolean>> IS_SLEEPING = DummyObjectProvider.createExtendedFor(Key.class,"IS_SLEEPING");

    /**
     * Represents the {@link Key} for whether an {@link Entity} is sneaking.
     *
     * <p>Sneaking entities generally move slower and do not make walking
     * sounds.</p>
     *
     * @see SneakingData#sneaking()
     */
    public static final Key<Value<Boolean>> IS_SNEAKING = DummyObjectProvider.createExtendedFor(Key.class,"IS_SNEAKING");

    /**
     * Represents the {@link Key} for whether an {@link Entity} is sprinting.
     *
     * @see SprintData#sprinting()
     */
    public static final Key<Value<Boolean>> IS_SPRINTING = DummyObjectProvider.createExtendedFor(Key.class,"IS_SPRINTING");

    /**
     * Represents the {@link Key} for whether a {@link Wolf}, a
     * {@link BlockState} of {@link BlockTypes#SPONGE} or an {@link ItemStack}
     * of {@link ItemTypes#SPONGE} is wet.
     *
     * @see WetData#wet()
     */
    public static final Key<Value<Boolean>> IS_WET = DummyObjectProvider.createExtendedFor(Key.class,"IS_WET");

    /**
     * Represents the {@link Key} for the {@link BlockState} represented by
     * an {@link ItemStack}.
     *
     * @see BlockItemData#state()
     */
    public static final Key<Value<BlockState>> ITEM_BLOCKSTATE = DummyObjectProvider.createExtendedFor(Key.class,"ITEM_BLOCKSTATE");

    /**
     * Represents the {@link Key} for the durability of an {@link ItemStack}.
     *
     * @see DurabilityData#durability()
     */
    public static final Key<BoundedValue<Integer>> ITEM_DURABILITY = DummyObjectProvider.createExtendedFor(Key.class,"ITEM_DURABILITY");

    /**
     * Represents the {@link Key} for the enchantments applied to an
     * {@link ItemStack}.
     *
     * <p>This data is usually applicable to all types of armor, weapons and
     * tools. Enchantments that are only stored on an item stack in order to
     * be transferred to another item (like on
     * {@link ItemTypes#ENCHANTED_BOOK}s) use the {@link #STORED_ENCHANTMENTS}
     * key instead.)</p>
     *
     * @see EnchantmentData#enchantments()
     */
    public static final Key<ListValue<Enchantment>> ITEM_ENCHANTMENTS = DummyObjectProvider.createExtendedFor(Key.class,"ITEM_ENCHANTMENTS");

    /**
     * Represents the {@link Key} for the displayed description ("lore") text
     * of an {@link ItemStack}.
     *
     * <p>The lore text is usually displayed when the player hovers his cursor
     * over the stack. For the contents of a book see {@link #BOOK_PAGES}
     * instead.</p>
     *
     * @see LoreData#lore()
     */
    public static final Key<ListValue<Text>> ITEM_LORE = DummyObjectProvider.createExtendedFor(Key.class,"ITEM_LORE");

    /**
     * Represents the {@link Key} for the knockback strength applied by an
     * {@link Arrow}.
     *
     * <p>For the knockback provided by hits with a weapon according to the
     * enchantment of the same name, see {@link #ITEM_ENCHANTMENTS}.</p>
     *
     * @see KnockbackData#knockbackStrength()
     */
    public static final Key<BoundedValue<Integer>> KNOCKBACK_STRENGTH = DummyObjectProvider.createExtendedFor(Key.class,"KNOCKBACK_STRENGTH");

    /**
     * Represents the {@link Key} for the output yielded by the last command of
     * a {@link CommandBlock}.
     *
     * @see CommandData#lastOutput()
     */
    public static final Key<OptionalValue<Text>> LAST_COMMAND_OUTPUT = DummyObjectProvider.createExtendedFor(Key.class,"LAST_COMMAND_OUTPUT");

    /**
     * Represents the {@link Key} for the last time a {@link User} has been
     * playing on the server.
     *
     * @see JoinData#lastPlayed()
     */
    public static final Key<Value<Instant>> LAST_DATE_PLAYED = DummyObjectProvider.createExtendedFor(Key.class,"LAST_DATE_PLAYED");

    /**
     * Represents the {@link Key} for representing the "layer" value of
     * {@link BlockTypes#SNOW} and other possible layered blocks.
     *
     * @see LayeredData#layer()
     */
    public static final Key<BoundedValue<Integer>> LAYER = DummyObjectProvider.createExtendedFor(Key.class,"LAYER");

    /**
     * Represents the {@link Key} for the rotation of an {@link Entity}'s left
     * arm.
     *
     * @see BodyPartRotationalData#leftArmDirection()
     */
    public static final Key<Value<Vector3d>> LEFT_ARM_ROTATION = DummyObjectProvider.createExtendedFor(Key.class,"LEFT_ARM_ROTATION");

    /**
     * Represents the {@link Key} for the rotation of an {@link Entity}'s left
     * leg.
     *
     * @see BodyPartRotationalData#leftLegDirection()
     */
    public static final Key<Value<Vector3d>> LEFT_LEG_ROTATION = DummyObjectProvider.createExtendedFor(Key.class,"LEFT_LEG_ROTATION");

    /**
     * Represents the {@link Key} for the state that something is "lit",
     * for example a {@link BlockTypes#FURNACE} or {@link BlockTypes#REDSTONE_TORCH}.
     *
     * @see LitData#lit()
     */
    public static final Key<Value<Boolean>> LIT = DummyObjectProvider.createExtendedFor(Key.class,"LIT");

    /**
     * Represents the {@link Key} for a {@link Llama}s carrying strength. The higher the strength,
     * the more items it can carry (effectively the size of inventory).
     */
    public static final Key<BoundedValue<Integer>> LLAMA_STRENGTH = DummyObjectProvider.createExtendedFor(Key.class,"LLAMA_STRENGTH");

    /**
     * Represents the {@link Key} for a {@link Llama}'s {@link LlamaVariant}.
     */
    public static final Key<Value<LlamaVariant>> LLAMA_VARIANT = DummyObjectProvider.createExtendedFor(Key.class,"LLAMA_VARIANT");

    /**
     * Represents the {@link Key} for the token used to lock a
     * {@link TileEntityCarrier}.
     *
     * @see LockableData#lockToken()
     */
    public static final Key<Value<String>> LOCK_TOKEN = DummyObjectProvider.createExtendedFor(Key.class,"LOCK_TOKEN");

    /**
     * Represents the {@link Key} for the maximum air supply a {@link Living}
     * may have.
     *
     * <p>For the current amount of air, check {@link #REMAINING_AIR}.</p>
     *
     * @see BreathingData#maxAir()
     */
    public static final Key<BoundedValue<Integer>> MAX_AIR = DummyObjectProvider.createExtendedFor(Key.class,"MAX_AIR");

    /**
     * Represents the {@link Key} for the maximum amount of ticks a
     * {@link Furnace} can burn with the currently used fuel item.
     *
     * @see FurnaceData#maxBurnTime()
     */
    public static final Key<BoundedValue<Integer>> MAX_BURN_TIME = DummyObjectProvider.createExtendedFor(Key.class,"MAX_BURN_TIME");

    /**
     * Represents the {@link Key} for the total time the current
     * {@link ItemStack} in a {@link Furnace} has to be cooked.
     *
     * @see FurnaceData#maxCookTime()
     */
    public static final Key<BoundedValue<Integer>> MAX_COOK_TIME = DummyObjectProvider.createExtendedFor(Key.class,"MAX_COOK_TIME");

    /**
     * Represents the {@link Key} for the maximum damage a {@link FallingBlock}
     * can deal.
     *
     * @see FallingBlockData#maxFallDamage()
     */
    public static final Key<BoundedValue<Double>> MAX_FALL_DAMAGE = DummyObjectProvider.createExtendedFor(Key.class,"MAX_FALL_DAMAGE");

    /**
     * Represents the {@link Key} for the maximum health of a {@link Living}.
     *
     * @see HealthData#maxHealth()
     */
    public static final Key<BoundedValue<Double>> MAX_HEALTH = DummyObjectProvider.createExtendedFor(Key.class,"MAX_HEALTH");

    /**
     * Represents the {@link Key} for representing the "moisture" state of
     * {@link BlockTypes#FARMLAND}.
     *
     * @see MoistureData#moisture()
     */
    public static final Key<BoundedValue<Integer>> MOISTURE = DummyObjectProvider.createExtendedFor(Key.class,"MOISTURE");

    /**
     * Represents the {@link Key} for the pitch of a {@link BlockTypes#NOTE_BLOCK}.
     *
     * @see NoteData#note()
     */
    public static final Key<Value<NotePitch>> NOTE_PITCH = DummyObjectProvider.createExtendedFor(Key.class,"NOTE_PITCH");

    /**
     * Represents the {@link Key} for representing the "occupied" state of
     * beds, for example {@link BlockTypes#WHITE_BED}.
     *
     * @see OccupiedData#occupied()
     */
    public static final Key<Value<Boolean>> OCCUPIED = DummyObjectProvider.createExtendedFor(Key.class,"OCCUPIED");

    /**
     * Represents the {@link Key} for representing a block's offset when inside
     * a {@link Minecart}.
     *
     * @see MinecartBlockData#offset()
     */
    public static final Key<Value<Integer>> OFFSET = DummyObjectProvider.createExtendedFor(Key.class,"OFFSET");

    /**
     * Represents the {@link Key} for representing the "open" state of
     * various door typed blocks.
     *
     * @see OpenData#open()
     */
    public static final Key<Value<Boolean>> OPEN = DummyObjectProvider.createExtendedFor(Key.class,"OPEN");

    /**
     * Represents the {@link ParrotVariant variant} of a {@link Parrot}.
     *
     * @see ParrotData#type()
     */
    public static final Key<Value<ParrotVariant>> PARROT_VARIANT = DummyObjectProvider.createExtendedFor(Key.class,"PARROT_VARIANT");

    /**
     * Represents the {@link Key} for the amount of ticks a {@link Furnace} has
     * already been burning with the current fuel item.
     *
     * <p>Once this value reaches the one of {@link #MAX_BURN_TIME}, the
     * furnace will require more fuel in order to keep burning.</p>
     *
     * @see FurnaceData#passedBurnTime()
     */
    public static final Key<BoundedValue<Integer>> PASSED_BURN_TIME = DummyObjectProvider.createExtendedFor(Key.class,"PASSED_BURN_TIME");

    /**
     * Represents the {@link Key} for the amount of ticks a {@link Furnace} has
     * been cooking the current item for.
     *
     * <p>Once this value reaches the one of {@link #MAX_COOK_TIME}, the
     * item will be finished cooking.</p>
     *
     * @see FurnaceData#passedCookTime()
     */
    public static final Key<BoundedValue<Integer>> PASSED_COOK_TIME = DummyObjectProvider.createExtendedFor(Key.class,"PASSED_COOK_TIME");

    /**
     * Represents the {@link Key} for the entities that act as passengers for
     * an {@link Entity}.
     *
     * <p>For example, a {@link Player} riding on a {@link Horse} or a
     * {@link Pig} would be considered its passenger.</p>
     *
     * @see PassengerData#passengers()
     */
    public static final Key<ListValue<UUID>> PASSENGERS = DummyObjectProvider.createExtendedFor(Key.class,"PASSENGERS");

    /**
     * Represents the {@link Key} for whether an {@link Entity} or
     * {@link BlockState} will be prevented from despawning/decaying.
     *
     * <p>In Vanilla, entities may despawn if the player moves too far from
     * them. A persisting entity will not be removed due to no players being
     * near it.</p>
     *
     * @see PersistingData#persistent()
     */
    public static final Key<Value<Boolean>> PERSISTENT = DummyObjectProvider.createExtendedFor(Key.class,"PERSISTENT");

    /**
     * Represents the {@link Key} for representing the pickup delay
     * of an {@link Item}.
     *
     * @see PickupDelayData#delay()
     */
    public static final Key<BoundedValue<Integer>> PICKUP_DELAY = DummyObjectProvider.createExtendedFor(Key.class,"PICKUP_DELAY");

    /**
     * Represents the {@link Key} for the "pickup rule" of an {@link Arrow}.
     *
     * @see PickupRuleData
     */
    public static final Key<Value<PickupRule>> PICKUP_RULE = DummyObjectProvider.createExtendedFor(Key.class,"PICKUP_RULE");

    /**
     * Represents the {@link Key} for whether a {@link Pig} is saddled.
     *
     * @see PigSaddleData#saddle()
     */
    public static final Key<Value<Boolean>> PIG_SADDLE = DummyObjectProvider.createExtendedFor(Key.class,"PIG_SADDLE");

    /**
     * Represents the {@link Key} for which block types an {@link ItemStack}
     * may be placed on.
     *
     * @see PlaceableData#placeable()
     */
    public static final Key<SetValue<BlockType>> PLACEABLE_BLOCKS = DummyObjectProvider.createExtendedFor(Key.class,"PLACEABLE_BLOCKS");

    /**
     * Represents the {@link Key} for the content of a
     * {@link ItemTypes#WRITABLE_BOOK}.
     *
     * <p>Use {@link Keys#BOOK_PAGES} if you wish to get the contents of a
     * {@link ItemTypes#WRITTEN_BOOK}</p>
     *
     * @see PlainPagedData#pages()
     */
    public static final Key<ListValue<String>> PLAIN_BOOK_PAGES = DummyObjectProvider.createExtendedFor(Key.class,"PLAIN_BOOK_PAGES");

    /**
     * Represents the {@link Key} for whether an {@link IronGolem} has been
     * created by a {@link Player}.
     *
     * @see PlayerCreatedData#playerCreated()
     */
    public static final Key<Value<Boolean>> PLAYER_CREATED = DummyObjectProvider.createExtendedFor(Key.class,"PLAYER_CREATED");

    /**
     * Represents the {@link Key} for representing the {@link PortionType}
     * of a {@link BlockState}.
     *
     * @see PortionData
     */
    public static final Key<Value<PortionType>> PORTION_TYPE = DummyObjectProvider.createExtendedFor(Key.class,"PORTION_TYPE");

    /**
     * Represents the {@link Key} for which potion effects are present on an
     * {@link Entity} or stored on an {@link ItemStack}.
     *
     * @see PotionEffectData#effects()
     */
    public static final Key<ListValue<PotionEffect>> POTION_EFFECTS = DummyObjectProvider.createExtendedFor(Key.class,"POTION_EFFECTS");

    /**
     * Represents the {@link Key} for representing the potion type of an {@link ItemStack}.
     *
     * @see PotionTypeData#type()
     */
    public static final Key<Value<PotionType>> POTION_TYPE = DummyObjectProvider.createExtendedFor(Key.class,"POTION_TYPE");

    /**
     * Represents the {@link Key} for representing the "power" state
     * of a {@link BlockState}.
     *
     * <p>Applies to blocks that may emit a Redstone signal of variable
     * strength, such as {@link BlockTypes#REDSTONE_WIRE},
     * {@link BlockTypes#DAYLIGHT_DETECTOR},
     * {@link BlockTypes#LIGHT_WEIGHTED_PRESSURE_PLATE} etc.</p>
     *
     * @see RedstonePoweredData#power()
     */
    public static final Key<BoundedValue<Integer>> POWER = DummyObjectProvider.createExtendedFor(Key.class,"POWER");

    /**
     * Represents the {@link Key} for representing the "powered" state
     * of a {@link BlockState}.
     *
     * <p>Applies to blocks that may be powered in order to emit a
     * Redstone signal of consistently maximum strength, such as
     * {@link BlockTypes#LEVER}, {@link BlockTypes#OAK_BUTTON},
     * {@link BlockTypes#OAK_PRESSURE_PLATE}, and their stone
     * counterparts.</p>
     *
     * @see PoweredData#powered()
     */
    public static final Key<Value<Boolean>> POWERED = DummyObjectProvider.createExtendedFor(Key.class,"POWERED");

    /**
     * Represents the {@link Key} for the type of a {@link Rabbit}.
     *
     * @see RabbitData
     */
    public static final Key<Value<RabbitType>> RABBIT_TYPE = DummyObjectProvider.createExtendedFor(Key.class,"RABBIT_TYPE");

    /**
     * Represents the {@link Key} for representing the {@link RailDirection}
     * of a {@link BlockState}.
     *
     * @see RailDirectionData
     */
    public static final Key<Value<RailDirection>> RAIL_DIRECTION = DummyObjectProvider.createExtendedFor(Key.class,"RAIL_DIRECTION");

    /**
     * Represents the {@link Key} for how much air a {@link Living} has left.
     *
     * @see BreathingData#remainingAir()
     */
    public static final Key<BoundedValue<Integer>> REMAINING_AIR = DummyObjectProvider.createExtendedFor(Key.class,"REMAINING_AIR");

    /**
     * Represents the {@link Key} for how many more ticks the current brewing
     * process of a {@link BrewingStand} will take.
     *
     * <p>If nothing is being brewed, the remaining brew time will be 0.</p>
     *
     * @see BrewingStandData#remainingBrewTime()
     */
    public static final Key<BoundedValue<Integer>> REMAINING_BREW_TIME = DummyObjectProvider.createExtendedFor(Key.class,"REMAINING_BREW_TIME");

    /**
     * Represents the {@link Key} for representing the {@link BlockState}
     * inside a {@link Minecart}.
     *
     * @see MinecartBlockData#block()
     */
    public static final Key<Value<BlockState>> REPRESENTED_BLOCK = DummyObjectProvider.createExtendedFor(Key.class,"REPRESENTED_BLOCK");

    /**
     * Represents the {@link Key} for the item displayed in an
     * {@link ItemFrame}.
     *
     * @see RepresentedItemData#item()
     */
    public static final Key<Value<ItemStackSnapshot>> REPRESENTED_ITEM = DummyObjectProvider.createExtendedFor(Key.class,"REPRESENTED_ITEM");

    /**
     * Represents the {@link Key} for the player represented by a
     * {@link BlockTypes#PLAYER_HEAD} (and {@link BlockTypes#PLAYER_WALL_HEAD})
     * block or a {@link ItemTypes#PLAYER_HEAD} item stack.
     *
     * @see RepresentedPlayerData#owner()
     */
    public static final Key<Value<GameProfile>> REPRESENTED_PLAYER = DummyObjectProvider.createExtendedFor(Key.class,"REPRESENTED_PLAYER");

    /**
     * Represents the {@link Key} for the spawn locations a {@link Player}
     * may have for various worlds based on {@link UUID} of the world.
     *
     * @see RespawnLocationData#respawnLocation()
     */
    public static final Key<MapValue<UUID, RespawnLocation>> RESPAWN_LOCATIONS = DummyObjectProvider.createExtendedFor(Key.class,"RESPAWN_LOCATIONS");

    /**
     * Represents the {@link Key} for the rotation of an {@link Entity}'s right
     * arm.
     *
     * @see BodyPartRotationalData#rightArmDirection()
     */
    public static final Key<Value<Vector3d>> RIGHT_ARM_ROTATION = DummyObjectProvider.createExtendedFor(Key.class,"RIGHT_ARM_ROTATION");

    /**
     * Represents the {@link Key} for the rotation of an {@link Entity}'s right
     * leg.
     *
     * @see BodyPartRotationalData#rightLegDirection()
     */
    public static final Key<Value<Vector3d>> RIGHT_LEG_ROTATION = DummyObjectProvider.createExtendedFor(Key.class,"RIGHT_LEG_ROTATION");

    /**
     * Represents the {@link Key} for the {@link Rotation} of a block or an
     * {@link ItemFrame}.
     *
     * @see RotationalData
     */
    public static final Key<Value<Rotation>> ROTATION = DummyObjectProvider.createExtendedFor(Key.class,"ROTATION");

    /**
     * Represents the {@link Key} for the current saturation of a {@link Living}.
     *
     * <p>When the saturation reaches 0, the {@link #FOOD_LEVEL} will decrease
     * and the saturation will be reset to maximum.</p>
     *
     * @see FoodData#saturation()
     */
    public static final Key<BoundedValue<Double>> SATURATION = DummyObjectProvider.createExtendedFor(Key.class,"SATURATION");

    /**
     * Represents the {@link Key} for the "scale" for the size of an
     * {@link Entity}.
     *
     * @see EntitySizeData#scale()
     */
    public static final Key<BoundedValue<Float>> SCALE = DummyObjectProvider.createExtendedFor(Key.class,"SCALE");

    /**
     * Represents the {@link Key} for representing the "should drop" state
     * of a {@link BlockState}.
     *
     * @see DropData#willDrop()
     */
    public static final Key<Value<Boolean>> SHOULD_DROP = DummyObjectProvider.createExtendedFor(Key.class,"SHOULD_DROP");

    /**
     * Represents the {@link Key} for the lines displayed on a {@link Sign}.
     *
     * @see SignData#lines()
     */
    public static final Key<ListValue<Text>> SIGN_LINES = DummyObjectProvider.createExtendedFor(Key.class,"SIGN_LINES");

    /**
     * Represents the {@link Key} for the skin of a {@link Humanoid}.
     *
     * <p>Skins can only be manipulated by supplying the UUID of a player
     * having that skin. The binary skin data is signed by Mojang so fully
     * customized skins are not possible.</p>
     *
     * @see SkinData#skin()
     */
    public static final Key<Value<ProfileProperty>> SKIN = DummyObjectProvider.createExtendedFor(Key.class,"SKIN");

    /**
     * Represents the {@link Key} for representing the {@link SlabPortion}
     * of a {@link BlockState}.
     *
     * @see SlabPortionData
     */
    public static final Key<Value<SlabPortion>> SLAB_PORTION = DummyObjectProvider.createExtendedFor(Key.class,"SLAB_PORTION");

    /**
     * Represents the {@link Key} for the size of a {@link Slime}.
     *
     * @see SlimeData#size()
     */
    public static final Key<BoundedValue<Integer>> SLIME_SIZE = DummyObjectProvider.createExtendedFor(Key.class,"SLIME_SIZE");

    /**
     * Represents the {@link Key} for representing the "snowed" state
     * of a {@link BlockState}.
     *
     * @see SnowedData#hasSnow()
     */
    public static final Key<Value<Boolean>> SNOWED = DummyObjectProvider.createExtendedFor(Key.class,"SNOWED");

    /**
     * Represents the {@link Key} for the list of {@link EntityArchetype}s able
     * to be spawned by a {@link MobSpawner}.
     *
     * @see MobSpawnerData#possibleEntitiesToSpawn()
     */
    public static final Key<WeightedCollectionValue<EntityArchetype>> SPAWNER_ENTITIES = DummyObjectProvider.createExtendedFor(Key.class,"SPAWNER_ENTITIES");

    /**
     * Represents the {@link Key} for the maximum amount of ticks between two
     * batches of entities spawned by a {@link MobSpawner}.
     *
     * @see MobSpawnerData#maximumSpawnDelay()
     */
    public static final Key<BoundedValue<Short>> SPAWNER_MAXIMUM_DELAY = DummyObjectProvider.createExtendedFor(Key.class,"SPAWNER_MAXIMUM_DELAY");

    /**
     * Represents the {@link Key} for the maximum number of entities around a
     * {@link MobSpawner}. A spawner will not spawn entities if there are more
     * entities around than this value permits.
     *
     * @see MobSpawnerData#maximumNearbyEntities()
     */
    public static final Key<BoundedValue<Short>> SPAWNER_MAXIMUM_NEARBY_ENTITIES = DummyObjectProvider.createExtendedFor(Key.class,"SPAWNER_MAXIMUM_NEARBY_ENTITIES");

    /**
     * Represents the {@link Key} for the minimum amount of ticks between two
     * batches of entities spawned by a {@link MobSpawner}.
     *
     * @see MobSpawnerData#minimumSpawnDelay()
     */
    public static final Key<BoundedValue<Short>> SPAWNER_MINIMUM_DELAY = DummyObjectProvider.createExtendedFor(Key.class,"SPAWNER_MINIMUM_DELAY");

    /**
     * Represents the {@link Key} for the next entity that will be spawned
     * by a {@link MobSpawner}.
     *
     * <p>Normally the entities to be spawned are determined by a random value
     * applied to the {@link #SPAWNER_ENTITIES} weighted collection. If this
     * value exists, it will override the random spawn with a definite one.</p>
     *
     * @see MobSpawnerData#nextEntityToSpawn()
     */
    public static final Key<Value<WeightedSerializableObject<EntityArchetype>>> SPAWNER_NEXT_ENTITY_TO_SPAWN = DummyObjectProvider.createExtendedFor(Key.class,"SPAWNER_NEXT_ENTITY_TO_SPAWN");

    /**
     * Represents the {@link Key} for the remaining number of ticks to pass
     * before another attempt to spawn entities is made by a {@link MobSpawner}.
     *
     * @see MobSpawnerData#remainingDelay()
     */
    public static final Key<BoundedValue<Short>> SPAWNER_REMAINING_DELAY = DummyObjectProvider.createExtendedFor(Key.class,"SPAWNER_REMAINING_DELAY");

    /**
     * Represents the {@link Key} for how close a {@link Player} has to be
     * around the {@link MobSpawner} in order for it to attempt to
     * spawn entities.
     *
     * @see MobSpawnerData#requiredPlayerRange()
     */
    public static final Key<BoundedValue<Short>> SPAWNER_REQUIRED_PLAYER_RANGE = DummyObjectProvider.createExtendedFor(Key.class,"SPAWNER_REQUIRED_PLAYER_RANGE");

    /**
     * Represents the {@link Key} for how many entities a {@link MobSpawner} has
     * spawned so far.
     *
     * @see MobSpawnerData#spawnCount()
     */
    public static final Key<BoundedValue<Short>> SPAWNER_SPAWN_COUNT = DummyObjectProvider.createExtendedFor(Key.class,"SPAWNER_SPAWN_COUNT");

    /**
     * Represents the {@link Key} for how far away from the
     * {@link MobSpawner} the entities spawned by it may appear.
     *
     * @see MobSpawnerData#spawnRange()
     */
    public static final Key<BoundedValue<Short>> SPAWNER_SPAWN_RANGE = DummyObjectProvider.createExtendedFor(Key.class,"SPAWNER_SPAWN_RANGE");

    /**
     * Represents the {@link Key} for representing the {@link StairShape}
     * of a {@link BlockState}.
     *
     * @see StairShapeData
     */
    public static final Key<Value<StairShape>> STAIR_SHAPE = DummyObjectProvider.createExtendedFor(Key.class,"STAIR_SHAPE");

    /**
     * Represents the {@link Key} for the {@link Statistic}s of a {@link Player}.
     *
     * @see StatisticData
     */
    public static final Key<MapValue<Statistic, Long>> STATISTICS = DummyObjectProvider.createExtendedFor(Key.class,"STATISTICS");

    /**
     * Represents the {@link Key} for the enchantments stored on an
     * {@link ItemStack}.
     *
     * <p>Stored enchantments are meant to be transferred. Usually this key
     * applies to {@link ItemTypes#ENCHANTED_BOOK} stacks. Enchantments
     * affecting the item stack are retrieved via {@link #ITEM_ENCHANTMENTS}
     * instead.</p>
     *
     * @see StoredEnchantmentData#enchantments()
     */
    public static final Key<ListValue<Enchantment>> STORED_ENCHANTMENTS = DummyObjectProvider.createExtendedFor(Key.class,"STORED_ENCHANTMENTS");

    /**
     * Represents the {@link Key} for representing the mode of a {@link Structure}.
     *
     * @see StructureData#mode()
     */
    public static final Key<Value<String>> STRUCTURE_AUTHOR = DummyObjectProvider.createExtendedFor(Key.class,"STRUCTURE_AUTHOR");

    /**
     * Represents the {@link Key} for representing the mode of a {@link Structure}.
     *
     * @see StructureData#mode()
     */
    public static final Key<Value<Boolean>> STRUCTURE_IGNORE_ENTITIES = DummyObjectProvider.createExtendedFor(Key.class,"STRUCTURE_IGNORE_ENTITIES");

    /**
     * Represents the {@link Key} for representing the mode of a {@link Structure}.
     *
     * @see StructureData#mode()
     */
    public static final Key<Value<Float>> STRUCTURE_INTEGRITY = DummyObjectProvider.createExtendedFor(Key.class,"STRUCTURE_INTEGRITY");

    /**
     * Represents the {@link Key} for representing the mode of a {@link Structure}.
     *
     * @see StructureData#mode()
     */
    public static final Key<Value<StructureMode>> STRUCTURE_MODE = DummyObjectProvider.createExtendedFor(Key.class,"STRUCTURE_MODE");

    /**
     * Represents the {@link Key} for representing the position of a {@link Structure}.
     *
     * @see StructureData#size()
     */
    public static final Key<Value<Vector3i>> STRUCTURE_POSITION = DummyObjectProvider.createExtendedFor(Key.class,"STRUCTURE_POSITION");

    /**
     * Represents the {@link Key} for representing the mode of a {@link Structure}.
     *
     * @see StructureData#mode()
     */
    public static final Key<Value<Boolean>> STRUCTURE_POWERED = DummyObjectProvider.createExtendedFor(Key.class,"STRUCTURE_POWERED");

    /**
     * Represents the {@link Key} for representing the mode of a {@link Structure}.
     *
     * @see StructureData#mode()
     */
    public static final Key<Value<Long>> STRUCTURE_SEED = DummyObjectProvider.createExtendedFor(Key.class,"STRUCTURE_SEED");

    /**
     * Represents the {@link Key} for representing the mode of a {@link Structure}.
     *
     * @see StructureData#mode()
     */
    public static final Key<Value<Boolean>> STRUCTURE_SHOW_AIR = DummyObjectProvider.createExtendedFor(Key.class,"STRUCTURE_SHOW_AIR");

    /**
     * Represents the {@link Key} for representing the mode of a {@link Structure}.
     *
     * @see StructureData#mode()
     */
    public static final Key<Value<Boolean>> STRUCTURE_SHOW_BOUNDING_BOX = DummyObjectProvider.createExtendedFor(Key.class,"STRUCTURE_SHOW_BOUNDING_BOX");

    /**
     * Represents the {@link Key} for representing the size of a {@link Structure}.
     *
     * @see StructureData#size()
     */
    public static final Key<Value<Vector3i>> STRUCTURE_SIZE = DummyObjectProvider.createExtendedFor(Key.class,"STRUCTURE_SIZE");

    /**
     * Represents the {@link Key} for representing the amount of "stuck arrows"
     * in {@link Living} entities.
     *
     * @see StuckArrowsData#stuckArrows()
     */
    public static final Key<BoundedValue<Integer>> STUCK_ARROWS = DummyObjectProvider.createExtendedFor(Key.class,"STUCK_ARROWS");

    /**
     * Represents a key for the amount of successful executions of a command
     * stored in a {@link CommandBlock} or {@link CommandBlockMinecart}.
     *
     * @see CommandData#successCount()
     */
    public static final Key<BoundedValue<Integer>> SUCCESS_COUNT = DummyObjectProvider.createExtendedFor(Key.class,"SUCCESS_COUNT");

    /**
     * Represents the {@link Key} for representing the "suspended" state
     * of a {@link BlockState}.
     */
    public static final Key<Value<Boolean>> SUSPENDED = DummyObjectProvider.createExtendedFor(Key.class,"SUSPENDED");

    /**
     * Represents the {@link Key} for representing the scoreboard tags applied
     * to an {@link Entity}.
     *
     * @see <a href="https://minecraft.gamepedia.com/Scoreboard#Tags">
     *     https://minecraft.gamepedia.com/Scoreboard#Tags</a>
     * @see TagData#tags()
     */
    public static final Key<SetValue<String>> TAGS = DummyObjectProvider.createExtendedFor(Key.class, "TAGS");

    /**
     * Represents the {@link Key} for the owner uuid of a tamed {@link Animal}.
     *
     * <p>Tamable animals in Vanilla may be a {@link Wolf}, an {@link Ocelot}
     * or a {@link Horse}.</p>
     *
     * @see TameableData#owner()
     */
    public static final Key<OptionalValue<UUID>> TAMED_OWNER = DummyObjectProvider.createExtendedFor(Key.class,"TAMED_OWNER");

    /**
     * Represents the {@link Key} for a targeted entity,
     * like by a {@link ShulkerBullet}.
     *
     * @see TargetedEntityData#value
     */
    public static final Key<Value<EntitySnapshot>> TARGETED_ENTITY = DummyObjectProvider.createExtendedFor(Key.class,"TARGETED_ENTITY");

    /**
     * Represents the {@link Key} for the location targeted by an
     * {@link EyeOfEnder} or a {@link Player}'s compass.
     *
     * @see TargetedLocationData#target()
     */
    public static final Key<Value<Vector3d>> TARGETED_LOCATION = DummyObjectProvider.createExtendedFor(Key.class,"TARGETED_LOCATION");

    /**
     * Represents the {@link Key} for the remaining fuse time in ticks of a
     * {@link FusedExplosive}. This value may be set to an arbitrary value
     * if the explosive is not primed.
     *
     * @see FuseData#ticksRemaining()
     */
    public static final Key<Value<Integer>> TICKS_REMAINING = DummyObjectProvider.createExtendedFor(Key.class,"TICKS_REMAINING");

    /**
     * Represents the {@link Key} for the full amount of experience a
     * {@link Player} has.
     *
     * @see ExperienceHolderData#totalExperience()
     */
    public static final Key<BoundedValue<Integer>> TOTAL_EXPERIENCE = DummyObjectProvider.createExtendedFor(Key.class,"TOTAL_EXPERIENCE");

    /**
     * Represents the {@link Key} for whether a {@link CommandBlock} does track
     * its output.
     *
     * <p>If this is set, the output of the most recent execution can be
     * retrieved using {@link #LAST_COMMAND_OUTPUT}.</p>
     *
     * @see CommandData#doesTrackOutput()
     */
    public static final Key<Value<Boolean>> TRACKS_OUTPUT = DummyObjectProvider.createExtendedFor(Key.class,"TRACKS_OUTPUT");

    /**
     * Represents the {@link Key} for the {@link TradeOffer}s offered by a
     * {@link Villager}.
     *
     * @see TradeOfferData#tradeOffers()
     */
    public static final Key<ListValue<TradeOffer>> TRADE_OFFERS = DummyObjectProvider.createExtendedFor(Key.class,"TRADE_OFFERS");

    /**
     * Represents the {@link Key} for whether an {@link ItemStack} is unbreakable.
     *
     * <p>Setting this to {@code  true} will prevent the item stack's
     * {@link #ITEM_DURABILITY} from changing.</p>
     *
     * @see DurabilityData#unbreakable()
     */
    public static final Key<Value<Boolean>> UNBREAKABLE = DummyObjectProvider.createExtendedFor(Key.class,"UNBREAKABLE");

    /**
     * Represents the {@link Key} for whether or not changes to {@link Keys#SKIN} should
     * be reflected in an entitie's {@link GameProfile}.
     *
     * @see SkinData#updateGameProfile()
     *
     * */
    public static final Key<Value<Boolean>> UPDATE_GAME_PROFILE = DummyObjectProvider.createExtendedFor(Key.class, "UPDATE_GAME_PROFILE");

    /**
     * Gets the {@link Key} for the "vanish" state of an {@link Entity}.
     *
     * <p>The presence of a vanished entity will not be made known to a client;
     * no packets pertaining to this entity are sent. Client-side, this entity
     * will cease to exist. Server-side it may still be targeted by hostile
     * entities or collide with other entities.</p>
     *
     * <p>Vanishing an {@link Entity} ridden by other entities (see
     * {@link #PASSENGERS} will cause problems.</p>
     *
     * @see Keys#VANISH
     */
    public static final Key<Value<Boolean>> VANISH = DummyObjectProvider.createExtendedFor(Key.class,"VANISH");

    /**
     * Represents the {@link Key} for whether an {@link Entity} ignores collision
     * with other entities.
     *
     * <p>This state will be ignored if the {@link Entity} is not also
     * vanished as per {@link #VANISH}.</p>
     *
     * @see InvisibilityData#ignoresCollisionDetection()
     */
    public static final Key<Value<Boolean>> VANISH_IGNORES_COLLISION = DummyObjectProvider.createExtendedFor(Key.class,"VANISH_IGNORES_COLLISION");

    /**
     * Represents the {@link Key} for
     * Gets the {@link Value} for whether an {@link Entity} can be targeted for
     * attack by another entity. This prevents neither {@link Player}s from
     * attacking the entity nor will it be protected from untargeted damage
     * like fire or explosions.
     *
     * <p>This state will be ignored if the {@link Entity} is not also
     * vanished as per {@link #VANISH}.}.</p>
     *
     * @see InvisibilityData#untargetable()
     */
    public static final Key<Value<Boolean>> VANISH_PREVENTS_TARGETING = DummyObjectProvider.createExtendedFor(Key.class,"VANISH_PREVENTS_TARGETING");

    /**
     * Represents the {@link Key} for the vehicle an {@link Entity} is riding.
     *
     * <p>Vehicles may be nested as a vehicle might itself ride another entity.
     * To get the vehicle on bottom, use {@link #BASE_VEHICLE}.</p>
     *
     * @see VehicleData#vehicle()
     */
    public static final Key<Value<EntitySnapshot>> VEHICLE = DummyObjectProvider.createExtendedFor(Key.class,"VEHICLE");

    /**
     * Represents the {@link Key} for the velocity of an {@link Entity}.
     *
     * @see VelocityData#velocity()
     */
    public static final Key<Value<Vector3d>> VELOCITY = DummyObjectProvider.createExtendedFor(Key.class,"VELOCITY");

    /**
     * Represents the {@link Key} for the speed at which an entity walks.
     *
     * @see MovementSpeedData#walkSpeed()
     */
    public static final Key<Value<Double>> WALKING_SPEED = DummyObjectProvider.createExtendedFor(Key.class,"WALKING_SPEED");

    /**
     * Represents the {@link Key} for whether a thrown {@link EyeOfEnder} will
     * shatter.
     *
     * @see ShatteringData#willShatter()
     */
    public static final Key<Value<Boolean>> WILL_SHATTER = DummyObjectProvider.createExtendedFor(Key.class,"WILL_SHATTER");

    /**
     * Represents the {@link Key} for how a {@link BlockTypes#REDSTONE_WIRE} is
     * connected to its neighboring blocks.
     *
     * @see WireAttachmentData#wireAttachments()
     */
    public static final Key<MapValue<Direction, WireAttachmentType>> WIRE_ATTACHMENTS = DummyObjectProvider.createExtendedFor(Key.class,"WIRE_ATTACHMENTS");

    /**
     * Represents the {@link Key} for how a {@link BlockTypes#REDSTONE_WIRE} is
     * connected to its neighboring block to the {@link Direction#EAST}.
     *
     * @see WireAttachmentData#wireAttachmentEast()
     */
    public static final Key<Value<WireAttachmentType>> WIRE_ATTACHMENT_EAST = DummyObjectProvider.createExtendedFor(Key.class,"WIRE_ATTACHMENT_EAST");

    /**
     * Represents the {@link Key} for how a {@link BlockTypes#REDSTONE_WIRE} is
     * connected to its neighboring block to the {@link Direction#NORTH}.
     *
     * @see WireAttachmentData#wireAttachmentNorth()
     */
    public static final Key<Value<WireAttachmentType>> WIRE_ATTACHMENT_NORTH = DummyObjectProvider.createExtendedFor(Key.class,"WIRE_ATTACHMENT_NORTH");

    /**
     * Represents the {@link Key} for how a {@link BlockTypes#REDSTONE_WIRE} is
     * connected to its neighboring block to the {@link Direction#SOUTH}.
     *
     * @see WireAttachmentData#wireAttachmentSouth()
     */
    public static final Key<Value<WireAttachmentType>> WIRE_ATTACHMENT_SOUTH = DummyObjectProvider.createExtendedFor(Key.class,"WIRE_ATTACHMENT_SOUTH");

    /**
     * Represents the {@link Key} for how a {@link BlockTypes#REDSTONE_WIRE} is
     * connected to its neighboring block to the {@link Direction#WEST}.
     *
     * @see WireAttachmentData#wireAttachmentWest()
     */
    public static final Key<Value<WireAttachmentType>> WIRE_ATTACHMENT_WEST = DummyObjectProvider.createExtendedFor(Key.class,"WIRE_ATTACHMENT_WEST");

    /**
     * Represents the {@link Key} for representing the {@link WoodType}
     * of a {@link Boat}.
     *
     * @see WoodData
     */
    public static final Key<Value<WoodType>> WOOD_TYPE = DummyObjectProvider.createExtendedFor(Key.class,"WOOD_TYPE");

    // SORTFIELDS:OFF

    // Suppress default constructor to ensure non-instantiability.
    private Keys() {
        throw new AssertionError("You should not be attempting to instantiate this class.");
    }

}
