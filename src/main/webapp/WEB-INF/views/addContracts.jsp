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
		           <form:form modelAttribute="contract" methode ="post">
		           <h1 style="text-align: center;">Contract Registration Form</h1>
				<div class="row">
				<div class="col-md-6">
      <div class="form-group">
     		<label for="startDate">Started Date</label> <br/>
					   <form:input path="startDate" class="form-control"/>
					   	   <form:errors path="startDate" cssClass="error"/>
     
				 </div>
    </div>
    <div class="col-md-6">
						 <div class="form-group">
                			<label for="endDate">Date End</label><br/>
						<form:input path="endDate" class="form-control"/> 
						   <form:errors path="endDate" cssClass="error"/>
     
					</div>
				</div>
		
				<div class="col-md-6">
		
		<div class="form-group">
  <label class="control-label">Amount</label>
  <div class="form-group">
    <div class="input-group mb-3">
      <div class="input-group-prepend">
        <span class="input-group-text">$</span>
      </div>
      <form:input path="amount" class="form-control" aria-label="Amount (to the nearest dollar)"/>
      <div class="input-group-append">
        <span class="input-group-text">.00</span>
        <form:errors path="amount" cssClass="error"/>
        
      </div>
    </div>
  </div>
</div>
</div>
				<div class="col-md-6">
					<div class="form-group">
						<label for="active">Active</label><br/> 
						<form:select path="active" class="form-control">
						  <option value="">Select Yes Or No</option>
              <option value="true" selected="selected">true</option>
            <option value="false">false</option>
        </form:select>
        <form:errors path="active" cssClass="error"/>
          				</div>
				</div>
		
			  <div class="col-md-6">
					<div class="form-group">
						<label for="tenant">Tenant</label><br/> 
					<form:select path="tenant" class="form-control">
        				 <c:forEach var="item" items="${tenants}">
            <option value="${item.id}">${item.firstName}</option>
          </c:forEach>
        </form:select>	
            <form:errors path="tenant" cssClass="error"/>
          		
					</div>
				</div>
			
			
			
			
			 <div class="col-md-6">
					<div class="form-group">
						<label for="residence">Residence</label><br/> 
					<form:select path="residence" class="form-control">
        				 <c:forEach var="item" items="${residences}">
            <option value="${item.id}">${item.id}</option>
          </c:forEach>
        </form:select>			
		    <form:errors path="residence" cssClass="error"/>
        			</div>
				</div>
			
    </div>
    <div style="float: right;">

				<a href="/contractList" class="btn btn-outline-warning"
					style="margin-right: 2em;">Cancel</a>
				<button id="btnSubmit" type="submit" class="btn btn-outline-primary">Register
					Contract</button>
			</div>
    
    	
		</form:form>
		
    </jsp:body>
</t:master>