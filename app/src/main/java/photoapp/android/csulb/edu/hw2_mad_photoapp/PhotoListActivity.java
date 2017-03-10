package photoapp.android.csulb.edu.hw2_mad_photoapp;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.Toolbar;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;

import java.util.ArrayList;
import java.util.List;

import photoapp.android.csulb.edu.hw2_mad_photoapp.Helper.PhotoAppDBHelper;
import photoapp.android.csulb.edu.hw2_mad_photoapp.model.Picture;

public class PhotoListActivity extends AppCompatActivity {

    private PhotoAppDBHelper db;
    private OnItemSelectedListViewListener mListener;

    private RecyclerView recyclerView;
    private List<Picture> imageList= new ArrayList<>();
    private ImageDataAdapter adapter;
    public static final String POSITIONVALUE ="position";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_photo_list);
        recyclerView = (RecyclerView)findViewById(R.id.rvlistItem);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Intent intent = new Intent(PhotoListActivity.this,AddPhotoActivity.class);

                startActivity(intent);

            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_photo_list, menu);
        return true;
    }

    private List<Picture> getListFromDataBase()
    {
        db = new PhotoAppDBHelper(this);

        try
        {

            //db.deleteAll();

            if(db.getRowCountTable()>0)
            {
                imageList = db.readDBFull();


            }
            /*else
            {
                Intent intent = new Intent(this,AddPhotoActivity.class);
                startActivity(intent);
            }*/



        }
        catch (Exception ex)
        {
            String error = ex.getMessage();
        }

        return imageList;
    }

    @Override
    protected void onResume()
    {
        super.onResume();

        adapter = new ImageDataAdapter(this,getListFromDataBase());
        adapter.setOnItemSelectedListener(new OnItemSelectedListViewListener() {
            @Override
            public void onImageItemSelected(int pos) {

                System.out.println(pos);

                Intent intent = new Intent(PhotoListActivity.this,ViewPictureActivity.class);
                intent.putExtra(POSITIONVALUE,pos);
                startActivity(intent);
            }
        });

        recyclerView.setAdapter(adapter);
    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            Uri packageURI = Uri.parse("package:photoapp.android.csulb.edu.hw2_mad_photoapp");
            Intent uninstallIntent = new Intent(Intent.ACTION_DELETE, packageURI);
            startActivity(uninstallIntent);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
