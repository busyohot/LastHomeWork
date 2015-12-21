package com.busyo.yeonho.calview;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;

public class MainActivity extends ActionBarActivity {

    static String gubn;
    static String toast;
    ThreadWeather threadWeather;
    CalGridAdapter gdadapter;    GridView gridView;

    ScListAdapter lsadapter;    ListView listView;
   APIWeather apiWeather = new APIWeather();

    //전월버튼
    Button btpre;
    //년월표시
    TextView tvym;
    //익월버튼
    Button btnext;

    //액티비티 첫 로딩시 현재날짜 표시. 선택한 날짜 표시 텍스트뷰
    TextView maxdate;

    //데이터 저장할 배열만들기
    static ArrayList <String> arraydate;
    static ArrayList <String> arraytime;
    static ArrayList <String> arraysc;
    static ArrayList <String> weather;
    static ArrayList <String> tem;
    static ArrayList <String> wcode;

    //인텐트로 넘겨받은 값
    String inputsc;
    String inputhh;
    String inputmm;
    String inputrb;

    //올해,이번달 값
    int curyear =Calendar.getInstance().get(Calendar.YEAR);
    int curmonth=Calendar.getInstance().get(Calendar.MONTH) + 1;
    int curdate=Calendar.getInstance().get(Calendar.DATE);

    //지금 년 월 값
    int nowyear =curyear;
    int nowmonth=curmonth;
    int nowdate=curdate;
    int gdoitcst;

    //선택한 년월,
    int selyear=nowyear;
    int selmonth=nowmonth;
    //화면에서 선택한 날짜(년과 월은 올해와 이번달 일테니 화면에서 선택한 날짜만 구하는변수
    int seldate=nowdate;

    static int listdate;
    static int listyear;
    static int listmonth;
    //전년,전월 값
    int preselyear;
    int preselmonth;
    int preseldate;

    //내년,다음달 값
    int nextselyear;
    int nextselmonth;
    int nextseldate;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        arraydate = new ArrayList<String>();
        arraytime=new ArrayList<String>();
        arraysc=new ArrayList<String>();
        weather = new ArrayList<String>();
        tem= new ArrayList<String>();
        wcode= new ArrayList<String>();
        threadWeather = new ThreadWeather();

        threadWeather.setDaemon(true);
        threadWeather.start();
        //Log.d("APIAPI1_메인액티비티1", weather.size() + "");
        int i=1;
        do
        {

            //Log.d("APIAPI1_메인액티비티0",apiWeather.flag.toString());
            try {

                    Thread.sleep(500);

                } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } while (gubn!="true");
        //Log.d("APIAPI1_메인액티비티2", toast+"");

        if(toast.equals("false"))
        {
            Toast.makeText(this,"날씨정보를 가져오지 못했습니다.\n네트워크상태를 확인하세요.",Toast.LENGTH_SHORT).show();
        }


        //Log.d("APIAPI1_메인액티비티2", weather.size() + "");

        setTitle("LastHomeWork 정연호"); //타이틀

        btpre=(Button)findViewById(R.id.btpre);
        btnext=(Button)findViewById(R.id.btnext);
        tvym=(TextView)findViewById(R.id.tvym);
        maxdate = (TextView)findViewById(R.id.maxdate);
        maxdate.setText("오늘 날짜 : " + nowyear + "년 " + nowmonth + "월 " + nowdate + "일");
        //Log.d("wheather", (String) weather.getData().get(0));
        //초기에는 현재의 년월 표시
        //nowym();
        psn(curyear, curmonth);

        tvym.setText(nowyear + "년 " + doubleText(String.valueOf(nowmonth)) + "월");
        //Log.d("mainaclass", nowyear + " " + nowmonth);
        String dt = doubleText(String.valueOf(nowdate));
        String mt= doubleText(String.valueOf(nowmonth));
        maxdate.setText("선택 날짜 : " + nowyear + "년 " + mt + "월 " + dt + "일");

        //년월표시 텍스트뷰를 눌렀을때 초기값으로 현재 년월 표시
        tvym.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //nowym();
                psn(curyear,curmonth);

                tvym.setText(nowyear + "년 " + doubleText(String.valueOf(nowmonth)) + "월");
                //Log.d("mainaclass", nowyear + " " + nowmonth);
                gdadapter.calgdselyear=nowyear;
                gdadapter.calgdselmonth=nowmonth;
                gdadapter.notifyDataSetChanged();
                String dt = doubleText(String.valueOf(seldate));
                String mt= doubleText(String.valueOf(selmonth));
                maxdate.setText("선택 날짜 : " + selyear + "년 " + mt + "월 " + dt + "일");


            }
        });

        btpre.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                psn(nowyear, nowmonth);
                nowyear=preselyear;
                nowmonth = preselmonth;
                tvym.setText(nowyear + "년 " + doubleText(String.valueOf(nowmonth)) + "월");
                //Log.d("mainaclass", nowyear + " " + nowmonth);
                gdadapter.calgdselyear=nowyear;
                gdadapter.calgdselmonth=nowmonth;
                gdadapter.notifyDataSetChanged();
                String dt = doubleText(String.valueOf(seldate));
                String mt= doubleText(String.valueOf(selmonth));
                maxdate.setText("선택 날짜 : " + selyear + "년 " + mt + "월 " + dt + "일");
            }
        });

        btnext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                psn(nowyear, nowmonth);
                nowyear=nextselyear;
                nowmonth = nextselmonth;
                tvym.setText(nowyear + "년 " + doubleText(String.valueOf(nowmonth)) + "월");
                //Log.d("mainaclass", nowyear + " " + nowmonth);
                gdadapter.calgdselyear=nowyear;
                gdadapter.calgdselmonth=nowmonth;
                gdadapter.notifyDataSetChanged();
                String dt = doubleText(String.valueOf(seldate));
                String mt= doubleText(String.valueOf(selmonth));
                maxdate.setText("선택 날짜 : " + selyear + "년 " + mt + "월 " + dt + "일");
            }
        });

        //그리드 아답터 변수를 생성한후 activity_main.xml의 그리드 뷰에 적용시킨다.
        gridView = (GridView)findViewById(R.id.gridview);
        gdadapter= new CalGridAdapter(this);
        gridView.setAdapter(gdadapter);
        gridView.setBackgroundColor(Color.BLACK);

        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

                int s = 0;
                int y = 0;
                gdadapter.date(position, s, y);

                gdoitcst = gdadapter.ST[s];//상태값 (전월인가 당월인가 익월인가)

                    if (gdoitcst ==0) { //당월
                    selyear=nowyear;
                        listyear=nowyear;
                    selmonth=nowmonth;
                        listmonth=nowmonth;
                        seldate = gdadapter.ID[position];
                        listdate = gdadapter.ID[position];//여기에 선택한날짜가 listview에서도 쓰인다
                        //Log.d("list", String.valueOf(seldate));
                    String dt = doubleText(String.valueOf(seldate));
                    String mt= doubleText(String.valueOf(selmonth));
                    maxdate.setText("선택 날짜 : " + selyear + "년 " + mt + "월 " + dt + "일");


                        lsadapter.notifyDataSetChanged();
                }
            }
        });

        //리스트 아답터 변수를 생성한후 activity_main.xml의 리스트 뷰에 적용시킨다.
        listView = (ListView)findViewById(R.id.listview);
        lsadapter= new ScListAdapter(this);
        listView.setAdapter(lsadapter);
        //listView.setBackgroundColor(Color.BLACK);
    }

    //년월일 이 들어오면 저번달 년월일 , 이번달 년월일, 다음달 년월일 구하기
    public int  psn(int year, int month){
        if (month - 1 == 0) {
            preselyear = year - 1;
            preselmonth = 12;
        } else if (month - 1 != 0) {
            preselyear = year;
            preselmonth = month-1;
        }

        nowyear=year;
        nowmonth=month;

        if (month +1 == 13){
            nextselyear = year +1;
            nextselmonth = 1;
        }
        else if (month +1 != 13){
            nextselyear = year;
            nextselmonth = month + 1;
        }

        return preselyear & preselmonth & nowyear & nowmonth & nextselyear & nextselmonth;
    }

    //길이가 1글자 이면 앞에 0을 붙여라
    public String doubleText (String singletext){
        String dbt;
        if (singletext.length() == 1){
            dbt = "0"+singletext;
        }
        else
        {
            dbt = singletext;
        }
        return dbt;
    }



    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();
        //메뉴 에서 아이템을 선택하여 수행
        //noinspection SimplifiableIfStatement
        if (id == R.id.scin) {

            // 액티비티를 띄워주도록 startActivityForResult() 메소드를 호출합니다.
            Intent dialintent = new Intent(getBaseContext(), dialog.class);
            String zd,zm;
            if (String.valueOf(seldate).length()==1){
                zd="0"+seldate;
            }
            else{
                zd=""+seldate;
            }

            if (String.valueOf(selmonth).length()==1){
                zm="0"+selmonth;
            }
            else{
                zm=""+selmonth;
            }
            dialintent.putExtra("year",selyear+"");
            dialintent.putExtra("month",zm + "");
            dialintent.putExtra("date",zd+"");
            startActivityForResult(dialintent, 1001);
            return true;
        }
        return super.onOptionsItemSelected(item);
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (requestCode ==  1001 && resultCode==RESULT_OK)
        {
            inputsc =data.getStringExtra("inputsc");
            inputhh =data.getStringExtra("inputhh");
            if (inputhh.toString().length()==1){
                inputhh="0"+inputhh;
            }
            inputmm =data.getStringExtra("inputmm");
            if (inputmm.toString().length()==1){
                inputmm="0"+inputmm;
            }
            inputrb = data.getStringExtra("inputrb");


            String zd,zm;
            if (String.valueOf(seldate).length()==1){
                zd="0"+seldate;
            }
            else{
                zd=""+seldate;
            }

            if (String.valueOf(selmonth).length()==1){
                zm="0"+selmonth;
            }
            else{
                zm=""+selmonth;
            }
            int maxsz = arraydate.size();
            arraydate.add(maxsz,selyear+""+zm+""+zd);
            arraytime.add(maxsz,inputrb+""+inputhh+""+inputmm);
            arraysc.add(maxsz, inputsc+"");

            gdadapter.notifyDataSetChanged();
            lsadapter.notifyDataSetChanged();
        }
    }
}
