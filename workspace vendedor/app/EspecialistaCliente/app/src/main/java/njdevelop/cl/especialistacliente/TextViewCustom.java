package njdevelop.cl.especialistacliente;

import android.content.Context;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.TextView;

/**
 * Created by nicolasjara on 05-02-2016.
 */
public class TextViewCustom extends TextView {

    Typeface miso_light;

    public TextViewCustom(Context context) {
        super(context);
        miso_light = Typeface.createFromAsset(context.getAssets(), Utils.FONT_MISO_LIGHT);
        setTypeface(miso_light);
    }

    public TextViewCustom(Context context, AttributeSet attrs) {
        super(context, attrs);
        miso_light = Typeface.createFromAsset(context.getAssets(), Utils.FONT_MISO_LIGHT);
        setTypeface(miso_light);
    }

    public TextViewCustom(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        miso_light = Typeface.createFromAsset(context.getAssets(), Utils.FONT_MISO_LIGHT);
        setTypeface(miso_light);
    }


    @Override
    public void setTypeface(Typeface tf) {
        super.setTypeface(tf);
    }

    @Override
    public void setTextColor(int color) {
        super.setTextColor(color);
    }
}
