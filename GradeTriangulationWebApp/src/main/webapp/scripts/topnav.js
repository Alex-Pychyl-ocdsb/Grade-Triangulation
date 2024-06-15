/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

document.write('\
<link rel="stylesheet" href="/styles/topnav.css" type="text/css">\
\
<div class="topnav">\
    <button onclick="leaveTo(\'/login\')">Log out</button>\
    <button onclick="leaveTo(\'/classes\')">Home</button>\
    <button onclick="toggleTheme()" class="img-button"><img id="theme-img" src="/images/darktheme.svg" alt="toggle theme"></button>\
</div>\
');

document.getElementById("html").className = sessionStorage.getItem("theme");
if (document.getElementById("html").className === "darktheme")
    document.getElementById("theme-img").src = "/images/lighttheme.svg";

function leaveTo(location) {
    if (typeof isSaved === "undefined" || isSaved === true
     || window.confirm('Are you sure you want to exit without saving?'))
        window.location = location; 
}

function toggleTheme() {
    if (document.getElementById("html").className === "darktheme") {
        document.getElementById("html").className = "lighttheme";
        document.getElementById("theme-img").src = "/images/darktheme.svg";
        sessionStorage.setItem("theme", "lighttheme");
    } else {
        document.getElementById("html").className = "darktheme";
        document.getElementById("theme-img").src = "/images/lighttheme.svg";
        sessionStorage.setItem("theme", "darktheme");
    };
}