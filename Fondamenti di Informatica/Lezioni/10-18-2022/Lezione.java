public class Lezione {
    public static void main(String[] args) {
        // concatenazione
        String hel = "Hel";
        String lo = "lo";
        String hello = hel + lo;

        System.out.println(hello.toUpperCase());
        System.out.println(hello.toLowerCase());

        // concatenazione int
        int number = 30;
        String s = number + " " + hello; // 30 Hello
        System.out.println(s);

        // conversione int e concatenazione
        String strNumber = Integer.toString(number);
        String s1 = strNumber + " " + hello; // 30 Hello
        System.out.println(s1);

        System.out.println(hello.charAt(2)); // l

        char newLine = '\n';
        System.out.println("Hello, \"World\"");
    }
}