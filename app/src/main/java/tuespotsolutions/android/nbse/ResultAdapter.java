package tuespotsolutions.android.nbse;

/**
 * Created by ravinder077 on 23-03-2018.
 */

import android.content.Context;
import android.graphics.Color;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TableRow;
import android.widget.TextView;

import java.util.List;


public class ResultAdapter extends RecyclerView.Adapter<ResultAdapter.MyViewHolder> {


    private List<ResultModal> list;
    private Context mcontext;

    public ResultAdapter(List<ResultModal> list) {
        this.list = list;
    }




    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.rowresultshow, parent, false);

        return new ResultAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final ResultModal data = list.get(position);

       if (position % 2 == 0) {

            holder.row.setBackgroundColor(Color.parseColor("#FFCECECE"));

        } else {

            holder.row.setBackgroundColor(Color.parseColor("#FFFFFFFF"));

        }

            holder.practical.setText(data.getPractical());
            holder.theory.setText(data.getTheory());
            holder.subjectName.setText(data.getSubject());
            holder.sCode.setText(data.getCode());


    }



    @Override
    public int getItemCount() {
        return list.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView sCode;
        public TextView subjectName;
        public TextView practical;
        public TextView theory;

        public TableRow row;

        public MyViewHolder(View view) {
            super(view);
           sCode = view.findViewById(R.id.result_code);
            subjectName = view.findViewById(R.id.result_subject);
            practical = view.findViewById(R.id.result_practical);
            theory = view.findViewById(R.id.result_theory);


            row = view.findViewById(R.id.row);
        }
    }

}

