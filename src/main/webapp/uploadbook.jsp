<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib prefix="s" uri="/struts-tags" %>
<%@include file="header.jsp"%>
<html>
<head>
</head>
<body>
<form action="<%=path%>/bookAction/uploadBook" method="post" enctype="multipart/form-data">
书名<input type="text" name="bookName" value="书"></input><br />
isbn<input type="text" name="isbn" value="isbn"></input><br />
作者<input type="text" name="author" value="作者"></input><br />
出版社<input type="text" name="press" value="出版社"></input><br />
分类（select框）<input type="text" name="category1" value="分类"></input><br />
出版时间 年<input type="text" name="publishYear" value="2017"></input><br />
出版时间 月<input type="text" name="publishMonth" value="7"></input><br />
版次 年<input type="text" name="edtionYear" value="2017"></input><br />
版次 月<input type="text" name="edtionMonth" value="7"></input><br />
版次 版<input type="text" name="edtionVersion" value="1"></input><br />
页数<input type="text" name="page" value="1000"></input><br />
装帧（select框）<input type="text" name="bookBinding" value="平装"></input><br />
开本（select框）<input type="text" name="bookFormat" value="32开"></input><br />
成色（select框）<input type="text" name="bookQuality" value="9成新"></input><br />
损毁情况(select框)<input type="text" name="bookDamage" value="无损毁"></input><br />
简介<textarea name="intro">简介</textarea><br />
发布状态 是否可借<input type="checkbox" name="bookBorrow" value="1" checked="checked"></input><br />
发布状态 借阅积分<input type="text" name="borrowCredit" value="0"></input><br />
发布状态 是否可换<input type="checkbox" name="bookExchange" value="1" checked="checked"></input><br />
发布状态 交换积分<input type="text" name="exchangeCredit" value="0"></input><br />
以下为非必填<br/>
<input type="file" name="coverPicture"></input><br />
<input type="file" name="otherPicture" multiple="multiple"></input><br />
<input type="submit" value="提交"><input><br />
</form>
</body>
</html>