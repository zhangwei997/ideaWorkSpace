package com.xakj.platform.utils;


import com.alibaba.fastjson.JSONObject;
import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @Description: TODO 系统时间公共类
 * @Author: Hardy
 * @DateTime: 2019/5/7 17:03
 * @Verstion 1.0
 */
public class DateUtils {
    private static Logger logger = Logger.getLogger(DateUtils.class.getName());
    private static Date date;

    private static SimpleDateFormat simpleDateFormat = new SimpleDateFormat();

    public static final String DATE_FORMAT_001 = "yyyy年MM月dd日 HH时mm分ss秒";

    public static final String DATE_FORMAT_002 = "yyyy-MM-dd HH:mm:ss";

    public static final String DATE_FORMAT_003 = "yyyy/MM/dd HH:mm:ss";

    public static final String DATE_FORMAT_004 = "yyyyMMdd";

    public static final String DATE_FORMAT_005 = "yyyy年MM月dd日";

    public static final String DATE_FORMAT_006 = "yyyy-MM-dd";

    public static final String DATE_FORMAT_007 = "HH:mm:ss";

    public static final String DATE_FORMAT_008 = "HH:mm:ss:SSS";

    public static final String DATE_FORMAT_009 = "yyyy-MM";

    public static final String DATE_FORMAT_010 = "yyyyMM";

    public static final String DATE_FORMAT_011 = "yyyy-MM-dd HH:mm";

    public static final String DATE_FORMAT_YYYYMMDD_POINT = "yyyy.MM.dd";

    /**
     * @Description:
     * @param format
     * @param date
     * @return
     * @return String 返回类型
     */
    public static String date2string(Date date, String format) {
        if (date == null) {
            return "";
        }
        DateFormat df = new SimpleDateFormat(format);
        String result = df.format(date);
        return result;
    }

    /**
     * @Description:
     * @param param
     * @param format
     * @return
     * @throws ParseException
     * @return Date 返回类型
     */
    public static Date string2date(String param, String format) throws ParseException {
        DateFormat df = new SimpleDateFormat(format);
        Date date = df.parse(param);
        return date;
    }

    /**
     * 取得当前时间
     *
     * @return 当前日期（Date）
     */
    public static Date getCurrentDate() {
        return new Date();
    }

    /***
     * 获取当前日期
     *
     * @Description: getCurrentDataStr
     * @return String
     */
    public static String getCurrentDataStr() {
        return getDate2Str("yyyyMMdd", new Date());
    }

    /***
     * 获取当前日期
     *
     * @Description: getCurrentDataStr
     * @return String
     */
    public static String getCurrentDataStr2() {
        return getDate2Str(DATE_FORMAT_006, new Date());
    }

    /***
     * 获取当前日期
     *
     * @Description: getCurrentDataStr
     * @return String
     */
    public static String getCurrentDataTimeStr() {
        return getDate2Str(DATE_FORMAT_002, new Date());
    }

    /**
     * 取得昨天此时的时间
     *
     * @return 昨天日期（Date）
     */
    public static Date getYesterdayDate() {
        return new Date(getCurTimeMillis() - 0x5265c00L);
    }

    /**
     * 取得去过i天的时间
     *
     * @param i
     * 过去时间天数
     * @return 昨天日期（Date）
     */
    public static Date getPastdayDate(int i) {
        return new Date(getCurTimeMillis() - 0x5265c00L * i);
    }

    /**
     * 取得当前时间的长整型表示
     *
     * @return 当前时间（long）
     */
    public static long getCurTimeMillis() {
        return System.currentTimeMillis();
    }

    /**
     * 取得当前时间的特定表示格式的字符串
     *
     * @param formatDate
     * 时间格式（如：yyyy/MM/dd hh:mm:ss）
     * @return 当前时间
     */
    public static synchronized String getCurFormatDate(String formatDate) {
        date = getCurrentDate();
        simpleDateFormat.applyPattern(formatDate);
        return simpleDateFormat.format(date);
    }

    /**
     * 取得某日期时间的特定表示格式的字符串
     *
     * @param format
     * 时间格式
     * @param date
     * 某日期（Date）
     * @return 某日期的字符串
     */
    public static synchronized String getDate2Str(String format, Date date) {
        simpleDateFormat.applyPattern(format);
        return simpleDateFormat.format(date);
    }

    /**
     * 将日期转换为长字符串（包含：年-月-日 时:分:秒）
     *
     * @param date
     * 日期
     * @return 返回型如：yyyy-MM-dd HH:mm:ss 的字符串
     */
    public static String getDate2LStr(Date date) {
        return getDate2Str("yyyy-MM-dd HH:mm:ss", date);
    }

    /**
     * 将日期转换为长字符串（包含：年/月/日 时:分:秒）
     *
     * @param date
     * 日期
     * @return 返回型如：yyyy/MM/dd HH:mm:ss 的字符串
     */
    public static String getDate2LStr2(Date date) {
        return getDate2Str("yyyy/MM/dd HH:mm:ss", date);
    }

    /**
     * 将日期转换为中长字符串（包含：年-月-日 时:分）
     *
     * @param date
     * 日期
     * @return 返回型如：yyyy-MM-dd HH:mm 的字符串
     */
    public static String getDate2MStr(Date date) {
        return getDate2Str("yyyy-MM-dd HH:mm", date);
    }

    /**
     * 将日期转换为中长字符串（包含：年/月/日 时:分）
     *
     * @param date
     * 日期
     * @return 返回型如：yyyy/MM/dd HH:mm 的字符串
     */
    public static String getDate2MStr2(Date date) {
        return getDate2Str("yyyy/MM/dd HH:mm", date);
    }

    /**
     * 将日期转换为短字符串（包含：年-月-日）
     *
     * @param date
     * 日期
     * @return 返回型如：yyyy-MM-dd 的字符串
     */
    public static String getDate2SStr(Date date) {
        return getDate2Str("yyyy-MM-dd", date);
    }

    /**
     * 将日期转换为短字符串（包含：年/月/日）
     *
     * @param date
     * 日期
     * @return 返回型如：yyyy/MM/dd 的字符串
     */
    public static String getDate2SStr2(Date date) {
        return getDate2Str("yyyy/MM/dd", date);
    }

    /**
     * 取得型如：yyyyMMddhhmmss的字符串
     *
     * @param date
     * @return 返回型如：yyyyMMddhhmmss 的字符串
     */
    public static String getDate2All(Date date) {
        return getDate2Str("yyyyMMddhhmmss", date);
    }

    /**
     * 将长整型数据转换为Date后，再转换为型如yyyy-MM-dd HH:mm:ss的长字符串
     *
     * @param l
     * 表示某日期的长整型数据
     * @return 日期型的字符串
     */
    public static String getLong2LStr(long l) {
        date = getLongToDate(l);
        return getDate2LStr(date);
    }

    /**
     * 将长整型数据转换为Date后，再转换为型如yyyy-MM-dd的长字符串
     *
     * @param l
     * 表示某日期的长整型数据
     * @return 日期型的字符串
     */
    public static String getLong2SStr(long l) {
        date = getLongToDate(l);
        return getDate2SStr(date);
    }

    /**
     * 将长整型数据转换为Date后，再转换指定格式的字符串
     *
     * @param l
     * 表示某日期的长整型数据
     * @param formatDate
     * 指定的日期格式
     * @return 日期型的字符串
     */
    public static String getLong2SStr(long l, String formatDate) {
        date = getLongToDate(l);
        simpleDateFormat.applyPattern(formatDate);
        return simpleDateFormat.format(date);
    }

    private static synchronized Date getStrToDate(String format, String str) {
        simpleDateFormat.applyPattern(format);
// ParsePosition parseposition = new ParsePosition(0);
        try {
            return simpleDateFormat.parse(str);
        } catch (ParseException e) {
            logger.error("---系统异常:", e);
        }
        return null;
    }

    /**
     * 将某指定的字符串转换为某类型的字符串
     *
     * @param format
     * 转换格式
     * @param str
     * 需要转换的字符串
     * @return 转换后的字符串
     */
    public static String getStr2Str(String format, String str) {
        Date date = getStrToDate(format, str);
        return getDate2Str(format, date);
    }

    /**
     * 将某指定的字符串转换为型如：yyyy-MM-dd HH:mm:ss的时间
     *
     * @param str
     * 将被转换为Date的字符串
     * @return 转换后的Date
     */
    public static Date getStr2LDate(String str) {
        return getStrToDate("yyyy-MM-dd HH:mm:ss", str);
    }

    /**
     * 取最小时间：yyyy-MM-dd HH:mm:ss的时间
     *
     * @param str
     * 将被转换为Date的字符串
     * @return 转换后的Date
     */
    public static Date getMinDate() {
        return getStrToDate("yyyy-MM-dd HH:mm:ss", "1900-01-01 00:00:00");
    }

    /**
     * 将某指定的字符串转换为型如：yyyy/MM/dd HH:mm:ss的时间
     *
     * @param str
     * 将被转换为Date的字符串
     * @return 转换后的Date
     */
    public static Date getStr2LDate2(String str) {
        return getStrToDate("yyyy/MM/dd HH:mm:ss", str);
    }

    /**
     * 将某指定的字符串转换为型如：yyyy-MM-dd HH:mm的时间
     *
     * @param str
     * 将被转换为Date的字符串
     * @return 转换后的Date
     */
    public static Date getStr2MDate(String str) {
        return getStrToDate("yyyy-MM-dd HH:mm", str);
    }

    /**
     * 将某指定的字符串转换为型如：yyyy/MM/dd HH:mm的时间
     *
     * @param str
     * 将被转换为Date的字符串
     * @return 转换后的Date
     */
    public static Date getStr2MDate2(String str) {
        return getStrToDate("yyyy/MM/dd HH:mm", str);
    }

    /**
     * 将某指定的字符串转换为型如：yyyy-MM-dd的时间
     *
     * @param str
     * 将被转换为Date的字符串
     * @return 转换后的Date
     */
    public static Date getStr2SDate(String str) {
        if (str != null && !"".equals(str)) {
            return getStrToDate("yyyy-MM-dd", str);
        }
        return null;
    }



    /**
     * 将某指定的字符串转换为型如：yyyy-MM-dd的时间
     *
     * @param str
     * 将被转换为Date的字符串
     * @return 转换后的Date
     */
    public static Date getStr2SDate2(String str) {
        return getStrToDate("yyyy/MM/dd", str);
    }

    /**
     * 将某长整型数据转换为日期
     *
     * @param l
     * 长整型数据
     * @return 转换后的日期
     */
    public static Date getLongToDate(long l) {
        return new Date(l);
    }

    /**
     * 以分钟的形式表示某长整型数据表示的时间到当前时间的间隔
     *
     * @param l
     * 长整型数据
     * @return 相隔的分钟数
     */
    public static int getOffMinutes(long l) {
        return getOffMinutes(l, getCurTimeMillis());
    }

    /**
     * 以分钟的形式表示两个长整型数表示的时间间隔
     *
     * @param from
     * 开始的长整型数据
     * @param to
     * 结束的长整型数据
     * @return 相隔的分钟数
     */
    public static int getOffMinutes(long from, long to) {
        return (int) ((to - from) / 60000L);
    }

/**
 * 以微秒的形式赋值给一个Calendar实例
 *
 * @param l
 * 用来表示时间的长整型数据
 */
// public static void setCalendar(long l) {
// CALENDAR.clear();
// CALENDAR.setTimeInMillis(l);
// }

/**
 * 以日期的形式赋值给某Calendar
 *
 * @param date
 * 指定日期
 */
// public static void setCalendar(Date date) {
// CALENDAR.clear();
// CALENDAR.setTime(date);
// }

    /**
     * 在此之前要由一个Calendar实例的存在
     *
     * @return 返回某年
     */
    public static int getYear() {
        return Calendar.getInstance().get(1);
    }

    /**
     * 在此之前要由一个Calendar实例的存在
     *
     * @return 返回某月
     */
    public static int getMonth() {
        return Calendar.getInstance().get(2) + 1;
    }

    /**
     * 在此之前要由一个Calendar实例的存在
     *
     * @return 返回某天
     */
    public static int getDay() {
        return Calendar.getInstance().get(5);
    }

    /**
     * 在此之前要由一个Calendar实例的存在
     *
     * @return 返回某小时
     */
    public static int getHour() {
        return Calendar.getInstance().get(11);
    }

    /**
     * 在此之前要由一个Calendar实例的存在
     *
     * @return 返回某分钟
     */
    public static int getMinute() {
        return Calendar.getInstance().get(12);
    }

    /**
     * 在此之前要由一个Calendar实例的存在
     *
     * @return 返回某秒
     */
    public static int getSecond() {
        return Calendar.getInstance().get(13);
    }

    @SuppressWarnings("deprecation")
    public static boolean stringIsDate(String strDate) {
        try {
            Date.parse(strDate);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /***
     * @Description: yyyy-MM-dd 获取年龄
     * @param birthDayStr
     * @return String
     */
    public static String getAgeByFormat006(String birthDayStr) {
        String age = "";
        try {
            if (birthDayStr != null && !"".equals(birthDayStr)) {
                age = getAge(getStr2SDate(birthDayStr));
            }
        } catch (Exception e) {
            logger.error("execute code occurred exception: ", e);
        }
        return age;
    }

    /***
     * @Description: 出生日期计算年龄
     * @param birthDay
     * @return
     * @throws Exception
     * String
     */
    public static String getAge(Date birthDay) throws Exception {
        Calendar cal = Calendar.getInstance();
        if (cal.before(birthDay)) {
            throw new IllegalArgumentException("The birthDay is before Now.It's unbelievable!");
        }
        int yearNow = cal.get(Calendar.YEAR);
        int monthNow = cal.get(Calendar.MONTH) + 1;
        int dayOfMonthNow = cal.get(Calendar.DAY_OF_MONTH);

        cal.setTime(birthDay);
        int yearBirth = cal.get(Calendar.YEAR);
        int monthBirth = cal.get(Calendar.MONTH);
        int dayOfMonthBirth = cal.get(Calendar.DAY_OF_MONTH);
        int age = yearNow - yearBirth;
        if (monthNow <= monthBirth) {
            if (monthNow == monthBirth) {
                if (dayOfMonthNow < dayOfMonthBirth) {
                    age--;
                }
            } else {
                age--;
            }
        }
        return age + "";
    }

    /**
     * 计算患者年龄,按实际年龄计算周岁
     *
     * @param dateOfBirth
     * @return
     */
    public static int getPatientAge(Date dateOfBirth) {
        int age = 0;
        Calendar born = Calendar.getInstance();
        Calendar now = Calendar.getInstance();
        if (dateOfBirth != null) {
            now.setTime(new Date());
            born.setTime(dateOfBirth);
            if (born.after(now)) {
                throw new IllegalArgumentException("Can't be born in the future");
            }
            age = now.get(Calendar.YEAR) - born.get(Calendar.YEAR);
            if (now.get(Calendar.DAY_OF_YEAR) < born.get(Calendar.DAY_OF_YEAR)) {
                age -= 1;
            }
        }
        return age;
    }

    /***
     * 对yyyy-M-d格式转换
     *
     * @Description: parseDateStr
     * @param dateStr
     * @return
     * @throws Exception
     * String
     */
    public static String parseDateStr(String dateStr) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat(DATE_FORMAT_006);
            Date date = sdf.parse(dateStr);
            String returnDate = sdf.format(date);
            return returnDate;
        } catch (Exception e) {
            logger.error("execute code occurred exception: ", e);
        }
        return "";
    }

    /**
     * 计算年龄(不足一个月算一个月,小于两岁的算月份,大于两岁的只算整岁)
     *
     * @param birthday
     * @param ageName_
     * @param monthName_
     * @return
     * @throws Exception
     */
    @Deprecated
    public static String getPersonAccurateAge(String birthday, String ageName_, String monthName_) throws Exception {
        String age = "";
      /*  birthday = StringUtil.trimObj(birthday);
        ageName_ = StringUtil.trimObj(ageName_);
        monthName_ = StringUtil.trimObj(monthName_);*/
        if (ageName_.equals("")) {
            ageName_ = "岁";
        }
        if (monthName_.equals("")) {
            monthName_ = "个月";
        }
        if (!birthday.equals("")) {
            SimpleDateFormat yMd = new SimpleDateFormat("yyyy-MM-dd");
            Date tempDate = yMd.parse(birthday);
            Date currentDate = yMd.parse(yMd.format(new Date()));
            if (tempDate.compareTo(currentDate) <= 0) {
//double months = (double) ((currentDate.getTime() - tempDate.getTime()) / (1000.0 * 60.0 * 60.0 * 24 * 30));
                int months = monthsBetween(tempDate, currentDate);
                int tempYears = (int) months / 12;
                int tempMonths = (int) months % 12;
                if (tempYears >= 0 && tempYears <= 2) {
                    if (tempMonths == 0 && months == 0) {
                        tempMonths = 1;
                    }
                    age = tempYears + ageName_ + tempMonths + monthName_;
                } else {
                    age = tempYears + ageName_;
                }
            } else {
                age = "0" + ageName_;
            }
        }
        return age;
    }


    /**
     * 计算两日期相差月份数
     * @param startDate
     * @param endDate
     * @return
     */
    public static int monthsBetween(Date startDate, Date endDate){
        int differMonth = 0;

        Calendar c1 = Calendar.getInstance();
        c1.setTime(startDate);

        int startDay = c1.get(Calendar.DAY_OF_MONTH);
        Calendar c2 = Calendar.getInstance();
        c2.setTime(endDate);
        while(c1.getTime().getTime() < c2.getTime().getTime()){
            c1.add(Calendar.MONTH, 1);
//////此处主要处理2月份的特殊情况，当递增一个月时，发现出身的天与递增后当月的最大天比较
            if(c1.getActualMaximum(Calendar.DAY_OF_MONTH) < startDay){
                c1.set(Calendar.DAY_OF_MONTH, c1.getActualMaximum(Calendar.DAY_OF_MONTH));
            }else{
                c1.set(Calendar.DAY_OF_MONTH, startDay);
            }
            if(c1.getTime().getTime() > c2.getTime().getTime()){
                break;
            }
            differMonth++;
        }
        return differMonth;
    }

    /**
     * 计算两个日期之间相差的天数
     *
     * @param smdate
     * 较小的时间
     * @param bdate
     * 较大的时间
     * @return 相差天数
     * @throws ParseException
     */
    public static int daysBetween(Date smdate, Date bdate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        smdate = sdf.parse(sdf.format(smdate));
        bdate = sdf.parse(sdf.format(bdate));
        Calendar cal = Calendar.getInstance();
        cal.setTime(smdate);
        long time1 = cal.getTimeInMillis();
        cal.setTime(bdate);
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);

        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     * 计算两个日期之间相差的天数
     *
     * @param smdate
     * 较小的时间
     * @param bdate
     * 较大的时间
     * @return 相差天数
     * @throws ParseException
     */
    public static int daysBetween(String smdate, String bdate) throws ParseException {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.setTime(sdf.parse(smdate));
        long time1 = cal.getTimeInMillis();
        cal.setTime(sdf.parse(bdate));
        long time2 = cal.getTimeInMillis();
        long between_days = (time2 - time1) / (1000 * 3600 * 24);

        return Integer.parseInt(String.valueOf(between_days));
    }

    /**
     * 获取指定日期所在月份的第一天和最后一天时间
     *
     * @Description: getFirstLastDayOfMonth
     * @param currentDate
     * @return Map key:firstDay, lastDay value:date yyyy-MM-dd 00:00:00
     * yyyy-MM-dd 23:59:59
     *
     */
    public static JSONObject getFirstLastDayOfMonth(Date currentDate) {
        JSONObject result = new JSONObject();

        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");

        Calendar cal = Calendar.getInstance();
        cal.setTime(currentDate);

// 获取前月的第一天
        cal.add(Calendar.MONTH, 0);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        result.put("firstDay", cal.getTime());
        result.put("firstDayStr", sdf.format(cal.getTime()));

        logger.debug("firstDayStr - " + result.get("firstDayStr"));

// 获取前月的最后一天
        cal = Calendar.getInstance();
        cal.add(Calendar.MONTH, 1);
        cal.set(Calendar.DAY_OF_MONTH, 0);
        cal.set(Calendar.HOUR, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);

        result.put("lastDay", cal.getTime());
        result.put("lastDayStr", sdf.format(cal.getTime()));

        logger.debug("lastDayStr - " + result.get("lastDayStr"));

        return result;
    }

    public static String getFirstDayOfMonth(Date currentDate) {
        simpleDateFormat.applyPattern(DATE_FORMAT_006);
        Calendar cal = Calendar.getInstance();
        cal.setTime(currentDate);

// 获取前月的第一天
        cal.add(Calendar.MONTH, 0);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);

        return simpleDateFormat.format(cal.getTime());
    }

    public static String getLastDayOfMonth(Date currentDate) {
        simpleDateFormat.applyPattern(DATE_FORMAT_006);
        Calendar cal = Calendar.getInstance();
        cal.setTime(currentDate);

// 获取前月的最后一天
        cal.set(Calendar.DATE, cal.getActualMaximum(Calendar.DATE));
        return simpleDateFormat.format(cal.getTime());
    }

    public static String getbeforeMonthOfDay(Date currentDate, int MONTH) {
        simpleDateFormat.applyPattern(DATE_FORMAT_010);
        Calendar cal = Calendar.getInstance();
        cal.setTime(currentDate);

// 获取前月的第一天
        cal.add(Calendar.MONTH, MONTH);
        cal.set(Calendar.DAY_OF_MONTH, 1);
        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);

        return simpleDateFormat.format(cal.getTime());
    }

    public static String getMonthOfYear(Date date) {
        simpleDateFormat.applyPattern(DATE_FORMAT_010);
        return simpleDateFormat.format(date);
    }

    /**
     * 去除时分秒
     *
     * @Description: truncateTimes
     * @param date
     * @return Date
     *
     */
    public static Date truncateTimes(Date date) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);

        cal.set(Calendar.HOUR, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);

        return cal.getTime();
    }

    /**
     * 是否为周末
     *
     * @Description: isWeekend
     * @param cal
     * @return boolean
     *
     */
    public static boolean isWeekend(Date dt) {
        Calendar cal = Calendar.getInstance();
        cal.setTime(dt);
        int week = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (week == 6 || week == 0) {// 0代表周日，6代表周六
            return true;
        }

        return false;
    }

    /**
     * 是否为周末
     *
     * @Description: isWeekend
     * @param dt
     * yyyy-MM-dd
     * @return boolean
     *
     */
    public static boolean isWeekend(String dt) {
        Date date = getStr2SDate(dt);
        if (date == null) {
            return false;
        }
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        int week = cal.get(Calendar.DAY_OF_WEEK) - 1;
        if (week == 6 || week == 0) {// 0代表周日，6代表周六
            return true;
        }

        return false;
    }

    /**
     * 获取指定日期区间的工作日记录集
     *
     * @Description: getWorkDays
     * @param startDate
     * @param endDate
     * @return List<String>
     *
     */
    public static List<String> getWorkDays(String startDate, String endDate) {
        List<String> list = checkDateScope(startDate, endDate);
        List<String> workDays = new ArrayList<>();

        for (String dtStr : list) {
            SimpleDateFormat smf = new SimpleDateFormat("yyyy-MM-dd");
            try {
                Date dt = smf.parse(dtStr);
                boolean isWeekend = DateUtils.isWeekend(dt);
                if (!isWeekend) {
                    workDays.add(dtStr);
                }
            } catch (ParseException e) {
                logger.error("execute code occurred exception: ", e);
            }
        }

        return workDays;
    }

    /****
     * 字符串转Date类型，带当前时分秒
     *
     * @Description: getStr2DateWithCurrentHMS
     * @param dateStr
     * @return Date
     *
     */
    public static Date getStr2DateWithCurrentHMS(String dateStr) {
        if (dateStr == null || "".equals(dateStr)) {
            return null;
        }
        try {
            String[] dates = dateStr.split("-");
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.YEAR, Integer.parseInt(dates[0]));
            calendar.set(Calendar.MONTH, Integer.parseInt(dates[1]) - 1);
            calendar.set(Calendar.DAY_OF_MONTH, Integer.parseInt(dates[2]));
            return calendar.getTime();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }


    /***
     * 判断日期是否为当天以前
     * @Description: isTodayBefore
     * @param dateStr
     * @return
     * boolean
     *
     */
    public static boolean isTodayBefore(String dateStr){
        String nowDateStr = DateUtils.getCurrentDataStr2();
        try {
            int flag = nowDateStr.compareTo(dateStr);
            if (flag > 0) { //大于0时，为当天以前
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }

    /***
     * 判断日期是否为当天或者以后
     * @Description: isTodayBefore
     * @param dateStr yyyy-mm-dd
     * @return
     * boolean
     *
     */
    public static boolean isTodayOrAfter(String dateStr){
        String nowDateStr = DateUtils.getCurrentDataStr2();
        try {
            int flag = nowDateStr.compareTo(dateStr);
            if (flag <= 0) { //小于0时，为当天以后
                return true;
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return false;
    }


    /**
     * 获取指定日期区间的 所有日期记录集
     *
     * @Description: checkDateScope
     * @param startDate
     * @param endDate
     * @return List<String>
     *
     */
    @SuppressWarnings("unused")
    public static List<String> checkDateScope(String startDate, String endDate) {
        List<String> dateList = new ArrayList<String>();
        if (StringUtils.isBlank(startDate) || StringUtils.isBlank(endDate) || endDate.compareTo(startDate) < 0) {
            return dateList;
        }

        if (startDate.equals(endDate)) {
            dateList.add(startDate);
            dateList.add(endDate);
        } else {
            dateList.add(startDate);
            try {
                SimpleDateFormat smf = new SimpleDateFormat("yyyy-MM-dd");
                Calendar cal = Calendar.getInstance();
                Date firstDate = smf.parse(startDate);
                Date lastDate = smf.parse(endDate);

                cal.setTime(firstDate);
                boolean flag = true;
                while (flag) {
                    cal.add(Calendar.DAY_OF_MONTH, 1);
                    String temp = smf.format(cal.getTime());
                    if (endDate.equals(temp)) {
                        dateList.add(endDate);
                        flag = false;
                    } else {
                        dateList.add(temp);
                    }
                }
            } catch (ParseException e) {
                logger.error("execute code occurred exception: ", e);
            }
        }

        return dateList;
    }

    public static String date2Str(Date date) {
        return date2string(date, DATE_FORMAT_006);
    }

    /**
     * 获取某天的开始时间
     */
    public static Date getDayBegin(Date date) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 0);
        cal.set(Calendar.MINUTE, 0);
        cal.set(Calendar.SECOND, 0);
        cal.set(Calendar.MILLISECOND, 0);
        return cal.getTime();
    }

    /**
     * 获取某天的结束时间
     */
    public static Date getDayEnd(Date date) {
        Calendar cal = new GregorianCalendar();
        cal.setTime(date);
        cal.set(Calendar.HOUR_OF_DAY, 23);
        cal.set(Calendar.MINUTE, 59);
        cal.set(Calendar.SECOND, 59);
        return cal.getTime();
    }

    /**
     * 判断日期是否在开始结束日期之内(用日期计算)
     *
     * @param beginDay
     * 开始日期
     * @param srcDay
     * 判断的日期
     * @param endDay
     * 结束日期
     * @return boolean
     */
    public static boolean isInDateRange(Date beginDay, Date srcDay, Date endDay) {
        Date begin = getDayBegin(beginDay);
        Date end = getDayEnd(endDay);
        return srcDay.after(begin) && srcDay.before(end);
    }

    /**
     * 判断当前日期是否在参数日期之前
     *
     * @param srcDate
     * 参数日期
     * @return boolean
     */
    public static boolean isBefore(Date srcDate) {
        Date begin = getDayBegin(srcDate);
        return (new Date()).before(begin);
    }

    /**
     * 格式化时间为(年.月.日)
     *
     * @param date
     * 时间
     * @return yyyy.mm.dd
     */
    public static String getYYYYMMDDPoint(Date date) {
        return getDate2Str(DATE_FORMAT_YYYYMMDD_POINT, date);
    }
    /**
     * 获取指定日期的月初和月末
     * @param datestr 时间字符串
     * @return startDate endDate
     * @throws ParseException
     */
    public static Map<String,Object> getBeginningMonthAndEndMonth(String datestr) throws ParseException{
        Map<String,Object>map=new HashMap<String,Object>();
        String beginStr = datestr+"-01";
        Date beginTo;
        Date endTo;
        beginTo = DateUtils.string2date(beginStr,DATE_FORMAT_006);
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(beginTo);
        calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
        endTo = calendar.getTime();
        map.put("startDate", beginTo);
        map.put("endDate", endTo);
        return map;
    }

    /**
     *
     * 获取指定增加或减少天数后的日期
     * @param dateTime 原始日期
     * @param diffDays 指定天数 可为负数
     * @return
     */
    public static Date addDays(Date dateTime, int diffDays){
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(dateTime);
        calendar.add(Calendar.DAY_OF_MONTH,diffDays);

        return calendar.getTime();
    }

    /**
     * 方法名称:calcAgeCommon
     * 方法描述: 年龄计算(参数第一个位置为 birthday，第二个位置为：visitday )
     * birthday --出生日期（yyyy-MM-dd)
     * visitday --就诊日期（yyyy-MM-dd) 默认为当天时间
     * @param 参数描述
     * 返回：患者当时就诊年龄
    年龄格式：
    1、大于等于5周岁的一律不展示月份，只展示岁数。
    2、小于5周岁的需展示月份（月份为空时无需展示）。
    3、小于1周岁的需展示到天，如：2月19天(包含患者登记列表、首页、今日就诊卡片、今日就诊顶部栏目、今日就诊患者信息、健康档案患者信息、收费卡片)
    4、年龄未填的，一律展示为“-”。
    备注：
    0岁3月20天，就展示为3月30天即可。
    0岁0月19天这种，还是需要展示月份的，展示为0月19天。
    2岁0月
     * @return 返回值描述
     * 创建人：HELIANG349
     * 创建时间：2018 M08 17 13:40:05
     */
    public static String calcAgeCommon(String ...args){
        if(null == args || args.length == 0){
            return "-";
        }
//年龄为空显示-
        if(null == args[0] || "".equals(args[0])){
            return "-";
        }
        DateTimeFormatter dateTimeFormatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String birthDayDateStr = args[0];
//如果只传入的一个参数，就诊时间默认为当天
        String visitdayStr = "";
        LocalDate birthDayDate = null;
        LocalDate visitday = null;
        try {
            if(args.length == 1){
                visitdayStr = LocalDate.now().format(dateTimeFormatter);
            }else{
                visitdayStr = args[1];
            }
//出生日期
            birthDayDate = LocalDate.parse(birthDayDateStr);
//就诊日期
            visitday = LocalDate.parse(visitdayStr);
        } catch (Exception e) {
            logger.error("传入时间格式有误解析失败 args："+args, e);
            throw e;
        }
        Period p = Period.between(birthDayDate, visitday);
//大于等于5周岁的一律不展示月份，只展示岁数
        if(p.getYears() >= 5){
            return p.getYears()+"岁";
        }
//大于等于一岁，小于5周岁显示到月份，且0月显示
        if(p.getYears() >= 1 && p.getYears() < 5){
            return p.getYears()+"岁"+p.getMonths()+"月";
        }
//小于一岁显示到天数 ，且0月显示
        if(p.getYears() < 1){
            return p.getMonths()+"月"+p.getDays()+"天";
        }
        return "";
    }

    public static void main(String[] args) {
//出生日期
        System.out.println(DateUtils.calcAgeCommon("2011-01-16","2018-02-05"));
    }


    /**
     * 组装成新的时间(取传入时间的日期(yyyy-mm-dd) +当前时间的时分秒(hh:mi:ss))
     *
     * @param src
     * 取该时间的日期(yyyy-mm-dd)
     * @return date
     */
    public static Date makeupDateUseCurTime(Date src) {
        if (src == null) {
            return new Date();
        }
        String yyyymmddStr = getYYYYMMDDStr(src);
        String hhmmssStr = getHHMMSSStr(new Date());
        return getStr2LDate(yyyymmddStr + " " + hhmmssStr);
    }

    public static String getYYYYMMDDStr(Date date) {
        return getDate2Str(DATE_FORMAT_006, date);
    }

    public static String getHHMMSSStr(Date date) {
        return getDate2Str(DATE_FORMAT_008, date);
    }

    /**
     * 日期格式转换yyyy-MM-dd‘T‘HH:mm:ss.SSSXXX  TO  yyyy-MM-dd HH:mm:ss
     * @throws ParseException
     */
    public static String dealDateFormat(String oldDateStr) throws ParseException{
        DateFormat df = new SimpleDateFormat("yyyy-MM-dd‘T‘HH:mm:ss.SSSXXX");
        Date  date = df.parse(oldDateStr);
        SimpleDateFormat df1 = new SimpleDateFormat ("EEE MMM dd HH:mm:ss Z yyyy", Locale.UK);
        Date date1 =  df1.parse(date.toString());
        DateFormat df2 = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
//  Date date3 =  df2.parse(date1.toString());
        return df2.format(date1);
    }

    /**
     * 将长时间格式字符串转换为时间 yyyy-MM-dd HH:mm:ss
     * @param strDate
     * @return
     */
    public static Date strToDateLong(String strDate) {
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        ParsePosition pos = new ParsePosition(0);
        Date strtodate = formatter.parse(strDate, pos);
        return strtodate;
    }
}
