package cn.EdgarLi.tools;

import java.util.ArrayList;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.RelativeLayout;
import android.widget.TextView;
import cn.EdgarLi.mycolor.R;



public class CardAdaperA<T> extends ArrayAdapter<T> {

	private LayoutInflater mInflater;
	private Context mContext;
	private int mResource;
	private ArrayList<ColorInfo> dat;
	private Mcallback mb = null;

	public void setCallback(Mcallback mb) {
		this.mb = mb;
	}

	@Override
	public int getCount() {
		// TODO Auto-generated method stub
		if (dat == null) {
			return 0;
		}
		return dat.size();
	}

	public CardAdaperA(Context context, int resource, ArrayList<ColorInfo> dat) {
		super(context, resource);
		intit(context, resource, dat);
	}

	public void intit(Context context, int resource, ArrayList<ColorInfo> dat) {
		mInflater = LayoutInflater.from(context);
		this.mContext = context;
		this.mResource = resource;
		this.dat = dat;
	}

	@SuppressLint("ViewHolder")
	@Override
	
	public View getView(final int position, View convertView, ViewGroup parent) {
		View view = mInflater.inflate(mResource, parent, false);
		RelativeLayout bg = (RelativeLayout) view.findViewById(R.id.card_backgroung);
		TextView text1 = (TextView) view.findViewById(R.id.text_color_hex);
		TextView text2 = (TextView) view.findViewById(R.id.text_color_rgb);
		text1.setText("#" + dat.get(position).hex.toString());
		text2.setText("RGB:" + dat.get(position).rgb.toString());
		text1.setTextColor(ColorHelper.fitColor(dat.get(position).color));
		bg.setBackgroundColor(dat.get(position).color);

		((Button) view.findViewById(R.id.btn_del)).setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mb.del(position);
			}
		});

		((Button) view.findViewById(R.id.btn_copy)).setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mb.copy(dat.get(position).hex.toString());
			}
		});

		((Button) view.findViewById(R.id.btn_share)).setOnClickListener(new View.OnClickListener() {

			@Override
			public void onClick(View v) {
				// TODO Auto-generated method stub
				mb.share(dat.get(position).hex.toString());
			}
		});

		return view;
	}

}
