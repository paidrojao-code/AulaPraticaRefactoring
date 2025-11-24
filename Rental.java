public class Rental {

   private Movie _movie;
   private int _daysRented;

   public Rental(Movie movie, int daysRented) {
      _movie = movie;
      _daysRented = daysRented;
   }

   public int getDaysRented() {
      return _daysRented;
   }

   public Movie getMovie() {
      return _movie;
   }
   
   public double getCharge() {
      double result = 0;
      
      // determine amounts for each line (Lógica movida de Customer.statement())
      switch (_movie.getPriceCode()) { // Usa o campo _movie da classe Rental
         case Movie.REGULAR:
            result += 2;
            if (_daysRented > 2) // Usa o campo _daysRented da classe Rental
               result += (_daysRented - 2) * 1.5;
            break;
         case Movie.NEW_RELEASE:
            result += _daysRented * 3;
            break;
         case Movie.CHILDRENS:
            result += 1.5;
            if (_daysRented > 3)
               result += (_daysRented - 3) * 1.5;
             break;
      }
      return result;
   }
   
   // <--- NOVO MÉTODO ADICIONADO AQUI
   public int getFrequentRenterPoints() {
       // add frequent renter points
       int result = 1;
       // add bonus for a two day new release rental
       if ((_movie.getPriceCode() == Movie.NEW_RELEASE) &&
           _daysRented > 1) result++;
       return result;
   }
}
