package tuespotsolutions.android.nbse;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.widget.ProgressBar;
import android.widget.TextView;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

public class ContactUs extends AppCompatActivity {

    private TextView mob,mail,location;
    private ProgressDialog progressDialog;
    private String mobile,emailid,address;

    @Override
    protected void onCreate(Bundle b){
        super.onCreate(b);
        setContentView(R.layout.contactus);

        progressDialog = new ProgressDialog(this);

        /***************************Ids assign*********************************/

        mob = findViewById(R.id.mob);
        mail = findViewById(R.id.email);
        location = findViewById(R.id.address);
        Toolbar toolbar = findViewById(R.id.toolbar);   //toolbar
        TextView textView=findViewById(R.id.title);   //toolbar

        /***************************Ids assign*********************************/
        textView.setText(getString(R.string.contact_us));
        setSupportActionBar(toolbar);    //toolbar 1

        // add back arrow to toolbar   //toolbar 1
        if (getSupportActionBar() != null) {
            getSupportActionBar().setDisplayHomeAsUpEnabled(true);
            getSupportActionBar().setDisplayShowHomeEnabled(true);
        }

        //toolbar 1 ends


        selectdata();


    }

    public void selectdata()
    {


/*********************************get ids from database**********************/

        JSONTaskIds jsonData =  new JSONTaskIds();
        jsonData.execute(getString(R.string.hostname)+"contact.php");
        System.err.println("Hit json "+getString(R.string.hostname)+"contact.php");


        ArrayList<String> data=null;
        try {
            System.err.println("try data get selectdata");
            data = jsonData.get();
            //System.err.println("msg try block after get data and name is : "+data.get(0));
            // System.err.println("hii" + data.get(0) + " " + data.get(1));
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }

        if(data!=null) {
            System.err.println("data found" + data.toArray());
            mobile = data.get(0);
            emailid = data.get(1);
            address = data.get(2);
        }
        else
        {
            System.err.println("data not found ");
            mobile = "Phone Number not Found";
            emailid = "Email not Found";
            address = "Address not Found";
        }

        mob.setText(mobile);
        mail.setText(emailid);
        location.setText(address);
/*********************************get ids from database ends**********************/
    }

    //JSON for select ids*************************************************************************

    class JSONTaskIds extends AsyncTask<String,String,ArrayList>
    {
        /** progress dialog to show user that the backup is processing. */
        /** application context. */
        @Override
        protected void onPreExecute() {
            System.err.println("onPreExecute in json");
            progressDialog.setMessage("Please wait");
            progressDialog.setCancelable(false);
            progressDialog.show();
        }



        @Override
        protected void onPostExecute(final ArrayList aa) {
            System.err.println("onPostExecute in json");
            if (progressDialog.isShowing()) {

                progressDialog.dismiss();
            }
        }

        @Override
        protected ArrayList<String> doInBackground(String... strings) {
            BufferedReader reader=null;
            HttpURLConnection connection=null;
            ArrayList<String> data=null;

            try {
                URL url = new URL(strings[0]);
                System.err.println("msg json get selectdata: " + url);
                connection = (HttpURLConnection) url.openConnection();
                connection.connect();
                InputStream stream = connection.getInputStream();
                reader = new BufferedReader(new InputStreamReader(stream));

                StringBuffer buffer = new StringBuffer();

                String line = "";
                while ((line = reader.readLine()) != null) {
                    buffer.append(line);
                }

                String finalJson = buffer.toString();
                System.err.println("msg after buffer : " + finalJson);
                JSONObject parentObject = new JSONObject(finalJson);

                if ("nothing".equalsIgnoreCase(finalJson)) {
                    return null;
                }
                else {

                    data = new ArrayList<String>();
                    data.removeAll(data);
                    System.out.println("msg in else " + finalJson);
                    JSONArray parentArray = parentObject.getJSONArray("tasks");
                    JSONObject finalObject = parentArray.getJSONObject(0);
                    String mobmsg = finalObject.getString("mobmsg");
                    String emailid = finalObject.getString("emailid");
                    String addeng = finalObject.getString("addeng");

                    data.add(mobmsg);
                    data.add(emailid);
                    data.add(addeng);

                    System.err.println("id " + mobmsg);
                    System.err.println("banner " + emailid);
                    System.err.println("interstitial " + addeng);

                    /******************/

                    System.err.println("mobmsg data " + data.get(0));

                    System.err.println("emailid data " + data.get(1));

                    System.err.println("addeng data " + data.get(2));

                }

                System.err.println("retruning data"+data.toArray().toString());
                return data;

            }
            catch(Exception e)
            {
                e.printStackTrace();
            }

            System.err.println("null retrun");
            return null;
        }

    }
    //JSON for select ids ends*******************************************************


}
