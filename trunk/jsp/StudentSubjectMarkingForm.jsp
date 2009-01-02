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
	<h1><s:text name="label.marksubject.title"/><s:property value="alum.Nombre"/>(<s:property value="asig.Nombre"/>)</h1>
	<s:url action="loginAction!doLogOut"  id="logout" escapeAmp="false">
	</s:url>
	<a href="<s:property value="#logout"/>"><s:text name="label.logout.button"/></a>
	<table class="borderAll">
		    <tr>
		        <th><s:text name="label.student.dni"/></th>
		        <th><s:text name="label.student.name"/></th>
		        <th><s:text name="label.student.phone"/></th>
		        
		        <th>&nbsp;&nbsp;</th>
		    </tr>
	    
	        <tr class="<s:if test="#status.even">even</s:if><s:else>odd</s:else>">
	            <td class="nowrap"><s:property value="alum.Dni"/></td>
	            <td class="nowrap"><s:property value="alum.Nombre"/></td>
	            <td class="nowrap"><s:property value="alum.Telefono"/></td>
	            
	        </tr>
			
	</table>
	
	<BR>
	
	<BR>
	<table class="borderAll">
		    <tr>
		        <th><s:text name="label.subject.Id"/></th>
		        <th><s:text name="label.subject.Name"/></th>
		        <th><s:text name="label.subject.Credits"/></th>
		        <th><s:text name="label.subject.Lecturer"/></th>
		        <th><s:text name="label.subject.NumStudents"/></th>
		        
		        <th>&nbsp;&nbsp;</th>
		    </tr>
	    
	        <tr class="<s:if test="#status.even">even</s:if><s:else>odd</s:else>">
	            <td class="nowrap"><s:property value="asig.Id"/></td>
	            <td class="nowrap"><s:property value="asig.Nombre"/></td>
	            <td class="nowrap"><s:property value="asig.Creditos"/></td>
	            <td class="nowrap"><s:property value="asig.Profesor.Nombre"/></td>
	            <td class="nowrap"><s:property value="asig.NumAlumnos"/></td>
	            
	            
	        </tr>
			
	</table>
	
	
	<BR>
	<BR>
	<s:form action="subjectMarkingAction" >
			
			<tr>
				<td colspan="2">
					<s:actionerror />
					<!--<s:fielderror />-->
				</td>
			</tr>
			<s:hidden name="idasig" value="asig.Id"/>
			<s:hidden name="idalum" value="alum.Dni"/>

			<s:textfield name="concepto" label="%{getText('label.evaluacion.Concept')}"/>
			<s:textfield name="nota" label="%{getText('label.evaluacion.Mark')}"/>
			<s:submit value="%{getText('button.label.submit')}" align="center"/><!--  <a href="<s:property value="#lo q sea"/>"></a>-->
			<s:submit value="%{getText('button.label.cancel')}" name="redirect-action:lecturerAction"/>
		
	</s:form>
	
</body>
</html>
