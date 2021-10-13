package com.company;

import java.util.concurrent.ThreadLocalRandom;

public class Logic {
    public int side = 3;                              // размер поля
    public int lenght = side*side;                   // длина массива
    public String[] array = new String[lenght];      // массив для кнопок
    Result result = new Result(side, side, 3);  // вводим размеры поля и количество знаков в линию нужны для победы
    Counter counter = new Counter(result);          // отправляем массив с данными о размере поля и его заполнении

    public void startPosition() {
        array = new String[lenght];
        positionPC();
    }

    public String resultS() { return result.process(array); }        // метод сообщает кто выиграл

    // метод управляющий методами positionUser и positionPC
    public boolean write(int index, String symbol) {
        // проверка на возможность установки Х или О
        if(array[index] == null) {
            array[index] = symbol;
        } else { return false; }    // нельзя записать
        return true;                // можно записать
    }

    //метод positionUser вызывается с кнопки и в него кнопка отправляет свой индекс
    public boolean positionUser(int index) {
        return write(index, "0");      // User ходит ноликами;
        // если запись символа возможна true
    }

    // positionPC - ход компьютера
    public boolean positionPC() {
        return write(counter.process(array), "X");  // метод process класса counter возвращает индекс лучшего хода
    }
}
