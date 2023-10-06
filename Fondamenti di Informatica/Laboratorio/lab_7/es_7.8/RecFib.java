import java.util.*;

public class RecFib {
   public static void main(String[] args) {
      int n = 0;
      try (Scanner console = new Scanner(System.in)) {
         System.out.println("Inserisci un numero intero positivo");
         n = console.nextInt();
         if (n < 1) {
            throw new InputMismatchException();
         }
      } catch (InputMismatchException e) {
         System.out.println("Valore non valido");
         System.exit(1);
      }
      
      //algoritmo di Fibonacci
      System.out.println("Numero di Fibonacci: " + recursiveFib(n - 1));
   }
   
   private static int recursiveFib(int n) {
      if (n == 0 || n == 1) return 1;
      return recursiveFib(n - 1) + recursiveFib(n - 2);
   }
}
