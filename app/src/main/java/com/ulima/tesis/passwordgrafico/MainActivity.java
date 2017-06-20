package com.ulima.tesis.passwordgrafico;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

import com.ulima.tesis.passwordgrafico.Blonder.BlonderMain;
import com.ulima.tesis.passwordgrafico.PassPoints.PassMain;

import java.util.Random;

public class MainActivity extends AppCompatActivity {



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Button but = (Button)findViewById(R.id.btnIng);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                /*Random randito = new Random();
                int num = randito.nextInt(2) +1;
                Intent intent;

                if (num == 1){
                    intent = new Intent(getApplicationContext(), BlonderMain.class);
                }else{
                    intent = new Intent(getApplicationContext(), PassMain.class);
                }*/
                Intent intent;
                intent = new Intent(getApplicationContext(), PassMain.class);
                startActivity(intent);
            }
        });


    }


}
