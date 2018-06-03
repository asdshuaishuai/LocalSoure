package com.huoshuai.face.service;

import java.util.List;

import com.huoshuai.face.user.UserTime;

public interface UserTimeService {
	
	
	public int timesUser(UserTime userTime);
	
	public List<UserTime> userTimes();
}
