//Alexandra Granström, algr5265
public class Bid {

    private int amount;
    private User user;

    public Bid (User user, int amount){
        this.user = user;
        this.amount = amount;
    }


    public int getAmount(){
        return amount;
    }

    public void setAmount(int amount){
        this.amount = amount;
    }

    public User getUser() {
        return user;
    }


    public String toString(){
        return String.format("%s %d kr", user.getName(), amount);
    }

}
