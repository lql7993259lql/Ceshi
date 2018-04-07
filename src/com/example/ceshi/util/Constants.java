package com.example.ceshi.util;

import java.io.File;

import android.os.Environment;

public class Constants {

	public static final String PATCH_FILE = "apk.patch";
	//public static final String URL_PATCH_DOWNLOAD = "http://172.19.167.1:8080/dn_app_update_server/"+PATCH_FILE;
	//linux remote
	public static final String URL_PATCH_DOWNLOAD = "http://192.168.1.105:8080/Test/"+PATCH_FILE;
	
	
	public static final String SD_CARD = Environment.getExternalStorageDirectory() + File.separator;
	
	//新版本apk的目录
	public static final String NEW_APK_PATH = SD_CARD+"apk_new.apk";
	
	public static final String PATCH_FILE_PATH = SD_CARD+PATCH_FILE;
	
}
