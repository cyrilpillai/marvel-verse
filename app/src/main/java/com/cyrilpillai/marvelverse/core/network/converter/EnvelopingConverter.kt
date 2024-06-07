package com.cyrilpillai.marvelverse.core.network.converter

import com.cyrilpillai.marvelverse.core.network.converter.model.NetworkResponseEnvelope
import com.squareup.moshi.Types
import okhttp3.ResponseBody
import retrofit2.Converter
import retrofit2.Retrofit
import java.lang.reflect.Type

class EnvelopingConverter : Converter.Factory() {
    override fun responseBodyConverter(
        type: Type,
        annotations: Array<Annotation>,
        retrofit: Retrofit
    ): Converter<ResponseBody, *> {
        val envelopedType = Types.newParameterizedType(NetworkResponseEnvelope::class.java, type)

        val delegate: Converter<ResponseBody, NetworkResponseEnvelope<Any>>? =
            retrofit.nextResponseBodyConverter(this, envelopedType, annotations)

        return Converter { delegate?.convert(it)?.data?.results }
    }
}