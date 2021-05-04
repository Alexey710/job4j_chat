<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html>
    <head>
        <title>Chat room</title>
        <style>
            .scroll {
                height: 400px;
                width: 700px;
                background: #fff;
                border: 3px solid #C1C1C1; 
                overflow-y: scroll; 
                border-radius: 10px
            }
            
        </style>
   </head>
    
<body onload="scrollDown();">
    <script type="text/javascript">
        function scrollDown() {
            console.log("start scrollDown")
            var block = document.getElementById("block");
            block.scrollTop = block.scrollHeight;
        }
    </script>
<span style="color: blueviolet; font-weight:600;font-size: 250%"><c:out value="Чат: ${chatName}"/></span>
<br>
<div id="block" class="scroll">
<c:forEach items="${chat}" var="chat_m">
                <p>
                    <span style="color: ${chat_m.user.colorCSS}; font-weight:600;font-size: 150%"><c:out value="${chat_m.user.username}"/></span>
                    <c:out value="${chat_m.content}"/>     
                </p>
</c:forEach>
</div>    

<form action="<c:url value='/chat?id=${id}'/>" method='POST'>
    <table>
        <br>
        <textarea style="border-radius: 30px;border: 3px solid #C1C1C1;
                  width: 400px;height: 100px;resize: none" name="message">
        </textarea>
        <tr>
            <td><input style="border-radius: 10px;
                  width: 160px;height: 40px;background: blueviolet;color: white" 
                  name="submit" type="submit" value="Отправить сообщение" /></td>
        </tr>
    </table>
</form>
</body>
</html>