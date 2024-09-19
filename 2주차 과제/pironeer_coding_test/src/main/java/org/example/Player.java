package org.example;

import java.util.Random;
import java.util.Scanner;

public class Player {
    private int hp;
    private int ad;
    private int ap;
    private static final int HP_INCREMENT = 3;
    private static final Scanner sc = new Scanner(System.in);

    public Player() {
        this.hp = 50;
        this.ad = 10;
        this.ap = 5;
    }

    public void statusSet(int points) {
        System.out.println(points + "만큼의 스탯을 추가합니다.");
        System.out.println("플레이어의 기본 스탯은 체력:50, 공격력: 10, 마법력: 5 입니다.");

        int hpPoints, adPoints, apPoints;
        while (true) {
            try {
                System.out.print("체력, 공격력, 마법력 포인트 순서로 공백을 구분하여 입력하세요: ");
                System.out.println("(1 포인트 당 체력 = 3, 공격력 = 1, 마법력 = 1 증가)");
                hpPoints = sc.nextInt();
                adPoints = sc.nextInt();
                apPoints = sc.nextInt();

                if (hpPoints + adPoints + apPoints == points) {
                    this.hp += hpPoints * HP_INCREMENT;
                    this.ad += adPoints;
                    this.ap += apPoints;
                    System.out.println("플레이어의 최종 스탯은 체력: " + this.hp + ", 공격력: " + this.ad + ", 마법력: " + this.ap);
                    break;
                } else {
                    System.out.println("입력한 포인트의 합이 " + points + "과 일치하지 않습니다. 다시 입력해주세요.");
                }
            } catch (Exception e) {
                System.out.println("잘못된 입력입니다. 다시 시도하세요.");
                sc.nextLine();
            }
        }
    }

    public void decreaseHp(int damage) {
        this.hp = Math.max(this.hp - damage, 0);
    }

    public void checkStatus(Enemy enemy) {
        System.out.println("현재 체력: " + this.hp + ", 공격력: " + this.ad + ", 마법력: " + this.ap);
        System.out.println("적 체력: " + enemy.getHp() + ", 공격력: " + enemy.getAd() + ", 방어력: " + enemy.getAdDefence());
    }

    public void basicAttack(Enemy enemy) {
        int critChance = new Random().nextInt(10);
        int damage;
        if (critChance <= 2) {
            damage = (this.ad - enemy.getAdDefence()) * 2;
            System.out.println("치명타! " + damage + "의 데미지를 주었습니다.");
        } else {
            damage = this.ad - enemy.getAdDefence();
            System.out.println("기본 공격으로 " + damage + "의 데미지를 주었습니다.");
        }
        enemy.decreaseHp(damage);
    }

    public void magicAttack(Enemy enemy) {
        int damage = (this.ap * 2) - enemy.getApDefence();
        enemy.decreaseHp(damage);
        System.out.println("마법 공격으로 " + damage + "의 데미지를 주었습니다.");
    }

    public void heal() {
        int healAmount = new Random().nextInt(6) + 5;
        this.hp += healAmount;
        System.out.println(healAmount + "만큼 체력을 회복했습니다. 현재 체력: " + this.hp);
    }

    public void attack(Enemy enemy, int playerIndex) {
        while (true) {
            System.out.println((playerIndex + 1) + "번 플레이어의 차례입니다. 행동을 선택하세요.");
            System.out.println("1: 스테이터스 확인 + 일반 공격, 2: 기본 공격, 3: 마법 공격, 4: 체력 회복");
            int choice = sc.nextInt();

            switch (choice) {
                case 1:
                    checkStatus(enemy);
                    basicAttack(enemy);
                    return;
                case 2:
                    basicAttack(enemy);
                    return;
                case 3:
                    magicAttack(enemy);
                    return;
                case 4:
                    heal();
                    return;
                default:
                    System.out.println("올바른 선택을 해주세요.");
            }
        }
    }

    public int getHp() {
        return hp;
    }

    public int getAd() {
        return ad;
    }
}
