package ru.popovich.projectgame;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.Layout;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    // ОБЪЯВЛЕНИЯ ПЕРЕМЕННЫХ ДЛЯ ЭЛЕМЕНТОВ ЭКРАНА

    // Контейнер-компоновщик для текста вопроса
    LinearLayout layout;

    // Контейнер для счетчика вопросов
    LinearLayout layoutTextcounter;

    // Текс-счетчик сверху
    TextView textCounter;

    // Текст вопроса по центру
    TextView question;

    // Вспомогательный текст под вопросом
    TextView textComment;

    // Контейнер для ответов
    LinearLayout layoutAnswers;

    // Кнопки ответов
    Button answer1;
    Button answer2;
    Button answer3;
    Button answer4;

    // Массив для вопросов
    String[][] array;
    int[] correct;

    // Счетчик вопросов
    int counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Инициализация контейнера для текста счетчика
        layoutTextcounter = findViewById(R.id.layout_textcounter);

        // Инициализация контейнера-компоновщика для текста вопроса
        layout = findViewById(R.id.question_layout);

        // Инициализация вспомогательного текста
        textComment = findViewById(R.id.text_comment);

        // Инициализация текста для счетчика
        textCounter = findViewById(R.id.textCounter);

        // Инициализация контейнера ответов
        layoutAnswers = findViewById(R.id.layout_answers);

        // Инициализация текста для вопроса и кнопок ответа
        question = findViewById(R.id.text_question);
        answer1 = findViewById(R.id.button_answer1);
        answer2 = findViewById(R.id.button_answer2);
        answer3 = findViewById(R.id.button_answer3);
        answer4 = findViewById(R.id.button_answer4);


        // Идентификация данных
        array = new String[10][5];
        array[0][0] = "Вопрос01"; array[0][1] = "Ответ11"; array[0][2] = "Ответ12"; array[0][3] = "Ответ13";array[0][4] = "Ответ14";
        array[1][0] = "Вопрос02"; array[1][1] = "Ответ21"; array[1][2] = "Ответ22"; array[1][3] = "Ответ23";array[1][4] = "Ответ24";
        array[2][0] = "Вопрос03"; array[2][1] = "Ответ31"; array[2][2] = "Ответ32"; array[2][3] = "Ответ33";array[2][4] = "Ответ34";

        // Массив корректности ответов
        correct = new int[10];
        correct[0]=1; correct[1]=2;correct[2]=3;correct[3]=1;


        // Скрытие полей счетчик и ответы из видимости
        layoutTextcounter.setVisibility(View.INVISIBLE);
        layoutAnswers.setVisibility(View.INVISIBLE);

        // Установка текста счетчика ответов
//        textCounter.setText("Вопрос №" + (counter+1));



    }


    // Метод активации следующего вопроса на текстовом контейнере вопроса
    public void next_question(View view){

        // Обновление поля счетчика
        textCounter.setText("Вопрос №" + (counter+1));

        // Установка цвета текса и фона
        question.setTextColor(Color.BLACK);
        layout.setBackgroundColor(Color.WHITE);

        // Обновление текста вопроса и ответа
        question.setText(array[counter][0]);
        answer1.setText(array[counter][1]);
        answer2.setText(array[counter][2]);
        answer3.setText(array[counter][3]);
        answer4.setText(array[counter][4]);

        // Включение видимости полей счетчик и ответы
        layoutTextcounter.setVisibility(View.VISIBLE);
        layoutAnswers.setVisibility(View.VISIBLE);

        textComment.setVisibility(View.INVISIBLE);


        // Отключение нажатия на поле вопроса
        layout.setEnabled(false);
    }

    // Метод для клика на ответ
    public void button_answer_click(View view) {

        // Выбор клика на соотвествующий ответ
        switch (view.getId()){
            case R.id.button_answer1:
                processing(correct[counter]==1);
                Toast.makeText(this, array[counter][1], Toast.LENGTH_LONG).show();
                break;
            case R.id.button_answer2:
                processing(correct[counter]==2);
                Toast.makeText(this, array[counter][2], Toast.LENGTH_LONG).show();
                break;
            case R.id.button_answer3:
                processing(correct[counter]==3);
                Toast.makeText(this, array[counter][3], Toast.LENGTH_LONG).show();
                break;
            case R.id.button_answer4:
                processing(correct[counter]==4);
                Toast.makeText(this, array[counter][4], Toast.LENGTH_LONG).show();
                break;
            default:
        }

        // Toast - всплывающее сообщение

    }


    // Обработка корректности ответа
    public void processing(boolean c){
        if(c){
            question.setText("ВЕРНО");
            question.setTextColor(Color.WHITE);
            layout.setBackgroundColor(Color.GREEN);
            layout.setEnabled(true);

            // Комментарий
            textComment.setText("следующий вопрос");
            textComment.setVisibility(View.VISIBLE);

            // Изменение счетчика вопроса
            String text = array[counter][0];
            String text1 = array[counter+1][0];

            if(array[counter+1][0] != null && counter+1 < array.length ) {
                counter++;
            }
            else {

                // Установка текста результата в центральном поле
                question.setText("ПОБЕДА");

                // Скрытие полей счетчик и ответы из видимости
                layoutTextcounter.setVisibility(View.INVISIBLE);
                layoutAnswers.setVisibility(View.INVISIBLE);

                // Установка комментария
                textComment.setText("пройти заново!");

                // Сброс счетчика
                counter=0;
            }

        }
        else {
            // Установка текста результата
            question.setText("НЕВЕРНО! Игра окончена!");
            question.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            layout.setBackgroundColor(Color.RED);

            // Скрытие полей счетчик и ответы из видимости
            layoutTextcounter.setVisibility(View.INVISIBLE);
            layoutAnswers.setVisibility(View.INVISIBLE);

            // Установка комментария
            textComment.setText("пройти заново!");
            // Включение видимости комментария
            textComment.setVisibility(View.VISIBLE);

            // Сброс счетчика
            counter=0;

            // Активация нажатия
            layout.setEnabled(true);
        }
    }



}
