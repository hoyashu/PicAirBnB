<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.*,model.dao.statistic.*"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>
<%LogrecordDao service = LogrecordDao.getInstance();
   int total = service.selectTotalLogrecordCount();
   int today = service.selectTodayLogrecordCount();
   pageContext.setAttribute("total", total);
   pageContext.setAttribute("today", today);%>

<div class="container">
<footer class="d-flex flex-wrap justify-content-between align-items-center py-3 my-4 border-top">
   
   <p class="col-md-4 mb-0 text-muted">© 2021 Company, Inc</p>

   <a href="${pageContext.request.contextPath}/index.jsp"
      class="col-md-4 d-flex align-items-center justify-content-center mb-3 mb-md-0 me-md-auto link-dark text-decoration-none">
      <img src="resource\img\logo.png" alt="사이트 로고" class="logo" >
   </a>
   <div id="visit-count" class="col-md-2 justify-content-end">
      <ul>
         <li>
            <p class="total">전체 방문자 : ${pageScope.total }</p>
         </li>

         <li>
            <p class="today">오늘 방문자 : ${pageScope.today }</p>
         </li>

      </ul>
   </div>
   
</footer>
</div>
<script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.bundle.min.js"
   integrity="sha384-ka7Sk0Gln4gmtz2MlQnikT1wXgYsOg+OMhuP+IlRH9sENBO0LRn5q+8nbTov4+1p" crossorigin="anonymous"></script>
</body>

</html>