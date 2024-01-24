public class ChessKnight {
    private String position;

    public ChessKnight(String initialSquare) {
        position = initialSquare;
    }

    public boolean isChessValidSquare(String chessSquare) {
        int x = chessSquare.charAt(0);
        int y = chessSquare.charAt(1);
        if (x < 97 || x > 104 || y < 48 || y > 56) {
            return false;
        }
        return true;
    }

    public boolean isKnightReachableSquare(String chessSquare) {
        int x = chessSquare.charAt(0);
        int y = chessSquare.charAt(1);
        int currentX = position.charAt(0);
        int currentY = position.charAt(1);
        if (Math.abs(x - currentX) + Math.abs(y - currentY) != 3 || Math.abs(x - currentX) == 0
                || Math.abs(y - currentY) == 0) {
            return false;
        }
        return true;
    }

    public void moveToSquare(String toChessSquare) {
        if (isKnightReachableSquare(toChessSquare)) {
            position = toChessSquare;
        }
    }

    public String toString() {
        return "Cavallo in " + position;
    }
}