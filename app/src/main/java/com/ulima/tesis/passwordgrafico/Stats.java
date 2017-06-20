package com.ulima.tesis.passwordgrafico;

import android.app.ProgressDialog;
import android.content.Intent;
import android.net.Uri;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
//import com.opencsv.CSVWriter;
import com.google.firebase.database.ValueEventListener;
import com.ulima.tesis.passwordgrafico.Beans.PassWordP;
import com.ulima.tesis.passwordgrafico.Beans.Puntos;
import com.ulima.tesis.passwordgrafico.PassPoints.PassVerif;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Stats extends AppCompatActivity {

    private TextView val1,val2,val3,val4,val5,val6;
    private FirebaseDatabase database;

    private List<PassWordP> listPuntos;
    private List<Puntos> puntos ;
    private Intent prevIntent;
    private ProgressDialog dialog;
    private int num;
    private String estat;
    private int cont;
    private int valores;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_stats);

        setTitle("Estadística");

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        prevIntent = getIntent();
        num = prevIntent.getIntExtra("num",0);
        listPuntos = (List<PassWordP>) prevIntent.getSerializableExtra("Pprev");
        puntos = (List<Puntos>)prevIntent.getSerializableExtra("puntos");
        String keyP = prevIntent.getStringExtra("key");

        database = FirebaseDatabase.getInstance();

        dialog = new ProgressDialog(Stats.this);
        //dialog.setProgressStyle(ProgressDialog.STYLE_HORIZONTAL);
        dialog.setMessage("Pensando");
        dialog.setIndeterminate(true);
        dialog.setCanceledOnTouchOutside(false);
        dialog.show();

        DatabaseReference Usuarioref = database.getReference().child("Resultados-PassP").child(""+num);
        String key = keyP; //Usuarioref.push().getKey();

        /*String baseDir = android.os.Environment.getExternalStorageDirectory().getAbsolutePath();
        String fileName = key+".csv";
        String filePath = baseDir + File.separator + fileName;

        File f = new File(filePath );
        CSVWriter writer = null;
        // File exist
        try {
            writer = new CSVWriter(new FileWriter(filePath));
        } catch (IOException e) {
            e.printStackTrace();
        }

        List<String[]> reporte = new ArrayList<>();*/

        Double valpor = 0d;
        List<Double> porc = new ArrayList<>();
        int cont = 0;
        //reporte.add(new String[] {"punto","densidad","X","Vx","punto","densidad","Y","Vy","Porcentaje"});
        for (PassWordP ps : listPuntos) {
            double a=0;
            for (Puntos punto : puntos) {

                int x = Math.round(((float)punto.getPx()));
                int y = Math.round(((float)punto.getPy()));
                //reporte.add(new String[] {punto.getPx()+"",ps.getDensidad().get(0)+"",""+x,""+ps.getPos().get(0),punto.getPy()+"",ps.getDensidad().get(1)+"",""+y,""+ps.getPos().get(1),""+punto.getPor()});
                if ( ps.getPos().get(0).equals(x) && ps.getPos().get(1).equals(y) ){
                    //cont++;
                    //a = a +  Float.valueOf(punto.getPor());
                    a = Float.valueOf(punto.getPor());
                    break;
                }

            }
            //reporte.add(new String[] {"------------"});
            //reporte.add(new String[] {""+a,""+cont});
            /*double v;
            if (cont ==0){
               v = 0;
            }else{
               v  = a/cont;
            }*/
            porc.add(a);
            valpor += a;
            if (a > 0.d){
                cont++;
            }

        }


        /*writer.writeAll(reporte);

        try {
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }*/
        obtenerCont();



        Usuarioref.child(key).child("Total").setValue(String.format("%.2f", (valpor/5)));
        Usuarioref.child(key).child("PassP").setValue(keyP);
        Usuarioref.child(key).child("data").child("0").setValue(String.format("%.2f", porc.get(0)));
        Usuarioref.child(key).child("data").child("1").setValue(String.format("%.2f", porc.get(1)));
        Usuarioref.child(key).child("data").child("2").setValue(String.format("%.2f", porc.get(2)));
        Usuarioref.child(key).child("data").child("3").setValue(String.format("%.2f", porc.get(3)));
        Usuarioref.child(key).child("data").child("4").setValue(String.format("%.2f", porc.get(4)));

        val1 = (TextView)findViewById(R.id.val1);
        val2 = (TextView)findViewById(R.id.val2);
        val3 = (TextView)findViewById(R.id.val3);
        val4 = (TextView)findViewById(R.id.val4);
        val5 = (TextView)findViewById(R.id.val5);
        val6 = (TextView)findViewById(R.id.val6);

        dialog.dismiss();

        /*val1.setText("Total: "+String.format("%.2f", (valpor/5))+" %");
        val2.setText("Punto 1: "+String.format("%.2f", porc.get(0))+" %");
        val3.setText("Punto 2: "+String.format("%.2f", porc.get(1))+" %");
        val4.setText("Punto 3: "+String.format("%.2f", porc.get(2))+" %");
        val5.setText("Punto 4: "+String.format("%.2f", porc.get(3))+" %");
        val6.setText("Punto 5: "+String.format("%.2f", porc.get(4))+" %");*/

        val2.setText("El modelo predijo:");
        val3.setText(cont+ " de 5 puntos seleccionados");
        val4.setText("Un "+(cont*100)/5+" %");
        val5.setVisibility(View.GONE);
        val6.setVisibility(View.GONE);
        //val6.setVisibility(View.GONE);

        Usuarioref = database.getReference().child("Acum").child(""+num);
        //key = Usuarioref.push().getKey();
        Usuarioref.child(keyP).setValue(cont);





        /*Uri u1 =   Uri.fromFile(f);


        Intent sendIntent = new Intent(Intent.ACTION_SEND);
        sendIntent.putExtra(Intent.EXTRA_SUBJECT, "Reporte");
        sendIntent.putExtra(Intent.EXTRA_STREAM, u1);
        sendIntent.setType("text/richtext");
        startActivity(sendIntent);*/

    }



    public void obtenerCont(){


        DatabaseReference Usuarioref = database.getReference().child("Acum").child(""+num);
        Usuarioref.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                cont=0;
                valores = 0;
                for (DataSnapshot ds : dataSnapshot.getChildren()){
                    cont++;
                    valores = valores + ds.getValue(Integer.class);
                }
                float vel = ((float)valores)/((float)(cont*5));
                estat = String.format("%.2f", (vel*100))+" %";
                val1.setText("Predictibilidad Total de la imagen: "+estat);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

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
        }

        return super.onOptionsItemSelected(item);

    }
}
