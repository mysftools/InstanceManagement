<div class="page-container">
	<h2 id="token">${token}</h2>
	<!-- BEGIN CONTENT -->
	<div class="page-content-wrapper">
		<!-- BEGIN CONTENT BODY -->
		<!-- BEGIN PAGE HEAD-->
		<div class="page-head">
			<div class="container">
				<!-- BEGIN PAGE TITLE -->
				<div class="page-title">
					<h1>Instance Details</h1>
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
							<div class="portlet light ">
								<div class="portlet-body">
									<ul class="nav nav-tabs">
										<li class="active">
											<a href="#tab_1_1" data-toggle="tab"> Run Details </a>
										</li>
										<li>
											<a href="#tab_1_2" onclick="colspan()" data-toggle="tab"> Back Up Details
											</a>
										</li>

									</ul>
									<div class="tab-content">
										<div class="tab-pane fade active in" id="tab_1_1">
											<div class="portlet light ">
												<div class="portlet-title">
													<div class="caption font-dark">
														<span class="caption-subject bold uppercase">Instance Run
															Details</span>
													</div>
												</div>
												<div class="portlet-body">
													<table
														class="table table-striped table-bordered table-hover table-checkable order-column"
														id="listinstdetails">
														<thead>
															<tr>

																<th>Instance Name</th>
																<th>Script</th>
																<th>Date</th>
																<th>Total Runs</th>
															</tr>
														</thead>
														<tbody>
														</tbody>
													</table>
												</div>
											</div>
										</div>
										<div class="tab-pane fade" id="tab_1_2">
											<div class="portlet light ">
												<div class="portlet-title">
													<div class="caption font-dark">
														<span class="caption-subject bold uppercase">Instance BackUp
															History</span>
													</div>
												</div>
												<div class="portlet-body">
													<table
														class="table table-striped table-bordered table-hover table-checkable order-column"
														id="bachuphistory">
														<thead>
															<tr>

																<th>Instance Name</th>
																<th>Script</th>
																<th>Date</th>

															</tr>
														</thead>
														<tbody>
														</tbody>
													</table>
												</div>
											</div>
										</div>

									</div>
									<div class="clearfix margin-bottom-20"> </div>

									<div class="tab-content">



									</div>
								</div>
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
</div>


<div id="responsive" class="modal fade" tabindex="-1" aria-hidden="true">
	<div class="modal-dialog">
		<div class="modal-content">
			<div class="modal-header">
				<div style="float:left;">
					<h4><b>Name Of Instance :- &nbsp;&nbsp;&nbsp; </b></h4>
				</div>
				<div style="float: left;">
					<h4 class="title">name</h4>
				</div>
			</div>
			<h4><b style="padding-left: 10px;">Script :- </b></h4>
			<div class="modal-body">
				<div class="row" style="padding-left: 10px;">
					<div class="script1">
						<div class="script"></div>
					</div>
				</div>
			</div>

			<div class="modal-footer">
				<button type="button" data-dismiss="modal" class="btn dark btn-outline" id="closemodel">Close</button>
			</div>
		</div>
	</div>
</div>