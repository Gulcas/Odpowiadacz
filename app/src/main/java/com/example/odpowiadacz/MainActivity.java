package com.example.odpowiadacz;


import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Spinner;
import android.widget.TextView;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

public class MainActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void answareClick(View view) { //reakcja na klikniecie przycisku
        TextView answare = findViewById(R.id.answareText); //referencja do istniejącego widoku textview

        Spinner question = findViewById(R.id.question); //referensja do spinner'a z pytaniami
        String odp = String.valueOf(question.getSelectedItem()); //pobiera pytanie ze spinera i konwertuje je na stringa
        // answare.setText(odp); //wyrzuca w textview odpowiedź taką sama jak pytanie

        Odp odpowiadacz = new Odp();
        List<String> listaOdp = odpowiadacz.getOdp(odp);
        StringBuilder odpGotowe = new StringBuilder();
        for (String pytanie : listaOdp) {
            odpGotowe.append(pytanie).append('\n');
        }
        answare.setText(odpGotowe);
    }

    private class Odp {
        List<String> getOdp(String question) {
            List<String> odpowiedzi = new ArrayList<>();
            Calendar day = Calendar.getInstance();
            String dayString = "" + day.get(Calendar.DAY_OF_WEEK);
            String today = "";
            SimpleDateFormat formatTime = new SimpleDateFormat("HH:mm:ss"); //sformatowanie pobranej daty do rządanego formatu
            String timeDay = formatTime.format(day.getTime()); //zapisanie daty w zmiennej string
            switch (dayString) {
                case "1":
                    today = "niedziela";
                    break;
                case "3":
                    today = "wtorek";
                    break;
                case "4":
                    today = "środa";
                    break;
                case "5":
                    today = "czwartek";
                    break;
                case "6":
                    today = "piątek";
                    break;
                case "7":
                    today = "sobota";
                    break;
            }
            if (question.equals(getResources().getStringArray(R.array.questionsArray)[0])) {
                odpowiedzi.add("Wybierz pytanie dupo");
            } else if (question.equals(getResources().getStringArray(R.array.questionsArray)[1])) {
                if (dayString.equals("2")) {
                    odpowiedzi.add("Tak, dziś jest Poniedziałek");
                } else {
                    odpowiedzi.add("Nie, dziś jest " + today);
                }
            } else if (question.equals(getResources().getStringArray(R.array.questionsArray)[2])) {
                odpowiedzi.add("Dziś jest " + today);
            } else if (question.equals(getResources().getStringArray(R.array.questionsArray)[3])) {
                odpowiedzi.add("Pracuje dla Zalando");
            } else if (question.equals(getResources().getStringArray(R.array.questionsArray)[4])) {
                odpowiedzi.add("Nazywam się Rafał");
            } else if (question.equals(getResources().getStringArray(R.array.questionsArray)[5])) {
                odpowiedzi.add("A lubię Heineken");
            } else if (question.equals(getResources().getStringArray(R.array.questionsArray)[6])) {
                odpowiedzi.add("Jest " + timeDay);
            }
            return odpowiedzi;
        }
    }
}
