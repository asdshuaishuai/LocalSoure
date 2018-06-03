package com.huoshuai.face.servlet;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.json.JSONObject;

import com.baidu.aip.face.AipFace;
import com.huoshuai.face.service.IUserService;
import com.huoshuai.face.service.UserTimeService;
import com.huoshuai.face.service.UserServiceImpl.UserServiceImpl;
import com.huoshuai.face.service.UserServiceImpl.UserTimeServiceImpl;
import com.huoshuai.face.user.UserTime;
import com.huoshuai.face.user.Users;
import com.huoshui.util.TimeUtil;
import com.huoshuai.*;

import sun.misc.BASE64Decoder;

public class FaceServlet extends HttpServlet {
	
	
	
	private static final String USER_STATUS_Z="正常";
	private static final String USER_STATUS_C="迟到";
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
	 * (非 Javadoc)
	* <p>Description(描述):get方法，主要调用post </p>
	* <p>Title: doGet</p>
	* @param request
	* @param response
	* @throws ServletException
	* @throws IOException
	* @see javax.servlet.http.HttpServlet#doGet(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
			doPost(request, response);
	}

	/**
	 * (非 Javadoc)
	* <p>Description(描述):post方法，主要进行业务操作 </p>
	* <p>Title: doPost</p>
	* @param request
	* @param response
	* @throws ServletException
	* @throws IOException
	* @see javax.servlet.http.HttpServlet#doPost(javax.servlet.http.HttpServletRequest, javax.servlet.http.HttpServletResponse)
	 */
	public void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		//防止乱码
		response.setContentType("text/html;charset=utf-8");
		request.setCharacterEncoding("utf-8");
		response.setCharacterEncoding("utf-8");
		
		
		String 		img			=	 	request.getParameter("img");			//图像数据
		String 		username 	= 		request.getParameter("username");		//用户名
		String 		tag			= 		request.getParameter("tag");
		String APP_ID = "10984171";
	    String API_KEY = "dejSGLpy7iWGiqXWkoUG2VFl";
	    String SECRET_KEY = "upr4EQyg2EkayOuGlwh6glYESvTkvqfQ";
	    AipFace client = new AipFace(APP_ID, API_KEY, SECRET_KEY);
		if(tag.equals("reg")){
			//注册
			face(username, img, response,request,client);
		}else if(tag.endsWith("login")){
			//登陆
			try {
				login(img, response, username,request,client);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}
	
	/**
	 * 
	* @Title: face
	* @Description: 该方法的主要作用：注册
	* 1.将base64编码的图片保存
	* 2.将图片路径保存在数据库里面
	* 3.将人脸图片识别在人脸库中注册
	* @param   设定文件  
	* @return  返回类型：void   
	* @throws
	 */
	public void face(String username,String img,HttpServletResponse response,HttpServletRequest request,AipFace client) {
		Users user = new Users();
		IUserService userService = new UserServiceImpl();
		// 图片名称
		String fileName = System.currentTimeMillis() + ".png";
		String basePath = request.getSession().getServletContext()
				.getRealPath("picture/");

		// 往数据库里面插入注册信息
		user.setId(((Long)System.currentTimeMillis()).intValue());
		user.setUsername(username);
		user.setHeadphoto("/picture/" + fileName);
		userService.save(user);
		// 往服务器里面上传图片
		GenerateImage(img, basePath + "/" + fileName);
		// 给人脸库中加入一个脸
		boolean flag = facesetAddUser(client, basePath + "/" + fileName,
				username);
		try {
			PrintWriter out = response.getWriter();
			// 中文乱码，写个英文比较专业 哈哈

			if (flag == false) {
				out.print("请把脸放上!!");//请把两脸放上
			} else {
				out.print("注册成功!!");  //注册成功
			}
		} catch (IOException e) {
			// TODO 异常执行块！
			e.printStackTrace();
		}
	}


	/**
	 * @throws ParseException 
	 * @throws IOException 
	 * 
	* @Title: login
	* @Description: 该方法的主要作用：登陆
	* 实现原理：
	* 1.从前台接收用户名
	* 2.然后把从前台传过来的图片先上传到服务器
	* 3.把上传的这张人脸与人脸库中的人脸对应
	* @param  设定文件  
	* @return  返回类型：String   
	* @throws
	 */
	public void login(String img,HttpServletResponse response,String username,HttpServletRequest request,AipFace client) throws IOException, ParseException {
		UserTime userTime=new UserTime();
		SimpleDateFormat sd=new SimpleDateFormat("HH:mm"); 
		Date time2=sd.parse("08:00");
		TimeUtil tu=new TimeUtil();
		tu.setTime(time2);
		UserTimeService utService=new UserTimeServiceImpl();
		Date dateTime = new Date();
		SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd"); 
		SimpleDateFormat sdft=new SimpleDateFormat("HH:mm"); 
		String date=sdf.format(dateTime);
		String time=sdft.format(dateTime);
		Date time1=sdft.parse(time);
		
		userTime.setUsername(username);
		userTime.setDate(date);
		userTime.setTime(time);
		
		if(time1.before(tu.getTime())) {
			userTime.setUserstatus(USER_STATUS_Z);
		}else {
			userTime.setUserstatus(USER_STATUS_C);
		}
		// 图片名称
		String fileName = System.currentTimeMillis() + ".png";
		String basePath = request.getSession().getServletContext()
				.getRealPath("picture/");
		Double result = null;
		JSONObject js=new JSONObject();
		// 往服务器里面上传图片
		
		GenerateImage(img, basePath + "/" + fileName);
		response.setContentType("text/html,charset=utf-8;");
	
			PrintWriter out = response.getWriter();
			result = verifyUser(client, basePath + "/" + fileName,username);
			if (result > 90) {
				
				// 匹配成功
				out.print("登陆成功");  
				utService.timesUser(userTime);
				
			} else {
				//匹配失败
				out.print("警告：匹配失败");
				
			}
		
		
	}
	
	/**
	 * 
		* @Title: GenerateImage
		* @Description: 该方法的主要作用：// 对字节数组字符串进行Base64解码并生成图片
		* @param  @param imgStr
		* @param  @param imgFilePath
		* @param  @return 设定文件  
		* @return  返回类型：boolean   
		* @throws
		 */
	public boolean GenerateImage(String imgStr, String imgFilePath) {
		if (imgStr == null) // 图像数据为空
			return false;
		BASE64Decoder decoder = new BASE64Decoder();
		try {
			// Base64解码
			byte[] bytes = decoder.decodeBuffer(imgStr);
			for (int i = 0; i < bytes.length; ++i) {
				if (bytes[i] < 0) {// 调整异常数据
					bytes[i] += 256;
				}
			}
			// 生成jpeg图片
			OutputStream out = new FileOutputStream(imgFilePath);
			out.write(bytes);
			out.flush();
			out.close();
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}
	
		/** 
		 * @Title: facesetAddUser
	    * @Description: 该方法的主要作用：人脸注册,给人脸库中注册一个人脸
	    * @param  @param client 设定文件  
	    * @return  返回类型：void   
	    * @throws
	     */
	public boolean facesetAddUser(AipFace client, String path, String username) {
		// 参数为数据库中注册的人脸
		HashMap<String, String> options = new HashMap<String, String>();
		JSONObject res = client.addUser(username, "test_users_info",
				Arrays.asList("group1", "group2"), path, options);
		 System.out.println(res.toString(2));
		// {"log_id": 3621903690062422}
		if (res.keySet().contains("error_code")) {
			return false;
		}
		return true;

	}
	/**
	 * 
	* @Title: verifyUser
	* @Description: 该方法的主要作用：人脸认证
	* @param  @param client
	* @param  @param path
	* @param  @param username
	* @param  @return 设定文件  
	* @return  返回类型：Double   
	* @throws
	 */
	    
	public Double verifyUser(AipFace client, String path, String username) {
		HashMap<String, Object> options = new HashMap<String, Object>(1);
		options.put("top_num", 5);
	
		JSONObject res = client.verifyUser(username,
				Arrays.asList("group1", "group2"), path, options);
		Double result = Double.parseDouble(res.getJSONArray("result").get(0)
				.toString());
		return result;
	}


}
