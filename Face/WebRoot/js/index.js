window.onload = function (){
          try{
              document.createElement("canvas").getContext("2d");
              document.getElementById("support").innerHTML = "";
          }catch(e){
              document.getElementById("support").innerHTML = "";
          }
      };
 
      window.addEventListener("DOMContentLoaded", function () {
          var video = document.getElementById("video");
          var videoObj = { "video": true };
          var errBack = function (error){
                  console.log("Video capture error: " + error.message, error.code);
              };
          if (navigator.getUserMedia) {
              navigator.getUserMedia(videoObj, function (stream) {
                  video.src = stream;
                  video.play();
              }, errBack);
          } else if (navigator.webkitGetUserMedia) {
              navigator.webkitGetUserMedia(videoObj, function (stream) {
                  video.src = window.URL.createObjectURL(stream);
                  video.play();
              }, errBack);
          } else if (navigator.mozGetUserMedia){
              navigator.mozGetUserMedia(videoObj, function (stream) {
                      video.src = window.URL.createObjectURL(stream);
                      video.play();
              }, errBack);
          }
          
          

          document.getElementById("snap").addEventListener("click",function(){
                  CatchCode();
          });
      }, false);

      