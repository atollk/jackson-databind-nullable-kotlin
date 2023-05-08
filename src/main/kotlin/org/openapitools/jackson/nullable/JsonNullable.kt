package org.openapitools.jackson.nullable

import java.io.Serializable
import java.util.*
import java.util.function.Consumer

class JsonNullable<T> private constructor(private val value: T, val isPresent: Boolean) : Serializable {
    /**
     * Obtain the value of this `JsonNullable`.
     *
     * @return the value, if present
     * @throws NoSuchElementException if no value is present
     */
    fun get(): T {
        if (!isPresent) {
            throw NoSuchElementException("Value is undefined")
        }
        return value
    }

    /**
     * Obtain the value of this `JsonNullable`.
     *
     * @param other the value to be returned if no value is present
     * @return the value of this `JsonNullable` if present, the submitted value otherwise
     */
    fun orElse(other: T): T {
        return if (isPresent) value else other
    }

    /**
     * If a value is present, performs the given action with the value,
     * otherwise does nothing.
     *
     * @param action the action to be performed, if a value is present
     */
    fun ifPresent(
        action: Consumer<in T>
    ) {
        if (isPresent) {
            action.accept(value)
        }
    }

    override fun equals(obj: Any?): Boolean {
        if (this === obj) {
            return true
        }
        if (obj !is JsonNullable<*>) {
            return false
        }
        val other = obj
        return value == other.value &&
                isPresent == other.isPresent
    }

    override fun hashCode(): Int {
        return Objects.hash(value, isPresent)
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
        fun <T> undefined(): JsonNullable<T> {
            return UNDEFINED as JsonNullable<T>
        }

        /**
         * Create a `JsonNullable` from the submitted value.
         *
         * @param value the value
         * @param <T>   the type of the value
         * @return the `JsonNullable` with the submitted value present.
        </T> */
        fun <T> of(value: T): JsonNullable<T> {
            return JsonNullable(value, true)
        }
    }
}