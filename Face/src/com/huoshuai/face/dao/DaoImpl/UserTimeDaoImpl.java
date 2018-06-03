package com.huoshuai.face.dao.DaoImpl;

import java.util.List;

import com.huoshuai.face.dao.UserTimeDao;
import com.huoshuai.face.user.UserTime;
import com.huoshuai.face.user.Users;

public class UserTimeDaoImpl extends TimeBaseDaoUtilImpl implements UserTimeDao {

	
	
	@Override
	public int timesUser(UserTime entity) {
		return super.timesUser(entity);
	}
	
	@Override
	public List<UserTime> userTimes() {
		return super.userTimes();
	}
	
	
}
