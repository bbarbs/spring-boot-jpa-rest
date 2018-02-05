package com.jparest.mapper;

import com.jparest.model.Credentials;
import com.jparest.model.dto.CredentialsDto;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Mappings;
import org.mapstruct.ReportingPolicy;

import java.util.List;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface CredentialsMapper {

    @Mappings({
            @Mapping(target = "id", source = "credentialId")
    })
    Credentials mapDTOtoEntity(CredentialsDto source);

    @Mappings({
            @Mapping(target = "credentialId", source = "id")
    })
    CredentialsDto mapEntityToDTO(Credentials source);

    List<Credentials> mapDTOsToEntities(List<CredentialsDto> sources);

    List<CredentialsDto> mapEntitiesToDTOs(List<Credentials> sources);
}
