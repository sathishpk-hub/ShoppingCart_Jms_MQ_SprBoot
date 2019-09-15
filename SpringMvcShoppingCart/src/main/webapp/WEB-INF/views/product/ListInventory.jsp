<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Inventory Page</title>

<script type="text/javascript"  src="webjars/jquery/2.2.4/jquery.min.js"></script>


<script type="text/javascript">

/* $(document).ready(function() {

	var list = ${list};
	$.each(list, function( index, value ) {
		alert( index + ": " + value );
	});
	
}); */


var productId = '';

function getId(prodId){
	alert("prodId="+prodId) ;
	productId=prodId;
	 alert("productId="+productId) ;
	
	
}

$(document).ready(function() {
	
	  $("#bth-search").click(function (event) {
		  alert("Message here");
		  console.log($("#hidden"+productId).text());
		  //alert($("#choosen_tag").val());
		//stop submit the form, we will post it manually.
	     event.preventDefault();
		
		//just make one alert to make sure function is calling
	    
		 
	      $.post("${pageContext.request.contextPath}/item/sendMsg",
	        {
	          productId: productId
	          
	        },
	        function(data,status){
	        	//below console.log prints data in browser console log. its printing below values
	        	//console.log(data) = {productId: "1", productName: "Apple IPhone 8 plus", productPhoto: "iphone_8_plus.png", productPrice: 1990}
	        	//console.log(status) = success
	        	console.log(data); 
	        	console.log(status); 
	            $('#stage').html('<p> productId: ' +data.productId + '</p>');
	            $('#stage').append('<p>productName : ' + data.productName+ '</p>');
	        }); 
	    });
	
	
});
</script>


</head>
<body>

	<h3>Inventory Page</h3>
	
	<!-- <form class="form-horizontal" id="search-form"> -->
	
	<table cellpadding="2" cellspacing="2" border="1">
		<tr>
			<th>Product Id</th>
			<th>Product Name</th>
			<th>Product Photo</th>
			<th>Product Price(SGD)</th>
			<th>Select Product</th>
		</tr>
		<c:forEach var="product" items="${listProducts}" varStatus="stat">
			<tr>
				<td>${product.productId }</td>
				<td>${product.productName }</td>
				<td><img src="${pageContext.request.contextPath }/resources/static/images/${product.productPhoto }" width="50"></td>
				<td>${product.productPrice }</td>
				<td align="center">
					<a href="${pageContext.request.contextPath }/item/sendMsg/${product.productId}">Choose</a>
				</td>
				
				<%-- <span id="idEspeciality${stat.index}">${product.productId}</span> --%>
				<%-- 
				<td align="center">
					<a href="${pageContext.request.contextPath }/item/buy/${product.productId}">Choose</a>
				</td>
				<input id="hidden${product.productId}"></input>
				<td><button type="submit" id="bth-search" onClick=getId('${product.productId}');>Search</button></td> --%>
				
				
			</tr>	
		</c:forEach>
		
		
		<div id="stage" style="background-color:#cc0;">      
      </div>
      
	</table>
<!-- </form> -->
</body>
</html>