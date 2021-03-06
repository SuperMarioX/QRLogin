package com.jikexueyuan.qrlogin;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;

import com.zxing.activity.CaptureActivity;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Paint.Cap;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends Activity implements OnClickListener {

	private Button btnScan;
	private EditText etUsername;
	private String s;

	//改成PC端相应地址
	//private static final String WEB_URL = "http://192.168.1.102/platform/qrlogin/";
	private static final String WEB_URL = "http://192.168.0.108/platform/qrlogin/";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		btnScan = (Button) findViewById(R.id.btnScan);
		btnScan.setOnClickListener(this);
		etUsername = (EditText) findViewById(R.id.etUsername);
		
	}

	@Override
	public void onClick(View v) {
		// 扫码操作
		Intent intent = new Intent(this, CaptureActivity.class);
		startActivityForResult(intent, 0);
	}

	@Override
	protected void onActivityResult(int requestCode, int resultCode, Intent data) {
		super.onActivityResult(requestCode, resultCode, data);
		if (resultCode == Activity.RESULT_OK) {
			String randnumber = data.getExtras().getString("result");
			String username = etUsername.getText().toString();
			//try {
				//s = URLEncoder.encode(username, "utf-8");
				//s = new String(username.getBytes("GBK"),"UTF-8");
			//} catch (UnsupportedEncodingException e) {
				//e.printStackTrace();
			//}
			String url = WEB_URL + "saveUsername.php?randnumber=" + randnumber
					+ "&username=" + username;
			
			//访问url
			HttpUtils.login(url);
		}
	}

}
