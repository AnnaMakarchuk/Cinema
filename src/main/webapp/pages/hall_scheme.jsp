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
            <button class="w3-btn w3-white w3-border w3-border-teal w3-round-large w3-left" onclick="location.href='/cinema/schedule'">
                <fmt:message key="schedule"/>
            </button>

            <button onclick="document.getElementById('id02').style.display='block'" class="w3-button w3-teal w3-round-large w3-right-align">
                 <fmt:message key="login.button"/></button>
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

            <button onclick="document.getElementById('id01').style.display='block'" class="w3-button w3-teal w3-round-large w3-right-align">
                 <fmt:message key="register.button"/>
            </button>
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

<div class="w3-container w3-center w3-text-teal">
    <p class="w3-left"><h1><b><c:out value="${schedule.movieName}"/></b></h1></p>
    <i class="fa fa-calendar" style="font-size:24px"><c:out value="${schedule.weekDay}"/> </i>
    <i class="fa fa-clock-o" style="font-size:24px"><c:out value="${schedule.time}"/> </i>
</div>

<div><br></div>

<div class="w3-bar w3-center">
        <c:forEach var = "r" begin = "1" end = "${hall.maxRow}">
                ${r}
                <c:forEach var = "c" begin = "1" end = "${hall.maxPlacesInRow}">
                        <button id="${r}-${c}" class="w3-btn w3-center w3-border w3-border-teal w3-round-large chair" onclick="submitButtonStyle(this, ${r}, ${c})" >
                                <div class="button_text">
                                        ${c}
                                </div>
                                <div class="button_price"> <c:out value="${hall.priceType[r]}" /> </div>
                        </button>
                </c:forEach>
            </p>
        </c:forEach>
</div>

<div class="w3-container w3-right">
        <div class="w3-bar w3-padding-medium w3-padding-24">
            <button class="w3-btn w3-white w3-border w3-border-teal w3-round-large" type="submit" onclick="submitTickets('boughttickets')">
            <fmt:message key="buy.selected.tickets"/>
            </button>
    </div>

<style>
    .chair {
        height: 50px;
        width: 50px;
    }

    .chair:hover .button_text {
        color: coral;
        opacity: 1;
    }

    .button_price {
        opacity: 0;
        text-align: center;
    }
    .chair:hover .button_price {
        opacity: 1;
    }
</style>

<script>

    var occupied = JSON.parse('${occupiedPlaces}');

    document.addEventListener('DOMContentLoaded', changePlaces());

    function changePlaces() {
        Object.keys(occupied).forEach(function(key) {
            var tmp = occupied[key];
            var btn = document.getElementById(tmp.row+"-"+tmp.place);
            if (btn != NaN) {
                console.log(tmp.row+"-"+tmp.place);
                console.log(btn);

                btn.disabled = true;
            }
        });

    }

    places = [];

    function submitButtonStyle(_this, row, place) {
        var color_one = "#009688";
        var bgcolor = _this.style.backgroundColor;
        _this.style.backgroundColor = color_one;
        if(bgcolor == _this.style.backgroundColor) {
            _this.style.backgroundColor = "#f1f1f1";
            places.splice({"row": row, "place": place}, 1);
        } else {
            places.push({"row": row, "place": place});
        }
    }

    function submitTickets(url) {
        console.log(places);
        if (places.length > 0) {
            console.log("sending post");
            var xhr = new XMLHttpRequest();
            xhr.open("POST", url, true);
            xhr.setRequestHeader('Content-Type', 'application/json');
            var body = new Object();
            body["scheduleId"]="${schedule.scheduleId}";
            body["places"]=Object.values(places);

            xhr.send(JSON.stringify(body));
        }
    }

    function addUrlParameter(name, value) {
      var searchParams = new URLSearchParams(window.location.search)
      searchParams.set(name, value)
      window.location.search = searchParams.toString()
    }
</script>

</body>
</html>