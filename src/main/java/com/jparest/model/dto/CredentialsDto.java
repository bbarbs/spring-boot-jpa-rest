package com.jparest.model.dto;

import io.swagger.annotations.ApiModelProperty;

public class CredentialsDto {

    @ApiModelProperty(hidden = true)
    private Long credentialId;

    @ApiModelProperty(required = true)
    private String username;

    @ApiModelProperty(required = true)
    private String password;

    public CredentialsDto() {
    }

    public CredentialsDto(Long credentialId, String username, String password) {
        this.credentialId = credentialId;
        this.username = username;
        this.password = password;
    }

    public Long getCredentialId() {
        return credentialId;
    }

    public void setCredentialId(Long credentialId) {
        this.credentialId = credentialId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}
