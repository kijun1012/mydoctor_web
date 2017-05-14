<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<div class="col-md-6">
	<div class="box box-success direct-chat direct-chat-success">
		<div class="box-header with-border">
			<h3 class="box-title">데이터 분석 결과</h3>


		</div>
		<!-- /.box-header -->
		<div class="box-body" style="min-height: 300px;">
			<!-- Conversations are loaded here -->
			<div class="direct-chat-messages">


				<!-- Message. Default to the left -->
				<div class="direct-chat-msg">

					<!-- /.direct-chat-info -->
					<img class="direct-chat-img"
						src="${pageContext.request.contextPath}/resources/dist/img/user1-128x128.jpg"
						alt="Message User Image">
					<!-- /.direct-chat-img -->
					<div class="direct-chat-text">혈압 관리가 필요합니다.</div>
					<!-- /.direct-chat-text -->
				</div>

				<div class="direct-chat-msg">

					<!-- /.direct-chat-info -->
					<img class="direct-chat-img"
						src="${pageContext.request.contextPath}/resources/dist/img/user1-128x128.jpg"
						alt="Message User Image">
					<!-- /.direct-chat-img -->
					<div class="direct-chat-text">혈당 관리가 필요합니다.</div>
					<!-- /.direct-chat-text -->
				</div>

				<div class="direct-chat-msg">

					<!-- /.direct-chat-info -->
					<img class="direct-chat-img"
						src="${pageContext.request.contextPath}/resources/dist/img/user1-128x128.jpg"
						alt="Message User Image">
					<!-- /.direct-chat-img -->
					<div class="direct-chat-text">혈압과 혈당 모두 관리가 필요합니다.</div>
					<!-- /.direct-chat-text -->
				</div>


				<!-- Message. Default to the left -->
				<div class="direct-chat-msg">
					<!-- /.direct-chat-info -->
					<img class="direct-chat-img"
						src="${pageContext.request.contextPath}/resources/dist/img/user1-128x128.jpg"
						alt="Message User Image">
					<!-- /.direct-chat-img -->
					<div class="direct-chat-text">건강합니다!</div>
					<!-- /.direct-chat-text -->
				</div>




			</div>


		</div>
		<!-- /.box-body -->

		<!-- /.box-footer-->
	</div>
	<!-- /.box -->
</div>