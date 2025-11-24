import java.util.Enumeration;
import java.util.Vector;

public class Customer {
   private String _name;
   private Vector _rentals = new Vector();

   public Customer (String name){
      _name = name;
   }

   public void addRental(Rental arg) {
      _rentals.addElement(arg);
   }
   
   public String getName (){
      return _name;
   }
  
  public String statement() {
     double totalAmount = 0;
     int frequentRenterPoints = 0;
     Enumeration rentals = _rentals.elements();
     String result = "Rental Record for " + getName() + "\n";
     while (rentals.hasMoreElements()) {
        // A chamada aqui permanece 'each', pois é a variável local do loop
        Rental each = (Rental) rentals.nextElement();
        double thisAmount = amountFor(each); 

        // add frequent renter points
        frequentRenterPoints ++;
        // add bonus for a two day new release rental
        if ((each.getMovie().getPriceCode() == Movie.NEW_RELEASE) &&
            each.getDaysRented() > 1) frequentRenterPoints ++;

        //show figures for this rental
        result += "\t" + each.getMovie().getTitle()+ "\t" +
            String.valueOf(thisAmount) + "\n";
        totalAmount += thisAmount;

     }
     //add footer lines
     result +=  "Amount owed is " + String.valueOf(totalAmount) + "\n";
     result += "You earned " + String.valueOf(frequentRenterPoints) +
             " frequent renter points";
     return result;
   }

   // <--- MUDANÇA AQUI: Parâmetro renomeado para aRental
   private double amountFor(Rental aRental) {
      double thisAmount = 0;
      
      // determine amounts for each line (O switch-case original)
      switch (aRental.getMovie().getPriceCode()) { // <--- USO DE aRental
         case Movie.REGULAR:
            thisAmount += 2;
            if (aRental.getDaysRented() > 2) // <--- USO DE aRental
               thisAmount += (aRental.getDaysRented() - 2) * 1.5; // <--- USO DE aRental
            break;
         case Movie.NEW_RELEASE:
            thisAmount += aRental.getDaysRented() * 3; // <--- USO DE aRental
            break;
         case Movie.CHILDRENS:
            thisAmount += 1.5;
            if (aRental.getDaysRented() > 3) // <--- USO DE aRental
               thisAmount += (aRental.getDaysRented() - 3) * 1.5; // <--- USO DE aRental
             break;
      }
      return thisAmount;
   }
}
