$(document).ready(function(){
    $('.button-left').click(function(){
        $('.sidebar').toggleClass('fliph');
    });
      
 });

 function openNav() {
    document.getElementById("main-menu").style.width = "250px";  
    document.body.style.opacity = "0.6";
}