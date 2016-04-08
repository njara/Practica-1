package njdevelop.cl.especialistavendedor;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.EditText;

/**
 * Created by nicol on 27-01-2016.
 */
public class EditTextCustom extends EditText {
    Typeface miso_light;

    public EditTextCustom(Context context) {
        super(context);
        miso_light = Typeface.createFromAsset(context.getAssets(), Utils.FONT_MISO_LIGHT);
        setTypeface(miso_light);
    }

    public EditTextCustom(Context context, AttributeSet attrs) {
        super(context, attrs);
        miso_light = Typeface.createFromAsset(context.getAssets(), Utils.FONT_MISO_LIGHT);
        setTypeface(miso_light);
    }

    public EditTextCustom(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        miso_light = Typeface.createFromAsset(context.getAssets(), Utils.FONT_MISO_LIGHT);
        setTypeface(miso_light);
    }
}
