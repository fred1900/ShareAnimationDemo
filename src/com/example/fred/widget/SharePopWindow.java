package com.example.fred.widget;

import com.example.fred.widget.ShareView.OnDownActionListener;

import com.example.fred.R;
import android.app.Activity;
import android.content.Context;
import android.graphics.drawable.ColorDrawable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.WindowManager.LayoutParams;
import android.widget.PopupWindow;

public class SharePopWindow extends PopupWindow implements OnDownActionListener{
	    private View sharePopWindow;  
	    private ShareView shareView;
	    private OnClickPointListener onclickPointListener;
	    public SharePopWindow(Activity context ) {  
	        super(context);  
	        LayoutInflater inflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
	        sharePopWindow=inflater.inflate(R.layout.share_pop_window, null); 
	        shareView=(ShareView) sharePopWindow.findViewById(R.id.share_view);
	        shareView.setOnDownActionListener(this);
	        this.setContentView(sharePopWindow);  
	        //设置SelectPicPopupWindow弹出窗体的宽  
	        this.setWidth(LayoutParams.MATCH_PARENT);  
	        //设置SelectPicPopupWindow弹出窗体的高  
	        this.setHeight(400);  
	        //设置SelectPicPopupWindow弹出窗体可点击  
	        this.setFocusable(true);  
	        //设置SelectPicPopupWindow弹出窗体动画效果  
	        //实例化一个ColorDrawable颜色为半透明  
	        ColorDrawable dw = new ColorDrawable(0xb0000000);  
	        //设置SelectPicPopupWindow弹出窗体的背景  
	        this.setBackgroundDrawable(dw);
	       this.setAnimationStyle(R.style.popWindow_animation);
}
	    public void setOnClickPointListener(OnClickPointListener onclickPointListener){
	    	this.onclickPointListener=onclickPointListener;
	    	
	    }
	    public interface OnClickPointListener {
		    public void OnClickPoint(int clickPoint);
		  }
		@Override
		public void OnDown(int clickPoint) {
			// TODO Auto-generated method stub
			onclickPointListener.OnClickPoint(clickPoint);
			
		}
}
