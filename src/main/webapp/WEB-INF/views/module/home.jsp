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
						Dashboard <small>dashboard & statistics</small>
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
						<div class="col-lg-4 col-md-4 col-sm-6 col-xs-12">
							<div class="dashboard-stat2 ">
								<div class="display">
									<div class="number">
										<h3 class="font-green-sharp">
											<span id="fc">0</span>
										</h3>
										<small>TOTAL CALLS</small>
									</div>
									<div class="icon">
										<i class="icon-pie-chart"></i>
									</div>
								</div>

							</div>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-6 col-xs-12">
							<div class="dashboard-stat2 ">
								<div class="display">
									<div class="number">
										<h3 class="font-red-haze">
											<span id="sc">0</span>
										</h3>
										<small>REMAINANG CALLS</small>
									</div>
									<div class="icon">
										<i class="icon-pie-chart"></i>
									</div>
								</div>

							</div>
						</div>
						<div class="col-lg-4 col-md-4 col-sm-6 col-xs-12">
							<div class="dashboard-stat2 ">
								<div class="display">
									<div class="number">
										<h3 class="font-blue-sharp">
											<span id="tc"></span>
										</h3>
										<small>Total Instance</small>
									</div>
									<div class="icon">
										<i class="icon-basket"></i>
									</div>
								</div>

							</div>
						</div>
					</div>
					<div class="row">
						<div class="col-md-12">
							<!-- BEGIN EXAMPLE TABLE PORTLET-->
							<div class="portlet light ">
								<div class="portlet-title">
									<div class="caption font-dark">
										<span class="caption-subject bold uppercase">Instance
											Table</span>
									</div>
								</div>
								<div class="portlet-body">
									<div class="table-toolbar">
										<div class="row">
											<div class="col-md-6">
												<div class="btn-group">
													<button class="btn sbold green" id="new_user"
														<% if(!session.getAttribute("role").equals("admin")){ %>
														disabled="disabled" <%} %>> Add New <i class="fa fa-plus"></i>
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
												<th>Type</th>
												<th>Name Of Instance</th>
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


<div class="modal fade draggable-modal" id="new_inst" tabindex="-1" role="basic" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">

				<h4 class="modal-title">Start Dragging Here</h4>
			</div>
			<div class="modal-body">
				<form class="register-form" action="#">
					<input name="uid" type="hidden" id="uid" value="" />
					<div id="instence">
						<div class="form-group">
							<label>Security Code</label> <input class="form-control placeholder-no-fix" type="text"
								autocomplete="off" placeholder="SecurityCode" name="securityCode" id="securityCode" />

						</div>

						<div class="form-group">
							<label>Type</label> <select class="form-control" name="select" id="select">
								<option value="">---Select---</option>
								<option value="Sand Box">Sand Box</option>
								<option value="Production">Production</option>

							</select>
						</div>
						<div class="form-group" id="nameOfInstance1">
							<label>Name Of Instance</label> <input class="form-control placeholder-no-fix" type="text"
								autocomplete="off" placeholder="nameOfInstance" name="nameOfInstance"
								id="nameOfInstance" />
						</div>
						<div class="form-group">
							<label>Client Key</label> <input class="form-control placeholder-no-fix" type="text"
								placeholder="clientKey" name="clientkey" id="clientkey" />

						</div>
						<div class="form-group">
							<label>Client Secreat</label> <input class="form-control placeholder-no-fix" type="text"
								placeholder="clientSecreat" name="clientSecreat" id="clientSecreat" />

						</div>
					</div>
					<div id="apex1">

						<table style="width: 70%">
							<tr>
								<th>Name of Instance</th>
								<td>
									<div id="instname"></div>
								</td>
							</tr>
						</table>
						<br />
						<div class="form-group">
							<label>Apex</label>
							<textarea class="form-control placeholder-no-fix" style="height: 116px;" autocomplete="off"
								placeholder="Write apex code" name="apex" id="apex"></textarea>
						</div>
						<div class="form-group">
							<label>NO of calls</label> <input class="form-control placeholder-no-fix" type="number"
								autocomplete="off" placeholder="Enter any number" name="call" id="call" />
						</div>
						<div>
							<div class="progress-info" id="show-api-call">
								<div class="progress">
									<span>df</span>
									<span id="p" style="width: 6%;" class="progress-bar progress-bar-success red-haze">
									</span>

								</div>
								<div class="status-title" id="pvalue"></div>
							</div>
						</div>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn dark btn-outline" data-dismiss="modal"
							id="cancle">Close</button>
						<button type="submit" class="btn green" id="inst">Save</button>
						<button type="submit" class="btn green" id="inst-update">Update</button>
						<button type="submit" class="btn green" id="inst-run">Run</button>
					</div>
				</form>
			</div>

		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>




<div id="viewinst" class="modal fade" tabindex="-1" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">Instance Detalis</h4>
			</div>
			<div class="modal-body">
				<div class="row">
					<div class="col-md-12">
						<div class="modal-header">
							<h4 class="modal-title">General Detalis</h4>
							<br />

							<table style="width: 70%">

								<tr>
									<th>User id</th>
									<td>
										<div id="viewtoken"></div>
									</td>
								</tr>
								<tr>
									<th>InstanceToken</th>
									<td>
										<div id="instToken"></div>
									</td>
								</tr>
								<tr>
									<th>Security Code</th>
									<td>
										<div id="securityCode1"></div>
									</td>
								</tr>
								<tr>
									<th>Type</th>
									<td>
										<div id="type"></div>
									</td>
								</tr>
								<tr>
									<th>Name Of Instance</th>
									<td>
										<div id="nameOfInstanced"></div>
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