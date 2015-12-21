package com.busyo.yeonho.calview;

import android.util.Log;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.InputStream;
import java.net.URL;

/**
 * Created by genius05 on 2015. 12. 19..
 */
public class APIWeather {

    String uri = "http://weather.yahooapis.com/forecastrss?w=1132599&u=c";
    // 웹사이트에 접속을 도와주는 클래스
    URL url;
    // XML문서의 내용을 임시로 저장할 변수
    String tagname = "", weather="", tem="", wcode="";
    // 데이터의 내용을 모두 읽어드렸는지에 대한 정보를 저장
    Boolean flag = false;


    public void apiw() {

        try {
            //xml문서를 읽고 해석해줄 수 있는 객체를 선언
            XmlPullParserFactory factory = XmlPullParserFactory.newInstance();
            //네임스페이스 사용여부
            factory.setNamespaceAware(true);
            //실제 xml문서를 읽어 드리면서 데이터를 추출해주는 객체 선언
            XmlPullParser xpp = factory.newPullParser();

            // 웹사이트에 접속
            url = new URL(uri);

            // 사이트 접속후에 xml 문서를 읽어서 가져옴
            InputStream in = url.openStream();

            // 웹사이트로부터 받아온 xml문서를 읽어드리면서 데이터를 추출해 주는 XmlPullParser객체로 넘겨줌
            xpp.setInput(in, "UTF-8");

            // 이벤트 내용을 사용하기 위해서 변수 선언
            int eventType = xpp.getEventType();

            // 반복문을 사용하여 문서의 끝까지 읽어 들이면서 데이터를 추출하여 각각의 벡터에 저장
            while(eventType != XmlPullParser.END_DOCUMENT ) { //END_DOCUMENT =1

                if(eventType == XmlPullParser.START_TAG)  //START_TAG = 2
                {

                    // 태그의 이름을 알아야 텍스트를 저장하기에 태그이름을 읽어서 변수에 저장
                    tagname = xpp.getName();
                    if (tagname.equals("forecast")){
                        tem=xpp.getAttributeValue(null,"low")+"/"+xpp.getAttributeValue(null,"high");
                        weather=xpp.getAttributeValue(null,"text");
                        wcode = xpp.getAttributeValue(null,"code");
                        Log.d("APIAPI+에이피아이웨더", wcode);//tem + "_" + weather);

                        MainActivity.tem.add(tem);
                        if (wcode.equals("0")){weather="토네이도";}
                        else if (wcode.equals("1")){weather="열대폭풍";}
                        else if (wcode.equals("2")){weather="허리케인";}
                        else if (wcode.equals("3")){weather="심한천둥번개";}
                        else if (wcode.equals("4")){weather="천둥번개";}
                        else if (wcode.equals("5")){weather="진눈깨비";}
                        else if (wcode.equals("6")){weather="진눈깨비";}
                        else if (wcode.equals("7")){weather="진눈깨비";}
                        else if (wcode.equals("8")){weather="진눈깨비";}
                        else if (wcode.equals("9")){weather="이슬비";}
                        else if (wcode.equals("10")){weather="진눈깨비";}
                        else if (wcode.equals("11")){weather="소나기";}
                        else if (wcode.equals("12")){weather="오전소나기";}
                        else if (wcode.equals("13")){weather="눈발날림";}
                        else if (wcode.equals("14")){weather="눈 조금";}
                        else if (wcode.equals("15")){weather="눈 날림";}
                        else if (wcode.equals("16")){weather="눈";}
                        else if (wcode.equals("17")){weather="우박";}
                        else if (wcode.equals("18")){weather="진눈깨비";}
                        else if (wcode.equals("19")){weather="먼지";}
                        else if (wcode.equals("20")){weather="안개";}
                        else if (wcode.equals("21")){weather="연무";}
                        else if (wcode.equals("22")){weather="심한연무";}
                        else if (wcode.equals("23")){weather="심한바람";}
                        else if (wcode.equals("24")){weather="바람";}
                        else if (wcode.equals("25")){weather="추움";}
                        else if (wcode.equals("26")){weather="구름";}
                        else if (wcode.equals("27")){weather="구름많음-밤";}
                        else if (wcode.equals("28")){weather="구름많음-낮";}
                        else if (wcode.equals("29")){weather="구름조금-밤";}
                        else if (wcode.equals("30")){weather="구름조금-낮";}
                        else if (wcode.equals("31")){weather="맑아짐-밤";}
                        else if (wcode.equals("32")){weather="해";}
                        else if (wcode.equals("33")){weather="맑음-밤";}
                        else if (wcode.equals("34")){weather="맑음-낮";}
                        else if (wcode.equals("35")){weather="비와 우박";}
                        else if (wcode.equals("36")){weather="더움";}
                        else if (wcode.equals("37")){weather="천둥번개";}
                        else if (wcode.equals("38")){weather="천둥번개";}
                        else if (wcode.equals("39")){weather="천둥번개";}
                        else if (wcode.equals("40")){weather="소나기";}
                        else if (wcode.equals("41")){weather="폭설";}
                        else if (wcode.equals("42")){weather="가끔 눈";}
                        else if (wcode.equals("43")){weather="폭설";}
                        else if (wcode.equals("44")){weather="구름조금";}
                        else if (wcode.equals("45")){weather="토네이도";}
                        else if (wcode.equals("46")){weather="천둥번개";}
                        else if (wcode.equals("47")){weather="천둥번개";}
                        else {weather="알수없음";}
                        Log.d("APIAPI+에이피아이웨더", weather);
                        MainActivity.weather.add(weather);
                        MainActivity.wcode.add(wcode);
                        // 변수 초기화


                        weather="";
                        tem="";
                        wcode="";
                    }


                }


                eventType = xpp.next();
            }
            // 모든 xml문서를 읽어드렸다면
            flag = true;
            MainActivity.gubn = "true";

        } catch(Exception e) {
            e.printStackTrace();
        }





    }

}
