package com.javarush.test.level14.lesson06.home01;

class UkrainianHen extends Hen {
    int getCountOfEggsPerMonth(){
        return 50;
    };
    public String getDescription(){
        return super.getDescription()+" Моя страна - "+UKRAINE+". Я несу "+this.getCountOfEggsPerMonth()+" яиц в месяц.";
    }
}