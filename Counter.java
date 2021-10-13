package com.company;

public class Counter {
    int depth = 5;                                          // глубина на которую компьютер просчитывает ходы
    int index;
    Result result;
    Counter(Result result) { this.result = result; }        // принимаем массив с данными

    public int process(String[] array) {
        recursion("0", array, -1, depth);     // в качестве координаты пишем -1, чтобы просчитать ходы и с нулевой позиции тоже
        return index;                                  // ход который должен сделать ПК - наилучший ход
    }

    // рекурсивный метод
    public int recursion(String symbol, String[] arrayOut, int coordinate, int depth) {
        String[] array = arrayOut.clone();         // копируем уже записанные крестики и нолики
        if(coordinate >= 0)  { array[coordinate] = symbol; }    // для просмотра всех вариантов

        if(result.process(array) == symbol) {      // условие победы, смотрим кто победил
            return 2;                              // 2-очка победа, 1-ничья, 0-поражение
        }

        int score = 0;          // результат противника
        int maxScore = -1;      // для фиксации лучшего возможного результата противника
        int index = 0;          // индекс для фиксации хода

// 12.10.2021 остановился тут
        // анализ всей матрицы, всех возможных вариантов ходов
        for(int i = 0; i < array.length; i++) {
            if(array[i] == null && depth > 0) {             // условие остановки просчета вариантов
                if(symbol == "X") { recursion("0", array, i, depth-1); }
                if(symbol == "0") { recursion("X", array, i, depth-1); }
                if(score > maxScore) {         // предполагаем что противник играет сильнейшим образом
                    maxScore = score;
                    index = i;                 // фиксируем индекс сильнейшего хода
                }
            }
        }
        this.index = index;                     // передаем индекс сильнейшего хода в index класса Counter
        if(maxScore == -1) { return 1; }
        else { return 2 - maxScore; }          // возвращаем исход игры 2-победа, 1-ничья, 0-поражение
    }
}
