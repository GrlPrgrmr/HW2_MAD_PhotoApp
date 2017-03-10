package photoapp.android.csulb.edu.hw2_mad_photoapp;

import android.content.Intent;
import android.content.res.Configuration;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Matrix;
import android.media.ExifInterface;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.io.IOException;

import photoapp.android.csulb.edu.hw2_mad_photoapp.Helper.PhotoAppDBHelper;
import photoapp.android.csulb.edu.hw2_mad_photoapp.model.Picture;

public class ViewPictureActivity extends AppCompatActivity {

    public static final String POSITIONVALUE ="position";
    private TextView captionTextView;
    private ImageView photoView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_picture);

        captionTextView =(TextView)findViewById(R.id.captionItem);
        photoView = (ImageView)findViewById(R.id.imageViewItem);

        Intent intent = getIntent();
        int posVal = intent.getIntExtra(POSITIONVALUE,-1);

        System.out.println("Photo position :"+posVal);

        if(posVal==-1)
        {
            Toast.makeText(this,"Error inside PhotoViewActivity",Toast.LENGTH_SHORT).show();
            finish();

        }
        else {
            try {
                PhotoAppDBHelper db = new PhotoAppDBHelper(this);
                Picture imgData = db.getRow(posVal);

                captionTextView.setText(imgData.getCaption());

                String path = imgData.getImagepath();
                System.out.println("Path : " + path);

                Bitmap bmap = BitmapFactory.decodeFile(path);

                Bitmap bmapRotated = handleCameraRotationImage(path);
                photoView.setImageBitmap(bmapRotated);
            } catch (Exception ex)

            {

                String s = ex.toString();
            }

        }

    }

    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
    }

    private Bitmap handleCameraRotationImage(String file)
    {
        BitmapFactory.Options bounds = new BitmapFactory.Options();
        bounds.inJustDecodeBounds = true;

            BitmapFactory.decodeFile(file, bounds);

            BitmapFactory.Options opts = new BitmapFactory.Options();
            Bitmap bm = BitmapFactory.decodeFile(file, opts);
            ExifInterface exif = null;
            try {
                exif = new ExifInterface(file);
            } catch (IOException e) {
                e.printStackTrace();
            }
            String orientString = exif.getAttribute(ExifInterface.TAG_ORIENTATION);
            int orientation = orientString != null ? Integer.parseInt(orientString) : ExifInterface.ORIENTATION_NORMAL;

            int rotationAngle = 0;
            if (orientation == ExifInterface.ORIENTATION_ROTATE_90) rotationAngle = 90;
            if (orientation == ExifInterface.ORIENTATION_ROTATE_180) rotationAngle = 180;
            if (orientation == ExifInterface.ORIENTATION_ROTATE_270) rotationAngle = 270;

            Matrix matrix = new Matrix();
            matrix.setRotate(rotationAngle, (float) bm.getWidth() / 2, (float) bm.getHeight() / 2);
            Bitmap rotatedBitmap = Bitmap.createBitmap(bm, 0, 0, bounds.outWidth, bounds.outHeight, matrix, true);

            return rotatedBitmap;


    }
}
