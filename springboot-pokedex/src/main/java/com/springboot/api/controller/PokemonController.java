package com.springboot.api.controller;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;


import com.springboot.api.dto.PokemonResponse;
import com.springboot.api.service.PokeApiService;


@CrossOrigin(origins =  "*" )
@RestController
@RequestMapping("/api")
public class PokemonController {
	
	Map<String, Object> response = new HashMap<>();
	
	@Autowired
	private  PokeApiService pokeServive ;
	
	@GetMapping("/pokemons/page/{page}")
	public ResponseEntity<?>getAll(@PathVariable Integer page) {
		
		Map<String, Object> response = new HashMap<>();
		
		Page<PokemonResponse> pokemons ;
		
		try {
			pokemons = pokeServive.getAll(PageRequest.of(page, 20));
		} catch (Exception e) {
			response.put("Mensaje", "Error en realizar la consulta");
			response.put("Error", e.getMessage().concat(": ".concat(e.getMessage())));
			return new ResponseEntity<Map>(response, HttpStatus.INTERNAL_SERVER_ERROR);
		}
		response.put("Mensaje", "Consulta exitosa");
		response.put("pokemons", pokemons);
		
		return new ResponseEntity<Map>(response, HttpStatus.OK);
	}
	
	/*@GetMapping("/pokemon/{id}")
	public PokemonResponse getPokemon(@PathVariable Long id) {
		        		
		return pokeServive.getPokemon(id);
	}*/
	

}
