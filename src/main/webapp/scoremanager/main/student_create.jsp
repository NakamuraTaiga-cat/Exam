<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/common/base.jsp">
	<c:param name="title">
		得点管理システム
	</c:param>
	<c:param name="scripts"></c:param>
	
	<c:param name="content">
		<section class="me-4">
			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">学生情報登録</h2>
			<form method="post" action="StudentCreateExecute.action">
				<%--入学年度選択 --%>
				<div class="col-12">
					<label class="form-label" for="entyear-select">入学年度</label>
					<select class="form-select " id="ent-year" name="ent-year" required>
						<option value="">--------</option>
						<c:forEach var="year" items="${ ent_year_set }">
							<option value="${ year }" <c:if test="${ year==ent_year }">selected</c:if>>${ year }</option>
						</c:forEach>
					</select>
				</div>
				<%-- 学生番号入力 --%>
				<div class="col-12">
					<label class="form-label" for="student-num">学生番号</label><br>
					<input type="text" class="form-control" id="no" name="no" maxlength="10" required placeholder="学生番号を入力してください" value="${ no }">
					<c:if test="${not empty error_no}">
					    <div class="text-warning mt-1">
					      ${error_no}
					    </div>
					</c:if>
				</div>
				<%-- 氏名入力 --%>
				<div class="col-12">
					<label class="form-label" for="student-name">氏名</label><br>
					<input type="text" class="form-control" id="name" name="name" maxlength="30" required placeholder="氏名を入力してください" value="${ name }">
				</div>
				<%-- クラス選択 --%>
					<div class="col-12">
						<label class="form-label" for="class-num">クラス</label>
						<select class="form-select " id="class-num" name="class-num" required>
							<option value="">--------</option>
							<c:forEach var="class_num" items="${ class_list }">
								<option value="${ class_num }" >${ class_num }</option>
							</c:forEach>
						</select>					
					</div>
				<%-- ボタン --%>
				<br>
				<div class="mb-3">
		            <button type="submit" name="end" class="btn btn-secondary">登録して終了</button>
				</div>
				<div>
					<a href="StudentList.action">戻る</a>
				</div>
			</form>
		</section>
	</c:param>
</c:import>