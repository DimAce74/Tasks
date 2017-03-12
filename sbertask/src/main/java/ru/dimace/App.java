package ru.dimace;

import sun.util.resources.LocaleData;

import java.math.BigDecimal;
import java.math.BigInteger;
import java.time.LocalDate;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

/**
 * Created by DimAce on 07.03.2017.
 */
public class App {
    public static void main(String[] args) {
        UtilClass utilClass = new UtilClass();
        UtilClass utilClass2 = new UtilClass();
        short s = 0;
        byte b = 0;
        System.out.println(utilClass.compareNumberWithZero(0.0));
        System.out.println(utilClass.compareNumberWithZero(0.1));
        System.out.println(utilClass.compareNumberWithZero(new Integer(0)));
        System.out.println(utilClass.compareNumberWithZero(new Float(0)));
        System.out.println(utilClass.compareNumberWithZero(new Long(0)));
        System.out.println(utilClass.compareNumberWithZero(s));
        System.out.println(utilClass.compareNumberWithZero(b));
        System.out.println(utilClass.compareNumberWithZero(BigInteger.ZERO));
        System.out.println(utilClass.compareNumberWithZero(BigDecimal.ZERO));

        List<Integer> ints = Arrays.asList(5, 5, 45, 88);
        utilClass.logData("sfgsfg", ints, '/');
        utilClass2.logData("werwer", ints, '-');

        Date a = new Date();
        try {
            Thread.currentThread().sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Date c = new Date();

        System.out.println(a.equals(c));


    }
}
