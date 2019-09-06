$("path").click(function(){
        alert($(this).attr("id"));
      $(this).css({ fill: "#000000" });
      $('#historico').append("<li>"+$(this).attr("id")+"</li>");
});