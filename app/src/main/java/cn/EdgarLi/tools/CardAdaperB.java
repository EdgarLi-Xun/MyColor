/**
 * 
 */
package cn.EdgarLi.tools;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;
import cn.EdgarLi.mycolor.MainActivity;
import cn.EdgarLi.mycolor.R;


public class CardAdaperB extends BaseAdapter {

	private ArrayList<ColorInfo> mData;
	private LayoutInflater mInflater;
	private ListView listView = null;
	private Mcallback mb = null;
	private Context context;
	public TextView title;
	public TextView info;
	public Button copy;
	public Button share;
	public Button collect;
	private RelativeLayout background;

	private ColorDatabase dat;

	public CardAdaperB(Context context, ArrayList<ColorInfo> mData, ListView listView) {
		this.mInflater = LayoutInflater.from(context);
		this.mData = mData;
		this.listView = listView;
		this.context = context;
		this.dat = MainActivity.dat;
	}

	public void setCallback(Mcallback mb) {
		this.mb = mb;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		return mData.size();
	}


	@SuppressLint("ViewHolder")
	@Override
	public View getView(final int position, View convertView, ViewGroup parent) {

		View view = mInflater.inflate(R.layout.item_color_card_1, parent, false);

		title = (TextView) view.findViewById(R.id.text_Hex);
		info = (TextView) view.findViewById(R.id.text_rgb);
		copy = (Button) view.findViewById(R.id.btn_copy);
		share = (Button) view.findViewById(R.id.btn_share);
		collect = (Button) view.findViewById(R.id.btn_collect);
		background = (RelativeLayout) view.findViewById(R.id.card_backgroung);

		title.setText((String) mData.get(position).hex);

		info.setText((String) mData.get(position).rgb);

		String back = (String) mData.get(position).rgb;
		int[] a = new int[3];
		a = cut(back);
		final int color = Color.rgb(a[0], a[1], a[2]);
		title.setTextColor(ColorHelper.fitColor(color));
		background.setBackgroundColor(color);
		copy.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				mb.copy((String) mData.get(position).hex + " " + (String) mData.get(position).rgb);
				
			}

		});

		share.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {

				mb.share((String) mData.get(position).hex + " " + (String) mData.get(position).rgb);
			}

		});

		collect.setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// 收藏
				switch (dat.add(color)) {
				case 1:
					Toast.makeText(context, "收藏成功", Toast.LENGTH_SHORT).show();
					break;
				case 0:
					Toast.makeText(context, "已存在", Toast.LENGTH_SHORT).show();
					break;
				case -1:
					Toast.makeText(context, "系统错误", Toast.LENGTH_SHORT).show();
					break;

				}
			}
		});
		return view;
	}

	private int[] cut(String arg) {
		String[] a = arg.split(",");
		int a1[] = new int[a.length];
		for (int i = 0; i < a1.length; i++) {
			a1[i] = Integer.valueOf(a[i]);
		}
		return a1;
	}

	@Override
	public Object getItem(int position) {
		// TODO Auto-generated method stub
		return mData.get(position);
	}

	@Override
	public long getItemId(int position) {
		// TODO Auto-generated method stub
		return 0;
	}

}
