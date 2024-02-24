package com.example.superheroes.antiHero.service;

import com.example.superheroes.antiHero.entity.AntiHeroEntity;
import com.example.superheroes.antiHero.repository.AntiHeroRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class AntiHeroServiceTest {
    @Mock
    private AntiHeroRepository antiHeroRepository;
    @InjectMocks
    AntiHeroService underTest;

    @Test
    void  canAddAntiHero() {
        // Given
        AntiHeroEntity antiHero = new AntiHeroEntity(
                UUID.randomUUID(),
                "Venom",
                "Lakandula",
                "Tondo",
                "Datu of Tondo",
                new SimpleDateFormat("dd-MM-yyyy HH:mm:ss z").format(new Date())
        );
        // When
        underTest.addAntiHero(antiHero);
        // Then
        ArgumentCaptor<AntiHeroEntity> antiHeroDtoArgumentCaptor = ArgumentCaptor.forClass(AntiHeroEntity.class);
        verify(antiHeroRepository).save(antiHeroDtoArgumentCaptor.capture());
        AntiHeroEntity capturedAntiHero = antiHeroDtoArgumentCaptor.getValue();
        assertThat(capturedAntiHero).isEqualTo(antiHero);
    }
}
