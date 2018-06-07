package tuespotsolutions.android.nbse;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.android.volley.Request;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;


import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.w3c.dom.Text;

import java.util.ArrayList;



public class Result_show extends AppCompatActivity {

    private String rollno,dob;
    private ArrayList<ResultModal> resultList;
    private RecyclerView recyclerView;
    private ResultAdapter resultAdapter;

    TextView totalmarks ;
    TextView position;

    @Override
    protected void onCreate(Bundle b) {

        super.onCreate(b);
        setContentView(R.layout.result_show);


        totalmarks=findViewById(R.id.t_marks);
        position=findViewById(R.id.result_pos);


        LinearLayout myfirst=findViewById(R.id.resultfirst);

        myfirst.setBackgroundColor(Color.GREEN);


        Intent i = getIntent();

        recyclerView = findViewById(R.id.recycler_result);
        resultList = new ArrayList<>();

        LinearLayoutManager layoutManager = new LinearLayoutManager(this,LinearLayoutManager.VERTICAL,false);

        recyclerView.setLayoutManager(layoutManager);
        resultAdapter = new ResultAdapter(resultList);

        recyclerView.setAdapter(resultAdapter);


       rollno = i.getStringExtra("rollno");
       dob = i.getStringExtra("dob");


        System.err.println(rollno+"This is a rollno");
        System.err.println(dob+"This is a date of birth");

        fetchStores();

    }

    private void fetchStores() {


        System.err.println("inside fetchStores ");
        final String roll = rollno;

        final String dofb = dob;


        JsonObjectRequest fetchAllStores = new JsonObjectRequest(Request.Method.POST, "http://tuespotsolutions.com/nbse/app/result_main?roll="+roll+"&dob="+dofb, null, new Response.Listener<JSONObject>() {

            @Override
            public void onResponse(JSONObject response) {

                System.err.println("inside onResponse ");

                System.err.println("http://tuespotsolutions.com/nbse/app/result_main?roll="+roll+"&dob="+dofb);
               
                //Log.d("", "Data query chat : " + "http://tuespotsolutions.com/casa/fetchpagegroupchat.php?mobno="+mobileno+"&dataoffset="+dataoffset);

                //Log.d("", "Fetch Stores chat: " + response);

                showStores(response);
                recyclerView.setAdapter(resultAdapter);
                System.err.println("adpter attached");
                resultAdapter.notifyDataSetChanged();
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
            JSONArray contacts = response.getJSONArray("finalresult");

            JSONObject tmarks = contacts.getJSONObject(0);

            String totalmark=tmarks.getString("totalmarks");
            String pos=tmarks.getString("stu_result");

            totalmarks.setText(totalmark);
            position.setText(pos);




            // looping through All Contacts
            JSONArray c = response.getJSONArray("tasks");
            for (int i = 0; i < c.length(); i++) {
                JSONObject obj = c.getJSONObject(i);

                String code = obj.getString("sub_code");
                String subject=obj.getString("sub_eng");
                String practical=obj.getString("practical");
                String theory=obj.getString("theory");



                ResultModal cc = new ResultModal();

                cc.setCode(code);
                cc.setSubject(subject);
                cc.setPractical(practical);
                cc.setTheory(theory);


                resultList.add(cc);



            }


        } catch (JSONException e) {


            System.err.println("inside showStores "+e.getMessage());
            Log.d("", "Show Stores: " + e.getMessage());
        }
    }

}
