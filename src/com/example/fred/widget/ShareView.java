package com.example.fred.widget;

import com.willen.topFloatDemo.R;

import android.R.integer;
import android.animation.AnimatorSet;
import android.animation.ValueAnimator;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.WindowManager;
import android.view.View.MeasureSpec;
import android.view.animation.BounceInterpolator;
import android.widget.Toast;

@SuppressLint("NewApi")
public class ShareView extends View{
	 private static final float RADIUS = 50f;  
	 private static final float HEIGHT_VIEW = 200f; 
	 private static final float  DISTANCEPOINT=200;
	 private OnDownActionListener mDown = null;
     private Point currentPoint1;  
     private Point currentPoint2;  
     private Point currentPoint3;  
     private float  startX;
     private float  startY;
     private float  width;
     private float height;
     private Paint mPaint; 
     private  Bitmap bitmap1;
     private  Bitmap bitmap2;
     private  Bitmap bitmap3;
	  
	    public ShareView(Context context, AttributeSet attrs) {  
	        super(context, attrs);  
	        mPaint = new Paint(Paint.ANTI_ALIAS_FLAG);  
	        mPaint.setColor(Color.BLUE); 
	        //初始化
	        WindowManager wm = (WindowManager) getContext().getSystemService(Context.WINDOW_SERVICE);
		    height = wm.getDefaultDisplay().getHeight();
		   // height=400;
		    width = wm.getDefaultDisplay().getWidth();
		     bitmap1 = BitmapFactory.decodeResource(getResources(), R.drawable.qq);  
		     bitmap2 = BitmapFactory.decodeResource(getResources(), R.drawable.tw);  
		     bitmap3 = BitmapFactory.decodeResource(getResources(), R.drawable.weibo);
		     System.out.println("bitmap_width  "+bitmap1.getWidth());
		     startX=(float) ((width-bitmap1.getWidth()*1-DISTANCEPOINT*2)/2);
			 startY=height-RADIUS;
	    }  
	  
	    @Override  
	    protected void onDraw(Canvas canvas) {  
	    	if(currentPoint1==null){
	    		currentPoint1 = new Point(startX, startY);  
			    currentPoint2 = new Point(startX+DISTANCEPOINT, startY);
			    currentPoint3 = new Point(startX+2*DISTANCEPOINT, startY); 
			    canvas.drawBitmap(bitmap1, currentPoint1.getX(),currentPoint1.getY(), mPaint); 
			    canvas.drawBitmap(bitmap2, currentPoint2.getX(),currentPoint2.getY(), mPaint); 
			    canvas.drawBitmap(bitmap3, currentPoint3.getX(),currentPoint3.getY(), mPaint); 
		    	startAnimation(1,startX,startY);
		    	startAnimation(2,startX+DISTANCEPOINT,startY);
		    	startAnimation(3,startX+2*DISTANCEPOINT,startY);
	    	}else {
			canvas.drawBitmap(bitmap1, currentPoint1.getX(), currentPoint1.getY(), mPaint);
			canvas.drawBitmap(bitmap2, currentPoint2.getX(), currentPoint2.getY(), mPaint);
			canvas.drawBitmap(bitmap3, currentPoint3.getX(), currentPoint3.getY(), mPaint);
			}
	    }  
	  
	    @SuppressWarnings("deprecation")
		@SuppressLint("NewApi")
		private void startAnimation(int flag,float startX,float startY) { 
	        Point startPoint = new Point(startX, startY);  
	        Point endPoint = new Point(startX, startY-HEIGHT_VIEW);  
	        ValueAnimator anim = ValueAnimator.ofObject(new PointEvaluator(), startPoint, endPoint);
	        if(flag==1){
	        	 anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {  
	 	            @SuppressLint("NewApi")
	 				@Override  
	 	            public void onAnimationUpdate(ValueAnimator animation) { 
	 	            	currentPoint1 = (Point) animation.getAnimatedValue(); 
	 	                invalidate();  
	 	            }  
	 	        });
        	    anim.setInterpolator(new BounceInterpolator());
				anim.setDuration(3000);
				anim.start();
	        }
	        if(flag==2){
	        	 anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {  
	 	            @SuppressLint("NewApi")
	 				@Override  
	 	            public void onAnimationUpdate(ValueAnimator animation) { 
	 	            	currentPoint2 = (Point) animation.getAnimatedValue(); 
	 	                invalidate();  
	 	            }  
	 	        }); 
        		anim.setInterpolator(new BounceInterpolator());
				anim.setDuration(3000);
				anim.setStartDelay(500);
				anim.start();
	        }
	        if(flag==3){
	        	 anim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {  
	 	            @SuppressLint("NewApi")
	 				@Override  
	 	            public void onAnimationUpdate(ValueAnimator animation) { 
	 	            	currentPoint3 = (Point) animation.getAnimatedValue(); 
	 	                invalidate();  
	 	            }  
	 	        }); 
        	    anim.setInterpolator(new BounceInterpolator());
				anim.setDuration(3000);
				anim.setStartDelay(1000);
				anim.start();
		        }
	    } 
	    
	    @Override  
	    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {  
	        setMeasuredDimension((int) width, (int) height);  
	    } 
	    
	   @Override 
	  public boolean onTouchEvent(MotionEvent event) {
		   float x=event.getX();
		   float y=event.getY();
		   if (event.getAction() == MotionEvent.ACTION_UP){
			  System.out.println("up"); 
		   }else if(event.getAction() == MotionEvent.ACTION_DOWN){
			   System.out.println("down");
			   int result=0;
			   result=isClick(x, y);
			   if(result==1){
				   mDown.OnDown(1);
				   return true;
			   }else if(result==2){
				   mDown.OnDown(2);
				   return true;
			   }else if(result==3){
				   mDown.OnDown(3);
				   return true;
			   }
		   }
		  mDown.OnDown(0);
		  return super.onTouchEvent(event);
	  }

	private int isClick(float x, float y) {
		// TODO Auto-generated method stub
		if(y>startY-HEIGHT_VIEW&&y<startY-HEIGHT_VIEW+bitmap1.getHeight()&&x>startX&&x<startX+bitmap1.getWidth()){
			return 1;
		}
		if(y>startY-HEIGHT_VIEW&&y<startY-HEIGHT_VIEW+bitmap1.getHeight()&&x>startX+DISTANCEPOINT&&x<startX+DISTANCEPOINT+bitmap1.getWidth()){
			return 2;
		}
		if(y>startY-HEIGHT_VIEW&&y<startY-HEIGHT_VIEW+bitmap1.getHeight()&&x>startX+2*DISTANCEPOINT&&x<startX+2*DISTANCEPOINT+bitmap1.getWidth()){
			return 3;
		}
		return 0;
	}
	// 为每个接口设置监听器
	  public void setOnDownActionListener(OnDownActionListener down) {
	    mDown = down;
	  }
	public interface OnDownActionListener {
	    public void OnDown(int clickPoint);
	  }
	  

	    
}
