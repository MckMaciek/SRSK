var client = null;

window.onload = connect;

function viewMessage(value, user, data){

    var getChatBox = document.getElementById('chat-box-messages-display');
    getChatBox.value += data + "  " + user + ": " + value + "\n";

}


function connect(){

    $("#side-menu-container").hide();

    var url = "ws://localhost:8080/chat";

    client = Stomp.client(url);
    client.connect({}, function(frame){
        client.subscribe('/topic/messages', function(message){
            viewMessage(JSON.parse(message.body).value, JSON.parse(message.body).user, JSON.parse(message.body).data);
        });
    })
}

var messageToSend = document.getElementById('messageToSend').value;
messageToSend.addEventListener("keyup", sendMessage());


function sendMessage(){

    var messageToSend = document.getElementById('messageToSend').value;
    client.send("/app/chat", {}, JSON.stringify({'value' : messageToSend, 'user': 'none', 'data' : 'none'}));

    document.getElementById('messageToSend').value = "";

}