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
package org.spongepowered.api.command.parameter;

import org.spongepowered.api.CatalogType;
import org.spongepowered.api.Sponge;
import org.spongepowered.api.command.CommandSource;
import org.spongepowered.api.command.parameter.managed.ValueCompleter;
import org.spongepowered.api.command.parameter.managed.ValueParameter;
import org.spongepowered.api.command.parameter.managed.ValueParameterModifier;
import org.spongepowered.api.command.parameter.managed.ValueParser;
import org.spongepowered.api.command.parameter.managed.ValueUsage;
import org.spongepowered.api.command.parameter.managed.standard.CatalogedValueParameterModifiers;
import org.spongepowered.api.command.parameter.managed.standard.CatalogedValueParameters;
import org.spongepowered.api.command.parameter.managed.standard.VariableValueParameterModifiers;
import org.spongepowered.api.command.parameter.managed.standard.VariableValueParameters;
import org.spongepowered.api.command.parameter.token.CommandArgs;
import org.spongepowered.api.entity.Entity;
import org.spongepowered.api.text.Text;
import org.spongepowered.api.util.ResettableBuilder;

import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Optional;
import java.util.function.BiFunction;
import java.util.function.Function;
import java.util.function.Supplier;

import javax.annotation.Nullable;

/**
 * Defines how an element of a command argument string should be parsed.
 */
public interface Parameter {

    /**
     * Gets a builder that builds a {@link Parameter}.
     *
     * @return The {@link Builder}
     */
    static Builder builder() {
        return Sponge.getRegistry().createBuilder(Builder.class);
    }

    /**
     * Returns a {@link Parameter.FirstOfBuilder} that allows plugins to attempt
     * to parse an argument using the supplied parameters in order. Once a
     * parameter has parsed the argument successfully, no more parameters
     * supplied here will be attempted.
     *
     * @param parameter The first {@link Parameter}
     * @return The {@link Parameter.FirstOfBuilder} to continue chaining
     */
    static Parameter.FirstOfBuilder firstOf(Parameter parameter) {
        return Sponge.getRegistry().createBuilder(FirstOfBuilder.class).or(parameter);
    }

    /**
     * Returns a {@link Parameter} that attempts to parse an argument using the
     * supplied parameters in order. Once a parameter has parsed the argument
     * successfully, no more parameters supplied here will be attempted.
     *
     * @param first The first {@link Parameter} that should be used for parsing
     * @param second The second {@link Parameter} that should be used for
     *               parsing, should the first {@link Parameter} fail to do so
     * @param parameters The remaining {@link Parameter}s
     * @return The {@link Parameter}
     */
    static Parameter firstOf(Parameter first, Parameter second, Parameter... parameters) {
        return Sponge.getRegistry().createBuilder(FirstOfBuilder.class).or(first).or(second).orFirstOf(parameters).build();
    }

    /**
     * Returns a {@link Parameter} that attempts to parse an argument using the
     * supplied parameters in order. Once a parameter has parsed the argument
     * successfully, no more parameters supplied here will be attempted.
     *
     * @param parameters The {@link Parameter}s
     * @return The {@link Parameter}
     */
    static Parameter firstOf(Iterable<Parameter> parameters) {
        return Sponge.getRegistry().createBuilder(FirstOfBuilder.class).orFirstOf(parameters).build();
    }

    /**
     * Returns a {@link Parameter.SequenceBuilder} that parses arguments using
     * the supplied parameters in order.
     *
     * @param parameter The first {@link Parameter} in the sequence
     * @return The {@link Parameter.SequenceBuilder}, to continue building the
     *         chain
     */
    static Parameter.SequenceBuilder seq(Parameter parameter) {
        return Sponge.getRegistry().createBuilder(SequenceBuilder.class).then(parameter);
    }

    /**
     * Returns a {@link Parameter} that parses arguments using the supplied
     * parameters in order.
     *
     * @param first The first {@link Parameter} that should be used for parsing
     * @param second The second {@link Parameter} that should be used for
     *               parsing
     * @param parameters The subsequent {@link Parameter}s to parse
     * @return The {@link Parameter}
     */
    static Parameter seq(Parameter first, Parameter second, Parameter... parameters) {
        return Sponge.getRegistry().createBuilder(SequenceBuilder.class).then(first).then(second).then(parameters).build();
    }

    /**
     * Returns a {@link Parameter} that parses arguments using the supplied
     * parameters in order.
     *
     * @param parameters The {@link Parameter}s
     * @return The {@link Parameter}
     */
    static Parameter seq(Iterable<Parameter> parameters) {
        return Sponge.getRegistry().createBuilder(SequenceBuilder.class).then(parameters).build();
    }

    // Convenience methods for getting the common parameter types - all in once place.
    // Modifiers (for the most part) are on the Parameter.Builder itself.

    static Parameter.Builder bool() {
        return Parameter.builder().setParser(CatalogedValueParameters.BOOLEAN);
    }

    static Parameter.Builder dimension() {
        return Parameter.builder().setParser(CatalogedValueParameters.DIMENSION);
    }

    static Parameter.Builder duration() {
        return Parameter.builder().setParser(CatalogedValueParameters.DURATION);
    }

    static Parameter.Builder doubleNumber() {
        return Parameter.builder().setParser(CatalogedValueParameters.DOUBLE);
    }

    static Parameter.Builder entity() {
        return Parameter.builder().setParser(CatalogedValueParameters.ENTITY);
    }

    static Parameter.Builder entityOrSource() {
        return entity().modifier(CatalogedValueParameterModifiers.OR_ENTITY_SOURCE);
    }

    static Parameter.Builder integerNumber() {
        return Parameter.builder().setParser(CatalogedValueParameters.INTEGER);
    }

    static Parameter.Builder location() {
        return Parameter.builder().setParser(CatalogedValueParameters.LOCATION);
    }

    static Parameter.Builder longNumber() {
        return Parameter.builder().setParser(CatalogedValueParameters.LONG);
    }

    static Parameter.Builder player() {
        return Parameter.builder().setParser(CatalogedValueParameters.PLAYER);
    }

    static Parameter.Builder playerOrSource() {
        return player().modifier(CatalogedValueParameterModifiers.OR_PLAYER_SOURCE);
    }

    static Parameter.Builder plugin() {
        return Parameter.builder().setParser(CatalogedValueParameters.PLUGIN);
    }

    static Parameter.Builder remainingJoinedStrings() {
        return Parameter.builder().setParser(CatalogedValueParameters.REMAINING_JOINED_STRINGS);
    }

    static Parameter.Builder remainingRawJoinedStrings() {
        return Parameter.builder().setParser(CatalogedValueParameters.REMAINING_RAW_JOINED_STRINGS);
    }

    static Parameter.Builder string() {
        return Parameter.builder().setParser(CatalogedValueParameters.STRING);
    }

    static Parameter.Builder user() {
        return Parameter.builder().setParser(CatalogedValueParameters.USER);
    }

    static Parameter.Builder userOrSource() {
        return user().modifier(CatalogedValueParameterModifiers.OR_PLAYER_SOURCE);
    }

    static Parameter.Builder vector3d() {
        return Parameter.builder().setParser(CatalogedValueParameters.VECTOR3D);
    }

    static Parameter.Builder worldProperties() {
        return Parameter.builder().setParser(CatalogedValueParameters.WORLD_PROPERTIES);
    }

    static <T extends CatalogType> Parameter.Builder catalogedElement(Class<T> type) {
        return Parameter.builder().setParser(VariableValueParameters.catalogedElementParameterBuilder().setCatalogedType(type)
                .prefix("minecraft")
                .prefix("sponge")
                .build());
    }

    static Parameter.Builder choices(String... choices) {
        VariableValueParameters.StaticChoicesBuilder builder = VariableValueParameters.staticChoicesBuilder().setShowInUsage(true);
        for (String choice : choices) {
            builder.choice(choice);
        }

        return Parameter.builder().setParser(builder.build());
    }

    static Parameter.Builder choices(Map<String, ?> choices) {
        return Parameter.builder().setParser(VariableValueParameters.staticChoicesBuilder().choices(choices).setShowInUsage(true).build());
    }

    static Parameter.Builder choices(Supplier<Iterable<String>> choices, Function<String, ?> valueFunction) {
        return Parameter.builder().setParser(
                VariableValueParameters.dynamicChoicesBuilder().setShowInUsage(true).setChoices(choices).setResults(valueFunction).build());
    }

    static <T extends Enum<T>> Parameter.Builder enumValue(Class<T> enumClass) {
        return Parameter.builder().setParser(VariableValueParameters.enumBuilder().setEnumClass(enumClass).build());
    }

    static Parameter.Builder literal(Object returnedValue, String... literal) {
        Iterable<String> iterable = Arrays.asList(literal);
        return literal(returnedValue, () -> iterable);
    }

    static Parameter.Builder literal(Object returnedValue, Supplier<Iterable<String>> literalSupplier) {
        return Parameter.builder()
                .setParser(VariableValueParameters.literalBuilder().setReturnValue(returnedValue).setLiteral(literalSupplier).build());
    }

    /**
     * Parses the next element(s) in the {@link CommandContext}
     *
     * @param source The {@link CommandSource} executing this command.
     * @param args The {@link CommandArgs} containing the strings that need
     *             to be parsed
     * @param context The {@link CommandContext} that contains the
     *                current state of the execution.
     * @throws ArgumentParseException thrown if the parameter could not be
     *                                 parsed
     */
    void parse(CommandSource source, CommandArgs args, CommandContext context) throws ArgumentParseException;

    /**
     * Returns potential completions of the current tokenized argument.
     *
     * @param source The {@link CommandSource} executing this command.
     * @param args The {@link CommandArgs} containing the strings that need
     *             to be parsed
     * @param context The {@link CommandContext} that contains the
     *                current state of the execution.
     * @return The potential completions.
     * @throws ArgumentParseException thrown if the parameter could not be
     *                                 parsed
     */
    List<String> complete(CommandSource source, CommandArgs args, CommandContext context) throws ArgumentParseException;

    /**
     * Gets the usage of this parameter.
     *
     * @param source The {@link CommandSource} that requested the usage
     * @return The usage
     */
    Text getUsage(CommandSource source);

    /**
     * Builds a {@link Parameter} from constituent components.
     */
    interface Builder extends ResettableBuilder<Parameter, Builder> {

        /**
         * The key that the parameter will place parsed values into.
         *
         * <p>Mandatory</p>
         *
         * @param key The key.
         * @return This builder, for chaining
         */
        default Builder setKey(String key) {
            return setKey(Text.of(key));
        }

        /**
         * The key that the parameter will place parsed values into.
         *
         * <p>Mandatory</p>
         *
         * @param key The key.
         * @return This builder, for chaining
         */
        Builder setKey(Text key);

        /**
         * The {@link ValueParser} that will extract the value(s) from the
         * parameters. If this is a {@link ValueParameter}, the object's
         * complete and usage methods will be used for completion and usage
         * unless this builder's {@link #setSuggestions(ValueCompleter)}} and
         * {@link #setUsage(ValueUsage)} methods are specified.
         *
         * @param parser The {@link ValueParameter} to use
         * @return This builder, for chaining
         */
        Builder setParser(ValueParser parser);

        /**
         * Provides a function that provides tab completions
         *
         * <p>Optional. If this is <code>null</code> (or never set),
         * completions will either be done via the supplied
         * {@link #setParser(ValueParser)} or will just return an empty
         * list. If this is supplied, no modifiers will run on completion.</p>
         *
         * @param completer The {@link ValueCompleter}
         * @return This builder, for chaining
         */
        Builder setSuggestions(@Nullable ValueCompleter completer);

        /**
         * Sets the usage. The {@link BiFunction} accepts the parameter key
         * and the calling {@link CommandSource}.
         *
         * <p>Optional. If this is <code>null</code> (or never set),
         * the usage string will either be provided via the supplied
         * {@link #setParser(ValueParser)} or will just return
         * the parameter's key. If this is supplied, no modifiers will run on
         * usage.</p>
         *
         * @param usage The function
         * @return This builder, for chaining
         */
        Builder setUsage(@Nullable ValueUsage usage);

        /**
         * Adds a {@link ValueParameterModifier} that modify the behavior of the
         * parameter, for example, by requiring that only one output is
         * obtained.
         *
         * <p>Note that the modifiers wrap around the call to the value parser,
         * the first will be called which will be expected to call into
         * later modifiers. They will be called in the order they are added to
         * the builder.</p>
         *
         * @param modifier  The modifier
         * @return This builder, for chaining
         */
        Builder modifier(ValueParameterModifier modifier);

        /**
         * Adds {@link ValueParameterModifier}s that modify the behavior of the
         * parameter, for example, by requiring that only one output is
         * obtained.
         *
         * <p>Note that the modifiers wrap around the call to the value parser,
         * the first will be called which will be expected to call into
         * later modifiers. They will be called in this order.</p>
         *
         * @param modifiers The modifiers, in the order that they should
         *                  be executed
         * @return This builder, for chaining
         */
        default Builder modifiers(ValueParameterModifier... modifiers) {
            return modifiers(Arrays.asList(modifiers));
        }

        /**
         * Adds {@link ValueParameterModifier}s that modify the behavior of the
         * parameter, for example, by requiring that only one output is
         * obtained.
         *
         * <p>Note that the modifiers wrap around the call to the value parser,
         * the first will be called which will be expected to call into
         * later modifiers. They will be called in this order.</p>
         *
         * @param modifiers The modifiers, in the order that they should
         *                  be executed
         * @return This builder, for chaining
         */
        default Builder modifiers(List<ValueParameterModifier> modifiers) {
            for (ValueParameterModifier modifier : modifiers) {
                modifier(modifier);
            }

            return this;
        }

        /**
         * Sets the permission that the executing {@link CommandSource} is
         * required to have in order for this parameter to be parsed.
         *
         * <p>If the source does not have this permission, this parameter
         * will simply be skipped. Consider combining this with
         * the {@link CatalogedValueParameterModifiers#OPTIONAL} or
         * {@link CatalogedValueParameterModifiers#OPTIONAL_WEAK} modifiers,
         * so that those with permission can also skip this parameter.</p>
         *
         * @param permission The permission to check for, or {@code null} for
         *                   no check.
         * @return This builder, for chaining
         */
        Builder setRequiredPermission(@Nullable String permission);

        // Convenience methods

        /**
         * Adds a check to ensure that only one value is in the context under
         * this parameter's key. If there are more than one, or zero, an
         * {@link ArgumentParseException} will be thrown.
         *
         * @return This builder, for chaining
         */
        default Builder onlyOne() {
            return modifiers(CatalogedValueParameterModifiers.ONLY_ONE);
        }

        /**
         * Indicates that the parameter should iterate over and parse all
         * remaining elements in the provided set of arguments.
         *
         * @return This builder, for chaining
         */
        default Builder allOf() {
            return modifiers(CatalogedValueParameterModifiers.ALL_OF);
        }

        /**
         * Marks this parameter as optional, such that if an argument does not
         * exist, an exception is not thrown.
         *
         * <p>This should not be used in conjunction with other optional
         * modifiers.</p>
         *
         * @return This builder, for chaining
         */
        default Builder optional() {
            return modifiers(CatalogedValueParameterModifiers.OPTIONAL);
        }

        /**
         * Marks this parameter as optional, such that if an argument does not
         * exist <em>or</em> cannot be parsed, an exception is not thrown.
         *
         * <p>This should not be used in conjunction with other optional
         * modifiers.</p>
         *
         * @return This builder, for chaining
         */
        default Builder optionalWeak() {
            return modifiers(CatalogedValueParameterModifiers.OPTIONAL_WEAK);
        }

        /**
         * Marks this parameter as optional, such that if an argument does not
         * exist <em>or</em> cannot be parsed, an exception is not thrown, and
         * the value provided is inserted into the context instead.
         *
         * <p>This should not be used in conjunction with other optional
         * modifiers.</p>
         *
         * @param defaultValue The default value if this parameter does not
         *                     enter a value into the {@link CommandContext}
         * @return This builder, for chaining
         */
        default Builder optionalOrDefault(Object defaultValue) {
            Optional<?> result = Optional.of(defaultValue);
            return optionalOrDefault(source -> result);
        }

        /**
         * Marks this parameter as optional, such that if an argument does not
         * exist <em>or</em> cannot be parsed, the supplier is executed instead.
         * If this returns a value, it is inserted into the context under the
         * parameter's key, otherwise, an {@link ArgumentParseException} is
         * thrown.
         *
         * <p>If a default value is inserted into the context, the command
         * parser will attempt to parse the argument this element couldn't
         * parse using the next parser in the chain.</p>
         *
         * <p>This should not be used in conjuction with other optional
         * modifiers.</p>
         *
         * @param defaultValueSupplier A {@link Supplier} that returns an object
         *                             to insert into the context if this
         *                             parameter cannot parse the argument. If
         *                             the supplier returns an empty optional,
         *                             the parameter will throw an exception, as
         *                             if the parameter is not optional.
         * @return This builder, for chaining
         */
        default Builder optionalOrDefault(Supplier<?> defaultValueSupplier) {
            return optionalOrDefault(source -> Optional.ofNullable(defaultValueSupplier.get()));
        }

        /**
         * Marks this parameter as optional, such that if an argument does not
         * exist <em>or</em> cannot be parsed, the function is executed instead.
         * If this returns a value, it is inserted into the context under the
         * parameter's key, otherwise, an {@link ArgumentParseException} is
         * thrown.
         *
         * <p>If a default value is inserted into the context, the command
         * parser will attempt to parse the argument this element couldn't
         * parse using the next parser in the chain.</p>
         *
         * <p>This should not be used in conjuction with other optional
         * modifiers.</p>
         *
         * @param defaultValueFunction A {@link Function} that returns an object
         *                             to insert into the context if this
         *                             parameter cannot parse the argument. If
         *                             the supplier returns an empty optional,
         *                             the parameter will throw an exception, as
         *                             if the parameter is not optional.
         * @return This builder, for chaining
         */
        default Builder optionalOrDefault(Function<CommandSource, Optional<?>> defaultValueFunction) {
            return modifier(VariableValueParameterModifiers.defaultValueModifierBuilder().setDefaultValueFunction(defaultValueFunction).build());
        }

        /**
         * Requires the parameter to be provided a certain number of times.
         *
         * @param times The number of times to repeat this parameter
         * @return This builder, for chaining
         */
        default Builder repeated(int times) {
            return modifiers(VariableValueParameterModifiers.repeatedValueModifierBuilder().setNumberOfTimes(times).build());
        }

        /**
         * Specifies that the parameter could be satisfied by entities returned from
         * a selector instead.
         *
         * <p>If onlyOne is false, then developers <strong>must</strong> account for
         * the fact that more than one entity can be returned. If this is true, then
         * the parameter will throw an exception if more than one entity is returned
         * </p>
         *
         * <p>In-built parameter types where it makes sense to support selectors
         * (such as {@link CatalogedValueParameters#PLAYER} do not need this
         * modifier as they will already check selectors.
         * </p>
         *
         * <p>If a selector is detected, the associated {@link ValueParameter}
         * will not run.</p>
         *
         * @param entityType The type of {@link Entity} that can be returned by
         *                   the selector
         * @param onlyOne    Whether only one entity should be returned
         * @param strict     If true, the parser will fail if an entity that is
         *                   not of the provided type, else it will just remove
         *                   any non-conforming entities
         * @return This builder, for chaining
         */
        default Builder supportSelectors(Class<? extends Entity> entityType, boolean onlyOne, boolean strict) {
            return modifiers(
                    VariableValueParameterModifiers
                            .selectorValueModifierBuilder()
                            .entityType(entityType)
                            .setExpectOne(onlyOne)
                            .setStrict(strict)
                            .build());
        }

        /**
         * Creates a {@link Parameter} from the builder.
         *
         * @return The {@link Parameter}
         */
        Parameter build();

    }

    /**
     * Specifies a builder for creating a {@link Parameter} that returns a
     * parameter that concatenates all parameters into a single
     * parameter to be executed one by one.
     */
    interface SequenceBuilder extends ResettableBuilder<Parameter, SequenceBuilder> {

        /**
         * Sets that this parameter is optional, and will be ignored if it isn't
         * specified - but will throw an error if this is passed something to
         * parse that it cannot parse.
         *
         * @return This builder, for chaining
         */
        SequenceBuilder optional();

        /**
         * Sets that this parameter is weak optional and will be ignored if it
         * cannot parse the next element(s).
         *
         * @return This builder, for chaining
         */
        SequenceBuilder optionalWeak();

        /**
         * Defines the next parameter in the parameter sequence.
         *
         * @param parameter The parameter
         * @return This builder, for chaining
         */
        SequenceBuilder then(Parameter parameter);

        /**
         * Adds a set of {@link Parameter}s to this builder.
         *
         * <p>The parameters will be parsed in the provided order.</p>
         *
         * @param parameters The parameters to add
         * @return This builder, for chaining
         */
        default SequenceBuilder then(Parameter... parameters) {
            return then(Arrays.asList(parameters));
        }

        /**
         * Adds a set of {@link Parameter}s to this builder.
         *
         * <p>The parameters will be parsed in the provided order.</p>
         *
         * @param parameters The parameters to add
         * @return This builder, for chaining
         */
        default SequenceBuilder then(Iterable<Parameter> parameters) {
            for (Parameter parameter : parameters) {
                then(parameter);
            }

            return this;
        }

        /**
         * Creates a {@link Parameter} from the builder.
         *
         * @return The {@link Parameter}
         */
        Parameter build();

    }

    /**
     * Specifies a builder for creating a {@link Parameter} that returns a
     * parameter that concatenates all parameters into a single
     * parameter to be executed one by one.
     */
    interface FirstOfBuilder extends ResettableBuilder<Parameter, FirstOfBuilder> {

        /**
         * Sets that this parameter is optional, and will be ignored if it isn't
         * specified - but will throw an error if this is passed something to
         * parse that it cannot parse.
         *
         * @return This builder, for chaining
         */
        FirstOfBuilder optional();

        /**
         * Sets that this parameter is weak optional and will be ignored if it
         * cannot parse the next element(s).
         *
         * @return This builder, for chaining
         */
        FirstOfBuilder optionalWeak();

        /**
         * Adds a parameter that can be used to parse an argument. Parameters
         * are checked in the order they are added to the builder.
         *
         * @param parameter The parameter
         * @return This builder, for chaining
         */
        FirstOfBuilder or(Parameter parameter);

        /**
         * Adds a set of {@link Parameter}s to this builder.
         *
         * <p>The parameters will be parsed in the provided order until one
         * succeeds.</p>
         *
         * @param parameters The parameters to add
         * @return This builder, for chaining
         */
        default FirstOfBuilder orFirstOf(Parameter... parameters) {
            return orFirstOf(Arrays.asList(parameters));
        }

        /**
         * Adds a set of {@link Parameter}s to this builder.
         *
         * <p>The parameters will be parsed in the provided order until one
         * succeeds.</p>
         *
         * @param parameters The parameters to add
         * @return This builder, for chaining
         */
        default FirstOfBuilder orFirstOf(Iterable<Parameter> parameters) {
            for (Parameter parameter : parameters) {
                or(parameter);
            }

            return this;
        }

        /**
         * Creates a {@link Parameter} from the builder.
         *
         * @return The {@link Parameter}
         */
        Parameter build();

    }

}
