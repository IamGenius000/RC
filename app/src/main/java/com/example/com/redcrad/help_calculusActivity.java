package com.example.com.redcrad;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class help_calculusActivity extends Activity implements View.OnClickListener {
    Button  limit,deri,deri_two,deri_three,inte,inte_two,inte_three,nointe,nointe_two,nointe_three;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_calculus);
        limit=findViewById(R.id.calculus_limit);
        deri=findViewById(R.id.calculus_deri);
        deri_two=findViewById(R.id.calculus_deri_two);
        deri_three=findViewById(R.id.calculus_deri_three);
        inte=findViewById(R.id.calculus_inte);
        inte_two=findViewById(R.id.calculus_inte_two);
        inte_three=findViewById(R.id.calculus_inte_three);
        nointe=findViewById(R.id.calculus_nointe);
        nointe_two=findViewById(R.id.calculus_nointe_two);
        nointe_three=findViewById(R.id.calculus_nointe_three);
        limit.setOnClickListener(this);
        deri.setOnClickListener(this);
        deri_two.setOnClickListener(this);
        deri_three.setOnClickListener(this);
        inte.setOnClickListener(this);
        inte_two.setOnClickListener(this);
        inte_three.setOnClickListener(this);
        nointe.setOnClickListener(this);
        nointe_two.setOnClickListener(this);
        nointe_three.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.calculus_limit)
        {
            Intent intent = new Intent();
            intent.putExtra("help", "limit((sin(x)-x)/x^3,x,0)");
            setResult(RESULT_OK, intent);
            finish();
        }
        if (v.getId() == R.id.calculus_deri)
        {
            Intent intent = new Intent();
            intent.putExtra("help", "diff(x^2,x,1)");
            setResult(RESULT_OK, intent);
            finish();
        }
        if (v.getId() == R.id.calculus_deri_two)
        {
            Intent intent = new Intent();
            intent.putExtra("help", "diff(x^2*y^2,x,1,y,1)");
            setResult(RESULT_OK, intent);
            finish();
        }
        if (v.getId() == R.id.calculus_deri_three)
        {
            Intent intent = new Intent();
            intent.putExtra("help", "diff(x^2*y^2*z^2,x,1,y,1,z,1)");
            setResult(RESULT_OK, intent);
            finish();
        }
        if (v.getId() == R.id.calculus_inte)
        {
            Intent intent = new Intent();
            intent.putExtra("help", "inte(x^2,x,0,2)");
            setResult(RESULT_OK, intent);
            finish();
        }
        if (v.getId() == R.id.calculus_inte_two)
        {
            Intent intent = new Intent();
            intent.putExtra("help", "inte(x^2*y^2,x,0,2,y,0,3)");
            setResult(RESULT_OK, intent);
            finish();
        }
        if (v.getId() == R.id.calculus_inte_three)
        {
            Intent intent = new Intent();
            intent.putExtra("help", "inte(x*cos(y)*sin(z),x,1,2,y,3,4,z,5,6)");
            setResult(RESULT_OK, intent);
            finish();
        }
        if (v.getId() == R.id.calculus_nointe)
        {
            Intent intent = new Intent();
            intent.putExtra("help", "nointe(x^2,x)");
            setResult(RESULT_OK, intent);
            finish();
        }
        if (v.getId() == R.id.calculus_nointe_two)
        {
            Intent intent = new Intent();
            intent.putExtra("help", "nointe(x^2*y^2,x,y)");
            setResult(RESULT_OK, intent);
            finish();
        }
        if (v.getId() == R.id.calculus_nointe_three)
        {
            Intent intent = new Intent();
            intent.putExtra("help", "nointe(x*cos(y)*sin(z),x,y,z)");
            setResult(RESULT_OK, intent);
            finish();
        }
    }
}
