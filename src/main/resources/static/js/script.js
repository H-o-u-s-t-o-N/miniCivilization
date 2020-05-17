console.log("it's working")
let SIDE = 16;
let iter = 0;
let commits
let urlAllTile = 'http://localhost:8080/tile'
let urlCreateUnit = 'http://localhost:8080/tile/createUnit'
let urlMoveUnit = 'http://localhost:8080/tile/moveUnit'



window.onload = async function main() {
await getTiles(urlAllTile)
    createTable()
}

async function getTiles(url) {
    let response = await fetch(url);
    commits = await response.json();

}
function createUnit() {
    let form = new FormData(document.getElementById('creatUnitForm'))
    if(form.values()!==null) {
        console.log(form)
        fetch(urlCreateUnit,
            {
                method: 'POST',
                redirect: "manual",
                body: form
            })
    }
}
function moveUnit() {
    let form = new FormData(document.getElementById('moveUnitForm'))
    if(form.values()!==null) {
        console.log(form)
        fetch(urlMoveUnit,
            {
                method: 'POST',
                redirect: "manual",
                body: form
            })
    }

}

function show() {
    console.log(commits)
}

function rememberTile() {
}

function createTable() {
    // document.getElementById("#start").style.display = "none";
    commits.sort(function (a,b) {
        return a.id-b.id
    })

    let element = document.querySelector('#table');
    let table = document.createElement("table")
    for (let i = 0; i < SIDE; i++) {
        let tr = document.createElement("tr");

        for (let j = 0; j < SIDE; j++) {

            let td = document.createElement("td");

                td.setAttribute("id", commits[iter].id)
                td.addEventListener('click',rememberTile)

                if(commits[iter].unit !== null){
                    td.innerHTML = commits[iter].unit.name
                    td.innerHTML += "<br>"
                    td.innerHTML += commits[iter].id
                }else {
                    td.innerHTML = " . "
                    td.innerHTML += "<br>"
                    td.innerHTML += commits[iter].id
                }
                if(commits[iter].land === "Mountain"){
                    td.style.backgroundColor = "darkgrey"
                }else {
                    td.style.backgroundColor = "aquamarine"
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

function refreshTable() {
    commits.sort(function (a,b) {
        return a.id-b.id
    })
    commits.forEach(elem =>{
    if(elem.unit !== null){
        document.getElementById(elem.id).innerHTML = elem.unit.name + "<br>" + elem.id
    }else {
        document.getElementById(elem.id).innerHTML = " . " + "<br>" + elem.id
    }
    }
    )
}




