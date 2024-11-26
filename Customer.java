import java.util.Enumeration;
import java.util.Vector;

public class Customer {
   private String _name;
   private Vector _rentals = new Vector();

   public Customer(String name) {
      _name = name;
   }

   public void addRental(Rental arg) {
      _rentals.addElement(arg);
   }

   public String getName() {
      return _name;
   }

   public String statement() {
      double totalAmount = 0;
      int frequentRenterPoints = 0;
      Enumeration rentals = _rentals.elements();
      String result = "Rental Record for " + getName() + "\n";

      while (rentals.hasMoreElements()) {
         Rental each = rentals.nextElement();
     
         // Chamada do método com o nome atualizado
         double thisAmount = amountFor(each);
     
         // Lógica restante permanece igual
         frequentRenterPoints++;
         if ((each.getMovie().getPriceCode() == Movie.NEW_RELEASE) &&
             each.getDaysRented() > 1) {
             frequentRenterPoints++;
         }
     
         result += "\t" + each.getMovie().getTitle() + "\t" +
                   String.valueOf(thisAmount) + "\n";
         totalAmount += thisAmount;
     }

         // Refatoração: usar o método amountFor para calcular thisAmount
         double thisAmount = amountFor(each);

         // Adicionar pontos de locação frequente
         frequentRenterPoints++;
         if ((each.getMovie().getPriceCode() == Movie.NEW_RELEASE) &&
             each.getDaysRented() > 1) {
            frequentRenterPoints++;
         }

         // Mostrar valores para este aluguel
         result += "\t" + each.getMovie().getTitle() + "\t" +
                   String.valueOf(thisAmount) + "\n";
         totalAmount += thisAmount;
      }

      // Adicionar rodapé
      result += "Amount owed is " + String.valueOf(totalAmount) + "\n";
      result += "You earned " + String.valueOf(frequentRenterPoints) +
                " frequent renter points";

      return result;
   }

   // Método extraído: calcula o valor para cada aluguel
   private double amountFor(Rental aRental) {
      double thisAmount = 0;
  
      switch (aRental.getMovie().getPriceCode()) {
          case Movie.REGULAR:
              thisAmount += 2;
              if (aRental.getDaysRented() > 2) {
                  thisAmount += (aRental.getDaysRented() - 2) * 1.5;
              }
              break;
          case Movie.NEW_RELEASE:
              thisAmount += aRental.getDaysRented() * 3;
              break;
          case Movie.CHILDRENS:
              thisAmount += 1.5;
              if (aRental.getDaysRented() > 3) {
                  thisAmount += (aRental.getDaysRented() - 3) * 1.5;
              }
              break;
      }
  
      return thisAmount;
  }
