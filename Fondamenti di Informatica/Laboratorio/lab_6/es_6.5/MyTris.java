public class MyTris {
    private char[][] table;

    public MyTris() {
        table = new char[3][3];
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                table[i][j] = '.';
            }
        }
    }

    public String toString() {
        String s = "";
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                s += table[i][j];
            }
            s += "\n";
        }
        return s;
    }

    public boolean setCharInPosition(int row, int column, char c) {
        if (this.getCharInPosition(row, column) == '.' && row < 3 && column < 3 && row >= 0 && column >= 0) {
            table[column][row] = c;
            return true;
        } else {
            return false;
        }
    }

    public char getCharInPosition(int row, int column) {
        return table[column][row];
    }

    public int getCount() {
        int tot = 0;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                if (table[i][j] != '.') {
                    tot++;
                }
            }
        }
        return tot;
    }

    public boolean isWinning(char c) {
        boolean winner = false, diagonalWin1 = true, diagonalWin2 = true;
        for (int i = 0; i < 3; i++) {
            boolean rowWin = true, columnWin = true;
            for (int j = 0; j < 3; j++) {
                if (table[i][j] != c)
                    rowWin = false;
                if (table[j][i] != c)
                    columnWin = false;
            }
            if (table[i][i] != c)
                diagonalWin1 = false;
            if (table[i][2 - i] != c)
                diagonalWin2 = false;
            if (rowWin || columnWin)
                winner = true;
        }
        if (diagonalWin1 || diagonalWin2)
            winner = true;
        return winner;
    }
}