package org.openapitools.jackson.nullable

import com.fasterxml.jackson.databind.BeanDescription
import com.fasterxml.jackson.databind.DeserializationConfig
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.deser.Deserializers
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer
import com.fasterxml.jackson.databind.type.ReferenceType

class JsonNullableDeserializers : Deserializers.Base() {
    override fun findReferenceDeserializer(
        refType: ReferenceType,
        config: DeserializationConfig, beanDesc: BeanDescription,
        contentTypeDeserializer: TypeDeserializer, contentDeserializer: JsonDeserializer<*>?
    ): JsonDeserializer<*>? {
        return if (refType.hasRawClass(JsonNullable::class.java)) JsonNullableDeserializer(
            refType,
            null,
            contentTypeDeserializer,
            contentDeserializer
        ) else null
    }
}