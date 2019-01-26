<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>申请列表</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
      function page(n, s) {
        $("#pageNo").val(n);
        $("#pageSize").val(s);
        $("#searchForm").submit();
        return false;
      }
    </script>
</head>
<body>
<ul class="nav nav-tabs">
    <li class="active"><a href="${ctx}/qyb/apply/list">申请列表</a></li>
</ul>
<form:form id="searchForm"  modelAttribute="applyCash"  action="${ctx}/qyb/apply/list" method="post" class="breadcrumb form-search">
    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
    <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
    <div>
        <label>用户名：</label><input id="user.name" name="user.name" type="text" maxlength="50" class="input-mini"
                                  value="${user.name}"/>

<%--        <label>提现金额：</label><input id="amount" name="amount" type="text" maxlength="50" class="input-mini"
                                   value="${amount}"/>--%>
            <%--</div>

            <div style="margin-top:8px;">--%>
            <%--        <label>日期范围：&nbsp;</label><input id="beginDate" name="beginDate" type="text" readonly="readonly" maxlength="20"
                                                     class="input-mini Wdate"
                                                     value="<fmt:formatDate value="${wUser.beginDate}" pattern="yyyy-MM-dd"/>"
                                                     onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
                    <label>&nbsp;--&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label><input id="endDate" name="endDate" type="text"
                                                                                readonly="readonly" maxlength="20"
                                                                                class="input-mini Wdate"
                                                                                value="<fmt:formatDate value="${wUser.endDate}" pattern="yyyy-MM-dd"/>"
                                                                                onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>&nbsp;&nbsp;
                    &nbsp;--%>
        &nbsp;&nbsp;&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>&nbsp;&nbsp;
    </div>
</form:form>
<sys:message content="${message}"/>


<table id="contentTable" class="table table-striped table-bordered table-condensed">
    <thead>
    <tr>
        <th>用户名</th>
        <th>提现申请时间</th>
        <th>金额</th>
        <th>提现申请时间</th>
    </thead>
    <tbody><%
        request.setAttribute("strEnter", "\n");
        request.setAttribute("strTab", "\t");
    %>
    <c:forEach items="${page.list}" var="item">
        <tr>
            <td>${item.user.name}</td>
            <td> <fmt:formatDate value="${item.createDate}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/>
            </td>
            <td>${item.amount}</td>
           <%-- <td>${item.status}</td>--%>
            <td>
                <c:if test="${item.status eq '1'}">
                    <a href="${ctx}/qyb/apply/updateSt?id=${item.id}&userId=${item.user.id}">同意</a>
                </c:if>
                <c:if test="${item.status eq '2'}">
                    已通过
                </c:if>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div class="pagination">${page}</div>
</body>
</html>