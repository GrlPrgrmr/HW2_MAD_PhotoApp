package photoapp.android.csulb.edu.hw2_mad_photoapp.model;

import android.graphics.Bitmap;

import java.util.ArrayList;

/**
 * Created by Neha on 3/7/2017.
 */

public class Picture {


    private String mCaption;
    private String mImagePath;
    private Bitmap mImageBitmap;

    public Picture(String path,String caption)
    {
        mCaption = caption;
        mImagePath=path;

    }

    public Picture()
    {

    }

    public void setCaption(String caption)
    {
        this.mCaption = caption;
    }
    public String getCaption()
    {
        return mCaption;
    }

    public String getImagepath()
    {
        return mImagePath;
    }

    public void setImagePath(String pathString)
    {
        this.mImagePath = pathString;
    }



    public Bitmap getImage()
    {
        return this.mImageBitmap;
    }

    public void setImage(Bitmap bm)
    {
        this.mImageBitmap = bm;
    }
    public static ArrayList<Picture> createImageList()
    {
        ArrayList<Picture> data = new ArrayList<Picture>();

        try
        {

        }
        catch (Exception ex)
        {
            String s = ex.getMessage();

        }

        return data;
    }
}
