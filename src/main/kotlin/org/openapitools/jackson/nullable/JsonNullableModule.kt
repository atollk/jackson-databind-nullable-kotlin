package org.openapitools.jackson.nullable

import com.fasterxml.jackson.core.Version
import com.fasterxml.jackson.core.json.PackageVersion
import com.fasterxml.jackson.databind.Module

class JsonNullableModule : Module() {
    private val NAME = "JsonNullableModule"
    override fun setupModule(context: SetupContext) {
        context.addSerializers(JsonNullableSerializers())
        context.addDeserializers(JsonNullableDeserializers())
        // Modify type info for JsonNullable
        context.addTypeModifier(JsonNullableTypeModifier())
        context.addBeanSerializerModifier(JsonNullableBeanSerializerModifier())
    }

    override fun version(): Version {
        return PackageVersion.VERSION
    }

    override fun hashCode(): Int {
        return NAME.hashCode()
    }

    override fun equals(o: Any?): Boolean {
        return this === o
    }

    override fun getModuleName(): String {
        return NAME
    }
}