package org.openapitools.jackson.nullable

import com.fasterxml.jackson.core.JsonGenerator
import com.fasterxml.jackson.core.io.SerializedString
import com.fasterxml.jackson.databind.SerializerProvider
import com.fasterxml.jackson.databind.ser.BeanPropertyWriter
import com.fasterxml.jackson.databind.ser.impl.UnwrappingBeanPropertyWriter
import com.fasterxml.jackson.databind.util.NameTransformer

class UnwrappingJsonNullableBeanPropertyWriter : UnwrappingBeanPropertyWriter {
    constructor(
        base: BeanPropertyWriter?,
        transformer: NameTransformer?
    ) : super(base, transformer)

    protected constructor(
        base: UnwrappingBeanPropertyWriter?,
        transformer: NameTransformer?, name: SerializedString?
    ) : super(base, transformer, name)

    override fun _new(transformer: NameTransformer, newName: SerializedString): UnwrappingBeanPropertyWriter {
        return UnwrappingJsonNullableBeanPropertyWriter(this, transformer, newName)
    }

    @Throws(Exception::class)
    override fun serializeAsField(bean: Any, gen: JsonGenerator, prov: SerializerProvider) {
        val value = get(bean)
        if (JsonNullable.undefined<Any>() == value || _nullSerializer == null && value == null) {
            return
        }
        super.serializeAsField(bean, gen, prov)
    }

    companion object {
        private const val serialVersionUID = 1L
    }
}