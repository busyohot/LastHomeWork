package com.busyo.yeonho.calview;

import android.content.Context;
import android.graphics.Color;
import android.view.Gravity;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

/**
 * Created by user on 2015-12-18.
 */
public class ScListAdapter extends BaseAdapter {
    //리스트 뷰에 담을 객체들
    String llistsc;
    String clistsc;
    String rlistsc;


    MainActivity mainActivity = new MainActivity();
    //컨텍스트 변수를 선언, this 컨텍스트를  생성자에서 받은후에 mcontext변수에 대입.
    //이는 mcontext변수를 다른 메소드에서 사용하기 위함
    Context lcontext;

    public ScListAdapter(Context c) {//아답터 객체생성
        lcontext = c;
    }

    public void listd(int a){//리스트 뷰에서 이 메쏘드를 불러 값을 구한다



        //Log.d("list", String.valueOf(mainActivity.listdate));
        String zd,zm;
        if (mainActivity.gdoitcst == 0){//이번달것만 리스트뷰를 보여주자

            if (String.valueOf(mainActivity.listdate).length()==1){//위에서 gridView.setOnItemClickListener 에서 구한 선택한 날짜를 listview 여기에서도 쓴다
                zd="0"+mainActivity.listdate;
            }
            else{
                zd=""+mainActivity.listdate;
            }

            if (String.valueOf(mainActivity.listmonth).length()==1){
                zm="0"+mainActivity.listmonth;
            }
            else{
                zm=""+mainActivity.listmonth;
            }
            //Log.d("list",((mainActivity.listyear+""+zm+""+zd).toString())+"__"+mainActivity.arraydate.get(a).toString());
            if ((mainActivity.listyear+""+zm+""+zd).toString().equals(mainActivity.arraydate.get(a).toString())){//켈린더에 선택한 날짜에 해당하는 값이 array에 있으면

                llistsc = mainActivity.arraydate.get(a);
                clistsc = mainActivity.arraytime.get(a);
                rlistsc = mainActivity.arraysc.get(a);
                llistsc=llistsc.substring(0,4)+"/"+llistsc.substring(4,6)+"/"+llistsc.substring(6,8)+" ";
                clistsc=clistsc.substring(0,2)+" "+clistsc.substring(2,4)+":"+ clistsc.substring(4,6)+" ";
            }
            else{
                llistsc=null;
            }
        }else{
            llistsc=null;
        }
        return ;
    }

    @Override
    public int getCount() {
        return mainActivity.arraydate.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    //한칸 한칸 내용을 리스트뷰에 넣는다
    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        listd(position);
        //Log.d("list",llistsc + clistsc + rlistsc);
        if (llistsc == null){
            TextView ltextView = new TextView(lcontext);
            //ltextView.setVisibility(View.GONE);

            ltextView.setHeight(0);
            ltextView.setBackgroundColor(Color.WHITE);

            return ltextView;
        }
        else{
            TextView ltextView = new TextView(lcontext);
            ltextView.setGravity(Gravity.LEFT);
            ltextView.setTextSize(18);
            ltextView.setBackgroundColor(Color.WHITE);
            ltextView.setText(llistsc + clistsc + rlistsc);
            ltextView.setPadding(2, 2, 2, 2);
            return ltextView;
        }
    }





}
