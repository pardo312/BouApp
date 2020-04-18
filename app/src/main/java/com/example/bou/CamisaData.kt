package com.example.bou

import com.google.firebase.database.Exclude
import com.google.firebase.database.IgnoreExtraProperties

@IgnoreExtraProperties
data class CamisaData (
    val Descripcion: String = "",
    val ID: String = "",
    val Pagado: String = "",
    val Precio: String = ""
) {

    @Exclude
    fun toMap(): Map<String, Any?> {
        return mapOf(
            "Descripcion" to Descripcion,
            "ID" to ID,
            "Pagado" to Pagado,
            "Precio" to Precio
        )
    }
}