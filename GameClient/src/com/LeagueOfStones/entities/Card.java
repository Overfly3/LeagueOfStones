package com.LeagueOfStones.entities;

public class Card {
	public int Id;
	public Race CardRace;
	public CardType Type;
	public int AttackDamage;
	public int Health;
	public int ManaCosts;
	public Card(int id, Race cardRace, CardType type, int attackDamage,
			int health, int manaCosts) {
		Id = id;
		CardRace = cardRace;
		Type = type;
		AttackDamage = attackDamage;
		Health = health;
		ManaCosts = manaCosts;
	}
}
