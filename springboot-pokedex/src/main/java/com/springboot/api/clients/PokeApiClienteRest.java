package com.springboot.api.clients;

import java.util.List;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.springboot.api.dto.PokemonResponse;

//@FeignClient(name="pokeApi", url="https://pokeapi.co/api/v2")
public interface PokeApiClienteRest {
	
	
	//@RequestMapping(method = RequestMethod.GET, value = "/pokemon")
	@GetMapping("/pokemon")
    public List<PokemonResponse> getAll();

}
