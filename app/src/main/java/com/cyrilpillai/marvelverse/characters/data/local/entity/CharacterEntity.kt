package com.cyrilpillai.marvelverse.characters.data.local.entity

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.cyrilpillai.marvelverse.characters.data.remote.model.CharacterResource

@Entity(tableName = "characters")
data class CharacterEntity(
    @PrimaryKey @ColumnInfo(name = "id") val id: Int,
    @ColumnInfo(name = "name") val name: String,
    @ColumnInfo(name = "description") val description: String
) {
    constructor(characterResource: CharacterResource) : this(
        id = characterResource.id,
        name = characterResource.name,
        description = characterResource.description
    )
}