package com.huoshuai.face.service.UserServiceImpl;

import java.util.List;

import com.huoshuai.face.dao.UserTimeDao;
import com.huoshuai.face.dao.DaoImpl.UserTimeDaoImpl;
import com.huoshuai.face.service.UserTimeService;
import com.huoshuai.face.user.UserTime;

public class UserTimeServiceImpl implements UserTimeService {

	private UserTimeDao userTimeDao=new UserTimeDaoImpl();
	
	
	@Override
	public int timesUser(UserTime userTime) {
		// TODO Auto-generated method stub
		return userTimeDao.timesUser(userTime);
	}
	@Override
	public List<UserTime> userTimes() {
		return userTimeDao.userTimes();
	}
	
}
