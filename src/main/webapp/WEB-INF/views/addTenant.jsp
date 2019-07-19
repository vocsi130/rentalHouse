<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<head>
<link rel="stylesheet" href="css/master.css">

</head>
<t:master>
	
	<jsp:attribute name="header">
    </jsp:attribute>
	<jsp:attribute name="footer">
    </jsp:attribute>
	<jsp:body>
		           <form:form modelAttribute="tenant"  methode ="post">
		           <h1 style="text-align: center;">Tenant  Form</h1>
						<div class="row">
				<div class="col-md-6">
                 <div class="form-group">
                                    <label for="firstName">First Name</label>
                                    <form:input type="text" path="firstName"  cssClass="form-control" /><br/>
                                    <form:errors  path="firstName"  cssClass="error" />
                                </div>
                               </div>
                               
                                <div class="col-md-6">
	                         <div class="form-group">
                                <label for="lastName">Last Name</label>
                                 <form:input type="text" path="lastName"  cssClass="form-control" /><br/>
                                  <form:errors type="text" path="lastName" cssClass="error"/>
                             </div>
</div>
  <div class="col-md-6">
	
                                <div class="form-group">
                                    <label for="age">Age</label>
                                    <form:input type="text" path="age"  cssClass="form-control" />
                                    <form:errors type="text" path="age" cssClass="error"/>
                                </div>
                        </div>
                          
                     
		    <div class="col-md-6">
					<div class="form-group">
						<label for="gender">Gender</label><br/> 
						<form:select path="gender" class="form-control" >
						  <option value="">Select Male Or Female</option>
              <option value="male" selected="selected">Male</option>
            <option value="female">Female</option>
        </form:select>
              <form:errors path="gender" cssClass="error"/>
                              
					</div>
				</div>
		   </div>
    <div style="float: right;">

				<a href="/tenantList" class="btn btn-outline-warning"
					style="margin-right: 2em;">Cancel</a>
				<button id="btnSubmit" type="submit" class="btn btn-outline-primary">Add Tenant
					</button>
			</div>
    
    	
		</form:form>
		
    </jsp:body>
</t:master>