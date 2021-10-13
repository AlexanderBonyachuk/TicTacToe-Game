package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Button extends JButton {
    private int index;
    private Logic logic = new Logic();
    private MyFrame myFrame;

    public Button(int index, int x, int y, Logic logic, MyFrame myFrame) {
        Font ex = new Font("Arial", 1, 40);    // стиль текста
        this.setFont(ex);                                     // задаем стиль текста
        this.index = index;
        this.logic = logic;
        this.myFrame = myFrame;
        // кнопка с координатами х у и размером 100х100
        this.setBounds(x * 100, y * 100, 100, 100);
        this.addActionListener(new MyKeyAdapter());
    }

    // вывод сообщений о статусе игрока
    public void action(String text) {
        JOptionPane.showMessageDialog(null, text);
        logic.startPosition();      // очистка позиций поля
        myFrame.update();           // обновляем отображение кнопок на поле
    }

    public boolean resultButton(String symbol) {
        if(symbol == "X") {
            action("Ты проиграл");
            return true;            // если игра окончена
        }
        if(symbol == "0") {
            action("Ты выиграл");
            return true;
        }
        if(symbol == "тупик") {
            action("Ничья");
            return true;
        }
        return false;           // если игра не окончена
    }

    // слушатель клавиш для каждой кнопки
    private class MyKeyAdapter implements ActionListener {

        @Override
        public void actionPerformed(ActionEvent e) {
                logic.positionUser(index);      // передаем в логику индекс нажатой кнопки
                myFrame.update();               // обновление отображения кнопки
                if(resultButton(logic.resultS())) { return; }  // проверка на конец игры (выиграл ли кто-то), если игра окончена вылетаем от сюда
// 11.10.2021 остановка тут
                logic.positionPC();
                myFrame.update();
                if(resultButton(logic.resultS())) { return; }
        }
    }
}
