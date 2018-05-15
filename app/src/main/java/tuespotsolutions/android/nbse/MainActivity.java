package tuespotsolutions.android.nbse;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements RecyclerViewAdapter.ItemListener {
    RecyclerView recyclerView;
    ArrayList<DataModel> arrayList;
    ArrayList<Image_Model> imagelist;
    Image_RecyclerViewAdapter adapter2;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);
        arrayList = new ArrayList<>();
        arrayList.add(new DataModel((String) getText(R.string.courses), R.drawable.course, "#86754d", R.drawable.roundcorners));
        arrayList.add(new DataModel((String) getText(R.string.result), R.drawable.result, "#86754d", R.drawable.roundcorners));
        arrayList.add(new DataModel((String) getText(R.string.datesheet), R.drawable.date_sheet, "#86754d", R.drawable.roundcorners));
        arrayList.add(new DataModel((String) getText(R.string.textbook), R.drawable.repository, "#86754d", R.drawable.roundcorners));
        arrayList.add(new DataModel((String) getText(R.string.prospectus), R.drawable.open_book, "#86754d", R.drawable.roundcorners));
        arrayList.add(new DataModel((String) getText(R.string.form), R.drawable.form, "#86754d", R.drawable.roundcorners));
        arrayList.add(new DataModel((String) getText(R.string.enquiry), R.drawable.enquiry, "#86754d", R.drawable.roundcorners));
        arrayList.add(new DataModel((String) getText(R.string.about_us), R.drawable.about, "#86754d", R.drawable.roundcorners));
        arrayList.add(new DataModel((String) getText(R.string.contact_us), R.drawable.contact_us, "#86754d", R.drawable.roundcorners));

        RecyclerViewAdapter adapter = new RecyclerViewAdapter(this, arrayList, this);
        recyclerView.setAdapter(adapter);





        LinearLayoutManager layoutManager1
                = new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false);
        imagelist = new ArrayList<>();
        imagelist.add(new Image_Model(1,R.drawable.a));
        imagelist.add(new Image_Model(2,R.drawable.b));
        imagelist.add(new Image_Model(3,R.drawable.c));

        RecyclerView myList =  findViewById(R.id.recyclerView1);
        myList.setLayoutManager(layoutManager1);
        adapter2 = new Image_RecyclerViewAdapter(imagelist);
        myList.setAdapter(adapter2);

        /**
         AutoFitGridLayoutManager that auto fits the cells by the column width defined.
         **/

        AutoFitGridLayoutManager layoutManager = new AutoFitGridLayoutManager(this, 333);
        recyclerView.setLayoutManager(layoutManager);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

    }

    @Override
    public void onItemClick(DataModel item) {
        if(item.text.equalsIgnoreCase((String) getText(R.string.courses)))
        {
            Intent intent = new Intent(getApplicationContext(),Courses.class);
            Toast.makeText(this, (String) getText(R.string.courses), Toast.LENGTH_SHORT).show();
            startActivity(intent);
        }
        else if(item.text.equalsIgnoreCase((String) getText(R.string.result)))
        {
            Intent intent = new Intent(getApplicationContext(),ResultMain.class);
            startActivity(intent);
            Toast.makeText(this, (String) getText(R.string.result), Toast.LENGTH_SHORT).show();
        }
        else if(item.text.equalsIgnoreCase((String) getText(R.string.datesheet)))
        {
            // Intent intent = new Intent(getApplicationContext(),HorariosMain.class);
            //startActivity(intent);
            Toast.makeText(this, (String) getText(R.string.datesheet), Toast.LENGTH_SHORT).show();
        }
        else if(item.text.equalsIgnoreCase((String) getText(R.string.textbook)))
        {
            // Intent intent = new Intent(getApplicationContext(),HorariosMain.class);
            //startActivity(intent);
            Toast.makeText(this, (String) getText(R.string.textbook), Toast.LENGTH_SHORT).show();
        }
        else if(item.text.equalsIgnoreCase((String) getText(R.string.prospectus)))
        {
            // Intent intent = new Intent(getApplicationContext(),HorariosMain.class);
            //startActivity(intent);
            Toast.makeText(this, (String) getText(R.string.prospectus), Toast.LENGTH_SHORT).show();
        }
        else if(item.text.equalsIgnoreCase((String) getText(R.string.form)))
        {
            // Intent intent = new Intent(getApplicationContext(),HorariosMain.class);
            //startActivity(intent);
            Toast.makeText(this, (String) getText(R.string.form), Toast.LENGTH_SHORT).show();
        }
        else if(item.text.equalsIgnoreCase((String) getText(R.string.enquiry)))
        {
            // Intent intent = new Intent(getApplicationContext(),HorariosMain.class);
            //startActivity(intent);
            Toast.makeText(this, (String) getText(R.string.enquiry), Toast.LENGTH_SHORT).show();
        }
        else if(item.text.equalsIgnoreCase((String) getText(R.string.about_us)))
        {
            // Intent intent = new Intent(getApplicationContext(),HorariosMain.class);
            //startActivity(intent);
            Toast.makeText(this, (String) getText(R.string.about_us), Toast.LENGTH_SHORT).show();
        }
        else if(item.text.equalsIgnoreCase((String) getText(R.string.contact_us)))
        {
            // Intent intent = new Intent(getApplicationContext(),HorariosMain.class);
            //startActivity(intent);
            Toast.makeText(this, (String) getText(R.string.contact_us), Toast.LENGTH_SHORT).show();
        }
    }
}
