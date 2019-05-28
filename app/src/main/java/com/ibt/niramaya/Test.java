package com.ibt.niramaya;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Locale;

public class Test {

    public static void main(String[] args) {
        isDateAfter("9/10/2015", "28/05/2019");
    }

    public static void checkDate(String str1, String str2){
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);

           /* String str1 = "9/10/2015";*/
            Date date1 = formatter.parse(str1);

            /*String str2 = "10/10/2015";*/
            Date date2 = formatter.parse(str2);

            if (date1.compareTo(date2) < 0) {
                System.out.println("date2 is Greater than my date1");
            }else if (date1.compareTo(date2) > 0) {
                System.out.println("date2 is Smaller than my date1");
            }else if (date1.compareTo(date2) == 0) {
                System.out.println("date2 is Equal date1");
            }

        } catch (ParseException e1) {
            e1.printStackTrace();
        }
    }

    public static void isDateAfter(String str1, String str2) {
        boolean isAfter = false;
        try {
            SimpleDateFormat formatter = new SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH);
            Date date1 = formatter.parse(str1);
            Date date2 = formatter.parse(str2);
            if (date1.compareTo(date2) >= 0) {
                System.out.println("date2 is Greater than my date1");
                isAfter = true;
            }
        } catch (ParseException e1) {
            e1.printStackTrace();
        }

    }
}
