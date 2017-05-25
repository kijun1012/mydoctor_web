<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="sf" uri="http://www.springframework.org/tags/form"%>


<div class="content-wrapper" style="min-height: 1000px;">
	<div class="row">
		<div class="col-md-6">
			<div class="container">
				<h4>
					<c:if test="${assignedUser.doctorname == null}">
							의사를 선택해주세요.
						</c:if>

					<c:if test="${assignedUser.doctorname != null}">
							${assignedUser.doctorname} 님이 선택되어 있습니다.
							</br>
						<small>(재 입력시 변경. 공백 입력시 선택 해제 됩니다.)</small>
					</c:if>

					<c:if test="${not empty error}">
						<div style="color: #ff0000">
							<h6>${error}</h6>
						</div>
					</c:if>
				</h4>
			</div>
		</div>
	</div>

	<div class="row">
		<div class="col-md-6">
			<sf:form action="${pageContext.request.contextPath}/chooseDoctor"
				method="post" modelAttribute="assignedUser">

				<input type="hidden" name="username"
					value="${assignedUser.username}" id="username" />

				<div class="input-group margin">
					<sf:input path="doctorname" id="doctorname" class="form-control" />
					<span class="input-group-btn">
						<button type="submit" value="Submit" class="btn btn-info btn-flat">선택</button>
					</span>
				</div>
			</sf:form>
		</div>


		<%-- <div class="col-md-6">
			<!-- DIRECT CHAT WARNING -->
			<div
				class="box box-primary box-solid direct-chat direct-chat-primary">
				<div class="box-header">
					<h3 class="box-title">Direct Chat in a Solid Box</h3>
					<div class="box-tools pull-right">
						<span data-toggle="tooltip" title="3 New Messages"
							class="badge bg-light-blue">3</span>
						<button class="btn btn-box-tool" data-widget="collapse">
							<i class="fa fa-minus"></i>
						</button>
						<button class="btn btn-box-tool" data-toggle="tooltip"
							title="Contacts" data-widget="chat-pane-toggle">
							<i class="fa fa-comments"></i>
						</button>
						<button class="btn btn-box-tool" data-widget="remove">
							<i class="fa fa-times"></i>
						</button>
					</div>
				</div>
				<!-- /.box-header -->
				<div class="box-body">
					<!-- Conversations are loaded here -->
					<div class="direct-chat-messages">
						<!-- Message. Default to the left -->
						<div class="direct-chat-msg">
							<div class="direct-chat-info clearfix">
								<span class="direct-chat-name pull-left">Alexander Pierce</span>
								<span class="direct-chat-timestamp pull-right">23 Jan
									2:00 pm</span>
							</div>
							<!-- /.direct-chat-info -->
							<img class="direct-chat-img"
								src="${pageContext.request.contextPath}/resources/dist/img/user1-128x128.jpg"
								alt="message user image">
							<!-- /.direct-chat-img -->
							<div class="direct-chat-text">Is this template really for
								free? That's unbelievable!</div>
							<!-- /.direct-chat-text -->
						</div>
						<!-- /.direct-chat-msg -->

						<!-- Message to the right -->
						<div class="direct-chat-msg right">
							<div class="direct-chat-info clearfix">
								<span class="direct-chat-name pull-right">Sarah Bullock</span> <span
									class="direct-chat-timestamp pull-left">23 Jan 2:05 pm</span>
							</div>
							<!-- /.direct-chat-info -->
							<img class="direct-chat-img"
								src="${pageContext.request.contextPath}/resources/dist/img/user3-128x128.jpg"
								alt="message user image">
							<!-- /.direct-chat-img -->
							<div class="direct-chat-text">You better believe it!</div>
							<!-- /.direct-chat-text -->
						</div>
						<!-- /.direct-chat-msg -->
					</div>
					<!--/.direct-chat-messages-->

					<!-- Contacts are loaded here -->
					<div class="direct-chat-contacts">
						<ul class="contacts-list">
							<li><a href="#"> <img class="contacts-list-img"
									src="${pageContext.request.contextPath}/resources/dist/img/user1-128x128.jpg"
									alt="Contact Avatar">
									<div class="contacts-list-info">
										<span class="contacts-list-name"> Count Dracula <small
											class="contacts-list-date pull-right">2/28/2015</small>
										</span> <span class="contacts-list-msg">How have you been? I
											was...</span>
									</div> <!-- /.contacts-list-info -->
							</a></li>
							<!-- End Contact Item -->
						</ul>
						<!-- /.contatcts-list -->
					</div>
					<!-- /.direct-chat-pane -->
				</div>
				<!-- /.box-body -->
				<div class="box-footer">
					<form action="#" method="post">
						<div class="input-group">
							<input type="text" name="message" placeholder="Type Message ..."
								class="form-control"> <span class="input-group-btn">
								<button type="button" class="btn btn-primary btn-flat">Send</button>
							</span>
						</div>
					</form>
				</div>
				<!-- /.box-footer-->
			</div>
			<!--/.direct-chat -->
		</div> --%>
	</div>
</div>