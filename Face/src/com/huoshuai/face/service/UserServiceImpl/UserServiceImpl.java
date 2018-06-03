 /**  
* @Title: BaseServiceImpl.java
* @Package org.service.impl
* @Description: TODO该方法的主要作用：
* @author A18ccms A18ccms_gmail_com  
* @date 2017-9-22 下午8:50:26
* @version V1.0  
*/
package com.huoshuai.face.service.UserServiceImpl;

import com.huoshuai.face.dao.IUserDao;
import com.huoshuai.face.dao.UserTimeDao;
import com.huoshuai.face.dao.DaoImpl.UserDaoImpl;
import com.huoshuai.face.dao.DaoImpl.UserTimeDaoImpl;
import com.huoshuai.face.service.IUserService;
import com.huoshuai.face.user.UserTime;
import com.huoshuai.face.user.Users;

/**   
 *    
 * 项目名称：test_face_photo   
 * 类名称：BaseServiceImpl   
 * 类描述：   Service接口的实现类
 * @version    
 *    
 */
public class UserServiceImpl implements IUserService {

	private IUserDao userDao  = new UserDaoImpl();
	
	
	/**(非 Javadoc)
	 * <p>Description(描述):注册 </p>
	 * <p>Title: save</p>
	 * @return
	 * @see org.service.IUserService#save()
	 */

	@Override
	public int save(Users user) {
		// TODO Auto-generated method stub
		return userDao.save(user);
	}

	

	/**(非 Javadoc)
	 * <p>Description(描述): 登陆</p>
	 * <p>Title: queryInfoByUsername</p>
	 * @param username
	 * @return
	 * @see org.service.IUserService#queryInfoByUsername(java.lang.String)
	 */

	@Override
	public Users queryInfoByUsername(String username) {
		// TODO Auto-generated method stub
		return userDao.queryInfoByUsername(username);
	}




	

}
