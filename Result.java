package com.company;

public class Result {
    private int width;
    private int height;
    private int distance;
    private String[] array;

    public Result(int width, int height, int distance) {
        this.width = width;
        this.height = height;
        this.distance = distance;
    }

    public String process(String[] array) {
        this.array = array;
        int i =0;

        // определяем есть ли одинаковые знаки в линию
        for(int y = 0; y < width; y++) {
            for (int x = 0; x < height; x++) {
                if(array[border(x,y)] != null) { i++; }

                for(int b = -1; b <= +1;b++) {
                    for(int a = -1; a <= +1;a++) {
                        if(!(a==0 && b==0)) {
                            if(check(x, y, a, b, "X")) { return "X"; }
                            if(check(x, y, a, b, "0")) { return "0"; }
                            if(i == array.length)             { return "тупик"; }
                        }
                    }
                }
            }
        }
        return "ничья";
    }

    // метод, проверяющий, что мы не зашли за границы
    public int border(int x, int y) {
        if(x >= 0 && x < width && y >= 0 && y < height) {
            return y * width + x;
        }
        return -1;
    }

    // метод проверяет есть ли крестики или нолики в каком то направлении
    public boolean check(int x, int y, int a, int b, String symbol) {
        for (int i = 0; i < distance; i++) {
            int index = border(x + a * i, y + b * i);       // пробиваем чтобы в направлении было что только одно Х или О
            if(index == -1) { return false; }
            else { if(array[index] != symbol) { return false; } }
        }
        return true;
    }
}
