package xzh.com.shadeview_master.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.LinearGradient;
import android.graphics.Matrix;
import android.graphics.Paint;
import android.graphics.Shader;
import android.util.AttributeSet;
import android.view.View;
import android.widget.TextView;

/**
 * Created by xiangzhihong on 2016/6/2 on 10:01.
 */
public class ShadeView extends TextView {

   private LinearGradient gradient;
   private Matrix matrix;
   private int mTranslate;

    public ShadeView(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        init();
    }

    //Shader.TileMode.CLAMP   重复最后一个颜色至最后 Shader.TileMode.MIRROR  重复着色的图像水平或垂直方向已镜像方式填充会有翻转效果 Shader.TileMode.REPEAT  重复着色的图像水平或垂直方向
    private void init() {
        Paint mPaint = getPaint();
        mPaint.setAntiAlias(true);
        mPaint.setDither(true);
        mPaint.setTextSize(40);
        gradient = new LinearGradient(0, 0, getMeasuredWidth(), 0, Color.GRAY, Color.RED, Shader.TileMode.REPEAT);
        mPaint.setShader(gradient);
        matrix = new Matrix();
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        if (matrix != null) {
            mTranslate += getMeasuredWidth() / 10;
            if (mTranslate > getMeasuredWidth() * 2) {
                mTranslate = -getMeasuredWidth();
            }
            matrix.setTranslate(mTranslate, 0);
            gradient.setLocalMatrix(matrix);
            postInvalidateDelayed(300);
        }
    }
}
