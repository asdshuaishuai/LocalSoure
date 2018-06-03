package com.huoshui.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class test {
	public static void main(String arg[]) throws ParseException {
		String DBDRIVER="com.microsoft.sqlserver.jdbc.SQLServerDriver";
        String DBURL="jdbc:sqlserver://localhost:1433; DatabaseName=faceDB";
        String DBUSER="sa";
        String PASSWORD="huoshuai";
        try {
			Class.forName(DBDRIVER);
		} catch (ClassNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    
		 String sql_2="select count(*) from usertimes where userstatus='³Ùµ½'";
	}
}
