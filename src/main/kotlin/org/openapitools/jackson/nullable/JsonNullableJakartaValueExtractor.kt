package org.openapitools.jackson.nullable

import jakarta.validation.valueextraction.UnwrapByDefault
import jakarta.validation.valueextraction.ValueExtractor

/**
 * Extractor for JsonNullable (modern jakarta-validation version)
 */
@UnwrapByDefault
class JsonNullableJakartaValueExtractor :
    ValueExtractor<JsonNullable<*>> {
    override fun extractValues(originalValue: JsonNullable<*>, receiver: ValueExtractor.ValueReceiver) {
        JsonNullableValueExtractorHelper.extractValues(
            originalValue,
            receiver::value
        )
    }
}