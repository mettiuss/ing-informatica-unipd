public class AlgTiming {
    public static void main(String[] args) {
        long initalTime = System.currentTimeMillis();
        int[] list = new int[500];
        for (int i = 0; i < list.length; i++) {
            for (int j = 0; j < list.length; j++) {
                list[i] = list[j];
            }
        }
        long finalTime = System.currentTimeMillis();
        System.out.println("Il tempo di esecuzione dell'algoritmo è: " + (finalTime - initalTime) + " ms");
    }
}