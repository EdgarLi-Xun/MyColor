package cn.EdgarLi.mycolor;

import java.io.FileNotFoundException;
import java.io.IOException;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.ClipboardManager;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Bitmap.Config;
import android.graphics.Canvas;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.MotionEvent;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.view.View.OnTouchListener;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import cn.EdgarLi.mycolor.R;
import cn.EdgarLi.tools.ColorDatabase;
import cn.EdgarLi.tools.ColorHelper;


public class PicPicker extends Activity {
    private static final int CHOOSE_PICTURE = 1;// 图库常量
    private static final int TAKE_PICTURE = 0;// 拍照常量
    protected static final String TAG = null;
    protected static final TextView tv_rgb = null;
    private ImageView picture;// 显示图片的组件
    Bitmap photo;
    int color;
    Button buttonview, color_view, copyHEX, share, takephoto, open, collect;
    TextView textHex;

    ColorDatabase dat;

    private ImageView posImg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.requestWindowFeature(Window.FEATURE_NO_TITLE);// 去掉标题栏
        setContentView(R.layout.layout_picker);

        TextView title = (TextView) findViewById(R.id.text_title);
        title.setText("采集颜色");

        picture = (ImageView) findViewById(R.id.picture);// 获取图片组件
        copyHEX = (Button) findViewById(R.id.copy_HEX);// 分享hex的时间按钮

        color_view = (Button) findViewById(R.id.color_picked);
        textHex = (TextView) findViewById(R.id.HEX);// 显示十六进制
        posImg = (ImageView) findViewById(R.id.pos);// 获取图片组件
        share = (Button) findViewById(R.id.share);
        open = (Button) findViewById(R.id.map_storage);
        takephoto = (Button) findViewById(R.id.camera);
        collect = (Button) findViewById(R.id.collect);

        Intent intent = this.getIntent();
        String name = intent.getStringExtra("name");
        //dat=(ColorDatabase) intent.getExtras().get("dat");
        dat = MainActivity.dat;
        choice(name);


        copyHEX.setOnClickListener(new OnClickListener() {

            @SuppressWarnings("deprecation")
            @Override
            public void onClick(View v) {
                // 复制
                ClipboardManager copy = (ClipboardManager) PicPicker.this.getSystemService(Context.CLIPBOARD_SERVICE);
                copy.setText(textHex.getText().toString());

                Toast.makeText(PicPicker.this, "已复制到粘贴板", Toast.LENGTH_SHORT).show();

            }
        });

        collect.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO 收藏
                // 收藏
                switch (dat.add(color)) {
                    case 1:
                        Toast.makeText(PicPicker.this, "收藏成功", Toast.LENGTH_SHORT).show();
                        break;
                    case 0:
                        Toast.makeText(PicPicker.this, "已存在", Toast.LENGTH_SHORT).show();
                        break;
                    case -1:
                        Toast.makeText(PicPicker.this, "系统错误", Toast.LENGTH_SHORT).show();
                        break;
                }
            }
        });

        share.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // TODO Auto-generated method stub
                String activityTitle = "分享";
                String msgTitle = "取色";
                Intent intent = new Intent(Intent.ACTION_SEND);

                intent.setType("text/plain"); // 纯文本

                intent.putExtra(Intent.EXTRA_SUBJECT, msgTitle);
                intent.putExtra(Intent.EXTRA_TEXT,
                        textHex.getText().toString() + "    " + color_view.getText().toString());
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                startActivity(Intent.createChooser(intent, activityTitle));
            }
        });
        open.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // 打开图库
                choice("map");

            }
        });

        takephoto.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View v) {
                // 打开相机
                choice("camear");

            }
        });
        // Toast.makeText(picture.this, "无图片", Toast.LENGTH_LONG).show();
        picture.setOnTouchListener(new OnTouchListener() {

            @SuppressLint("ClickableViewAccessibility")
            @Override
            public boolean onTouch(View v, MotionEvent event) {

                if (event.getAction() == MotionEvent.ACTION_MOVE || event.getAction() == MotionEvent.ACTION_DOWN) {
                    int x = (int) event.getX();
                    int y = (int) event.getY();
                    if (x > 0 && y > 0 && x < picture.getWidth() && y < picture.getHeight()) {
                        Bitmap bmpShop = Bitmap.createBitmap(picture.getWidth(), picture.getHeight(), Config.ARGB_8888);
                        Canvas cav = new Canvas(bmpShop);
                        picture.draw(cav);
                        posImg.setVisibility(View.VISIBLE);
                        RelativeLayout.LayoutParams params = (RelativeLayout.LayoutParams) posImg.getLayoutParams();
                        params.setMargins(x - 32, y - 32, picture.getWidth() - x + 64, picture.getHeight() - y + 64);
                        posImg.setLayoutParams(params);
                        color = bmpShop.getPixel(x, y);
                        // 获取颜色
                        int r = Color.red(color);
                        int g = Color.green(color);
                        int b = Color.blue(color);
                        color_view.setBackgroundColor(color);
                        color_view.setText(r + "," + g + "," + b);
                        textHex.setText("#" + ColorHelper.toHex(color));
                    }
                }
                return true;
            }

        });

    }

    private void choice(String name) {
        // TODO Auto-generated method stub

        if (name.equals("camear")) {
            Intent cameraIntent = new Intent(android.provider.MediaStore.ACTION_IMAGE_CAPTURE);

            startActivityForResult(cameraIntent, TAKE_PICTURE);
        } else {
            Intent i = new Intent(Intent.ACTION_PICK, android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
            i.setDataAndType(MediaStore.Images.Media.EXTERNAL_CONTENT_URI, "image/*");
            startActivityForResult(i, CHOOSE_PICTURE);

        }
    }

    protected void onActivityResult(int requestCode, int resultCode, Intent data) {

        if (resultCode == RESULT_OK) {
            switch (requestCode) {
                case TAKE_PICTURE:
                    photo = (Bitmap) data.getExtras().get("data");
                    picture.setImageBitmap(photo);

                    break;

                case CHOOSE_PICTURE:
                    ContentResolver resolver = getContentResolver();
                    // 照片的原始资源地址
                    Uri originalUri = data.getData();
                    try {
                        // 使用ContentProvider通过URI获取原始图片
                        photo = MediaStore.Images.Media.getBitmap(resolver, originalUri);
                        if (photo != null) {
                            picture.setImageBitmap(photo);

                        }
                    } catch (FileNotFoundException e) {
                        e.printStackTrace();
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                    break;

                default:
                    break;
            }

        } else
            finish();
        super.onActivityResult(requestCode, resultCode, data);
    }

    public void btnExit(View view) {
        finish();
    }

}