package com.example.superheroes.antiHero.service;

import com.example.superheroes.antiHero.entity.AntiHeroEntity;
import com.example.superheroes.antiHero.repository.AntiHeroRepository;
import com.example.superheroes.exception.NotFoundException;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class AntiHeroService {
    private final AntiHeroRepository repo;

    public AntiHeroEntity findAntiHeroById(UUID id) {
        return findOrThrow(id);
    }

    private AntiHeroEntity findOrThrow(final UUID id) {
        return repo.findById(id)
                .orElseThrow(
                        () -> new NotFoundException("Anti-hero by id " + id + " was not found")
                );
    }
}
