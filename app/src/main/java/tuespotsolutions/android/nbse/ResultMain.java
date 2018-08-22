package tuespotsolutions.android.nbse;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class ResultMain extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle b){
        super.onCreate(b);
        setContentView(R.layout.result_main);

        final EditText rollno = findViewById(R.id.rollno) ;
        final EditText dob = findViewById(R.id.dob);
        Button submit_result = findViewById(R.id.submit_result);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar); //toolbar 1
        TextView textView=findViewById(R.id.title);  //toolbar1
        textView.setText(getString(R.string.result)); //TOOLBAR1

        submit_result.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {


                Intent intent= new Intent(ResultMain.this, Result_show.class);
                intent.putExtra("rollno", rollno.getText().toString());
                intent.putExtra("dob", dob.getText().toString());
                startActivity(intent);

                System.err.println("Roll No."+rollno.getText());
                System.err.println("Date of Birth"+dob.getText());
            }
        });


        setSupportActionBar(toolbar);    //toolbar 1

        // add back arrow to toolbar   //toolbar 1
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

    }


}
