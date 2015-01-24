package com.LeagueOfStones.entities;

public class Card {
	public int Id;
	public int AttackDamage;
	public int Health;
	public int ManaCosts;
	public boolean isDead = false;
	
	public Card(int id, int attackDamage, int health, int manaCosts) {
		Id = id;
		AttackDamage = attackDamage;
		Health = health;
		ManaCosts = manaCosts;
	}
	
	public void damageCard(int damage){
		Health = Health-damage;
		if(Health<=0){
			isDead = true;
		}
	}
	
}

