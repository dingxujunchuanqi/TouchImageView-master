package com.wuxinle.touchimageview;
import android.app.Activity;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;
import com.wuxinle.touchimageview.utils.ScreenUtil;

/**
 * Created by dingxujun on 2018/5/11.
 *
 * @project TouchImageView-master
 */

public class DragViewActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dragview_activity);
        initDragButton();//一个图片的
        initDragButton1();//两个图片的并且都具有点击事件
    }

    private void initDragButton() {
        DragFloatActionButton.Builder builder = new DragFloatActionButton.Builder()
                .setActivity(this)
                .setDefaultLeft(ScreenUtil.getInstance(this).getScreenWidth() - 120)
                .setDefaultTop(800)
                .setNeedNearEdge(true)
                .setSize(120);
        ImageView imageView = new ImageView(this);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(R.mipmap.ic_launcher);

        imageView.setOnClickListener(v -> {
           /* speekDialog.show();
            tv_result.setText("");
            voiceView.setText("0:00");*/
            Toast.makeText(this, "我是二维码", 0).show();

        });
        DragFloatActionButton.addDragView(builder, imageView);
    }

    private void initDragButton1() {
        DragFloatActionButton.Builder builder = new DragFloatActionButton.Builder()
                .setActivity(this)
                .setDefaultLeft(ScreenUtil.getInstance(this).getScreenWidth() - 120)
                .setDefaultTop(900)
                .setNeedNearEdge(true);
        ImageView imageView = new ImageView(this);
        imageView.setScaleType(ImageView.ScaleType.CENTER_CROP);
        imageView.setImageResource(R.mipmap.ic_launcher);
        View view = View.inflate(this, R.layout.dragimageview, null);
        view.findViewById(R.id.onekey_complete_af).setOnClickListener(v ->
                Toast.makeText(DragViewActivity.this, "一键后", 0).show());
        ImageView onekey_complete_bf = (ImageView) view.findViewById(R.id.onekey_complete_bf);
        onekey_complete_bf.setOnClickListener(v ->
                Toast.makeText(DragViewActivity.this, "一键前", 0).show());
        DragFloatActionButton.addDragView(builder, view);
    }
}
