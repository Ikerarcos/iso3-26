<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
	<head>
		<title><s:text name="application.title"/></title>
		<link href="<s:url value="/css/main.css"/>" rel="stylesheet"
		type="text/css"/>
	</head>
	<body>
		<s:form action="doLogin" method="POST">
			<tr>
				<td colspan="2">Login</td>
			</tr>
			<tr>
				<td colspan="2">
					<s:actionerror />
					<!--<s:fielderror />-->
				</td>
			</tr>
			<s:textfield name="username" label="%{getText('label.login.name')}"/>
			<s:password name="password" label="%{getText('label.login.password')}"/>
			<s:submit value="%{getText('label.login.button')}" align="center"/>
		</s:form>
	</body>

</html>

