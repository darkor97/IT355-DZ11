<head>
</head>
<body>

 	<div class="generic-container">
		<table class="table table-hover table-striped">
			<thead>
				<tr>
					<th>Id</th>
					<th>Event Name</th>
				</tr>
			</thead>
			<tbody>
				<c:forEach items="${events}" var="event">
				<tr>
					<td>${event.Id}</td>
					<td>${event.Name}</td>
				</tr>
				</c:forEach>
			</tbody>
		</table>
	</div>
</body>
</html>