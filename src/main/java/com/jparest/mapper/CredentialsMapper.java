package com.jparest.mapper;

import com.jparest.model.Credentials;
import com.jparest.model.dto.CredentialsDto;
import org.mapstruct.*;

import java.util.List;

@Mapper(
        componentModel = "spring",
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface CredentialsMapper {

    @Mappings({
            @Mapping(target = "id", source = "credentialId")
    })
    Credentials mapToCredentials(CredentialsDto source);

    @IterableMapping(elementTargetType = Credentials.class)
    List<Credentials> mapToCredentialsList(List<CredentialsDto> sources);

    @Mappings({
            @Mapping(target = "credentialId", source = "id")
    })
    CredentialsDto mapToCredentialsDto(Credentials source);

    @IterableMapping(elementTargetType = CredentialsDto.class)
    List<CredentialsDto> mapToCredentialsDtoList(List<Credentials> sources);
}
