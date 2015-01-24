package com.LeagueOfStones.entities;

public class Card {
	public int Id;
	public int AttackDamage;
	public int Health;
	public int ManaCosts;
	public Card(int id, int attackDamage, int health, int manaCosts) {
		Id = id;
		AttackDamage = attackDamage;
		Health = health;
		ManaCosts = manaCosts;
	}
}
