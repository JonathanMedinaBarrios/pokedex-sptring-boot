package com.springboot.api.service;

import java.util.List;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import com.springboot.api.dto.PokemonResponse;


public interface PokeApiService {
	
	public Page<PokemonResponse> getAll(Pageable pageable);
	public PokemonResponse getPokemon(Long id);
}
