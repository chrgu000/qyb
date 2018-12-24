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
    <li class="active"><a href="${ctx}/qyb/wUser/list">用户列表</a></li>
</ul>
<form:form id="searchForm" action="${ctx}/qyb/wUser/list" method="post" class="breadcrumb form-search">
    <input id="pageNo" name="pageNo" type="hidden" value="${page.pageNo}"/>
    <input id="pageSize" name="pageSize" type="hidden" value="${page.pageSize}"/>
    <div>
        <label>用户名：</label><input id="name" name="name" type="text" maxlength="50" class="input-mini"
                                  value="${user.name}"/>

        <label>公司名称：</label><input id="company.name" name="company.name" type="text" maxlength="50" class="input-mini"
                                   value="${user.company.name}"/>
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
        <th>姓名</th>
        <th>用户类型</th>
        <th>所属公司</th>
        <th>职位</th>
        <th>手机号码</th>
        <th>注册时间</th>
        <th>操作</th>
    </thead>
    <tbody><%
        request.setAttribute("strEnter", "\n");
        request.setAttribute("strTab", "\t");
    %>
    <c:forEach items="${page.list}" var="item">
        <tr>
            <td>${item.name}</td>
            <td>${fns:getDictLabels(item.vipLevel, 'vip_level', '普通用户')}
            </td>
            <td>${item.company.name}</td>
            <td>${item.position}</td>
            <td>${item.mobile}</td>
            <td>
                    <fmt:formatDate value="${item.createDate}" type="both" pattern="yyyy-MM-dd HH:mm:ss"/>
            <td>
                <a href="${ctx}/qyb/wUser/form?id=${item.id}">修改</a>
<%--                <a href="${ctx}/qyb/wUser/delete?id=${item.id}"
                   onclick="return confirmx('确认要删除这条信息？', this.href)">删除</a>--%>
            </td>
        </tr>
    </c:forEach>
    </tbody>
</table>
<div class="pagination">${page}</div>
</body>
</html>