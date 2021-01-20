import java.util.ArrayList;
import java.util.Iterator;
/**
 * A simple model of an auction.
 * The auction maintains a list of lots of arbitrary length.
 *
 * @author David J. Barnes and Michael Kölling.
 * @version 2011.07.31
 */
public class Auction
{
    // The list of Lots in this auction.
    private ArrayList<Lot> lots;
    // The number that will be given to the next lot entered
    // into this auction.
    private int nextLotNumber;

    /**
     * Create a new auction.
     */
    public Auction()
    {
        lots = new ArrayList<Lot>();
        nextLotNumber = 1;
    }

    /**
     * Enter a new lot into the auction.
     * @param description A description of the lot.
     */
    public void enterLot(String description)
    {
        lots.add(new Lot(nextLotNumber, description));
        nextLotNumber++;
    }

    /**
     * Show the full list of lots in this auction.
     */
    public void showLots()
    {
        for(Lot lot : lots) {
            System.out.println(lot.toString());
        }
    }

    /**
     * Make a bid for a lot.
     * A message is printed indicating whether the bid is
     * successful or not.
     * 
     * @param lotNumber The lot being bid for.
     * @param bidder The person bidding for the lot.
     * @param value  The value of the bid.
     */
    public void makeABid(int lotNumber, Person bidder, long value)
    {
        Lot selectedLot = getLot(lotNumber);
        if(selectedLot != null) {
            if(selectedLot.bidFor(new Bid(bidder, value))) {
                System.out.println("The bid for lot number " +
                    lotNumber + " was successful.");
            }
            else {
                // Report which bid is higher.

                System.out.println("Lot number: " + lotNumber +
                    " already has a bid of: " +
                    selectedLot.getHighestBid().getValue());
            }
        }
    }

    /**
     * Return the lot with the given number. Return null
     * if a lot with this number does not exist.
     * @param lotNumber The number of the lot to return.
     */
    public Lot getLot(int lotNumber)
    {
        Lot selectedLot = null;
        if(lotNumber >= 1){
            Iterator<Lot> it = lots.iterator();   
            while (it.hasNext()){
                Lot lotActually = it.next();
                if(lotActually.getNumber() == lotNumber){
                    selectedLot = lotActually;
                }
            }
        }
        return selectedLot;
    }

    public void close(){
        for(Lot lot : lots) {
            Bid bidActually = lot.getHighestBid();
            if(lot.getHighestBid() != null){
                Person personHighestBid = bidActually.getBidder();
                String name = personHighestBid.getName();
                System.out.println(lot.toString() + " vendido a " + name);
            }
            else{
                System.out.println(lot.toString() + " no vendido ");
            }
        }
    }

    public ArrayList<Lot> getUnsoId(){
        ArrayList<Lot> lotsNotSold = new ArrayList<Lot>();
        Iterator<Lot> it = lots.iterator();        
        while (it.hasNext()){
            Lot lotActually = it.next();
            if(lotActually.getHighestBid() == null){
                lotsNotSold.add(lotActually);                
            }
        }
        return lotsNotSold;     
    }
    
        /** 
     * Elimina el lote con el n�mero de lote especificado.
     * @param number El n�mero del lote que hay que eliminar,
     * @return El lote con el n�mero dado o null si no existe tal lote.
     */
    public Lot removeLot(int number){
        Lot selectedLot = null;
        Iterator<Lot> it = lots.iterator();        
        if(number >= 1){               
            while (it.hasNext()){
                Lot lotActually = it.next();
                if(lotActually.getNumber() == number){
                    selectedLot = lotActually;
                    it.remove();
                }
            }
        }
        return selectedLot;
    }
    }
