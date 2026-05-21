<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">

<c:param name="title">
    科目変更
</c:param>

<c:param name="content">

<h2 style="
    background-color:#eeeeee;
    padding:10px;
">
    科目情報変更
</h2>

<form action="SubjectUpdateExecute.action"
      method="post">

<input type="hidden"
       name="cd"
       value="${subject.cd}">

<table style="
    width:100%;
    border-collapse:collapse;
">

<tr>
    <td style="padding:10px;">
        科目コード
    </td>
</tr>

<tr>
    <td style="padding:10px;">

        ${subject.cd}

        <c:if test="${not empty cdError}">
            <div style="
                color:orange;
                margin-top:5px;
            ">
                ${cdError}
            </div>
        </c:if>

    </td>
</tr>

<tr>
    <td style="padding:10px;">
        科目名
    </td>
</tr>

<tr>
    <td style="padding:10px;">

        <input type="text"
               name="name"
               value="${subject.name}"
               required
               placeholder="科目名を入力してください"
               style="width:100%;">

        <c:if test="${not empty nameError}">
            <div style="
                color:orange;
                margin-top:5px;
            ">
                ${nameError}
            </div>
        </c:if>

    </td>
</tr>

<tr>
    <td style="padding:10px;">

        <button type="submit"
        style="
            background-color:#007bff;
            color:white;
            border:none;
            padding:8px 16px;
            border-radius:5px;
        ">
            変更
        </button>

    </td>
</tr>

<tr>
    <td style="padding:10px;">

        <a href="SubjectList.action">
            戻る
        </a>

    </td>
</tr>

</table>

</form>

</c:param>

</c:import>