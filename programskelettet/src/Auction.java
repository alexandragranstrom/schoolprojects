//Alexandra Granström, algr5265
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Arrays;

public class Auction {

    private Dog dog;
    private ArrayList<Bid> bids = new ArrayList<>(); //skapa en lista för bud för att kunna hämta högsta buden, andvänd sortering för detta
    private int auctionId;

    private Bid [] bidarray; //lista aktion - lägga in buden från arraylist för att skriva ut tre högsta buden

    public Auction(Dog dog, int auctionId) {
        this.dog = dog;
        this.auctionId = auctionId;
    }

    public void removeBid(User user){
        for (Bid usersBid : bids){
            if(usersBid.getUser().equals(user)){
                bids.remove(usersBid);
                return;
            }
        }
    }


    private String printHighestBids() {  //kan även vara public
        sort(bids);
        bidarray = new Bid[Math.min(3, bids.size())];

        // this loop puts the arraylist bids in to an array.
        for (int i = 0; i < bidarray.length ; i++) {
            if (bids.get(i) != null) {
                Bid bud = bids.get(i);
                bidarray[i] = bud;

            }
        }
        return Arrays.toString(bidarray); //skriva ut mina 3 högsta bud */

    }

    public Dog getDog(){
        return dog;
    }

    public String getDogName(){
        return dog.getName();
    }

    public int getAuctionNr(){
        return auctionId;
    }

    public void newBid(User user, int amount){//lägga in bud i listan
        if(!updateBid(user, amount)) {
            Bid b = new Bid(user, amount);
            bids.add(b);
        }
    }


    public boolean updateBid(User user, int amount){
        for (Bid bid : bids){
            if (user == bid.getUser()){
                bid.setAmount(amount);
                return true;
            }
        }
        return false;
    }

    public User getUserOfHighestAmount(){
        sort(bids);
        return bids.get(0).getUser();

    }

    public int getHighestAmount(){
        if (bids.isEmpty()){
            return 0;
        } else {
            sort(bids); //sortering av arrayList
            return bids.get(0).getAmount(); //Skriv ut högsta budet
        }
    }

    private void sort(ArrayList<Bid> bids){
        for(int i = 1; i < bids.size(); i++){
            moveLeftUntilSorted(bids, i);
        }
    }

    private void moveLeftUntilSorted(ArrayList<Bid> bids, int index){
        for(;index > 0 && bids.get(index).getAmount() > bids.get(index-1).getAmount(); index--){
            int indexTwo = index -1;
            swap(bids, index, indexTwo);
        }
    }

    private void swap(ArrayList<Bid> bids, int indexOne, int indexTwo){
        Bid temp = bids.get(indexOne);
        bids.set(indexOne, bids.get(indexTwo));
        bids.set(indexTwo, temp);

    }
    public void printArray(){
        System.out.println(Arrays.toString(bidarray));
    }


    public ArrayList<Bid> getBids() {
        sort(bids);
        return new ArrayList<Bid>(bids); //
    }

    @Override
    public String toString(){
        if (bids.isEmpty()){
            return String.format("Auction #%d: %s.", auctionId, getDogName());
        }
        else {
            return String.format("Auction #%d: %s. Top bids: %s", auctionId, getDogName(), printHighestBids());
        }
    }
}
