var websocket=null;
if('WebSocket'in window){
    websocket=new WebSocket("ws://202.116.163.203:8080/origins/websocket");}

else{
    alert("不支持websocket");
}
websocket.onopen=function(){
    alert("开启")
};
websocket.onclose=function(){
};
websocket.onerror=function(){
};
window.onbeforeLoad=function(){
    websocket.close();
};
websocket.onmessage=function(event){
};
function send(messsage){
    websocket.send(messsage);
}