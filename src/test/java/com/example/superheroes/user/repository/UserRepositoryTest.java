package com.example.superheroes.user.repository;

import com.example.superheroes.antiHero.entity.UserEntity;
import com.example.superheroes.antiHero.repository.UserRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

@DataJpaTest
public class UserRepositoryTest {
    @Autowired
    private UserRepository underTest;

    @Test
    public void itShouldCheckWhenUserEmailExists() {
        String email = "test@test.com";
        UserEntity userEntity = new UserEntity(email, "3142523");
        underTest.save(userEntity);
        boolean expected = underTest.selectExistsEmail(email);
        assertThat(expected).isTrue();
    }
}
