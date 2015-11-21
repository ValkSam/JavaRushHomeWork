package com.javarush.test.level21.lesson16.big01;

import java.util.ArrayList;

/**
 * Created by Valk on 15.03.15.
 */
public class Hippodrome {
    private static ArrayList<Horse> horses = new ArrayList<>();

    public ArrayList<Horse> getHorses(){return horses;}

    public static Hippodrome game;

    public void move(){
        for (Horse horse : horses){
            horse.move();
        }
    }
    public void print(){
        for (Horse horse : horses){
            horse.print();
        }
        System.out.println();
        System.out.println();
    }
    public void run(){
        for (int i = 1; i<=100; i++){
            move();
            print();
            try {Thread.sleep(500);} catch (InterruptedException e){}
        }
    }

    public Horse getWinner(){
        if (horses.size() == 0) return null;
        double maxDist = horses.get(0).getDistance();
        Horse winner = horses.get(0);
        for (int i = 1; i<horses.size(); i++){
            if (horses.get(i).getDistance() > maxDist) {
                maxDist = horses.get(i).getDistance();
                winner = horses.get(i);
            }
        }
        return winner;

    }
    public void printWinner() {
        Horse winner = getWinner();
        if (winner != null) {
            System.out.println("Winner is "+winner.getName()+"!");
        }
    }

    public static void main(String[] args) {
        game = new Hippodrome();
        game.horses.add(new Horse("horse 1", 3, 0));
        game.horses.add(new Horse("horse 2", 3, 0));
        game.horses.add(new Horse("horse 3", 3, 0));
        game.run();
        game.printWinner();
    }
}
