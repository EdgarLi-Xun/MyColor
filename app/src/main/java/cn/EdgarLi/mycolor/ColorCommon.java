/**
 * 
 */
package cn.EdgarLi.mycolor;

import java.util.ArrayList;
import android.content.ClipboardManager;
import android.content.Context;
import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;
import cn.EdgarLi.mycolor.R;
import cn.EdgarLi.tools.CardAdaperB;
import cn.EdgarLi.tools.ColorInfo;
import cn.EdgarLi.tools.Mcallback;
import cn.EdgarLi.tools.StaticData;


public class ColorCommon extends Activity implements  Mcallback {

	Thread th;

	private String[] mListTitle;
	private String[] mListStr;

	private ArrayList<ColorInfo> mData;
	ListView listview;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);

		setContentView(R.layout.layout_lib_list);
		listview = (ListView) findViewById(R.id.listView1);

		Intent intent = this.getIntent();
		int id = intent.getIntExtra("color", 0);
		
		TextView title=(TextView) findViewById(R.id.text_title);
		title.setText(intent.getStringExtra("title"));

		mListTitle = StaticData.getHex(id);
		mListStr = StaticData.getRGB(id);

		mData = getData();
		CardAdaperB adapter = new CardAdaperB(this, mData, listview);
		listview.setAdapter(adapter);
		adapter.setCallback(this);

	}

	private ArrayList<ColorInfo> getData() {
		ArrayList<ColorInfo> list = new ArrayList<ColorInfo>();

		for (int i = 0; i < mListTitle.length; i++) {
			list.add(new ColorInfo(mListTitle[i], mListStr[i]));
		}
		return list;

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

	public void btnExit(View view) {
		finish();
	}

	@SuppressWarnings("deprecation")
	@Override
	public void copy(String arg) {
		// TODO Auto-generated method stub
		ClipboardManager copy = (ClipboardManager) ColorCommon.this.getSystemService(Context.CLIPBOARD_SERVICE);
		copy.setText(arg);
		Toast.makeText(this, "已复制到粘贴板", Toast.LENGTH_SHORT).show();
	}



	@Override
	public void del(int id) {
		// TODO Auto-generated method stub
		
	}

}
