public class ArrayAlgorithms {
    public static void selectionSort(BankAccount[] list) {
        for (int i = 0; i < list.length; i++) {
            int min = i;
            for (int j = i; j < list.length; j++) {
                if (list[min].compareTo(list[j]) == 1) {
                    min = j;
                }
            }
            swap(list, min, i);
        }
    }

    public static void insertionSort(BankAccount[] list) {
        for (int i = 1; i < list.length; i++) {
            int t = i;
            for (int j = t - 1; j >= 0; j--) {
                if (list[t].compareTo(list[j]) == -1) {
                    swap(list, j, t);
                    t--;
                }
            }
        }
    }

    private static void swap(BankAccount[] list, int from, int to) {
        BankAccount t = list[to];
        list[to] = list[from];
        list[from] = t;
    }

    public static void mergeSort(BankAccount[] list) {
        if (list.length == 1)
            return;

        int split = list.length / 2;
        BankAccount[] left = new BankAccount[split];
        BankAccount[] right = new BankAccount[list.length - split];
        System.arraycopy(list, 0, left, 0, split);
        System.arraycopy(list, split, right, 0, list.length - split);

        mergeSort(left);
        mergeSort(right);

        merge(list, left, right);
    }

    private static void merge(BankAccount[] list, BankAccount[] left, BankAccount[] right) {
        int ilist = 0, ileft = 0, iright = 0;
        while (ileft < left.length && iright < right.length) {
            if (left[ileft].compareTo(right[iright]) == 1) {
                list[ilist++] = left[ileft++];
            } else {
                list[ilist++] = right[iright++];
            }
        }

        while (ileft > left.length) {
            list[ilist++] = left[ileft++];
        }

        while (iright > right.length) {
            list[ilist++] = right[iright++];
        }

    }
}
