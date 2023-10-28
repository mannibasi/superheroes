package com.example.superheroes.antiHero.h2.service;

import com.example.superheroes.antiHero.entity.AntiHeroEntity;
import com.example.superheroes.antiHero.repository.AntiHeroRepository;
import com.example.superheroes.antiHero.service.AntiHeroService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.assertj.core.api.Assertions.assertThat;

@DataJpaTest
public class AntiHeroH2ServiceTest {
    @Autowired
    private AntiHeroRepository repo;
    private AntiHeroService service;

    @BeforeEach
    public void setUp() {
        service = new AntiHeroService(repo);
    }

    @Test
    public void shouldFindAllAntiHeroes() {
        AntiHeroEntity antiHero = new AntiHeroEntity();
        antiHero.setFirstName("Eddie");
        antiHero.setLastName("Brock");
        antiHero.setHouse("MCU");
        service.addAntiHero(antiHero);
        Iterable<AntiHeroEntity> antiHeroList = service.findAllAntiHeroes();
        AntiHeroEntity savedAntiHero = antiHeroList.iterator().next();
        assertThat(savedAntiHero).isNotNull();
    }

    @Test
    public void shouldAddAntiHero() {
        AntiHeroEntity antiHero = new AntiHeroEntity();
        antiHero.setFirstName("Eddie");
        antiHero.setLastName("Brock");
        antiHero.setHouse("MCU");
        service.addAntiHero(antiHero);
        Iterable<AntiHeroEntity> antiHeroList = service.findAllAntiHeroes();
        AntiHeroEntity savedAntiHero = antiHeroList.iterator().next();
        assertThat(savedAntiHero).isEqualTo(antiHero);
    }

    @Test
    public void shouldUpdateAntiHero() {
        AntiHeroEntity antiHero = new AntiHeroEntity();
        antiHero.setFirstName("Eddie");
        antiHero.setLastName("Brock");
        antiHero.setHouse("MCU");
        AntiHeroEntity savedAntiHero = service.addAntiHero(antiHero);
        savedAntiHero.setHouse("DC");
        service.updateAntiHero(savedAntiHero.getId(),savedAntiHero);
        AntiHeroEntity foundAntiHero = service.findAntiHeroById(savedAntiHero.getId());
        assertThat(foundAntiHero.getHouse()).isEqualTo("DC");
    }
}
