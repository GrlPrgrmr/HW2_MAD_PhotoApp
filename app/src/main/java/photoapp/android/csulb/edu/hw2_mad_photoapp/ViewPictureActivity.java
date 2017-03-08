package photoapp.android.csulb.edu.hw2_mad_photoapp;

import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

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
                photoView.setImageBitmap(bmap);
            } catch (Exception ex)

            {

                String s = ex.toString();
            }

        }

    }
}
