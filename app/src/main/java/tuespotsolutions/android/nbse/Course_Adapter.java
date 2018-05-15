package tuespotsolutions.android.nbse;

/**
 * Created by ravinder077 on 23-03-2018.
 */

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;



import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class Course_Adapter extends RecyclerView.Adapter<Course_Adapter.MyViewHolder> {


    private List<Course_Model> list;
    private Context mcontext;

    public Course_Adapter(List<Course_Model> list) {
        this.list = list;
    }


    public Course_Adapter(List<Course_Model> list, Context context) {

        this.mcontext=context;
        this.list = list;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.course_recycler_item, parent, false);

        return new Course_Adapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final Course_Model data = list.get(position);

        if (position % 2 == 0) {

            holder.row.setBackgroundColor(Color.parseColor("#FFCECECE"));

        } else {

            holder.row.setBackgroundColor(Color.parseColor("#FFFFFFFF"));

        }

        holder.s_code.setText(data.getSub_code());
        holder.sub.setText(data.getSub());


    }



    @Override
    public int getItemCount() {
        return list.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView s_code, sub;
        public TableRow row;

        public MyViewHolder(View view) {
            super(view);
            s_code = view.findViewById(R.id.code);
            sub = view.findViewById(R.id.sub);
            row = view.findViewById(R.id.row);
        }
    }

}

