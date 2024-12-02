package checkers;

import java.io.Serializable;

public class Coordinate implements Serializable {

    private final int c;

    public Coordinate(int c) {
        this.c = c;
    }

    Coordinate() {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    public int get() {
        return c;
    }

    public boolean equals(Coordinate d) {
        return (c == d.get());
    }

    public int row() {
        int row;
        row = (int) Math.floor(c / 4.0);
        if (c % 4 == 0) {
            return row - 1;
        } else {
            return row;
        }
    }

    public int column() {
        if (row() % 2 != 0) {
            return (((c - (row() * 4)) * 2) - 1);
        } else {
            return (((c - (row() * 4)) * 2) - 2);
        }
    }



    public String toString() {
        return "" + c;
    }

    public Coordinate upLeftMove() {
        if (row() % 2 != 0) {
            return new Coordinate(c - 5);
        } else {
            return new Coordinate(c - 4);
        }
    }

    public Coordinate upRightMove() {
        if (row() % 2 != 0) {
            return new Coordinate(c - 4);
        } else {
            return new Coordinate(c - 3);
        }
    }

    public Coordinate downLeftMove() {
        if (row() % 2 != 0) {
            return new Coordinate(c + 3);
        } else {
            return new Coordinate(c + 4);
        }
    }

    public Coordinate downRightMove() {
        if (row() % 2 != 0) {
            return new Coordinate(c + 4);
        } else {
            return new Coordinate(c + 5);
        }
    }

    public Coordinate upLeftJump() {
        return new Coordinate(c - 9);
    }

    public Coordinate upRightJump() {
        return new Coordinate(c - 7);
    }

    public Coordinate downLeftJump() {
        return new Coordinate(c + 7);
    }

    public Coordinate downRightJump() {
        return new Coordinate(c + 9);
    }

    // Новый метод для сдвига вправо
    public Coordinate moveRight() {
        // Проверяем, можем ли мы сдвигать вправо (в пределах доски)
        if (column() < 8) { // Сдвиг возможен только до последней колонки
            return new Coordinate(c + 1);  // Сдвигаем на одну клетку вправо
        }
        return null;  // Если сдвиг невозможен (вне границ доски), возвращаем null
    }
}
