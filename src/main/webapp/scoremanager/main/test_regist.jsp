<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<c:import url="/common/base.jsp">
	<c:param name="title">
		得点管理システム
	</c:param>
	
	<c:param name="scripts"></c:param>
	
	<c:param name="content">
		<section class="me-4">
			<h2 class="h3 mb-3 fw-norma bg-secondary bg-opacity-10 py-2 px-4">成績管理</h2>
			<form method="get" action="TestRegist.action">
				<div class="row border mx-3 mb-3 py-2 align-items-center rounded" id="filter">
				<%-- 入学年度のセレクト --%>
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
								<option value="${ subject.cd }">${ subject.name }</option>
							</c:forEach>
						</select>
					</div>
					<%-- 回数のセレクト --%>
					<div class="col-2">
						<label class="form-label" for="student-f4-select">回数</label>
						<select class="form-select " id="student-f4-select" name="f4">
							<option value="0">--------</option>
							<c:forEach var="num" items="${ num_set }">
								<option value="${ num }" <c:if test="${ num==f4 }">selected</c:if>>${ num }</option>
							</c:forEach>
						</select>
					</div>
					<div class="col-2 text-center">
						<button class="btn btn-secondary" id="filter-button">検索</button>
					</div>
					<div class="mt-2 text-warning">${ errors.get("f1") }</div>
				</div>
				<%-- 検索ボタンを押した後 --%>
			</form>
			<c:if test="${ not empty test_list }">
				<c:choose>
				
				    <%-- 検索結果があるとき --%>
				    <c:when test="${ test_list.size() > 0 }">
				        <div class="mb-2">
				            科目：${ subject.name }（${ testNo }回）
				        </div>
	
				            <table class="table table-hover">
				                <thead>
				                    <tr>
				                        <th>入学年度</th>
				                        <th>クラス</th>
				                        <th>学生番号</th>
				                        <th>氏名</th>
				                        <th>点数</th>
				                    </tr>
				                </thead>
				
				                <tbody>
				                    <c:forEach var="t" items="${ test_list }">
				                        <tr>
	
				                            <td>${ t.student.entYear }</td>
	
				                            <td>${ t.student.classNum }</td>
	
				                            <td>
				                                ${ t.student.no }
				                                <input type="hidden"
				                                       name="studentNo"
				                                       value="${ t.student.no }">
				                            </td>
	
				                            <td>${ t.student.name }</td>
	
				                            <td>
				                                <input type="number"
				                                       class="form-control"
				                                       name="point"
				                                       value="${ t.point }"
				                                       min="0" max="100">
				                            </td>
				                        </tr>
				                    </c:forEach>
				                </tbody>
				            </table>
				
				            <div class="mt-3">
				                <button class="btn btn-secondary">
				                    登録して終了
				                </button>
				            </div>
				
				    </c:when>
				
				    <%-- 検索結果が0件のとき --%>
				    <c:otherwise>
				        <div>該当する成績情報が存在しませんでした</div>
				    </c:otherwise>
				
				</c:choose>
				
			</c:if>
		</section>
	</c:param>
</c:import>
