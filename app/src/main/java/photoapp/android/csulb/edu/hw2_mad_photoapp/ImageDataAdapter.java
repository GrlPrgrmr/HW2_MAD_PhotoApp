package photoapp.android.csulb.edu.hw2_mad_photoapp;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import photoapp.android.csulb.edu.hw2_mad_photoapp.model.Picture;

/**
 * Created by Neha on 3/7/2017.
 */

public class ImageDataAdapter extends RecyclerView.Adapter<ImageDataAdapter.ImageViewWithCaptionHolder> {

    public  class ImageViewWithCaptionHolder extends RecyclerView.ViewHolder
    {
        public TextView captionTextView;
        public ImageView currentImageView;

        public ImageViewWithCaptionHolder(View itemView) {
            super(itemView);

            captionTextView = (TextView)itemView.findViewById(R.id.caption);
            //currentImageView =(ImageView)itemView.findViewById(R.id.imageViewItem);
        }

    }

    private List<Picture> mImageData;
    private Context mContext;
    private OnItemSelectedListViewListener onItemSelectedListViewListener;

    public ImageDataAdapter(Context ctxt,List<Picture> imageList)
    {
        this.mImageData = imageList;
        mContext = ctxt;
    }

    private Context getContext()
    {
        return mContext;
    }

    //inflating a Layout from xml and returning the holder
    @Override
    public ImageViewWithCaptionHolder onCreateViewHolder(ViewGroup parent, int viewType) {

        Context ct = parent.getContext();
        LayoutInflater inflator =LayoutInflater.from(ct);

        View itemView = inflator.inflate(R.layout.list_item_recyclerview,parent,false);

        ImageViewWithCaptionHolder viewHolder = new ImageViewWithCaptionHolder(itemView);
        return viewHolder;

    }

    //involves populating data into the item through holder

    @Override
    public void onBindViewHolder(ImageViewWithCaptionHolder holder, final int position) {

        //get the model data based on position
        holder.captionTextView.setText(mImageData.get(position).getCaption());
        //holder.currentImageView.setImageBitmap(mImageData.get(position).getImage());


        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(onItemSelectedListViewListener!=null)
                {
                    onItemSelectedListViewListener.onImageItemSelected(position);
                }
            }
        });
        //set item views based on views and data model

    }

    @Override
    public int getItemCount() {
        return mImageData.size();
    }


    public void setOnItemSelectedListener(OnItemSelectedListViewListener onItemSelectedListener)
    {
        this.onItemSelectedListViewListener = onItemSelectedListener;

    }

}

