package com.example.yjw.Canvas;

import android.content.Context;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.View;

import com.example.yjw.myviewpager.R;

public class TestOnDraw extends View {
    private Paint paint;

    public TestOnDraw(Context context) {
        super(context);
    }

    public TestOnDraw(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        paint = new Paint();
        paint.setColor(Color.YELLOW);
        paint.setStrokeJoin(Paint.Join.ROUND);
        paint.setStrokeCap(Paint.Cap.ROUND);//设置线条的末端
        paint.setStrokeWidth(30);//设置线条宽度
        paint.setTextSize(24);//设置文本大小
        paint.setAntiAlias(true);// 设置抗锯齿开关
        paint.setAlpha(1);//设置透明度
        paint.setStyle(Paint.Style.STROKE);//描边，空心 Paint.Style.FILL：填充内部
    }

    public TestOnDraw(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    /*第一个是在类面里实例化的时候被调用的，也就是new class();
    第二个是在layout XML当中被调用。
    第三个是需要主动被调用，例如放在第二个构造函数*/

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //显示区域用某个颜色填充满
        canvas.drawColor(Color.BLUE);

        //画圆形(圆心，半径)
        canvas.drawCircle(500, 500, 200, paint);

        //绘制弧线区域,定义一个矩形区域(左上右下)
        RectF rectF = new RectF(100, 100, 1000, 1000);
        //以 RectF 中心为坐标中心，中心所在直线为水平线，负角度弧斜上走，
        // 正角度弧斜下走,或者说以时钟三点钟为0度，顺时针为正，逆时针为负
        canvas.drawArc(rectF, //弧线所使用的矩形区域大小
                0,  //开始角度
                90, //扫过的角度
                false, //是否使用中心，若为true表示此弧会和 RectF 中心相连形成扇形，否则，弧的两头直接相连形成图形
                paint);

        //绘制弧线区域，扇形,定义一个矩形区域(左上右下)
        RectF rectF1 = new RectF(0, 0, 100, 100);
        canvas.drawArc(rectF1, //弧线所使用的矩形区域大小
                0,  //开始角度
                90, //扫过的角度
                true, //是否使用中心
                paint);

        //画一条直线（开始和结束点坐标）
        canvas.drawLine(50, 50, 1000, 1000, paint);

        //定义一个矩形区域
        RectF oral = new RectF(50, 200, 100, 500);
        //矩形区域内切椭圆
        canvas.drawOval(oral, paint);

        //按照既定点 绘制文本内容
        canvas.drawPosText("android", new float[]{
                100, 100,//第一个字母在坐标
                200, 200,
                300, 300, 400, 400, 500, 500, 600, 600, 700, 700}, paint);

        //绘制矩形区域
        RectF rectF2 = new RectF(100, 30, 200, 130);
        //绘制矩形
        canvas.drawRect(rectF2, paint);

        //绘制矩形区域
        RectF rectF3 = new RectF(210, 30, 310, 130);
        //绘制圆角矩阵，四个圆心画圆角，把矩阵的的四个角替换掉
        canvas.drawRoundRect(rectF3,
                30,//x轴的半径
                30,//y轴的半径
                paint);

        //定义一条路径 ,移动到指定坐标
        Path path = new Path();
        path.moveTo(120, 10);
        path.lineTo(130, 50);
        path.lineTo(10, 10);
        path.lineTo(120, 10);
        canvas.drawPath(path, paint);

        //定义一条路径 ,移动到指定坐标
        Path path1 = new Path();
        path1.moveTo(132, 500);
        path1.lineTo(132, 550);
        path1.lineTo(132, 650);
        path1.lineTo(150, 700);
        path1.lineTo(160, 800);
        path1.lineTo(132, 500);
        //hOffset : 与路径起始点的水平偏移距离
        //vOffset : 与路径中心的垂直偏移量
        canvas.drawTextOnPath("android", path1, 50f, 50f, paint);

        String text = "akjhfawbfsjdwi我i";
        Path path2 = new Path();
        path2.addCircle(500, 500, 300, Path.Direction.CCW);
        canvas.drawTextOnPath(text, path2, 0f, 0f, paint);

        //移动和旋转
        //二维变换的另一种方式 —— Matrix
        Matrix matrix = new Matrix();
        matrix.postTranslate(1000, 0);//左移1000
        matrix.postRotate(45);//顺时针旋转45度
        canvas.drawBitmap(BitmapFactory.decodeResource(getResources(), R.mipmap.baidu), matrix, paint);

        canvas.translate(500, 0);//移动当前画布
        canvas.drawCircle(100, 0, 50, paint);

        //save 之后可以调用 Canvas 的平移、放缩、旋转、错切、裁剪等对当前画布进行操作,
        // 再进行相应的绘制，避免影响画布上已绘制的 view，
        // 配合 canvas.restore()(将当前画布恢复到初始状态) 使用
        paint.setColor(Color.RED);
        canvas.save();
        canvas.scale(0.5f, 0.5f);//x,y均缩小一半
        canvas.drawCircle(400, 400, 50, paint);
        canvas.restore();
        paint.setColor(Color.WHITE);
        canvas.drawCircle(400, 400, 50, paint);

        canvas.drawCircle(500, 200, 50, paint);
        canvas.save();
        canvas.scale(0.5f,0.5f,500,200);//x,y均缩小一半,px,py为缩放后的画布提供坐标点
        paint.setColor(Color.RED);
        canvas.drawCircle(500,200,50,paint);
        canvas.restore();
    }
}
