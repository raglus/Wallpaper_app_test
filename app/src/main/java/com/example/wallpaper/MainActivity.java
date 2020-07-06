package com.example.wallpaper;

import androidx.appcompat.app.AppCompatActivity;

import android.app.WallpaperManager;
import android.content.Context;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.GridView;
import android.widget.ImageView;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {

    Integer[]myImageArray = {
            R.drawable.back1, R.drawable.back2,
            R.drawable.back3, R.drawable.back4,
            R.drawable.back6, R.drawable.back10,
    };

    GridView myGridView;
    ImageView myCurrentWallpaper;
    WallpaperManager myWallpaperManager;
    Drawable myDrawable;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        myGridView = findViewById(R.id.myGridView);
        myCurrentWallpaper = findViewById(R.id.myImageView);

        myGridView.setAdapter(new ImageAdapter(getApplicationContext()));
    }
    private void updateMyWallpaper(){

        myWallpaperManager = WallpaperManager.getInstance(getApplicationContext());
        myDrawable = myWallpaperManager.getDrawable();
        myCurrentWallpaper = findViewById(R.id.myImageView);

    }
    public class ImageAdapter extends BaseAdapter{

        Context myContext;

        public ImageAdapter(Context applicationContext) {
            myContext = applicationContext;
        }

        @Override
        public int getCount() {
            return myImageArray.length;
        }

        @Override
        public Object getItem(int position) {
            return null;
        }

        @Override
        public long getItemId(int position) {
            return 0;
        }

        @Override
        public View getView(final int i, View view, ViewGroup viewGroup) {
            ImageView GridImageView;

            if(view == null){
                GridImageView = new ImageView(myContext);
                GridImageView.setLayoutParams(new GridView.LayoutParams(512, 512));
                GridImageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
                myWallpaperManager = WallpaperManager.getInstance(myContext);
            }else{
                GridImageView = (ImageView) view;
            }
            GridImageView.setImageResource(myImageArray[i]);


            GridImageView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    try {
                        myCurrentWallpaper.setImageResource(myImageArray [i]);
                        myWallpaperManager.setResource(myImageArray [i]);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    updateMyWallpaper();
                }
            });

            return GridImageView;
        }
    }
}