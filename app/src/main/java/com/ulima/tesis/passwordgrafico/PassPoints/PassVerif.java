package com.ulima.tesis.passwordgrafico.PassPoints;

import android.app.ProgressDialog;
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

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.ulima.tesis.passwordgrafico.Beans.PassWordP;
import com.ulima.tesis.passwordgrafico.Beans.Puntos;
import com.ulima.tesis.passwordgrafico.MainActivity;
import com.ulima.tesis.passwordgrafico.MyView;
import com.ulima.tesis.passwordgrafico.R;
import com.ulima.tesis.passwordgrafico.Stats;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;


public class PassVerif extends AppCompatActivity implements MyView.OnToggledListener{

    private MyView[] myViews;
    private TextView tvTam, tvTamP;
    private GridLayout myGridLayout;
    private int w,h,wp,hp;
    private String key;

    private FirebaseDatabase database;
    private DatabaseReference passRef;
    private List<PassWordP> listPuntos = new ArrayList<>();
    private List<PassWordP> listPuntosPrev;
    private Intent prevIntent;
    private List<Puntos> puntos ;
    private ProgressDialog dialog;
    private int num;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setTitle("Vuelva a ingresar su contraseña");
        setContentView(R.layout.activity_pass_main);
        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        database = FirebaseDatabase.getInstance();
        prevIntent = getIntent();
        listPuntosPrev = (List<PassWordP>) prevIntent.getSerializableExtra("Pprev");
        /*Toast.makeText(PassVerif.this,""+listPuntosPrev.size()
                ,Toast.LENGTH_SHORT).show();*/

        myGridLayout = (GridLayout)findViewById(R.id.mygrid);

        num = prevIntent.getIntExtra("num",0);
        if (num ==1){
            myGridLayout.setBackgroundResource(R.drawable.callealfonso);
        }else{
            myGridLayout.setBackgroundResource(R.drawable.p3);
        }


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
        but.setText("Verificar");
        but.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (listPuntos.size()!= 5){
                    Toast.makeText(PassVerif.this,"Debe seleccionar 5 puntos de contraseña"
                            ,Toast.LENGTH_SHORT).show();
                }else{
                    comparar();
                }
            }
        });


    }


    @Override
    public void OnToggled(MyView v, boolean touchOn) {

        if (touchOn){
            if (listPuntos.size()<5){
                PassWordP p = new PassWordP();
                List<Integer> val = new ArrayList<>();
                val.add(v.getIdX()+1);
                val.add(v.getIdY()+1);
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
                Toast.makeText(PassVerif.this,"Solo se usaran los 5 primeros toques"
                        ,Toast.LENGTH_SHORT).show();

            }
        }else{
            if (listPuntos.size() > 0){
                List<PassWordP> list2 = new ArrayList<>();
                for (PassWordP ps : listPuntos){
                    if(ps.getPos().get(0) == (v.getIdX()+1) && ps.getPos().get(1) == (v.getIdY()+1)){
                        list2.add(ps);
                        Toast.makeText(PassVerif.this,"Eliminado",Toast.LENGTH_SHORT).show();
                    }
                }
                if (!list2.isEmpty()){
                    listPuntos.remove(list2.get(0));
                }

            }
        }


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
                Intent intent = new Intent(getApplicationContext(),PassVerif.class);
                intent.putExtra("Pprev",(Serializable)listPuntosPrev);
                startActivity(intent);
                finish();
        }

        return super.onOptionsItemSelected(item);

    }

    public int dpToPx(int dp) {
        DisplayMetrics displayMetrics = getResources().getDisplayMetrics();
        return Math.round(dp * (displayMetrics.xdpi / DisplayMetrics.DENSITY_DEFAULT));
    }

    public void comparar(){
        if (listPuntosPrev.size() > 0 ){
            int cont = 0;
            for (PassWordP ps : listPuntos){
                for(PassWordP pa : listPuntosPrev){
                    //Toast.makeText(PassVerif.this,ps.getPos().get(0).equals(pa.getPos().get(0))+" "+ps.getPos().get(1).equals(pa.getPos().get(1)),Toast.LENGTH_SHORT).show();
                    if (ps.getPos().get(0).equals(pa.getPos().get(0)) && ps.getPos().get(1).equals(pa.getPos().get(1))){
                        cont++;
                    }
                }
            }
            if (cont==5){
                passRef = database.getReference().child("Contraseñas-PassPoints").child(""+num);
                key = passRef.push().getKey();
                passRef.child(key).setValue(listPuntos);

                estat();

            }else{
                Toast.makeText(PassVerif.this,"Las contraseñas no coinciden, vuelva a intentar",Toast.LENGTH_SHORT).show();
                finish();
            }
        }
    }

    public void estat(){

        dialog = new ProgressDialog(PassVerif.this);
        //dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        dialog.setMessage("Obteniendo predicciones PassPoints");
        dialog.setIndeterminate(true);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

        DatabaseReference obtP = database.getReference().child("PSimp-PassP").child(""+num);
        obtP.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                puntos = new ArrayList<>();

                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    puntos.add(ds.getValue(Puntos.class));
                }
                mostrar();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public void mostrar(){
        //Toast.makeText(PassVerif.this,""+puntos.size(),Toast.LENGTH_SHORT).show();
        dialog.dismiss();
        Intent intent;
        if (1==1){
            intent = new Intent(getApplicationContext(),Stats.class);
            intent.putExtra("puntos",(Serializable)puntos);
            intent.putExtra("Pprev",(Serializable)listPuntos);
            intent.putExtra("key",key);
            intent.putExtra("num",num);
            startActivity(intent);

        }else{
            intent = new Intent(getApplicationContext(), MainActivity.class);
            startActivity(intent);
            finish();
            Toast.makeText(PassVerif.this,"Contraseña registrada",Toast.LENGTH_SHORT).show();
        }


    }
}
