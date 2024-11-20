package com.chigayuki.minecraft.util;

public class FishEnemy {
  private String name;
  private int hp;
  private int attack;
  private int defense;
  private int speed;
  private int exp;

  public FishEnemy(String name, int hp, int attack, int defense, int speed, int exp) {
    this.name = name;
    this.hp = hp;
    this.attack = attack;
    this.defense = defense;
    this.speed = speed;
    this.exp = exp;
  }
}
