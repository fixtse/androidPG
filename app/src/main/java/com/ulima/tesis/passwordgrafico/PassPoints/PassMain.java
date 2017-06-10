package com.ulima.tesis.passwordgrafico.PassPoints;

import android.content.Intent;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Button;
import android.widget.GridLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ulima.tesis.passwordgrafico.Beans.PassWordP;
import com.ulima.tesis.passwordgrafico.MyView;
import com.ulima.tesis.passwordgrafico.R;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class PassMain extends AppCompatActivity implements MyView.OnToggledListener {

    private MyView[] myViews;
    private TextView tvTam, tvTamP;
    private GridLayout myGridLayout;
    private int w,h,wp,hp;
    private List<PassWordP> listPuntos = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("PassPoints");
        setContentView(R.layout.activity_pass_main);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        myGridLayout = (GridLayout)findViewById(R.id.mygrid);
        tvTam = (TextView)findViewById(R.id.tvTam);
        tvTamP = (TextView)findViewById(R.id.tvTamP);

        int numOfCol = myGridLayout.getColumnCount();
        int numOfRow = myGridLayout.getRowCount();
        myViews = new MyView[numOfCol*numOfRow];
        for(int yPos=0; yPos<numOfRow; yPos++){
            for(int xPos=0; xPos<numOfCol; xPos++){
                MyView tView = new MyView(this, xPos, yPos);
                tView.setOnToggledListener(this);
                myViews[yPos*numOfCol + xPos] = tView;
                myGridLayout.addView(tView);
            }

        }


        w = dpToPx(390);
        h = dpToPx(320);
        wp=w/25;
        hp=h/20;
        tvTam.setText(w+"x"+h);
        tvTamP.setText(25*20+" secciones de "+wp+"x"+hp);

        myGridLayout.getViewTreeObserver().addOnGlobalLayoutListener(
                new ViewTreeObserver.OnGlobalLayoutListener(){

                    @Override
                    public void onGlobalLayout() {

                        final int MARGIN = 0;

                        int pWidth = myGridLayout.getWidth();
                        int pHeight = myGridLayout.getHeight();
                        int numOfCol = myGridLayout.getColumnCount();
                        int numOfRow = myGridLayout.getRowCount();
                        int w = pWidth/numOfCol;
                        int h = pHeight/numOfRow;

                        for(int yPos=0; yPos<numOfRow; yPos++){
                            for(int xPos=0; xPos<numOfCol; xPos++){
                                GridLayout.LayoutParams params =
                                        (GridLayout.LayoutParams)myViews[yPos*numOfCol + xPos].getLayoutParams();
                                params.width = w - 2*MARGIN;
                                params.height = h - 2*MARGIN;
                                params.setMargins(MARGIN, MARGIN, MARGIN, MARGIN);
                                myViews[yPos*numOfCol + xPos].setLayoutParams(params);
                            }
                        }

                    }});

        Button but = (Button)findViewById(R.id.btnIng);
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listPuntos.size()!= 5){
                    Toast.makeText(PassMain.this,"Debe seleccionar 5 puntos de contraseña"
                            ,Toast.LENGTH_SHORT).show();
                }else{
                    Intent intent = new Intent(getApplicationContext(),PassVerif.class);
                    intent.putExtra("Pprev",(Serializable)listPuntos);
                    startActivity(intent);
                }
            }
        });
    }

    @Override
    public void OnToggled(MyView v, boolean touchOn) {

        //get the id string
        /*String idString = (v.getIdX()+1) + ":" + (v.getIdY()+1);
        String pixel = ((v.getIdX()*wp)+1)+"x"+((v.getIdY()*hp)+1)+" ";
        String real = (int)(v.getrX()+(v.getIdX()*wp)+1)+":"+(int)(v.getrY()+(v.getIdY()*hp)+1);

        Toast.makeText(PassMain.this,
                "Seleccionado:\n" +
                        idString + "\n" +
                        pixel+touchOn+"\n"+
                        real,
                Toast.LENGTH_SHORT).show();*/


        if (touchOn){
            if (listPuntos.size()<5){
            PassWordP p = new PassWordP();
            List<Integer> val = new ArrayList<>();
            val.add(v.getIdX());
            val.add(v.getIdY());
            p.setPos(val);
            List<Integer> val2 = new ArrayList<>();
            val2.add(wp);
            val2.add(hp);
            p.setDensidad(val2);
            List<Integer> val3 = new ArrayList<>();
            val3.add((int)(v.getrX()+(v.getIdX()*wp)+1));
            val3.add((int)(v.getrY()+(v.getIdY()*hp)+1));
            p.setPreal(val3);

            listPuntos.add(p);

            }else{
                Toast.makeText(PassMain.this,"Solo se usaran los 5 primeros toques"
                        ,Toast.LENGTH_SHORT).show();

            }
        }else{
            if (listPuntos.size() > 0){
                List<PassWordP> list2 = new ArrayList<>();
                for (PassWordP ps : listPuntos){
                    if(ps.getPos().get(0) == v.getIdX() && ps.getPos().get(1) == v.getIdY()){
                        list2.add(ps);
                        Toast.makeText(PassMain.this,"Eliminado",Toast.LENGTH_SHORT).show();
                    }
                }
                if (!list2.isEmpty()){
                    listPuntos.remove(list2.get(0));
                }

            }
        }


    }

    public int dpToPx(int dp) {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater=getMenuInflater();
        inflater.inflate(R.menu.menu_exportar, menu);
        return super.onCreateOptionsMenu(menu);
    }

    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            // Respond to the action bar's Up/Home button
            case android.R.id.home:
                if (getParentActivityIntent() == null) {
                    Log.i("TAG", "You have forgotten to specify the parentActivityName in the AndroidManifest!");
                    onBackPressed();
                } else {
                    NavUtils.navigateUpFromSameTask(this);
                }
                return true;
            case R.id.Mexportar:
                Intent intent = new Intent(getApplicationContext(),PassMain.class);
                startActivity(intent);
                finish();
        }

        return super.onOptionsItemSelected(item);

    }
}
