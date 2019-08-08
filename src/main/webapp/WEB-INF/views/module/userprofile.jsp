<!-- BEGIN CONTAINER -->
<div class="page-container">
	<!-- BEGIN CONTENT -->
	<div class="page-content-wrapper">
		<!-- BEGIN CONTENT BODY -->
		<!-- BEGIN PAGE HEAD-->

		<div class="page-head">
			<div class="container">
				<!-- BEGIN PAGE TITLE -->
				<div class="page-title">
					<h1>User Profile</h1>
				</div>
				<!-- END PAGE TITLE -->
			</div>
		</div>
		<!-- END PAGE HEAD-->
		<!-- BEGIN PAGE CONTENT BODY -->
		<div class="page-content">
			<div class="container">

				<!-- BEGIN PAGE CONTENT INNER -->
				<div class="page-content-inner">
					<div class="row">
						<div class="col-md-12">
							<!-- BEGIN PROFILE SIDEBAR -->

							<!-- END BEGIN PROFILE SIDEBAR -->
							<!-- BEGIN PROFILE CONTENT -->
							<div class="profile-content">
								<div class="row">
									<div class="col-md-12">
										<div class="portlet light ">
											<div class="portlet-title tabbable-line">
												<div class="caption caption-md">
													<i class="icon-globe theme-font hide"></i> <span
														class="caption-subject font-blue-madison bold uppercase">Profile
														Account</span>
												</div>
												<ul class="nav nav-tabs">
													<li class="active"><a href="#tab_1_1" data-toggle="tab">Personal
															Info</a></li>
													<li><a href="#tab_1_3" data-toggle="tab">Change
															Password</a></li>

												</ul>
											</div>
											<div class="portlet-body">
												<div class="tab-content">
													<!-- PERSONAL INFO TAB -->
													<div class="tab-pane active" id="tab_1_1">
														<form role="form" action="#" id="user-form">
															<div class="form-group">
																<label class="control-label">Client Id</label> <input
																	type="text" placeholder="Client Id"
																	class="form-control" name="memberToken"
																	disabled="disabled" id="token" />
															</div>
															<div class="form-group">
																<label class="control-label">User Name</label> <input
																	type="text" placeholder="User Name" id="username"
																	class="form-control" />
															</div>
															<div class="form-group">
																<label class="control-label">User ID</label> <input
																	type="text" placeholder="User ID" id="userid"
																	class="form-control" />
															</div>
															<div class="form-group">
																<label class="control-label">Role</label>
																<select name="role_list" id="role_list"
																	class="select form-control" disabled="disabled">
																	<option value="">---select role-- </option>
																	<option value="admin">admin</option>
																	<option value="developer">developer</option>
																</select>
															</div>
															<div class="margiv-top-10">
																<a href="javascript:;" class="btn green"
																	id="user-update"> Save Changes </a>
															</div>
														</form>
													</div>
													<!-- END PERSONAL INFO TAB -->

													<!-- CHANGE PASSWORD TAB -->
													<div class="tab-pane" id="tab_1_3">
														<form action="#">
															<div class="form-group">
																<label class="control-label">Current Password</label>
																<input type="password" class="form-control"
																	id="Password" />
															</div>
															<div class="form-group">
																<label class="control-label">New Password</label> <input
																	type="password" class="form-control"
																	id="npassword" />
															</div>
															<div class="form-group">
																<label class="control-label">Re-type New
																	Password</label> <input type="password"
																	class="form-control" id="cpassword" />
															</div>
															<div class="margin-top-10">
																<a href="" class="btn green" id="changepass"> Change
																	Password </a> <a href="" id="cancelpass"
																	class="btn default">
																	Cancel </a>
															</div>
														</form>
													</div>
													<!-- END CHANGE PASSWORD TAB -->

												</div>
											</div>
										</div>
									</div>
								</div>
							</div>
							<!-- END PROFILE CONTENT -->
						</div>
					</div>
				</div>
				<!-- END PAGE CONTENT INNER -->
			</div>
		</div>
		<!-- END PAGE CONTENT BODY -->
		<!-- END CONTENT BODY -->
	</div>
	<!-- END CONTENT -->
	<!-- BEGIN QUICK SIDEBAR -->
	<a href="javascript:;" class="page-quick-sidebar-toggler"> <i class="icon-login"></i>
	</a>

	<!-- END QUICK SIDEBAR -->
</div>
<!-- END CONTAINER -->