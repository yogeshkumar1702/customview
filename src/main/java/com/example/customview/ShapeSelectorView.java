package com.example.customview;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;

public class ShapeSelectorView extends View {

    private int shapeColor;
    private boolean displayShapeName;
    private int shapeWidth = 300;
    private int shapeHeight = 300;
    private int textXOffset = 0;
    private int textYOffset = 30;
    private Paint paintShape;
    private String[] shapeValues = { "Square" ,
            "Circle" ,
            "Triangle"};
    private int currentShapeIndex = 0;


    public ShapeSelectorView(Context context , AttributeSet attrs){
        super(context, attrs);
        initAttrs(attrs);
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);

        int textPadding = 10;
        int contentWidth = shapeWidth;

        int minW = contentWidth + getPaddingLeft() + getPaddingRight();
        int w = resolveSizeAndState(minW,widthMeasureSpec,0);

        int minH = shapeHeight + getPaddingBottom() + getPaddingTop();
        if(displayShapeName){
            minH += textYOffset+textPadding;
        }

        int h =resolveSizeAndState(minH,heightMeasureSpec,0);
        setMeasuredDimension(w,h);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        boolean result = super.onTouchEvent(event);
        if(event.getAction() == MotionEvent.ACTION_DOWN){
            currentShapeIndex = (++currentShapeIndex) % shapeValues.length;
            postInvalidate();
            return true;
        }
        return result;
    }

    public String getSelectedShape(){
        return shapeValues[currentShapeIndex];
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        paintShape = new Paint();
        paintShape.setStyle(Paint.Style.FILL);
        paintShape.setColor(shapeColor);
        paintShape.setTextSize(30);

        //String shapeSelected = shapeValues[currentShapeIndex];
        switch(currentShapeIndex){
            case 0 :
                canvas.drawRect(0,0,shapeWidth,shapeHeight,paintShape);
                textXOffset = 0;
                break;
            case 1:
                canvas.drawCircle(shapeWidth / 2,shapeHeight / 2,shapeWidth / 2,paintShape);
                textXOffset = 12;
                break;
            case 2:
                Path trianglePath = new Path();
                trianglePath.moveTo(0,shapeHeight);
                trianglePath.lineTo(shapeWidth,shapeHeight);
                trianglePath.lineTo(shapeWidth/2,0);
                canvas.drawPath(trianglePath,paintShape);
                textXOffset = 0;
                break;
            default:
                Log.e(getContext().toString(),"Can't Draw");
        }




    }

    private void initAttrs(AttributeSet attrs) {
        TypedArray a = getContext().getTheme().obtainStyledAttributes(attrs,
                R.styleable.ShapeSelectorView,0,0);

        try{
            shapeColor = a.getColor(R.styleable.ShapeSelectorView_shapeColor, Color.BLACK);
            displayShapeName = a.getBoolean(R.styleable.ShapeSelectorView_displayShapeName,false);

        }finally {
            a.recycle();
        }

    }
}