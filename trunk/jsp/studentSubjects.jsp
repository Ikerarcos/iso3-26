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
	<h1><s:text name="label.student.title"/><s:property value="alum.Nombre"/>(<s:property value="alum.Dni"/>)</h1>
	
	<s:url action="studentAction!doListaMatricular"  id="listaMatricular" escapeAmp="false">
	</s:url>	
	<a href="<s:property value="#listaMatricular"/>"><s:text name="label.studentmatric.button"/></a>       
	
	<s:url action="subjectAction!doMostrarNotas"  id="mostrarnotas" escapeAmp="false">
		<s:param name="idalumno" value="alum.Dni"/>
	</s:url>
	<a href="<s:property value="#mostrarnotas"/>"><s:text name="label.studentnotas.button"/></a>       
	
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
		    <s:iterator value="asiglist" status="status">
		        <tr class="<s:if test="#status.even">even</s:if><s:else>odd</s:else>">
		            <td class="nowrap"><s:property value="%{ Id }"/></td>
		            <td class="nowrap"><s:property value="%{ Nombre }"/></td>
		            <td class="nowrap"><s:property value="%{ Creditos }"/></td>
		            <td class="nowrap"><s:property value="asig.profe"/></td>
		            <s:url action="doSubjectAction!listUnits" id="numunidades" escapeAmp="false">
						<s:param name="id" value="%{ Id }"/>
					</s:url>
		            <td class="nowrap"><s:property value="#numunidades"/></td>
		            <td class="nowrap"><s:property value="profe.numalumnos"/></td>
		            <td class="nowrap">
		            <s:url action="studentAction!desmatricular"  id="desmatricular" escapeAmp="false">
		            	<s:param name="idasig" value="%{ Id }"/>
					</s:url>
					<a href="<s:property value="#desmatricular"/>"><s:text name="label.studentdesmatric.button"/></a></td>
					<td class="nowrap">
					<s:url action="subjectAction!doMostrarNotas"  id="notas" escapeAmp="false">
						<s:param name="idasig" value="%{ Id }"/>
						<s:param name="idalumno" value="%{ alum.Dni }"/>
					</s:url>
					<a href="<s:property value="#notas"/>"><s:text name="label.studentnotasasig.button"/></a></td>
		        </tr>
			</s:iterator>
	</table>
	
</body>
</html>
