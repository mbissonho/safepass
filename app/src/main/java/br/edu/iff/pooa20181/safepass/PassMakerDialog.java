package br.edu.iff.pooa20181.safepass;

import android.app.Dialog;
import android.content.ClipboardManager;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.SeekBar;
import android.widget.TextView;

import java.util.Random;

public class PassMakerDialog extends Dialog {

    private TextView genPass, lNumCharsValue;
    private SeekBar slider;
    private Context context;
    private Button btnGen, btnCopy, btnNew;

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
        this.btnGen = findViewById(R.id.btnGen);
        this.btnCopy = findViewById(R.id.btnCopy);
        this.btnNew = findViewById(R.id.btnNew);

        this.slider.setMax(22);

        PassMakerDialog.this.gen();

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
                PassMakerDialog.this.gen();
            }
        });

        this.btnGen.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                PassMakerDialog.this.gen();
            }
        });

        this.btnCopy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ClipboardManager clip = (ClipboardManager) PassMakerDialog.this.context.getSystemService(Context.CLIPBOARD_SERVICE);
                clip.setText(PassMakerDialog.this.genPass.getText().toString());
                Helper.launchMessage(PassMakerDialog.this.context,"Copiado!");
            }
        });

        this.btnNew.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(PassMakerDialog.this.context, ManageContaActivity.class);
                intent.putExtra("id",0);
                intent.putExtra("genPass",PassMakerDialog.this.genPass.getText().toString());
                PassMakerDialog.this.context.startActivity(intent);
                PassMakerDialog.this.dismiss();
            }
        });

    }

    private void updateCurrentNumChars(int numSlider){
        this.currentNumChars = numSlider + 8;
    }

    private String passMaker(int num){

        String pass = "";

        Random r = new Random();

        int lastType = 0;
        int nextType;
        int choose;

        //0
        String[] low = {"a","b","c","d","e","f","g","h","i","j","k","l","m","n","o","p","q","r","s","u","v","w","x","y","z"};
        //1
        String[] up = {"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P","Q","R","S","U","V","W","X","Y","Z"};
        //2
        String[] nums = {"0","1","2","3","4","5","6","7","8","9"};
        //3
        String[] special = {"!","@","#","$","%","¨","&","*","(",")","-","+"};

        for(int i = 0; i < num; i++){

            do{
                nextType = r.nextInt(4);
            }while(lastType == nextType);

            lastType = nextType;

            if(nextType == 0){
                choose = r.nextInt(low.length);
                pass += low[choose];
            }else if(nextType == 1){
                choose = r.nextInt(up.length);
                pass += up[choose];
            }else if(nextType == 2){
                choose = r.nextInt(nums.length);
                pass += nums[choose];
            }else{
                choose = r.nextInt(special.length);
                pass += special[choose];
            }
        }

        return pass;
    }

    private void gen(){
        PassMakerDialog.this.genPass.setText(PassMakerDialog.this.passMaker(currentNumChars));
    }

}
