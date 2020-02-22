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

    TextView textCounter;

    TextView question;
    Button answer1;
    Button answer2;
    Button answer3;
    Button answer4;

    LinearLayout layout;


    String[][] array;
    int[] correct;
    int counter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        layout = findViewById(R.id.question_layout);

        textCounter = findViewById(R.id.textCounter);
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
        correct = new int[10];
        correct[0]=1; correct[1]=2;correct[2]=3;correct[3]=1; correct[4]=2;correct[5]=3;

        textCounter.setText("Вопрос №" + (counter+1));
        question.setText(array[0][0]);
        answer1.setText(array[0][1]);
        answer2.setText(array[0][2]);
        answer3.setText(array[0][3]);
        answer4.setText(array[0][4]);

        layout.setEnabled(false);

    }

    public void button_answer1_click(View view) {

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

    }


    public void processing(boolean c){
        if(c){
            question.setText("ВЕРНО");
            question.setTextColor(Color.WHITE);
            layout.setBackgroundColor(Color.GREEN);
            layout.setEnabled(true);
        }
        else {
            question.setText("НЕВЕРНО! Игра окончена!");
            layout.setBackgroundColor(Color.RED);
        }
    }

    public void next_question(View view){
        counter++;

        textCounter.setText("Вопрос №" + (counter+1));

        question.setTextColor(Color.BLACK);
        layout.setBackgroundColor(Color.WHITE);

        question.setText(array[counter][0]);
        answer1.setText(array[counter][1]);
        answer2.setText(array[counter][2]);
        answer3.setText(array[counter][3]);
        answer4.setText(array[counter][4]);
    }

}
