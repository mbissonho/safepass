package br.edu.iff.pooa20181.safepass;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.widget.SeekBar;
import android.widget.TextView;

public class PassMakerDialog extends Dialog {

    private TextView genPass, lNumCharsValue;
    private SeekBar slider;
    private Context context;

    private int currentNumChars = 8;

    public PassMakerDialog(@NonNull Context context) {
        super(context);
        this.context = context;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        this.setContentView(R.layout.passmaker_content);

        int width = (int)(this.context.getResources().getDisplayMetrics().widthPixels*0.90);
        int height = (int)(this.context.getResources().getDisplayMetrics().heightPixels*0.35);

        this.getWindow().setLayout(width, height);


        this.genPass = findViewById(R.id.genPass);
        this.lNumCharsValue = findViewById(R.id.lNumCharsValue);
        this.slider = findViewById(R.id.slider);

        this.slider.setMax(22);


        this.slider.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
            @Override
            public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
                PassMakerDialog.this.updateCurrentNumChars(PassMakerDialog.this.slider.getProgress());
                PassMakerDialog.this.lNumCharsValue.setText(Integer.toString(PassMakerDialog.this.currentNumChars));

            }

            @Override
            public void onStartTrackingTouch(SeekBar seekBar) {

            }

            @Override
            public void onStopTrackingTouch(SeekBar seekBar) {

            }
        });


    }

    private void updateCurrentNumChars(int numSlider){
        this.currentNumChars = numSlider + 8;
    }



}
