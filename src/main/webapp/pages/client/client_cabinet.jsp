<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<fmt:setLocale value="${locale}" />
<fmt:setBundle basename="textBundle"/>

<html lang="${locale}">
<head>
    <meta charset="UTF-8">
    <title>Cinema</title>
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <link type="text/css" href=" https://www.w3schools.com/w3css/4/w3.css" rel="stylesheet">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">
</head>

<script>
    function addUrlParameter(name, value) {
      var searchParams = new URLSearchParams(window.location.search)
      searchParams.set(name, value)
      window.location.search = searchParams.toString()
    }
</script>

<body class="w3-light-grey">
<div class="w3-container w3-teal w3-opacity w3-left-align front-size:20px">
            <div class="w3-dropdown-hover w3-right ">
                <button class="w3-button w3-teal"><fmt:message key="language"/></button>
                <div class="w3-dropdown-content w3-bar-block w3-card-4" style="min-width:70px">
                  <a class="w3-bar-item w3-button w3-teal" onclick="addUrlParameter('locale', 'en')"><fmt:message key="language.en" /></a>
                  <a class="w3-bar-item w3-button  w3-teal" onclick="addUrlParameter('locale', 'ru')"><fmt:message key="language.ru" /></a>
                </div>
            </div>
            <div class="w3-container w3-center w3-padding ">
                <h1><fmt:message key="cinema.project"/></h1>
            </div>
</div>


<div class="w3-container w3-right-align">
    <div class="w3-bar w3-padding-large w3-padding-24">

       <button class="w3-btn w3-teal w3-border w3-round-large w3-left" onclick="location.href='/cinema'">
                <fmt:message key="on.main.page"/>
                </button>
             <button onclick="document.getElementById('id01').style.display='block'" class="w3-button w3-teal w3-border w3-border-teal w3-round-large  w3-right-align">
                   <fmt:message key="update.button"/></button>
                   <div id="id01" class="w3-modal">
                       <div class="w3-modal-content w3-card-4 w3-animate-zoom" style="max-width:600px">
                           <div class="w3-center"><br>
                              <span onclick="document.getElementById('id01').style.display='none'" class="w3-button w3-xlarge w3-hover-red w3-display-topright" title="Close Modal">&times;</span>
                           </div>
                           <form class="w3-container" method="POST" action="/cinema/update">
                              <div class="w3-section w3-left-align">
                                  <label><b><fmt:message key="new.login"/></b></label>
                                  <input class="w3-input w3-border w3-margin-bottom" type="text" placeholder="<fmt:message key="enter.login"/>" name="login" required>

                                  <label><b><fmt:message key="new.password"/></b></label>
                                  <input class="w3-input w3-border" type="password" placeholder="<fmt:message key="enter.password"/>" name="password" required>

                                  <button class="w3-button w3-block w3-teal w3-round-large w3-section w3-padding" type="submit">
                                  <fmt:message key="update.button"/></button>

                              </div>
                           </form>
                       </div>
             </div>

              <button class="w3-btn w3-teal w3-border w3-border-red w3-round-large w3-right-align" onclick="location.href='/cinema/logout'">
                    <fmt:message key="logout.button"/>
              </button>

              <button class="w3-btn  w3-teal w3-border w3-border-red w3-round-large w3-right-align" onclick="location.href='/cinema/deleteaccount'">
                                                  <fmt:message key="delete.account"/>
                                                </button>
      </div>
</div>

<div class="w3-container w3-center">
    <footer class="w3-container w3-teal w3-round-large" style="width:100% ">
        <h4><b><fmt:message key="clientCabinet"/></b></h4>
    </footer>
</div>
<div><p></p></div>
<form class="w3-container w3-card-4 w3-light-grey w3-text-teal" style="width:50%;margin:auto">
     <div class="w3-row w3-section">
        <h4><b><fmt:message key="user.name"/></b></h4>
        <p><div class="w3-col" style="width:50px"><i class="w3-xxlarge fa fa-user">&nbsp</i>
        </div><h3><b><c:out value="${user.userName}"/>&nbsp<c:out value="${user.userSurname}"/></b></h3>
        </p>
     </div>

    <div class="w3-row w3-section">
        <h4><b><fmt:message key="user.email"/></b></h4>
        <p><div class="w3-col" style="width:50px"><i class="w3-xxlarge fa fa-envelope-o">&nbsp</i>
        </div><h3><b><c:out value="${user.userEMailAddress}"/></b></h3>
        </p>
    </div>

    <div class="w3-row w3-section">
        <h4><b><fmt:message key="user.gender"/></b></h4>
        <p><div class="w3-col" style="width:50px"><i class="w3-xxlarge fa fa-male">&nbsp</i>
        </div><h3><b><c:out value="${user.gender}"/></b></h3>
    </div>
 </form>

<div class="w3-container w3-right-align">
    <p> <div class="w3-bar w3-padding w3-padding-12">
            <button class="w3-btn w3-white w3-border w3-border-teal w3-round-large w3-left-align" onclick="location.href='/cinema/tickets'">
                <fmt:message key="show.ticket"/>
            </button>

            <button class="w3-btn w3-white w3-border w3-border-teal w3-round-large w3-left-align" onclick="location.href='/cinema/schedule'">
                <fmt:message key="buy.ticket"/>
            </button>
        </div>
    </p>
</div>



</body>
</html>