package org.openapitools.jackson.nullable

import com.fasterxml.jackson.databind.BeanProperty
import com.fasterxml.jackson.databind.JsonSerializer
import com.fasterxml.jackson.databind.jsontype.TypeSerializer
import com.fasterxml.jackson.databind.ser.std.ReferenceTypeSerializer
import com.fasterxml.jackson.databind.type.ReferenceType
import com.fasterxml.jackson.databind.util.NameTransformer

class JsonNullableSerializer :
    ReferenceTypeSerializer<JsonNullable<*>> {
    /*
    / **********************************************************
    / * Constructors, factory methods
    / **********************************************************
     */
    constructor(
        fullType: ReferenceType, staticTyping: Boolean,
        vts: TypeSerializer, ser: JsonSerializer<Any?>
    ) : super(fullType, staticTyping, vts, ser)

    protected constructor(
        base: JsonNullableSerializer?, property: BeanProperty?,
        vts: TypeSerializer?, valueSer: JsonSerializer<*>?, unwrapper: NameTransformer?,
        suppressableValue: Any?
    ) : super(
        base, property, vts, valueSer, unwrapper,
        suppressableValue, false
    )

    override fun withResolved(
        prop: BeanProperty,
        vts: TypeSerializer, valueSer: JsonSerializer<*>?,
        unwrapper: NameTransformer
    ): ReferenceTypeSerializer<JsonNullable<*>> {
        return JsonNullableSerializer(
            this, prop, vts, valueSer, unwrapper,
            _suppressableValue
        )
    }

    override fun withContentInclusion(
        suppressableValue: Any,
        suppressNulls: Boolean
    ): ReferenceTypeSerializer<JsonNullable<*>> {
        return JsonNullableSerializer(
            this, _property, _valueTypeSerializer,
            _valueSerializer, _unwrapper,
            suppressableValue
        )
    }

    /*
    / **********************************************************
    / * Abstract method impls
    / **********************************************************
     */
    override fun _isValuePresent(value: JsonNullable<*>): Boolean {
        return value.isPresent
    }

    override fun _getReferenced(value: JsonNullable<*>): Any {
        return value.get()!!
    }

    override fun _getReferencedIfPresent(value: JsonNullable<*>): Any {
        return (if (value.isPresent) value.get() else null)!!
    }

    companion object {
        private const val serialVersionUID = 1L
    }
}