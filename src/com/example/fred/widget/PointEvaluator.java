package com.example.fred.widget;

import android.animation.TypeEvaluator;
import android.annotation.SuppressLint;

@SuppressLint("NewApi")
@SuppressWarnings("rawtypes")
public class PointEvaluator implements TypeEvaluator{  
	  
    @Override  
    public Object evaluate(float fraction, Object startValue, Object endValue) {  
        Point startPoint = (Point) startValue;  
        Point endPoint = (Point) endValue;  
        float x = startPoint.getX() + fraction * (endPoint.getX() - startPoint.getX());  
        float y = startPoint.getY() + fraction * (endPoint.getY() - startPoint.getY());  
        Point point = new Point(x, y);  
        return point;  
    }  
  
}  