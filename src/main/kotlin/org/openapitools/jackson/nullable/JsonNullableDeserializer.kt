package org.openapitools.jackson.nullable

import com.fasterxml.jackson.core.JsonParser
import com.fasterxml.jackson.core.JsonToken
import com.fasterxml.jackson.databind.DeserializationConfig
import com.fasterxml.jackson.databind.DeserializationContext
import com.fasterxml.jackson.databind.JavaType
import com.fasterxml.jackson.databind.JsonDeserializer
import com.fasterxml.jackson.databind.deser.ValueInstantiator
import com.fasterxml.jackson.databind.deser.std.ReferenceTypeDeserializer
import com.fasterxml.jackson.databind.jsontype.TypeDeserializer
import com.fasterxml.jackson.databind.type.ReferenceType
import java.io.IOException

class JsonNullableDeserializer(
    fullType: JavaType?, inst: ValueInstantiator?,
    typeDeser: TypeDeserializer?, deser: JsonDeserializer<*>?
) :
    ReferenceTypeDeserializer<JsonNullable<Any>>(fullType, inst, typeDeser, deser) {
    private var isStringDeserializer = false

    /*
    / **********************************************************
    / * Life-cycle
    / **********************************************************
     */
    init {
        if (fullType is ReferenceType && fullType.referencedType != null) {
            isStringDeserializer = fullType.referencedType.isTypeOrSubTypeOf(
                String::class.java
            )
        }
    }

    /*
    / **********************************************************
    / * Abstract method implementations
    / **********************************************************
     */
    @Throws(IOException::class)
    override fun deserialize(p: JsonParser, ctxt: DeserializationContext): JsonNullable<Any> {
        val t = p.currentToken
        if (t == JsonToken.VALUE_STRING && !isStringDeserializer) {
            val str = p.text.trim { it <= ' ' }
            if (str.isEmpty()) {
                return JsonNullable.undefined()
            }
        }
        return super.deserialize(p, ctxt)
    }

    public override fun withResolved(
        typeDeser: TypeDeserializer,
        valueDeser: JsonDeserializer<*>?
    ): JsonNullableDeserializer {
        return JsonNullableDeserializer(
            _fullType, _valueInstantiator,
            typeDeser, valueDeser
        )
    }

    override fun getAbsentValue(ctxt: DeserializationContext): Any {
        return JsonNullable.undefined<Any>()
    }

    override fun getNullValue(ctxt: DeserializationContext): JsonNullable<Any> {
        return JsonNullable.of(null)
    }

    override fun getEmptyValue(ctxt: DeserializationContext): Any {
        return JsonNullable.undefined<Any>()
    }

    override fun referenceValue(contents: Any): JsonNullable<Any> {
        return JsonNullable.of(contents)
    }

    override fun getReferenced(reference: JsonNullable<Any>): Any {
        return reference.get()
    }

    override fun updateReference(reference: JsonNullable<Any>, contents: Any): JsonNullable<Any> {
        return JsonNullable.of(contents)
    }

    override fun supportsUpdate(config: DeserializationConfig): Boolean {
        // yes; regardless of value deserializer reference itself may be updated
        return java.lang.Boolean.TRUE
    }

    companion object {
        private const val serialVersionUID = 1L
    }
}