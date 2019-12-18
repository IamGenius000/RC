package com.example.com.redcrad;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.chaquo.python.Python;
import com.chaquo.python.android.AndroidPlatform;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity
        implements NavigationView.OnNavigationItemSelectedListener,View.OnClickListener {
    EditText text;
    TextView out;
    Button zero,one,two,three,four,five,six,seven,eight,nine;
    Button jia,jian,cheng,chu,chengfang,douhao,dian,tuiwei,qingchu,dengyu,wuqiong,bracesL,bracesR,mbL,mbR,pL,pR,gL,gR;
    Button dianji,genghao,pi;
    Button q,w,e,r,t,y,u,i,o,p,a,s,d,f,g,h,j,k,l,z,x,c,v,b,n,m;
    Button jisuan;
    TextView tt;
    String[] check;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar =findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this, drawer, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
        drawer.setDrawerListener(toggle);
        toggle.syncState();

        NavigationView navigationView = (NavigationView) findViewById(R.id.nav_view);
        navigationView.setNavigationItemSelectedListener(this);

        check= new String[]{"diff", "inte","nointe","limit","eva","equ","tequ","simp","tsimp","summ","com","nop","model","dot","cos","dc","projection","product","da","sd","od","mixed","det","norm"};
        text = (EditText) findViewById(R.id.t1);
        out= (TextView) findViewById(R.id.out);
        pi=(Button)findViewById(R.id.pi);
        q=(Button)findViewById(R.id.q);                                    //字母
        w=(Button)findViewById(R.id.w);
        e=(Button)findViewById(R.id.e);
        r=(Button)findViewById(R.id.r);
        t=(Button)findViewById(R.id.t);
        y=(Button)findViewById(R.id.y);
        u=(Button)findViewById(R.id.u);
        i=(Button)findViewById(R.id.i);
        o=(Button)findViewById(R.id.o);
        p=(Button)findViewById(R.id.p);
        a=(Button)findViewById(R.id.a);
        s=(Button)findViewById(R.id.s);
        d=(Button)findViewById(R.id.d);
        f=(Button)findViewById(R.id.f);
        g=(Button)findViewById(R.id.g);
        h=(Button)findViewById(R.id.h);
        j=(Button)findViewById(R.id.j);
        k=(Button)findViewById(R.id.k);
        l=(Button)findViewById(R.id.l);
        z=(Button)findViewById(R.id.z);
        x=(Button)findViewById(R.id.x);
        c=(Button)findViewById(R.id.c);
        v=(Button)findViewById(R.id.v);
        b=(Button)findViewById(R.id.b);
        n=(Button)findViewById(R.id.n);
        m=(Button)findViewById(R.id.m);
        zero = (Button) findViewById(R.id.zero);                                 // 数字
        one = (Button) findViewById(R.id.one);
        two = (Button) findViewById(R.id.two);
        three = (Button) findViewById(R.id.three);
        four = (Button) findViewById(R.id.four);
        five = (Button) findViewById(R.id.five);
        six = (Button) findViewById(R.id.six);
        seven = (Button) findViewById(R.id.seven);
        eight = (Button) findViewById(R.id.eight);
        nine = (Button) findViewById(R.id.nine);
        jia = (Button) findViewById(R.id.jia);                                      //运算符
        jian = (Button) findViewById(R.id.jian);
        cheng = (Button) findViewById(R.id.cheng);
        chu = (Button) findViewById(R.id.chu);
        chengfang = (Button) findViewById(R.id.chengfang);                           //字符
        douhao = (Button) findViewById(R.id.douhao);
        dian = (Button) findViewById(R.id.dian);
        dianji=(Button)findViewById(R.id.dianji);
        genghao=(Button)findViewById(R.id.genghao);
        dengyu = (Button) findViewById(R.id.dengyu);
        wuqiong= (Button) findViewById(R.id.wuqiong);
        tuiwei = (Button) findViewById(R.id.tuiwei);                                //退格
        qingchu = (Button) findViewById(R.id.qingchu);
        bracesL = (Button) findViewById(R.id.bracesL);                               //括号组
        bracesR = (Button) findViewById(R.id.bracesR);
        mbL = (Button) findViewById(R.id.mbL);
        mbR = (Button) findViewById(R.id.mbR);
        pL = (Button) findViewById(R.id.pL);
        pR = (Button) findViewById(R.id.pR);
        gL=(Button)findViewById(R.id.gL);
        gR=(Button)findViewById(R.id.gR);
        jisuan = (Button) findViewById(R.id.jisuan);
        tt=(TextView)findViewById(R.id.show);
        q.setOnClickListener(this);
        w.setOnClickListener(this);
        e.setOnClickListener(this);
        r.setOnClickListener(this);
        t.setOnClickListener(this);
        y.setOnClickListener(this);
        u.setOnClickListener(this);
        i.setOnClickListener(this);
        o.setOnClickListener(this);
        p.setOnClickListener(this);
        a.setOnClickListener(this);
        s.setOnClickListener(this);
        d.setOnClickListener(this);
        f.setOnClickListener(this);
        g.setOnClickListener(this);
        h.setOnClickListener(this);
        j.setOnClickListener(this);
        k.setOnClickListener(this);
        l.setOnClickListener(this);
        z.setOnClickListener(this);
        x.setOnClickListener(this);
        c.setOnClickListener(this);
        v.setOnClickListener(this);
        b.setOnClickListener(this);
        n.setOnClickListener(this);
        m.setOnClickListener(this);
        zero.setOnClickListener(this);
        one.setOnClickListener(this);
        two.setOnClickListener(this);
        three.setOnClickListener(this);
        four.setOnClickListener(this);
        five.setOnClickListener(this);
        six.setOnClickListener(this);
        seven.setOnClickListener(this);
        eight.setOnClickListener(this);
        nine.setOnClickListener(this);
        jia.setOnClickListener(this);
        jian.setOnClickListener(this);
        cheng.setOnClickListener(this);
        chu.setOnClickListener(this);
        chengfang.setOnClickListener(this);
        douhao.setOnClickListener(this);
        dian.setOnClickListener(this);
        dianji.setOnClickListener(this);
        genghao.setOnClickListener(this);
        tuiwei.setOnClickListener(this);
        qingchu.setOnClickListener(this);
        dengyu.setOnClickListener(this);
        wuqiong.setOnClickListener(this);
        bracesL.setOnClickListener(this);
        bracesR.setOnClickListener(this);
        mbL.setOnClickListener(this);
        mbR.setOnClickListener(this);
        pL.setOnClickListener(this);
        pR.setOnClickListener(this);
        gL.setOnClickListener(this);
        gR.setOnClickListener(this);
        jisuan.setOnClickListener(this);
        pi.setOnClickListener(this);
        initPython();
    }

    void initPython(){
        if (! Python.isStarted()) {
             Python.start(new AndroidPlatform(this));
        }
    }
    @Override
    public void onBackPressed() {
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        if (drawer.isDrawerOpen(GravityCompat.START)) {
            drawer.closeDrawer(GravityCompat.START);
        } else {
            super.onBackPressed();
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {                  //帮助
        int id = item.getItemId();
        if (id == R.id.action_help) {
            return true;
        }
        return super.onOptionsItemSelected(item);
    }

    @SuppressWarnings("StatementWithEmptyBody")
    @Override
    public boolean onNavigationItemSelected(MenuItem item) {
        int id = item.getItemId();
        if (id == R.id.nav_function)
        {
            Toast.makeText(this, "(*/ω＼*) 别看我，我还没准备好", Toast.LENGTH_LONG).show();
        } else if (id == R.id.nav_linear)
        {
            Intent intent=new Intent(MainActivity.this,help_matrixActivity.class);
            startActivityForResult(intent, 1);
        }
        else if(id==R.id.nav_vector)
        {
            Intent intent=new Intent(MainActivity.this,help_vectorActivity.class);
            startActivityForResult(intent, 3);
        }
        else if (id == R.id.nav_probability)
        {
            Intent intent=new Intent(MainActivity.this,help_probabilityActivity.class);
            startActivityForResult(intent, 2);
        }
        else if(id==R.id.nav_Calculus)
        {
            Intent intent=new Intent(MainActivity.this,help_calculusActivity.class);
            startActivityForResult(intent, 4);
        }
        else if(id==R.id.nav_Algebra)
        {
            Intent intent=new Intent(MainActivity.this,help_algebraActivity.class);
            startActivityForResult(intent, 5);
        }
        else if (id == R.id.nav_geometry)
        {
            Toast.makeText(this, "(*/ω＼*) 别看我，我还没准备好", Toast.LENGTH_LONG).show();
        }
        else if (id == R.id.nav_census)
        {
            Toast.makeText(this, "(*/ω＼*) 别看我，我还没准备好", Toast.LENGTH_LONG).show();
        }
        else if(id==R.id.nav_share)
        {
            Toast.makeText(this, "ο(=•ω＜=)ρ⌒☆ 亲爱的内测用户，我还没有准备好，先不要分享哦", Toast.LENGTH_LONG).show();
        }
        else if(id==R.id.nav_about)
        {
            Toast.makeText(this, "Σ(っ °Д °;)っ 有bug找2728849348，错都是他写的", Toast.LENGTH_LONG).show();
        }
        DrawerLayout drawer = (DrawerLayout) findViewById(R.id.drawer_layout);
        drawer.closeDrawer(GravityCompat.START);
        return true;
    }
    @Override
    public void onClick(View v) {
        if(v.getId()==R.id.q)                                                      //字母
        {
            String st;
            String ll;
            String lll;
            int i=text.getSelectionStart();
            st=text.getText().toString();
            int len=st.length();
            ll=st.substring(0,i);
            ll+="q";
            lll=st.substring(i,len);
            ll+=lll;
            text.setText(ll);
            text.setSelection(i+1);
        }
        if(v.getId()==R.id.w)
        {
            String st;
            String ll;
            String lll;
            int i=text.getSelectionStart();
            st=text.getText().toString();
            int len=st.length();
            ll=st.substring(0,i);
            ll+="w";
            lll=st.substring(i,len);
            ll+=lll;
            text.setText(ll);
            text.setSelection(i + 1);
        }
        if(v.getId()==R.id.e)
        {
            String st;
            String ll;
            String lll;
            int i=text.getSelectionStart();
            st=text.getText().toString();
            int len=st.length();
            ll=st.substring(0,i);
            ll+="e";
            lll=st.substring(i,len);
            ll+=lll;
            text.setText(ll);
            text.setSelection(i + 1);
        }
        if(v.getId()==R.id.r)
        {
            String st;
            String ll;
            String lll;
            int i=text.getSelectionStart();
            st=text.getText().toString();
            int len=st.length();
            ll=st.substring(0,i);
            ll+="r";
            lll=st.substring(i,len);
            ll+=lll;
            text.setText(ll);
            text.setSelection(i + 1);
        }
        if(v.getId()==R.id.t)
        {
            String st;
            String ll;
            String lll;
            int i=text.getSelectionStart();
            st=text.getText().toString();
            ll=st.substring(0,i);
            ll+="t";
            int len=st.length();
            lll=st.substring(i,len);
            ll+=lll;
            text.setText(ll);
            text.setSelection(i + 1);
        }
        if(v.getId()==R.id.y)
        {
            String st;
            String ll;
            String lll;
            int i=text.getSelectionStart();
            st=text.getText().toString();
            ll=st.substring(0,i);
            ll+="y";
            int len=st.length();
            lll=st.substring(i,len);
            ll+=lll;
            text.setText(ll);
            text.setSelection(i + 1);
        }
        if(v.getId()==R.id.u)
        {
            String st;
            String ll;
            String lll;
            int i=text.getSelectionStart();
            st=text.getText().toString();
            ll=st.substring(0,i);
            ll+="u";
            int len=st.length();
            lll=st.substring(i,len);
            ll+=lll;
            text.setText(ll);
            text.setSelection(i + 1);
        }
        if(v.getId()==R.id.i)
        {
            String st;
            String ll;
            String lll;
            int i=text.getSelectionStart();
            st=text.getText().toString();
            ll=st.substring(0,i);
            ll+="i";
            int len=st.length();
            lll=st.substring(i,len);
            ll+=lll;
            text.setText(ll);
            text.setSelection(i + 1);
        }
        if(v.getId()==R.id.o)
        {
            String st;
            String ll;
            String lll;
            int i=text.getSelectionStart();
            st=text.getText().toString();
            ll=st.substring(0,i);
            ll+="o";
            int len=st.length();
            lll=st.substring(i,len);
            ll+=lll;
            text.setText(ll);
            text.setSelection(i + 1);
        }
        if(v.getId()==R.id.p)
        {
            String st;
            String ll;
            String lll;
            int i=text.getSelectionStart();
            st=text.getText().toString();
            ll=st.substring(0,i);
            ll+="p";
            int len=st.length();
            lll=st.substring(i,len);
            ll+=lll;
            text.setText(ll);
            text.setSelection(i + 1);
        }
        if(v.getId()==R.id.a)
        {
            String st;
            String ll;
            String lll;
            int i=text.getSelectionStart();
            st=text.getText().toString();
            ll=st.substring(0,i);
            ll+="a";
            int len=st.length();
            lll=st.substring(i,len);
            ll+=lll;
            text.setText(ll);
            text.setSelection(i + 1);
        }
        if(v.getId()==R.id.s)
        {
            String st;
            String ll;
            String lll;
            int i=text.getSelectionStart();
            st=text.getText().toString();
            ll=st.substring(0,i);
            ll+="s";
            int len=st.length();
            lll=st.substring(i,len);
            ll+=lll;
            text.setText(ll);
            text.setSelection(i + 1);
        }
        if(v.getId()==R.id.d)
        {
            String st;
            String ll;
            String lll;
            int i=text.getSelectionStart();
            st=text.getText().toString();
            ll=st.substring(0,i);
            ll+="d";
            int len=st.length();
            lll=st.substring(i,len);
            ll+=lll;
            text.setText(ll);
            text.setSelection(i + 1);
        }
        if(v.getId()==R.id.f)
        {
            String st;
            String ll;
            String lll;
            int i=text.getSelectionStart();
            st=text.getText().toString();
            ll=st.substring(0,i);
            ll+="f";
            int len=st.length();
            lll=st.substring(i,len);
            ll+=lll;
            text.setText(ll);
            text.setSelection(i + 1);
        }
        if(v.getId()==R.id.g)
        {
            String st;
            String ll;
            String lll;
            int i=text.getSelectionStart();
            st=text.getText().toString();
            ll=st.substring(0,i);
            ll+="g";
            int len=st.length();
            lll=st.substring(i,len);
            ll+=lll;
            text.setText(ll);
            text.setSelection(i + 1);
        }
        if(v.getId()==R.id.h)
        {
            String st;
            String ll;
            String lll;
            int i=text.getSelectionStart();
            st=text.getText().toString();
            ll=st.substring(0,i);
            ll+="h";
            int len=st.length();
            lll=st.substring(i,len);
            ll+=lll;
            text.setText(ll);
            text.setSelection(i + 1);
        }
        if(v.getId()==R.id.j)
        {
            String st;
            String ll;
            String lll;
            int i=text.getSelectionStart();
            st=text.getText().toString();
            ll=st.substring(0,i);
            ll+="j";
            int len=st.length();
            lll=st.substring(i,len);
            ll+=lll;
            text.setText(ll);
            text.setSelection(i + 1);
        }
        if(v.getId()==R.id.k)
        {
            String st;
            String ll;
            String lll;
            int i=text.getSelectionStart();
            st=text.getText().toString();
            ll=st.substring(0,i);
            ll+="k";
            int len=st.length();
            lll=st.substring(i,len);
            ll+=lll;
            text.setText(ll);
            text.setSelection(i + 1);
        }
        if(v.getId()==R.id.l)
        {
            String st;
            String ll;
            String lll;
            int i=text.getSelectionStart();
            st=text.getText().toString();
            ll=st.substring(0,i);
            ll+="l";
            int len=st.length();
            lll=st.substring(i,len);
            ll+=lll;
            text.setText(ll);
            text.setSelection(i + 1);
        }
        if(v.getId()==R.id.z)
        {
            String st;
            String ll;
            String lll;
            int i=text.getSelectionStart();
            st=text.getText().toString();
            ll=st.substring(0,i);
            ll+="z";
            int len=st.length();
            lll=st.substring(i,len);
            ll+=lll;
            text.setText(ll);
            text.setSelection(i + 1);
        }
        if(v.getId()==R.id.x)
        {
            String st;
            String ll;
            String lll;
            int i=text.getSelectionStart();
            st=text.getText().toString();
            ll=st.substring(0,i);
            ll+="x";
            int len=st.length();
            lll=st.substring(i,len);
            ll+=lll;
            text.setText(ll);
            text.setSelection(i + 1);
        }
        if(v.getId()==R.id.c) {
            String st;
            String ll;
            String lll;
            int i = text.getSelectionStart();
            st = text.getText().toString();
            ll = st.substring(0, i);
            ll += "c";
            int len=st.length();
            lll=st.substring(i,len);
            ll += lll;
            text.setText(ll);
            text.setSelection(i + 1);
        }
        if(v.getId()==R.id.v)
        {
            String st;
            String ll;
            String lll;
            int i=text.getSelectionStart();
            st=text.getText().toString();
            ll=st.substring(0,i);
            ll+="v";
            int len=st.length();
            lll=st.substring(i,len);
            ll+=lll;
            text.setText(ll);
            text.setSelection(i + 1);
        }
        if(v.getId()==R.id.b)
        {
            String st;
            String ll;
            String lll;
            int i=text.getSelectionStart();
            st=text.getText().toString();
            ll=st.substring(0,i);
            ll+="b";
            int len=st.length();
            lll=st.substring(i,len);
            ll+=lll;
            text.setText(ll);
            text.setSelection(i + 1);
        }
        if(v.getId()==R.id.n)
        {
            String st;
            String ll;
            String lll;
            int i=text.getSelectionStart();
            st=text.getText().toString();
            ll=st.substring(0,i);
            ll+="n";
            int len=st.length();
            lll=st.substring(i,len);
            ll+=lll;
            text.setText(ll);
            text.setSelection(i + 1);
        }
        if(v.getId()==R.id.m)
        {
            String st;
            String ll;
            String lll;
            int i=text.getSelectionStart();
            st=text.getText().toString();
            ll=st.substring(0,i);
            ll+="m";
            int len=st.length();
            lll=st.substring(i,len);
            ll+=lll;
            text.setText(ll);
            text.setSelection(i + 1);
        }
        if(v.getId()==R.id.zero)                                  //数字
        {
            String st;
            String ll;
            String lll;
            int i=text.getSelectionStart();
            st=text.getText().toString();
            ll=st.substring(0,i);
            ll+="0";
            int len=st.length();
            lll=st.substring(i,len);
            ll+=lll;
            text.setText(ll);
            text.setSelection(i + 1);
        }
        if(v.getId()==R.id.one)
        {
            String st;
            String ll;
            String lll;
            int i=text.getSelectionStart();
            st=text.getText().toString();
            ll=st.substring(0,i);
            ll+="1";
            int len=st.length();
            lll=st.substring(i,len);
            ll+=lll;
            text.setText(ll);
            text.setSelection(i + 1);
        }
        if(v.getId()==R.id.two)
        {
            String st;
            String ll;
            String lll;
            int i=text.getSelectionStart();
            st=text.getText().toString();
            ll=st.substring(0,i);
            ll+="2";
            int len=st.length();
            lll=st.substring(i,len);
            ll+=lll;
            text.setText(ll);
            text.setSelection(i + 1);
        }
        if(v.getId()==R.id.three)
        {
            String st;
            String ll;
            String lll;
            int i=text.getSelectionStart();
            st=text.getText().toString();
            ll=st.substring(0,i);
            ll+="3";
            int len=st.length();
            lll=st.substring(i,len);
            ll+=lll;
            text.setText(ll);
            text.setSelection(i + 1);
        }
        if(v.getId()==R.id.four)
        {
            String st;
            String ll;
            String lll;
            int i=text.getSelectionStart();
            st=text.getText().toString();
            ll=st.substring(0,i);
            ll+="4";
            int len=st.length();
            lll=st.substring(i,len);
            ll+=lll;
            text.setText(ll);
            text.setSelection(i + 1);
        }
        if(v.getId()==R.id.five)
        {
            String st;
            String ll;
            String lll;
            int i=text.getSelectionStart();
            st=text.getText().toString();
            ll=st.substring(0,i);
            ll+="5";
            int len=st.length();
            lll=st.substring(i,len);
            ll+=lll;
            text.setText(ll);
            text.setSelection(i + 1);
        }
        if(v.getId()==R.id.six)
        {
            String st;
            String ll;
            String lll;
            int i=text.getSelectionStart();
            st=text.getText().toString();
            ll=st.substring(0,i);
            ll+="6";
            int len=st.length();
            lll=st.substring(i,len);
            ll+=lll;
            text.setText(ll);
            text.setSelection(i + 1);
        }
        if(v.getId()==R.id.seven)
        {
            String st;
            String ll;
            String lll;
            int i=text.getSelectionStart();
            st=text.getText().toString();
            ll=st.substring(0,i);
            ll+="7";
            int len=st.length();
            lll=st.substring(i,len);
            ll+=lll;
            text.setText(ll);
            text.setSelection(i + 1);
        }
        if(v.getId()==R.id.eight)
        {
            String st;
            String ll;
            String lll;
            int i=text.getSelectionStart();
            st=text.getText().toString();
            ll=st.substring(0,i);
            ll+="8";
            int len=st.length();
            lll=st.substring(i,len);
            ll+=lll;
            text.setText(ll);
            text.setSelection(i + 1);
        }
        if(v.getId()==R.id.nine)
        {
            String st;
            String ll;
            String lll;
            int i=text.getSelectionStart();
            st=text.getText().toString();
            ll=st.substring(0,i);
            ll+="9";
            int len=st.length();
            lll=st.substring(i,len);
            ll+=lll;
            text.setText(ll);
            text.setSelection(i + 1);
        }
        if(v.getId()==R.id.jia)                                                        //运算符
        {
            String st;
            String ll;
            String lll;
            int i=text.getSelectionStart();
            st=text.getText().toString();
            ll=st.substring(0,i);
            ll+="+";
            int len=st.length();
            lll=st.substring(i,len);
            ll+=lll;
            text.setText(ll);
            text.setSelection(i + 1);
        }
        if(v.getId()==R.id.jian)
        {
            String st;
            String ll;
            String lll;
            int i=text.getSelectionStart();
            st=text.getText().toString();
            ll=st.substring(0,i);
            ll+="-";
            int len=st.length();
            lll=st.substring(i,len);
            ll+=lll;
            text.setText(ll);
            text.setSelection(i + 1);
        }
        if(v.getId()==R.id.cheng)
        {
            String st;
            String ll;
            String lll;
            int i=text.getSelectionStart();
            st=text.getText().toString();
            ll=st.substring(0,i);
            ll+="*";
            int len=st.length();
            lll=st.substring(i,len);
            ll+=lll;
            text.setText(ll);
            text.setSelection(i + 1);
        }
        if(v.getId()==R.id.chu)
        {
            String st;
            String ll;
            String lll;
            int i=text.getSelectionStart();
            st=text.getText().toString();
            ll=st.substring(0,i);
            ll+="/";
            int len=st.length();
            lll=st.substring(i,len);
            ll+=lll;
            text.setText(ll);
            text.setSelection(i + 1);
        }
        if(v.getId()==R.id.tuiwei)                                       //退格
        {
            String st;
            String ll;
            String lll;
            int i;
            i=text.getSelectionStart();
            if(i!=0) {
                st = text.getText().toString();
                if(i==text.length()) {
                    ll = st.substring(0, st.length() - 1);
                    i=ll.length();
                }
                else
                {
                    ll=st.substring(0,i-1);
                    lll=st.substring(i,text.length());
                    i=ll.length();
                    ll+=lll;
                }
                text.setText(ll);
                text.setSelection(i);
            }
        }
        if(v.getId()==R.id.qingchu)
        {
            text.setText("");
            out.setText("");
        }
        if(v.getId()==R.id.dengyu)                                                    //字符
        {
            String st;
            String ll;
            String lll;
            int i=text.getSelectionStart();
            st=text.getText().toString();
            ll=st.substring(0,i);
            ll+="=";
            int len=st.length();
            lll=st.substring(i,len);
            ll+=lll;
            text.setText(ll);
            text.setSelection(i + 1);
        }
        if(v.getId()==R.id.wuqiong)                                                    //字符
        {
            String st;
            String ll;
            String lll;
            int i=text.getSelectionStart();
            st=text.getText().toString();
            ll=st.substring(0,i);
            ll+="∞";
            int len=st.length();
            lll=st.substring(i,len);
            ll+=lll;
            text.setText(ll);
            text.setSelection(i + 1);
        }
        if(v.getId()==R.id.chengfang)
        {
            String st;
            String ll;
            String lll;
            int i=text.getSelectionStart();
            st=text.getText().toString();
            ll=st.substring(0,i);
            ll+="^";
            int len=st.length();
            lll=st.substring(i,len);
            ll+=lll;
            text.setText(ll);
            text.setSelection(i + 1);
        }
        if(v.getId()==R.id.douhao)
        {
            String st;
            String ll;
            String lll;
            int i=text.getSelectionStart();
            st=text.getText().toString();
            ll=st.substring(0,i);
            ll+=",";
            int len=st.length();
            lll=st.substring(i,len);
            ll+=lll;
            text.setText(ll);
            text.setSelection(i + 1);
        }
        if(v.getId()==R.id.dian)
        {
            String st;
            String ll;
            String lll;
            int i=text.getSelectionStart();
            st=text.getText().toString();
            ll=st.substring(0,i);
            ll+=".";
            int len=st.length();
            lll=st.substring(i,len);
            ll+=lll;
            text.setText(ll);
            text.setSelection(i + 1);
        }
        if(v.getId()==R.id.dianji)
        {
            String st;
            String ll;
            String lll;
            int i=text.getSelectionStart();
            st=text.getText().toString();
            ll=st.substring(0,i);
            ll+="·";
            int len=st.length();
            lll=st.substring(i,len);
            ll+=lll;
            text.setText(ll);
            text.setSelection(i + 1);
        }
        if(v.getId()==R.id.genghao)
        {
            String st;
            String ll;
            String lll;
            int i=text.getSelectionStart();
            st=text.getText().toString();
            ll=st.substring(0,i);
            ll+="√";
            int len=st.length();
            lll=st.substring(i,len);
            ll+=lll;
            text.setText(ll);
            text.setSelection(i + 1);
        }
        if(v.getId()==R.id.bracesL)                                       //括号组
        {
            String st;
            String ll;
            String lll;
            int i=text.getSelectionStart();
            st=text.getText().toString();
            ll=st.substring(0,i);
            ll+="{";
            int len=st.length();
            lll=st.substring(i,len);
            ll+=lll;
            text.setText(ll);
            text.setSelection(i + 1);
        }
        if(v.getId()==R.id.bracesR)
        {
            String st;
            String ll;
            String lll;
            int i=text.getSelectionStart();
            st=text.getText().toString();
            ll=st.substring(0,i);
            ll+="}";
            int len=st.length();
            lll=st.substring(i,len);
            ll+=lll;
            text.setText(ll);
            text.setSelection(i + 1);
        }
        if(v.getId()==R.id.mbL)
        {
            String st;
            String ll;
            String lll;
            int i=text.getSelectionStart();
            st=text.getText().toString();
            ll=st.substring(0,i);
            ll+="[";
            int len=st.length();
            lll=st.substring(i,len);
            ll+=lll;
            text.setText(ll);
            text.setSelection(i + 1);
        }
        if(v.getId()==R.id.mbR)
        {
            String st;
            String ll;
            String lll;
            int i=text.getSelectionStart();
            st=text.getText().toString();
            ll=st.substring(0,i);
            ll+="]";
            int len=st.length();
            lll=st.substring(i,len);
            ll+=lll;
            text.setText(ll);
            text.setSelection(i + 1);
        }
        if(v.getId()==R.id.pL)
        {
            String st;
            String ll;
            String lll;
            int i=text.getSelectionStart();
            st=text.getText().toString();
            ll=st.substring(0,i);
            ll+="(";
            int len=st.length();
            lll=st.substring(i,len);
            ll+=lll;
            text.setText(ll);
            text.setSelection(i + 1);
        }
        if(v.getId()==R.id.pR)
        {
            String st;
            String ll;
            String lll;
            int i = text.getSelectionStart();
            st = text.getText().toString();
            ll = st.substring(0, i);
            ll += ")";
            int len=st.length();
            lll=st.substring(i,len);
            ll += lll;
            text.setText(ll);
            text.setSelection(i + 1);

        }
        if(v.getId()==R.id.gL)                                       //光标移动
        {
            int i=text.getSelectionStart();
            if(i!=0)
                text.setSelection(i - 1);
        }
        if(v.getId()==R.id.gR)
        {
            int i=text.getSelectionStart();
            if(i!=text.getText().toString().length())
                text.setSelection(i + 1);
        }
        if(v.getId()==R.id.jisuan)                          //计算
        {
            boolean bool=false;
            String flag=text.getText().toString();
            for(int i=0;i<check.length;i++)
            {
                if(flag.contains(check[i]))
                {
                    Operation_main.setText(out);
                    Operation_main.setcontext(this);
                    out.setText("");
                    try {
                        Operation_main.check(flag);
                    } catch (Exception ex) {
                        out.append("请检查输入格式是否正确");
                    }
                    bool=true;
                    break;
                }
            }
            if(!bool)
            {
                Intent intent=new Intent(MainActivity.this,showActivity.class);
                Bundle bundle=new Bundle();
                bundle.putCharSequence("out", text.getText().toString());
                intent.putExtras(bundle);
                startActivity(intent);
            }
        }
        if(v.getId()==R.id.pi)
        {
            String st;
            String ll;
            String lll;
            int i=text.getSelectionStart();
            st=text.getText().toString();
            ll=st.substring(0,i);
            ll+="∏";
            int len=st.length();
            lll=st.substring(i,len);
            ll+=lll;
            text.setText(ll);
            text.setSelection(i + 1);
        }
    }
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {                       //回滚数据重写
        super.onActivityResult(requestCode, resultCode, data);
        switch (requestCode) {
            case 1:
                if (resultCode == RESULT_OK) {
                    String getData = data.getStringExtra("help");
                    text.setText(getData);
                }
            case 2:
                if (resultCode == RESULT_OK) {
                    String getData = data.getStringExtra("help");
                    text.setText(getData);
                }
            case 3:
                if (resultCode == RESULT_OK) {
                    String getData = data.getStringExtra("help");
                    text.setText(getData);
                }
            case 4:
                if (resultCode == RESULT_OK) {
                    String getData = data.getStringExtra("help");
                    text.setText(getData);
                }
            case 5:
                if (resultCode == RESULT_OK) {
                    String getData = data.getStringExtra("help");
                    text.setText(getData);
                }
                break;
            default:
        }
    }
}
