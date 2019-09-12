package com.example.yjw.Animation.AnimationTest;

import android.animation.TypeEvaluator;

public class PointEvaluator implements TypeEvaluator {
    /**
     * 第一个参数fraction非常重要，这个参数用于表示动画的完成度的，
     * 我们应该根据它来计算当前动画的值应该是多少，第二第三个参数分别表示动画的初始值和结束值
     * @param fraction
     * @param startValue
     * @param endValue
     * @return
     */
    @Override
    public Object evaluate(float fraction, Object startValue, Object endValue) {
        /**
         * 先是将startValue和endValue强转成Point对象，
         * 然后同样根据fraction来计算当前动画的x和y的值，
         * 最后组装到一个新的Point对象当中并返回
         */
        Point startPoint = (Point) startValue;
        Point endPoint = (Point) endValue;
        float x = startPoint.getX() + fraction * (endPoint.getX() - startPoint.getX());
        float y = startPoint.getY() + fraction * (endPoint.getY() - startPoint.getY());
        Point point = new Point(x, y);
        return point;
    }
}
