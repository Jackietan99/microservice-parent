package com.jsfd.core.utils;

import java.sql.Date;
import java.sql.Timestamp;
import java.text.DateFormat;
import java.text.ParseException;
import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

/**
 * 时间工具类
 */
public abstract class DateUtils {
	private DateUtils(){}//禁止实例化
	// 各种时间格式
	public static final SimpleDateFormat date_sdf = new SimpleDateFormat("yyyy-MM-dd");
	// 各种时间格式
	public static final SimpleDateFormat yyyyMMdd = new SimpleDateFormat("yyyyMMdd");
	// 各种时间格式
	public static final SimpleDateFormat date_sdf_wz = new SimpleDateFormat("yyyy年MM月dd日");
	public static final SimpleDateFormat time_sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm");
	public static final SimpleDateFormat yyyymmddhhmmss = new SimpleDateFormat("yyyyMMddHHmmss");
	public static final SimpleDateFormat short_time_sdf = new SimpleDateFormat("HH:mm");
	public static final SimpleDateFormat datetimeFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	// 以毫秒表示的时间
	private static final long DAY_IN_MILLIS = 24 * 3600 * 1000;
	private static final long HOUR_IN_MILLIS = 3600 * 1000;
	private static final long MINUTE_IN_MILLIS = 60 * 1000;
	private static final long SECOND_IN_MILLIS = 1000;
	
	
	//@param mode 获取模式: 日("day")、周("week")、月("month")("0")、季("season")、年("year")("1")
	public static final String DATEMODE_DAY = "day";
	public static final String DATEMODE_WEEK = "week";
	public static final String DATEMODE_MONTH = "month";
	public static final String DATEMODE_SEASON = "season";
	public static final String DATEMODE_YEAR = "year";
	
	// 得到星座
	private final static int[] dayArr = new int[] { 20, 19, 21, 20, 21, 22, 23, 23, 23, 24, 23, 22 };  
	private final static String[] constellationArr = new String[] { "摩羯座", "水瓶座", "双鱼座", "白羊座", "金牛座", "双子座", "巨蟹座", "狮子座", "处女座", "天秤座", "天蝎座", "射手座", "摩羯座" };  
	
	// 指定模式的时间格式
	public static SimpleDateFormat getSDFormat(String pattern) {
		return new SimpleDateFormat(pattern);
	}
	
	/**
	 * 当前日历，这里用中国时间表示
	 * 
	 * @return 以当地时区表示的系统当前日历
	 */
	public static Calendar getCalendar() {
		return Calendar.getInstance();
	}
	/**
	 * 指定毫秒数表示的日历
	 * 
	 * @param millis
	 *            毫秒数
	 * @return 指定毫秒数表示的日历
	 */
	public static Calendar getCalendar(long millis) {
		Calendar cal = Calendar.getInstance();
		// --------------------cal.setTimeInMillis(millis);
		cal.setTime(new Date(millis));
		return cal;
	}
	/**
	 * 当前日期
	 * 
	 * @return 系统当前时间
	 */
	public static java.util.Date getDate() {
		return new java.util.Date();
	}

	/**
	 * 指定毫秒数表示的日期
	 * 
	 * @param millis
	 *            毫秒数
	 * @return 指定毫秒数表示的日期
	 */
	public static Date getDate(long millis) {
		return new Date(millis);
	}
	/**
	 * 指定毫秒数的时间戳
	 * 
	 * @param millis
	 *            毫秒数
	 * @return 指定毫秒数的时间戳
	 */
	public static Timestamp getTimestamp(long millis) {
		return new Timestamp(millis);
	}
	
	/**
	 * 时间戳转换为字符串
	 * 
	 * @param time
	 * @return
	 */
	public static String format(Timestamp time) {
		java.util.Date date = null;
		if (null != time) {
			date = new Date(time.getTime());
		}
		return date_sdf.format(date);
	}
	/**
	 * 字符串转换时间戳
	 * 
	 * @param str
	 * @return
	 */
	public static Timestamp str2Timestamp(String str) {
		java.util.Date date = parse(str);
		return new Timestamp(date.getTime());
	}
	/**
	 * 以字符形式表示的时间戳
	 * 
	 * @param time
	 *            毫秒数
	 * @return 以字符形式表示的时间戳
	 */
	public static Timestamp getTimestamp(String time) {
		return new Timestamp(Long.parseLong(time));
	}

	/**
	 * 系统当前的时间戳
	 * 
	 * @return 系统当前的时间戳
	 */
	public static Timestamp getTimestamp() {
		return new Timestamp(new java.util.Date().getTime());
	}

	/**
	 * 指定日期的时间戳
	 * 
	 * @param date
	 *            指定日期
	 * @return 指定日期的时间戳
	 */
	public static Timestamp getTimestamp(java.util.Date date) {
		return new Timestamp(date.getTime());
	}

	/**
	 * 指定日历的时间戳
	 * 
	 * @param cal
	 *            指定日历
	 * @return 指定日历的时间戳
	 */
	public static Timestamp getCalendarTimestamp(Calendar cal) {
		// ---------------------return new Timestamp(cal.getTimeInMillis());
		return new Timestamp(cal.getTime().getTime());
	}

	public static Timestamp gettimestamp() {
		java.util.Date dt = new java.util.Date();
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String nowTime = df.format(dt);
		Timestamp buydate = Timestamp.valueOf(nowTime);
		return buydate;
	}
	/**
	 * 将java.util.Date转换为Calendar类型
	 * 
	 * @param date
	 * @return Calendar
	 */
	public static Calendar toCalendar(java.util.Date date) {
		if (date == null) {
			return null;
		} else {
			Calendar calendar = Calendar.getInstance();
			calendar.setTime(date);
			return calendar;
		}
	}

	/**
	 * 将Calendar转换为java.util.Date类型
	 * 
	 * @param date
	 * @return java.util.Date
	 */
	public static java.util.Date toUtilDate(Calendar calendar) {
		if (calendar == null) {
			return null;
		} else {
			return calendar.getTime();
		}
	}
	/**
	 * 将java.util.Date转换为java.sql.Date.Date类型
	 * 
	 * @param date
	 * @return
	 */
	public static Date toSqlDate(java.util.Date date) {
		if (date == null) {
			return null;
		} else {
			return new Date(date.getTime());
		}
	}
	/**
	 * 将java.sql.Date.Date转换为java.util.Date类型
	 * 
	 * @param date
	 * @return
	 */
	public static java.util.Date toUtilDate(Date date) {
		if (date == null) {
			return null;
		} else {
			return new java.util.Date(date.getTime());
		}
	}

	/**
	 * 将日期格式字符串按指定格式转换为java.sql.Date时间类型
	 * 
	 * @param str
	 *            字符串
	 * @param format
	 *            指定格式
	 * @return java.sql.Date
	 */
	public static Date parseSqlDate(String str, String format) {
		if (str == null || format == null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		java.util.Date date = null;
		try {
			date = sdf.parse(str);
		} catch (Exception e) {
			return null;
		}
		return new Date(date.getTime());
	}

	/**
	 * 将日期格式字符串按指定格式转换为java.sql.Timestamp时间类型
	 * 
	 * @param str
	 *            字符串
	 * @param format
	 *            指定格式
	 * @return java.sql.Timestamp
	 */
	public static Timestamp parseSqlTimestamp(String str, String format) {
		if (str == null || format == null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		java.util.Date date = null;
		try {
			date = sdf.parse(str);
		} catch (Exception e) {
			return null;
		}
		return new Timestamp(date.getTime());
	}

	/**
	 * 判断一个日期字符串是否符合日期格式yyyy-MM-dd HH:mm:ss
	 * 
	 * @param args
	 */
	public static boolean isDateFormat(String strDate) {
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		try {
			sdf.format(sdf.parse(strDate));
			return true;
		} catch (ParseException e) {
			return false;
		}
	}
	/**
	 * 按默认格式解析时间形式字符串,默认格式yyyy-MM-dd HH:mm:ss
	 * @param str 字符串格式必须与默认格式严格相同，
	 * 如： 默认格式是 yyyy-MM-dd HH:mm:ss
	 *     则字符串格式必须是2001-01-01 23:15:46,否则解析失败,返回null
	 * 
	 * 
	 * 根据字符串格式使用 parseBy(String str, String format);解析
	 * @return 
	 */
	public static java.util.Date parse(String str) {
		//yyyy-MM-dd HH:mm:ss
		return parseBy(str, "yyyy-MM-dd HH:mm:ss");
	}
	/**
	 * 按默认格式格式化时间,默认格式yyyy-MM-dd
	 * @param date
	 * @return
	 */
	public static String format(java.util.Date date) {
		//按yyyy-MM-dd HH:mm:ss格式化时间
		//return formatBy(date,"yyyy-MM-dd HH:mm:ss");
		//yyyy-MM-dd  
		return DateFormat.getDateInstance().format(date);//DateFormat.SHORT
	}

	/**
	 * 
	 * 按指定的format格式格式化时间
	 * 
	 * @param date
	 * @param pattern
	 *  "yyyy年MM月dd日" 
	 *  "yyyy-MM-dd" 
	 *  "yyyy/MM/dd" 
	 *  "yyyy-MM-dd HH:mm:ss" 
	 *  例：
	 *   DateUtil.formatBy(new Date(), "MM")取两位数月 
	 *   DateUtil.formatBy(new Date(), "dd")取两位数日
	 * @return
	 */
	public static String format(java.util.Date date, String pattern) {
		/*
		if (null == pattern) {
			pattern = "yyyy-MM-dd HH:mm:ss";
		}
		if (date == null) {
			date = new java.util.Date();
			date.setTime(0);
		}
		*/
		if (date == null || pattern == null) {
			return null;
		} else {
			SimpleDateFormat sdf = new SimpleDateFormat(pattern);
			return sdf.format(date);
		}
	}

	/**
	 * 按指定的format格式解析时间形式字符串
	 * 
	 * @param str
	 *            日期形式字符串
	 * @param format
	 *            指定时间格式
	 * 
	 * @return java.util.Date 时间类型
	 */
	public static java.util.Date parseBy(String str, String format) {
		if (str == null || format == null) {
			return null;
		}
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		java.util.Date date = null;
		try {
			ParsePosition pos = new ParsePosition(0);
			date = sdf.parse(str,pos);
			//date = sdf.parse(str);
		} catch (Exception exception) {
			
		}
		return date;
	}

	/**
	 * 格式化为中文形式字符串
	 * @param date
	 * @return
	 */
	public static String formatChina(java.util.Date date) {
		String result = "";
		SimpleDateFormat format = new SimpleDateFormat("yyyy年MM月dd日");
		result = format.format(date);
		return result;
	}
	/**
	 * 解析时间形式字符串为中文形式字符串
	 * @param date
	 * @return
	 */
	public static String parseChina(String str) {
		java.util.Date date = parseBy(str,"yyyy-MM-dd");
		String result = "";
		result = formatChina(date);

		return result;
	}
	/**
	 * 将日期格式字符串转换为时间格式 yyyy-MM-dd字符串
	 * 
	 * @param strDate  日期格式字符串
	 * @return String 时间格式yyyy-MM-dd字符串
	 */
	public static String parseShort(String strDate) {
		return format(parseBy(strDate, "yyyy-MM-dd"), "yyyy-MM-dd");
	}
	/**
	 * 格式化Calendar为yyyy-MM-dd格式字符串，
	 * @param Calendar
	 *            c
	 * @return 返回 yyyy-MM-dd形式字符串
	 */
	public static String format(Calendar calendar) {
		return (new StringBuilder(String.valueOf(calendar.get(Calendar.YEAR)))).append("-").append(getMonthFormat(calendar)).append("-").append(getDayFormat(calendar)).toString();
	}

	/**
	 * 格式化Calendar为yyyy年MM月dd日格式字符串，
	 * 
	 * @param Calendar
	 *            calendar
	 * @return 返回 yyyy年MM月dd日形式字符串
	 */
	public static String formatToChina(Calendar calendar) {
		return (new StringBuilder(String.valueOf(calendar.get(Calendar.YEAR)))).append("年").append(getMonthFormat(calendar)).append("月").append(getDayFormat(calendar)).append("日").toString();
	}

	/**
	 * 格式化Calendar为yyyy/MM/dd格式字符串， 格式化时间的格式，
	 * 
	 * @param Calendar
	 *            c
	 * @return 返回 yyyy/MM/dd形式字符串
	 */
	public static String formatCalen(Calendar c) {
		return (new StringBuilder(String.valueOf(c.get(Calendar.YEAR)))).append("/").append(getMonthFormat(c)).append("/").append(getDayFormat(c)).toString();
	}
	/**
	 * 取年
	 * @param calendar
	 * @return int
	 */
	public static int getYear(Calendar calendar) {
		return calendar.get(Calendar.YEAR);
	}
	/**
	 * 取年
	 * @param calendar
	 * @return int
	 */
	public static int getYear(java.util.Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return getYear(calendar);
	}

	/**
	 * 取年的天数
	 * 
	 * @param calendar
	 * @return int
	 */
	public static int getYearMaxDay(Calendar calendar) {
		return calendar.getActualMaximum(Calendar.DAY_OF_YEAR);
	}
	/**
	 * 取年的天数
	 * 
	 * @param calendar
	 * @return int
	 */
	public static int getYearMaxDay(java.util.Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return getYearMaxDay(calendar);
	}

	/**
	 * 取格式化为二位月
	 */
	public static String getMonthFormat(Calendar c) {
		String month = String.valueOf(c.get(Calendar.MONTH) + 1);
		if (month.length() != 2) {
			month = (new StringBuilder("0")).append(month).toString();
		}
		return month;
	}
	/**
	 * 取格式化为二位月
	 */
	public static String getMonthFormat(java.util.Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return getMonthFormat(calendar);
	}
	/**
	 * 取月
	 */
	public static int getMonth(Calendar calendar) {
		return calendar.get(Calendar.MONTH) + 1;
	}
	/**
	 * 取月
	 */
	public static int getMonth(java.util.Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return getMonth(calendar);
	}

	/**
	 * 取月最后一天(月最大天数)
	 * 
	 * @param calendar
	 * @return
	 */
	public static int getMonthMaxDay(Calendar calendar) {
		return calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 取月第一天(月最小天数)
	 * 
	 * @param calendar
	 * @return
	 */
	public static int getMonthMinDay(Calendar calendar) {
		return calendar.getActualMinimum(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 取月第一天的日期时间 
	 */
	public static java.util.Date getMonthFirstDate(Calendar calendar) {
		// 设定日期，取当前月最少天数作为第一天
		Integer minDateNum = calendar.getActualMinimum(Calendar.DAY_OF_MONTH);
		calendar.set(Calendar.DAY_OF_MONTH, minDateNum);
		// 设定小时、分钟、秒
		calendar.set(Calendar.HOUR, calendar.getActualMinimum(Calendar.HOUR));
		calendar.set(Calendar.AM_PM, Calendar.AM);
		calendar.set(Calendar.MINUTE, calendar.getActualMinimum(Calendar.MINUTE));
		calendar.set(Calendar.SECOND, calendar.getActualMinimum(Calendar.SECOND));
		//return formatBy(calendar.getTime(), "yyyy-MM-dd HH:mm:ss");
		return calendar.getTime();
	}
	/**
	 * 取月第一天的日期时间
	 * 
	 */
	public static java.util.Date getMonthFirstDateBy(java.util.Date date) {
		if (date == null) {
			date = new java.util.Date();
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return getMonthFirstDate(c);
	}
	/**
	 * 取月第一天的日期时间,指定返回格式
	 * 
	 */
	public static String getMonthFirstDateBy(java.util.Date date,String format) {
		if (date == null) {
			date = new java.util.Date();
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return format(getMonthFirstDate(c),format);
	}
	/**
	 * 取月第一天的日期时间 yyyy-MM-dd HH:mm:ss
	 */
	public static String getMonthFirstDate(java.util.Date date) {
		return getMonthFirstDateBy(date,"yyyy-MM-dd HH:mm:ss");
	}
	/**
	 * 取月最后一天的日期时间 yyyy-MM-dd HH:mm:ss
	 */
	public static java.util.Date getMonthLastDate(Calendar calendar) {
		// 设定日期,取当前月最大天数作为最后一天
		Integer maxDateNum = calendar.getActualMaximum(Calendar.DAY_OF_MONTH);
		calendar.set(Calendar.DAY_OF_MONTH, maxDateNum);
		// 设定小时、分钟、秒
		calendar.set(Calendar.HOUR, calendar.getActualMaximum(Calendar.HOUR));
		calendar.set(Calendar.AM_PM, Calendar.PM);
		calendar.set(Calendar.MINUTE, calendar.getActualMaximum(Calendar.MINUTE));
		calendar.set(Calendar.SECOND, calendar.getActualMaximum(Calendar.SECOND));
		return calendar.getTime();
	}
	/**
	 * 获得月最后一天的日期时间
	 */
	public static java.util.Date getMonthLastDateBy(java.util.Date date) {
		if (date == null) {
			date = new java.util.Date();
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return getMonthLastDate(c);
	}
	/**
	 * 获得月最后一天的日期时间 ,指定返回格式
	 */
	public static String getMonthLastDateBy(java.util.Date date,String format) {
		if (date == null) {
			date = new java.util.Date();
		}
		Calendar c = Calendar.getInstance();
		c.setTime(date);
		return format(getMonthLastDate(c),format);
	}
	/**
	 * 获得月最后一天的日期时间 yyyy-MM-dd HH:mm:ss
	 */
	public static String getMonthLastDate(java.util.Date date) {
		return getMonthLastDateBy(date,"yyyy-MM-dd HH:mm:ss");
	}

	/**
	 * 取日
	 */
	public static int getDay(Calendar calendar) {
		return calendar.get(Calendar.DAY_OF_MONTH);
	}

	/**
	 * 取日
	 */
	public static int getDay(java.util.Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return getDay(calendar);
	}

	/**
	 * 取格式化为二位日
	 */
	public static String getDayFormat(Calendar c) {
		String day = String.valueOf(c.get(Calendar.DAY_OF_MONTH));
		if (day.length() != 2) {
			day = (new StringBuilder("0")).append(day).toString();
		}
		return day;
	}

	/**
	 * 取格式化为二位日
	 */
	public static String getFormatDay(java.util.Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return getDayFormat(calendar);
	}

	/**
	 * 取时
	 */
	public static int getHour(Calendar calendar) {
		return calendar.get(Calendar.HOUR_OF_DAY);
	}

	/**
	 * 取时
	 */
	public static int getHour(java.util.Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return getHour(calendar);
	}

	/**
	 * 取格式化为二位时
	 */
	public static String getFormatHour(Calendar calendar) {
		String hour = String.valueOf(calendar.get(Calendar.HOUR_OF_DAY));
		if (hour.length() != 2) {
			hour = (new StringBuilder("0")).append(hour).toString();
		}
		return hour;
	}

	/**
	 * 取格式化为二位时
	 */
	public static String getFormatHour(java.util.Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return getFormatHour(calendar);
	}

	/**
	 * 取分
	 */
	public static int getMinute(Calendar calendar) {
		return calendar.get(Calendar.MINUTE);
	}

	/**
	 * 取分
	 */
	public static int getMinute(java.util.Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return getMinute(calendar);
	}

	/**
	 * 取格式化为二位分
	 */
	public static String getFormatMinute(Calendar calendar) {
		String minute = String.valueOf(calendar.get(Calendar.MINUTE));
		if (minute.length() != 2) {
			minute = (new StringBuilder("0")).append(minute).toString();
		}
		return minute;
	}

	/**
	 * 取格式化为二位分
	 */
	public static String getFormatMinute(java.util.Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return getFormatMinute(calendar);
	}

	/**
	 * 取秒
	 */
	public static int getSecond(Calendar calendar) {
		return calendar.get(Calendar.SECOND);
	}

	/**
	 * 取秒
	 */
	public static int getSecond(java.util.Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return getSecond(calendar);
	}

	/**
	 * 取格式化为二位秒
	 */
	public static String getFormatSecond(Calendar calendar) {
		String second = String.valueOf(calendar.get(Calendar.SECOND));
		if (second.length() != 2) {
			second = (new StringBuilder("0")).append(second).toString();
		}
		return second;
	}

	/**
	 * 取格式化为二位秒
	 */
	public static String getFormatSecond(java.util.Date date) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		return getFormatSecond(calendar);
	}

	/**
	 * 增加天数
	 * 
	 * @param date
	 * @param day
	 * @return
	 */
	public static java.util.Date addDay(java.util.Date date, int day) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.DAY_OF_MONTH, day);
		return cal.getTime();
	}
	/**
	 * 增加月数
	 * 
	 * @param date
	 * @param month
	 * @return
	 */
	public static java.util.Date addMONTH(java.util.Date date, int month) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.MONTH, month);
		return cal.getTime();
	}
	/**
	 * 增加年
	 * 
	 * @param date
	 * @param year
	 * @return
	 */
	public static java.util.Date addYear(java.util.Date date, int year) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.YEAR, year);
		return cal.getTime();
	}
	/**
	 * 按指定类型取当前指定类型时间
	 * 
	 * @param type
	 *            指定类型： type = 1 取年 Calendar.YEAR=1； type = 2 取月 Calendar.MONTH=2; type = 3 取日 Calendar.DAY_OF_MONTH=5
	 * @return int 指定类型时间
	 */
	public static int getCurTime(int type) {
		if (type == 1) {
			return Calendar.getInstance().get(Calendar.YEAR);
		}
		if (type == 2) {
			return Calendar.getInstance().get(Calendar.MONTH) + 1;
		}
		if (type == 3) {
			return Calendar.getInstance().get(Calendar.DAY_OF_MONTH);
		} else {
			return 0;
		}
	}

	/**
	 * 返回两个日期之间的所有天数的日期
	 * @param begin
	 * @param end
	 * @return
	 */
	public static List<java.util.Date> getDate(java.util.Date begin, java.util.Date end) {
		List<java.util.Date> result = new ArrayList<java.util.Date>();
		while (begin.before(end)) {
			Calendar c = Calendar.getInstance();
			c.setTime(begin);
			result.add(c.getTime());
			begin = addDay(begin, 1);
			/*
			result.add(new java.util.Date(begin.getYear(), begin.getMonth(), begin.getDate()));
			begin.setDate(begin.getDate() + 1);
			*/
		}
		//result.add(end);
		return result;
	}

	/**
	 * 根据两个日期获得之间所有的工作日期返回一个日期数组 (工作日不包括星期六和星期日)
	 * 
	 * @param begin
	 *            开始日期
	 * @param end
	 *            结束日期
	 * @return List<Date> 存放日期的数组
	 */
	public static List<java.util.Date> getDateWeek(java.util.Date begin, java.util.Date end) {
		if (null == begin) {
			begin = new java.util.Date();
		}
		if (null == end) {
			end = new java.util.Date();
		}
		List<java.util.Date> list = DateUtils.getDate(begin, end);
		List<java.util.Date> result = new ArrayList<java.util.Date>();
		Calendar calendar = Calendar.getInstance();
		for (int i = 0; i < list.size(); i++) {
			calendar.setTime(list.get(i));
			if (calendar.get(Calendar.DAY_OF_WEEK) == 1 || calendar.get(Calendar.DAY_OF_WEEK) == 7) {
				continue;
			} else {
				result.add(list.get(i));
			}
		}
		return result;
	}

	/**
	 * 根据日期类型的数组获得数组里所有日期的星期
	 * 
	 * @param list
	 *            日期类型的数组
	 * @return 返回字符串数组
	 */
	public static List<String> getDateWeeks(List<java.util.Date> list) {
		List<String> result = new ArrayList<String>();
		Calendar cal = Calendar.getInstance();
		for (int i = 0; i < list.size(); i++) {
			cal.setTime(list.get(i));
			switch (cal.get(Calendar.DAY_OF_WEEK)) {
			case 1:
				result.add("星期天");
				break;
			case 2:
				result.add("星期一");
				break;
			case 3:
				result.add("星期二");
				break;
			case 4:
				result.add("星期三");
				break;
			case 5:
				result.add("星期四");
				break;
			case 6:
				result.add("星期五");
				break;
			case 7:
				result.add("星期六");
				break;
			default:
				break;
			}
		}
		return result;
	}
	/**
	 * 指定日历的毫秒数
	 * 
	 * @param cal
	 *            指定日历
	 * @return 指定日历的毫秒数
	 */
	public static long getMillis(Calendar cal) {
		// --------------------return cal.getTimeInMillis();
		return cal.getTime().getTime();
	}
	/**
	 * 计算两个时间之间的差值，根据标志的不同而不同
	 * 
	 * @param flag
	 *            计算标志，表示按照年/月/日/时/分/秒等计算
	 * @param calSrc
	 *            减数
	 * @param calDes
	 *            被减数
	 * @return 两个日期之间的差值
	 */
	public static int dateDiff(char flag, Calendar calSrc, Calendar calDes) {

		long millisDiff = getMillis(calSrc) - getMillis(calDes);

		if (flag == 'y') {
			return (calSrc.get(Calendar.YEAR) - calDes.get(Calendar.YEAR));
		}

		if (flag == 'd') {
			return (int) (millisDiff / DAY_IN_MILLIS);
		}

		if (flag == 'h') {
			return (int) (millisDiff / HOUR_IN_MILLIS);
		}

		if (flag == 'm') {
			return (int) (millisDiff / MINUTE_IN_MILLIS);
		}

		if (flag == 's') {
			return (int) (millisDiff / SECOND_IN_MILLIS);
		}

		return 0;
	}

	/**
	 * 取两时间点之间日期毫秒差
	 * 
	 * @param date1
	 * @param date2
	 * @return date1-date2的时间毫秒差
	 */
	public static long getDateDifference(java.util.Date date1, java.util.Date date2) {
		if (date1 != null && date2 != null) {
			Calendar c1 = toCalendar(date1);
			Calendar c2 = toCalendar(date2);
			return c1.getTimeInMillis() - c2.getTimeInMillis();
		} else {
			return 0L;
		}
	}

	/**
	 * 取两时间点之间日期天数差
	 * 
	 * @param date
	 * @param date2
	 * @return
	 */
	public static int daysBetween(java.util.Date date1, java.util.Date date2) {
		/*
		 * SimpleDateFormat myFormatter = new SimpleDateFormat("yyyy-MM-dd"); java.util.Date date = myFormatter.parse("2003-05-1"); java.util.Date mydate = myFormatter.parse("1899-12-30"); long day = (date.getTime() - mydate.getTime()) / (24 * 60 * 60 * 1000); System.out.println(day);
		 */
		/*
		 * Calendar cNow = Calendar.getInstance(); Calendar cReturnDate = Calendar.getInstance(); cNow.setTime(date1); cNow.set(Calendar.HOUR_OF_DAY, 0); cNow.set(Calendar.MINUTE, 0); cNow.set(Calendar.SECOND, 0);
		 * 
		 * cReturnDate.setTime(date2); cReturnDate.set(Calendar.HOUR_OF_DAY, 0); cReturnDate.set(Calendar.MINUTE, 0); cReturnDate.set(Calendar.SECOND, 0);
		 * 
		 * long todayMs = cNow.getTimeInMillis(); long returnMs = cReturnDate.getTimeInMillis(); long intervalMs = todayMs - returnMs; return (int) (intervalMs / 86400000);// (1000 * 86400)
		 */
		// return date1.getTime() / (24*60*60*1000) - date2.getTime() / (24*60*60*1000);
		return (int) (date1.getTime() / 86400000 - date2.getTime() / 86400000); // 用立即数，减少乘法计算的开销
	}

	/**
	 * 按指定的时间域，转换value为对应时间域的值
	 * 
	 * @param value
	 * @param field
	 * @return
	 */
	public static double getDateValue(long value, int field) {
		if (field == Calendar.SECOND) {
			return (new Double(value)).doubleValue() / 1000D;
		}
		if (field == Calendar.MINUTE) {
			return (new Double(value)).doubleValue() / 60000D;
		}
		if (field == Calendar.HOUR) {
			return (new Double(value)).doubleValue() / 3600000D;
		}
		if (field == Calendar.DAY_OF_MONTH) {
			return (new Double(value)).doubleValue() / 86400000D;
		}
		if (field == Calendar.MONTH) {
			return (new Double(value)).doubleValue() / -1616567296D;
		}
		if (field == Calendar.YEAR) {
			return (new Double(value)).doubleValue() / -1636543488D;
		} else {
			return 0.0D;
		}
	}

	public static ArrayList<Calendar> getTimeQuantum(java.util.Date startDate, java.util.Date endDate) {
		ArrayList<Calendar> calendars = new ArrayList<Calendar>();
		try {
			long l = getDateDifference(startDate, endDate);
			int day = Double.valueOf(getDateValue(l, 5)).intValue();
			Calendar startCalendar = toCalendar(startDate);
			int today = startCalendar.get(5);
			for (int i = 0; i < day; i++) {
				Calendar calendar = Calendar.getInstance();
				calendar.setTime(startDate);
				calendar.set(5, today);
				calendars.add(calendar);
				today++;
			}

		} catch (Exception e) {
			e.printStackTrace();
		}
		return calendars;
	}

	public static java.util.Date modifyDate(java.util.Date date, int year, int month, int day, int hour, int min, int sec) {
		Calendar cal = Calendar.getInstance();
		cal.setTime(date);
		cal.add(Calendar.YEAR, year);
		cal.add(Calendar.MONTH, month);
		cal.add(Calendar.DAY_OF_MONTH, day);
		cal.add(Calendar.HOUR, hour);
		cal.add(Calendar.MINUTE, min);
		cal.add(Calendar.SECOND, sec);
		return cal.getTime();
	}
	/**
	 * 得当前的长时间字符串
	 */
	public static String getCurrentLongDate() {
		Calendar calendar = Calendar.getInstance();
		return datetimeFormat.format(calendar.getTime());
	}
	/**
	 * 根据系统当前时间，生成yyyyMMddHHmmss格式字符串，
	 * 
	 * @return
	 */
	public static String getCurrentDateCode() {
		/*
		 * Calendar calendar=Calendar.getInstance(); Random random = new Random(); return String.valueOf(System.currentTimeMillis()) + String.valueOf(random.nextInt());
		 */
		DateFormat dateForma = new SimpleDateFormat("yyyyMMddHHmmss");
		String dateString = dateForma.format(new java.util.Date());

		return dateString;
	}
	public static String getCurrentDateCode(String format) {
		java.util.Date date=new java.util.Date();
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		return sdf.format(date);
	}
	/**
	 * 获取当天开始时间 0时0分0秒
	 * 
	 * @return
	 */
	public static java.util.Date getTodayBeginDate() {
/*
		java.util.Date beginDate = new java.util.Date();
		java.util.Date endDate = new java.util.Date();
		beginDate.setHours(0);
		beginDate.setMinutes(0);
		beginDate.setSeconds(0);
		endDate.setHours(23);
		endDate.setMinutes(59);
		endDate.setSeconds(59);
*/
		Calendar calendar = Calendar.getInstance();
		// calendar.set(Calendar.HOUR, 0);
		calendar.set(Calendar.HOUR_OF_DAY, 0);
		calendar.set(Calendar.MINUTE, 0);
		calendar.set(Calendar.SECOND, 0);
		calendar.set(Calendar.MILLISECOND, 0);
		java.util.Date beginDate = calendar.getTime();
		return beginDate;
	}

	/**
	 * 获取当天结束时间 23时59分59秒
	 * @return
	 */
	public static java.util.Date getTodayEndDate() {
		Calendar calendar = Calendar.getInstance();
		// calendar.set(Calendar.HOUR, 11);//12小时制
		calendar.set(Calendar.HOUR_OF_DAY, 23);// 24小时制
		calendar.set(Calendar.MINUTE, 59);
		calendar.set(Calendar.SECOND, 59);
		calendar.set(Calendar.MILLISECOND, 999);
		java.util.Date endDate = calendar.getTime();
		
		return endDate;
	}
	
	/**
	 * 按模式获取当前时间的开始时间与结束时间
	 * @param date 当前时间
	 * @param mode 获取模式: 日("day")、周("week")、月("month")("0")、季("season")、年("year")("1")
	 * @param format 指定格式 如：yyyy-MM-dd HH:mm:ss
	 */
	public static String[] getBegainAndEndTime(java.util.Date date, String mode,String format) {
		SimpleDateFormat sdf = new SimpleDateFormat(format);
		java.util.Date[] dates = getBegainAndEndDate(date, mode);
		return new String[] { sdf.format(dates[0]), sdf.format(dates[1]) };
	}
	/**
	 * 按模式获取当前时间的开始时间与结束时间
	 * @param date 当前时间
	 * @param mode 获取模式: 日("day")、周("week")、月("month")("0")、季("season")、年("year")("1")
	 */
	public static java.util.Date[] getBegainAndEndDate(java.util.Date date, String mode) {
		Calendar calendar = Calendar.getInstance();
		calendar.setTime(date);
		java.util.Date begin = new java.util.Date();
		java.util.Date end = new java.util.Date();
		if (mode == null || mode.equalsIgnoreCase("日") || mode.equalsIgnoreCase("day")) {
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MILLISECOND, 0);
			begin = calendar.getTime();
			// calendar.set(Calendar.HOUR, 11);//12小时制
			calendar.set(Calendar.HOUR_OF_DAY, 23);// 24小时制
			calendar.set(Calendar.MINUTE, 59);
			calendar.set(Calendar.SECOND, 59);
			calendar.set(Calendar.MILLISECOND, 999);
			end = calendar.getTime();
		} else if (mode.equalsIgnoreCase("周") || mode.equalsIgnoreCase("week")) {
			calendar.set(Calendar.DAY_OF_WEEK, Calendar.MONDAY);
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MILLISECOND, 0);
			begin = calendar.getTime();
			calendar.set(Calendar.DAY_OF_WEEK, Calendar.SATURDAY);
			calendar.add(Calendar.DAY_OF_WEEK, 1);
			calendar.set(Calendar.HOUR_OF_DAY, 23);
			calendar.set(Calendar.MINUTE, 59);
			calendar.set(Calendar.SECOND, 59);
			calendar.set(Calendar.MILLISECOND, 999);
			end = calendar.getTime();
		} else if (mode.equalsIgnoreCase("月") || mode.equalsIgnoreCase("month") || mode.equalsIgnoreCase("0")) {
			calendar.set(Calendar.DAY_OF_MONTH, 1);
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MILLISECOND, 0);
			begin = calendar.getTime();
			calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
			calendar.set(Calendar.HOUR_OF_DAY, 23);
			calendar.set(Calendar.MINUTE, 59);
			calendar.set(Calendar.SECOND, 59);
			calendar.set(Calendar.MILLISECOND, 999);
			end = calendar.getTime();
		} else if (mode.equalsIgnoreCase("季") || mode.equalsIgnoreCase("season")) {
			int month = calendar.get(Calendar.MONTH) + 1;
			if (month % 3 == 0) {// 季度结束月 6(3,4,5)
				calendar.set(Calendar.MONTH, month - 3);
				calendar.set(Calendar.DAY_OF_MONTH, 1);
				calendar.set(Calendar.HOUR_OF_DAY, 0);
				calendar.set(Calendar.MINUTE, 0);
				calendar.set(Calendar.SECOND, 0);
				calendar.set(Calendar.MILLISECOND, 0);
				begin = calendar.getTime();
				calendar.set(Calendar.MONTH, month - 1);
				calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
				calendar.set(Calendar.HOUR_OF_DAY, 23);
				calendar.set(Calendar.MINUTE, 59);
				calendar.set(Calendar.SECOND, 59);
				calendar.set(Calendar.MILLISECOND, 999);
				end = calendar.getTime();
			} else if (month % 3 == 1) {// 季度起始月 4(3,4,5)
				calendar.set(Calendar.MONTH, month - 1);
				calendar.set(Calendar.DAY_OF_MONTH, 1);
				calendar.set(Calendar.HOUR_OF_DAY, 0);
				calendar.set(Calendar.MINUTE, 0);
				calendar.set(Calendar.SECOND, 0);
				calendar.set(Calendar.MILLISECOND, 0);
				begin = calendar.getTime();
				calendar.set(Calendar.MONTH, month + 1);
				calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
				calendar.set(Calendar.HOUR_OF_DAY, 23);
				calendar.set(Calendar.MINUTE, 59);
				calendar.set(Calendar.SECOND, 59);
				calendar.set(Calendar.MILLISECOND, 999);
				end = calendar.getTime();
			} else if (month % 3 == 2) {// 季度中间月 5(3,4,5)
				calendar.set(Calendar.MONTH, month - 2);
				calendar.set(Calendar.DAY_OF_MONTH, 1);
				calendar.set(Calendar.HOUR_OF_DAY, 0);
				calendar.set(Calendar.MINUTE, 0);
				calendar.set(Calendar.SECOND, 0);
				calendar.set(Calendar.MILLISECOND, 0);
				begin = calendar.getTime();
				calendar.set(Calendar.MONTH, month);
				calendar.set(Calendar.DAY_OF_MONTH, calendar.getActualMaximum(Calendar.DAY_OF_MONTH));
				calendar.set(Calendar.HOUR_OF_DAY, 23);
				calendar.set(Calendar.MINUTE, 59);
				calendar.set(Calendar.SECOND, 59);
				calendar.set(Calendar.MILLISECOND, 999);
				end = calendar.getTime();
			}
		} else if (mode.equalsIgnoreCase("年") || mode.equalsIgnoreCase("year") || mode.equalsIgnoreCase("1")) {
			calendar.set(Calendar.DAY_OF_YEAR, 1);
			calendar.set(Calendar.HOUR_OF_DAY, 0);
			calendar.set(Calendar.MINUTE, 0);
			calendar.set(Calendar.SECOND, 0);
			calendar.set(Calendar.MILLISECOND, 0);
			begin = calendar.getTime();
			calendar.set(Calendar.DAY_OF_YEAR, calendar.getActualMaximum(Calendar.DAY_OF_YEAR));
			calendar.set(Calendar.HOUR_OF_DAY, 23);
			calendar.set(Calendar.MINUTE, 59);
			calendar.set(Calendar.SECOND, 59);
			calendar.set(Calendar.MILLISECOND, 999);
			end = calendar.getTime();
		}

		return new java.util.Date[] { begin, end };
	}
	/**
	 * 得到星座
	 * @param month 月
	 * @param day 日
	 * @return
	 */
	public static String getConstellation(int month, int day) {  
	    return day < dayArr[month - 1] ? constellationArr[month - 1] : constellationArr[month];  
	}
	
}
