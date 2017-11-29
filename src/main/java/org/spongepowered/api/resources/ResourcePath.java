package org.spongepowered.api.resources;

import org.spongepowered.api.Sponge;
import org.spongepowered.api.util.ResettableBuilder;

/**
 * <p>A resource path should contain a namespace. If one is not provided,
 * {@code minecraft} will be used instead. The namespace and path must not
 * contain any special characters or uppercase letters. For example,
 * {@code lagmeter:functions/lm.json}.</p>
 *
 * <p>In the pack, the path will point to a resource. The resource should
 * be located roughly at {@code data/namespace/path}</p>
 */
public interface ResourcePath extends Comparable<ResourcePath> {

    /**
     * Creates a new {@link Builder} to build a {@link ResourcePath}.
     *
     * @return The new builder
     */
    static Builder builder() {
        return Sponge.getRegistry().createBuilder(Builder.class);
    }

    /**
     * Creates a new {@link ResourcePath} using the given namespace and path.
     *
     * @param namespace The namespace to use
     * @param path The path to use
     * @return A new ResourcePath
     */
    static ResourcePath of(String namespace, String path) throws IllegalArgumentException {
        return builder().namespace(namespace).path(path).build();
    }

    /**
     * Parses a new {@link ResourcePath} from the given string representation.
     * The representation should include both the namespace and path, separated
     * by a colon (:). Path items should be separated by a forward slash (/).
     * If the namespace is excluded, the default of minecraft is used instead.
     *
     * <p>e.g. {@code lagmeter:functions/lm.json} points to the
     * functions/lm.json file in the lagmeter namespace.</p>
     *
     * @param path The path
     * @return
     */
    static ResourcePath parse(String path) throws IllegalArgumentException {
        return builder().parse(path).build();
    }

    String getNamespace();

    String getPath();

    /**
     * Represents a builder to create {@link ResourcePath} instances.
     */
    interface Builder extends ResettableBuilder<ResourcePath, Builder> {

        /**
         * Sets the namespace of the {@link ResourcePath}.
         *
         * @param namespace The namespace
         * @return This builder
         * @throws IllegalArgumentException if the namespace contains illegal characters
         */
        Builder namespace(String namespace) throws IllegalArgumentException;

        /**
         * Sets the path of the {@link ResourcePath}.
         *
         * @param path The path
         * @return This builder
         */
        Builder path(String path) throws IllegalArgumentException;

        /**
         * Parses a string which represents a {@link ResourcePath}. This
         * completes the builder.
         *
         * @param path The string representation of a resource path
         * @return This builder
         * @see ResourcePath#parse(String)
         */
        Builder parse(String path) throws IllegalArgumentException;

        /**
         * Builds an instance of a {@link ResourcePath}.
         *
         * @return A new instance of a {@link ResourcePath}
         * @throws IllegalStateException if a path was not provided
         */
        ResourcePath build() throws IllegalStateException;

    }
}
