package org.openapitools.jackson.nullable

import com.fasterxml.jackson.databind.JavaType
import com.fasterxml.jackson.databind.type.ReferenceType
import com.fasterxml.jackson.databind.type.TypeBindings
import com.fasterxml.jackson.databind.type.TypeFactory
import com.fasterxml.jackson.databind.type.TypeModifier
import java.lang.reflect.Type

class JsonNullableTypeModifier : TypeModifier() {
    override fun modifyType(type: JavaType, jdkType: Type, bindings: TypeBindings, typeFactory: TypeFactory): JavaType {
        if (type.isReferenceType || type.isContainerType) {
            return type
        }
        val raw = type.rawClass
        val refType: JavaType
        refType = if (raw == JsonNullable::class.java) {
            type.containedTypeOrUnknown(0)
        } else {
            return type
        }
        return ReferenceType.upgradeFrom(type, refType)
    }
}