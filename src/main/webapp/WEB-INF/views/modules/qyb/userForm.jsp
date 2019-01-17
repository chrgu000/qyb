<%@ page contentType="text/html;charset=UTF-8" %>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<html>
<head>
    <title>用户管理</title>
    <meta name="decorator" content="default"/>
    <script type="text/javascript">
      $(document).ready(function () {
        $("#name").focus();
        $("#inputForm").validate({
          submitHandler: function (form) {
            loading('正在提交，请稍等...');
            form.submit();
          },
          errorContainer: "#messageBox",
          errorPlacement: function (error, element) {
            $("#messageBox").text("输入有误，请先更正。");
            if (element.is(":checkbox") || element.is(":radio") || element.parent().is(".input-append")) {
              error.appendTo(element.parent().parent());
            } else {
              error.insertAfter(element);
            }
          }
        });
      });
    </script>
</head>
<body>
<ul class="nav nav-tabs">
    <li><a href="${ctx}/qyb/wUser/list">用户列表</a></li>
    <li class="active">
        <a href="${ctx}/qyb/wUser/form?id=${wUser.id}">用户修改</a>
    </li>
</ul>
<br/>
<form:form id="inputForm" modelAttribute="user" action="${ctx}/qyb/wUser/updateLevel" method="post" class="form-horizontal">
    <form:hidden path="id"/>
    <sys:message content="${message}"/>

    <div class="control-group">
        <label class="control-label">姓名:</label>
        <div class="controls">
            <form:input path="name" htmlEscape="false" maxlength="50" class="required" readonly="true"/>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">用户类型:</label>
        <div class="controls">
            <form:select path="vipLevel" class="input-medium">
                <form:option value="" label=""/>
                <form:options items="${fns:getDictList('vip_level')}" itemLabel="label" itemValue="value"
                              htmlEscape="false"/>
            </form:select>
        </div>
    </div>
    <div class="control-group">
        <label class="control-label">余额:</label>
        <div class="controls">
            <form:input path="balance" htmlEscape="false" maxlength="50" class="required" />
        </div>
    </div>
    <div class="form-actions">
        <input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
        <input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
    </div>
</form:form>
</body>
</html>