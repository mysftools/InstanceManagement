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
						Dashboard <small> dashboard & statistics</small>
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
										<small>TOTAL RUNS</small>
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
										<small>REMAINANG RUNS</small>
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
												<th></th>
												<th>Type</th>
												<th>Name Of Customer</th>
												<th>Api Version </th>
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
							<input class="form-control placeholder-no-fix" type="text" autocomplete="off"
								placeholder="Coustomer Name" name="nameOfCoustomer" id="nameOfCoustomer" />
						</div>
						<div class="form-group" id="nameOfInstance1">
							<input class="form-control placeholder-no-fix" type="text" autocomplete="off"
								placeholder="Instance Name" name="nameOfInstance" id="nameOfInstance" />
						</div>
						<div class="form-group">
							<input class="form-control placeholder-no-fix" type="text" autocomplete="off"
								placeholder="User Name" name="username" id="username" />
						</div>
						<div class="form-group">
							<input class="form-control placeholder-no-fix" type="password" autocomplete="off"
								placeholder="Passsword" name="password" id="password" />
						</div>
						<div class="form-group">
							<input class="form-control placeholder-no-fix" type="text" autocomplete="off"
								placeholder="Security Code" name="securityCode" id="securityCode" />
						</div>
						<div class="form-group">
							<select class="form-control" name="select" id="select">
								<option value="">---Select---</option>
								<option value="true">Sand Box</option>
								<option value="false">Production</option>
							</select>
						</div>
						<div class="form-group">
							<input class="form-control placeholder-no-fix" type="text" placeholder="client Key"
								name="clientkey" id="clientkey" />
						</div>
						<div class="form-group">
							<input class="form-control placeholder-no-fix" type="text" placeholder="client Secreat"
								name="clientSecreat" id="clientSecreat" />
						</div>
						<div class="form-group">
							<input class="form-control placeholder-no-fix" type="text" placeholder="Api version"
								name="apiversion" id="apiversion" value="46.0" />
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
							<textarea class="form-control placeholder-no-fix" style="height: 116px;" autocomplete="off"
								placeholder="Write apex code" name="apex" id="apex"></textarea>
						</div>
						<div class="form-group">
							<input class="form-control placeholder-no-fix" type="number" autocomplete="off"
								placeholder="Enter any number" name="call" id="call" />
						</div>
						<div>
							<div class="progress-info" id="show-api-call">
								<div class="progress" id="pp1">
									<div id="myProgress">
										<div id="myBar"></div>
									</div>
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
						<button type="submit" class="btn green" id="inst-stop">Stop</button>
					</div>
				</form>
			</div>
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>


<div class="modal fade draggable-modal" id="back-up" tabindex="-1" role="basic" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<h4 class="modal-title">Start Dragging Here</h4>
			</div>
			<div class="modal-body">
				<form class="backup-form" enctype="multipart/form-data" action="#">
					<input name="uid" type="hidden" id="uid" value="" />
					<div class="form-group">
						<input type="file" class="form-control" name="filepath" id="filepath" accept=".xml">
						<span id="filepath-required" style="color:red" class="hide">Select XML File </span>
					</div>
					<div class="modal-footer">
						<button type="button" class="btn dark btn-outline" data-dismiss="modal"
							id="cancle">Close</button>
						<button type="submit" class="btn green" id="backup-btn">Save</button>
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
									<th>Customer Name</th>
									<td>
										<div id="viewtoken"></div>
									</td>
								</tr>
								<tr>
									<th>User Name</th>
									<td>
										<div id="username1"></div>
									</td>
								</tr>
								<tr>
									<th>Name Of Instance</th>
									<td>
										<div id="nameOfInstanced"></div>
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
									<th>SandBox</th>
									<td>
										<div id="type"></div>
									</td>
								</tr>
								<tr>
									<th>Api Version</th>
									<td>
										<div id="apiversion1"></div>
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

<div class="modal fade bs-modal-lg" id="price" tabindex="-1" role="dialog" aria-hidden="true">
	<div class="modal-dialog modal-lg">
		<div class="modal-content">
			<div class="modal-header">
				<button type="button" class="close" data-dismiss="modal" aria-hidden="true"></button>
				<h2 class="modal-title" align="center">
					Pricing
				</h2>
			</div>
			<div class="modal-body">
				<div class="portlet light portlet-fit ">
					<div class="portlet-body">
						<div class="pricing-content-2">
							<div class="pricing-table-container">
								<div class="row padding-fix">
									<div class="col-md-3 no-padding">
										<div class="price-column-container border-right border-top border-left">
											<div class="price-table-head price-1">
												<h2 class="uppercase no-margin">Budget</h2>
											</div>
											<div class="price-table-pricing">
												<h3>
													<span class="price-sign">$</span>24</h3>
												<p class="uppercase">per month</p>
											</div>
											<div class="price-table-content">
												<!-- <div class="row no-margin">
													<div class="col-xs-3 text-right">
														<i class="icon-user"></i>
													</div>
													<div class="col-xs-9 text-left uppercase">3 Members</div>
												</div> -->
												<div class="row no-margin">
													<div class="col-xs-3 text-right">
														<i class="fa fa-exchange"></i>
													</div>
													<div class="col-xs-9 text-left uppercase">50 Runns</div>
												</div>
												<!-- <div class="row no-margin">
													<div class="col-xs-3 text-right">
														<i class="icon-screen-smartphone"></i>
													</div>
													<div class="col-xs-9 text-left uppercase">Single Device</div>
												</div>
												<div class="row no-margin">
													<div class="col-xs-3 text-right">
														<i class="icon-refresh"></i>
													</div>
													<div class="col-xs-9 text-left uppercase">Monthly Backups</div>
												</div> -->
											</div>
											<div class="price-table-footer">
												<button type="button" class="btn grey-salsa btn-outline sbold uppercase bold" onclick="purchaseruns(500,50)">Get It</button>
											</div>
										</div>
									</div>
									<div class="col-md-3 no-padding">
										<div class="price-column-container border-top">
											<div class="price-table-head price-1">
												<h2 class="uppercase no-margin">Solo</h2>
											</div>
											<div class="price-table-pricing">
												<h3>
													<span class="price-sign">$</span>39</h3>
												<p class="uppercase">per month</p>
											</div>
											<div class="price-table-content">
												<!-- <div class="row no-margin">
													<div class="col-xs-3 text-right">
														<i class="icon-user"></i>
													</div>
													<div class="col-xs-9 text-left uppercase">5 Members</div>
												</div> -->
												<div class="row no-margin">
													<div class="col-xs-3 text-right">
														<i class="fa fa-exchange"></i>
													</div>
													<div class="col-xs-9 text-left uppercase">100 Runns</div>
												</div>
												<!-- <div class="row no-margin">
													<div class="col-xs-3 text-right">
														<i class="icon-screen-smartphone"></i>
													</div>
													<div class="col-xs-9 text-left uppercase">Single Device</div>
												</div>
												<div class="row no-margin">
													<div class="col-xs-3 text-right">
														<i class="icon-refresh"></i>
													</div>
													<div class="col-xs-9 text-left uppercase">Weekly Backups</div>
												</div> -->
											</div>
											<div class="price-table-footer">
												<button type="button" class="btn grey-salsa btn-outline sbold uppercase bold" onclick="purchaseruns(1000,100)">Get It</button>
											</div>
										</div>
									</div>
									<div class="col-md-3 no-padding">
										<div class="price-column-container featured-price border-top">
											<div class="price-feature-label uppercase bg-green-jungle">Best Value</div>
											<div class="price-table-head price-2">
												<h2 class="uppercase no-margin">Start up</h2>
											</div>
											<div class="price-table-pricing">
												<h3>
													<span class="price-sign">$</span>59</h3>
												<p class="uppercase">per month</p>
											</div>
											<div class="price-table-content">
												<!-- <div class="row no-margin">
													<div class="col-xs-3 text-right">
														<i class="icon-user-follow"></i>
													</div>
													<div class="col-xs-9 text-left uppercase">20 Members</div>
												</div> -->
												<div class="row no-margin">
													<div class="col-xs-3 text-right">
														<i class="fa fa-exchange"></i>
													</div>
													<div class="col-xs-9 text-left uppercase">500 Runns</div>
												</div>
												<!-- <div class="row no-margin">
													<div class="col-xs-3 text-right">
														<i class="icon-cloud-download"></i>
													</div>
													<div class="col-xs-9 text-left uppercase font-green sbold">Cloud Syncing</div>
												</div>
												<div class="row no-margin">
													<div class="col-xs-3 text-right">
														<i class="icon-refresh"></i>
													</div>
													<div class="col-xs-9 text-left uppercase font-green sbold">Daily Backups</div>
												</div> -->
											</div>
											<div class="price-table-footer">
												<button type="button" class="btn green featured-price uppercase" onclick="purchaseruns(5000,500)">Get it now!</button>
											</div>
										</div>
									</div>
									<div class="col-md-3 no-padding">
										<div class="price-column-container border-top border-right">
											<div class="price-table-head price-3">
												<h2 class="uppercase no-margin">Enterprise</h2>
											</div>
											<div class="price-table-pricing">
												<h3>
													<span class="price-sign">$</span>128</h3>
												<p class="uppercase">per month</p>
											</div>
											<div class="price-table-content">
												<!-- <div class="row no-margin">
													<div class="col-xs-3 text-right">
														<i class="icon-users"></i>
													</div>
													<div class="col-xs-9 text-left uppercase">100 Members</div>
												</div> -->
												<div class="row no-margin">
													<div class="col-xs-3 text-right">
														<i class="fa fa-exchange"></i>
													</div>
													<div class="col-xs-9 text-left uppercase font-green sbold">1000 Runns</div>
												</div>
												<!-- <div class="row no-margin">
													<div class="col-xs-3 text-right">
														<i class="icon-cloud-download"></i>
													</div>
													<div class="col-xs-9 text-left uppercase">Cloud Syncing</div>
												</div>
												<div class="row no-margin">
													<div class="col-xs-3 text-right">
														<i class="icon-refresh"></i>
													</div>
													<div class="col-xs-9 text-left uppercase">Weekly Backups</div>
												</div> -->
											</div>
											<div class="price-table-footer">
												<button type="button" class="btn grey-salsa btn-outline sbold uppercase bold" onclick="purchaseruns(10000,1000)">Get It</button>
											</div>
										</div>
									</div>
								</div>
							</div>
						</div>
					</div>
				</div>
			</div>		
		</div>
		<!-- /.modal-content -->
	</div>
	<!-- /.modal-dialog -->
</div>
<script src="https://checkout.razorpay.com/v1/checkout.js"></script>