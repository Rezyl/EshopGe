<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="/resources/css/bootstrap.min.css" rel="stylesheet" type="text/css">
        <title>Home</title>
    </head>
    <body>
         <script src="http://code.jquery.com/jquery.js"></script>
        <script src="/resources/js/bootstrap.min.js"></script>
        <h1>Hello World!</h1>
        <p>This is the homepage!</p>

        <form class="form-horizontal" role="form">
            <div class="form-group">
                <label for="email1" class="col-sm-2 control-label">Email</label>
                <div class="col-sm-10">
                    <input type="email" class="form-control" id="email1" placeholder="Email">
                </div>
            </div>
            <div class="form-group">
                <label for="heslo1" class="col-sm-2 control-label">Heslo</label>
                <div class="col-sm-10">
                    <input type="password" class="form-control" id="heslo1" placeholder="Heslo">
                </div>
            </div>
            <div class="form-group">
                <div class="col-sm-offset-2 col-sm-10">
                    <button type="submit" class="btn btn-default">Potvrdit</button>
                </div>
            </div>
        </form>

    </body>
</html>
