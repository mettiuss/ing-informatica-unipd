import java.util.Scanner;
import java.io.*;

public class StudentManager {
    public static void main(String[] args) {
        int fSize = 10, vSize = 0;
        Student[] students = new Student[fSize];
        try (FileReader reader = new FileReader("students.txt"); Scanner file = new Scanner(reader)) {
            while (file.hasNextLine()) {

                if (vSize + 1 > fSize) {
                    fSize = fSize * 2;
                    Student[] new_students = new Student[fSize];
                    System.arraycopy(students, 0, new_students, 0, vSize);
                    students = new_students;
                }
                Scanner person = new Scanner(file.nextLine());
                person.useDelimiter(" ");
                students[vSize] = new Student(person.next(), Integer.parseInt(person.next()),
                        Integer.parseInt(person.next()));
                vSize++;
            }
        } catch (IOException e) {
            System.out.println("Couldn't read file");
        }

        Scanner console = new Scanner(System.in);
        String command = "";

        do {
            if (command.equals("S")) {
                System.out.println("Inserisci il cognome dello studente");
                String name = console.nextLine();
                boolean found = false;
                for (int i = 0; i < vSize; i++) {
                    if (students[i].name.equals(name)) {
                        System.out.println(students[i]);
                        found = true;
                    }
                }
                if (!found) {
                    System.out.println("Cognome non trovato!");
                }
            }
            System.out.println("Inserisci un comando (Q, S)");
            command = console.nextLine().toUpperCase();
        } while (!command.equals("Q"));

        console.close();
    }
}

class Student {
    public String name;
    public int scritto;
    public int orale;

    public Student(String name, int scritto, int orale) {
        this.name = name;
        this.scritto = scritto;
        this.orale = orale;
    }

    public int media() {
        return (scritto + orale) / 2;
    }

    public String toString() {
        return name + " | voto scritto: " + scritto + " voto orale: " + orale + " media: " + this.media();
    }
}
