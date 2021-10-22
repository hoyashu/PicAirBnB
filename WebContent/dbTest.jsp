<%-- dbTest.jsp --%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ page import="util.DBConn, java.sql.*" %>

<%
	Connection conn = DBConn.getConnection();
%>

<%=conn%>