
/*
    L'insieme C dei numeri complessi e' l'insieme delle coppie ordinate (x,y) 
    con x e y appartenenti a R (insieme dei numeri reali).
    Dato il numero complesso z = x + i *y, i numeri reali x e y sono detti
    parte reale e parte immaginaria di z.
    Nei commenti che seguono, facciamo riferimento ai numeri complessi
    z = x + i*y, z1 = x1 + i*y1, z2 = x2 + i*y2
*/
import java.util.Scanner;

class MyComplex {
    // variabili di esemplare
    private double re;
    private double im;

    // inizializza il numero complesso al valore 0 + i0 (zero complesso)
    public MyComplex() {
        re = 0;
        im = 0;
    }

    // inizializza il numero complesso al valore re + i*im
    public MyComplex(double realPart, double imagPart) {
        re = realPart;
        im = imagPart;
    }

    // Somma a questo numero complesso il numero complesso z
    // somma di due complessi: z = z1+z2 = (x1+x2) + i(y1+y2)
    public MyComplex add(MyComplex z) {
        return new MyComplex(re + z.getRe(), im + z.getIm());
    }

    // Sottrae a questo numero complesso il numero complesso z
    // sottrazione di due complessi: z = z1-z2 = (x1-x2) +i(y1-y2)
    public MyComplex sub(MyComplex z) {
        return new MyComplex(re - z.getRe(), im - z.getIm());
    }

    // Moltiplica questo numero complesso per il numero complesso z
    // prodotto di due complessi: z = z1*z2 = (x1*x2 -y1*y2) + i*(x1*y2 + x2*y1)
    public MyComplex mult(MyComplex z) {
        return new MyComplex(re * z.getRe() - im * z.getIm(), re * z.getIm() + z.getRe() * im);
    }

    // Divide questo numero complesso per il numero complesso z
    // divisione fra due complessi: z1/z2 = z1 *1/z2, per z2 != 0
    public MyComplex div(MyComplex z) {
        return mult(z.inv());
    }

    // Calcola il coniugato di questo numero complesso
    // coniugato del complesso z: z^= x - i*y
    public MyComplex conj() {
        return new MyComplex(re, -im);
    }

    // Calcola l'inverso rispetto al prodotto di un numero complesso
    // inverso del complesso z: 1/z = x/(|z|*|z|) -i*y/(|z|*|z|), per z != 0
    public MyComplex inv() {
        return new MyComplex(re / (Math.pow(mod(), 2)), im / (Math.pow(mod(), 2)));
    }

    // Calcola il modulo di questo numero complesso
    // modulo del complesso z: |z| = sqrt( x*x + y*y).
    public double mod() {
        return Math.sqrt(Math.pow(re, 2) + Math.pow(im, 2));
    }

    // Crea una stringa che rappresenta questo numero complesso
    // del tipo "x + i * y"
    public String toString() {
        return re + " + i * " + im;
    }

    // -------- metodi di accesso ----------

    // Restituisce la parte immaginaria di un numero complesso
    public double getIm() {
        return im;
    }

    // Restituisce la parte reale di un numero complesso
    public double getRe() {
        return re;
    }
}

public class MyComplexTester {
    // Il programma riceve due numeri complessi da standard input, uno per riga, nel
    // formato "x y ",
    // dove x (parte reale) e y (parte immaginaria) sono separate dal carattere
    // spazio ' '.

    // Fornisce a standard output somma, sottrazione, prodotto, divisione, elementi
    // inversi e coniugati dei numeri.
    // Provare con numeri semplici, in modo da verificare i risultati ottenuti.
    public static void main(String[] args) {
        Scanner console = new Scanner(System.in);

        System.out.println("Inserisci il primo numero complesso in formato 'x y'");
        MyComplex one = new MyComplex(console.nextDouble(), console.nextDouble());
        System.out.println("Inserisci il secondo numero complesso in formato 'x y'");
        MyComplex two = new MyComplex(console.nextDouble(), console.nextDouble());

        System.out.print("Somma: ");
        System.out.println(one.add(two).toString());

        System.out.print("Sottrazione: ");
        System.out.println(one.sub(two).toString());

        System.out.print("Prodotto: ");
        System.out.println(one.mult(two).toString());

        System.out.print("Divisione: ");
        System.out.println(one.div(two).toString());

        System.out.println("Numeri inversi: ");
        System.out.println(one.inv().toString());
        System.out.println(two.inv().toString());

        System.out.println("Numeri coniugati: ");
        System.out.println(one.conj().toString());
        System.out.println(two.conj().toString());
    }
}