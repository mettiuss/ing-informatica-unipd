/*Progettare un algoritmo che trovi il valore massimo e il valore minimo in un insieme di n numeri (reali) 
  usando (SEMPRE) un numero di confronti (tra elementi dell’insieme) minore di 3n/2 */

public class MinMax {
    public static double[] findMinMax(double[] array) {
        // Precondizione
        if (array.length == 0) {
            double[] returnArray = { 0, 0 };
            return returnArray;
        }

        // caso banale
        if (array.length == 1) {
            double[] returnArray = { array[0], array[0] };
            return returnArray;
        }

        // Crea due array confrontando coppie di valori, così da ottenere due array, uno
        // con valori generalmente più alti e uno con valori generalmente più bassi
        // Questo richiede un numero di confronti pari a n/2
        int mid = array.length / 2;
        int higherIndex = 0;
        double[] higherArray = new double[mid];
        int lowerIndex = 0;
        double[] lowerArray = new double[array.length - mid];

        for (int i = 0; i < array.length; i += 2) {
            if ((i + 1) == array.length) {
                lowerArray[lowerIndex++] = array[i];
                continue;
            }

            if (array[i] > array[i + 1]) {
                higherArray[higherIndex++] = array[i];
                lowerArray[lowerIndex++] = array[i + 1];
            } else {
                higherArray[higherIndex++] = array[i + 1];
                lowerArray[lowerIndex++] = array[i];
            }
        }

        // Trovo ora il minimo dall'array dei numeri più bassi, che conincide con il
        // minimo dell'array iniziale
        // Questa operazione richiede un numero di confronti pari a n/2
        double min = lowerArray[0];
        for (int i = 0; i < lowerArray.length; i++) {
            if (lowerArray[i] < min) {
                min = lowerArray[i];
            }
        }

        // Nello stesso modo cerco il massimo, usando ancora un numero di confronti pari
        // a n/2
        double max = higherArray[0];
        for (int i = 0; i < higherArray.length; i++) {
            if (higherArray[i] > max) {
                max = higherArray[i];
            }
        }

        double[] returnArray = { min, max };
        return returnArray;
    }

    public static void main(String[] args) {
        double[] testArray = { 2, 12, 5, 25, -65, 0, -4, -4 };

        double[] result = findMinMax(testArray);
        System.out.println(
                String.format("The minimum value in the array is %s and the maximum is %s", result[0], result[1]));
    }
}
