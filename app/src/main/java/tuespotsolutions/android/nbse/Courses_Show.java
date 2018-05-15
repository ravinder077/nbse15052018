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

        recyclerView = findViewById(R.id.recycler_course);
        clist = new ArrayList<>();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);
        recyclerView.setLayoutManager(layoutManager);
        course_adapter = new Course_Adapter(clist);

        outer = findViewById(R.id.outerlayout);

       //received value starts

        Intent intent=getIntent();
        String employeeid= intent.getStringExtra("cname");


        if(employeeid.equalsIgnoreCase("sec"))

        {

            coursesName="Secondary (10th)";
            cname="Secondary";
            outer.setBackgroundColor(Color.parseColor("#32beff"));


        }
        else if(employeeid.equalsIgnoreCase("s_sec"))
        {
            coursesName="Senior Secondary (12th)";
            cname="Senior Secondary";
            outer.setBackgroundColor(Color.parseColor("#4dc957"));

        }

        else if(employeeid.equalsIgnoreCase("dip"))
        {
            coursesName="Diploma";
            cname="Diploma";
            outer.setBackgroundColor(Color.parseColor("#ff6b4e"));

        }

        //received values ends

        textView = findViewById(R.id.course_title);
        textView.setText(cname.toString().toUpperCase());

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // add back arrow to toolbar
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        fetchStores();
        //ode for endliess scrolling ends
    }


    private void fetchStores() {

        System.err.println("inside fetchStores ");
        final String sub = coursesName;
        JsonObjectRequest fetchAllStores = new JsonObjectRequest(Request.Method.POST, "http://tuespotsolutions.com/nbse/datafrom_secondry.php?sub="+sub, null, new Response.Listener<JSONObject>() {





            @Override
            public void onResponse(JSONObject response) {

                System.err.println("inside onResponse ");

                System.err.println("http://tuespotsolutions.com/nbse/datafrom_secondary.php?sub="+sub);
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
                String code = obj.getString("code");
                String subject=obj.getString("subject");


                Course_Model cc = new Course_Model();

                cc.setSub_code(code);
                cc.setSub(subject);
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
