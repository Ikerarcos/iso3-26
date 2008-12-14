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
		
		<s:form action="studentAction!matricular" >
			<s:select name="subject.id" list="asiglistmatric" listKey="name" listValue="name" label="%{getText('label.matric.select')}"/>
			
			<s:submit value="%{getText('button.label.submit')}" />
		    <s:submit value="%{getText('button.label.cancel')}" name="redirect-action:studentAction"/>
		
		</s:form>
		
	</body>

</html>