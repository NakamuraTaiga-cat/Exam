<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="jakarta.tags.core" %>

<c:import url="/common/base.jsp">

    <c:param name="title">
        科目管理
    </c:param>

    <c:param name="content">

      <h2 style="
    background-color:#eeeeee;
    padding:10px;
">
    科目管理
</h2>

        <div style="text-align:right;">
            <a href="subject_create.jsp">
    新規登録
</a>
        </div>

        <table style="width:100%;">

            <tr>
               <th style="
    border-bottom:1px solid #cccccc;
    padding:10px;
    text-align:left;
">
    科目コード
</th>

<th style="
    border-bottom:1px solid #cccccc;
    padding:10px;
    text-align:left;
">
    科目名
</th>

<th style="
    border-bottom:1px solid #cccccc;
    padding:10px;
">
    変更
</th>

<th style="
    border-bottom:1px solid #cccccc;
    padding:10px;
">
    削除
</th>
            </tr>

            <c:forEach var="s" items="${list}">

            <tr>
                <td style="
    border-bottom:1px solid #cccccc;
    padding:10px;
">
    ${s.cd}
</td>

<td style="
    border-bottom:1px solid #cccccc;
    padding:10px;
">
    ${s.name}
</td>

<td style="
    border-bottom:1px solid #cccccc;
    padding:10px;
">
    <a href="
SubjectUpdate.action?cd=${s.cd}
">
    変更
</a>
</td>

<td style="
    border-bottom:1px solid #cccccc;
    padding:10px;
">
  <a href="
SubjectDelete.action?cd=${s.cd}
">
    削除
</a>
</td>
            </tr>

            </c:forEach>

        </table>

    </c:param>

</c:import>