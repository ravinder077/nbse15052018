package tuespotsolutions.android.nbse;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.MenuItem;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

public class Courses extends AppCompatActivity {

    private LinearLayout sec, s_sec, dip;

    @Override
    protected void onCreate(Bundle b) {

        super.onCreate(b);
        setContentView(R.layout.courses);

        sec = findViewById(R.id.secondary);
        s_sec = findViewById(R.id.s_secondary);
        dip = findViewById(R.id.diploma);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar); //toolbar 1
        TextView textView=findViewById(R.id.title);  //toolbar1
        textView.setText("COURSE"); //TOOLBAR1
        sec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Courses_Show.class);

                i.putExtra("cname","sec");


                startActivity(i);
            }
        });

        s_sec.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Courses_Show.class);
                i.putExtra("cname","s_sec");
                startActivity(i);
            }
        });
        dip.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getApplicationContext(),Courses_Show.class);
                i.putExtra("cname","dip");
                startActivity(i);
            }
        });

        setSupportActionBar(toolbar);    //toolbar 1

        // add back arrow to toolbar   //toolbar 1
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        //toolbar 1 ends

    }



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // handle arrow click here
        if (item.getItemId() == android.R.id.home) {
            finish(); // close this activity and return to preview activity (if there is any)
        }

        return super.onOptionsItemSelected(item);
    }
}
