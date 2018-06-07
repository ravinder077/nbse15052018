package tuespotsolutions.android.nbse;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.MenuItem;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.HashMap;

public class Courses_Show extends AppCompatActivity {

     private  RecyclerView recyclerView;
     private ArrayList<Course_Model> clist;
     static Course_Adapter course_adapter;
     private LinearLayout outer;
     private   TextView textView;
     private String coursesName;
     private String cname;





    @Override
    protected void onCreate(Bundle b)
    {
        super.onCreate(b);
        setContentView(R.layout.course_recycler);


        LinearLayout myfirst=findViewById(R.id.myfirstlinear);

        myfirst.setBackgroundColor(Color.GREEN);



        recyclerView = findViewById(R.id.recycler_course);
        clist = new ArrayList<>();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        course_adapter = new Course_Adapter(clist);

        recyclerView.setAdapter(course_adapter);


        outer = findViewById(R.id.outerlayout);

       //received value starts
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);   //toolbar 1

        Intent intent=getIntent();
        String employeeid= intent.getStringExtra("cname");

         TextView textView=findViewById(R.id.title);   //toolbar 1


         if(employeeid.equalsIgnoreCase("sec"))

        {

            coursesName="Secondary (10th)";
            cname="Secondary";
            outer.setBackgroundColor(Color.parseColor("#32beff"));
            textView.setText("Secondary");  //toolbar 1

        }

        else if(employeeid.equalsIgnoreCase("s_sec"))
        {
            coursesName="Senior Secondary (12th)";
            cname="Senior Secondary";
            outer.setBackgroundColor(Color.parseColor("#4dc957"));
            textView.setText("Senior Secondary");
        }

        else if(employeeid.equalsIgnoreCase("dip"))
        {
            coursesName="Diploma";
            cname="Diploma";
            outer.setBackgroundColor(Color.parseColor("#ff6b4e"));
            textView.setText("Diploma");
        }

        //received values ends


        setSupportActionBar(toolbar);    //toolbar 1

        // add back arrow to toolbar   //toolbar 1
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        //toolbar 1 ends

        fetchStores();
        //ode for endliess scrolling ends
    }


    private void fetchStores() {

        System.err.println("inside fetchStores ");
        final String sub = coursesName;

       final StringBuilder query = new StringBuilder();
        query.append("?sub=");

        try {
            query.append(URLEncoder.encode(sub, "UTF-8"));
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        JsonObjectRequest fetchAllStores = new JsonObjectRequest(Request.Method.POST, "http://tuespotsolutions.com/nbse/app/dataform_secondary.php"+query, null, new Response.Listener<JSONObject>() {





            @Override
            public void onResponse(JSONObject response) {

                System.err.println("inside onResponse ");

                System.err.println("http://tuespotsolutions.com/nbse/app/dataform_secondary.php"+query);
                //Log.d("", "Data query chat : " + "http://tuespotsolutions.com/casa/fetchpagegroupchat.php?mobno="+mobileno+"&dataoffset="+dataoffset);

                //Log.d("", "Fetch Stores chat: " + response);
                showStores(response);
                recyclerView.setAdapter(course_adapter);
                System.err.println("adpter attached");
                course_adapter.notifyDataSetChanged();
                System.err.println("data set changed attached");
            }
        }, new Response.ErrorListener() {



            @Override
            public void onErrorResponse(VolleyError error) {


                System.err.println("inside onErrorResponse "+error.getMessage());
                Log.d("", "Fetch Stores Error: " + error.getMessage());
            }
        });
        ApplicationController.getInstance().addToRequestQueue(fetchAllStores);
    }


    private void showStores(JSONObject response) {
        try {
            JSONArray contacts = response.getJSONArray("tasks");

            // looping through All Contacts
            JSONArray c = response.getJSONArray("tasks");
            for (int i = 0; i < c.length(); i++) {
                JSONObject obj = c.getJSONObject(i);

                String code = obj.getString("sub_code");
                String subject=obj.getString("sub_eng");
                String stream=obj.getString("stream");


                Course_Model cc = new Course_Model();

                cc.setSub_code(code);
                cc.setSub(subject);
                cc.setStream(stream);
                clist.add(cc);



            }
        } catch (JSONException e) {


            System.err.println("inside showStores "+e.getMessage());
            Log.d("", "Show Stores: " + e.getMessage());
        }
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
