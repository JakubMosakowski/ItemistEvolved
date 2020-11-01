package com.jakmos.itemistevolved.domain.mapper

import co.windly.limbo.utility.mapping.CleanCodeMapper
import com.jakmos.itemistevolved.domain.model.Checklist
import com.jakmos.itemistevolved.network.dto.ChecklistDto
import com.jakmos.itemistevolved.persistence.database.entity.ChecklistEntity
import com.jakmos.itemistevolved.persistence.database.entity.ChecklistView
import org.mapstruct.Mapper
import org.mapstruct.Mapping
import org.mapstruct.Mappings
import org.mapstruct.NullValueCheckStrategy
import org.mapstruct.NullValueMappingStrategy
import org.mapstruct.ReportingPolicy.IGNORE

@Mapper(
  nullValueMappingStrategy = NullValueMappingStrategy.RETURN_DEFAULT,
  nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
  unmappedTargetPolicy = IGNORE
)
abstract class ChecklistMapper : CleanCodeMapper<ChecklistDto, ChecklistEntity, Checklist> {

  //region Entity -> Domain

  @Mappings(value = [
    Mapping(source = "checklistEntity.id", target = "id"),
    Mapping(source = "checklistEntity.name", target = "name"),
    Mapping(source = "checklistEntity.imageUrl", target = "imageUrl"),
    Mapping(source = "checklistEntity.createdAt", target = "createdAt"),
    Mapping(source = "subsections", target = "subsections")
  ])
  abstract fun mapViewToDomain(view: ChecklistView): Checklist

  //endregion

  //region View -> Domain

  fun mapViewListToDomainList(views: List<ChecklistView>): List<Checklist> =
    views
      .map(::mapViewToDomain)

  //endregion
}
