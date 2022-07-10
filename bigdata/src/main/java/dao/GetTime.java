package dao;

import java.text.SimpleDateFormat;
import java.util.Date;

public class GetTime {
	public static String getTime() {
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        return df.format(new Date());
	}
	public static String getTime2() {
		SimpleDateFormat df = new SimpleDateFormat("yyyyMMddHHmmss");
        return df.format(new Date());
	}
}
