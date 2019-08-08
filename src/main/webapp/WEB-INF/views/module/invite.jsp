<div class="page-container">

	<!-- BEGIN CONTENT -->
	<div class="page-content-wrapper">
		<!-- BEGIN CONTENT BODY -->
		<!-- BEGIN PAGE HEAD-->
		<div class="page-head">
			<div class="container">
				<!-- BEGIN PAGE TITLE -->
				<div class="page-title">
					<h1>
						User Details
					</h1>
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
							<!-- BEGIN EXAMPLE TABLE PORTLET-->
							<div class="portlet light ">
								<div class="portlet-title">
									<div class="caption font-dark">
										<span class="caption-subject bold uppercase">User
											Table</span>
									</div>
								</div>
								<div class="portlet-body">
									<div class="table-toolbar">
										<div class="row">
											<div class="col-md-6">
												<div class="btn-group">
													<button class="btn sbold green" id="add_new"> Add New
														<i class="fa fa-plus"></i>
													</button>
												</div>
											</div>

										</div>
									</div>
									<table
										class="table table-striped table-bordered table-hover table-checkable order-column"
										id="listMember">
										<thead>
											<tr>
												<th>View</th>
												<th>User Name</th>
												<th>User ID</th>
												<th>Role</th>
												<th>Status</th>
												<th>Action</th>
											</tr>
										</thead>
										<tbody>


										</tbody>
									</table>
								</div>
							</div>
							<!-- END EXAMPLE TABLE PORTLET-->
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


	<!-- END QUICK SIDEBAR -->
</div>
<!-- END CONTAINER -->


<div class="modal fade draggable-modal" id="new_user" tabindex="-1" role="basic" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">Start Dragging Here</h4>
			</div>
			<div class="modal-body">
				<form class="register-form">
					<input type="hidden" name="role_list" id="role_list" value="developer" />
					<input type="hidden" name="memberToken" id="memberToken" />
					<div class="form-group">
						<input class="form-control placeholder-no-fix" type="text" autocomplete="off"
							placeholder="Name of user" name="username" id="username" />
					</div>
					<div class="form-group">
						<input class="form-control placeholder-no-fix" type="text" autocomplete="off"
							placeholder="User ID" name="userid" id="userid" />
					</div>
					<div id="pass">
						<div class="form-group">
							<input class="form-control placeholder-no-fix" type="password" autocomplete="off"
								placeholder="Password" name="password" id="password" />
						</div>
						<div class="form-group">
							<input class="form-control placeholder-no-fix" type="password" autocomplete="off"
								placeholder="Confirm Password" name="cpassword" id="cpassword" />

						</div>
					</div>
					<div class="form-group">

						<select id="multiple" class="form-control select2-multiple"  multiple>
						</select>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn dark btn-outline" data-dismiss="modal"
							id="cancle">Close</button>
						<button type="submit" class="btn green" id="user-save">Save</button>
						<button type="submit" class="btn green" id="user-update">Update</button>
					</div>
				</form>
			</div>

		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>


<div id="viewUser" class="modal fade" tabindex="-1" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">User Detalis</h4>
			</div>
			<div class="modal-body">
				<div class="row">
					<div class="col-md-12">
						<div class="modal-header">

							<br />
							<table style="width: 70%">

								<tr>
									<th>User Name</th>
									<td>
										<div id="username1"></div>
									</td>
								</tr>
								<tr>
									<th>User ID</th>
									<td>
										<div id="userid1"></div>
									</td>
								</tr>
								<tr>
									<th>Role</th>
									<td>
										<div id="role1"></div>
									</td>
								</tr>

							</table>
						</div>

					</div>
				</div>
			</div>

			<div class="modal-footer">
				<button type="button" data-dismiss="modal" class="btn dark btn-outline">Close</button>
			</div>
		</div>
	</div>
</div>