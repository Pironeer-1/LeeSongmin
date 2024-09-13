package org.example;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class Game {
    private static final Scanner sc = new Scanner(System.in);
    private final int statusPoints = 13;
    private List<Player> playerList;
    private Enemy enemy;

    public Game() {
        this.playerList = new ArrayList<>();
    }

    public void setPlayers() {
        System.out.print("플레이어 인원을 입력하세요: ");
        int playerCount = sc.nextInt();

        for (int i = 0; i < playerCount; i++) {
            Player player = new Player();
            player.statusSet(statusPoints);
            playerList.add(player);
        }
    }

    public void setEnemy() {
        enemy = new Enemy(playerList.size());
    }

    public boolean turnCheck() {
        playerList.removeIf(player -> player.getHp() <= 0);
        return playerList.size() > 0 && enemy.getHp() > 0;
    }

    public Player selectTargetPlayer() {
        Random rand = new Random();
        return playerList.get(rand.nextInt(playerList.size()));
    }

    public void startGame() {
        setPlayers();
        setEnemy();

        while (turnCheck()) {
            for (int i = 0; i < playerList.size(); i++) {
                Player player = playerList.get(i);
                player.attack(enemy, i);

                if (enemy.getHp() <= 0) {
                    System.out.println("축하합니다! 적을 처치했습니다!");
                    return;
                }
            }

            if (turnCheck()) {
                Player targetPlayer = selectTargetPlayer();
                int targetIndex = playerList.indexOf(targetPlayer);
                enemy.attack(targetPlayer, targetIndex);
            } else {
                break;
            }
        }

        if (enemy.getHp() <= 0) {
            System.out.println("축하합니다! 승리하셨습니다!");
        } else {
            System.out.println("패배하셨습니다.");
        }
    }
}

