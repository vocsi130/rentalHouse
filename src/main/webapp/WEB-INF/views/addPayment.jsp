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
		           <form:form modelAttribute="payment" methode ="post">
		           <h1 style="text-align: center;">Payment Registration Form</h1>
				<div class="row">
				<div class="col-md-6">
      <div class="form-group">
     		<label for="payDate">Pay Date</label> <br/>
					   <form:input path="payDate" class="form-control"/>
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
      </div>
    </div>
  </div>
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
					</div>
				</div>
			
					
    </div>
    <div style="float: right;">

				<a href="/paymentList" class="btn btn-outline-warning"
					style="margin-right: 2em;">Cancel</a>
				<button id="btnSubmit" type="submit" class="btn btn-outline-primary">Register
					Payment</button>
			</div>
    
    	
		</form:form>
		
    </jsp:body>
</t:master>