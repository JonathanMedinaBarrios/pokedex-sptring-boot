package com.springboot.api.dto;

public class PokemonAbility {
	
	private String name; 
	private boolean is_hidden;
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public boolean isIs_hidden() {
		return is_hidden;
	}
	public void setIs_hidden(boolean is_hidden) {
		this.is_hidden = is_hidden;
	} 
	
}
