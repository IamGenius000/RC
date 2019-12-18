package com.example.com.redcrad;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class help_vectorActivity extends Activity implements View.OnClickListener {
    Button vector_model,vector_dot,vector_cos,vector_dc,vector_pro,vector_product,vector_da,vector_sd,vector_od,vector_mixed;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_vector);
        vector_model = (Button) findViewById(R.id.vector_model);
        vector_dot= (Button) findViewById(R.id.vector_dot);
        vector_cos= (Button) findViewById(R.id.vector_cos);
        vector_dc= (Button) findViewById(R.id.vector_dc);
        vector_pro= (Button) findViewById(R.id.vector_pro);
        vector_product= (Button) findViewById(R.id.vector_product);
        vector_da= (Button) findViewById(R.id.vector_da);
        vector_sd= (Button) findViewById(R.id.vector_sd);
        vector_od= (Button) findViewById(R.id.vector_od);
        vector_mixed= (Button) findViewById(R.id.vector_mixed);
        vector_model.setOnClickListener(this);
        vector_dot.setOnClickListener(this);
        vector_cos.setOnClickListener(this);
        vector_dc.setOnClickListener(this);
        vector_pro.setOnClickListener(this);
        vector_product.setOnClickListener(this);
        vector_da.setOnClickListener(this);
        vector_sd.setOnClickListener(this);
        vector_od.setOnClickListener(this);
        vector_mixed.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.vector_model)
        {
            Intent intent = new Intent();
            intent.putExtra("help", "model(5,3,2)");
            setResult(RESULT_OK, intent);
            finish();
        }
        if(v.getId()==R.id.vector_dot)
        {
            Intent intent = new Intent();
            intent.putExtra("help", "dot((1,2,3),(1,2,3))");
            setResult(RESULT_OK, intent);
            finish();
        }
        if(v.getId()==R.id.vector_cos)
        {
            Intent intent = new Intent();
            intent.putExtra("help", "cos((1,2,3),(1,2,3))");
            setResult(RESULT_OK, intent);
            finish();
        }
        if(v.getId()==R.id.vector_dc)
        {
            Intent intent = new Intent();
            intent.putExtra("help", "dc(1,2,3)");
            setResult(RESULT_OK, intent);
            finish();
        }
        if(v.getId()==R.id.vector_pro)
        {
            Intent intent = new Intent();
            intent.putExtra("help", "projection((1,2,3),(1,2,3))");
            setResult(RESULT_OK, intent);
            finish();
        }
        if(v.getId()==R.id.vector_product)
        {
            Intent intent = new Intent();
            intent.putExtra("help", "product((1,2,3),(1,2,3))");
            setResult(RESULT_OK, intent);
            finish();
        }
        if(v.getId()==R.id.vector_da)
        {
            Intent intent = new Intent();
            intent.putExtra("help", "da(1,2,3)");
            setResult(RESULT_OK, intent);
            finish();
        }
        if(v.getId()==R.id.vector_sd)
        {
            Intent intent = new Intent();
            intent.putExtra("help", "sd(1,2,3)");
            setResult(RESULT_OK, intent);
            finish();
        }
        if(v.getId()==R.id.vector_od)
        {
            Intent intent = new Intent();
            intent.putExtra("help", "od(1,2,3)");
            setResult(RESULT_OK, intent);
            finish();
        }
        if(v.getId()==R.id.vector_mixed)
        {
            Intent intent = new Intent();
            intent.putExtra("help", "mixed((1,2,3),(1,2,3),(1,2,3))");
            setResult(RESULT_OK, intent);
            finish();
        }
    }
}
