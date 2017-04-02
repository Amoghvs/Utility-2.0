package com.example.abhi.utility.calactivities;

import android.content.ClipData;
import android.content.ClipboardManager;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.abhi.utility.R;
import com.example.abhi.utility.caljav.Calculator;
import com.example.abhi.utility.caljav.CalculatorImpl;
import com.example.abhi.utility.caljav.Config;
import com.example.abhi.utility.caljav.Constants;
import com.example.abhi.utility.caljav.Formatter;
import com.example.abhi.utility.caljav.Utils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.OnLongClick;
import me.grantland.widget.AutofitHelper;

public class CalculatorMainAct extends SimpleActivity implements Calculator {
    @BindView(R.id.result)
    TextView mResult;
    @BindView(R.id.formula) TextView mFormula;

    private static CalculatorImpl mCalc;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.calculatormainact);
        overridePendingTransition(R.animator.left_in, R.animator.left_out);

        ButterKnife.bind(this);

        mCalc = new CalculatorImpl(this);
        AutofitHelper.create(mResult);
        AutofitHelper.create(mFormula);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        Config.newInstance(getApplicationContext()).setIsFirstRun(false);
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
        overridePendingTransition(R.animator.right_in, R.animator.right_out);
    }



    @OnClick(R.id.btn_plus)
    public void plusClicked() {
        mCalc.handleOperation(Constants.PLUS);
    }

    @OnClick(R.id.btn_minus)
    public void minusClicked() {
        mCalc.handleOperation(Constants.MINUS);
    }

    @OnClick(R.id.btn_multiply)
    public void multiplyClicked() {
        mCalc.handleOperation(Constants.MULTIPLY);
    }

    @OnClick(R.id.btn_divide)
    public void divideClicked() {
        mCalc.handleOperation(Constants.DIVIDE);
    }

    @OnClick(R.id.btn_modulo)
    public void moduloClicked() {
        mCalc.handleOperation(Constants.MODULO);
    }

    @OnClick(R.id.btn_power)
    public void powerClicked() {
        mCalc.handleOperation(Constants.POWER);
    }

    @OnClick(R.id.btn_root)
    public void rootClicked() {
        mCalc.handleOperation(Constants.ROOT);
    }

    @OnClick(R.id.btn_clear)
    public void clearClicked() {
        mCalc.handleClear();
    }

    @OnLongClick(R.id.btn_clear)
    public boolean clearLongClicked() {
        mCalc.handleReset();
        return true;
    }

    @OnClick(R.id.btn_equals)
    public void equalsClicked() {
        mCalc.handleEquals();
    }

    @OnClick({R.id.btn_decimal, R.id.btn_0, R.id.btn_1, R.id.btn_2, R.id.btn_3, R.id.btn_4, R.id.btn_5, R.id.btn_6, R.id.btn_7, R.id.btn_8,
            R.id.btn_9})
    public void numpadClick(View view) {
        numpadClicked(view.getId());
    }

    public void numpadClicked(int id) {
        mCalc.numpadClicked(id);
    }

    @OnLongClick(R.id.formula)
    public boolean formulaLongPressed() {
        return copyToClipboard(false);
    }

    @OnLongClick(R.id.result)
    public boolean resultLongPressed() {
        return copyToClipboard(true);
    }

    private boolean copyToClipboard(boolean copyResult) {
        String value = mFormula.getText().toString().trim();
        if (copyResult) {
            value = mResult.getText().toString().trim();
        }

        if (value.isEmpty())
            return false;

        final ClipboardManager clipboard = (ClipboardManager) getSystemService(CLIPBOARD_SERVICE);
        final ClipData clip = ClipData.newPlainText(getResources().getString(R.string.app_name), value);
        clipboard.setPrimaryClip(clip);
        Utils.showToast(getApplicationContext(), R.string.copied_to_clipboard);
        return true;
    }

    @Override
    public void setValue(String value) {
        mResult.setText(value);
    }

    // used only by Robolectric
    @Override
    public void setValueDouble(double d) {
        mCalc.setValue(Formatter.doubleToString(d));
        mCalc.setLastKey(Constants.DIGIT);
    }

    public void setFormula(String value) {
        mFormula.setText(value);
    }

    public CalculatorImpl getCalc() {
        return mCalc;
    }
}