
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/common/base.jsp">
	<c:param name="title">
		得点管理システム
	</c:param>

	<c:param name="content">
		<section class="me-4">
			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">学生情報変更</h2>

			<form method="post" action="StudentUpdateExecute.action">

				<%-- 入学年度（表示のみ） --%>
				<div class="col-12">
					<label class="form-label">入学年度</label>
					<div class="form-control-plaintext">${ ent_year }</div>
					<input type="hidden" name="ent-year" value="${ ent_year }" />
				</div>
				<%-- 学生番号（表示のみ：枠なしで背景と同色に） --%>
				<div class="col-12">
					<label class="form-label" for="no">学生番号</label>
					<div class="form-control-plaintext" id="no">${ no }</div>
					<!-- 送信用に hidden で値を保持 -->
					<input type="hidden" name="no" value="${ no }" />
				</div>

				<%-- 氏名入力 --%>
				<div class="col-12">
					<label class="form-label" for="student-name">氏名</label><br>
					<input type="text" class="form-control" id="name" name="name" maxlength="30" required placeholder="氏名を入力してください" value="${ name }">
					<c:if test="${ not empty errors.name }">
						<div class="text-danger">${ errors.name }</div>
					</c:if>
				</div>

				<%-- クラス選択 --%>
				<div class="col-12">
					<label class="form-label" for="class-num">クラス</label>
					<select class="form-select " id="class-num" name="class-num" required>
						<option value="">--------</option>
						<c:forEach var="cnum" items="${ class_list }">
							<option value="${ cnum }" <c:if test="${ cnum==class_num }">selected</c:if>>${ cnum }</option>
						</c:forEach>
					</select>
				</div>

				<%-- 在学中 --%>
				<div class="col-12 form-check mt-2">
					<input class="form-check-input" type="checkbox" id="is-attend" name="is-attend" value="t" <c:if test="${ is_attend }">checked</c:if> />
					<label class="form-check-label" for="is-attend">在学中</label>
				</div>

				<br>
				<div class="mb-3">
					<button type="submit" name="update" class="btn btn-secondary">変更</button>
				</div>

				<div>
					<a href="StudentList.action">戻る</a>
				</div>
			</form>
		</section>
	</c:param>
</c:import>
