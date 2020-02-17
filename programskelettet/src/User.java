//Alexandra Granström, algr5265
import java.util.ArrayList;

public class User {

    private String name;
   // private ArrayList<Dog> dogs = new ArrayList<>(); //skapa en lista för ägare av hundar
    private Dog[] userDogList = new Dog[0];

    public User(String name){
        this.name = name;

    }

    public String getName(){
        return name;
    }

 //   public void removeDogFromUser(Dog dog){ dogs.remove(dog);    }

    public void removeDogFromUser(Dog dog){
        Dog[] temp = new Dog[userDogList.length-1];
        for (int i = 0, k = 0; i < userDogList.length; i++) {
                if(userDogList[i].equals(dog)){
                    continue;
                }
                //lägga in resterande i den nya listan temp, k används för att veta vilken plats som är fylld
                temp[k++]=userDogList[i];
        }
        this.userDogList = temp;
    }

    public void addDog(Dog dog){  //lägga in hundar i listan
        Dog[] tempArray = userDogList;
        userDogList = new Dog[userDogList.length+1];
        System.arraycopy(tempArray, 0, userDogList,0,tempArray.length); //kopierar över temp till userDogList, måste skriva in längden av den jag kopierar över
        userDogList[userDogList.length-1] = dog;
    }
/*
    public Dog[] getDogs(){
        return userDogList;
    }
    //public ArrayList<Dog> getDogs(){ return new ArrayList<Dog>(dogs); } //skapar en kopia av listan
*/
    public Dog[] getDogs(){
        Dog[] temp = new Dog[userDogList.length];
        System.arraycopy(userDogList,0, temp,0,userDogList.length); //kopierar över userDogList till temp
        return temp;
    }

    @Override
    public String toString(){
        return String.format("%s %s", name, getDogs());
    }
}
