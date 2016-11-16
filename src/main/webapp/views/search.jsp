<html>
<!-- 新 Bootstrap 核心 CSS 文件 -->
<link rel="stylesheet" href="http://cdn.bootcss.com/bootstrap/3.3.0/css/bootstrap.min.css">

<!-- jQuery文件。务必在bootstrap.min.js 之前引入 -->
<script src="http://cdn.bootcss.com/jquery/1.11.1/jquery.min.js"></script>

<!-- 最新的 Bootstrap 核心 JavaScript 文件 -->
<script src="http://cdn.bootcss.com/bootstrap/3.3.0/js/bootstrap.min.js"></script> 
<body>
<nav class="navbar navbar-default" role="navigation">
    <div class="container-fluid">
    <div class="navbar-header">
        <a class="navbar-brand" href="#">泺源派出所</a>
    </div>
    <div>
        <ul class="nav navbar-nav navbar-right"> 
            <li><a href="#"><span class="glyphicon glyphicon-log-in"></span> 登录</a></li> 
        </ul> 
    </div>
    </div>
</nav>

<div class="row">
	<div class="col-lg-12">
		<div style="margin: 60px 0px">
			<h1 class="text-center">泺源派出所警务搜索</h1>
		<div/>
	</div>
</div>
<form>
<div class="row">
	<div class="col-lg-3">
	</div>
	<div class="col-lg-6">
		<div class="input-group">
			<div class="input-group-btn">
				<button type="button" class="btn btn-default 
				dropdown-toggle" data-toggle="dropdown">搜索类型
					<span class="caret"></span>
				</button>
				<ul class="dropdown-menu">
					<li>
						<a href="#">房屋</a>
					</li>
					<li>
						<a href="#">人口</a>
					</li>
					<li>
						<a href="#">单位</a>
					</li>
					<li>
						<a href="#">场所</a>
					</li>
					<li>
						<a href="#">监控</a>
					</li>
				</ul>
			</div><!-- /btn-group -->
			<input type="text" class="form-control" placeholder="搜索词之间请用空格分隔, 如“泺源 泉城广场”">
			<span class="input-group-btn">
				<button class="btn btn-default" type="button">搜索</button>
			</span>
		</div><!-- /input-group -->
	</div><!-- /.col-lg-6 -->
	<div class="col-lg-3">
	</div>
</div><!-- /.row -->
</form>

</body>
</html>
