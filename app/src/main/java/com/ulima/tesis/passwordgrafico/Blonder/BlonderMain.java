package com.ulima.tesis.passwordgrafico.Blonder;

import android.content.Intent;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.LightingColorFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.NavUtils;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.ulima.tesis.passwordgrafico.PassPoints.PassMain;
import com.ulima.tesis.passwordgrafico.R;

public class BlonderMain extends AppCompatActivity {

    private TextView tvTam, tvTamP;
    private RelativeLayout rel;
    private ImageView im1,im2,im3,im4,im5,im6,im7,im8;
    private int w,h,wp,hp;
    private boolean b1,b2,b3,b4,b5,b6,b7,b8;
    private float rx,ry;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_blonder_main);
        setTitle("Blonder");

        getSupportActionBar().setHomeButtonEnabled(true);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);

        tvTam = (TextView)findViewById(R.id.tvTam);
        tvTamP = (TextView)findViewById(R.id.tvTamP);
        rel = (RelativeLayout)findViewById(R.id.mygrid);

        w = dpToPx(390);
        h = dpToPx(320);
        wp=w/25;
        hp=h/20;
        tvTam.setText(w+"x"+h);
        tvTamP.setText(25*20+" secciones de "+wp+"x"+hp);

        im1 = (ImageView)findViewById(R.id.im1);
        b1=false;
        im1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!b1){
                    Drawable myIcon = getResources().getDrawable( R.drawable.sol );
                    ColorFilter filter = new LightingColorFilter( Color.RED, Color.BLUE );
                    myIcon.setColorFilter(filter);
                    im1.setImageDrawable(myIcon);
                    b1=true;
                }else{
                    Drawable myIcon = getResources().getDrawable( R.drawable.sol );
                    myIcon.clearColorFilter();
                    im1.setImageDrawable(myIcon);
                    b1=false;
                }
            }
        });

        b2=false;
        im2 = (ImageView)findViewById(R.id.im2);
        im2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!b2){
                    Drawable myIcon = getResources().getDrawable( R.drawable.boy );
                    ColorFilter filter = new LightingColorFilter( Color.RED, Color.BLUE );
                    myIcon.setColorFilter(filter);
                    im2.setImageDrawable(myIcon);
                    b2=true;
                }else{
                    Drawable myIcon = getResources().getDrawable( R.drawable.boy );
                    myIcon.clearColorFilter();
                    im2.setImageDrawable(myIcon);
                    b2=false;
                }
            }
        });

        b3=false;
        im3 = (ImageView)findViewById(R.id.im3);
        im3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!b3){
                    Drawable myIcon = getResources().getDrawable( R.drawable.bike );
                    ColorFilter filter = new LightingColorFilter( Color.RED, Color.BLUE );
                    myIcon.setColorFilter(filter);
                    im3.setImageDrawable(myIcon);
                    b3=true;
                }else{
                    Drawable myIcon = getResources().getDrawable( R.drawable.bike);
                    myIcon.clearColorFilter();
                    im3.setImageDrawable(myIcon);
                    b3=false;
                }
            }
        });

        b4=false;
        im4 = (ImageView)findViewById(R.id.im4);
        im4.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!b4){
                    Drawable myIcon = getResources().getDrawable( R.drawable.tree );
                    ColorFilter filter = new LightingColorFilter( Color.RED, Color.BLUE );
                    myIcon.setColorFilter(filter);
                    im4.setImageDrawable(myIcon);
                    b4=true;
                }else{
                    Drawable myIcon = getResources().getDrawable( R.drawable.tree );
                    myIcon.clearColorFilter();
                    im4.setImageDrawable(myIcon);
                    b4=false;
                }
            }
        });

        b5=false;
        im5 = (ImageView)findViewById(R.id.im5);
        im5.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!b5){
                    Drawable myIcon = getResources().getDrawable( R.drawable.clock9);
                    ColorFilter filter = new LightingColorFilter( Color.RED, Color.BLUE );
                    myIcon.setColorFilter(filter);
                    im5.setImageDrawable(myIcon);
                    b5=true;
                }else{
                    Drawable myIcon = getResources().getDrawable( R.drawable.clock9);
                    myIcon.clearColorFilter();
                    im5.setImageDrawable(myIcon);
                    b5=false;
                }
            }
        });

        b6=false;
        im6 = (ImageView)findViewById(R.id.im6);
        im6.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!b6){
                    Drawable myIcon = getResources().getDrawable( R.drawable.ant );
                    ColorFilter filter = new LightingColorFilter( Color.RED, Color.BLUE );
                    myIcon.setColorFilter(filter);
                    im6.setImageDrawable(myIcon);
                    b6=true;
                }else{
                    Drawable myIcon = getResources().getDrawable( R.drawable.ant);
                    myIcon.clearColorFilter();
                    im6.setImageDrawable(myIcon);
                    b6=false;
                }
            }
        });

        b7=false;
        im7 = (ImageView)findViewById(R.id.im7);
        im7.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!b7){
                    Drawable myIcon = getResources().getDrawable( R.drawable.rocket );
                    ColorFilter filter = new LightingColorFilter( Color.RED, Color.BLUE );
                    myIcon.setColorFilter(filter);
                    im7.setImageDrawable(myIcon);
                    b7=true;
                }else{
                    Drawable myIcon = getResources().getDrawable( R.drawable.rocket );
                    myIcon.clearColorFilter();
                    im7.setImageDrawable(myIcon);
                    b7=false;
                }
            }
        });

        rel.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View v, MotionEvent event) {
                rx = event.getX();
                ry = event.getY();
                switch (event.getAction()) {
                    case MotionEvent.ACTION_DOWN:

                        Toast.makeText(BlonderMain.this,rx+" "+ry
                                ,Toast.LENGTH_SHORT).show();


                        return true;

                    case MotionEvent.ACTION_UP:

                        Toast.makeText(BlonderMain.this,rx+" "+ry
                                ,Toast.LENGTH_SHORT).show();
                        return true;

                }
                return false;

            }
        });



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
                Intent intent = new Intent(getApplicationContext(),BlonderMain.class);
                startActivity(intent);
                finish();
        }

        return super.onOptionsItemSelected(item);

    }

}
