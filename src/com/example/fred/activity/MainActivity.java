package com.example.fred.activity;

import android.app.Activity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.example.fred.R;
import com.example.fred.widget.SharePopWindow;
import com.example.fred.widget.SharePopWindow.OnClickPointListener;
import com.example.fred.widget.ShareView;
import com.example.fred.widget.ShareView.OnDownActionListener;

public class MainActivity extends Activity     {
	 
    Button shareButton;
    SharePopWindow sharePopWindow;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		//初始化控件
		shareButton=(Button) this.findViewById(R.id.share_button);
		shareButton.setOnClickListener(new OnClickListener() {             
	            public void onClick(View v) {  
	                //实例化SelectPicPopupWindow  
	            	sharePopWindow = new SharePopWindow(MainActivity.this); 
	            	OnClickPointListener onClickPointListener=new OnClickPointListener() {
						
						@Override
						public void OnClickPoint(int clickPoint) {
							// TODO Auto-generated method stub
							if(clickPoint==1){
								Toast.makeText(MainActivity.this, "qq", Toast.LENGTH_SHORT).show();
							}else if(clickPoint==2){
								Toast.makeText(MainActivity.this, "tw", Toast.LENGTH_SHORT).show();
							}else if(clickPoint==3){
								Toast.makeText(MainActivity.this, "wb", Toast.LENGTH_SHORT).show();
							}else{
								sharePopWindow.dismiss();
							}
						}
					};
	            	sharePopWindow.setOnClickPointListener(onClickPointListener);
	                //显示窗口  
	            	sharePopWindow.showAtLocation(MainActivity.this.findViewById(R.id.share_button), Gravity.BOTTOM|Gravity.CENTER_HORIZONTAL, 0, 0); //设置layout在PopupWindow中显示的位置  
	            }  
	        }); 
	}
	 

 
  
}
