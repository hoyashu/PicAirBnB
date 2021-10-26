<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*,model.dao.statistic.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%LogrecordDao service = LogrecordDao.getInstance();
   int total = service.selectTotalLogrecordCount();
   int today = service.selectTodayLogrecordCount();
   pageContext.setAttribute("total", total);
   pageContext.setAttribute("today", today);%>
<footer>
   <div class="footer-inner">
      <ul class="footer-left">
         <li><a href="/">이용약관</a><i class="icon">|</i></li>
         <li><a href="/">개인정보처리방침</a></li>
      </ul>
      <div class="visit-count">
         <ul>
            <li><p class="total">전체 방문자 : ${pageScope.total }</p></li>
            <li><p class="today">오늘 방문자 : ${pageScope.today }</p></li>
         </ul>
      </div>
   </div>
</footer>
</body>
</html>