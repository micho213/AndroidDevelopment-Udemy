package com.example.pikasso.view;

import android.annotation.SuppressLint;
import android.content.ContentValues;
import android.content.Context;
import android.content.ContextWrapper;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Point;
import android.media.Image;
import android.net.Uri;
import android.os.Environment;
import android.provider.MediaStore;
import android.util.AttributeSet;
import android.util.Log;
import android.view.Gravity;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.util.HashMap;

public class PikassoView extends View {

    public static final float TOUCH_TOLERANCE = 10;
    private Bitmap bitmap;
    private Canvas bitmapCanvas;
    private Paint paintScreen;
    private Paint paintLine;
    private HashMap<Integer, Path> pathmap;
    private HashMap<Integer, Point> previousPointMap;



    private float savedWidth;
    private int savedColor;

    private int backgroundColor = Color.WHITE;




    public PikassoView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();


    }


    private void init(){


        paintScreen = new Paint();

        paintLine = new Paint();
        paintLine.setAntiAlias(true);
        paintLine.setColor(Color.BLACK);
        paintLine.setStyle(Paint.Style.STROKE);
        paintLine.setStrokeWidth(8);
        paintLine.setStrokeCap(Paint.Cap.ROUND);

        pathmap = new HashMap();
        previousPointMap = new HashMap<>();


    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        bitmap = Bitmap.createBitmap(getWidth(), getHeight() , Bitmap.Config.ARGB_8888);
        bitmapCanvas = new Canvas(bitmap);
        bitmap.eraseColor(Color.WHITE);

//        super.onSizeChanged(w, h, oldw, oldh);
    }


    public void changeBackgroundColor(int alpha, int red, int green, int blue){
        bitmapCanvas.drawColor(Color.argb(alpha,red,green,blue));
        backgroundColor = Color.argb(alpha,red,green,blue);

    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawBitmap(bitmap,0,0,paintScreen);

        for (Integer key: pathmap.keySet()) {
            canvas.drawPath(pathmap.get(key), paintLine);
        }
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        // variables to hold values associated with the event

        int action = event.getActionMasked();
        int actionIndex = event.getActionIndex();   // pointer, finger or mouse

        if (action == MotionEvent.ACTION_DOWN || action == MotionEvent.ACTION_POINTER_UP) {

            touchStarted ( event.getX(actionIndex) ,
                    event.getY(actionIndex),
                    event.getPointerId(actionIndex));

        }else if (action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_POINTER_UP){

            touchEnded(event.getPointerId(actionIndex));

        }else {

            touchMoved(event);
        }

        invalidate(); // this redraws the screen

        return true;
    }
    private void touchMoved (MotionEvent event){

        for (int i = 0; i < event.getPointerCount(); i ++){
            int id = event.getPointerId(i);
            int pointerIndex = event.findPointerIndex(id);

            if (pathmap.containsKey(id)){
                float newX = event.getX(id);
                float newY = event.getY(id);


                Path path = pathmap.get(id);
                Point point = previousPointMap.get(id);

                // calculate how far the user moved from the last update

                float deltaX = Math.abs(newX - point.x);
                float deltaY = Math.abs(newY - point.y);


                // if the distance is significant then its a movement
                if (deltaX >= TOUCH_TOLERANCE || deltaY >= TOUCH_TOLERANCE){

                    // move the path to new location
                    path.quadTo(point.x, point.y, (newX + point.x)/2 , (newY + point.y)/2);


                    // store the new coords

                    point.x = (int) newX;
                    point.y = (int) newY;
                }
            }
        }



    }

    public void setDrawingColor (int color){
        paintLine.setColor(color);
    }

    public int getDrawingColor(){
        return paintLine.getColor();
    }

    public void setLineWidth(int width){
        paintLine.setStrokeWidth(width);
    }

    public int getLineWidth () {
        return (int)paintLine.getStrokeWidth();
    }

    public void clear(){
        pathmap.clear();
        previousPointMap.clear();
        bitmap.eraseColor(Color.WHITE);
        invalidate();


    }

    private void touchEnded (int id){

        Path path = pathmap.get(id);
        bitmapCanvas.drawPath(path,paintLine);
        path.reset();

    }

    private void touchStarted ( float x, float y, int id){
        Path path;  // store the path
        Point point;  // store the last point in the path


        if (pathmap.containsKey(id)){
            path = pathmap.get(id);
            point = previousPointMap.get(id);
        }else{
            // this is a new touch if empty
            path = new Path();
            pathmap.put(id, path);
            point = new Point();
            previousPointMap.put(id, point);
        }

        // move to the coords of the touch

        path.moveTo(x , y );
        point.x = (int) x;
        point.y = (int) y;

    }


    public void erase(){

        if (paintLine.getColor() == Color.WHITE){
            Toast.makeText(getContext(), "Pen" , Toast.LENGTH_LONG).show();
            paintLine.setColor(savedColor);
            paintLine.setStrokeWidth(savedWidth);
        }else{
            Toast.makeText(getContext(), "Rubber" , Toast.LENGTH_LONG).show();
            savedColor = paintLine.getColor();

            paintLine.setColor(backgroundColor);

            savedWidth = paintLine.getStrokeWidth();
            paintLine.setStrokeWidth(85);
        }

    }

}
