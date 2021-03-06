<%-- 
    Document   : index
    Created on : 08-May-2014, 15:40:58
    Author     : Ocheja Patrick Ileanwa
--%>

<%@page import="com.primus.appstates.StudentState"%>
<%@page import="com.primus.appstates.LecturerState"%>
<%@page import="com.primus.appstates.AdministratorState"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<%
    AdministratorState administratorState = AdministratorState.getInstance(request);
    LecturerState lecturerState = LecturerState.getInstance(request);
    StudentState studentState = StudentState.getInstance(request);
    boolean isLoggedIn = false;
%>
<!DOCTYPE html>
<!--[if IE 7]><html class="lt-ie10 lt-ie9 lt-ie8" lang="en"><![endif]-->
<!--[if IE 8]><html class="lt-ie10 lt-ie9" lang="en"><![endif]-->
<!--[if IE 9]><html class="lt-ie10" lang="en"><![endif]-->
<!--[if gt IE 9]><!--><html class="no-js"><!--<![endif]-->
    <head>
        <!-- Basic Page Needs
        ================================================== -->
        <title>University Of Nigeria</title>
        <meta charset="utf-8" />
        <meta name="description" content="Coralix Themes">
        <meta name="author" content="Coralix Themes">

        <!-- Mobile Specific Metas
        ================================================== -->    
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1" />

        <!-- Custom styles 
        ================================================== -->
        <!-- Bootstrap -->
        <link href="css/bootstrap/bootstrap.min.css" rel="stylesheet">
        <!--<link href="css/bootstrap.mi.css" rel="stylesheet">-->
        <link href="css/bootstrap/bootstrap-responsive.min.css" rel="stylesheet">

        <!-- Custom Stylesheet -->
        <link href="css/styles.css" rel="stylesheet" media="screen">

        <link id="link_theme" href="css/green.css" rel="stylesheet">
        <link rel="stylesheet" type="text/css" href="<%=request.getContextPath()%>/usersarea/js/bootstrap-glyphicons.css" />

        <!-- Font icons -->
        <link href="css/fontello.css" rel="stylesheet" >
        <!--[if IE 7]>
        <link href="css/fontello-ie7.css" rel="stylesheet" ><![endif]-->

        <!-- Media Queries -->
        <link href="css/media-queries.css" rel="stylesheet" media="screen">

        <!--[if IE 8 ]><link href="css/ie8.css" rel="stylesheet" media="screen"><![endif]-->
        <link rel="stylesheet" href="css/nivo-slider/nivo-slider.css" type="text/css" media="screen" /><!-- nivo slider -->
        <link rel="stylesheet" href="css/nivo-slider/default.css" type="text/css" media="screen" />

        <!-- HTML5 shim, for IE6-8 support of HTML5 elements 
            ================================================== -->
        <!--[if lt IE 9]>
          <script src="http://html5shim.googlecode.com/svn/trunk/html5.js"></script>
        <![endif]-->

        <link rel="shortcut icon" href="images/favicon.html">

        <!-- Select-->
        <link rel="stylesheet" href="js/select/chosen.css">

        <!--        <link rel="stylesheet" href="css/font-awesome.min.css"/>
                <link rel="stylesheet" href="css/style.css" />-->


        <!--effect bxslider-->
        <link rel="stylesheet" type="text/css" href="js/bxslider/jquery.bxslider.css" />
    </head>


    <body>

        <!--Header-->
        <header>
            <div class="row-fluid">
                <div class="navbar-fixed-top">

                    <!--Top bar-->
                    <section class="topbar">
                        <div class="container">
                            <div class="row-fluid">
                                <h1 class="pull-left"><a title="Home" href="<%=request.getContextPath()%>/">UNN Official Website</a></h1>
                                <a href="#" title="CTA" class="btn pull-right"><i class="icon-phone-1"></i> Call us +2348068674787</a>
                            </div>
                            <div class="row-fluid">
                                <%
                                    if (administratorState.isSignedIn()) {
                                        isLoggedIn = true;
                                %>
                                <span class="pull-right h1">
                                    <span class="glyphicon-chevron-up" style="text-transform: lowercase; font-size: 14px;">Welcome <%=administratorState.getCurrentAdministrator().getFirstName()%></span>
                                    <a id='loginLink' class="external" href="<%=request.getContextPath()%>/logout">
                                        <i class="icon-logout"></i>Logout </a></span>
                                        <%

                                        } else if (studentState.isSignedIn()) {
                                            isLoggedIn = true;
                                        %>
                                <a id='loginLink' class="external" href="<%=request.getContextPath()%>/logout">
                                    Welcome <%=studentState.getCurrentStudent().getStudentName().getFirstName()%><i class="glyphicon glyphicon-share-alt"></i> Logout </a>
                                    <%

                                    } else if (lecturerState.isSignedIn()) {
                                        isLoggedIn = true;
                                    %>
                                <a id='loginLink' class="external" href="<%=request.getContextPath()%>/logout">
                                    Welcome <%=lecturerState.getCurrentLecturer().getLecturerName().getFirstName()%><i class="glyphicon glyphicon-share-alt"></i> Logout </a>
                                    <%

                                    } else {
                                        isLoggedIn = false;%>
                                    <%
                                                }%>
                            </div>
                        </div>
                    </section>
                    <!--End Top bar-->

                    <div class="navbar">
                        <div class="navbar-inner">
                            <div class="container">

                                <!--Mobile Main Menu-->
                                <button type="button" class="btn btn-navbar" data-toggle="collapse" data-target=".nav-collapse">
                                    <span class="icon-bar"></span>
                                    <span class="icon-bar"></span>
                                    <span class="icon-bar"></span>
                                </button>
                                <!--Mobile Main Menu-->

                                <!--Desktop Main Menu-->
                                <div class="nav-collapse collapse">
                                    <ul class="nav">
                                        <li class="active"><a href="#menu-homepage">Home</a></li>
                                        <li><a href="#menu-features">Features</a></li>
                                        <li><a href="#menu-courses">Programs</a></li>
                                        <!--<li><a href="#menu-info">Information</a></li>-->
                                        <!--<li><a href="#menu-pricing">Pricing</a></li>-->
                                        <li><a href="#menu-gallery"> Locations</a></li>
                                        <!--<li><a href="#menu-testimonials">Testimonials</a></li>-->
                                        <!--<li><a href="#menu-map"> Map</a></li>-->
                                        <li><a href="#menu-contact">Contact</a></li>
                                            <%
                                                if (administratorState.isSignedIn() || lecturerState.isSignedIn()
                                                        || studentState.isSignedIn()) {
                                            %>
                                        <li><a id='loginLink' class="external" href="<%=request.getContextPath()%>/view">DASHBOARD </a></li>
                                            <%
                                                }
                                            %>

                                        <!--<li><a href="http://google.com/" class="external">External</a></li>-->
                                    </ul>
                                    <ul class="nav pull-right">
                                        <!--                                        <li class="dropdown">
                                                                                    <a href="#" class="dropdown-toggle social_button" data-toggle="dropdown">Get Social <b class="caret"></b></a>
                                                                                    <ul class="dropdown-menu">
                                                                                        <li><a href="#"><i class="icon-facebook-1"></i>Facebook</a></li>
                                                                                        <li><a href="#"><i class="icon-twitter-1"></i>Twitter</a></li>
                                                                                        <li><a href="#"><i class="icon-googleplus"></i>Google +</a></li>
                                                                                        <li><a href="#"><i class="icon-flickr-circled"></i>Flickr</a></li>
                                                                                        <li><a href="#"><i class="icon-youtube"></i>Youtube</a></li>
                                                                                    </ul>
                                                                                </li>-->
                                        <li>
                                            <%
                                                if (!isLoggedIn) {
                                            %>
                                            <a id='loginLink' class="external" href="<%=request.getContextPath()%>/view">LOGIN</a>
                                            <%
                                                }
                                            %>
                                        </li>
                                    </ul>
                                </div>
                                <!--End Desktop Main Menu-->

                            </div><!--/.container -->
                        </div><!--/.nav-inner -->
                    </div>
                </div>
            </div>
        </header>
        <!--Header-->

        <!-- slider box-->
        <section class="sliderbox">
            <div id="menu-homepage" class="row-fluid">
                <div class="row-fluid">

                    <!-- slider -->
                    <div class="span8 theme-default">
                        <div id="slider" class="nivoSlider">
                            <a href="#"><img src="img/nivo-slider/slider-01.jpg" alt="" title="This is an example of a caption" /></a>
                            <a href="#"><img src="img/nivo-slider/slider-02.jpg" alt="" title="This is an example of a caption2" /></a>
                            <a href="#"><img src="img/nivo-slider/slider-03.jpg" alt="" title="This is an example of a caption2" /></a>

                        </div>
                        <div id="htmlcaption" class="nivo-html-caption">
                            <strong>This</strong> is an example of a <em>HTML</em> caption with <a href="#">a link</a>.
                        </div>
                    </div>
                    <!-- end slider -->

                    <!-- main form -->
                    <div class="span4 course-form-box">
                        <h3>Subscribe to Get information</h3>
                        <p>The Best way to help you reach your goals</p>
                        <form id="curse-form" class="nm" action="" method="post" accept-charset="utf-8">

                            <div  id="loading" style="display: none" class='alert'>
                                <a class='close' data-dismiss='alert'>×</a>
                                Loading
                            </div>
                            <div id="response"></div>

                            <div class="row-fluid content_form">

                                <input class="input-large" type="text" required placeholder="First Name" name="firstname" />


                                <input class="input-large" type="text" required placeholder="Last Name" name="lastname" />


                                <input class="input-large" type="text" required placeholder="*Email" name="email" />


                                <input class="input-large" type="text" required placeholder="Telephone" name="phone" />


                                <select class="input-large chzn-select" name="country">
                                    <option>Select your Country</option>
                                    <option>Nigeria</option>
                                    <option>Others</option>
                                </select>


                                <select class="input-large chzn-select" name="education">
                                    <option>Your Education Level</option>
                                    <option>Basic</option>
                                    <option>Advanced</option>
                                    <option>Proffesional</option>
                                    <option>Expert</option>
                                </select>


                                <select class="input-large chzn-select" name="curse">
                                    <option>Courses you want to learn</option>
                                    <option>Engineering</option>
                                    <option>Physical Sciences</option>
                                    <option>Biological Sciences</option>
                                    <option>Pharmaceutical Sciences</option>
                                    <option>Law</option>
                                    <option>Medical Sciences</option>
                                </select>

                                <button class="btn btn-large btn-primary" type="submit"><i class="icon-edit"></i>request information</button>

                            </div>                                      
                        </form>
                    </div>
                    <!-- end main form -->

                </div>
            </div>
        </section>
        <!-- end slider box-->


        <!-- features -->
        <section class="features generic-section" >
            <div class="container" id="menu-features">

                <!--title of the section-->
                <div class="row-fluid title">
                    <h1>How we stand out</h1>
                    <h3>why you should consider studying in UNN</h3>
                </div>
                <!--end title of the section-->

                <div class="row-fluid">
                    <div class="span3 fea">
                        <img src="img/features/01.png" alt="image study at your  time">
                        <h4>study at your  time</h4>
                        <p>You can now choose to study at any time in the year. Simply know the time that best suits you from our Regular, Part time and Sandwich programs schedules.</p>
                    </div>
                    <div class="span3 fea">
                        <img src="img/features/02.png" alt="image from anywhere you want">
                        <h4>from anywhere you want </h4>
                        <p>Our learning tools enable you to study from anywhere. Distant learning tools are made available online with real time assistance from our qualified lecturers.</p>
                    </div>
                    <div class="span3 fea">
                        <img src="img/features/03.png" alt="image video tutorials">
                        <h4>video tutorials</h4>
                        <p>Videos on lectures and field works are made available to aide understanding. Visual aide is one of the sure ways of passing information easily.</p>
                    </div>
                    <div class="span3 fea">
                        <img src="img/features/04.png" alt="image community support">
                        <h4>community support</h4>
                        <p>As an institution, we support our host community with varieties of social amenities. We see them as the true picture of what we want to be and so help build the society.</p>
                    </div>
                </div>

                <div class="row-fluid">
                    <div class="span3 fea">
                        <img src="img/features/05.png" alt="image classes organization">
                        <h4>classes organization</h4>
                        <p>Our classes are intuitively organised. Learning environment is very conducive and adequate resources are provided for optimum understanding.</p>
                    </div>
                    <div class="span3 fea">
                        <img src="img/features/06.png" alt="image user focus courses">
                        <h4>special courses</h4>
                        <p>As the world continues to grow, we continue to make our own contributions from researches and innovations through special courses that we offer.</p>
                    </div>
                    <div class="span3 fea">
                        <img src="img/features/07.png" alt="image get the news by email">
                        <h4>get the news by email</h4>
                        <p>Be actively involved and informed! Join our mailing list!</p>
                    </div>
                    <div class="span3 fea">
                        <img src="img/features/08.png" alt="image online certificate tests">
                        <h4>iTranscript</h4>
                        <p>With a click on a button, you can have your transcript digitized and posted to your choice destination.</p>
                    </div>
                </div>


            </div>
        </section>
        <!-- end features -->



        <!-- gallery -->
        <section class="gallery generic-section">
            <div class="container" id="menu-gallery">

                <!--title of the section-->
                <div class="row-fluid title">
                    <h1>Meet all our locations</h1>
                    <h3>You can learn online, or at our ground locations where you can learn on campus, online, or a blend of both. </h3>
                </div>
                <!--end title of the section-->

                <div class="row-fluid">
                    <ul class="grid cs-style-4">
                        <li class="span3">
                            <figure>
                                <div><img src="img/gallery/unn-library.jpg" alt="img05"></div>
                                <figcaption>
                                    <h3>Nnamdi Azikiwe Library</h3>
                                    <span>FG</span>
                                    <a class="btn" href="#">Take a look</a>
                                </figcaption>
                            </figure>
                        </li>
                        <li class="span3">
                            <figure>
                                <div><img src="img/gallery/mtn-library.jpg" alt="img06"></div>
                                <figcaption>
                                    <h3>MTN Library</h3>
                                    <span>MTN/Google</span>
                                    <a class="btn" href="#">Take a look</a>
                                </figcaption>
                            </figure>
                        </li>
                        <li class="span3">
                            <figure>
                                <div><img src="img/gallery/ekpo ref.jpg" alt="img02"></div>
                                <figcaption>
                                    <h3>Convocation Arena</h3>
                                    <span>UNN</span>
                                    <a class="btn" href="#">Take a look</a>
                                </figcaption>
                            </figure>
                        </li>
                        <li class="span3">
                            <figure>
                                <div><img src="img/gallery/students-reading.jpg" alt="img04"></div>
                                <figcaption>
                                    <h3>Quiet Study</h3>
                                    <span>SUG</span>
                                    <a class="btn" href="#">Take a look</a>
                                </figcaption>
                            </figure>
                        </li>
                    </ul>
                </div>

                <div class="row-fluid">
                    <ul class="grid cs-style-4">
                        <li class="span3">
                            <figure>
                                <div><img src="img/gallery/students-lab.jpg" alt="img02"></div>
                                <figcaption>
                                    <h3>Biochemistry Laboratory</h3>
                                    <span>FG</span>
                                    <a class="btn" href="#">Take a look</a>
                                </figcaption>
                            </figure>
                        </li>
                        <li class="span3">
                            <figure>
                                <div><img src="img/gallery/students-sports.jpg" alt="img02"></div>
                                <figcaption>
                                    <h3>Game Centre</h3>
                                    <span>Sports Centre</span>
                                    <a class="btn" href="#">Take a look</a>
                                </figcaption>
                            </figure>
                        </li>
                        <li class="span3">
                            <figure>
                                <div><img src="img/gallery/students-walking.jpg" alt="img02"></div>
                                <figcaption>
                                    <h3>Socials</h3>
                                    <span>SUG</span>
                                    <a class="btn" href="#">Take a look</a>
                                </figcaption>
                            </figure>
                        </li>
                        <li class="span3">
                            <figure>
                                <div><img src="img/gallery/students-music.jpg" alt="img02"></div>
                                <figcaption>
                                    <h3>Music</h3>
                                    <span>Music Department</span>
                                    <a class="btn" href="#">Take a look</a>
                                </figcaption>
                            </figure>
                        </li>
                    </ul>
                </div>

            </div>
        </section>
        <!-- end gallery -->



        <!-- testimonials -->
<!--        <section class="testimonials generic-section">
            <div class="container" id="menu-testimonials">

                title of the section
                <div class="row-fluid title">
                    <h1>Loved By Over 25 000 Customer</h1>
                    <h3>What they say about us</h3>
                </div>
                end title of the section

                <div class="row-fluid">
                    <div class="span12">

                         Cataloge Carousel Starts 
                        <div id="myCarousel2" class="carousel slide">
                            <div class="carousel-inner">
                                 Item One Starts  
                                <div class="item active">
                                    <div class="row">
                                        <div class="featurette">
                                            <img class="featurette-image img-circle" src="img/testimonials/testimonials-01.jpg" alt="image">
                                            <div class="text">
                                                <p class="lead">“One of the great needs in ministerial training these days is for living a balance of academic excellence, practical training and godly spiritual development, not only among the students but also in those who teach them. Denver Seminary seems to be achieving and maintaining this balance. I'm impressed with the faculty and the graduates I have met”</p>
                                                <h2 class="featurette-user">amanda Ferdinand <br />- 21 July 2015 - </h2>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                 Item One Ends 
                                 Item Two Starts  
                                <div class="item">
                                    <div class="row">
                                        <div class="featurette">
                                            <img class="featurette-image img-circle" src="img/testimonials/testimonials-02.jpg" alt="image">
                                            <div class="text">
                                                <p class="lead">“One of the great needs in ministerial training these days is for living a balance of academic excellence, practical training and godly spiritual development, not only among the students but also in those who teach them. Denver Seminary seems to be achieving and maintaining this balance. I'm impressed with the faculty and the graduates I have met”</p>
                                                <h2 class="featurette-user">amanda Ferdinand <br />- 21 July 2015 - </h2>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                 Item Two Ends 
                                 Item Three Starts  
                                <div class="item">
                                    <div class="row">
                                        <div class="featurette">
                                            <img class="featurette-image img-circle" src="img/testimonials/testimonials-03.jpg" alt="image">
                                            <div class="text">
                                                <p class="lead">“One of the great needs in ministerial training these days is for living a balance of academic excellence, practical training and godly spiritual development, not only among the students but also in those who teach them. Denver Seminary seems to be achieving and maintaining this balance. I'm impressed with the faculty and the graduates I have met”</p>
                                                <h2 class="featurette-user">amanda Ferdinand <br />- 21 July 2015 - </h2>
                                            </div>
                                        </div>
                                    </div>
                                </div>
                                 Item Three Ends 
                            </div>
                            <a class="left carousel-control" href="#myCarousel2" data-slide="prev"><img src="img/left.png" alt="//"></a>
                            <a class="right carousel-control" href="#myCarousel2" data-slide="next"><img src="img/right.png" alt="//"></a>
                        </div>
                         Cataloge Carousel Ends 

                    </div>
                </div>
            </div>
        </section>-->
        <!-- end testimonials -->




        <!-- pricing tables -->
<!--        <section class="pricing generic-section">
            <div class="container" id="menu-pricing">

                title of the section
                <div class="row-fluid title">
                    <h1>Loved By Over 25 000 Customer</h1>
                    <h3>What they say about us</h3>
                </div>
                end title of the section

                 tables 
                <div class="row-fluid">

                    <div class="span3 item">
                        <div class="header">
                            <h3>Basic</h3>
                            <h5>Great to take the first step</h5>
                        </div>
                        <h4 class="price"><span>$</span>19</h4>
                        <ul class="unstyled">
                            <li>Basic Grammar </li>
                            <li>Strategic Management </li>
                            <li>Writing and Critical Reading </li>
                            <li>Journalism </li>
                            <li>Mathematics for teaching</li>
                            <li class="last"><a href="#" class="btn btn-large"><i class="icon-right-circle"></i> Register Now</a></li>
                        </ul>
                    </div>

                    <div class="span3 item">
                        <div class="header">
                            <h3>Premium</h3>
                            <h5>Great to take the first step</h5>
                        </div>
                        <h4 class="price"><span>$</span>29</h4>
                        <ul class="unstyled">
                            <li>Mathematics for teaching </li>
                            <li>Strategic Management </li>
                            <li>Journalism </li>
                            <li>Financial Markets</li>
                            <li>Biotechnology </li>
                            <li class="last"><a href="#" class="btn btn-large"><i class="icon-right-circle"></i> Register Now</a></li>
                        </ul>
                    </div>

                    <div class="span3 item">
                        <div class="header">
                            <h3>Proffesional</h3>
                            <h5>Great to take the first step</h5>
                        </div>
                        <h4 class="price"><span>$</span>39</h4>
                        <ul class="unstyled">
                            <li>Journalism </li>
                            <li>Strategic Management</li>
                            <li>Biotechnology </li>
                            <li>Mathematics for teaching </li>
                            <li>Basic Grammar </li>
                            <li class="last"><a href="#" class="btn btn-large"><i class="icon-right-circle"></i> Register Now</a></li>
                        </ul>
                    </div>

                    <div class="span3 item">
                        <div class="header">
                            <h3>Elite</h3>
                            <h5>Great to take the first step</h5>
                        </div>
                        <h4 class="price"><span>$</span>59</h4>
                        <ul class="unstyled">
                            <li>Journalism </li>
                            <li>Writing and Critical Reading </li>
                            <li>Biotechnology </li>
                            <li>Strategic Management </li>
                            <li>Mathematics for teaching </li>
                            <li class="last"><a href="#" class="btn btn-large"><i class="icon-right-circle"></i> Register Now</a></li>
                        </ul>
                    </div>

                </div>
                 end tables 

            </div>
        </section>-->
        <!-- end pricing tables -->




        <!-- map -->
<!--        <section class="mapi generic-section">
            <div class="container" id="menu-map">
                title of the section
                <div class="row-fluid title">
                    <h1>Loved By Over 25 000 Customer</h1>
                    <h3>What they say about us</h3>
                </div>
                end title of the section

                <div class="row-fluid">
                    <div class="span12">
                        <img src="img/map-infographic.png" alt="image"> 
                    </div>
                </div>
            </div>
        </section>-->
        <!-- end map -->



        <!-- teachers -->
<!--        <section class="teachers">
            <div class="container">
                <div class="row-fluid">
                    <div class="content_carousel">
                        <ul class="thumbnails bxslider">
                            <li class="console_img first">
                                <a class="thumbnail" data-placement="bottom" href="#" data-toggle="tooltip" title="Teacher name">
                                    <img src="img/teachers/01.jpg" alt="">
                                </a>
                            </li>
                            <li class="console_img">
                                <a class="thumbnail" data-placement="bottom" href="#" data-toggle="tooltip" title="Teacher name">
                                    <img src="img/teachers/02.jpg" alt="">
                                </a>
                            </li>
                            <li class="console_img">
                                <a class="thumbnail" data-placement="bottom" href="#" data-toggle="tooltip" title="Teacher name">
                                    <img src="img/teachers/03.jpg" alt="">
                                </a>
                            </li>
                            <li class="console_img">
                                <a class="thumbnail" data-placement="bottom" href="#" data-toggle="tooltip" title="Teacher name">
                                    <img src="img/teachers/04.jpg" alt="">
                                </a>
                            </li>
                            <li class="console_img">
                                <a class="thumbnail" data-placement="bottom" href="#" data-toggle="tooltip" title="Teacher name">
                                    <img src="img/teachers/05.jpg" alt="">
                                </a>
                            </li>
                            <li class="console_img">
                                <a class="thumbnail" data-placement="bottom" href="#" data-toggle="tooltip" title="Teacher name">
                                    <img src="img/teachers/06.jpg" alt="">
                                </a>
                            </li>
                            <li class="console_img">
                                <a class="thumbnail" data-placement="bottom" href="#" data-toggle="tooltip" title="Teacher name">
                                    <img src="img/teachers/05.jpg" alt="">
                                </a>
                            </li>
                            <li class="console_img">
                                <a class="thumbnail" data-placement="bottom" href="#" data-toggle="tooltip" title="Teacher name">
                                    <img src="img/teachers/04.jpg" alt="">
                                </a>
                            </li>
                            <li class="console_img">
                                <a class="thumbnail" data-placement="bottom" href="#" data-toggle="tooltip" title="Teacher name">
                                    <img src="img/teachers/03.jpg" alt="">
                                </a>
                            </li>
                            <li class="console_img">
                                <a class="thumbnail" data-placement="bottom" href="#" data-toggle="tooltip" title="Teacher name">
                                    <img src="img/teachers/02.jpg" alt="">
                                </a>
                            </li>
                            <li class="console_img">
                                <a class="thumbnail" data-placement="bottom" href="#" data-toggle="tooltip" title="Teacher name">
                                    <img src="img/teachers/05.jpg" alt="">
                                </a>
                            </li>
                            <li class="console_img last">
                                <a class="thumbnail" data-placement="bottom" href="#" data-toggle="tooltip" title="Teacher name">
                                    <img src="img/teachers/02.jpg" alt="">
                                </a>
                            </li>

                            <li class="console_img first">
                                <a class="thumbnail" data-placement="bottom" href="#" data-toggle="tooltip" title="Teacher name">
                                    <img src="img/teachers/03.jpg" alt="">
                                </a>
                            </li>
                            <li class="console_img">
                                <a class="thumbnail" data-placement="bottom" href="#" data-toggle="tooltip" title="Teacher name">
                                    <img src="img/teachers/04.jpg" alt="">
                                </a>
                            </li>
                            <li class="console_img">
                                <a class="thumbnail" data-placement="bottom" href="#" data-toggle="tooltip" title="Teacher name">
                                    <img src="img/teachers/05.jpg" alt="">
                                </a>
                            </li>
                            <li class="console_img">
                                <a class="thumbnail" data-placement="bottom" href="#" data-toggle="tooltip" title="Teacher name">
                                    <img src="img/teachers/06.jpg" alt="">
                                </a>
                            </li>
                            <li class="console_img">
                                <a class="thumbnail" data-placement="bottom" href="#" data-toggle="tooltip" title="Teacher name">
                                    <img src="img/teachers/02.jpg" alt="">
                                </a>
                            </li>
                            <li class="console_img">
                                <a class="thumbnail" data-placement="bottom" href="#" data-toggle="tooltip" title="Teacher name">
                                    <img src="img/teachers/03.jpg" alt="">
                                </a>
                            </li>
                            <li class="console_img">
                                <a class="thumbnail" data-placement="bottom" href="#" data-toggle="tooltip" title="Teacher name">
                                    <img src="img/teachers/02.jpg" alt="">
                                </a>
                            </li>
                            <li class="console_img">
                                <a class="thumbnail" data-placement="bottom" href="#" data-toggle="tooltip" title="Teacher name">
                                    <img src="img/teachers/04.jpg" alt="">
                                </a>
                            </li>
                            <li class="console_img">
                                <a class="thumbnail" data-placement="bottom" href="#" data-toggle="tooltip" title="Teacher name">
                                    <img src="img/teachers/03.jpg" alt="">
                                </a>
                            </li>
                            <li class="console_img">
                                <a class="thumbnail" data-placement="bottom" href="#" data-toggle="tooltip" title="Teacher name">
                                    <img src="img/teachers/05.jpg" alt="">
                                </a>
                            </li>
                            <li class="console_img">
                                <a class="thumbnail" data-placement="bottom" href="#" data-toggle="tooltip" title="Teacher name">
                                    <img src="img/teachers/06.jpg" alt="">
                                </a>
                            </li>
                            <li class="console_img last">
                                <a class="thumbnail" data-placement="bottom" href="#" data-toggle="tooltip" title="Teacher name">
                                    <img src="img/teachers/02.jpg" alt="">
                                </a>
                            </li>

                        </ul>
                    </div>

                    title of the section
                    <div class="row-fluid title">
                        <h1>Loved By Over 25 000 Customer</h1>
                        <h3>What they say about us</h3>
                    </div>
                    end title of the section
                </div>
            </div>
        </section>-->
        <!-- end teachers -->

        <!-- information -->
<!--        <section class="information generic-section">
            <div class="container" id="menu-info">
                <div class="row-fluid">
                    <div class="span7">
                        <img src="img/info-image.png" alt="image">
                    </div>
                    <div class="span5">
                        <h1>Designed To Convert Better</h1>
                        <div class="accordion" id="accordion2">
                            <div class="accordion-group">
                                <div class="accordion-heading">
                                    <a class="accordion-toggle open" data-toggle="collapse" data-parent="#accordion2" href="#collapse1">
                                        Biotechnology <i class="icon-minus"></i>
                                    </a>
                                </div>
                                <div id="collapse1" class="accordion-body collapse in">
                                    <div class="accordion-inner">
                                        <p>
                                            Lorem ipsum dolor sit ametiral bro constur adipisicing elit, sedAmalesuada at aliquet a, Cras tincidunt ultricies nunc et egestas. Nam in felis eu turpis lobo rtis placerat in id neque. In vel massa nec risus commodo porttitor sed eget urna. Maecenas in nisl ante.
                                            Sed quis leo diam.
                                        </p>
                                    </div>
                                </div>
                            </div>
                            <div class="accordion-group">
                                <div class="accordion-heading">
                                    <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#collapse2">
                                        Information technology <i class="icon-plus"></i>
                                    </a>
                                </div>
                                <div id="collapse2" class="accordion-body collapse">
                                    <div class="accordion-inner">
                                        <p>
                                            Duis aute irure dolor in reprehenderit in voluptate velit esse cilum dolore eu fugiat. Lorem ipsum dolor sit amet, consectetur. adipisicing elit, sed do eiusmod
                                        </p>
                                    </div>
                                </div>
                            </div>
                            <div class="accordion-group">
                                <div class="accordion-heading">
                                    <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#collapse3">
                                        Mathematics for teaching <i class="icon-plus"></i>
                                    </a>
                                </div>
                                <div id="collapse3" class="accordion-body collapse">
                                    <div class="accordion-inner">
                                        <p>
                                            Duis aute irure dolor in reprehenderit in voluptate velit esse cilum dolore eu fugiat. Lorem ipsum dolor sit amet, consectetur. adipisicing elit, sed do eiusmod
                                        </p>
                                    </div>
                                </div>
                            </div>
                            <div class="accordion-group">
                                <div class="accordion-heading">
                                    <a class="accordion-toggle" data-toggle="collapse" data-parent="#accordion2" href="#collapse4">
                                        Journalism <i class="icon-plus"></i>
                                    </a>
                                </div>
                                <div id="collapse4" class="accordion-body collapse">
                                    <div class="accordion-inner">
                                        <p>
                                            Duis aute irure dolor in reprehenderit in voluptate velit esse cilum dolore eu fugiat. Lorem ipsum dolor sit amet, consectetur. adipisicing elit, sed do eiusmod
                                        </p>
                                    </div>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </section>-->
        <!-- end information -->



        <!-- courses -->
        <section class="courses generic-section">
            <div class="container" id="menu-courses">

                <!--title of the section-->
                <div class="row-fluid title">
                    <h1>Programs</h1>
                    <h3>Faculties and Colleges</h3>
                </div>
                <!--end title of the section-->

                <div class="row-fluid">
                    <ul class="grid cs-style-5">
                        <li class="span4">
                            <figure>
                                <img src="<%=request.getContextPath()%>/img/courses/biological-sciences.png" alt="image">
                                <figcaption>
                                    <div class="info_context">
                                        <div class="span6 title_context">
                                            <h3>Biological Sciences</h3>
                                            <span>Undergraduate Degree</span>
                                        </div>
                                        <a class="btn" href="#">Read more</a>
                                    </div>
                                </figcaption>
                            </figure>
                        </li>
                        <li class="span4">
                            <figure>
                                <img src="<%=request.getContextPath()%>/img/courses/engineering.jpg" alt="image">
                                <figcaption>
                                    <div class="info_context">
                                        <div class="span6 title_context">
                                            <h3>Engineering</h3>
                                            <span>Undergraduate & Master Degree</span>
                                        </div>
                                        <a class="btn" href="#">Read more</a>
                                    </div>
                                </figcaption>
                            </figure>
                        </li>
                        <li class="span4">
                            <figure>
                                <img src="<%=request.getContextPath()%>/img/courses/law.jpg" alt="image">
                                <figcaption>
                                    <div class="info_context">
                                        <div class="span6 title_context">
                                            <h3>Law</h3>
                                            <span>Undergraduate Degree</span>
                                        </div>
                                        <a class="btn" href="#">Read more</a>
                                    </div>
                                </figcaption>
                            </figure>
                        </li>
                    </ul>
                </div>

                <div class="row-fluid">
                    <ul class="grid cs-style-5">
                        <li class="span4">
                            <figure>
                                <img src="<%=request.getContextPath()%>/img/courses/pharmaceutical-sciences.jpg" alt="img01">
                                <figcaption>
                                    <div class="info_context">
                                        <div class="span6 title_context">
                                            <h3>Pharmaceutical Sciences</h3>
                                            <span>Undergraduate Degree</span>
                                        </div>
                                        <a class="btn" href="#">Read more</a>
                                    </div>
                                </figcaption>
                            </figure>
                        </li>
                        <li class="span4">
                            <figure>
                                <img src="<%=request.getContextPath()%>/img/courses/physical-sciences.jpg" alt="img01">
                                <figcaption>
                                    <div class="info_context">
                                        <div class="span6 title_context">
                                            <h3>Physical Sciences</h3>
                                            <span>Undergraduate & Master Degree</span>
                                        </div>
                                        <a class="btn" href="#">Read more</a>
                                    </div>
                                </figcaption>
                            </figure>
                        </li>
                        <li class="span4">
                            <figure>
                                <img src="<%=request.getContextPath()%>/img/courses/surgeons.jpg" alt="img01">
                                <figcaption>
                                    <div class="info_context">
                                        <div class="span6 title_context">
                                            <h3>Medical Sciences</h3>
                                            <span>Undergraduate Degree</span>
                                        </div>
                                        <a class="btn" href="#">Read more</a>
                                    </div>
                                </figcaption>
                            </figure>
                        </li>
                    </ul>
                </div>

            </div>
        </section>
        <!-- end courses -->




        <!-- contact -->
        <section class="contact generic-section">
            <div class="content_map">
                <div id="map"></div>
            </div>

            <div class="container" id="menu-contact">
                <div class="row-fluid">
                    <div class="span4 course-form-box offset8">
                        <h3>Subscribe to Get information</h3>
                        <p>The Best way to help you reach your goalsDonec id elit non mi porta gravida at eget metus. </p><br>
                        <p>
                            Fusce dapibus, tellus ac cursus commodo, tortor mauris condimentum nibh, ut fermentum massa justo sit amet risus. Etiam porta sem malesuad.
                        </p>
                        <form id="contact-form" class="nm" action="http://university.coralixthemes.com/index_submit" method="post">

                            <div class="row-fluid content_form">

                                <input class="input-large" type="text" required placeholder="Name" name="firstname" />


                                <input class="input-large" type="text" required placeholder="*Email" name="firstname" />


                                <textarea class="text-large"  required placeholder="Telephone" name="tex_msg"></textarea>


                                <button class="btn btn-large btn-primary" type="submit"><i class="icon-edit"></i>send e-mail</button>

                            </div>
                        </form>
                    </div>
                </div>

            </div>
        </section>
        <!-- end contact -->




        <!-- footer -->
        <footer>
            <div class="container">
                <div class="row-fluid">

                    <div class="span3">
                        <h4 class="footer-title"><i class="icon-calendar"></i>  Calendar</h4>
                        <ul class="unstyled">
                            <li><a href="#">&raquo; Computer Science Symposium<span>24/05/2014</span></a></li>
                            <li><a href="#">&raquo; Mathematics Day<span>17/09/2014</span></a></li>
                            <li><a href="#">&raquo; Agriculture and Forestry Day  <span>11/10/2014</span></a></li>
                            <li><a href="#">&raquo; Biotechnology Day    <span>21/10/2014</span></a></li>
                            <li><a href="#">&raquo; Google IO <span>15/12/2014</span></a></li>
                        </ul>
                    </div>

                    <div class="span3">
                        <h4 class="footer-title"><i class="icon-mail"></i> CONTACT US</h4>
                        <p>We will be pleased to host you, visit us today!</p>
                        <ul class="unstyled contact-info">
                            <li><i class="icon-home-outline"></i> Address: 3rd Floor ICT Complex UNN</li>
                            <li><i class="icon-desktop-1"></i> <a href="#">ict.unn.edu.ng</a> </li>
                            <li><i class="icon-phone-1"></i> phone: 042-555-36</li>
                            <li><i class="icon-mobile"></i> Mobile: 806-867-4787</li>
                        </ul>
                    </div>

                    <div class="span3">
                        <h4 class="footer-title"><i class="icon-comment-1"></i>  important questions</h4>
                        <ul class="unstyled">
                            <li><a href="#">&raquo; When to choose a major? </a></li>
                            <li><a href="#">&raquo; How to choose a major? </a></li>
                            <li><a href="#">&raquo; What are your interests? </a></li>
                            <li><a href="#">&raquo; What are your academic strengths? </a></li>
                            <li><a href="#">&raquo; What are your career goals? </a></li>
                        </ul>
                    </div>

                    <div class="span3">
                        <h4 class="footer-title"><i class="icon-article-alt"></i> NEWSLETTER</h4>
                        <p>Make sure you dont miss interesting happenings by joining our newsletter program.</p>
                        <div class="suscribe course-form-box">
                            <form id="search-form" class="nm" action="" method="get">
                                <div class="row-fluid content_form">


                                    <input class="input-large" type="text" required placeholder="*Email" name="firstname" />


                                    <button class="btn btn-large btn-primary" type="submit"><i class="icon-edit"></i>suscribe</button>

                                </div>
                            </form>
                        </div>
                    </div>
                </div>
            </div>
            <div class="copyright">
                <ul class="unstyled">
                    <li class="tooltip_hover" title="facebook" data-placement="top"  data-toggle="tooltip"><a  href="#" ><i class="icon-facebook"></i></a></li>
                    <li class="tooltip_hover"  title="twitter" data-placement="top"  data-toggle="tooltip"><a href="#"><i class="icon-twitter"></i></a></li>
                    <li class="tooltip_hover"  title="vimeo" data-placement="top"  data-toggle="tooltip"><a href="#"><i class="icon-vimeo"></i></a></li>
                    <li class="tooltip_hover" title="skype" data-placement="top"  data-toggle="tooltip"><a href="#"><i class="icon-skype-circled"></i></a></li>
                </ul>
                <h6>© 2014. University of Nigeria Nsukka. All rights reserved.</h6>
            </div>
        </footer>
        <!-- end footer -->

        <!--<div class="modal fade" id="login-modal" tabindex="-1" role="dialog" aria-labelledby="myModalLabel" aria-hidden="true">
                <div class="modal-dialog">
                <div class="modal-content">
                        <div class="modal-header login_modal_header">
                                <button type="button" class="close" data-dismiss="modal" aria-hidden="true">&times;</button>
                                <h2 class="modal-title" id="myModalLabel">Login to Your Account</h2>
                        </div>
                        <div class="modal-body login-modal">
                                <p>Stack Overflow is a question and answer site for professional and enthusiast programmers. It's 100% free, no registration required</p>
                                <br/>
                                <div class="clearfix"></div>
                                <div id='social-icons-conatainer'>
                                        <div class='modal-body-left'>
                                                <div class="form-group">
                                                <input type="text" id="username" placeholder="Enter your name" value="" class="form-control login-field">
                                                <i class="fa fa-user login-field-icon"></i>
                                        </div>
                        
                                        <div class="form-group">
                                                <input type="password" id="login-pass" placeholder="Password" value="" class="form-control login-field">
                                                <i class="fa fa-lock login-field-icon"></i>
                                        </div>
                        
                                        <a href="#" class="btn btn-success modal-login-btn">Login</a>
                                        <a href="#" class="login-link text-center">Lost your password?</a>
                                        </div>
                                
                                        <div class='modal-body-right'>
                                                <div class="modal-social-icons">
                                                        <a href='#' class="btn btn-default facebook"> <i class="fa fa-facebook modal-icons"></i> Sign In with Facebook </a>
                                                        <a href='#' class="btn btn-default twitter"> <i class="fa fa-twitter modal-icons"></i> Sign In with Twitter </a>
                                                        <a href='#' class="btn btn-default google"> <i class="fa fa-google-plus modal-icons"></i> Sign In with Google </a>
                                                        <a href='#' class="btn btn-default linkedin"> <i class="fa fa-linkedin modal-icons"></i> Sign In with Linkedin </a>
                                                </div> 
                                        </div>	
                                        <div id='center-line'> OR </div>
                                </div>																												
                                <div class="clearfix"></div>
                                
                                <div class="form-group modal-register-btn">
                                        <button class="btn btn-default"> New User Please Register</button>
                                </div>
                        </div>
                        <div class="clearfix"></div>
                        <div class="modal-footer login_modal_footer">
                        </div>
                </div>
                </div>
        </div>-->

        <a href="#" class="scrollup"><i class="icon-up-open"></i></a>      

        <!-- ======================= JQuery libs =========================== -->

        <script src="js/vendor/jquery-1.10.2.min.js"></script>
        <!--<script src="js/jquery-1.7.1.js"></script>-->
        <!--<script src="http://ajax.googleapis.com/ajax/libs/jqueryui/1.7.1/jquery-ui.min.js"></script>-->

        <!-- Bootstrap-->
        <script src="js/vendor/bootstrap.min.js"></script>
        <!--<script src="js/bootstrap/bootstrap.min.js"></script>-->

        <!-- Modernizr -->
        <script src="js/vendor/modernizr-2.6.2.min.js"></script>

        <!-- Nivo slider -->
        <script type="text/javascript" src="js/nivo-slider/jquery.nivo.slider.js"></script>



        <!--Scrollbar-->         
        <script src="js/nicescroll/jquery.nicescroll.min.js" type="text/javascript"></script>

        <!--Scroll To-->         
        <script src="js/nav/jquery.scrollTo.js"></script> 
        <script src="js/nav/jquery.nav.js"></script> 

        <!--Retina Support-->
        <script src="js/retina/retina.js" type="text/javascript"></script>

        <!--scroll-->
        <script src="js/select/chosen.jquery.js" type="text/javascript"></script>

        <!--bxslider-->
        <script src="js/bxslider/jquery.bxslider.js" type="text/javascript"></script>

        <!--effect hover-->
        <script src="js/effect_hover/modernizr.custom.js"></script>
        <script src="js/effect_hover/toucheffects.js"></script>

        <!-- maps google-->
        <script type="text/javascript" src="http://maps.google.com/maps/api/js?sensor=true"></script>
        <script type="text/javascript" src="js/gmaps/gmaps.js"></script>
        <!-- end maps google-->

        <!--Custom Template Javascript-->
        <script type="text/javascript" src="js/script.js"></script>


        <!-- ======================= End JQuery libs =========================== -->
    </body>
</html>

