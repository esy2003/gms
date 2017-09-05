<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<jsp:include page="../common/common_head.jsp"></jsp:include>
	<div id="gms-container">
	<div class="row">
  <div class="col-lg-6" style="width:500px; margin:0 auto;">
    <div class="input-group">
      <input id="searchName" name="searchName" type="text" class="form-control" placeholder="Search for...">
      <span class="input-group-btn">
        <button class="btn btn-default" type="button" onclick="searchName()">Go!</button>
      </span>
    </div><!-- /input-group -->
  </div><!-- /.col-lg-6 -->
</div><!-- /.row -->
<div style="height: 40px; width: 300px;"></div>
		<!-- userId, userPw, name, ssn, regdate, email, major, subject -->
		<table id="member-table-main">
			<tr class="member-table-menu">
				<th>번호</th>
				<th>아이디</th>
				<th>이름</th>
				<th>생년월일</th>
				<th>핸드폰번호</th>
				<th>수강과목</th>
				<th>이메일</th>
				<th>가입날짜</th>
				<th>수정/삭제</th>
			</tr>
			<c:forEach var="i" items="${requestScope.list}">
			<tr class="member-table-menu">
				<td>${i.num }</td>
				<td>${i.userId }</td>
				<td><a onclick="detailStudent('${i.userId}')">${i.name }</a></td>
				<td>${i.ssn }</td>
				<td>${i.phone }</td>
				<td>${i.title }</td>
				<td>${i.email }</td>
				<td>${i.regdate }</td>
				<td>
				<a onclick="updateStudent('${i.userId}')">수정</a>
				/
				<a onclick="deleteStudent('${i.userId}')">삭제</a>
				</td>
			</tr>
			</c:forEach>
		</table>
		<nav aria-label="Page navigation">
  <ul class="pagination">
  <c:if test="${requestScope.prevBlock gt 0}">
  <li><a onclick="list('member', 'member_list', '1')" href="#"><span class="glyphicon glyphicon-step-backward" aria-hidden="true"></span></a></li>
    <li>
      <a onclick="list('member', 'member_list', '${requestScope.startPage-5}')" href="#" aria-label="Previous">
        <span aria-hidden="true">&laquo;</span>
      </a>
      </li>
      </c:if>
      <c:forEach varStatus="i" begin="${requestScope.startPage }" end="${requestScope.endPage }" step="1">
      	<c:choose>
			<c:when test="${i.index eq requestScope.pageNumber}">
				<li class="active"><a href="#">${i.index}</a></li>
			</c:when>
			<c:otherwise>
				<li><a onclick="list('member','member_list', '${i.index}')">${i.index}</a></li>
			</c:otherwise>
		</c:choose>
      </c:forEach>
      <c:if test="${requestScope.prevBlock eq 0}">
    <li>
      <a onclick="list('member','member_list','${requestScope.endPage+1}')" href="#" aria-label="Next">
        <span aria-hidden="true">&raquo;</span>
      </a>
    </li>
    <li><a onclick="list('member', 'member_list', '${requestScope.theNumberOfPages}')" href="#"><span class="glyphicon glyphicon-step-forward" aria-hidden="true"></span></a></li>
    </c:if>
  </ul>
</nav>
	</div>

<jsp:include page="../common/footer.jsp"></jsp:include>