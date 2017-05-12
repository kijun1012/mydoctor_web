<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<div class="content-wrapper" style="min-height: 1000px;">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<h1>
			심박<small>심박 기록 </small>
		</h1>
		<ol class="breadcrumb">
			<li><a href="/"><i class="fa fa-dashboard"></i>Dashboard</a></li>
			<li class="active">심박</li>
		</ol>
	</section>

	<div class="col-md-6">
		<div class="box box-info">
			<div class="box-header with-border">
				<h3 class="box-title">심박</h3>

				<div class="box-tools pull-right">
					<button type="button" class="btn btn-box-tool"
						data-widget="collapse">
						<i class="fa fa-minus"></i>
					</button>
					<button type="button" class="btn btn-box-tool" data-widget="remove">
						<i class="fa fa-times"></i>
					</button>
				</div>
			</div>
			<div class="box-body">
				<div class="chart">
					<canvas id="canvas"></canvas>
				</div>


			</div>
			<!-- /.box-body -->
		</div>
	</div>
	<div class="col-md-6">


		<div class="input-group">

			<c:if test="${empty heartRates}">

				<h2 class="text-yellow">저장된 데이터가 없습니다.</h2>
			</c:if>

			<c:if test="${not empty heartRates }">
				<div class="input-group-addon">
					<div class="col-md-4">
						<i class="fa fa-calendar"></i> 시작<input type="text" id="fromDate">
					</div>
					<div class="col-md-4">
						종료<input type="text" id="toDate">

					</div>
					<div class="col-md-4">
						<button type="button" class="btn btn-default" id="search">조회하기</button>
					</div>

				</div>
			</c:if>

		</div>




		<table class="table table-striped header-fixed">
			<thead>
				<tr>
					<th>number</th>
					<th>date</th>
					<th>heartRate</th>
				</tr>
			</thead>
			<tbody>
				<c:set var="id" value="1" />
				<c:forEach var="heartRate" items="${heartRates}">

					<tr>
						<td><c:out value="${id}" /></td>
						<td>${heartRate.measurement_time}</td>
						<td>${heartRate.heartRate }</td>
					</tr>
					<c:set var="id" value="${id+1}" />
				</c:forEach>

			</tbody>
		</table>
	</div>
</div>

<script type="text/javascript">
	var result = new Array();
	var time = new Array();
	var number = 1;

	<c:forEach var="heartRate" items = "${heartRates}">
	result.push("${heartRate.heartRate}");
	//time.push("${heartRate.measurement_time}");
	time.push(number);
	number += 1;
	</c:forEach>

	var randomScalingFactor = function() {
		return Math.round(Math.random() * 100)
	};

	var months = [ "January", "February", "March", "April", "May", "June",
			"July", "August", "September", "October", "November", "December" ];
	var lineChart = null;
	var lineChartData = {
		labels : time,
		datasets : [ {
			label : "HeartRate",
			fillColor : "rgba(210, 214, 222, 1)",
			strokeColor : "rgba(210, 214, 222, 1)",
			pointColor : "rgba(210, 214, 222, 1)",
			pointStrokeColor : "#c1c7d1",
			pointHighlightFill : "#fff",
			pointHighlightStroke : "rgba(220,220,220,1)",
			data : result
		} ]

	};

	var lineChartOption = {
		//Boolean - If we should show the scale at all
		showScale : true,
		scaleBeginAtZero : true,

		//Boolean - Whether grid lines are shown across the chart
		scaleShowGridLines : false,
		//String - Colour of the grid lines
		scaleGridLineColor : "rgba(0,0,0,.05)",
		//Number - Width of the grid lines
		scaleGridLineWidth : 1,
		//Boolean - Whether to show horizontal lines (except X axis)
		scaleShowHorizontalLines : true,
		//Boolean - Whether to show vertical lines (except Y axis)
		scaleShowVerticalLines : true,
		//Boolean - Whether the line is curved between points
		bezierCurve : true,
		//Number - Tension of the bezier curve between points
		bezierCurveTension : 0.3,
		//Boolean - Whether to show a dot for each point
		pointDot : true,
		//Number - Radius of each point dot in pixels
		pointDotRadius : 2,
		//Number - Pixel width of point dot stroke
		pointDotStrokeWidth : 1,
		//Number - amount extra to add to the radius to cater for hit detection outside the drawn point
		pointHitDetectionRadius : 20,
		//Boolean - Whether to show a stroke for datasets
		datasetStroke : true,
		//Number - Pixel width of dataset stroke
		datasetStrokeWidth : 2,
		//Boolean - Whether to fill the dataset with a color
		datasetFill : false,

		//Boolean - whether to maintain the starting aspect ratio or not when responsive, if set to false, will take up entire container
		maintainAspectRatio : true,
		//Boolean - whether to make the chart responsive to window resizing
		responsive : true

	};

	$(function() {
		var ctx = document.getElementById("canvas").getContext("2d");
		lineChart = new Chart(ctx).Line(lineChartData, lineChartOption);
	});

	$("canvas").on("click", function(e) {
		var activePoints = lineChart.getPointsAtEvent(e);
		console.log(activePoints);

		for ( var i in activePoints) {
			console.log(activePoints[i].value);
		}
	});

	//기간 입력 JS--------------------------------------------------------------------------

	$('#fromDate').datepicker({
		format : 'yyyy-mm-dd',
		autoclose : true,
		language : 'kr',
		todayHighlight : true

	});
	$('#toDate').datepicker({
		format : 'yyyy-mm-dd',
		autoclose : true,
		language : 'kr',
		todayHighlight : true

	});

	var s = "${pageContext.request.contextPath}/heartrate";

	//s = s + "/search?" + username + "/" + $('#fromDate').val() + "/" + $('#toDate').val();

	$("button#search").on(
			"click",
			function() {

				var username = "${heartRates[0].username}";
				var toDate = $('#toDate').val();
				var fromDate = $('#fromDate').val();

				if (toDate == "" || fromDate == "") {
					alert("날짜를 입력해주세요!");
				} else {
					var url = s + "/search?" + username + "/" + fromDate + "/"
							+ toDate;

					window.location.href = url;
				}
			});
</script>