 /**  
* @Title: UserDaoImpl.java
* @Package org.dao.impl
* @Description: TODO�÷�������Ҫ���ã�
* @author A18ccms A18ccms_gmail_com  
* @date 2017-9-22 ����8:52:58
* @version V1.0  
*/
package com.huoshuai.face.dao.DaoImpl;

import com.huoshuai.face.dao.IUserDao;
import com.huoshuai.face.user.Users;

/**   
 *    
 * ��Ŀ���ƣ�test_face_photo   
 * �����ƣ�UserDaoImpl   
 * �޸ı�ע��   
 * @version    
 *    
 */
public class UserDaoImpl extends BaseDaoUtilImpl<Users> implements IUserDao {
	/**
	 * (�� Javadoc)
	* <p>Description(����): ע��</p>
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
	 * (�� Javadoc)
	* <p>Description(����):��½ </p>
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
