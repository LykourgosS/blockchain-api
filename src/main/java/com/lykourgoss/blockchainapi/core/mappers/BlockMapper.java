package com.lykourgoss.blockchainapi.core.mappers;

import com.lykourgoss.blockchainapi.core.Block;
import com.lykourgoss.blockchainapi.core.BlockDto;
import com.lykourgoss.blockchainapi.core.helpers.serializer.JsonSerializer;
import com.lykourgoss.blockchainapi.core.helpers.serializer.Serializer;
import com.lykourgoss.blockchainapi.core.mappers.custom.DataToStringDataMapper;
import com.lykourgoss.blockchainapi.core.mappers.custom.StringDataToDataMapper;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.springframework.beans.factory.annotation.Autowired;

@Mapper(componentModel = MappingConstants.ComponentModel.SPRING)
public abstract class BlockMapper implements GenericMapper<BlockDto, Block> {
    @Autowired
    protected JsonSerializer serializer;

    @Mapping(source = "stringData", target = "data", qualifiedBy = StringDataToDataMapper.class)
    @Override
    public abstract Block toApplicationEntity(BlockDto blockDto);

    @StringDataToDataMapper
    public Object stringDataToData(String string) {
        return serializer.deserialize(string);
    }

    @Mapping(source = "data", target = "stringData", qualifiedBy = DataToStringDataMapper.class)
    @Override
    public abstract BlockDto toDatabaseEntity(Block block);

    @DataToStringDataMapper
    public String dataToStringData(Object data) {
        return serializer.serialize(data);
    }
}
