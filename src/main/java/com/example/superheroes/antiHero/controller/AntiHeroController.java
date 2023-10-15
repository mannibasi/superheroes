package com.example.superheroes.antiHero.controller;

import com.example.superheroes.antiHero.dto.AntiHeroDto;
import com.example.superheroes.antiHero.entity.AntiHeroEntity;
import com.example.superheroes.antiHero.service.AntiHeroService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import javax.validation.Valid;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@AllArgsConstructor
@RestController
@RequestMapping("api/v1/anti-heroes")
public class AntiHeroController {
    private final AntiHeroService service;
    private final ModelMapper mapper;

    @GetMapping
    public List<AntiHeroDto> getAntiHeroes() {
        var antiHeroList = StreamSupport
                .stream(service.findAllAntiHeroes().spliterator(),
                        false)
                .collect(Collectors.toList());
        return antiHeroList
                .stream()
                .map(this::convertToDto)
                .collect(Collectors.toList());

    }

    @GetMapping("/{id}")
    public AntiHeroDto getAntiHeroById(@PathVariable("id") UUID id) {
        return convertToDto(service.findAntiHeroById(id));
    }

    @PostMapping
    public AntiHeroDto postAntiHero(@RequestBody @Valid AntiHeroDto dto) {
        var entity = convertToEntity(dto);
        var antiHero = service.addAntiHero(entity);
        return convertToDto(antiHero);
    }

    @PutMapping("/{id}")
    public void putAntiHero(@PathVariable("id") UUID id, @RequestBody @Valid AntiHeroDto dto) {
        if(!id.equals(dto.getId())) throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "id does not match");
        var entity = convertToEntity(dto);
        service.updateAntiHero(id, entity);
    }

    @DeleteMapping("/{id}")
    public void deleteAntiHeroById(@PathVariable("id") UUID id) {
        service.removeAntiHeroById(id);
    }

    private AntiHeroDto convertToDto(AntiHeroEntity entity) {
        return mapper.map(entity, AntiHeroDto.class);
    }

    private AntiHeroEntity convertToEntity(AntiHeroDto dto) {
        return mapper.map(dto, AntiHeroEntity.class);
    }
}
