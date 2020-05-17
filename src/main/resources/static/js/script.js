console.log("it's working")
let SIDE = 16;
let iter = 0;
let commits
let urlAllTile = 'http://localhost:8080/tile'




window.onload = async function main() {
    await getTiles(urlAllTile)
    creatTable()
}

async function getTiles(url) {
    let response = await fetch(url);
    commits = await response.json();

}
function show() {
    console.log(commits)
}
function creatTable() {
    let element = document.querySelector('#table');
    let table = document.createElement("table")
    for (let i = 0; i < SIDE; i++) {
        let tr = document.createElement("tr");

        for (let j = 0; j < SIDE; j++) {

            let td = document.createElement("td");

                td.setAttribute("id", commits[iter].name)
                td.innerHTML = ". "
                if(commits[iter].unit !== null){
                    td.innerHTML = commits[iter].unit.name
                }
                // console.log(commits[iter].name)
                // console.log(iter)
                tr.appendChild(td);
                // if(iter<249)
                    iter++
        }
        table.appendChild(tr);
    }
    element.appendChild(table);
    iter = 0
}


