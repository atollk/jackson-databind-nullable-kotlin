package org.openapitools.jackson.nullable

import java.io.Serializable
import java.util.*

data class JsonNullable<T>(private val value: T, val isPresent: Boolean) : Serializable {
    /**
     * Obtain the value of this `JsonNullable`.
     *
     * @return the value, if present
     * @throws NoSuchElementException if no value is present
     */
    fun get(): T =
        if (!isPresent)
            throw NoSuchElementException("Value is undefined")
        else
            value

    /**
     * Obtain the value of this `JsonNullable`.
     *
     * @param other the value to be returned if no value is present
     * @return the value of this `JsonNullable` if present, the submitted value otherwise
     */
    fun orElse(other: T): T =
        if (isPresent) value else other

    /**
     * If a value is present, performs the given action with the value,
     * otherwise does nothing.
     *
     * @param action the action to be performed, if a value is present
     */
    fun ifPresent(
        action: (T) -> Unit
    ) {
        if (isPresent) {
            action(value)
        }
    }

    override fun toString(): String {
        return if (isPresent) String.format("JsonNullable[%s]", value) else "JsonNullable.undefined"
    }

    companion object {
        private const val serialVersionUID = 1L
        private val UNDEFINED: JsonNullable<*> = JsonNullable<Any?>(null, false)

        /**
         * Create a `JsonNullable` representing an undefined value (not present).
         *
         * @param <T> a type wildcard
         * @return an empty `JsonNullable` with no value defined
        </T> */
        fun <T> undefined(): JsonNullable<T> = UNDEFINED as JsonNullable<T>

        /**
         * Create a `JsonNullable` from the submitted value.
         *
         * @param value the value
         * @param <T>   the type of the value
         * @return the `JsonNullable` with the submitted value present.
        </T> */
        fun <T> of(value: T): JsonNullable<T> = JsonNullable(value, true)
    }
}