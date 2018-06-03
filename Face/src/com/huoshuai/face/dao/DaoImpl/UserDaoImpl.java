 /**  
* @Title: UserDaoImpl.java
* @Package org.dao.impl
* @Description: TODO该方法的主要作用：
* @author A18ccms A18ccms_gmail_com  
* @date 2017-9-22 下午8:52:58
* @version V1.0  
*/
package com.huoshuai.face.dao.DaoImpl;

import com.huoshuai.face.dao.IUserDao;
import com.huoshuai.face.user.Users;

/**   
 *    
 * 项目名称：test_face_photo   
 * 类名称：UserDaoImpl   
 * 修改备注：   
 * @version    
 *    
 */
public class UserDaoImpl extends BaseDaoUtilImpl<Users> implements IUserDao {
	/**
	 * (非 Javadoc)
	* <p>Description(描述): 注册</p>
	* <p>Title: save</p>
	* @param entity
	* @return
	* @see org.dao.impl.BaseDaoUtilImpl#save(java.lang.Object)
	 */
	@Override
	public int save(Users entity) {
		return super.save(entity);
	}

	
	/**
	 * (非 Javadoc)
	* <p>Description(描述):登陆 </p>
	* <p>Title: queryInfoByUsername</p>
	* @param username
	* @return
	* @see org.dao.impl.BaseDaoUtilImpl#queryInfoByUsername(java.lang.String)
	 */
	@Override
	public Users queryInfoByUsername(String username) {
		// TODO Auto-generated method stub
		return super.queryInfoByUsername(username);
	}





}
