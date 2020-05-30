console.log("it's working");

const urlAllTile = "http://localhost:8080/game/tiles";
const urlAllUnits = "http://localhost:8080/game/units";
const urlCreateArcher = "http://localhost:8080/unit/createArcher";
const urlCreateWarrior = "http://localhost:8080/unit/createWarrior";
const urlCreateCity = "http://localhost:8080/unit/createCity";
const urlCreateColonist = "http://localhost:8080/unit/createColonist";
const urlMoveUnit = "http://localhost:8080/unit/moveUnit";
const urlRestoreActionPoints = "http://localhost:8080/game";
const urlTurnEnd = "http://localhost:8080/game/turnEnd";
const urlIsCanMove = "http://localhost:8080/game/isCanMove";
let isClickMove = false;
let DataForInfo;
// createApp принимает первым аргументом функцию onStateUpdate
// onStateUpdate функция будет вызвана каждый раз когда меняется state
// createApp принимает первым аргументом функцию onStateUpdate
// onStateUpdate функция будет вызвана каждый раз когда меняется state
function createApp(onStateUpdate) {
    // создается изначальное состояние приложения
    // состояние - это что-то вроде базы данных где будут хранится все наши данные
    // все наше WEB приложение должно брать данные только от сюда (это единственный источник правды)
    // обновить состояние можно только одним способом - вызвать функцию updateState
    const state = {
        current: {
            commits: [],
            isCanMove: false,
            units : []
        }
    };

    // updateState принимает первым аргументом функцию updateStateFunction
    // updateStateFunction принимает как аргумент текущее состояние (currentState) и должна вернуть новое состояние (updatedState)
    function updateState(updateStateFunction) {
        const updatedState = updateStateFunction(state.current);

        state.current = { ...state.current, ...updatedState };
        onStateUpdate(state.current, updateState);

        console.log("current state: ", state.current);
    }

    // вызываем onStateUpdate сразу же после создания App
    onStateUpdate(state.current, updateState);

    return { updateState: updateState };
}
// _____ SETUP APP _________

window.onload = function main() {
    // renderApp вызван на каждое обновление state
    const app = createApp(renderApp);

    const getAllData = () => {

        getBoolean()
            .then(function (isCanMove) {
                app.updateState(function () {
                    return {
                        isCanMove: isCanMove
                    };
                });
            })
            .catch(function (error) {
                console.error("getBoolean error", error);
            });

        getUnits()
            .then(function (units) {
                app.updateState(function () {
                    return {
                        units: units
                    };
                });
            })
            .catch(function (error) {
                console.error("getUnits error", error);
            });

        getCommits()
            .then(function (commits) {
                app.updateState(function () {
                    return {
                        commits: commits
                    };
                });
            })
            .catch(function (error) {
                console.error("getCommits error", error);
            });
    }

    getAllData()

    const button = document.getElementById('Reload')
    if(button) {
        button.addEventListener("click", getAllData);
    }
};

// _____ RENDER FUNCTIONS _________
//   все функции которые отвечают за отрисовку
//   вызывается тогда когда обновляется state
function renderApp(state) {
    DataForInfo = null;
    DataForInfo = [...state.units];
    createTable(state.commits);
    checkTurn(state.isCanMove)
}

function checkTurn(boolean) {
    const imgMove = document.getElementById('imgMove')
    const imgLock = document.getElementById('imgLock')
    if(boolean){
        imgMove.style.display = "inline"
        imgLock.style.display = "none"
    }else {
        imgMove.style.display = "none"
        imgLock.style.display = "inline"
    }
}

function createTable(commits) {
    if (!commits) return
    if (commits.length < 1) return

    const SIDE = 16;
    const localUser = document.getElementById("username").value
    let iter = 0;

    // чтобы избежать мутирования данных
    // мутирование данных в js приводит к багам
    const commitsSorted = [...commits].sort(function (a, b) {
        return a.id - b.id;
    });

    // const a = { a: 1, b: 2 }
    // const b = { c: 3 }
    // const c = { ...a, ...b }


    const table = document.createElement("div");
    table.setAttribute("class","table")

    for (let i = 0; i < SIDE; i++) {
        const tr = document.createElement("div");
        tr.setAttribute("class","row")

        for (let j = 0; j < SIDE; j++) {
            const td = document.createElement("div");
            td.setAttribute("class","cell")
            const id = commitsSorted[iter].id
            td.setAttribute("onclick", "setIdTileA("+id+")")
                const land = document.createElement("div");
                land.setAttribute("class","land")
                land.setAttribute("id", commitsSorted[iter].id);
                if (commitsSorted[iter].land === "Mountain") {
                    land.classList.add("mountain");
                }else if (commitsSorted[iter].land === "Desert"){
                    land.classList.add("desert");
                }
                else {
                    land.classList.add("grass");
                }
                        const city = document.createElement("div");
                        city.setAttribute("class","city")

                        if(commitsSorted[iter].city !== null){
                            if(localUser === commitsSorted[iter].city.player.username)
                                city.classList.add("City")
                            else
                                city.classList.add("EnemyCity")
                        }

                            const unit = document.createElement("div");
                            unit.setAttribute("class","unit")

                            if (commitsSorted[iter].unit !== null) {
                                unit.setAttribute("onclick", "showUnitDetails("+commitsSorted[iter].unit.id+")")
                                if( localUser === commitsSorted[iter].unit.player.username ){
                                    switch (commitsSorted[iter].unit.type) {
                                        case "Archer":
                                            unit.classList.add("Archer")
                                            break;
                                        case "ArcherVeteran":
                                            unit.classList.add("ArcherVeteran")
                                            break;
                                        case "Colonist":
                                            unit.classList.add("Colonist")
                                            break;
                                        case "Warrior":
                                            unit.classList.add("Warrior")
                                            break;
                                        case "WarriorVeteran":
                                            unit.classList.add("WarriorVeteran")
                                            break;
                                    }
                                }else {
                                    switch (commitsSorted[iter].unit.type) {
                                        case "Archer":
                                            unit.classList.add("EnemyArcher")
                                            break;
                                        case "ArcherVeteran":
                                            unit.classList.add("EnemyArcherVeteran")
                                            break;
                                        case "Colonist":
                                            unit.classList.add("EnemyColonist")
                                            break;
                                        case "Warrior":
                                            unit.classList.add("EnemyWarrior")
                                            break;
                                        case "WarriorVeteran":
                                            unit.classList.add("EnemyWarriorVeteran")
                                            break;
                                    }
                                }
                            }
                        city.appendChild(unit)
                    land.appendChild(city)
                td.appendChild(land)
            tr.appendChild(td);
        iter++;
        }
        table.appendChild(tr);
    }

    const tableContainer = document.querySelector("#tableContainer");

    if (tableContainer) {
        // удаляем все что есть в контейнере и добавляем только что созданную таблицу
        tableContainer.innerHTML = "";
        tableContainer.appendChild(table);
    }
}

// ___________ Another Functions __________

function setIdTileA(id) {
    const cell = document.getElementById(id)
    const inputs = document.getElementsByClassName("idTileA")
    const cells = document.getElementsByClassName("land")
    for (let input of inputs) {
        input.setAttribute("value", id)
    }
    if(isClickMove) {
        cell.style.borderColor = "blue";
        const inputA = document.getElementById("idTileA")
        const inputB = document.getElementById("idTileB")

        inputA.removeAttribute("class")
        inputA.setAttribute("class","idTileA")
        inputB.removeAttribute("class")
        inputB.setAttribute("class","idTileB")
        isClickMove = false
    } else {
        for (let cell1 of cells) {
            cell1.removeAttribute("style")
        }
        cell.style.borderColor = "red";
    }
}

function firstClickOnMove() {
    const inputA = document.getElementById("idTileA")
    const inputB = document.getElementById("idTileB")

    inputA.removeAttribute("class")
    inputA.setAttribute("class","idTileB")
    inputB.removeAttribute("class")
    inputB.setAttribute("class","idTileA")
    isClickMove = true
}

function showUnitDetails(id){

}


// _____ API _________
// все функции которые работаеют с API

async function getCommits() {
    const response = await fetch(urlAllTile);
    return await response.json();
}

async function getUnits() {
    const response = await fetch(urlAllUnits);
    return await response.json();
}

async function getBoolean() {
    const response = await fetch(urlIsCanMove);
    return await response.json();
}



function restoreActionPoints() {
  fetch(urlRestoreActionPoints, {
    method: "POST",
    redirect: "manual"
  });
}

function createCity() {

        let form = new FormData(document.getElementById("createCityForm"));

        if (form.values() !== null) {
            fetch(urlCreateCity, {
                method: "POST",
                redirect: "manual",
                body: form
            });
        }

}

function createColonist() {

        let form = new FormData(document.getElementById("createColonistForm"));
        if (form.values() !== null) {
            fetch(urlCreateColonist, {
                method: "POST",
                redirect: "manual",
                body: form
            });
        }

}

function createArcher() {

        const form = new FormData(document.getElementById("createArcherForm"));

        if (form.values() !== null) {
            fetch(urlCreateArcher, {
                method: "POST",
                redirect: "manual",
                body: form
            });
        }

}

function createWarrior() {

        let form = new FormData(document.getElementById("createWarriorForm"));

        if (form.values() !== null) {
            fetch(urlCreateWarrior, {
                method: "POST",
                redirect: "manual",
                body: form
            });
        }

}

function moveUnit() {

        let form = new FormData(document.getElementById("moveUnitForm"));

        if (form.values() !== null) {
            fetch(urlMoveUnit, {
                method: "POST",
                redirect: "manual",
                body: form
            });
        }

}

function finishTurn(){
    fetch(urlTurnEnd, {
        method: "POST"
    })
}


