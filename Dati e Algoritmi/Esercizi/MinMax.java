/*Progettare un algoritmo che trovi il valore massimo e il valore minimo in un insieme di n numeri (reali) 
  usando (SEMPRE) un numero di confronti (tra elementi dell’insieme) minore di 3n/2 */

public class MinMax {
    public static double[] findMinMax(double[] a) {
        // Precondizione
        if (a == null || a.length == 0) {
            throw new IllegalArgumentException();
        }

        // Crea due array confrontando coppie di valori, così da ottenere due array, uno
        // con valori generalmente più alti e uno con valori generalmente più bassi
        // Questo richiede un numero di confronti pari a n/2
        double[] mins = new double[(a.length+1)/2];
        double[] maxs = new double[(a.length+1)/2];

        for (int i = 0; i < a.length; i += 2) {
            if (a[i] < a[i + 1]) {
                mins[i/2] = a[i];
                maxs[i/2] = a[i+1];
            } else {
                mins[i/2] = a[i+1];
                maxs[i/2] = a[i];
            }
        }

        if (a.length % 2 == 1) // se lunghezza dispari...
            mins[mins.length-1] = maxs[maxs.length-1] = a[a.length-1];

        // Trovo ora il minimo e il massimo a partire dagli array di mins e max, l'operazione richiede n confronti
        double min = mins[0];
        double max = maxs[0];
        for (int i = 1; i < mins.length; i++)
        {  
            if (mins[i] < min) min = mins[i];
            if (maxs[i] > max) max = maxs[i];
        }

        return new double[] {min, max};
    }

    public static void main(String[] args) {
        double[] testArray = { 2, 12, 5, 25, -65, 0, -4, -4 };

        double[] result = findMinMax(testArray);
        System.out.println(
                String.format("The minimum value in the array is %s and the maximum is %s", result[0], result[1]));
    }
}
