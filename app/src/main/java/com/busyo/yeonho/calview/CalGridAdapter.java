package com.busyo.yeonho.calview;

import android.content.Context;
import android.graphics.Color;
import android.graphics.Typeface;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.Calendar;

/**
* Created by user on 2015-12-15.
*/


//그리드 아답터클래스를 구성한다. 그리고나서 그리드아답터에 그리드뷰를 꽂는다
public class CalGridAdapter extends BaseAdapter {    //BaseAdapter의 상속을 받은 GridAdapter를 정의 한다
//그리드뷰에 보여질 날짜(IteamDate) 배열로 지정
Integer [] ID;
//날짜가 전월의 날짜인지 이번달인지 다음달의 날짜인지 상태값 Status
Integer [] ST;
///요일코드숫자 표시
Integer [] YI;
MainActivity mainActivity = new MainActivity();


    //달력을 표현할 년도 와 월
    int calgdselyear = mainActivity.curyear; //초기값 : 지금 년도
    int calgdselmonth = mainActivity .curmonth;  //초기값 : 지금 월


    //전년,전월 값
    int preyear;
    int premonth;

    //내년,다음달 값
    int nextyear;
    int nextmonth;

    //컨텍스트 변수를 선언, this 컨텍스트를  생성자에서 받은후에 mcontext변수에 대입.
    //이는 mcontext변수를 다른 메소드에서 사용하기 위함
    Context mcontext;
    public CalGridAdapter(Context c){//아답터 객체생성
        mcontext =c;
    }
    //여기부터 그리드뷰 안에 한칸 한칸 보여줄 값을 구해온다
    public void date(int a, int s, int y){//getView 에서 position 값과 s 값을 넘겨받음
        ID = new Integer[42];   ST = new Integer[42];   YI = new Integer[42];
        //position값이 (요일값-1) 보다 작으면 앞의 빈칸에 전월 날짜를 넣는다 요일값은 1부터 7까지이고 position값은 0부터 시작해서 1을 빼주는거다
        mainActivity.psn(calgdselyear,calgdselmonth);

        //Log.d("calgd", calgdselyear + " " + calgdselmonth);
        if (a< ((getFirstName(calgdselyear, calgdselmonth))-1))
        {

            //남는칸에 전월의 날짜 구하기
            if (a==0) {
                ID[a] = (getMonthLastDay(mainActivity.preselyear, mainActivity.preselmonth)) - ((getFirstName(calgdselyear, calgdselmonth)) - 1) + 1;
                ST[s]=-1;
                YI[y] = getGetDay(mainActivity.preselyear,mainActivity.preselmonth,ID[a]);
            }
            else{
                ID[a] = (getMonthLastDay(mainActivity.preselyear, mainActivity.preselmonth)) - ((getFirstName(calgdselyear, calgdselmonth)) - 1) + 1+a;
                ST[s]=-1;
                YI[y] = getGetDay(mainActivity.preselyear,mainActivity.preselmonth,ID[a]);
            }
        }
        else if(a>= ((getFirstName(calgdselyear, calgdselmonth))-1))
        {
            //이번달 날짜 구하기
            ID[a] = a - ((getFirstName(calgdselyear, calgdselmonth)) - 1)+1;
            ST[s]=0;
            YI[y] = getGetDay(calgdselyear,calgdselmonth,ID[a]);

            if(ID[a]>(getMonthLastDay(calgdselyear, calgdselmonth)))
            {
                //남는칸에 다음달 날짜 구하기
                ID[a]=a-(getMonthLastDay(calgdselyear, calgdselmonth))    - ((getFirstName(calgdselyear, calgdselmonth)) - 1)   +1;
                ST[s]=1;

                YI[y] = getGetDay(mainActivity.nextselyear,mainActivity.nextselmonth,ID[a]);
            }
        }
        return ;
    }


    @Override
    public int getCount() {
        return 42;//개수를 반환(총 몇개냐). 1일이 토요일에시작하면 6줄이 필요한 경우가 있으니까 7*6 해서 42번 반환하고 1일의
        //시작요일에따라 배열의 시작위치를 정하고 앞의 빈칸 에는 전월의 날짜 뒤의 빈칸에는 다음달의 날짜를 넣는다
    }
    @Override
    public Object getItem(int position) {
        return null;
    }
    @Override
    public long getItemId(int position) {
        return 0;
    }

    //한칸 한칸 내용을 뷰에 넣는다
    @Override
    public View getView(final int position, View convertView, ViewGroup parent) {
        int s=0, y=0;
        date(position, s, position);//현재 위치번호와 , 상태값(날짜가 이전월,현재월,다음월) 인자
        //그리드의 각 칸마다 보여준다
        TextView textView = new TextView(mcontext);
        textView.setHeight(150);
        textView.setPadding(2, 2, 2, 2);
        textView.setGravity(Gravity.LEFT);

        textView.setBackgroundColor(Color.WHITE);









        //날짜가 전월인지,이번달인지,다음달인지의 상태값
        if (ST[s] ==-1){ //-1은 전월

            //숫자가 1자리이면 앞에 0을 붙이고 아니면 그냥 그대로 표시
            if (ID[position].toString().length() == 1){
                textView.setText("0" + ID[position].toString() + "\n" //+ "\n"
                 );
            }
            else{
                textView.setText(ID[position].toString()+"\n" //+ "\n"
                 );
            }


            textView.setTextSize(11);
            textView.setTypeface(Typeface.defaultFromStyle(Typeface.ITALIC));
            textView.setTextColor(Color.GRAY);
        }
        else if (ST[s] ==0){//0은 이번달


            if (calgdselyear == mainActivity.curyear && calgdselmonth == mainActivity.curmonth &&  ID[position]>=mainActivity.curdate && (ID[position] - mainActivity.curdate)<5)
            {

                //Log.e("빼기", String.valueOf(ID[position] - mainActivity.curdate));//숫자가 1자리이면 앞에 0을 붙이고 아니면 그냥 그대로 표시
                if (ID[position].toString().length() == 1){
                    textView.setText("0" + ID[position].toString()
                                    //+ "\n"
                                    +" "
                                    + MainActivity.tem.get(ID[position] - mainActivity.curdate)
                                    + "\n"
                                    + MainActivity.weather.get(ID[position] - mainActivity.curdate)



                    );
                }
                else{
                    textView.setText(ID[position].toString()
                                    //+ "\n"
                                    +" "
                                    + MainActivity.tem.get(ID[position] - mainActivity.curdate)
                                    + "\n"
                                    + MainActivity.weather.get(ID[position] - mainActivity.curdate)

                    );
                }
            }
            else
            {
                //숫자가 1자리이면 앞에 0을 붙이고 아니면 그냥 그대로 표시
                if (ID[position].toString().length() == 1){
                    textView.setText("0" + ID[position].toString() + "\n" //+ "\n"
                    );
                }
                else{
                    textView.setText(ID[position].toString()+"\n" //+ "\n"
                    );
                }
            }



            String dt=doubleText(ID[position].toString());
            String mt=doubleText(String.valueOf(calgdselmonth));
            textView.setTextSize(11);
            textView.setTextColor(Color.BLACK);
            textView.setTypeface(Typeface.DEFAULT_BOLD);

            int j = 0;//초기값

            for (int i = 0; i < MainActivity.arraydate.size(); i++) {
                //Log.d("11111111",arraydate.get(i)+"__"+(calgdselyear + "" + mt + "" + dt));
                if (MainActivity.arraydate.get(i).equals(calgdselyear + "" + mt + "" + dt)) {//선택한 날짜에 해당하는 자료가 array 에 있다면
                    j = 1;//j를 1로 바꾸어 주황색 및,굵은 글자를 나타낼수 있게 한다
                }
            }
            if (j == 1) {
                textView.setBackgroundColor(Color.argb(255, 255, 180, 60));

            }
            if (YI[position] == 1){//일요일
                //textView.setBackgroundColor(Color.argb(255, 255, 188, 188)); //붉은계통으로
                textView.setTextColor(Color.RED); //붉은계통으로
            }
            else if (YI[position] == 7) {//토요일)
                //textView.setBackgroundColor(Color.argb(255, 200, 236, 255));//푸른계통으로
                textView.setTextColor(Color.BLUE);//푸른계통으로
            }
        }
        else if (ST[s] == 1){//1은 다음달

            //숫자가 1자리이면 앞에 0을 붙이고 아니면 그냥 그대로 표시
            if (ID[position].toString().length() == 1){
                textView.setText("0" + ID[position].toString() + "\n" + "\n");
            }
            else{
                textView.setText(ID[position].toString()+"\n" + "\n");
            }


            textView.setTextSize(11);
            textView.setTypeface(Typeface.defaultFromStyle(Typeface.ITALIC));
            textView.setTextColor(Color.GRAY);
        }


        return textView;
    }


    //입력한 년과 월의 1일의 요일에 해당하는 숫자 구하기. 1일을 무슨요일 부터 시작할지를 구하기위해
    public int getFirstName(int year, int month){
        month--;
        Calendar d =Calendar.getInstance();
        d.set(year,month,1);//년월일 지정
        int a = d.get(Calendar.DAY_OF_WEEK);//지정한 년월일의 해당요일의 숫자코드 반환 일요일은 1,토요일은 7
        return a;
    }

    //입력한 년과 월의 마지막 날짜 구하기
    public int getMonthLastDay(int year, int month) {
        month--;//입력받은 월에서 1을 뺀다. 날짜함수의 월은 0 부터 11까지이다. 1월을 찾으려면 1월을 입력받으면 0에 해당하는것을 찾아야한다
        //1월부터 12월까지
        int[] d = {31,28,31,30,31,30,31,31,30,31,30,31};
        //윤년계산
        if (((0 == (year%4)) && ( (0 != (year%100)) || (0 == (year%400)))) && month == 1) {
            return 29; //윤년이면 2월(month-1)엔 29반환
        } else {
            return d[month]; //아니면 해당월의 날짜 반환
        }
    }

    //입력한 년,월,일로 요일코드를 구해낸것
    public Integer getGetDay(int year,int month,int date){
        month--;
        Calendar d =Calendar.getInstance();
        d.set(year,month,date);//년월일 지정
        int b = d.get(Calendar.DAY_OF_WEEK);//지정한 년월일의 해당요일의 숫자코드 반환 일요일은 1,토요일은 7
        return b;
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

}

