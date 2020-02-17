//Alexandra Granström, algr5265

import java.util.Collections;
import java.util.Scanner;
import java.util.ArrayList;


public class Program {
    private Scanner input = new Scanner(System.in);

    private ArrayList<Dog> dogList = new ArrayList<>();
    private ArrayList<User> userList = new ArrayList<>();
    private ArrayList<Auction> auctionList = new ArrayList<>();
    private int auctionNr = 1;

    private void start() {
        printProgramMenu();
        Dog dog1 = new Dog("alex", "dalmatin", 12, 32);
        Dog dog2 = new Dog("teo", "tax", 11, 80);
        Dog dog3 = new Dog("hasse", "tax", 11, 10);
        Dog dog4 = new Dog("Namn", "tax", 12, 5);
        dogList.add(dog1);
        dogList.add(dog2);
        dogList.add(dog3);
        User user1 = new User("klara");
        User user2 = new User("soffan");
        User user3 = new User("hanna");
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        dog1.setUser(user1);
        user1.addDog(dog1);
        dog2.setUser(user1);
        user1.addDog(dog2);
        doCommand();
    }
    private void printProgramMenu(){
        System.out.println("Welcome to dog register!");
        System.out.println("Available commands are:");
        System.out.println("- Register new dog");
        System.out.println("- Increase age");
        System.out.println("- List dogs");
        System.out.println("- Remove dog");
        System.out.println("- Register new user");
        System.out.println("- List users");
        System.out.println("- Remove user");
        System.out.println("- Start auction");
        System.out.println("- List auctions");
        System.out.println("- List bids");
        System.out.println("- Make bid");
        System.out.println("- Close auction");
        System.out.println("- Exit");

    }

    private String enterString(String text) {
        System.out.println(text);
        String enteredString = input.nextLine().toLowerCase().trim();
        while (enteredString.equals("")) {
            System.out.println("Error: Line can not be empty");
            System.out.println(text);
            enteredString = input.nextLine().toLowerCase().trim();
        }
        return enteredString;
    }

    private int enterInteger(String text) {
        System.out.println(text);
        int enteredInt = input.nextInt();
        input.nextLine();
        return enteredInt;
    }


    private void registerDog() {
        System.out.println("Register new dog by entering name, breed, age & weight: ");
        String dogName = enterString("Name: ");
        String dogBreed = enterString("Breed: ");
        int dogAge = enterInteger("Age: ");
        int dogWeight = enterInteger("Weight:");
        Dog theDog = new Dog(dogName, dogBreed, dogAge, dogWeight);
        dogList.add(theDog);
        System.out.println(theDog.getName() + " added to the register");

    }

    private void registerUser() {
        System.out.println("Enter name: ");
        String userName = enterString("Name: ");
        User theUser = new User(userName);
        userList.add(theUser);
        System.out.println("You registered new user");

    }

    private void listUsers() {
        if (userList.isEmpty()) {
            System.out.println("Error: no users in register");
        } else {
            String text;
            for (User u : userList) {
                text = u.getName();
                ArrayList<String> listDogs = new ArrayList<>();
                for (Dog d : u.getDogs()) { //letar i array efter hund här blir det fel...
                    listDogs.add(d.getName());
                }


                System.out.println (text + listDogs);

            }

        }
    }

    private void removeUser() {
        System.out.println("Enter name of user ");
        String nameUser = enterString("Name: ");
        for (User u : userList) {
            if (u.getName().equalsIgnoreCase(nameUser)) {
                for (Dog d : u.getDogs()) {
                    dogList.remove(d);

                }
                for (Auction a : auctionList) {
                    a.removeBid(u); //ta bort users bid.
                }
                userList.remove(u);
                System.out.println("User is removed");
                return;
            }
        }
        System.out.println("Error: no such user");
    }

    private void increaseAge() {
        System.out.println("Enter name of dog ");
        String nameDog = enterString("Name:");
        for (Dog d : dogList) {
            if (d.getName().equals(nameDog)) {
                d.addAge();
                System.out.println(d.getName() + " is now one year older");
                return;
            }

        }
        System.out.println("Error, no such dog");
    }

    private void listDogs() {
        Collections.sort(dogList);
        if (dogList.isEmpty()) {
            System.out.println("Error: no dogs in register");
        } else {

            System.out.println("Smallest tail length to display: ");
            double tailleght = input.nextDouble();
            input.nextLine();
            System.out.println("The following dogs has such a large tail: ");
            for (Dog d : dogList) {
                if (d.getTailLength() >= tailleght) {
                    // System.out.println("The following dogs has such a large tail: ");
                    System.out.println("*" + d); //skriva ut soterad lista, först på svanslängd sedan namn
                }
            }
        }
    }

    private void removeDog() {
        System.out.println("Enter name of dog ");
        String nameDog = enterString("Name: ");
        Dog d = findDog(nameDog);
            if (d != null){
                if(d.getUser() != null){
                    d.getUser().removeDogFromUser(d);

                }
                for (Auction auctionsDog : auctionList) {
                    if (d.equals(auctionsDog.getDog())) {
                        auctionList.remove(auctionsDog);
                        break;
                    }
                }
                removeFromDogList(d);
                return;
            }


        System.out.println("Error: no such dog");
            }


    private void removeFromDogList(Dog d){
        dogList.remove(d);
        System.out.println("Dog is removed");
    }

    private void startAuction() {
        System.out.println("Enter the name of the dog ");
        String nameDog = enterString("Name: ");
        Dog d = findDog(nameDog);
        if (d == null) {
            System.out.println("Error: no such dog");
            return;
        }
        if (d.isUpForAuction()) {
            System.out.println("Error: Dogs is already up for action");
            return;
        }
        if (d.getUser() != null) {
            System.out.println("Error: Dog is already owned.");
            return;
        }

        makeAuction(d, auctionNr);
        auctionNr++;
    }

    private void makeAuction(Dog d, int auctionNr){
        Auction a = new Auction(d, auctionNr); //skapa en aktion
        d.setUpForAuction(true);
        auctionList.add(a); //lägg til hund a i actionslistan
        System.out.println("Dog is up for auction with auctionnumber: " + auctionNr);
    }

    private Dog findDog(String nameDog) {
        for (Dog d : dogList) {
            if (d.getName().equalsIgnoreCase(nameDog)) { //titta att hunden finns med i listan
                return d; //retunera hund
            }

        }
        return null; // hunden inte finns
    }

    private User findUser(String nameUser) {
        for (User u : userList) {
            if (u.getName().equalsIgnoreCase(nameUser)) { //titta att user finns med i listan
                return u; //retunera user
            }

        }
        return null; // user inte finns i listan
    }

    private Auction findAuctionDog(String nameDog) {
        for (Auction d : auctionList) {
            if (d.getDogName().equalsIgnoreCase(nameDog)) { //titta att aktionshund finns med i listan
                return d; //retunera aktionshund
            }

        }
        return null; // aktionshund inte finns
    }

    private void listAuctions() {
        if (auctionList.isEmpty()) {
            System.out.println("Error, no ongoing auctions");
        } else {
            for (Auction a : auctionList) {
                System.out.println(a); // enbart skriva ut hundnamn och top bids + aktionsNr (skriver ut hela aktionsobjektets toString, hade kunnat ändra och kalla på metoden istället
            }
        }
    }

    private void listBids() {
        System.out.println("Enter the name of the dog ");
        String nameDog = enterString("Dogname: ");
        Auction a = findAuctionDog(nameDog);

        if (a == null) {
            System.out.println("Error: dog is not up for auction");
            return;
        }
        ArrayList<Bid> bidList = a.getBids();
        if (bidList.isEmpty()) {
            System.out.println("No bids registred yet for this auction");
            return;
        }
        System.out.println("Here are the bids for this auction");
        //vill göra netLine för nästa users bud
        for (Bid b : bidList) {
            System.out.println(b);
        }


    }


    private void makeBid() {
        String nameUser = enterString("Username: ");
        User a = findUser(nameUser);
        if (a == null) {
            System.out.println("Error, user does not exit");
            return;
        }
        String nameDog = enterString("Dogname: ");
        Auction auctiondog = findAuctionDog(nameDog);

        if (auctiondog == null) {
            System.out.println("Error, dogs is not up for auction");
            return;
        }

        System.out.println("Enter amount (min " + (auctiondog.getHighestAmount() + 1) + (")>"));
        int newBid = input.nextInt();
        input.nextLine();
        checkBid(auctiondog, newBid, a);
        System.out.println("Bid made");
    }

    private void checkBid(Auction auctionDog, int newBid, User a){
        while (newBid<=auctionDog.getHighestAmount()) {
            System.out.println("Error: bids to low");
            System.out.println("Enter amount (min " + (auctionDog.getHighestAmount() + 1) + ")");
            newBid = input.nextInt();
            input.nextLine();
        }
        auctionDog.newBid(a, newBid);
    }

    private void closeAuction() {
        System.out.println("Enter name of dog");
        String nameDog = enterString("Dogname: ");
        Auction auctionDog = findAuctionDog(nameDog);
        if (auctionDog == null) {
            System.out.println("Error: this dog is not up for auction");
            return;
        }
        if (auctionDog.getBids().isEmpty()) {

            auctionList.remove(auctionDog);
            auctionDog.getDog().setUpForAuction(false);
            System.out.println("The auction is closed. No bids where made for " + nameDog);
            return;
        }
        System.out.println("The auction is closed. The winning bid was " + auctionDog.getHighestAmount() + " kr and was made by " + auctionDog.getUserOfHighestAmount().getName());
        Dog dog = findDog(nameDog);
        User user = findUser(auctionDog.getUserOfHighestAmount().getName());
        dog.setUser(user);
        user.addDog(dog);
        dog.setUpForAuction(false);
        auctionList.remove(auctionDog);

    }


    private void doCommand() {
        boolean run = true;
        while (run) {
            String text = enterString("Command: ");
            switch (text) {
                case "exit":
                    run = false;
                    break;
                case "register new dog":
                    registerDog();
                    break;
                case "increase age":
                    increaseAge();
                    break;
                case "list dogs":
                    listDogs();
                    break;
                case "remove dog":
                    removeDog();
                    break;
                case "register new user":
                    registerUser();
                    break;
                case "list users":
                    listUsers();
                    break;
                case "remove user":
                    removeUser();
                    break;
                case "start auction":
                    startAuction();
                    break;
                case "list auctions":
                    listAuctions();
                    break;
                case "list bids":
                    listBids();
                    break;
                case "make bid":
                    makeBid();
                    break;
                case "close auction":
                    closeAuction();
                    break;
                default:
                    System.out.println("Error: unknown command " + text);
            }
        }
        exitProgram();
    }

    private void exitProgram() {
        System.out.println("Goodbye!");
    }

    public static void main(String[] args) {

        Program myProgram = new Program();

        myProgram.start();

    }
}