<html>
	<head>
		<meta charset="utf-8">
		<meta name="viewport" content="width=device-width,initial-scale=1">
		<title>jQuery Mobile Bootstrap Theme</title>
		<link rel="stylesheet" href="/static/themes/Bootstrap.css">
		<link rel="stylesheet" href="http://code.jquery.com/mobile/1.4.0/jquery.mobile.structure-1.4.0.min.css" />
		<link rel="stylesheet" href="/static/themes/jquery.mobile.icons.min.css" />
		<script src="http://code.jquery.com/jquery-1.8.2.min.js"></script>
		<script src="http://code.jquery.com/mobile/1.4.0/jquery.mobile-1.4.0.min.js"></script>
	</head>
	<body>
	<div data-role="page" data-theme="a">

	<div data-role="content" data-theme="a">

	<form action="/user/add" method="post">


	<div data-role="fieldcontain">
    						<label for="name">登录名</label>
    						<input type="text" name="userName" id="userName" value=""  />
    					</div>
<div data-role="fieldcontain">
    						<label for="name">密码</label>
    						<input type="text" name="password" id="password" value=""  />
    					</div>
        <div class="ui-body ui-body-b">
						<fieldset class="ui-grid-a">
							<div class="ui-block-a"><button type="submit" data-theme="d">Cancel</button></div>
							<div class="ui-block-b"><button type="submit" data-theme="a">Submit</button></div>
					    </fieldset>
					</div>

    					</form>
    					</div>
    					</div>
</body>
</html>