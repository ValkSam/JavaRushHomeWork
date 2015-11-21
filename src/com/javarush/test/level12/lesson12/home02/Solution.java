
public class Solution
{

    public static void main(String[] args)
    {
        Cat cat = new Cat();                //создается объект типа Cat
        cat.setName("Я - кот");             //работает Cat.setName. Результат в Cat.name

        System.out.println(cat.getName()); //результат "Pet"
    }

}

class Pet
{
    private String name = "Pet";
    public String getName() {return name;} //берется из поля Pet.name ("Pet"), что на первый взгляд ожидаемо.
    //Но вот, что не понятно: Pet.name - не static поле, т.е. без объекта не существует. Но в тоже время, не создав объект
    //класса Pet (создан объект класса Cat), значение поля Pet.name все таки получили. Как это объясняется?
}

class Cat extends Pet
{
    private String name = "Cat";
    public void setName(String name){this.name = name;}
}

/*

*/