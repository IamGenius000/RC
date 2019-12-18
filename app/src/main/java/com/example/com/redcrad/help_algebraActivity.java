package com.example.com.redcrad;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class  help_algebraActivity extends Activity implements View.OnClickListener {
    Button simp,tsimp,summ,exp,exp_two,equ,noequ,noequ_three,equ_lin,tequ;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_algebra);
        simp=findViewById(R.id.algebra_simp);
        tsimp=findViewById(R.id.algebra_tsimp);
        summ=findViewById(R.id.algebra_summ);
        exp=findViewById(R.id.algebra_exp);
        exp_two=findViewById(R.id.algebra_exp_two);
        equ=findViewById(R.id.algebra_equ);
        noequ=findViewById(R.id.algebra_noequ);
        noequ_three=findViewById(R.id.algebra_noequ_three);
        equ_lin=findViewById(R.id.algebra_equ_lin);
        tequ=findViewById(R.id.algebra_tequ);
        simp.setOnClickListener(this);
        tsimp.setOnClickListener(this);
        summ.setOnClickListener(this);
        exp.setOnClickListener(this);
        exp_two.setOnClickListener(this);
        equ.setOnClickListener(this);
        noequ.setOnClickListener(this);
        noequ_three.setOnClickListener(this);
        equ_lin.setOnClickListener(this);
        tequ.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.algebra_simp)
        {
            Intent intent = new Intent();
            intent.putExtra("help", "simp(x^2+2*x+1)");
            setResult(RESULT_OK, intent);
            finish();
        }
        if (v.getId() == R.id.algebra_tsimp)
        {
            Intent intent = new Intent();
            intent.putExtra("help", "tsimp(2*sin(x)*cos(x))");
            setResult(RESULT_OK, intent);
            finish();
        }
        if (v.getId() == R.id.algebra_summ)
        {
            Intent intent = new Intent();
            intent.putExtra("help", "summ(2*x,x,1,5)");
            setResult(RESULT_OK, intent);
            finish();
        }
        if (v.getId() == R.id.algebra_exp)
        {
            Intent intent = new Intent();
            intent.putExtra("help", "eva(x+2,x,3)");
            setResult(RESULT_OK, intent);
            finish();
        }
        if (v.getId() == R.id.algebra_exp_two)
        {
            Intent intent = new Intent();
            intent.putExtra("help", "eva(x+y+2,x,3,y,2)");
            setResult(RESULT_OK, intent);
            finish();
        }
        if (v.getId() == R.id.algebra_equ)
        {
            Intent intent = new Intent();
            intent.putExtra("help", "equ(2*x^2+1,3,x)");
            setResult(RESULT_OK, intent);
            finish();
        }
        if (v.getId() == R.id.algebra_noequ)
        {
            Intent intent = new Intent();
            intent.putExtra("help", "equ(x+1-2,y+x-4,x,y)");
            setResult(RESULT_OK, intent);
            finish();
        }
        if (v.getId() == R.id.algebra_noequ_three)
        {
            Intent intent = new Intent();
            intent.putExtra("help", "equ(x+1-2,y+x-4,z+x-3,x,y,z)");
            setResult(RESULT_OK, intent);
            finish();
        }
        if (v.getId() == R.id.algebra_equ_lin)
        {
            Intent intent = new Intent();
            intent.putExtra("help", "equ(x+1-2,y+x+z-4,x,y,z)");
            setResult(RESULT_OK, intent);
            finish();
        }
        if (v.getId() == R.id.algebra_tequ)
        {
            Intent intent = new Intent();
            intent.putExtra("help", "tequ(sin(x)+sin(y)-1,x+y-pi/3,x,y)");
            setResult(RESULT_OK, intent);
            finish();
        }
    }
}
