package flutur.org.customsoftkeyboard;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.text.TextPaint;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewDebug;

/**
 * Created by Mallika Priya Khullar on 25/08/18.
 */
public class CustomSoftKeyboard extends View implements View.OnClickListener{

    private static final int TYPE_ALPHA = 1;
    private static final int TYPE_ALPHANUMERIC = 2;
    private static final int TYPE_NUMERIC = 3;

    private Typeface mTypeface;
    private int mKeyTypes;
    private int mKeyTextSize;
    private int mKeyTextColor;
    private int mBackgroundColor;
    private TextPaint mTextPaint;

    /**
     * Listener for virtual keyboard events.
     */
    public interface OnKeyboardActionListener {

        /**
         * Called when the user presses a key. This is sent before the {@link #onKey} is called.
         * For keys that repeat, this is only called once.
         * @param primaryCode the unicode of the key being pressed. If the touch is not on a valid
         * key, the value will be zero.
         */
        void onPress(int primaryCode);

        /**
         * Called when the user releases a key. This is sent after the {@link #onKey} is called.
         * For keys that repeat, this is only called once.
         * @param primaryCode the code of the key that was released
         */
        void onRelease(int primaryCode);

        /**
         * Send a key press to the listener.
         * @param primaryCode this is the key that was pressed
         * @param keyCodes the codes for all the possible alternative keys
         * with the primary code being the first. If the primary key code is
         * a single character such as an alphabet or number or symbol, the alternatives
         * will include other characters that may be on the same key or adjacent keys.
         * These codes are useful to correct for accidental presses of a key adjacent to
         * the intended key.
         */
        void onKey(int primaryCode, int[] keyCodes);
    }



    public CustomSoftKeyboard(Context context) {
        super(context);
    }

    public CustomSoftKeyboard(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public CustomSoftKeyboard(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public CustomSoftKeyboard(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);

        float density = getResources().getDisplayMetrics().density;

        // Defaults, may need to link this into theme settings

        if (attrs != null) {
            // Attribute initialization
            final TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.CustomSoftKeyboard, 0, 0);

            mBackgroundColor = a.getColor(R.styleable.CustomSoftKeyboard_backgroundColor, 0xFF000000);
            mKeyTextColor = a.getColor(R.styleable.CustomSoftKeyboard_textColor, 0xFFffffff);
            mKeyTextSize = a.getDimensionPixelSize(R.styleable.CustomSoftKeyboard_textSize, 18);

            boolean isAlpha, isNumeric, isAlphanumeric;
            isAlpha = a.getBoolean(R.styleable.CustomSoftKeyboard_alphaVisible, true);
            isNumeric = a.getBoolean(R.styleable.CustomSoftKeyboard_numVisible, false);
            isAlphanumeric = isAlpha && isNumeric;

            mKeyTypes = isAlphanumeric?TYPE_ALPHANUMERIC: isAlpha?TYPE_ALPHA:TYPE_NUMERIC;
            a.recycle();
        }

        mTextPaint = new TextPaint();
        mTextPaint.setColor(mKeyTextColor);
        mTextPaint.setAntiAlias(true);
        mTextPaint.setStyle(Paint.Style.FILL);
        mTextPaint.setTextSize(mKeyTextSize);
    }


    @Override
    public void onClick(View v) {

    }
}
