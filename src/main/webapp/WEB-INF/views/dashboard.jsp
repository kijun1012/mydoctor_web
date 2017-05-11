<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>


<!-- Content Wrapper. Contains page content -->
<div class="content-wrapper">
	<!-- Content Header (Page header) -->
	<section class="content-header">
		<h1>
			My Doctor <small>Dash Board</small>
		</h1>
		<ol class="breadcrumb">
			<li><a href="#"><i class="fa fa-dashboard"></i>DashBoard</a></li>
		</ol>
	</section>

	<!-- Main content -->
	<section class="content">



		<div class="row">
			<div class="col-lg-2 col-xs-4">
				<!-- small box -->
				<div class="small-box bg-aqua">
					<div class="inner">
						<h3>
							<c:if test="${heartRate == null}">
							-
						</c:if>
							<c:if test="${heartRate != null}">
							${heartRate.heartRate}
						</c:if>
						</h3>

						<p>심박수</p>
					</div>
					<div class="icon">
						<i class="ion-ios-pulse-strong"></i>
					</div>
					<a href="<c:url value="/heartrate"/>" class="small-box-footer">More
						info <i class="fa fa-arrow-circle-right"></i>
					</a>
				</div>
			</div>
			<div class="col-lg-2 col-xs-4">
				<!-- small box -->
				<div class="small-box bg-green">

					<div class="inner">
						<h3>
							<c:if test="${bloodPressure == null }">
							-/-
						</c:if>
							<c:if test="${bloodPressure != null }">
							${bloodPressure.HR}/${bloodPressure.HP}
						</c:if>
						</h3>

						<p>혈압</p>
					</div>
					<div class="icon">
						<i class="ion-android-favorite"></i>
					</div>
					<a href="<c:url value="/bloodPressure"/>" class="small-box-footer">More
						info <i class="fa fa-arrow-circle-right"></i>
					</a>
				</div>
			</div>
			<div class="col-lg-2 col-xs-4">
				<!-- small box -->
				<div class="small-box bg-maroon">
					<div class="inner">
						<h3>
							<c:if test="${stepCount == null }">
							-
						</c:if>
							<c:if test="${stepCount != null }">
							${stepCount.stepCount}
						</c:if>
						</h3>

						<p>걸음수</p>
					</div>
					<div class="icon">
						<i class="ion-android-walk"></i>
					</div>
					<a href="<c:url value="/stepCount"/>" class="small-box-footer">More
						info <i class="fa fa-arrow-circle-right"></i>
					</a>
				</div>
			</div>
			<!-- ./col -->
			<div class="col-lg-2 col-xs-4">
				<!-- small box -->
				<div class="small-box bg-aqua">
					<div class="inner">
						<h3>
							<c:if test="${bloodSugar == null }">
							-
						</c:if>
							<c:if test="${bloodSugar != null }">
							${bloodSugar.BG}
						</c:if>
						</h3>

						<p>혈당</p>
					</div>
					<div class="icon">
						<i class="ion-waterdrop"></i>
					</div>
					<a href="<c:url value="/bloodSugar"/>" class="small-box-footer">More
						info <i class="fa fa-arrow-circle-right"></i>
					</a>
				</div>
			</div>
			<!-- ./col -->
			<div class="col-lg-2 col-xs-4">
				<!-- small box -->
				<div class="small-box bg-green">
					<div class="inner">
						<h3>44</h3>

						<p>수면</p>
					</div>
					<div class="icon">
						<i class="ion-ios-alarm"></i>
					</div>
					<a href="#" class="small-box-footer">More info <i
						class="fa fa-arrow-circle-right"></i></a>
				</div>
			</div>
			<!-- ./col -->
			<div class="col-lg-2 col-xs-4">
				<!-- small box -->
				<div class="small-box bg-purple">
					<div class="inner">
						<h3>65</h3>

						<p>체중</p>
					</div>
					<div class="icon">
						<i class="ion-ios-body"></i>
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
							<span class="info-box-icon bg-navy"><i
								class="ion-android-alert"></i></span>

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
							<span class="info-box-icon bg-navy"><i
								class="ion-android-alert"></i></span>

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
							<span class="info-box-icon bg-navy"><i
								class="ion-android-alert"></i></span>

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
						조언1<br>조언2
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
