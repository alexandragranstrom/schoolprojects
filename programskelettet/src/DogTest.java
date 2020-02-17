import java.util.ArrayList;

public class DogTest {
    public static void main (String[] args){
        Dog dog1 = new Dog("Alex", "Dalmatin", 12, 32 );
        Dog dog2 = new Dog("Teo", "Tax", 11, 80);

        System.out.println(dog1 + " , " + dog2);

        System.out.println(dog2);

        ArrayList<String> list = new ArrayList<>();

        list.add("Alex");
        list.add("Klara");
        list.add("Hanna");

        System.out.println(list);
    }

}
