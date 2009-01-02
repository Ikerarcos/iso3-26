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
	<h1><s:text name="label.student.title"/><s:property value="alum.Nombre"/></h1>
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
		        <th><s:text name="label.evaluacion.Subject"/></th>
		        <th><s:text name="label.evaluacion.Id"/></th>
		        <th><s:text name="label.evaluacion.Concept"/></th>
		        <th><s:text name="label.evaluacion.Note"/></th>
		        
		        <th>&nbsp;&nbsp;</th>
		    </tr>
		    <s:iterator value="setallnotas" status="status">
		        <tr class="<s:if test="#status.even">even</s:if><s:else>odd</s:else>">
		            <td class="nowrap"><s:property value="%{ Asig.Nombre }"/></td>
		            <td class="nowrap"><s:property value="%{ Id }"/></td>
		            <td class="nowrap"><s:property value="%{ Concepto }"/></td>
		            <td class="nowrap"><s:property value="%{ Nota }"/></td>
		            
		        </tr>
			</s:iterator>
	</table>
	<s:form action="studentAction" method="listing">
		    
		    <s:submit value="%{getText('button.label.cancel')}"/>
		    
	</s:form>
	
</body>
</html>
