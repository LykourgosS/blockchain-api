package com.lykourgoss.blockchainapi.core.mappers;

import org.mapstruct.MappingTarget;

import java.util.List;

public interface GenericMapper<TDatabaseEntity, TApplicationEntity> {
    TApplicationEntity toApplicationEntity(TDatabaseEntity databaseEntity);

    List<TApplicationEntity> toApplicationEntityList(List<TDatabaseEntity> databaseEntities);

    TDatabaseEntity toDatabaseEntity(TApplicationEntity applicationEntity);

    List<TDatabaseEntity> toDatabaseEntityList(List<TApplicationEntity> applicationEntities);

    void updateFromApplicationEntity(TApplicationEntity applicationEntity, @MappingTarget TDatabaseEntity databaseEntity);

    void updateFromDatabaseEntity(TDatabaseEntity databaseEntity, @MappingTarget TApplicationEntity applicationEntity);
}
