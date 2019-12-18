package com.example.com.redcrad;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;

public class showActivity extends Activity {
    TextView text;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show);
        text=(TextView)findViewById(R.id.show);
        Intent intent=getIntent();
        Bundle bundle=intent.getExtras();
        String yuan=bundle.getString("out");
        Operation.setcontext(showActivity.this);
        matrix.setcontext(showActivity.this);
        matrix.setext(text);                                //调试用
        text.setText("");
        Operation.result=new real("0");
        Operation.setext(text);
        Operation.link.clear();
        Operation.num=0;
        initPython();
        try {
            Operation.check(yuan);
        } catch (Exception e) {
            //  text.setText("");
            text.append("提示：check出错请输入正确的式子");
        }
        //  Operation.calculation(yuan);

    }
    void initPython(){
        if (! Python.isStarted()) {
            Python.start(new AndroidPlatform(this));
        }
    }
}
