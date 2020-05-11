let SIDE = 5;
let gameField = [[]];

console.log("it's working")

for (let i = 0; i < SIDE; i++) {
    for (let j = 0; j < SIDE; j++) {
        gameField[i][j] = `${i}${j}`;
    }
}
for (var i = 0; i <= SIDE; i++) {
    table.innerHTML += "<tr>";
    for (var j = 0; j <= SIDE; j++) {
        table.innerHTML += "<td>" + gameField[i][j] + "</td>";
    }
    table.innerHTML += "</tr>";
}
