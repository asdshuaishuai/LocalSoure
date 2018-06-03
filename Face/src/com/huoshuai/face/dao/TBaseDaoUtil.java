package com.huoshuai.face.dao;

import java.util.List;

import com.huoshuai.face.user.UserTime;

public interface TBaseDaoUtil {
	public int timesUser(UserTime entity);
	
	public List<UserTime> userTimes();
}
