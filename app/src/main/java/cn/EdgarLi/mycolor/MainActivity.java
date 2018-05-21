package cn.EdgarLi.mycolor;

import java.io.Serializable;

import android.app.Activity;
import android.app.AlertDialog;
import android.app.AlertDialog.Builder;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.Window;
import android.widget.Button;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import cn.EdgarLi.mycolor.R;
import cn.EdgarLi.tools.CardAdaperA;
import cn.EdgarLi.tools.ColorDatabase;
import cn.EdgarLi.tools.ColorInfo;
import cn.EdgarLi.tools.Mcallback;

@SuppressWarnings("serial")
public class MainActivity extends Activity implements Mcallback {
	Button button;
	ListView colorList;
	CardAdaperA<ColorInfo> adapter;
	View popMenu, bg;
	boolean showMenu = false;
	public static Uri imageUri;// 图片的Uri
	public static Bitmap bitmap;
	public static ColorDatabase dat = null;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);// 去掉标题栏
		setContentView(R.layout.layout_main);

		TextView title = (TextView) findViewById(R.id.text_title);
		title.setText("收藏列表");

		popMenu = findViewById(R.id.pop_menu);
		popMenu.setVisibility(View.GONE);
		bg = findViewById(R.id.back_hid);
		// popMenu.setVisibility(View.VISIBLE);
		colorList = (ListView) findViewById(R.id.color_listView);
		dat = new ColorDatabase(this);

		adapter = new CardAdaperA<ColorInfo>(this, R.layout.item_color_card_0, dat.getArrayList());
		adapter.setCallback(this);
		colorList.setAdapter(adapter);

	}

	public void addBtn(View view) {
		bg.setVisibility(View.GONE);
		popMenu.setVisibility(View.GONE);
		showMenu = false;
		adapter.notifyDataSetChanged();
	}

	public void btnCamera(View view) {
		bg.setVisibility(View.GONE);
		popMenu.setVisibility(View.GONE);
		showMenu = false;

		Intent i = new Intent(MainActivity.this, PicPicker.class);
		i.putExtra("name", "camear");
		startActivity(i);

	}

	public void btnPhoto(View view) {
		bg.setVisibility(View.GONE);
		popMenu.setVisibility(View.GONE);

		showMenu = false;
		Intent i = new Intent(MainActivity.this, PicPicker.class);
		i.putExtra("name", "map");

		startActivity(i);

	}

	public void btnLib(View view) {
		bg.setVisibility(View.GONE);
		popMenu.setVisibility(View.GONE);

		showMenu = false;

		Intent i = new Intent(MainActivity.this, ColorLibActivity.class);
		startActivity(i);
	}

	public void btnMenu(View view) {
		showMenu = !showMenu;
		if (showMenu) {
			bg.setVisibility(View.VISIBLE);
			popMenu.setVisibility(View.VISIBLE);

		} else {
			bg.setVisibility(View.GONE);
			popMenu.setVisibility(View.GONE);

		}
	}

	public void btnExit(View view) {
		finish();
	}

	public void btnBG(View view) {
		popMenu.setVisibility(View.GONE);
		bg.setVisibility(View.GONE);
		showMenu = false;
	}

	@Override
	protected void onResume() {

		super.onResume();
		adapter.notifyDataSetChanged();
	}

	@Override
	public void share(String arg) {
		// TODO Auto-generated method stub
		String activityTitle = "分享";
		String msgTitle = "取色";
		Intent intent = new Intent(Intent.ACTION_SEND);

		intent.setType("text/plain"); // 纯文本

		intent.putExtra(Intent.EXTRA_SUBJECT, msgTitle);
		intent.putExtra(Intent.EXTRA_TEXT, arg);
		intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
		startActivity(Intent.createChooser(intent, activityTitle));
	}

	@Override
	public void copy(String arg) {
		// TODO Auto-generated method stub
		ClipboardManager copy = (ClipboardManager) this.getSystemService(Context.CLIPBOARD_SERVICE);
		copy.setText(arg);
		Toast.makeText(this, "已复制到粘贴板", Toast.LENGTH_SHORT).show();
	}

	@Override
	public void del(final int id) {
		new AlertDialog.Builder(MainActivity.this).setTitle("提示")
		.setMessage("是否删除此项？")// 设置显示的内容
		.setNegativeButton("是", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				dat.del(id);
				adapter.notifyDataSetChanged();
			}
		}).setPositiveButton("否", new DialogInterface.OnClickListener() {
			@Override
			public void onClick(DialogInterface dialog, int which) {
				return;
			}
		}).show();
	}


}
