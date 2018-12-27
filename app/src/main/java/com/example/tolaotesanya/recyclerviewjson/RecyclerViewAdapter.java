package com.example.tolaotesanya.recyclerviewjson;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;

/**
 * Created by tolaotesanya on 27/12/2018.
 */

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {


    //for Debugging
    private static final String TAG = "RecyclerViewAdapter";

    private Context mContext;
    private ArrayList<ListItem> mListItems;
    private OnItemClicklistener mOnItemClicklistener;

    public interface OnItemClicklistener {
        void onItemClick(int position);

    }

    public void setmOnItemClicklistener(OnItemClicklistener listener){
        mOnItemClicklistener = listener;
    }

    public RecyclerViewAdapter(Context context, ArrayList<ListItem> listItems) {
        this.mContext = context;
        this.mListItems = listItems;
    }

    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //responsible for inflating the view
        View view = LayoutInflater.from(mContext).inflate(R.layout.listitemlayout, parent, false);
        return new ViewHolder(view);
    }

    //important and will change depending on how you want your layout to look like.
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        // great for debugging - will print every time an item gets added.
        Log.d(TAG, "OnBindViewHolder: called. ");

        ListItem currentItem = mListItems.get(position);

        String imageUrl = currentItem.getImageURL();
        String name = currentItem.getName();
        String desc = currentItem.getDesc();

        holder.mimageName.setText("Names: " + name);
        holder.mimageDesc.setText("Description: " + desc);
        Picasso.get().load(imageUrl).fit().centerInside().into(holder.mImageView);

    }

    @Override
    public int getItemCount() {
        //important to display items
        return mListItems.size();
    }

    //Holding view ready for it to be declared.
    public class ViewHolder extends RecyclerView.ViewHolder {

        ImageView mImageView;
        TextView mimageName;
        TextView mimageDesc;


        public ViewHolder(View itemView) {
            super(itemView);
            mImageView = itemView.findViewById(R.id.imageView);
            mimageName = itemView.findViewById(R.id.image_name);
            mimageDesc = itemView.findViewById(R.id.image_desc);

            //will be used for the onclick listener
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    if(mOnItemClicklistener != null)
                    {
                        int position = getAdapterPosition();
                        if(position != RecyclerView.NO_POSITION){
                            mOnItemClicklistener .onItemClick(position);
                        }
                    }
                }
            });





        }
    }


}
