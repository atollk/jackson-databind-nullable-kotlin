package org.openapitools.jackson.nullable

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.databind.PropertyName
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter
import com.fasterxml.jackson.databind.util.NameTransformer

class JsonNullableBeanPropertyWriter : BeanPropertyWriter {
    constructor(base: BeanPropertyWriter?) : super(base)
    protected constructor(base: BeanPropertyWriter?, newName: PropertyName?) : super(base, newName)

    override fun _new(newName: PropertyName): BeanPropertyWriter {
        return JsonNullableBeanPropertyWriter(this, newName)
    }

    override fun unwrappingWriter(unwrapper: NameTransformer): BeanPropertyWriter {
        return UnwrappingJsonNullableBeanPropertyWriter(this, unwrapper)
    }

    @Throws(Exception::class)
    override fun serializeAsField(bean: Any, jgen: JsonGenerator, prov: SerializerProvider) {
        val value = get(bean)
        if (JsonNullable.undefined<Any>() == value || _nullSerializer == null && value == null) {
            return
        }
        super.serializeAsField(bean, jgen, prov)
    }

    companion object {
        private const val serialVersionUID = 1L
    }
}