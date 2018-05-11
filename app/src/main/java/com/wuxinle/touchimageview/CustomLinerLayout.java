package com.wuxinle.touchimageview;

import android.content.Context;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.LinearLayout;

/**
 * Created by dingxujun on 2018/5/11.
 *
 * @project TouchImageView-master
 */

public class CustomLinerLayout extends LinearLayout {
    public CustomLinerLayout(Context context) {
        super(context);
    }

    public CustomLinerLayout(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomLinerLayout(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }
    private float DownX;
    private float DownY;
    private float UpX;
    private float UpY;
    @Override
    public boolean onInterceptTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
                DownX = event.getRawX();
                DownY = event.getRawY() - 25;
                break;
            case MotionEvent.ACTION_MOVE:
            case MotionEvent.ACTION_UP:
                UpX = event.getRawX();
                UpY = event.getRawY() - 25;
                if (Math.abs(UpX - DownX) > 10 || Math.abs(UpY - DownY) > 10) {
                    //处理滑动事件
                    System.out.println("-------DownX----------");
                    return true;
                } else {
                    //处理点击事件
                }
              break;
            default:
                break;
        }
      return super.onInterceptTouchEvent(event);

    }
}
