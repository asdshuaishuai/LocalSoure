/**
 * 
 */
package com.huoshuai.face.dao;

/**
 * 
*    
* 项目名称：test_face_photo   
* 类名称：IBaseDaoUtil   
* 类描述：   共用接口

*
 */
public interface IBaseDaoUtil<T> {
	
	/**
	 * 
	* @Description: 该方法的主要作用：添加数据
	* @Title: save
	* @param  @param entity    保存的对象
	* @param  @param tag		区分是Emp还是Dept
	* @param  @return 设定文件  
	* @return  返回类型：int   
	* @throws
	 */
	public int save(T entity);
	

	
	/**
	 * 
	* @Description: 该方法的主要作用：根据编号查询数据
	* @Title: queryById
	* @param  @param id
	* @param  @return 设定文件  
	* @return  返回类型：T   
	* @throws
	 */
	public T queryInfoByUsername(String username);
	
	
	
	
}
