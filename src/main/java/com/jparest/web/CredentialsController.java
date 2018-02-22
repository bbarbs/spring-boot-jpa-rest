package com.jparest.web;

import com.jparest.mapper.CredentialsMapper;
import com.jparest.model.Credentials;
import com.jparest.model.dto.CredentialsDto;
import com.jparest.rest.ApiResponse;
import com.jparest.rest.patch.Patch;
import com.jparest.service.CredentialService;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;
import java.util.List;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

@RestController
@RequestMapping(value = "/v1/api")
public class CredentialsController {

    @Autowired
    CredentialService credentialService;

    @Autowired
    CredentialsMapper mapper;

    /**
     * Get customer credentials.
     *
     * @param customerId
     * @return
     */
    @ApiOperation(
            value = "Get customer credentials"
    )
    @GetMapping(
            value = "/customers/{customerId}/credentials",
            produces = APPLICATION_JSON_VALUE
    )
    public List<CredentialsDto> getCustomerCredentials(@ApiParam(value = "Customer Id", required = true) @PathVariable(name = "customerId") Long customerId) {
        List<Credentials> credentials = this.credentialService.getCustomerCredentials(customerId);
        return this.mapper.mapToCredentialsDtoList(credentials);
    }

    /**
     * Add customer credentials.
     *
     * @param customerId
     * @param dto
     * @return
     */
    @ApiOperation(
            value = "Add customer credentials"
    )
    @PostMapping(
            value = "/customers/{customerId}/credentials",
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<CredentialsDto> addCustomerCredentials(
            @ApiParam(value = "Customer Id", required = true) @PathVariable(name = "customerId") Long customerId,
            @ApiParam(value = "Credentials", required = true) @RequestBody CredentialsDto dto) {
        Credentials credentials = this.credentialService.addCustomerCredentials(customerId, this.mapper.mapToCredentials(dto));
        return new ApiResponse<>(
                HttpStatus.CREATED.value(),
                HttpStatus.CREATED,
                Arrays.asList(this.mapper.mapToCredentialsDto(credentials))
        );
    }

    /**
     * Update customer credentials.
     *
     * @param credentialId
     * @param dto
     * @return
     */
    @ApiOperation(
            value = "Update customer credentials"
    )
    @PutMapping(
            value = "/credentials/{credentialId}",
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<CredentialsDto> updateCustomerCredentials(
            @ApiParam(value = "Credential Id", required = true) @PathVariable(name = "credentialId") Long credentialId,
            @ApiParam(value = "Credentials", required = true) @RequestBody CredentialsDto dto) {
        Credentials credentials = this.credentialService.updateCustomerCredentials(credentialId, this.mapper.mapToCredentials(dto));
        return new ApiResponse<>(
                HttpStatus.CREATED.value(),
                HttpStatus.CREATED,
                Arrays.asList(this.mapper.mapToCredentialsDto(credentials))
        );
    }

    /**
     * Patch customer credentials.
     *
     * @param credentialId
     * @param patch
     * @return
     */
    @ApiOperation(
            value = "Patch customer credentials"
    )
    @PatchMapping(
            value = "/credentials/{credentialId}",
            consumes = APPLICATION_JSON_VALUE,
            produces = APPLICATION_JSON_VALUE
    )
    @ResponseStatus(HttpStatus.CREATED)
    public ApiResponse<CredentialsDto> patchCustomerCredentials(
            @ApiParam(value = "Credential Id", required = true) @PathVariable(name = "credentialId") Long credentialId,
            @ApiParam(value = "Credentials details", required = true) @RequestBody Patch patch) {
        Credentials credentials = this.credentialService.patchCustomerCredentials(credentialId, patch);
        return new ApiResponse<>(
                HttpStatus.CREATED.value(),
                HttpStatus.CREATED,
                Arrays.asList(this.mapper.mapToCredentialsDto(credentials))
        );
    }
}
