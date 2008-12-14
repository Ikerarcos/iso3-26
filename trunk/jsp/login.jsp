<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
	<head>
		<link href="<s:url value="/css/main.css"/>" rel="stylesheet"
		type="text/css"/>
		<title><s:text name="application.title"/></title>
		
	</head>
	<body>
		<div class="titleDiv"><s:text name="application.title"/></div>
		</title><h1><s:text name="label.login.title"/></h1>
		<s:form action="loginAction!doLogin" method="login">
			
			<tr>
				<td colspan="2">
					<s:actionerror />
					<!--<s:fielderror />-->
				</td>
			</tr>
			<s:textfield name="username" label="%{getText('label.login.name')}"/>
			<s:password name="password" label="%{getText('label.login.password')}"/>
			<s:select name="selectedrol.fullName" list="roles" listKey="fullName" listValue="fullName" label="%{getText('label.roles.select')}"/>
			<!--<% session.putValue("rol",request.getParameter("username")); %>-->
			<s:submit value="%{getText('label.login.button')}" align="center"/>
		</s:form>
		
	</body>

</html>

