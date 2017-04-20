<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<div class="content-wrapper" style="min-height: 1000px;">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<h1>
			혈압 <small>혈압 기록</small>
		</h1>
		<ol class="breadcrumb">
			<li><a href="/"><i class="fa fa-dashboard"></i>Dashboard</a></li>
			<li class="active">혈압</li>
		</ol>
	</section>

	<div class="col-md-6">
		<div class="box box-info">
			<div class="box-header with-border">
				<h3 class="box-title">혈압</h3>

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

		<table class="table table-striped">
			<thead>
				<tr>
					<th>number</th>
					<th>date</th>
					<th>min</th>
					<th>max</th>
				</tr>
			</thead>
			<tbody>
				<c:set var="id" value="1" />
				<c:forEach var="bloodPressure" items="${bloodPressures}">

					<tr>
						<td><c:out value="${id}" /></td>
						<td>${bloodPressure.date}</td>
						<td>${bloodPressure.diastolic_pressure}</td>
						<td>${bloodPressure.systolic_pressure}</td>
					</tr>
					<c:set var="id" value="${id+1}" />
				</c:forEach>

			</tbody>
		</table>
	</div>
</div>

<script type="text/javascript">
	var min = new Array();
	var max = new Array();
	var time = new Array();
	var number = 1;
	<c:forEach var="bloodPressure" items = "${bloodPressures}">
	min.push("${bloodPressure.diastolic_pressure}");
	max.push("${bloodPressure.systolic_pressure}");
	
	time.push(number);
	number += 1;
	
	// time.push("${bloodPressure.date}");
	</c:forEach>

	var randomScalingFactor = function() {
		return Math.round(Math.random() * 100)
	};

	var months = [ "January", "February", "March", "April", "May", "June",
			"July", "August", "September", "October", "November", "December" ];
	var lineChart = null;
	var lineChartData = {
		labels : time,
		datasets : [
		            {
			label : "max",
			fillColor : "rgba(210, 214, 222, 1)",
			strokeColor : "rgba(210, 214, 222, 1)",
			pointColor : "rgba(210, 214, 222, 1)",
			pointStrokeColor : "#c1c7d1",
			pointHighlightFill : "#fff",
			pointHighlightStroke : "rgba(220,220,220,1)",
			data : max
		},{
			label : "min",
			fillColor : "rgba(210, 214, 222, 1)",
			strokeColor : "rgba(210, 214, 222, 1)",
			pointColor : "rgba(210, 214, 222, 1)",
			pointStrokeColor : "#c1c7d1",
			pointHighlightFill : "#fff",
			pointHighlightStroke : "rgba(220,220,220,1)",
			data : min
		}
		
		]

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
</script>