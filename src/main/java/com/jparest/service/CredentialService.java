package com.jparest.service;

import com.jparest.model.Credentials;
import com.jparest.rest.patch.Patch;

import java.util.List;

public interface CredentialService {

    Credentials addCustomerCredentials(Long customerId, Credentials credentials);

    Credentials updateCustomerCredentials(Long credentialsId, Credentials credentials);

    Credentials patchCustomerCredentials(Long credentialsId, Patch patch);

    List<Credentials> getCustomerCredentials(Long customerId);

    boolean isUsernameExists(String username);
}
