package com.sk.brook;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by songk on 17/11/20.
 */
public class DateTempUtil {

    private final static String pattern1 = "yyyy-MM-dd HH:mm:ss";
    private final static String pattern2 = "yyyy-MM-ddHH:mm:ss";
    private final static String pattern3 = "yyyyMMdd HH:mm:ss";

    private final static List<String> patternList = new ArrayList<String>();
    static {
        patternList.add(pattern1);
        patternList.add(pattern2);
        patternList.add(pattern3);
    }



    public static Date parseString(String dateStr){

        Date date = null;
        for(String pattern : patternList){
            try{
                SimpleDateFormat sdf  = new SimpleDateFormat(pattern);
                date = sdf.parse(dateStr);
                break;
            }catch (Exception e){
                System.out.println(e);
            }
        }
        return date;
    }
}
