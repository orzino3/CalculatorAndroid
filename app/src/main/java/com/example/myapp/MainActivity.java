package com.example.myapp;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;


public class MainActivity extends AppCompatActivity {

    TextView textView;
    double num1 = 0;
    String operator = "";
    boolean isEqualClicked = false;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textView = findViewById(R.id.textViewResult);
    }

    public void printDigit(View view) {
        Button clickedButton = (Button) view;

        if(isEqualClicked){
            textView.setText(clickedButton.getText());
        }
        else {
            textView.append(clickedButton.getText());

        }
    }

    public void clear(View view){
        textView.setText("");
        num1 = 0;
        operator = "";
        isEqualClicked = false;
    }

    public void calc(View view) {
        Button actionButton = (Button) view;
        operator = actionButton.getText().toString();

        num1 = Integer.parseInt(textView.getText().toString());
        textView.setText("");
    }

    public void equal(View view){
        double num2 = Integer.parseInt(textView.getText().toString());
        double result = 0;
        isEqualClicked = true;

        switch (operator){
            case "+":
                result = num1 + num2;
                break;
            case "-":
                result = num1 - num2;
                break;
            case "X":
                result = num1 * num2;
                break;
            case "/":
                if (num2 != 0){
                    if(num1 % num2 != 0){
                         result =  num1 / num2;
                    }
                    else{
                        result = (int) (num1 / num2);
                    }
                }
                else{
                    textView.setText("Error");
                    return;
                }
                break;
        }

        if (result == (int) result) {
            textView.setText(String.valueOf((int) result));
        } else {
            textView.setText(String.format("%.2f", result));
        }
    }


}
