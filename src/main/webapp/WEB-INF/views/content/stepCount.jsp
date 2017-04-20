<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<div class="content-wrapper" style="min-height: 1000px;">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<h1>
			걸음<small>걸음 기록</small>
		</h1>
		<ol class="breadcrumb">
			<li><a href="/"><i class="fa fa-dashboard"></i>DashBoard</a></li>
			<li class="active">걸음</li>
		</ol>
	</section>

	<div class="col-md-6">
		<!-- BAR CHART -->
		<div class="box box-success">
			<div class="box-header with-border">
				<h3 class="box-title">Bar Chart</h3>

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
					<th>stepCount</th>
				</tr>
			</thead>
			<tbody>
				<c:set var="id" value="1" />
				<c:forEach var="stepCount" items="${stepCounts}">

					<tr>
						<td><c:out value="${id}" /></td>
						<td>${stepCount.date}</td>
						<td>${stepCount.stepCount }</td>
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
	<c:forEach var="stepCount" items = "${stepCounts}">
	result.push("${stepCount.stepCount}");
	//time.push("${stepCount.date}");
	time.push(number);
	number += 1;
	</c:forEach>

	var randomScalingFactor = function() {
		return Math.round(Math.random() * 100)
	};
	var months = [ "January", "February", "March", "April", "May", "June",
			"July", "August", "September", "October", "November", "December" ];
	var barChart = null;
	var barChartData = {
		labels : time,
		datasets : [ {
			fillColor : "rgba(220,220,220,0.5)",
			strokeColor : "rgba(220,220,220,0.8)",
			highlightFill : "rgba(220,220,220,0.75)",
			highlightStroke : "rgba(220,220,220,1)",
			data : result
		} ]

	};

	var barChartOption = {
		//Boolean - Whether the scale should start at zero, or an order of magnitude down from the lowest value
		scaleBeginAtZero : true,

		max : 10000,
		//Boolean - Whether grid lines are shown across the chart
		scaleShowGridLines : true,
		//String - Colour of the grid lines
		scaleGridLineColor : "rgba(0,0,0,0.05)",
		//Number - Width of the grid lines
		scaleGridLineWidth : 1,
		//Boolean - If there is a stroke on each bar
		barShowStroke : false,
		//Number - Pixel width of the bar stroke
		barStrokeWidth : 2,
		//Number - Spacing between each of the X value sets
		barValueSpacing : 5,
		//Number - Spacing between data sets within X values
		barDatasetSpacing : 1,
		onAnimationProgress : function() {
			console.log("onAnimationProgress");
		},
		onAnimationComplete : function() {
			console.log("onAnimationComplete");
		},
		//Boolean - whether to maintain the starting aspect ratio or not when responsive, if set to false, will take up entire container
		maintainAspectRatio : true,
		//Boolean - whether to make the chart responsive to window resizing
		responsive : true

	}

	$(function() {
		var ctx = document.getElementById("canvas").getContext("2d");
		barChart = new Chart(ctx).Bar(barChartData, barChartOption);
	});

	$("input#btnAdd").on(
			"click",
			function() {
				barChart.addData([ randomScalingFactor() ],
						months[(barChart.datasets[0].bars.length) % 12]);
			});

	$("canvas").on("click", function(e) {
		var activeBars = barChart.getBarsAtEvent(e);
		console.log(activeBars);

		for ( var i in activeBars) {
			console.log(activeBars[i].value);
		}
	});
</script>