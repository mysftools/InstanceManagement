<div class="page-container">
	<div class="page-content-wrapper">
		<!-- BEGIN CONTENT BODY -->
		<!-- BEGIN PAGE CONTENT BODY -->
		<div class="page-head">
			<div class="container">
				<!-- BEGIN PAGE TITLE -->
				<div class="page-title">
					<h1>
						Back Up
					</h1>
				</div>
				<!-- END PAGE TITLE -->
			</div>
		</div>
		<div class="page-content">
			<div class="container">
				<!-- BEGIN PAGE CONTENT INNER -->
				<div class="page-content-inner">
					<div class="row">
						<div class="col-md-12">
							<!-- BEGIN TICKET DETAILS CONTENT -->
							<div class="app-ticket app-ticket-details app-ticket-config">
								<div class="row">
									<div class="col-md-12">
										<div class="portlet light ">
											<div class="portlet-title tabbable-line">
												<div class="caption caption-md">
													<i class="icon-globe theme-font hide"></i> <span
														class="caption-subject font-blue-madison bold uppercase">Back Up
														Your Data</span>
												</div>
											</div>
											<div class="portlet-body">
												<div class="row">
													<div class="col-md-8">
														<div class="ticket-title bold uppercase font-blue">Instance
															Details</div>
													</div>
												</div>
												<div class="row">
													<div class="col-sm-4 col-xs-6">
														<div class="ticket-counter">
															<h4 id="totalcalls"> </h4>
															<p class="label label-md label-info bold uppercase">
																Total Calls</p>
														</div>
													</div>
													<div class="col-sm-4 col-xs-6">
														<div class="ticket-counter">
															<h4 id="remainingcalls"></h4>
															<p class="label label-md label-success bold uppercase">
																Remaining Calls</p>
														</div>
													</div>
													<div class="col-sm-4 col-xs-6">
														<div class="ticket-counter">
															<h4 id="totalinstanc"></h4>
															<p class="label label-md label-warning bold uppercase">
																Total Instance</p>
														</div>
													</div>

												</div>
												<br />
												<div class="portlet-body form">
													<div id="fileupload">
														<form action="#" class="form-horizontal form-bordered"
															enctype="multipart/form-data" id="upload-form">
															<div class="form-body">
																<div class="form-group">
																	<label class="col-md-3 control-label">Choose File
																	</label>
																	<div class="col-md-9" id="filepath-e">
																		<input type="file" class="form-control"
																			name="filepath" id="filepath" accept=".xml"
																			required>
																		<span id="filepath-required" style="color:red"
																			class="hide">Select XML File </span>
																	</div>
																</div>
																<div class="form-group">
																	<label class="col-md-3 control-label"></label>
																	<div class="col-md-9">
																		<input type="button" value="Submit"
																			id="file-upload" class="btn btn-success">
																	</div>
																</div>
															</div>
														</form>
													</div>
												</div>
												<div class="portlet-body form">
													<!-- BEGIN FORM-->
													<div id="backupform">
														<form action="#" class="form-horizontal form-row-seperated"
															id="backup-form">
															<div class="form-body">
																<div class="form-group">
																	<label class="control-label col-md-3">Back up All
																		Instance</label>
																	<div class="col-md-9">
																		<input type="checkbox" class="make-switch"
																			onchange="valueChanged()" checked=""
																			id="backupstatus" data-on-color="primary"
																			data-off-color="info">
																	</div>
																</div>
																<div id="showinst">
																	<div class="form-group">
																		<label
																			class="control-label col-md-3">Select</label>
																		<div class="col-md-9">
																			<select multiple="multiple"
																				class="multi-select"
																				id="my_multi_select">

																			</select>
																		</div>
																	</div>
																</div>
															</div>
															<div class="form-actions">
																<div class="row">
																	<div class="col-md-offset-3 col-md-9">
																		<button type="submit" class="btn green"
																			id="backup-btn">
																			<i class="fa fa-check"></i> Submit</button>
																	</div>
																</div>
															</div>
														</form>
													</div>
													<!-- END FORM-->
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
</div>