package org.openapitools.jackson.nullable

internal object JsonNullableValueExtractorHelper {
    fun extractValues(originalValue: JsonNullable<*>, valueSetter: ValueSetter) {
        if (originalValue.isPresent) {
            valueSetter.apply(null, originalValue.get())
        }
    }

    internal fun interface ValueSetter {
        fun apply(var1: String?, var2: Any?)
    }
}