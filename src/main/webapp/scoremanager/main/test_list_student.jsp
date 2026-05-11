<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/common/base.jsp">
	<c:param name="title">
		得点管理システム
	</c:param>
	
	<c:param name="scripts"></c:param>
	
	<c:param name="content">
		<section class="me-4">
			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">成績参照</h2>
			<form method="get" action="TestList.action">
				<div class="row border mx-3 mb-3 py-2 align-items-center rounded" id="filter">
					<div class="col-2 text-center">科目情報</div>
					<div class="col-2">
						<label class="form-label" for="student-f1-select">入学年度</label>
						<select class="form-select " id="student-f1-select" name="f1">
							<option value="0">--------</option>
							<c:forEach var="year" items="${ ent_year_set }">
								<option value="${ year }" <c:if test="${ year==f1 }">selected</c:if>>${ year }</option>
							</c:forEach>
						</select>
					</div>
					<%-- クラスのセレクト --%>
					<div class="col-2">
						<label class="form-label" for="student-f2-select">クラス</label>
						<select class="form-select " id="student-f2-select" name="f2">
							<option value="0">--------</option>
							<c:forEach var="num" items="${ class_num_set }">
								<option value="${ num }" <c:if test="${ num==f2 }">selected</c:if>>${ num }</option>
							</c:forEach>
						</select>
					</div>
					<%-- 科目のセレクト --%>
					<div class="col-4">
						<label class="form-label" for="student-f3-select">科目</label>
						<select class="form-select " id="student-f3-select" name="f3">
							<option value="0">--------</option>
							<c:forEach var="subject" items="${ subject_set }">
								<option value="${ subject.cd }"<c:if test="${ subject.cd == f3 }">selected</c:if>>${ subject.name }</option>
							</c:forEach>
						</select>
					</div>
					<div class="col-2 text-center">
						<button class="btn btn-secondary" id="filter-button">検索</button>
					</div>
				</div>	
			</form>
			<form method="get" action="TestList.action">
				<div class="row border mx-3 mb-3 py-2 align-items-center rounded" id="filter">
					<div class="col-2 text-center">学生情報</div>
					<div class="col-4">
						<label class="form-label" for="student-f1-select">学生番号</label>
						<input type="text" class="form-control" id="student-f4" name="f4" maxlength="10" required placeholder="学生番号を入力してください" value="${ f4 }">
					</div>
					<div class="col-2 text-center">
						<button class="btn btn-secondary" id="filter-button">検索</button>
					</div>
			</form>
			
        </section>
        
        <%-- 科目の検索ボタンを押した後 --%>

		<c:if test="${searchType == 'subject'}">
		<!-- 科目別一覧 -->
			<c:forEach var="t" items="${test_list}">
				<tr>
					<th>入学年度</th>
					<th>クラス</th>
					<th>学生番号</th>
					<th>氏名</th>
					<th>1回</th>
					<th>2回</th>
				</tr>
				<tr>
					<td>${t.entYear}</td>
					<td>${t.classNum}</td>
					<td>${t.studentNo}</td>
					<td>${t.studentName}</td>
					<td>${t.pointsMap[1]}</td>
					<td>${t.pointsMap[2]}</td>
				</tr>
			</c:forEach>
		</c:if>

    </c:param>
</c:import>
