<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="t" tagdir="/WEB-INF/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>

<t:master>
	<jsp:attribute name="header">
    </jsp:attribute>
	<jsp:attribute name="footer">
    </jsp:attribute>
	<jsp:body>
		           <form:form modelAttribute="house"  methode ="post">
		           <h1 style="text-align: center;">House  Form</h1>
				<div class="row">
				<div class="col-md-6">
      <div class="form-group">
     		<label for="builtDate">Build Date</label> <br/>
					   <form:input path="builtDate" class="form-control"/>
				 </div>
    </div>
    <div class="col-md-6">
						 <div class="form-group">
                			<label for="squareFt">Square</label><br/>
						<form:input path="squareFt" class="form-control"/> 
					</div>
				</div>
	
	<div class="col-md-6">
						 <div class="form-group">
                			<label for="street">Street</label><br/>
						<form:input path="address.street" class="form-control"/> 
					</div>
				</div>
	
	<div class="col-md-6">
						 <div class="form-group">
                			<label for="city">City</label><br/>
						<form:input path="address.city" class="form-control"/> 
					</div>
				</div>
	
	<div class="col-md-6">
						 <div class="form-group">
                			<label for="state">State</label><br/>
						<form:input path="address.state" class="form-control"/> 
					</div>
				</div>
	
	<div class="col-md-6">
						 <div class="form-group">
                			<label for="zip">Zip Code</label><br/>
						<form:input path="address.zip" class="form-control"/> 
					</div>
				</div>
	
	<div class="col-md-6">
						 <div class="form-group">
                			<label for="lotSize">Lot Size</label><br/>
						<form:input path="lotSize" class="form-control"/> 
					</div>
				</div>
	
		    </div>
    <div style="float: right;">

				<a href="/houseList" class="btn btn-outline-warning"
					style="margin-right: 2em;">Cancel</a>
				<button id="btnSubmit" type="submit" class="btn btn-outline-primary">Add House
					</button>
			</div>
    
    	
		</form:form>
		
    </jsp:body>
</t:master>