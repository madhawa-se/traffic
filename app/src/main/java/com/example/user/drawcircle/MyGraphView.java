package com.example.user.drawcircle;

import java.util.ArrayList;
import java.util.List;

import android.app.Dialog;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class MyGraphView extends View {

    Paint paintBackground;
    Paint paintGraph;
    Context myContext;
    int rate=6;
    private Rect r = new Rect();


    public MyGraphView(Context context) {
        super(context);
        init(context);
    }

    public MyGraphView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }

    public MyGraphView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        init(context);
    }

    private void init(Context c){
        myContext = c;

        paintBackground = new Paint();
        paintBackground.setStyle(Paint.Style.FILL);
        paintBackground.setColor(Color.BLACK);

        paintGraph = new Paint();
        paintGraph.setStyle(Paint.Style.STROKE);
        paintGraph.setColor(Color.WHITE);
        paintGraph.setStrokeWidth(3);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float eventX = event.getX();
        float eventY = event.getY();

        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:

                rate++;
                invalidate();
                final Dialog dialog = new Dialog(myContext);
                dialog.setContentView(R.layout.activity_ustom);
                dialog.setTitle("Title...");
                dialog.show();
                return true;
            case MotionEvent.ACTION_MOVE:
                break;
            case MotionEvent.ACTION_UP:
                // nothing to do
                break;
            default:
                return false;
        }
        return true;
    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        int x = getWidth();
        int y = getHeight();
        int radius=(Math.min(x,y)-100)/2;
        Log.w("width"," radius "+radius+" - x "+x+" y"+y);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.parseColor("#000000"));
        canvas.drawPaint(paint);
        // Use Color.parseColor to define HTML colors

       // canvas.drawCircle(x / 2, y / 2, radius, paint);
        RectF rectF = new RectF(x / 2-radius, y / 2-radius, x / 2+radius, y / 2+radius);
        //canvas.drawOval(rectF, paint);

        paint.setColor(Color.parseColor("#64B5F6"));
        canvas.drawArc (rectF, 0, 60, true, paint);

        paint.setColor(Color.parseColor("#42A5F5"));
        canvas.drawArc (rectF, 60, 60, true, paint);

        paint.setColor(Color.parseColor("#2196F3"));
        canvas.drawArc (rectF, 120, 60, true, paint);

        paint.setColor(Color.parseColor("#1E88E5"));
        canvas.drawArc (rectF, 180, 60, true, paint);

        paint.setColor(Color.parseColor("#1976D2"));
        canvas.drawArc (rectF, 240, 60, true, paint);

        paint.setColor(Color.parseColor("#1565C0"));
        canvas.drawArc (rectF, 300, 60, true, paint);



        paint.setColor(Color.parseColor("#000000"));
        canvas.drawCircle(x / 2, y / 2, radius/3, paint);

        paint.setColor(Color.parseColor("#4DD0E1"));
        canvas.drawCircle(x / 2, y / 2, radius/3-5, paint);

        paint.setColor(Color.parseColor("#000000"));
        canvas.drawCircle(x / 2, y / 2, radius/3-8, paint);

        paint.setColor(Color.parseColor("#00E676"));
        paint.setTextSize(30);
        drawCenter(canvas,paint,rate+"");
       // canvas.drawText("6", x / 2, y / 2, paint);
    }

    private void drawCenter(Canvas canvas, Paint paint, String text) {
        canvas.getClipBounds(r);
        int cHeight = r.height();
        int cWidth = r.width();
        paint.setTextAlign(Paint.Align.LEFT);
        paint.getTextBounds(text, 0, text.length(), r);
        float x = cWidth / 2f - r.width() / 2f - r.left;
        float y = cHeight / 2f + r.height() / 2f - r.bottom;
        canvas.drawText(text, x, y, paint);
    }



}
