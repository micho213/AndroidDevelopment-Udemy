package com.example.pikasso;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.Toast;

import com.example.pikasso.view.PikassoView;

public class MainActivity extends AppCompatActivity {
    private PikassoView pikassoView;
    private AlertDialog.Builder currentAlertDialog;
    private AlertDialog dialog;
    private AlertDialog colorDialog;

    private ImageView widthImageView;

    private SeekBar alphaSeekBar ;
    private SeekBar redSeekBar ;
    private SeekBar greenSeekBar ;
    private SeekBar blueSeekBar ;

    private Button red;
    private Button blue;
    private Button green;
    private Button black;

    private int strokeWidth = 8;

    private Paint p = new Paint();
    private View coloView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        p.setStrokeWidth(strokeWidth);

        pikassoView = findViewById(R.id.view);


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        
        switch (item.getItemId()){
            case R.id.clearid: {
                pikassoView.clear();
                break;
            }
            case R.id.colorid: {
                showColorDialog();
                break;
            }
            case R.id.lineWidth: {
                showLineWidthDialog();

                break;
            }
            case R.id.eraseid: {
                pikassoView.erase();
                break;
            }
        }

        return super.onOptionsItemSelected(item);
    }


   private void showColorDialog(){

        currentAlertDialog = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.color_dialog, null);

        alphaSeekBar = view.findViewById(R.id.alphaSeekBar);
        redSeekBar = view.findViewById(R.id.redSeekBar);
        greenSeekBar = view.findViewById(R.id.greenSeekBar);
        blueSeekBar = view.findViewById(R.id.blueSeekBar);


        red = view.findViewById(R.id.redColor);
       blue = view.findViewById(R.id.blueColor);
       black = view.findViewById(R.id.blackColor);
       green = view.findViewById(R.id.greenColor);

       red.setBackgroundColor(Color.RED);
       blue.setBackgroundColor(Color.BLUE);
       black.setBackgroundColor(Color.BLACK);
       green.setBackgroundColor(Color.GREEN);



       coloView = view.findViewById(R.id.colorView);

        // register the listeners

       alphaSeekBar.setOnSeekBarChangeListener(colorSeekBarChanged);
       redSeekBar.setOnSeekBarChangeListener(colorSeekBarChanged);
       greenSeekBar.setOnSeekBarChangeListener(colorSeekBarChanged);
       blueSeekBar.setOnSeekBarChangeListener(colorSeekBarChanged);

       final int color = pikassoView.getDrawingColor();

       alphaSeekBar.setProgress(Color.alpha(color));
       redSeekBar.setProgress(Color.red(color));
       greenSeekBar.setProgress(Color.green(color));
       blueSeekBar.setProgress(Color.blue(color));


       Button setColorButton = view.findViewById(R.id.setColorButton);
       setColorButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               pikassoView.setDrawingColor(Color.argb(
                       alphaSeekBar.getProgress(), redSeekBar.getProgress(),
                       greenSeekBar.getProgress(), blueSeekBar.getProgress()
               ));

               colorDialog.dismiss();
           }
       });

       Button setBackgroundColorButton = view.findViewById(R.id.setBackgroundColorButton);

       setBackgroundColorButton.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               pikassoView.changeBackgroundColor(alphaSeekBar.getProgress(),redSeekBar.getProgress(),greenSeekBar.getProgress(),blueSeekBar.getProgress());
               colorDialog.dismiss();
           }

       });

       currentAlertDialog.setView(view);
       currentAlertDialog.setTitle("Choose a Color");
       colorDialog = currentAlertDialog.create();
       colorDialog.show();

       red.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               redSeekBar.setProgress(255);
               greenSeekBar.setProgress(0);
               blueSeekBar.setProgress(0);
           }
       });

       blue.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               redSeekBar.setProgress(0);
               greenSeekBar.setProgress(0);
               blueSeekBar.setProgress(255);
           }
       });

       black.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               redSeekBar.setProgress(0);
               greenSeekBar.setProgress(0);
               blueSeekBar.setProgress(0);
           }
       });

       green.setOnClickListener(new View.OnClickListener() {
           @Override
           public void onClick(View view) {
               redSeekBar.setProgress(0);
               greenSeekBar.setProgress(255);
               blueSeekBar.setProgress(0);
           }
       });
   }

   private SeekBar.OnSeekBarChangeListener colorSeekBarChanged = new SeekBar.OnSeekBarChangeListener() {
       @Override
       public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
           pikassoView.setBackgroundColor(Color.argb(
                alphaSeekBar.getProgress(), redSeekBar.getProgress(),
                   greenSeekBar.getProgress(), blueSeekBar.getProgress()
           ));

           // preview the color

           coloView.setBackgroundColor(Color.argb(
                   alphaSeekBar.getProgress(), redSeekBar.getProgress(),
                   greenSeekBar.getProgress(), blueSeekBar.getProgress()
           ));
       }

       @Override
       public void onStartTrackingTouch(SeekBar seekBar) {

       }

       @Override
       public void onStopTrackingTouch(SeekBar seekBar) {

       }
   };

    private void showLineWidthDialog(){
        currentAlertDialog = new AlertDialog.Builder(this);
        View view = getLayoutInflater().inflate(R.layout.width_dialog, null);
        final SeekBar widthSeekBar = view.findViewById(R.id.widthSeekBar);
        Button setLineWidthButton = view.findViewById(R.id.widthDialogButton);

        widthSeekBar.setProgress(strokeWidth);

        widthImageView = view.findViewById(R.id.imageView);


        setLineWidthButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(getApplicationContext(), "Changed" , Toast.LENGTH_LONG).show();

                pikassoView.setLineWidth(widthSeekBar.getProgress());
                dialog.dismiss();
                currentAlertDialog = null;
            }
        });

        widthSeekBar.setOnSeekBarChangeListener(widthSeekBarChange);
        updateWidthLine(p);

        currentAlertDialog.setView(view);

        dialog = currentAlertDialog.create();
        dialog.setTitle("Set Width");
        dialog.show();
    }

    private SeekBar.OnSeekBarChangeListener widthSeekBarChange = new SeekBar.OnSeekBarChangeListener() {

        @Override
        public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {

            strokeWidth = progress;
            p.setColor(pikassoView.getDrawingColor());
            p.setStrokeCap(Paint.Cap.ROUND);
            p.setStrokeWidth(progress);

            updateWidthLine(p);
        }

        @Override
        public void onStartTrackingTouch(SeekBar seekBar) {
        }

        @Override
        public void onStopTrackingTouch(SeekBar seekBar) {

        }
    };

    private void updateWidthLine(Paint p){
        Bitmap bitmap = Bitmap.createBitmap(400,100, Bitmap.Config.ARGB_8888);
        Canvas canvas = new Canvas(bitmap);

        bitmap.eraseColor(getResources().getColor(R.color.colorPrimary));

        canvas.drawLine(30,50,370,50, p);
        widthImageView.setImageBitmap(bitmap);
    }
}
