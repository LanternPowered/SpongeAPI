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
package org.spongepowered.api.resource;

import com.google.common.io.ByteStreams;
import com.google.common.io.CharStreams;
import com.google.common.io.MoreFiles;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.data.DataView;
import org.spongepowered.api.data.persistence.DataFormat;
import org.spongepowered.api.data.persistence.InvalidDataFormatException;
import org.spongepowered.api.util.ResettableBuilder;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.Reader;
import java.nio.charset.Charset;
import java.nio.file.OpenOption;
import java.nio.file.Path;
import java.util.List;
import java.util.Optional;
import javax.annotation.WillNotClose;

/**
 * A resource can represent any kind of loaded data. It can be a file on the
 * filesystem, a network location, or even generated at runtime. Use
 * {@link #openStream()} to load the data held by a resource.
 */
public interface Resource extends InputStreamSupplier {

    /**
     * Creates a new builder for a {@link Resource}.
     *
     * @return A new builder
     */
    static Builder builder() {
        return Sponge.getRegistry().createBuilder(Builder.class);
    }

    /**
     * Creates a new resource for the given {@link ResourcePath} and
     * {@link InputStream} supplier. The supplier should return a new
     * input stream each time it is called.
     *
     * @param path The path of the resource
     * @param stream Supplier for the input stream
     * @return
     */
    static Resource of(ResourcePath path, InputStreamSupplier stream) {
        return builder().path(path).stream(stream).build();
    }

    /**
     * Gets the path of this resource.
     *
     * @return The path
     */
    ResourcePath getResourcePath();

    /**
     * Gets the {@link Pack} which owns this resource. The pack is set
     * automatically when the resource is added to a pack.
     *
     * @return The parent pack.
     */
    Pack getPack();

    /**
     * Returns a new {@link InputStream} of this resource. A new input stream
     * should be created each time this method is called.
     *
     * @return A new input stream
     */
    @Override
    InputStream openStream() throws IOException;

    /**
     * Gets the metadata for this resource.
     *
     * <p>The metadata file has the same name as this resource, but has
     * {@code .mcmeta} appended to the end.</p>
     *
     * <p>For example: the metadata for the resource
     * {@code minecraft:textures/blocks/water_flow.png} would be located at
     * {@code minecraft:textures/blocks/water_flow.png.mcmeta}</p>
     *
     * @return The metadata or {@link Optional#empty() empty} if it doesn't exist.
     * @see <a href=http://minecraft.gamepedia.com/Resource_pack#Contents> Minecraft Wiki/Resource Packs
     */
    default Optional<DataView> getMetadata() throws IOException {
        return Optional.empty();
    }

    /**
     * Gets a reader for this resource using the given {@link Charset}.
     *
     * @param charset The charset
     * @return A new reader
     * @throws IOException if an error occurs
     */
    default Reader getReader(Charset charset) throws IOException {
        return new InputStreamReader(openStream(), charset);
    }

    /**
     * Reads this resource into a string using the given charset.
     *
     * @param charset The charset
     * @return The string contents
     * @throws IOException if an error occurs
     */
    default String readString(Charset charset) throws IOException {
        try (Reader r = getReader(charset)) {
            return CharStreams.toString(r);
        }
    }

    /**
     * Reads a list of string lines from this resource using the given charset.
     *
     * @param charset The charset
     * @return The list of strings
     * @throws IOException if an error occurs
     */
    default List<String> readLines(Charset charset) throws IOException {
        try (Reader r = getReader(charset)) {
            return CharStreams.readLines(r);
        }
    }

    /**
     * Reads the bytes from this resource and returns them in an array.
     *
     * @return The bytes
     * @throws IOException if an error occurs
     */
    default byte[] readBytes() throws IOException {
        try (InputStream in = openStream()) {
            return ByteStreams.toByteArray(in);
        }
    }

    /**
     * Reads this resource into a {@link DataView}.
     *
     * @param format The data format to use
     * @return The dataview
     * @throws IOException if an error occurs
     */
    default DataView readDataView(DataFormat format) throws IOException {
        try (InputStream in = openStream()) {
            return format.readFrom(in);
        } catch (InvalidDataFormatException e) {
            throw new IOException(e);
        }
    }

    /**
     * Copies this resource to a given {@link Path}.
     *
     * @param path The file to write to
     * @param options The options to use
     * @throws IOException if an error occurs
     */
    default void copyTo(Path path, OpenOption... options) throws IOException {
        try (InputStream in = openStream()) {
            MoreFiles.asByteSink(path, options).writeFrom(in);
        }
    }

    /**
     * Copies this resource to a given {@link OutputStream}.
     *
     * @param out The output stream to write
     * @throws IOException if an error occurs
     */
    default void copyTo(@WillNotClose OutputStream out) throws IOException {
        try (InputStream in = openStream()) {
            ByteStreams.copy(in, out);
        }
    }

    /**
     * A builder for a {@link Resource}.
     */
    interface Builder extends ResettableBuilder<Resource, Builder> {

        /**
         * Sets the {@link ResourcePath} of the {@link Resource}.
         *
         * @param path The path
         * @return This builder
         */
        Builder path(ResourcePath path);

        /**
         * Sets the {@link InputStreamSupplier} of the {@link Resource}.
         *
         * @param stream The inputstream supplier
         * @return This builder
         */
        Builder stream(InputStreamSupplier stream);

        /**
         * Sets the metadata for the {@link Resource}.
         *
         * @param metadata The metadata
         * @return This builder
         */
        Builder metadata(DataView metadata);

        /**
         * Creates a new {@link Resource}.
         *
         * @return A new resource
         * @throws IllegalStateException if this builder is incomplete
         */
        Resource build() throws IllegalStateException;
    }

}
