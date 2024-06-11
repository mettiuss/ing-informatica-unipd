public class ArrayUtil {
    public static void mergeSort(Comparable[] l) {
        //caso base
        if (l.length < 2) return;
        int mid = l.length / 2;
        Comparable[] left = new Comparable[mid];
        Comparable[] right = new Comparable[l.length - mid];
        System.arraycopy(l, 0, left, 0, mid);
        System.arraycopy(l, mid, right, 0, l.length - mid);
        mergeSort(left);
        mergeSort(right);

        int i = 0, il = 0, ir = 0;
        while (il < left.length && ir < right.length) {
            if (left[il].compareTo(right[ir]) < 0) {
                l[i++] = left[il++];
            } else {
                l[i++] = right[ir++];
            }
        }
        while (il < left.length) {
            l[i++] = left[il++];
        }
        while (ir < right.length) {
            l[i++] = right[ir++];
        }
    }

    public static void insertionSort(Comparable[] l) {
        for (int i = 1; i < l.length; i++) {
            Comparable obj = l[i];
            int j = i;
            while (j > 0 && l[j - 1].compareTo(obj) > 0) {
                l[j] = l[j - 1];
                j--;
            }
            l[j] = obj;
        }
    }

    public static void selectionSort(Comparable[] l) {
        for (int i = 0; i < l.length; i++) {
            int min = i;
            for (int j = i; j < l.length; j++) {
                if (l[min].compareTo(l[j]) > 0) {
                    min = j;
                }
            }
            //swap
            Comparable t = l[min];
            l[min] = l[i];
            l[i] = t;
        }
    }

    public static int linearSearch(Object[] l, Object term) {
        for (int i = 0; i < l.length; i++) {
            if (l[i].equals(term)) {
                return i;
            }
        }
        return -1;
    }

    public static int binarySearch(Comparable[] l, Comparable term) {
        return binSearch(l, term, 0, l.length - 1);
    }

    private static int binSearch(Comparable[] l, Comparable term, int start, int end) {
        if (start > end) return -1;
        int mid = (end + start) / 2;
        if (l[mid].equals(term)) return mid;
        if (l[mid].compareTo(term) > 0) {
            return binSearch(l, term, start, mid - 1);
        } else {
            return binSearch(l, term, mid + 1, end);
        }
    }
}