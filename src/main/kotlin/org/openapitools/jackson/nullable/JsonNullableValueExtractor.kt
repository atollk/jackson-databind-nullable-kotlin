package org.openapitools.jackson.nullable;

import javax.validation.valueextraction.ExtractedValue
import javax.validation.valueextraction.UnwrapByDefault
import javax.validation.valueextraction.ValueExtractor

/**
 * Extractor for JsonNullable (classic javax-validation version)
 */
@UnwrapByDefault
class JsonNullableValueExtractor : ValueExtractor<JsonNullable<@ExtractedValue Any?>> {
    override fun extractValues(originalValue: JsonNullable<Any?>?, receiver: ValueExtractor.ValueReceiver) {
        if (originalValue != null) {
            JsonNullableValueExtractorHelper.extractValues(originalValue, receiver::value)
        }
    }
}
