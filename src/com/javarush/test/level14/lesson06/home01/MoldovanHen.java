package com.javarush.test.level14.lesson06.home01;

class MoldovanHen extends Hen {
    int getCountOfEggsPerMonth(){
        return 360;
    };
    public String getDescription(){
        return super.getDescription()+" Моя страна - "+MOLDOVA+". Я несу "+this.getCountOfEggsPerMonth()+" яиц в месяц.";
    }
}