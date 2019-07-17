<div class="page-header">
	<!-- BEGIN HEADER TOP -->
	<div class="page-header-top">
		<div class="container">
			<!-- BEGIN LOGO -->
			<div class="page-logo">

			</div>
			<!-- END LOGO -->
			<a href="javascript:;" class="menu-toggler"></a>
			<!-- BEGIN TOP NAVIGATION MENU -->
			<div class="top-menu">
				<ul class="nav navbar-nav pull-right">
					<!-- BEGIN USER LOGIN DROPDOWN -->
					<li class="dropdown dropdown-user dropdown-dark"><a href="javascript:;" class="dropdown-toggle"
							data-toggle="dropdown" data-hover="dropdown" data-close-others="true"> <span
								class="username username-hide-mobile">Nick</span> <i class="icon-logout"></i>
						</a>
						<ul class="dropdown-menu dropdown-menu-default">
							<li><a href="/usermanagement"> <i class="icon-user"></i>
									My Profile
								</a></li>
							<li class="divider"></li>
							<li><a href="/logout"> <i class="icon-key"></i> Log Out
								</a></li>
						</ul>
					</li>
					<!-- END USER LOGIN DROPDOWN -->
				</ul>
			</div>
			<!-- END TOP NAVIGATION MENU -->
		</div>
	</div>
	<!-- END HEADER TOP -->
	<!-- BEGIN HEADER MENU -->
	<div class="page-header-menu">
		<div class="container">

			<div class="hor-menu  ">
				<ul class="nav navbar-nav">
					<li class="menu-dropdown classic-menu-dropdown home"><a href="/instancemanagement"> Dashboard
							<span></span>
						</a></li>
						<% if( session.getAttribute("role").equals("admin")){ %>
					<li class="menu-dropdown mega-menu-dropdown backup "><a href="/backup">Back Up<span></span>
						</a></li>
						<% } %>
					<li class="menu-dropdown mega-menu-dropdown userprofile "><a href="/usermanagement">User
							Profile<span></span>
						</a></li>
				</ul>
			</div>
			<!-- END MEGA MENU -->
		</div>
	</div>
	<!-- END HEADER MENU -->
</div>