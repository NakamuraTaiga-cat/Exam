<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">

<c:param name="title">
    科目削除
</c:param>

<c:param name="content">

<h2 style="
    background-color:#eeeeee;
    padding:10px;
">
    科目情報削除
</h2>

<p>
「${subject.name}(${subject.cd})」
を削除してもよろしいですか？
</p>

<form action="SubjectDeleteExecute.action"
      method="post">

<input type="hidden"
       name="cd"
       value="${subject.cd}">

<button type="submit"
style="
    background-color:red;
    color:white;
    border:none;
    padding:8px 16px;
    border-radius:5px;
">
    削除
</button>

</form>

<br>

<a href="SubjectList.action">
    戻る
</a>

</c:param>

</c:import>