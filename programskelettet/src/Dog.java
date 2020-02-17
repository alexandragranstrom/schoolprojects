//Alexandra Granström, algr5265
public class Dog implements Comparable<Dog> {
//definera klass variablarna (egenskaperna för klassen hund

    private String name;
    private int age;
    private String breed;
    private int weight;
    private boolean upForAuction = false;
    private User user; //skapa en variabel för ägare


    //definera konstruktorn

    public Dog(String name, String breed, int age, int weight) {
        this.name = name;
        this.age = age;
        this.breed = breed.toLowerCase();
        this.weight = weight;

    }

    public void addAge() {
        this.age += 1;
    }

    public String getName() {
        return name;
    }

    public int getAge() {
        return age;
    }

    public String getBreed() {
        return breed;
    }

    public int getWeight() {
        return weight;
    }

    public double getTailLength() {
        if (breed.equals("tax") || breed.equals("dachshund")) {
            return 3.7;
        } else {
            return (age * weight) / 10.0;
        }
    }

    public void setUser(User user) {
        this.user = user;
    }

    public User getUser() {
        return user;
    }

    public boolean isUpForAuction() {
        return upForAuction;
    }

    public void setUpForAuction(boolean value) {
        upForAuction = value;
    }

    public int compareTo(Dog d) {
        if (getTailLength() < d.getTailLength())
            return -1;
        else if (getTailLength() > d.getTailLength())
            return 1;
        else
            return name.compareTo(d.getName());
    }

    @Override
    public String toString() {
        if (user == null) {
            return String.format("%s (%s, %d years, %d kg, %.1f cm tail)", name, breed, age, weight, getTailLength());
        }
        return String.format("%s (%s, %d years, %d kg, %.1f cm tail, owned by %s)", name, breed, age, weight, getTailLength(), user.getName());

    }
}