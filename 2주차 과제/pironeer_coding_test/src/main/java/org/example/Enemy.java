package org.example;

import java.util.Random;

public class Enemy {
    private final int maxHp;
    private int hp;
    private final int ad;
    private final int adDefence;
    private final int apDefence;

    public Enemy(int playerCount) {
        this.maxHp = 100 * playerCount;
        this.hp = maxHp;
        this.ad = 25;
        this.adDefence = 7;
        this.apDefence = 7;
    }

    public void decreaseHp(int damage) {
        this.hp = Math.max(this.hp - damage, 0);
    }

    public void basicAttack(Player player, int playerIndex) {
        int damage = this.ad - player.getAd();
        player.decreaseHp(damage);
        System.out.println("적의 차례입니다.");
        System.out.println((playerIndex + 1) + "번 플레이어에게 " + damage + "의 데미지를 주었습니다. 남은 체력: " + player.getHp());
    }

    public void heal() {
        if (this.hp < this.maxHp) {
            this.hp += 7;
            System.out.println("적의 차례입니다.");
            System.out.println("적이 7만큼 체력을 회복했습니다. 현재 체력: " + this.hp);
        } else {
            System.out.println("적의 차례입니다.");
            System.out.println("적의 체력이 이미 최대입니다.");
        }
    }

    public void attack(Player player, int playerIndex) {
        Random rand = new Random();
        int action = rand.nextInt(10);
        System.out.println("적의 차례입니다.");
        if (action == 0) {
            System.out.println("적은 섣불리 움직이지 못하고 있습니다.");
        } else if (action <= 7) {
            basicAttack(player, playerIndex);
        } else {
            heal();
        }
    }

    public int getHp() {
        return hp;
    }

    public int getAd() {
        return ad;
    }

    public int getAdDefence() {
        return adDefence;
    }

    public int getApDefence() {
        return apDefence;
    }
}
