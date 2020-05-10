package com.mosman.tutorfinderapp.utils;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class Time {
    public static void main(String[] args)
    {
        //Input date in String format
        String input = "2014-04-02 22:22:12";
        System.out.println(getTime(input));
    }
    public static String getTime(String input){

        //Date/time pattern of input date
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //Date/time pattern of desired output date
        DateFormat outputformat = new SimpleDateFormat("hh:mmaa MM-dd"); //yyyy-MM-dd hh:mm:ss aa
        Date date = null;
        String output = null;
        try{
            //Conversion of input String to date
            date= df.parse(input);
            //old date format to new date format
            output = outputformat.format(date);
        }catch(ParseException pe){
            pe.printStackTrace();
        }
        return output;
    }
}
