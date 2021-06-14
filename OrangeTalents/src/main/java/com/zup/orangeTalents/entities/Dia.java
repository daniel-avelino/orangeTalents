package com.zup.orangeTalents.entities;

public enum Dia {
	SEGUNDA_FEIRA(1), 
	TERÇA_FEIRA(2), 
	QUARTA_FEIRA(3),
	QUINTA_FEIRA(4),
	SEXTA_FEIRA(5);
	
	Dia(int value) {
	}	
	
	public int getValue() {
		return ordinal() + 1;
	}
}
