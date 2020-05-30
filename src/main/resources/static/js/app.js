var stompClient = null;


function connectinnnng() {
    const socket = new SockJS('/createArcher');
    stompClient = Stomp.over(socket);
    stompClient.connect({}, function(frame) {
        console.log('Connected: ' + frame);
        stompClient.subscribe('/topic/tiles', function(greeting){
            showGreeting(JSON.parse(greeting.body).content);
            // createTable()
        });
    });
}
function showGreeting(message) {
    var response = document.getElementById('response');
    var p = document.createElement('p');
    // p.style.wordWrap = 'break-word';
    p.appendChild(document.createTextNode(message));
    response.appendChild(p);
}

function sendName() {
    var name = document.getElementById('name').value;
    stompClient.send("/app/hello", {}, JSON.stringify({ 'name': name }));
}



function sendArcher() {
    const idTile = document.getElementById('idTile').value;
    // const form = new FormData(document.getElementById("createArcherForm"));
    stompClient.send("/app/createArcher", {}, JSON.stringify( { 'tileId' : idTile }));
}



