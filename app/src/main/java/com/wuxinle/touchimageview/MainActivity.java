package com.wuxinle.touchimageview;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.Toast;

import com.wuxinle.touchimageview.utils.ScreenUtil;

public class MainActivity extends Activity implements DragView.DragViewOnclickListener {

    private DragView drag_imgage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        drag_imgage = (DragView) findViewById(R.id.drag_imgage);
        drag_imgage.setDragViewOnclickListener(this);
//        measure2();
        measure4();
    }



    private void measure2() {//比如一张图片，测量的是实际图片的高度,不管你有没有在布局文件中固定宽高
        //手动调用测量方法。
//制定测量规则 参数表示size + mode
        int w = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        int h = View.MeasureSpec.makeMeasureSpec(0, View.MeasureSpec.UNSPECIFIED);
        drag_imgage.measure(w, h);
//调用measure方法之后就可以获取宽高。
        int height = drag_imgage.getMeasuredHeight();
        int width = drag_imgage.getMeasuredWidth();

        int with1 = (ScreenUtil.getInstance(this).getScreenWidth()) - width - 10;
        int height1 = (ScreenUtil.getInstance(this).getScreenHeight()) / 2;

        drag_imgage.setX(with1);
        drag_imgage.setY(height1);
    }
    private void measure4() {//测量的是 你设置后的控件的宽高
        ViewTreeObserver vto = drag_imgage.getViewTreeObserver();
        vto.addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
            @Override
            public void onGlobalLayout() {
                drag_imgage.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                Log.i("imageview4", "width: " + drag_imgage.getWidth());
                Log.i("imageview4", "height: " + drag_imgage.getHeight());
                int width1 = (ScreenUtil.getInstance(MainActivity.this).getScreenWidth()) - drag_imgage.getWidth() - 20;
                int height1 = (ScreenUtil.getInstance(MainActivity.this).getScreenHeight()) / 2;

                drag_imgage.setX(width1);
                drag_imgage.setY(height1);
            }
        });

    }

    @Override
    public void dragViewOnClick() {
        Toast.makeText(this, "点击我了", 0).show();
    }


    /**
     * 随手滑动的demo
     */
    public  void clickDragview(View view){
        Intent intent =new Intent(this,DragViewActivity.class);
        startActivity(intent);
    }
}

