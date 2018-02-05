package com.jparest.repository;

import com.jparest.model.Credentials;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.Assert.assertNotNull;

@RunWith(SpringRunner.class)
@DataJpaTest
public class CredentialRepositoryTest {

    @Autowired
    TestEntityManager entityManager;

    @Autowired
    CredentialsRepository credentialsRepository;

    @Test
    public void testShouldFindCredentialsByUsername() {
        Credentials credentials = new Credentials();
        credentials.setUsername("user");
        credentials.setPassword("pass");
        this.entityManager.persist(credentials);
        Credentials result = this.credentialsRepository.findByUsername("user");
        assertNotNull(result);
        assertThat(result.getPassword()).isEqualTo(credentials.getPassword());
    }
}
