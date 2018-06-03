<%@ page language="java" import="java.util.*,java.sql.*" pageEncoding="UTF-8"%>
<%@ page import="com.huoshuai.face.service.UserTimeService" %>
<%@ page import="com.huoshuai.face.service.UserServiceImpl.UserTimeServiceImpl" %>
<%@ page import="com.huoshuai.face.user.UserTime" %>
<%
String path = request.getContextPath();
String basePath = request.getScheme()+"://"+request.getServerName()+":"+request.getServerPort()+path+"/";
%>

<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN">
<html >
<head>
<base href="<%=basePath%>">

<title>Login</title>
<meta http-equiv="pragma" content="no-cache">
<meta http-equiv="cache-control" content="no-cache">
<meta http-equiv="expires" content="0">
<meta http-equiv="keywords" content="keyword1,keyword2,keyword3">
<meta http-equiv="description" content="This is my page">
<script type="text/javascript">
 $(function() {
		/* For zebra striping */
        $("table tr:nth-child(odd)").addClass("odd-row");
		/* For cell text alignment */
		$("table td:first-child, table th:first-child").addClass("first");
		/* For removing the last border */
		$("table td:last-child, table th:last-child").addClass("last");
});
</script>

<style type="text/css">

	html, body, div, span, object, iframe,
	h1, h2, h3, h4, h5, h6, p, blockquote, pre,
	abbr, address, cite, code,
	del, dfn, em, img, ins, kbd, q, samp,
	small, strong, sub, sup, var,
	b, i,
	dl, dt, dd, ol, ul, li,
	fieldset, form, label, legend,
	table, caption, tbody, tfoot, thead, tr, th, td {
		margin:0;
		padding:0;
		border:0;
		outline:0;
		font-size:100%;
		vertical-align:baseline;
		background:transparent;
	}
	
	body {
		margin:0;
		padding:0;
		font:12px/15px "Helvetica Neue",Arial, Helvetica, sans-serif;
		color: #555;
		background:#f5f5f5 url(images/bg.jpg);
	}
	
	a {color:#666;}
	
	#content {width:65%; max-width:690px; margin:6% auto 0;}
	
	/*
	Pretty Table Styling
	CSS Tricks also has a nice writeup: http://css-tricks.com/feature-table-design/
	*/
	
	table {
		overflow:hidden;
		border:1px solid #d3d3d3;
		background:#fefefe;
		width:70%;
		margin:5% auto 0;
		-moz-border-radius:5px; /* FF1+ */
		-webkit-border-radius:5px; /* Saf3-4 */
		border-radius:5px;
		-moz-box-shadow: 0 0 4px rgba(0, 0, 0, 0.2);
		-webkit-box-shadow: 0 0 4px rgba(0, 0, 0, 0.2);
	}
	
	th, td {padding:18px 28px 18px; text-align:center; }
	
	th {padding-top:22px; text-shadow: 1px 1px 1px #fff; background:#e8eaeb;}
	
	td {border-top:1px solid #e0e0e0; border-right:1px solid #e0e0e0;}
	
	tr.odd-row td {background:#f6f6f6;}
	
	td.first, th.first {text-align:left}
	
	td.last {border-right:none;}
	
	/*
	Background gradients are completely unnecessary but a neat effect.
	*/
	
	td {
		background: -moz-linear-gradient(100% 25% 90deg, #fefefe, #f9f9f9);
		background: -webkit-gradient(linear, 0% 0%, 0% 25%, from(#f9f9f9), to(#fefefe));
	}
	
	tr.odd-row td {
		background: -moz-linear-gradient(100% 25% 90deg, #f6f6f6, #f1f1f1);
		background: -webkit-gradient(linear, 0% 0%, 0% 25%, from(#f1f1f1), to(#f6f6f6));
	}
	
	th {
		background: -moz-linear-gradient(100% 20% 90deg, #e8eaeb, #ededed);
		background: -webkit-gradient(linear, 0% 0%, 0% 20%, from(#ededed), to(#e8eaeb));
	}
	
	/*
	I know this is annoying, but we need additional styling so webkit will recognize rounded corners on background elements.
	Nice write up of this issue: http://www.onenaught.com/posts/266/css-inner-elements-breaking-border-radius
	
	And, since we've applied the background colors to td/th element because of IE, Gecko browsers also need it.
	*/
	
	tr:first-child th.first {
		-moz-border-radius-topleft:5px;
		-webkit-border-top-left-radius:5px; /* Saf3-4 */
	}
	
	tr:first-child th.last {
		-moz-border-radius-topright:5px;
		-webkit-border-top-right-radius:5px; /* Saf3-4 */
	}
	
	tr:last-child td.first {
		-moz-border-radius-bottomleft:5px;
		-webkit-border-bottom-left-radius:5px; /* Saf3-4 */
	}
	
	tr:last-child td.last {
		-moz-border-radius-bottomright:5px;
		-webkit-border-bottom-right-radius:5px; /* Saf3-4 */
	}


</style>

</head>

<body>


<div id="content">
<h1 style="font-size:50px;color:red" align=center>欢迎使用人脸考勤</h1>
  <div style="float:left">
  <table border="1"  cellspacing="0">
      <tr>
          <td>序号</td>
          <td>用户名</td>
          <td>签到日期</td>
          <td>签到时间</td>
          <td>考勤状态</td>
         
      </tr>
        <%
        String DBDRIVER="com.microsoft.sqlserver.jdbc.SQLServerDriver";
        String DBURL="jdbc:sqlserver://localhost:1433; DatabaseName=faceDB";
        String DBUSER="sa";
        String PASSWORD="huoshuai";
        List<UserTime> userList =new ArrayList<UserTime>();
        try{
            Class.forName(DBDRIVER);
            Connection cn=DriverManager.getConnection(DBURL,DBUSER,PASSWORD);
            Statement st=cn.createStatement();
            String sql="select * from usertimes ";
            ResultSet rs=st.executeQuery(sql);
            
            while(rs.next()){
            	UserTime ut=new UserTime();
              /*  String sno=rs.getString("字段1");
                String sname=rs.getString("字段2");
                String sex=rs.getString("字段3");
                java.sql.Date birthday=rs.getDate("字段四");
                String sd=rs.getString("字段5");
               */
               ut.setId(rs.getInt("id"));
               ut.setUsername(rs.getString("username"));
               ut.setDate(rs.getString("date"));
               ut.setTime(rs.getString("time"));
               ut.setUserstatus(rs.getString("userstatus"));
               userList.add(ut);
            }
            rs.close();//关闭结果集
         
            
            cn.close();//关闭操作
            }
            catch(Exception ex){
            System.out.println(ex.getMessage());
            System.out.println("连接异常");
            ex.printStackTrace();
            }
       	  // UserTimeService dao=new UserTimeServiceImpl();
           
           for(UserTime ul:userList)
           {%>
          <tr>
              <td><%=ul.getId() %></td>
              <td><%=ul.getUsername() %></td>
              <td><%=ul.getDate()%></td>
              <td><%=ul.getTime() %></td>
               <td><%=ul.getUserstatus()%></td>
          </tr>
            <%}
       %>
  </table>
  </div>

</div>

</body>
</html>
