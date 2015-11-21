package com.javarush.test.level14.lesson06.home01;

class RussianHen extends Hen {
    int getCountOfEggsPerMonth(){
        return 60;
    };
    public String getDescription(){
        return super.getDescription()+" Моя страна - "+RUSSIA+". Я несу "+this.getCountOfEggsPerMonth()+" яиц в месяц.";
    }
}
