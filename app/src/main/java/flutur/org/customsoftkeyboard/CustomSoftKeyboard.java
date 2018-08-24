package flutur.org.customsoftkeyboard;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.view.View;

/**
 * Created by Mallika Priya Khullar on 25/08/18.
 */
public class CustomSoftKeyboard extends View implements View.OnClickListener{

    private static final int TYPE_ALPHA = 1;
    private static final int TYPE_ALPHANUMERIC = 2;
    private static final int TYPE_NUMERIC = 3;

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

    private void init(Context context, AttributeSet attrs) {

        float density = getResources().getDisplayMetrics().density;

        // Defaults, may need to link this into theme settings
        int textColor = ContextCompat.getColor(context, R.color.colorTextDefault);

        mProgressWidth = (int) (mProgressWidth * density);
        mArcWidth = (int) (mArcWidth * density);
        mTextSize = (int) (mTextSize * density);

        mIndicatorIcon = ContextCompat.getDrawable(context, R.drawable.indicator);

        if (attrs != null) {
            // Attribute initialization
            final TypedArray a = context.obtainStyledAttributes(attrs,
                    R.styleable.SwagPoints, 0, 0);

            Drawable indicatorIcon = a.getDrawable(R.styleable.SwagPoints_indicatorIcon);
            if (indicatorIcon != null)
                mIndicatorIcon = indicatorIcon;

            int indicatorIconHalfWidth = mIndicatorIcon.getIntrinsicWidth() / 2;
            int indicatorIconHalfHeight = mIndicatorIcon.getIntrinsicHeight() / 2;
            mIndicatorIcon.setBounds(-indicatorIconHalfWidth, -indicatorIconHalfHeight, indicatorIconHalfWidth,
                    indicatorIconHalfHeight);

            mPoints = a.getInteger(R.styleable.SwagPoints_points, mPoints);
            mMin = a.getInteger(R.styleable.SwagPoints_min, mMin);
            mMax = a.getInteger(R.styleable.SwagPoints_max, mMax);
            mStep = a.getInteger(R.styleable.SwagPoints_step, mStep);

            mProgressWidth = (int) a.getDimension(R.styleable.SwagPoints_progressWidth, mProgressWidth);
            progressColor = a.getColor(R.styleable.SwagPoints_progressColor, progressColor);

            mArcWidth = (int) a.getDimension(R.styleable.SwagPoints_arcWidth, mArcWidth);
            arcColor = a.getColor(R.styleable.SwagPoints_arcColor, arcColor);

            mTextSize = (int) a.getDimension(R.styleable.SwagPoints_textSize, mTextSize);
            mTextColor = a.getColor(R.styleable.SwagPoints_textColor, mTextColor);

            mClockwise = a.getBoolean(R.styleable.SwagPoints_clockwise,
                    mClockwise);
            mEnabled = a.getBoolean(R.styleable.SwagPoints_enabled, mEnabled);
            a.recycle();
        }
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
    }

    @Override
    public void onClick(View v) {

    }
}
