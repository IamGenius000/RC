package com.example.com.redcrad;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class help_matrixActivity extends Activity implements View.OnClickListener {
    Button matrix_demo,matrix_plus_minus,matrix_mult,matrix_num,matrix_dott,
            matrix_norm,matrix_dim,matrix_t,matrix_rank,matrix_trace,
            matrix_inverse,matrix_det,matrix_qr,matrix_lu,matrix_svd,matrix_chol,matrix_adj,matrix_eig,matrix_alg,matrix_ref;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_help_matrix);
        matrix_demo= (Button) findViewById(R.id.matrix_demo);
        matrix_plus_minus= (Button) findViewById(R.id.matrix_plus_minus);
        matrix_mult= (Button) findViewById(R.id.matrix_mult);
        matrix_num= (Button) findViewById(R.id.matrix_num);
        matrix_dott= (Button) findViewById(R.id.matrix_dott);
        matrix_norm= (Button) findViewById(R.id.matrix_norm);
        matrix_dim= (Button) findViewById(R.id.matrix_dim);
        matrix_t= (Button) findViewById(R.id.matrix_t);
        matrix_rank= (Button) findViewById(R.id.matrix_rank);
        matrix_trace= (Button) findViewById(R.id.matrix_trace);
        matrix_inverse= (Button) findViewById(R.id.matrix_inverse);
        matrix_det= (Button) findViewById(R.id.matrix_det);
        matrix_qr= (Button) findViewById(R.id.matrix_qr);
        matrix_lu= (Button) findViewById(R.id.matrix_lu);
        matrix_svd= (Button) findViewById(R.id.matrix_svd);
        matrix_chol= (Button) findViewById(R.id.matrix_chol);
        matrix_adj= (Button) findViewById(R.id.matrix_adj);
        matrix_eig= (Button) findViewById(R.id.matrix_eig);
        matrix_alg= (Button) findViewById(R.id.matrix_alg);
        matrix_ref= (Button) findViewById(R.id.matrix_ref);
        matrix_demo.setOnClickListener(this);
        matrix_plus_minus.setOnClickListener(this);
        matrix_mult.setOnClickListener(this);
        matrix_num.setOnClickListener(this);
        matrix_dott.setOnClickListener(this);
        matrix_norm.setOnClickListener(this);
        matrix_dim.setOnClickListener(this);
        matrix_t.setOnClickListener(this);
        matrix_rank.setOnClickListener(this);
        matrix_trace.setOnClickListener(this);
        matrix_inverse.setOnClickListener(this);
        matrix_det.setOnClickListener(this);
        matrix_qr.setOnClickListener(this);
        matrix_lu.setOnClickListener(this);
        matrix_svd.setOnClickListener(this);
        matrix_chol.setOnClickListener(this);
        matrix_adj.setOnClickListener(this);
        matrix_eig.setOnClickListener(this);
        matrix_alg.setOnClickListener(this);
        matrix_ref.setOnClickListener(this);
    }
    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.matrix_demo)
        {
            Intent intent = new Intent();
            intent.putExtra("help", "((1,2,3),(1,2,3),(1,2,3))");
            setResult(RESULT_OK, intent);
            finish();
        }
        if(v.getId()==R.id.matrix_plus_minus)
        {
            Intent intent = new Intent();
            intent.putExtra("help", "((1,2,3),(1,2,3),(1,2,3))+((1,2,3),(1,2,3),(1,2,3))");
            setResult(RESULT_OK, intent);
            finish();
        }
        if(v.getId()==R.id.matrix_mult)
        {
            Intent intent = new Intent();
            intent.putExtra("help", "((1,2,3),(1,2,3),(1,2,3))*((1,2,3),(1,2,3),(1,2,3))");
            setResult(RESULT_OK, intent);
            finish();
        }
        if(v.getId()==R.id.matrix_num)
        {
            Intent intent = new Intent();
            intent.putExtra("help", "3((1,2,3),(1,2,3),(1,2,3))");
            setResult(RESULT_OK, intent);
            finish();
        }
        if(v.getId()==R.id.matrix_dott)
        {
            Intent intent = new Intent();
            intent.putExtra("help", "((1,2,3),(1,2,3),(1,2,3))·((1,2,3),(1,2,3),(1,2,3))");
            setResult(RESULT_OK, intent);
            finish();
        }
        if(v.getId()==R.id.matrix_norm)
        {
            Intent intent = new Intent();
            intent.putExtra("help", "norm((1,2,3),(1,2,3),(1,2,3))");
            setResult(RESULT_OK, intent);
            finish();
        }
        if(v.getId()==R.id.matrix_dim)
        {
            Intent intent = new Intent();
            intent.putExtra("help", "dim((1,2,3),(1,2,3),(1,2,3))");
            setResult(RESULT_OK, intent);
            finish();
        }
        if(v.getId()==R.id.matrix_t)
        {
            Intent intent = new Intent();
            intent.putExtra("help", "t((1,2,3),(1,2,3),(1,2,3)))");
            setResult(RESULT_OK, intent);
            finish();
        }
        if(v.getId()==R.id.matrix_rank)
        {
            Intent intent = new Intent();
            intent.putExtra("help", "rank((1,2,3),(1,2,3),(1,2,3))");
            setResult(RESULT_OK, intent);
            finish();
        }
        if (v.getId()==R.id.matrix_trace)
        {
            Intent intent = new Intent();
            intent.putExtra("help", "trace((1,2,3),(1,2,3),(1,2,3))");
            setResult(RESULT_OK, intent);
            finish();
        }
        if (v.getId()==R.id.matrix_inverse)
        {
            Intent intent = new Intent();
            intent.putExtra("help", "inverse((1,2,3),(1,2,3),(1,2,3))");
            setResult(RESULT_OK, intent);
            finish();
        }
        if(v.getId()==R.id.matrix_det)
        {
            Intent intent = new Intent();
            intent.putExtra("help", "det((1,2,3),(1,2,3),(1,2,3))");
            setResult(RESULT_OK, intent);
            finish();
        }
        if(v.getId()==R.id.matrix_qr)
        {
            Intent intent = new Intent();
            intent.putExtra("help", "qr((1,2,3),(0,4,5),(0,0,6))");
            setResult(RESULT_OK, intent);
            finish();
        }
        if(v.getId()==R.id.matrix_lu)
        {
            Intent intent = new Intent();
            intent.putExtra("help", "lu((1,2,6),(2,5,15),(6,15,46))");
            setResult(RESULT_OK, intent);
            finish();
        }
        if(v.getId()==R.id.matrix_svd)
        {
            Intent intent = new Intent();
            intent.putExtra("help", "svd((0,-1.6,0.6),(0,1.2,0.8),(0,0,0),(0,0,0))");
            setResult(RESULT_OK, intent);
            finish();
        }
        if(v.getId()==R.id.matrix_chol)
        {
            Intent intent = new Intent();
            intent.putExtra("help", "chol((3,1,0),(1,3,0),(0,0,3))");
            setResult(RESULT_OK, intent);
            finish();
        }
        if(v.getId()==R.id.matrix_adj)
        {
            Intent intent = new Intent();
            intent.putExtra("help", "adj((1,2,3),(1,2,3),(1,2,3))");
            setResult(RESULT_OK, intent);
            finish();
        }
        if(v.getId()==R.id.matrix_eig)
        {
            Intent intent = new Intent();
            intent.putExtra("help", "eig((0,1,1),(1,0,1),(1,1,0))");
            setResult(RESULT_OK, intent);
            finish();
        }
        if(v.getId()==R.id.matrix_alg)
        {
            Intent intent = new Intent();
            intent.putExtra("help", "alg((0,1,1),(1,0,1),(1,1,1))");
            setResult(RESULT_OK, intent);
            finish();
        }
        if(v.getId()==R.id.matrix_ref)
        {
            Intent intent = new Intent();
            intent.putExtra("help", "ref((1,2,3),(1,2,3),(1,2,3))");
            setResult(RESULT_OK, intent);
            finish();
        }
    }
}
