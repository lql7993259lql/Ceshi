package com.example.ceshi;

import java.io.File;

import com.example.ceshi.util.ApkUtils;
import com.example.ceshi.util.BsPatch;
import com.example.ceshi.util.Constants;
import com.example.ceshi.util.DownloadUtils;
import com.weile.demo.MyApp;
import com.weile.demo.Test;

import android.app.Activity;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		
		Button btn = (Button)findViewById(R.id.cs);
		btn.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				String str = Test.Plus3();
				Toast.makeText(getApplicationContext(), str, Toast.LENGTH_LONG).show();
				
			}
		});
		 new ApkUpdateTask().execute();
	}
	
	class ApkUpdateTask extends AsyncTask<Void, Void, Boolean>{

		@Override
		protected Boolean doInBackground(Void... params) {
			try {
				//1.下载差分包
				Log.d("jason", "开始下载");
				File patchFile = DownloadUtils.download(Constants.URL_PATCH_DOWNLOAD);
				
				//获取当前应用的apk文件/data/app/app
				String oldfile = ApkUtils.getSourceApkPath(MainActivity.this, getPackageName());
				//2.合并得到新版本的APK文件
				String newfile = Constants.NEW_APK_PATH;
				String patchfile = patchFile.getAbsolutePath();
				BsPatch.patch(oldfile, newfile, patchfile);
				
				Log.d("jason", "oldfile:"+oldfile);
				Log.d("jason", "newfile:"+newfile);
				Log.d("jason", "patchfile:"+patchfile);
			} catch (Exception e) {
				e.printStackTrace();
				return false;
			}
			
			return true;
		}
		
		@Override
		protected void onPostExecute(Boolean result) {
			super.onPostExecute(result);
			//3.安装
			if(result){
				Toast.makeText(MainActivity.this, "您正在进行无流量更新", Toast.LENGTH_SHORT).show();
				ApkUtils.installApk(MainActivity.this, Constants.NEW_APK_PATH);
			}
		}
	}
}
