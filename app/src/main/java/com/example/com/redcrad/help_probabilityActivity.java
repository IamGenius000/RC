package com.example.com.redcrad;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class help_probabilityActivity extends Activity implements View.OnClickListener{
    Button p_com,p_nop;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_probability);
        p_com= (Button) findViewById(R.id.probability_com);
        p_nop= (Button) findViewById(R.id.probability_nop);
        p_com.setOnClickListener(this);
        p_nop.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.probability_com)
        {
            Intent intent = new Intent();
            intent.putExtra("help", "com(5,3)");
            setResult(RESULT_OK, intent);
            finish();
        }
        if(v.getId()==R.id.probability_nop)
        {
            Intent intent = new Intent();
            intent.putExtra("help", "nop(5，3)");
            setResult(RESULT_OK, intent);
            finish();
        }
    }
}
