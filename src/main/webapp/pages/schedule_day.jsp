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
    <p> <div class="w3-bar w3-padding-large w3-padding-24">
           <c:choose>
                <c:when test="${user == null}">
                        <button onclick="document.getElementById('id02').style.display='block'" class="w3-button w3-teal w3-round-large w3-right-align">
                            <fmt:message key="login.button"/>
                        </button>
                        <button onclick="document.getElementById('id01').style.display='block'" class="w3-button w3-teal w3-round-large w3-right-align">
                                <fmt:message key="register.button"/>
                           </button>
                </c:when>
                <c:otherwise>
                        <button class="w3-btn w3-border w3-teal w3-round-large" onclick="submitTickets('boughttickets')">
                            <fmt:message key="buy.selected.tickets"/>
                        </button>

                        <button class="w3-btn w3-border w3-teal w3-round-large w3-right-align" onclick="location.href='/cinema/cabinet'">
                            <fmt:message key="back.cabinet"/>
                        </button>
                        <button class="w3-btn w3-border w3-teal w3-round-large w3-right-align w3-border-red" onclick="location.href='/cinema/logout'">
                            <fmt:message key="logout.button"/>
                        </button>
                </c:otherwise>
            </c:choose>




                 <div id="id02" class="w3-modal">
                    <div class="w3-modal-content w3-card-4 w3-animate-zoom" style="max-width:600px">
                        <div class="w3-center"><br>
                            <span onclick="document.getElementById('id02').style.display='none'" class="w3-button w3-xlarge w3-hover-red w3-display-topright" title="Close Modal">&times;</span>
                        </div>
                        <form class="w3-container" method="POST" action="/cinema/login">
                            <div class="w3-section w3-left-align">
                                <label><b><fmt:message key="login"/></b></label>
                                <input class="w3-input w3-border w3-margin-bottom" type="text" placeholder="<fmt:message key="enter.login"/>" name="login" required>

                                <label><b><fmt:message key="password"/></b></label>
                                <input class="w3-input w3-border" type="password" placeholder="<fmt:message key="enter.password"/>" name="password" required>

                                <button class="w3-button w3-block w3-teal w3-round-large w3-section w3-padding" type="submit">
                                    <fmt:message key="login.button"/>
                                </button>
                            </div>
                        </form>
                    </div>
                 </div>
                 <div id="id01" class="w3-modal">
                    <div class="w3-modal-content w3-card-4 w3-animate-zoom" style="max-width:600px">
                        <div class="w3-center"><br>
                            <span onclick="document.getElementById('id01').style.display='none'" class="w3-button w3-xlarge w3-hover-red w3-display-topright" title="Close Modal">&times;</span>
                        </div>

                        <form class="w3-container" method="POST" action="/cinema/register">
                            <div class="w3-section w3-left-align">
                                <label><b><fmt:message key="name"/></b></label>
                                <input class="w3-input w3-border w3-margin-bottom" type="text" placeholder="<fmt:message key="enter.name"/>" name="name" required>

                                <label><b><fmt:message key="surname"/></b></label>
                                <input class="w3-input w3-border w3-margin-bottom" type="text" placeholder="<fmt:message key="enter.surname"/>" name="surname" required>

                                <label><b><fmt:message key="login"/></b></label>
                                <input class="w3-input w3-border w3-margin-bottom" type="text" placeholder="<fmt:message key="enter.login"/>" name="login" required>

                                <label><b><fmt:message key="email"/></b></label>
                                <input class="w3-input w3-border w3-margin-bottom" type="text" placeholder="<fmt:message key="enter.email"/>" name="email" required>

                                <label><b><fmt:message key="password"/></b></label>
                                <input class="w3-input w3-border w3-margin-bottom" type="password" placeholder="<fmt:message key="enter.password"/>" name="password" required>

                                <form action="">
                                    <input type="radio" name="gender" value="male"><fmt:message key="male"/><br>
                                    <input type="radio" name="gender" value="female"><fmt:message key="female"/><br>
                                </form>

                                <button class="w3-button w3-block w3-teal w3-round-large w3-section w3-padding" type="submit">
                                    <fmt:message key="register.button"/></button>
                            </div>
                        </form>
                    </div>
                 </div>
    </p>
</div>

<div class="w3-container w3-center">
    <footer class="w3-container w3-teal w3-round-large" style="width:100% ">
        <h4><b><fmt:message key="hall.name"/></b></h4>
    </footer>
</div>
<br>

<div class="w3-container">
  <div id="day_panel" class="w3-bar w3-teal w3-text-white">
    <a href="?day=monday" class="w3-bar-item w3-button" onclick="submitButtonStyle(this, 'monday')"><fmt:message key="monday"/></a>
    <a href="?day=tuesday" class="w3-bar-item w3-button " onclick="submitButtonStyle(this)"><fmt:message key="tuesday"/></a>
    <a href="?day=wednesday" class="w3-bar-item w3-button " onclick="submitButtonStyle(this)"><fmt:message key="wednesday"/></a>
    <a href="?day=thursday" class="w3-bar-item w3-button" onclick="submitButtonStyle(this)"><fmt:message key="thursday"/></a>
    <a href="?day=friday" class="w3-bar-item w3-button " onclick="submitButtonStyle(this)"><fmt:message key="friday"/></a>
    <a href="?day=saturday" class="w3-bar-item w3-button " onclick="submitButtonStyle(this)"><fmt:message key="saturday"/></a>
    <a href="?day=sunday" class="w3-bar-item w3-button " onclick="submitButtonStyle(this)"><fmt:message key="sunday"/></a>
  </div>

<div class="w3-container w3-text-teal">
    <p class="w3-left"><fmt:message key="session"/><c:out value="${weekday}" /><fmt:message key="click.message"/></p>
    <table class="w3-table w3-bordered">
        <tr>
            <th><fmt:message key="movie.name"/></th>
            <th><fmt:message key="movie.time"/></th>
        </tr>
        <tr>
            <c:forEach var="schedule" items="${schedules}">
                <td><br><c:out value="${schedule.movieName}"/></td>
                <c:forEach var="time" items="${schedule.timeList}">
                    <td class="w3-left"><button class="w3-button w3-border w3-round-large w3-section w3-padding" button onclick="location.href='/cinema/hallscheme?schedule_id=${time.scheduleId}'">
                        <c:out value="${time.time}"/></button></td>
                   </td>
            </c:forEach>
            </tr>
        </c:forEach>
    </table>
</div>


<script>
    function submitButtonStyle(_this, day) {
        if ("${weekday}" == day) {
            _this.style.background = "#800000";
        }
}
</script>

</body>
</html>