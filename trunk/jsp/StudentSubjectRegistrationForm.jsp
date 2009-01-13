<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
	<head>
		<link href="<s:url value="/css/main.css"/>" rel="stylesheet"
		type="text/css"/>
		<title><s:text name="application.title"/></title>
		
	</head>
	<body>
		<div class="titleDiv"><s:text name="application.title"/></div>
		</title><h1><s:text name="label.matric.title"/><s:property value="alum.nombre"/>(<s:property value="alum.Dni"/>)</h1>
		<s:url action="loginAction!doLogOut"  id="logout" escapeAmp="false">
		</s:url>
		<a href="<s:property value="#logout"/>"><s:text name="label.logout.button"/></a>
		<s:form action="studentAction!matricularse">
			<tr>
				<td colspan="2">
					<s:actionerror />
					<!--<s:fielderror />-->
				</td>
			</tr>
			<s:select name="idasig" list="asiglistmatric" listKey="id" listValue="name" label="%{getText('label.matric.select')}"/>			
			<s:submit value="%{getText('button.label.submit')}" align="center" />
		    <s:submit value="%{getText('button.label.cancel')}" name="redirect-action:studentAction" align="center"/>
		    
		</s:form>
	</body>

</html>