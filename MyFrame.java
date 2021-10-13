package com.company;

import javax.swing.*;
import java.awt.*;

public class MyFrame extends JFrame {
    public Logic logic = new Logic();
    public Button[] buttons = new Button[logic.lenght];

    public MyFrame() {
        this.setVisible(true);                        // отображение окна
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);    // закрытие на крестик
        this.setSize(logic.side * 100 + 10, logic.side * 100 + 40);   // размер окна исходя из количества клеток
        this.setResizable(false);                    // не растягиваемое окно
        this.setLocation(1500,400);            // расположение окна
        this.setTitle("Крестики-Нолики");           // название программы
// 13.10.2021 добавил автоматическую корректировку размера окна, в зависимости о раскроя поля
        JPanel panel = new JPanel();
        panel.setLayout(null);              // обнуляем слои
        this.add(panel);
        int index = 0;                      // обозначаем кнопки 0-9
        for(int y = 0; y < logic.side; y++) {
            for(int x = 0; x < logic.side; x++) {
                // создаем по 9 кнопок через конструктор
                panel.add(buttons[index] = new Button(index, x, y, logic, this));
                index++;
            }
        }
        logic.startPosition();          // очистка позиций поля
        this.update();                  // обновляем отображение кнопок на поле
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new MyFrame();
            }
        });
    }

    public void update() {
        for(int i =0; i < buttons.length; i++) {
            // пишем текст на кнопках согласно логике со всеми проверками
            buttons[i].setText(logic.array[i]);
        }
    }
}
