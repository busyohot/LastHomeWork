package com.busyo.yeonho.calview;

/**
 * Created by genius05 on 2015. 12. 19..
 */
public class ThreadWeather  extends Thread {
    APIWeather apiWeather = new APIWeather();


    public void run() {
        int i=0;
        while (true) {
            try {

                apiWeather.apiw();//날씨구하는 API에 apiw 메소드를 수행
                //Log.d("APIAPI333_쓰레드웨더",apiWeather.flag.toString());
                //Thread.sleep(1000);
                // 네트워크를 통하여 데이터를 모두 불러왔다면
                if (apiWeather.flag == true) {
                    //MainActivity.weather.add(apiWeather.title);
                    //MainActivity.tem.add(apiWeather.desc);

                    MainActivity.toast = "true";
                    break;
                }
                else if (apiWeather.flag != true)
                {
                    if(i>10)
                    {

                        MainActivity.tem.add("");
                        MainActivity.tem.add("");
                        MainActivity.tem.add("");
                        MainActivity.tem.add("");
                        MainActivity.tem.add("");

                        MainActivity.weather.add("");
                        MainActivity.weather.add("");
                        MainActivity.weather.add("");
                        MainActivity.weather.add("");
                        MainActivity.weather.add("");


                        apiWeather.flag = true;
                        MainActivity.gubn = "true";
                        MainActivity.toast = "false";
                        break;
                    }

                    else{
                        Thread.sleep(500);
                        i++;
                    }

                }

            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        //Log.d("APIAPI333+쓰레드웨더", MainActivity.weather.size() + "");

    }
}

