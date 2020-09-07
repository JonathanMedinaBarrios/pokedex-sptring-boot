package com.springboot.api.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.springboot.api.dto.PokemonAbility;
import com.springboot.api.dto.PokemonResponse;
import com.springboot.api.dto.PokemonType;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;

import com.google.gson.JsonArray;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;


@Service
public class PokeApiServiceImpl implements PokeApiService {
	
    String url = "https://pokeapi.co/api/v2/pokemon/";
    String type;
    
	@Autowired
	//private PokeApiClienteRest clienteFeign; 
	private RestTemplate clienteRest;

	private HttpHeaders headers = new HttpHeaders(); 
	
	@Override
	@Cacheable("pokemons_page")
	public Page<PokemonResponse> getAll(Pageable pageable) {
			      
        List<PokemonResponse> pokemons = new ArrayList<>(); 
               
        for(int i= 1; i<=60;i++) {
        	pokemons.add(this.getPokemon(Long.parseLong(i+"")));
        }
        
        
        int start = (int) pageable.getOffset();
		int end = (start + pageable.getPageSize()) > pokemons.size() ? pokemons.size() : (start + pageable.getPageSize());
		Page<PokemonResponse> pages = new PageImpl<PokemonResponse>(pokemons.subList(start, end), pageable, pokemons.size());
        
		return pages; 
	}
	
	@Cacheable("pokemons")
	@Override
	public PokemonResponse getPokemon(Long id) { 
		
		headers.setAccept(Arrays.asList(MediaType.APPLICATION_JSON));
        headers.add("user-agent", "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/54.0.2840.99 Safari/537.36");
        HttpEntity<String> entity = new HttpEntity<String>("parameters", headers);
        Map<String, String> pathVariables = new HashMap<String, String>();
		pathVariables.put("id", id.toString());
        ResponseEntity<String> response  = clienteRest.exchange("https://pokeapi.co/api/v2/pokemon/{id}", HttpMethod.GET, entity, String.class,pathVariables);
        
        JsonObject dato_respuesta = new JsonParser().parse(response.getBody()).getAsJsonObject();
        
        PokemonResponse pokemon = new PokemonResponse();
        pokemon = parseador(dato_respuesta); 
        pokemon.setNumber(id) ;      
        
        return pokemon; 
	}
	
	public PokemonResponse parseador(JsonObject obje) {
		
		type = ""; 
		PokemonResponse pokemon = new PokemonResponse();
		pokemon.setName(obje.get("name").toString().replaceAll("\"", ""));
		pokemon.setWeight(obje.get("weight").toString());
		
				
		JsonObject sprites = obje.getAsJsonObject("sprites"); 
		JsonObject other = sprites.getAsJsonObject("other");
		JsonObject official_artwork = other.getAsJsonObject("official-artwork");
		pokemon.setImage(official_artwork.get("front_default").toString().replaceAll("\"", ""));
		
		
		List<PokemonAbility> abilities = new ArrayList<>(); 
		JsonArray json_abilities = obje.get("abilities").getAsJsonArray();
		json_abilities.forEach(item -> {
			PokemonAbility ability = new PokemonAbility(); 
			JsonObject json_ability = new JsonParser().parse(item.toString()).getAsJsonObject();
			ability.setIs_hidden(Boolean.parseBoolean( json_ability.get("is_hidden").toString()));
			JsonObject jsonAbility = json_ability.getAsJsonObject("ability");
			ability.setName(jsonAbility.get("name").toString().replaceAll("\"", ""));
			abilities.add(ability);
		});
		pokemon.setAbilities(abilities);
		
		JsonArray json_types = obje.get("types").getAsJsonArray();
		json_types.forEach(item -> {		
			JsonObject json_type = new JsonParser().parse(item.toString()).getAsJsonObject();
			JsonObject jsonType = json_type.getAsJsonObject("type");
			type += jsonType.get("name").toString().replaceAll("\"", "") +" - ";
		}); 
		pokemon.setType(type.substring(0, type.length() - 3));
		
		return pokemon; 
	}

}
