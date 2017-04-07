﻿<%@ page import="test.AnalysisResult" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en" class="app">
<head>
    <meta charset="utf-8"/>
    <title>Testing Tool</title>
    <meta name="description"
          content="app, web app, responsive, admin dashboard, admin, flat, flat ui, ui kit, off screen nav"/>
    <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1"/>
    <link rel="stylesheet" href="css/app.v2.css" type="text/css"/>
    <link href="http://libs.baidu.com/fontawesome/4.0.3/css/font-awesome.css" rel="stylesheet">
</head>
<body>
<section class="vbox">
    <header class="bg-dark dk header navbar navbar-fixed-top-xs">
        <div class="navbar-header aside-md"><a href="#" class="navbar-brand" data-toggle="fullscreen">Software
            Testing</a></div>
        <ul class="nav navbar-nav hidden-xs">
            <li>
                <div class="m-t m-l"><h5>Designed by blw</h5></div>
            </li>
        </ul>
    </header>
    <section>
        <section class="hbox stretch"> <!-- .aside -->
            <aside class="bg-light lter b-r aside-md hidden-print" id="nav">
                <section class="vbox">
                    <header class="header bg-primary lter text-center clearfix">
                        <div class="btn-group">
                            <button type="button" class="btn btn-sm btn-dark btn-icon" title="New project"><i
                                    class="fa fa-plus"></i></button>
                            <div class="btn-group hidden-nav-xs">
                                <button type="button" class="btn btn-sm btn-primary dropdown-toggle"
                                        data-toggle="dropdown"> Switch Project <span class="caret"></span></button>
                                <ul id="select" class="dropdown-menu text-left">
                                    <li><a href="javascript:"
                                           onclick="document.getElementById('project').value = 'Triangle';">Triangle</a>
                                    </li>
                                    <li><a href="javascript:"
                                           onclick="document.getElementById('project').value = 'Date';">Date</a></li>
                                    <li><a href="javascript:"
                                           onclick="document.getElementById('project').value = 'Salary';">Sale</a></li>
                                </ul>
                            </div>
                        </div>
                    </header>
                    <section class="w-f scrollable">
                        <div class="slim-scroll" data-height="auto" data-disable-fade-out="true" data-distance="0"
                             data-size="5px" data-color="#333333">
                            <nav class="nav-primary hidden-xs">

                                <form class="nav" method="post" action="Test" id="form" enctype="multipart/form-data">
                                    <i class="fa fa-dashboard icon" style="margin-top: 20px; margin-left: 5% ">
                                        <b class="bg-danger"></b>
                                    </i>
                                    <input class="form-control" type="text" id="project" name="project"
                                           style="width: 90%; margin-left: 5%; margin-right: 5%"
                                           placeholder="Project name"
                                           value="<%= request.getAttribute("project") == null ? "" : (String)request.getAttribute("project") %>"/>

                                    <i class="fa fa-file-text icon" style="margin-top: 20px; margin-left: 5% ">
                                        <b class="bg-primary"></b>
                                    </i>
                                    <a href="javascript:" class="a-upload"
                                       style="width: 90%; margin-left: 5%; margin-right: 5%">
                                        <input type="file" name="file" id="file"/>
                                        <div style="font-size: 14px ">Select file</div>
                                    </a>
                                    <i class="fa fa-columns icon" style="margin-top: 20px; margin-left: 5%">
                                        <b class="bg-warning"></b>
                                    </i>
                                    <input type="text" class="form-control" id="tester" name="tester"
                                           style="width: 90%; margin-left: 5%; margin-right: 5%" placeholder="Tester"
                                           value="<%= request.getAttribute("tester") == null ? "" : (String)request.getAttribute("tester") %>"
                                           data-required="true"/>

                                    <button type="button" class="btn btn-success btn-s-xs"
                                            onclick="document.getElementById('form').submit();"
                                            style="margin-top: 20px; margin-left: 29%">Submit
                                    </button>
                                </form>

                            </nav>
                            <!-- / nav --> </div>
                    </section>
                </section>
            </aside>
            <!-- /.aside -->
            <section id="content">
                <section class="vbox">
                    <section class="scrollable padder">

                        <div class="m-b-md">
                            <p class="m-b-none" style="margin-top: 20px">Here you can see the correctness and the
                                testing use cases ...</p>
                        </div>

                        <div class="row">
                            <% AnalysisResult analysisResult = (AnalysisResult) request.getAttribute("analysisResult"); %>
                            <div class="col-sm-12">
                                <form data-validate="parsley">
                                    <section class="panel panel-default">
                                        <header class="panel-heading"><span class="h4">Result Analysis</span>
                                        </header>
                                        <div class="form-group"
                                             style="margin-top: 20px; margin-left: 5%; margin-right: 5%">
                                            <label>Number of Testing Use Case</label>
                                            <input type="text" class="form-control" placeholder="Disabled input here..."
                                                   disabled
                                                   value="<%= analysisResult == null ? "" : analysisResult.getTotalUsecase() %>">
                                        </div>
                                        <div class="form-group"
                                             style="margin-top: 20px; margin-left: 5%; margin-right: 5%">
                                            <label>Number of Right</label>
                                            <input type="text" class="form-control" placeholder="Disabled input here..."
                                                   disabled
                                                   value="<%= analysisResult == null ? "" : analysisResult.getRightNum()%>">
                                        </div>
                                        <div class="form-group"
                                             style="margin-top: 20px; margin-left: 5%; margin-right: 5%">
                                            <label>Number of Wrong</label>
                                            <input type="text" class="form-control" placeholder="Disabled input here..."
                                                   disabled
                                                   value="<%= analysisResult == null ? "" : analysisResult.getWrongNum()%>">
                                        </div>
                                        <div class="form-group"
                                             style="margin-top: 20px; margin-left: 5%; margin-right: 5%">
                                            <label>Percentage</label>
                                            <input type="text" class="form-control" placeholder="Disabled input here..."
                                                   disabled
                                                   value="<%= analysisResult == null ? "" : analysisResult.getPercentage()%>">
                                        </div>
                                        <footer class="panel-footer text-right bg-light lter">
                                            <a type="button" class="btn btn-success btn-s-xs"
                                                    style="margin-right: 3.5%" href="Test?filename=<%= (String)request.getAttribute("resultFile") %>">Excel
                                            </a>
                                        </footer>

                                    </section>
                                </form>
                            </div>
                        </div>

                    </section>
                </section>
            </section>
        </section>
    </section>
</section>
</body>
<script src="js/app.v2.js"></script>
</html>
