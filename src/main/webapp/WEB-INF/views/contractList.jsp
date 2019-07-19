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
		          <div class="container">
    <div>
        <span style="font-size: 1.5em">List of Contracts</span>
        <span style="float:right;">
                <a class="btn btn-primary" href="addContract">New Contract</a>
            	<button id="btnSubmit" type="submit" class="btn btn-light">Print </button>
			</span>
    </div> 
  <!--    <div style="float: right;">

				<a href="/addHouse" class="btn btn-outline-warning"
					style="margin-right: 2em;">Add</a>
				<button id="btnSubmit" type="submit" class="btn btn-outline-primary">Print
					</button>
			</div>-->
    
   
    <table class="table">
        <thead>
        <tr>
            <th scope="col">Date Start</th>
            <th scope="col">Date End</th>
            <th scope="col">Street</th>
            <th scope="col">City</th>
            <th scope="col">State</th>
            <th scope="col">Zip</th>
            <th scope="col">Tenant Name</th>
            <th scope="col">Tenant FirstName</th>
            <th scope="col">Active</th>
            <th scope="col">Amount</th>
            <th scope="col">Operations</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="contract" items="${contracts}">
            <tr>
                <td>${contract.startDate}</td>
                <td>${contract.endDate}</td>
                <td>${contract.residence.address.street}</td>
                <td>${contract.residence.address.city}</td>
                <td>${contract.residence.address.state}</td>
                <td>${contract.residence.address.zip}</td>
                <td>${contract.tenant.firstName}</td>
                <td>${contract.tenant.lastName}</td>
                <td>${contract.active}</td>
                <td>${contract.amount}</td>
            <td>
                <a	href="contracts/ + ${contract.id}">
				<i class="fa fa-edit" style="font-size: 17px; color: blue;"></i>
				</a>
				
                <a	href="contracts/delete/ + ${contract.id}">&nbsp; &nbsp; &nbsp;
				<i class="fa fa-trash" style="font-size: 17px; color: red;"></i>
				</a>
				</td> 
            </tr>
        </c:forEach>
        </tbody>
    </table>
    
</div>

    </jsp:body>
</t:master>