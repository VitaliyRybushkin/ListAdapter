package com.example.lab3;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Toast;
import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    static ArrayList<State> states = new ArrayList<State>();
    private State current = null;
    ListView somethingList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // начальная инициализация списка
        if(states.size()==0) {
            setInitialData();
        }
        // получаем элемент ListView
        somethingList = findViewById(R.id.somethingList);
        // создаем адаптер
        StateAdapter stateAdapter = new StateAdapter(this, R.layout.list_item, states);
        // устанавливаем адаптер
        somethingList.setAdapter(stateAdapter);
        // слушатель выбора в списке
        AdapterView.OnItemClickListener itemListener = new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View v, int position, long id) {

                // получаем выбранный пункт
                State selectedState = (State)parent.getItemAtPosition(position);
                current=selectedState;

                Toast.makeText(getApplicationContext(), "Выбрана новость \"" + selectedState.getName() + "\" ",
                        Toast.LENGTH_SHORT).show();
                try {
                    Thread.sleep(560);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                Toast.makeText(getApplicationContext(), selectedState.getInfo() ,
                        Toast.LENGTH_SHORT).show();
            }
        };
        somethingList.setOnItemClickListener(itemListener);
        Button act2 = (Button)findViewById(R.id.button);
        Button del = (Button)findViewById(R.id.button3);

        del.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(current != null) {
                    if(states.contains(current)) {
                        states.remove(current);
                        recreate();
                    }
                }
                else{
                    Toast.makeText(getApplicationContext(), "НЕ ВЫБРАНА НОВОСТЬ ДЛЯ УДАЛЕНИЯ",
                            Toast.LENGTH_SHORT).show();
                }
            }
        });



        act2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, CreateActivity.class);
                startActivity(intent);
            }
        });
    }

    private void setInitialData(){

        states.add(new State ("Пожар", "Вчера сгорел дом\nУжас!", R.drawable.fire));
        states.add(new State ("Чертежи домов", "Купите макет от дизайнера\nВсего 3 млн. рублей", R.drawable.house));
        states.add(new State ("Продам гараж", "Село Бычьи\n200 м2 \nЧисто, обшит дубом ", R.drawable.house_color));
        states.add(new State ("Летим на Марс", "Макс Инолович перебрал с метилом.\nСмотреть видео", R.drawable.planet));
        states.add(new State ("Смерч", "Скупили всю бумагу!", R.drawable.tornado));
    }
}