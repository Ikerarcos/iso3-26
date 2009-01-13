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
	<h1><s:text name="label.lecturer.title"/><s:property value="profe.Nombre"/>(<s:property value="profe.Dni"/>)</h1>
	<s:url action="loginAction!doLogOut"  id="logout" escapeAmp="false">
	</s:url>
	<a href="<s:property value="#logout"/>"><s:text name="label.logout.button"/></a>
	
	
	<table class="borderAll">
		    <tr>
		        <th><s:text name="label.lecturer.Id"/></th>
		        <th><s:text name="label.lecturer.Nombre"/></th>
		        <th><s:text name="label.lecturer.Creditos"/></th>
		        <th><s:text name="label.lecturer.Profesor"/></th>
		        <th><s:text name="label.lecturer.Listado"/></th>
		        <th><s:text name="label.lecturer.Numero"/></th>
		        <th>&nbsp;&nbsp;</th>
		    </tr>
		    <s:iterator value="asigs" status="status">
		        <tr class="<s:if test="#status.even">even</s:if><s:else>odd</s:else>">
		            <td class="nowrap"><s:property value="%{ Id }"/></td>
		            <td class="nowrap"><s:property value="%{ Nombre }"/></td>
		            <td class="nowrap"><s:property value="%{ Creditos }"/></td>
		            <td class="nowrap"><s:property value="profe.nombre"/></td>
		            <!--<s:url action="subjectAction!listUnits" id="numunidades" escapeAmp="false">
						<s:param name="asig.id" value="%{ Id }"/>
					</s:url>-->
		            <td class="nowrap"><s:property value="unidades.size"/></td>
		            <td class="nowrap"><s:property value="alumnos.size"/></td>
		            <td class="nowrap">
		            <s:url action="lecturerAction!listAlumnos"  id="studentslist" escapeAmp="false">
		            	<s:param name="idasig" value="%{ Id }"/>
					</s:url>
					<a href="<s:property value="#studentslist"/>"><s:text name="label.lecturer.button"/></a></td>
		        </tr>
			</s:iterator>
	</table>
	
</body>
</html>
