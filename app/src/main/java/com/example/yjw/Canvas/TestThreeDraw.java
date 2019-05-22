package com.example.yjw.Canvas;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PointF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

import java.util.ArrayList;

/**
 * 当用户点击时将出现一个小点，拖动时将画出一条用细点组成的虚线
 */
public class TestThreeDraw extends View {
    private Paint paint;
    private ArrayList<PointF> graphics = new ArrayList<PointF>();

    public TestThreeDraw(Context context) {
        super(context);
    }

    public TestThreeDraw(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        paint.setColor(Color.RED);
        paint.setStrokeWidth(3);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);
    }

    public TestThreeDraw(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        graphics.add(new PointF(event.getX(), event.getY()));

        invalidate(); //重新绘制区域
        return true;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        for (PointF point : graphics) {
            canvas.drawPoint(point.x, point.y, paint);
        }
    }
}
