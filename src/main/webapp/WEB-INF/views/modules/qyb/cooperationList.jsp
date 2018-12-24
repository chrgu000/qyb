<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>合作列表</title>
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
    <li class="active"><a href="${ctx}/qyb/cooperation/list">合作列表</a></li>
</ul>
<form:form id="searchForm" action="${ctx}/qyb/cooperation/list" method="post" class="breadcrumb form-search">
    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
    <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
    <div>
        <label>用户名：</label><input id="wUser.name" name="user.name" type="text" maxlength="50" class="input-mini"
                                  value="${cooperation.user.name}"/>
        <label>公司名称：</label><input id="wCompany.name" name="company.name" type="text" maxlength="50" class="input-mini"
                                   value="${cooperation.company.name}"/>
            <%--</div>

            <div style="margin-top:8px;">--%>
        <label>日期范围：&nbsp;</label><input id="beginDate" name="beginDate" type="text" readonly="readonly" maxlength="20"
                                         class="input-mini Wdate"
                                         value="<fmt:formatDate value="${cooperation.beginDate}" pattern="yyyy-MM-dd"/>"
                                         onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>
        <label>&nbsp;--&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;</label><input id="endDate" name="endDate" type="text"
                                                                    readonly="readonly" maxlength="20"
                                                                    class="input-mini Wdate"
                                                                    value="<fmt:formatDate value="${cooperation.endDate}" pattern="yyyy-MM-dd"/>"
                                                                    onclick="WdatePicker({dateFmt:'yyyy-MM-dd',isShowClear:false});"/>&nbsp;&nbsp;
        &nbsp;
        &nbsp;&nbsp;&nbsp;<input id="btnSubmit" class="btn btn-primary" type="submit" value="查询"/>&nbsp;&nbsp;
    </div>
</form:form>
<sys:message content="${message}"/>


<table id="contentTable" class="table table-striped table-bordered table-condensed">
    <thead>
    <tr>
        <th>标题</th>
        <th>发布用户</th>
        <th>所属公司</th>
        <th>合作地区</th>
        <th>状态</th>
        <th>发布时间</th>
        <th>操作</th>
    </thead>
    <tbody><%
        request.setAttribute("strEnter", "\n");
        request.setAttribute("strTab", "\t");
    %>
    <c:forEach items="${page.list}" var="item">
        <tr>
            <td>${item.title}</td>
            <td>${item.user.name}</td>
            <td>${item.company.name}</td>
            <td>${item.area}</td>
            <td>${item.status==1?'待审核':'已审核'}</td>
            <td>
                    <fmt:formatDate value="${item.createDate}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/>
            <td>

                <c:if test="${item.status==1}">
                    <a href="${ctx}/qyb/cooperation/updateStatus?id=${item.id}&status=2">同意</a>
                    <a href="${ctx}/qyb/cooperation/updateStatus?id=${item.id}&status=3">不同意</a>
                </c:if>

                <a href="${ctx}/qyb/cooperation/delete?id=${item.id}"
                   onclick="return confirmx('确认要删除这条信息？', this.href)">删除</a>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div class="pagination">${page}</div>
</body>
</html>