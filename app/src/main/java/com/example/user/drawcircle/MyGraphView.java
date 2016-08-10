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
    int rate = 6;
    int sectors = 6;
    int sectorAngle;
    int x, y, radius, centerX, centerY = 0;
    String colors1[] = {"00B8D4", "0091EA", "304FFE", "6200EA", "C51162", "d50000"};
    String colors[] = {"F06292", "EC407A", "E91E63", "D81B60", "C2185B", "AD1457"};
    String colors2[]={"#4dd0e1","#26c6da","#00bcd4","#00acc1","#0097a7","#00838f"};
    String colors3[]={"#26a69a","#009688","#00897b","#00796b","#00695c","#004d40"};
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

    private void init(Context c) {

        sectorAngle = 360 / sectors;
        myContext = c;

        paintBackground = new Paint();
        paintBackground.setStyle(Paint.Style.FILL);
        paintBackground.setColor(Color.BLACK);

        paintGraph = new Paint();
        paintGraph.setStyle(Paint.Style.STROKE);
        //paintGraph.setColor(Color.WHITE);
        paintGraph.setStrokeWidth(3);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        float eventX = event.getX();
        float eventY = event.getY();

        x = getWidth();
        y = getHeight();
        int radius = (int) Math.sqrt((eventY - centerY) * (eventY - centerY) + (eventX - centerX) * (eventX - centerX));
        if (radius < this.radius) {
            double radian = Math.atan2((eventY - centerY), eventX - centerX);
            double degrees = (radian * 180) / Math.PI;
            double angle = 0;
            if (degrees < 0) {
                angle = 360 + degrees;
            } else {
                angle = degrees;
            }
            int sector = (int) (angle / sectorAngle) + 1;

            Log.wtf("sector", sector + "");
            if (event.getAction() == MotionEvent.ACTION_DOWN) {

                    rate = sector;
                    invalidate();
                    return true;

            }
            return true;
        }
        return true;

    }


    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        x = getWidth();
        y = getHeight();
        radius = (Math.min(x, y) - 50) / 2;
        centerX = x / 2;
        centerY = y / 2;
        Log.w("width", " radius " + radius + " - x " + x + " y" + y);
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStyle(Paint.Style.FILL);


        // canvas.drawCircle(x / 2, y / 2, radius, paint);
        RectF rectF = new RectF(centerX - radius, centerY - radius, centerX + radius, centerY + radius);
        //canvas.drawOval(rectF, paint);

        paint.setColor(Color.parseColor("#00ff00"));
        for (int i = 0; i < sectors; i++) {
            paint.setColor(Color.parseColor("" + colors3[i]));
            canvas.drawArc(rectF, sectorAngle * i, sectorAngle, true, paint);
        }


        paint.setColor(Color.parseColor("#000000"));
        canvas.drawCircle(x / 2, y / 2, radius / 3, paint);

        paint.setColor(Color.parseColor("#00E5FF"));
        canvas.drawCircle(x / 2, y / 2, radius / 3 - 5, paint);

        paint.setColor(Color.parseColor("#000000"));
        canvas.drawCircle(x / 2, y / 2, radius / 3 - 7, paint);

        paint.setColor(Color.parseColor("#00E5FF"));
        paint.setTextSize(30);
        drawCenter(canvas, paint, rate + "");
        // canvas.drawText("6", x / 2, y / 2, paint);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int width = getMeasuredWidth();
        int height = getMeasuredHeight();
        int widthWithoutPadding = width - getPaddingLeft() - getPaddingRight();
        int heigthWithoutPadding = height - getPaddingTop() - getPaddingBottom();

        int sqwidth=Math.min(widthWithoutPadding,heigthWithoutPadding);



        setMeasuredDimension(sqwidth, sqwidth);
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
