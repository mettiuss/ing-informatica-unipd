import java.util.*;

public class IterFib {
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
      System.out.println("Numero di Fibonacci: " + iterativeFib(n));
   }
   
   private static int iterativeFib(int n) {
      int i = 1;
      int last = 0;
      int count = 1;
      while (n > count) {
         int t = i;
         i += last;
         last = t;
         count++;
      }
      return i;
   }
}
