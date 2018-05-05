package com.wuxinle.touchimageview;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.widget.ImageView;
import android.widget.Toast;
import com.wuxinle.touchimageview.utils.ScreenUtil;
/**
 * Created by dingxujun on 2018/4/27.
 *
 * @project TouchImageView-master
 */

public class DragView extends ImageView {
    private Context context;
    private float moveX;
    private float moveY;
    private float DownX;
    private float DownY;
    private float UpX;
    private float UpY;
    private DragViewOnclickListener mListener;

    public DragView(Context context) {
        super(context);
    }

    public DragView(Context context, AttributeSet attrs) {
        super(context, attrs);
        this.context = context;
    }


    /**
     * 坐标说明 view.getX()坐标是点击的空间左上角相对父容器而言的
     * event.getX();触摸点相对于其所在组件原点的坐标
     * event.getRowX() 触摸点相对于屏幕原点的
     *
     * @date 创建时间 2018/4/27
     * @author dingxujun
     */

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_DOWN:
//获取相对屏幕的坐标(以屏幕左上角为原点,25是系统状态栏的高度)
                moveX = event.getX();
                moveY = event.getY();
                DownX = event.getRawX();
                DownY = event.getRawY() - 25;
                System.out.println("--------------DownX-------"+DownX+"pp"+DownY);

                setPressed(true);
                break;
            case MotionEvent.ACTION_MOVE:
                if (event.getRawX() > 50 && event.getRawX() < (ScreenUtil.getInstance(context).getScreenWidth() - 20)
                        && event.getRawY() > 50 && event.getRawY() < (ScreenUtil.getInstance(context).getScreenHeight() - 100)) {
                    setX(getX() + (event.getX() - moveX));
                    setY(getY() + (event.getY() - moveY));
                } else {
                    setX((ScreenUtil.getInstance(context).getScreenWidth()) - getMeasuredWidth() - 20);
                    setY(ScreenUtil.getInstance(context).getScreenHeight()/2);
                }
                getParent().requestDisallowInterceptTouchEvent(true);//请求父view别拦截我的事件
                break;
            case MotionEvent.ACTION_UP:
                setPressed(false);
                UpX = event.getRawX();
                UpY = event.getRawY() - 25;
                if (Math.abs(UpX - DownX) > 10 || Math.abs(UpY - DownY) > 10) {
                    //处理滑动事件
                    getParent().requestDisallowInterceptTouchEvent(false);//把事件还给父view
                    Toast.makeText(context, "滑动", 0).show();
                } else {
                    //处理点击事件
                    mListener.dragViewOnClick();
                }
                break;
            case MotionEvent.ACTION_CANCEL:
                break;
        }
        return true;
    }

    public interface DragViewOnclickListener {
        void dragViewOnClick();
    }

    public void setDragViewOnclickListener(DragViewOnclickListener listener) {
        this.mListener = listener;

    }
}
