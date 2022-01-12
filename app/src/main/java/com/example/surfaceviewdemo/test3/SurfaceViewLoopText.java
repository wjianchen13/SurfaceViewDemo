package com.example.surfaceviewdemo.test3;

import android.content.Context;
import android.content.res.Resources;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.DisplayMetrics;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class SurfaceViewLoopText extends SurfaceView implements SurfaceHolder.Callback, Runnable {
    private SurfaceHolder mSurfaceHolder;
    //绘图的Canvas
    private Canvas mCanvas;
    //子线程标志位
    private boolean mIsDrawing;
    //画笔
    private Paint mPaint;
    //路径
    private Path mPath;
    
    private String str = "ABCDEFG";
    
    private int x = 0;
    private int y = 100;
    
    private float mWidth;
    
    private float mHeight;
    
    private int mScreenWidth;
    
    public SurfaceViewLoopText(Context context) {
        this(context, null);
    }

    public SurfaceViewLoopText(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SurfaceViewLoopText(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint = new Paint();
        mPaint.setColor(Color.BLACK);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(5);
        mPaint.setTextSize(50);
        mPaint.setAntiAlias(true);
        mPath = new Path();
        mPath.moveTo(0, 100);
        initView();
        mWidth = mPaint.measureText(str);
        mHeight = mPaint.descent() - mPaint.ascent();
        mScreenWidth = (int)(getScreenWidth(context));
        x = mScreenWidth;
        y = (int)mHeight;
    }

    public int getScreenWidth(Context context) {
        if (context == null)
            return 0;

        Resources resources = context.getResources();
        if (resources == null)
            return 0;

        DisplayMetrics metrics = resources.getDisplayMetrics();
        if (metrics == null)
            return 0;

        return metrics.widthPixels;
    }
    

    @Override
    public void surfaceCreated(SurfaceHolder holder) {
        mIsDrawing = true;
        new Thread(this).start();
    }

    @Override
    public void surfaceChanged(SurfaceHolder holder, int format, int width, int height) {

    }

    @Override
    public void surfaceDestroyed(SurfaceHolder holder) {
        mIsDrawing = false;
    }

    @Override
    public void run() {
        while (mIsDrawing) {
            long start = System.currentTimeMillis();
            x = x - 5;
            if(x < -mWidth)
                x = mScreenWidth;
            drawSomething();
            long end = System.currentTimeMillis();

            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            
        }
    }

    private void drawSomething() {
        try {
            mCanvas = mSurfaceHolder.lockCanvas();
//            mCanvas.drawRect(x,y - mHeight,x + mWidth,y, mPaint);
            mCanvas.drawColor(Color.WHITE);
            mCanvas.drawText(str, x, y, mPaint);
        } catch (Exception e){

        } finally {
            if (mCanvas != null){
                //释放canvas对象并提交画布
                mSurfaceHolder.unlockCanvasAndPost(mCanvas);
            }
        }
    }

    /**
     * 初始化View
     */
    private void initView(){
        mSurfaceHolder = getHolder();
        mSurfaceHolder.addCallback(this);
        setFocusable(true);
        setKeepScreenOn(true);
        setFocusableInTouchMode(true);
    }
    
}