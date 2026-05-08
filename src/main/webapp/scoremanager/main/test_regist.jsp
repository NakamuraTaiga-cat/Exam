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
								<option value="${ subject.cd }"<c:if test="${ subject.cd == f3 }">selected</c:if>>${ subject.name }</option>
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
				
				<c:if test="${ not empty message }">
				    <div class="text-danger">
				        ${ message }
				    </div>
				</c:if>
								
				<%-- 検索ボタンを押した後 --%>
			</form>

            <!-- 検索後一覧＋登録フォーム -->
            <c:if test="${ searched and not empty test_list }">

                <form method="post" action="TestRegistExecute.action">

                    <!-- 検索条件引き継ぎ -->
                    <input type="hidden" name="f3" value="${ f3 }">
                    <input type="hidden" name="f4" value="${ f4 }">
                    <input type="hidden" name="f2" value="${ f2 }">

                    <div class="mb-2">
                        科目：
                        <c:forEach var="s" items="${ subject_set }">
                            <c:if test="${ s.cd == f3 }">${ s.name }</c:if>
                        </c:forEach>
                        （${ f4 }回）
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

                            <c:forEach var="t" items="${ test_list }" varStatus="st">
                                <tr>
                                    <td>${ t.student.entYear }</td>
                                    <td>${ t.student.classNum }</td>
                                    <td>${ t.student.no }</td>
                                    <td>${ t.student.name }</td>
                                    <td>
                                        <input type="number"
                                               class="form-control"
                                               name="point_${ t.student.no }"
                                               <c:if test="${ t.point != null }">
                                                   value="${ t.point }"
                                               </c:if>

                                        <c:if test="${ errors != null and not empty errors['point_' + t.student.no] }">
                                            <div class="text-warning">
                                                ${ errors['point_' + t.student.no] }
                                            </div>
                                        </c:if>
                                    </td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>

                    <button class="btn btn-secondary mt-3">
                        登録して終了
                    </button>
                </form>

            </c:if>
        </section>
    </c:param>
</c:import>

