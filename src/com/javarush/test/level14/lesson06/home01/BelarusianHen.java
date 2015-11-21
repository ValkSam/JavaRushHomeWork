package com.javarush.test.level14.lesson06.home01;

class BelarusianHen extends Hen {
    int getCountOfEggsPerMonth(){
        return 70;
    };
    public String getDescription(){
        return super.getDescription()+" Моя страна - "+BELARUS+". Я несу "+this.getCountOfEggsPerMonth()+" яиц в месяц.";
    }
}
