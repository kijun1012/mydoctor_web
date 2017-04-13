<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>


<div class="content-wrapper" style="min-height:1000px;">
   <!-- Content Header (Page header) -->
   <section class="content-header">
      <h1>
         	심박수 <small>심박수 기록</small>
      </h1>
      <ol class="breadcrumb">
         <li><a href="/mydoctor/"><i class="fa fa-dashboard"></i>Dashboard</a></li>
         <li class="active">심박수</li>
      </ol>
   </section>

   <div class="col-md-6">
      <div class="box box-info">
         <div class="box-header with-border">
            <h3 class="box-title">맥박</h3>

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
      <input type="button" id="btnAdd" value="add data">
      <table class="table table-striped">
         <thead>
            <tr>
               <th>id</th>
               <th>heartRate</th>
            </tr>
         </thead>
         <tbody>
            <c:forEach var="heartRate" items="${heartRates}">

               <tr>
                  <td>${heartRate.date}</td>
                  <td>${heartRate.heartRate }</td>
               </tr>

            </c:forEach>

         </tbody>
      </table>
   </div>
</div>

<script type="text/javascript">
   var result = new Array();
   var time = new Array();
   
   <c:forEach var="heartRate" items = "${heartRates}">
      result.push("${heartRate.heartRate}");
      time.push("${heartRate.date}");
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
         label : "Electronics",
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

   $("input#btnAdd").on(
         "click",
         function() {
            lineChart.addData([ randomScalingFactor(),
                  randomScalingFactor() ],
                  months[(lineChart.datasets[0].points.length) % 12]);
         });

   $("canvas").on("click", function(e) {
      var activePoints = lineChart.getPointsAtEvent(e);
      console.log(activePoints);

      for ( var i in activePoints) {
         console.log(activePoints[i].value);
      }
   });
</script>