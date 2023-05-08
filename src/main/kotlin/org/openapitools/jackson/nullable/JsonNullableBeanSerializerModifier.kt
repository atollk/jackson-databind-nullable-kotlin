package org.openapitools.jackson.nullable

import com.fasterxml.jackson.databind.BeanDescription
import com.fasterxml.jackson.databind.SerializationConfig
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter
import com.fasterxml.jackson.databind.ser.BeanSerializerModifier

class JsonNullableBeanSerializerModifier : BeanSerializerModifier() {
    override fun changeProperties(
        config: SerializationConfig,
        beanDesc: BeanDescription,
        beanProperties: MutableList<BeanPropertyWriter>
    ): List<BeanPropertyWriter> {
        for (i in beanProperties.indices) {
            val writer = beanProperties[i]
            val type = writer.type
            if (type.isTypeOrSubTypeOf(JsonNullable::class.java)) {
                beanProperties[i] = JsonNullableBeanPropertyWriter(writer)
            }
        }
        return beanProperties
    }
}