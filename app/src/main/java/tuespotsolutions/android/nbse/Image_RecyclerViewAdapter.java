package tuespotsolutions.android.nbse;

/**
 * Created by ravinder077 on 23-03-2018.
 */

import android.content.Context;
import android.content.Intent;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;



import java.util.List;

import de.hdodenhof.circleimageview.CircleImageView;


public class Image_RecyclerViewAdapter extends RecyclerView.Adapter<Image_RecyclerViewAdapter.MyViewHolder> {


    private List<Image_Model> postlist;
    private Context mcontext;

    public Image_RecyclerViewAdapter(List<Image_Model> postlist) {
        this.postlist = postlist;
    }


    public Image_RecyclerViewAdapter(List<Image_Model> postlist, Context context) {

        this.mcontext=context;
        this.postlist = postlist;
    }


    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.image_card, parent, false);

        return new Image_RecyclerViewAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(final MyViewHolder holder, int position) {
        final Image_Model wallData = postlist.get(position);


        holder.profilepicture.setImageResource(wallData.getImage());




    }



    @Override
    public int getItemCount() {
        return postlist.size();
    }



    public class MyViewHolder extends RecyclerView.ViewHolder {



        public ImageView profilepicture;




        public MyViewHolder(View view) {
            super(view);




            profilepicture = view.findViewById(R.id.image);



        }
    }

}

