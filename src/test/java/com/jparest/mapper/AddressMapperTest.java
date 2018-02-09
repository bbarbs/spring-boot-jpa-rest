package com.jparest.mapper;

import com.jparest.model.Address;
import com.jparest.model.dto.AddressDto;
import com.jparest.model.enums.AddressEnum;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
public class AddressMapperTest {

    private Logger logger = LoggerFactory.getLogger(getClass());

    @InjectMocks
    AddressMapperImpl mapper;

    @Test
    public void testShouldMapEntityToDTO() {
        Address address = new Address();
        address.setId(1L);
        address.setType(AddressEnum.PRIMARY);
        address.setAddress("Test");
        // Map
        AddressDto dto = this.mapper.mapToAddressDto(address);
        assertNotNull(dto);
        assertThat(dto.getAddress()).isEqualTo(address.getAddress());
        assertThat(dto.getAddressType()).isEqualTo(address.getType());
    }

    @Test
    public void testShouldMapDTOtoEntity() {
        AddressDto dto = new AddressDto();
        dto.setAddressId(1L);
        dto.setAddressType(AddressEnum.PRIMARY);
        dto.setAddress("Test");
        Address address = this.mapper.mapToAddress(dto);
        assertNotNull(address);
        assertThat(address.getAddress()).isEqualTo(dto.getAddress());
        assertThat(address.getType()).isEqualTo(dto.getAddressType());
    }

    @Test
    public void testShouldMapEntitiesToDTOs() {
        Address address = new Address();
        address.setId(1L);
        address.setType(AddressEnum.PRIMARY);
        address.setAddress("Test");
        List<AddressDto> dtos = this.mapper.mapToAddressDtoList(Arrays.asList(address));
        assertNotNull(dtos);
        dtos.stream().forEach(dto -> {
            assertThat(dto.getAddress()).isEqualTo(address.getAddress());
            assertThat(dto.getAddressType()).isEqualTo(address.getType());
        });
    }

    @Test
    public void testShouldMapDTOsToEntities() {
        AddressDto dto = new AddressDto();
        dto.setAddressId(1L);
        dto.setAddressType(AddressEnum.PRIMARY);
        dto.setAddress("Test");
        List<Address> addresses = this.mapper.mapToAddressList(Arrays.asList(dto));
        assertNotNull(addresses);
        addresses.stream().forEach(address -> {
            assertThat(dto.getAddress()).isEqualTo(address.getAddress());
            assertThat(dto.getAddressType()).isEqualTo(address.getType());
        });
    }
}
