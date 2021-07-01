<%--
  Created by IntelliJ IDEA.
  User: UOG
  Date: 2021/6/5
  Time: 22:41
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>$Title$</title>
    <meta charset=UTF-8">
  </head>
  <body>
  <div id="msg_from_server"></div>
  发送数据:<button id="sends"  >发送字符串</button>
  <script type="text/javascript">
    if (!!window.EventSource) {
      var source = new EventSource('http://localhost:8088/EnergyProject/newsseemitter/120', { withCredentials: true }); //为http://localhost:8080/testSpringMVC/push
      s = '';
      source.addEventListener('message', function (e) {

        s += e.data + "<br/>"
       console.log("正常返回"+s)
        console.log("连接状态"+source.readyState)

      });

      source.addEventListener('open', function (e) {
        console.log(e)
        console.log("连接打开.");
        console.log("连接状态"+source.readyState)
      }, false);

      source.addEventListener('error', function (e) {
        if (e.readyState == EventSource.CLOSED) {
          console.log("连接关闭");
          console.log("连接状态"+source.readyState)
        } else {
          console.log("错误返回"+e.readyState);
        }
      }, false);

    } else {
      console.log("没有sse");
    }

    window.onbeforeunload = function() {
      closeSse();
    };

    // 关闭Sse连接
    function closeSse() {
      source.close();
      const httpRequest = new XMLHttpRequest();
      httpRequest.open('GET', 'http://localhost:8088/EnergyProject/close/120', true);
      httpRequest.send();
      console.log("close");
      console.log("连接状态"+source.readyState)
    }

    let buttons=document.getElementById("sends");
    buttons.onclick=function (){
      const httpRequest = new XMLHttpRequest();
      httpRequest.open('GET', 'http://localhost:8088/EnergyProject/sqlserver', true);
      httpRequest.send();
      console.log("发送数据")
    }
  </script>


  </body>
</html>
