package com.springboot.api.dto;

import java.util.List;

public class PokemonResponse {
	
	private Long number; 
	private String name; 
	private String image; 
	private String type; 
	private List<PokemonAbility> abilities; 
	private String weight;
	
	
	public Long getNumber() {
		return number;
	}
	public void setNumber(Long number) {
		this.number = number;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getWeight() {
		return weight;
	}
	public void setWeight(String weight) {
		this.weight = weight;
	}
	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}
		
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}
	public List<PokemonAbility> getAbilities() {
		return abilities;
	}
	public void setAbilities(List<PokemonAbility> abilities) {
		this.abilities = abilities;
	} 
			
}
