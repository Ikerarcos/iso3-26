<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="s" uri="/struts-tags" %>
<html>
<head>
    <link href="<s:url value="/css/main.css"/>" rel="stylesheet" type="text/css"/>
	<title><s:text name="application.title"/></title>
</head>
<body>
	<s:actionerror/>
	<div class="titleDiv"><s:text name="application.title"/></div>
	<h1><s:text name="label.subject.title"/><s:property value="asig.Profesor.Nombre"/>(<s:property value="asig.Nombre"/>)</h1>
	
	
	<s:url action="loginAction!doLogOut"  id="logout" escapeAmp="false">
	</s:url>
	<a href="<s:property value="#logout"/>"><s:text name="label.logout.button"/></a>
	
	
	
	<table class="borderAll">
		    <tr>
		        <th><s:text name="label.student.dni"/></th>
		        <th><s:text name="label.student.name"/></th>
		        <th><s:text name="label.student.phone"/></th>
		        <th><s:text name="Id"/></th>
		        <th>&nbsp;&nbsp;</th>
		    </tr>
		    <s:iterator value="alumnos" status="status">
		        <tr class="<s:if test="#status.even">even</s:if><s:else>odd</s:else>">
		            <td class="nowrap"><s:property value="%{ Dni }"/></td>
		            <td class="nowrap"><s:property value="%{ Nombre }"/></td>
		            <td class="nowrap"><s:property value="%{ Telefono }"/></td>
		            <td class="nowrap">
		            <s:url action="subjectMarkingAction!doSubjectMarkingAction" id="marking" escapeAmp="false">
						<s:param name="idasig" value="asig.Id"/>
						<s:param name="idalum" value="%{ Dni }"/>
					</s:url>
		            <a href="<s:property value="#marking"/>"><s:text name="label.evaluacion.Add"/></a>
		            </td>
		        </tr>
			</s:iterator>
			
		
	</table>
	<s:form action="doLecturerAction" >
			<s:submit value="%{getText('button.label.cancel')}" align="center"/>	
	</s:form>
	
</body>
</html>
