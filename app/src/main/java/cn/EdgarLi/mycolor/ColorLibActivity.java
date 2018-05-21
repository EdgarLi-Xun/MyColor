package cn.EdgarLi.mycolor;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import cn.EdgarLi.mycolor.R;


public class ColorLibActivity extends Activity {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.layout_lib_home);
	}

	public void btn_red(View view) {

		Intent i = new Intent(ColorLibActivity.this, ColorCommon.class);
		i.putExtra("color", 0);
		i.putExtra("title", "红色系");
		startActivity(i);
	}

	public void btn_blue(View view) {

		Intent i = new Intent(ColorLibActivity.this, ColorCommon.class);
		i.putExtra("color", 1);
		i.putExtra("title", "蓝色系");
		startActivity(i);
	}

	public void btn_green(View view) {

		Intent i = new Intent(ColorLibActivity.this, ColorCommon.class);
		i.putExtra("color", 2);
		i.putExtra("title", "绿色系");
		startActivity(i);
	}

	public void btn_grey(View view) {

		Intent i = new Intent(ColorLibActivity.this, ColorCommon.class);
		i.putExtra("color", 3);
		i.putExtra("title", "灰色系");
		startActivity(i);
	}

	public void btn_brown(View view) {

		Intent i = new Intent(ColorLibActivity.this, ColorCommon.class);
		i.putExtra("color", 4);
		i.putExtra("title", "综色系");
		startActivity(i);
	}

	public void btn_purple(View view) {

		Intent i = new Intent(ColorLibActivity.this, ColorCommon.class);
		i.putExtra("color", 5);
		i.putExtra("title", "紫色系");
		startActivity(i);
	}

	public void btn_yellow(View view) {

		Intent i = new Intent(ColorLibActivity.this, ColorCommon.class);
		i.putExtra("color", 6);
		i.putExtra("title", "黄色系");
		startActivity(i);
	}
}
