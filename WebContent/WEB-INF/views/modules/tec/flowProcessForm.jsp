<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ include file="/WEB-INF/views/include/taglib.jsp" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta name="decorator" content="default">
	<title>工艺流程与工序关系管理</title>
	<script type="text/javascript">
		$(document).ready(function() {
			$("#value").focus();
			$("#inputForm").validate({
				submitHandler: function(form){
					loading('正在提交，请稍等...');
					form.submit();
				},
				errorContainer: "#messageBox",
				errorPlacement: function(error, element) {
					$("#messageBox").text("输入有误，请先更正。");
					if (element.is(":checkbox")||element.is(":radio")||element.parent().is(".input-append")){
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
	<!-- 1.tab -->
	<ul class="nav nav-tabs">
		<li><a href="${ctx}/tec/flowProcess/">工艺流程与工序关系列表</a></li>
		<li class="active"><a href="">工艺流程与工序关系${not empty flowProcess.id?'修改':'添加' }</a></li>
	</ul>
	<br/>
	<!-- 2.表单 -->
	<form:form id="inputForm" method="post" action="${ctx}/tec/flowProcess/save" modelAttribute="flowProcess" class="form-horizontal">
		<form:hidden path="id"/>
		<div class="control-group">
			<label class="control-label">工序：</label>
			<div class="controls">
				<form:select path="process.id" id="pId" class="input-medium required">
					<form:option value="" label=""/>
					<form:options items="${processList}" itemLabel="proName" itemValue="id" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">所属工艺流程：</label>
			<div class="controls">
				<form:select path="flow.id" id="fId" class="input-medium required">
					<form:option value="" label=""/>
					<form:options items="${flowList }" itemLabel="flowName" itemValue="id" htmlEscape="false"/>
				</form:select>
				<span class="help-inline"><font color="red">*</font></span>
			</div>
		</div>
		<div class="control-group">
			<label class="control-label">排序号：</label>
			<div class="controls">
				<form:input path="sort" htmlEscape="false" maxlength="50" class="digits"/>
			</div>
		</div>
		<div class="form-actions">
			<input id="btnSubmit" class="btn btn-primary" type="submit" value="保 存"/>&nbsp;
			<input id="btnCancel" class="btn" type="button" value="返 回" onclick="history.go(-1)"/>
		</div>
	</form:form>
</body>
</html>