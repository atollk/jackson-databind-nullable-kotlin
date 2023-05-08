package org.openapitools.jackson.nullable

import com.fasterxml.jackson.databind.BeanDescription
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.MapperFeature
import com.fasterxml.jackson.databind.SerializationConfig
import com.fasterxml.jackson.databind.jsontype.TypeSerializer
import com.fasterxml.jackson.databind.ser.Serializers
import com.fasterxml.jackson.databind.type.ReferenceType

class JsonNullableSerializers : Serializers.Base() {
    override fun findReferenceSerializer(
        config: SerializationConfig,
        refType: ReferenceType, beanDesc: BeanDescription,
        contentTypeSerializer: TypeSerializer, contentValueSerializer: JsonSerializer<Any>
    ): JsonSerializer<*>? {
        if (JsonNullable::class.java.isAssignableFrom(refType.rawClass)) {
            val staticTyping = (contentTypeSerializer == null
                    && config.isEnabled(MapperFeature.USE_STATIC_TYPING))
            return JsonNullableSerializer(
                refType, staticTyping,
                contentTypeSerializer, contentValueSerializer
            )
        }
        return null
    }
}