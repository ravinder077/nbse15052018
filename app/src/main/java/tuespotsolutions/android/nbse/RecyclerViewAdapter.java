package tuespotsolutions.android.nbse;

import android.content.Context;
import android.graphics.Color;
import android.media.Image;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import java.util.ArrayList;


public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {

    ArrayList<DataModel> mValues;
    ArrayList<Image_Model> imgValues;
    Context mContext,imgContext;
    protected ItemListener mListener,imgListener;

    public RecyclerViewAdapter(Context context, ArrayList<DataModel> values, ItemListener itemListener) {

        mValues = values;
        mContext = context;
        mListener=itemListener;
    }
    public RecyclerViewAdapter(ArrayList<Image_Model> values, Context context, ItemListener itemListener) {

        imgValues = values;
        imgContext = context;
        imgListener=itemListener;
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        public TextView textView;
        public ImageView imageView;
        public RelativeLayout relativeLayout;
        DataModel item;

        public ViewHolder(View v) {
            super(v);
            v.setOnClickListener(this);
            textView = (TextView) v.findViewById(R.id.textView);
            imageView = (ImageView) v.findViewById(R.id.imageView);
            relativeLayout = (RelativeLayout) v.findViewById(R.id.relativeLayout);

        }

        public void setData(DataModel item) {
            this.item = item;

            textView.setText(item.text);
            imageView.setImageResource(item.drawable);
            relativeLayout.setBackgroundColor(Color.parseColor(item.color));
            relativeLayout.setBackgroundResource(item.drawbaleback);

        }


        @Override
        public void onClick(View view) {
            if (mListener != null) {
                mListener.onItemClick(item);
            }
        }
    }

    @Override
    public RecyclerViewAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        View view = LayoutInflater.from(mContext).inflate(R.layout.recycler_view_item, parent, false);

        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ViewHolder Vholder, int position) {
        Vholder.setData(mValues.get(position));

    }

    @Override
    public int getItemCount() {

        return mValues.size();
    }

    public interface ItemListener {
        void onItemClick(DataModel item);
    }
}