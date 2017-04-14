<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
   pageEncoding="UTF-8"%>


<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
   <!-- Content Header (Page header) -->
   <section class="content-header">
      <h1>
         My Doctor <small>homepage</small>
      </h1>
      <ol class="breadcrumb">
         <li><a href="#"><i class="fa fa-dashboard"></i> Level</a></li>
         <li class="active">Here</li>
      </ol>
   </section>

   <!-- Main content -->
   <section class="content">



      <div class="row">
         <div class="col-lg-2 col-xs-4">
            <!-- small box -->
            <div class="small-box bg-aqua">
               <div class="inner">
                  <h3>150</h3>

                  <p>심박수</p>
               </div>
               <div class="icon">
                  <i class="fa fa-heartbeat"></i>
               </div>
               <a href="<c:url value="/heartrate"/>" class="small-box-footer">More
                  info <i class="fa fa-arrow-circle-right"></i>
               </a>
            </div>
         </div>
         <div class="col-lg-2 col-xs-4">
            <!-- small box -->
            <div class="small-box bg-aqua">
               <div class="inner">
                  <h3>150</h3>

                  <p>혈압</p>
               </div>
               <div class="icon">
                  <i class="ion ion-bag"></i>
               </div>
               <a href="#" class="small-box-footer">More info <i
                  class="fa fa-arrow-circle-right"></i>
               </a>
            </div>
         </div>
         <div class="col-lg-2 col-xs-4">
            <!-- small box -->
            <div class="small-box bg-aqua">
               <div class="inner">
                  <h3>150</h3>

                  <p>걸음수</p>
               </div>
               <div class="icon">
                  <i class="fa fa-child"></i>
               </div>
               <a href="<c:url value="/stepCount"/>" class="small-box-footer">More
                  info <i class="fa fa-arrow-circle-right"></i>
               </a>
            </div>
         </div>
         <!-- ./col -->
         <div class="col-lg-2 col-xs-4">
            <!-- small box -->
            <div class="small-box bg-green">
               <div class="inner">
                  <h3>
                     53<sup style="font-size: 20px">%</sup>
                  </h3>

                  <p>혈당</p>
               </div>
               <div class="icon">
                  <i class="ion ion-stats-bars"></i>
               </div>
               <a href="#" class="small-box-footer">More info <i
                  class="fa fa-arrow-circle-right"></i></a>
            </div>
         </div>
         <!-- ./col -->
         <div class="col-lg-2 col-xs-4">
            <!-- small box -->
            <div class="small-box bg-yellow">
               <div class="inner">
                  <h3>44</h3>

                  <p>수면</p>
               </div>
               <div class="icon ">
                  <i class="fa fa-bed "></i>
               </div>
               <a href="#" class="small-box-footer">More info <i
                  class="fa fa-arrow-circle-right"></i></a>
            </div>
         </div>
         <!-- ./col -->
         <div class="col-lg-2 col-xs-4">
            <!-- small box -->
            <div class="small-box bg-red">
               <div class="inner">
                  <h3>65</h3>

                  <p>체중</p>
               </div>
               <div class="icon">
                  <i class="ion ion-pie-graph"></i>
               </div>
               <a href="#" class="small-box-footer">More info <i
                  class="fa fa-arrow-circle-right"></i></a>
            </div>
         </div>
         <!-- ./col -->
      </div>
      <div class="row">
         <div class="col-md-6">
            <h4>위험 지수</h4>
            <div class="row">
               <div class="col-md-12">
                  <div class="info-box">
                     <span class="info-box-icon bg-aqua"><i
                        class="fa fa-envelope-o"></i></span>

                     <div class="info-box-content">
                        <span class="info-box-text">심혈관 위험</span> <span
                           class="info-box-number">1,410</span>
                     </div>
                     <!-- /.info-box-content -->
                  </div>
               </div>
            </div>
            <div class="row">
               <div class="col-md-12">
                  <!-- small box -->
                  <div class="info-box">
                     <span class="info-box-icon bg-aqua"><i
                        class="fa fa-envelope-o"></i></span>

                     <div class="info-box-content">
                        <span class="info-box-text">제2형 당뇨병</span> <span
                           class="info-box-number">1,410</span>
                     </div>
                     <!-- /.info-box-content -->
                  </div>
               </div>
            </div>
            <div class="row">
               <div class="col-md-12">
                  <!-- small box -->
                  <div class="info-box">
                     <span class="info-box-icon bg-aqua"><i
                        class="fa fa-envelope-o"></i></span>

                     <div class="info-box-content">
                        <span class="info-box-text">비만도</span> <span
                           class="info-box-number">1,410</span>
                     </div>
                     <!-- /.info-box-content -->
                  </div>
               </div>
            </div>
         </div>


         <div class="col-md-6">
            <div class="box box-warning box-solid">
               <div class="box-header with-border">
                  <h3 class="box-title">조언</h3>

                  <div class="box-tools pull-right">
                     <button type="button" class="btn btn-box-tool"
                        data-widget="collapse">
                        <i class="fa fa-minus"></i>
                     </button>
                  </div>
                  <!-- /.box-tools -->
               </div>
               <!-- /.box-header -->
               <div class="box-body" style="min-height: 300px;">
               술을 먹지 마세요<br> 담배도 피지 마세요
               </div>
               <!-- /.box-body -->
            </div>
            <!-- /.box -->
         </div>

      </div>


   </section>
   <!-- /.content -->
</div>
<!-- /.content-wrapper -->
