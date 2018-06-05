package com.commerceerp.system.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;

/**
 * 时间工具
 * Created by Administrator on 2018/3/26.
 */
public class DateUtil {

    //当前日期加上day天
    public static String getAfterDate(String date,int day) {
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");
        try {
            Date d=format.parse(date);
            Date date2 = new Date(d.getTime() + day * 24 * 60 * 60 * 1000);
            return format.format(date2);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        return date;
    }

    //为获取当前系统时间
    public static String getSystemTime(String pattern) {
        SimpleDateFormat df = new SimpleDateFormat(pattern);//设置日期格式
        String str = df.format(new Date());// new Date()为获取当前系统时间
        return str;
    }

    /**
     * 求两个时间相差的天、时、分
     * @param startTime
     * @param endTime
     * @param format
     * @param n 0.天 1.时 2.分
     * @return
     */
    public static long dateDiff(String startTime, String endTime, String format,int n) {
        //按照传入的格式生成一个simpledateformate对象
        SimpleDateFormat sd = new SimpleDateFormat(format);
        long nd = 1000 * 24 * 60 * 60;//一天的毫秒数
        long nh = 1000 * 60 * 60;//一小时的毫秒数
        long nm = 1000 * 60;//一分钟的毫秒数
        long diff = 0;
        try{
            //获得两个时间的毫秒时间差异
            diff = sd.parse(endTime).getTime() - sd.parse(startTime).getTime();
        }catch (Exception e){

        }
        long day = diff / nd;//计算差多少天
        long hour = diff / nh;//计算差多少小时
        long min = diff / nm;//计算差多少分钟
        long time[] = {day,hour,min};
        //System.out.println("时间相差：" + day + "天" + hour + "小时" + min + "分钟" );
        return time[n];
    }

    //比较两个时间
    public static boolean compareDate(String startTime, String endTime){
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
        try {
            Date date1 = sf.parse(startTime);
            Date date2 = sf.parse(endTime);
            return !date1.after(date2);
        } catch (ParseException e) {
            e.printStackTrace();
            return false;
        }
    }

    /**
     * 获得该月最后一天
     * @param year
     * @param month
     * @return
     */
    public static String getLastDayOfMonth(int year,int month){
        Calendar cal = Calendar.getInstance();
        //设置年份
        cal.set(Calendar.YEAR,year);
        //设置月份
        cal.set(Calendar.MONTH, month-1);
        //获取某月最大天数
        int lastDay = cal.getActualMaximum(Calendar.DAY_OF_MONTH);
        //设置日历中月份的最大天数
        cal.set(Calendar.DAY_OF_MONTH, lastDay);
        //格式化日期
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String lastDayOfMonth = sdf.format(cal.getTime());
        return lastDayOfMonth;
    }

}
