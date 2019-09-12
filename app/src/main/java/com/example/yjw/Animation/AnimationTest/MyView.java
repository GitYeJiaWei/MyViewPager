package com.example.yjw.Animation.AnimationTest;

import android.animation.AnimatorSet;
import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateInterpolator;
import android.view.animation.BounceInterpolator;

public class MyView extends View {
    private static float RADIUS = 50f;//圆的半径
    private Paint paint;//画笔
    private Point currentPoint;
    private String color;

    public MyView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint(Paint.ANTI_ALIAS_FLAG);
        paint.setColor(Color.BLUE);
    }

    public String getColor() {
        return color;
    }

    /**
     * 改变了画笔颜色之后立即刷新视图，然后onDraw()方法就会调用。
     * 在onDraw()方法当中会根据当前画笔的颜色来进行绘制，这样颜色也就会动态进行改变了
     * @param color
     */
    public void setColor(String color) {
        this.color = color;
        paint.setColor(Color.parseColor(color));
        invalidate();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (currentPoint == null){
            currentPoint = new Point(RADIUS,RADIUS);
            drawCircle(canvas);
            startAnimation();
        }else {
            drawCircle(canvas);
        }
    }

    private void drawCircle(Canvas canvas){
        float x = currentPoint.getX();
        float y = currentPoint.getY();
        canvas.drawCircle(x,y,RADIUS,paint);
    }

    private void startAnimation(){
        Point startPoint = new Point(getWidth()/2,RADIUS);
        Point endPoint = new Point(getWidth()/2,getHeight()-RADIUS);
        ValueAnimator va = ValueAnimator.ofObject(new PointEvaluator(),startPoint,endPoint);
        va.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator animation) {
                currentPoint = (Point) animation.getAnimatedValue();//获取跟新后的位置
                invalidate();
            }
        });
        ObjectAnimator oa = ObjectAnimator.ofObject(this,"color",
                new ColorEvaluator(),"#0000FF","#FF0000");
        AnimatorSet as = new AnimatorSet();//组合动画
        as.play(va).with(oa);
        //as.setInterpolator(new AccelerateInterpolator(2f));
        //as.setInterpolator(new BounceInterpolator());
        as.setInterpolator(new DecelerateAccelerateInterpolator());
        as.setDuration(5000);
        as.start();
    }
}
