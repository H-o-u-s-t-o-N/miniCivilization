console.log("it's working");

let urlAllTile = "http://localhost:8080/game/tile";
// let urlCreateUnit = "http://localhost:8080/tile/createUnit";
let urlCreateArcher = "http://localhost:8080/unit/createArcher";
let urlCreateWarrior = "http://localhost:8080/unit/createWarrior";
let urlMoveUnit = "http://localhost:8080/unit/moveUnit";
let urlRestoreActionPoints = "http://localhost:8080/game";
let urlIsCanMove = "http://localhost:8080/game/isCanMove";

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
            isCanMove: false
        }
    };

    // вызываем onStateUpdate впервые
    onStateUpdate(state.current);

    return {
        // updateState принимает первым аргументом функцию updateStateFunction
        // updateStateFunction принимает как аргумент текущее состояние (currentState) и должна вернуть новое состояние (updatedState)
        updateState: function (updateStateFunction) {
            const updatedState = updateStateFunction(state.current);

            state.current = { ...state.current, ...updatedState };
            onStateUpdate(state.current);

            console.log("current state: ", state.current);
        }
    };
}

// _____ SETUP APP _________

window.onload = function main() {
    // renderApp вызван на каждое обновление state
    const app = createApp(renderApp);

    getBoolean()
        .then(function (isCanMove) {
            app.updateState(function () {
                return {
                    isCanMove: isCanMove
                };
            });
        })
        .catch(function (error) {
            console.error("getBool error", error);
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
};

// _____ RENDER FUNCTIONS _________
// все функции которые отвечают за отрисовку
//   вызывается тогда когда обновляется state
function renderApp(state) {
    createTable(state.commits);
}

function createTable(commits) {
    if (!commits) return
    if (commits.length < 1) return

    const SIDE = 16;
    let iter = 0;

    // чтобы избежать мутирования данных
    // мутирование данных в js приводит к багам
    const commitsSorted = [...commits].sort(function (a, b) {
        return a.id - b.id;
    });

    // const a = { a: 1, b: 2 }
    // const b = { c: 3 }
    // const c = { ...a, ...b }

    const table = document.createElement("table");

    for (let i = 0; i < SIDE; i++) {
        const tr = document.createElement("tr");

        for (let j = 0; j < SIDE; j++) {
            const td = document.createElement("td");

            td.setAttribute("id", commitsSorted[iter].id);

            if (commitsSorted[iter].unit !== null) {
                td.innerHTML = commitsSorted[iter].unit.name;
                td.innerHTML += "<br>";
                td.innerHTML += commitsSorted[iter].unit.health;
                td.innerHTML += "<br>";
                td.innerHTML += commitsSorted[iter].id;
            } else {
                td.innerHTML = " . ";
                td.innerHTML += "<br>";
                td.innerHTML += commitsSorted[iter].id;
            }

            if (commitsSorted[iter].land === "Mountain") {
                td.classList.add("mountain");
            } else {
                td.classList.add("grass");
            }

            tr.appendChild(td);

            iter++;
        }

        table.appendChild(tr);
    }

    const tableContainer = document.querySelector("#table");

    if (tableContainer) {
        // удаляем все что есть в контейнере и добавляем только что созданную таблицу
        tableContainer.innerHTML = "";
        tableContainer.appendChild(table);
    }
}

// function refreshTable() {
//   commits.sort(function (a, b) {
//     return a.id - b.id;
//   });
//   commits.forEach((elem) => {
//     if (elem.unit !== null) {
//       document.getElementById(elem.id).innerHTML =
//         elem.unit.name + "<br>" + elem.unit.health + "<br>" + elem.id;
//     } else {
//       document.getElementById(elem.id).innerHTML = " . " + "<br>" + elem.id;
//     }
//   });
// }

// _____ API _________
// все функции которые работаеют с API

async function getCommits() {
    const response = await fetch(urlAllTile);
    let commits = await response.json();
    return commits;
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

function createUnit() {
  let form = new FormData(document.getElementById("createUnitForm"));

  if (form.values() !== null) {
    // console.log(form)
    fetch(urlCreateUnit, {
      method: "POST",
      redirect: "manual",
      body: form
    });
  }
}

function createArcher() {
  let form = new FormData(document.getElementById("createArcherForm"));

  if (form.values() !== null) {
    console.log(form);
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
    console.log(form);
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
    console.log(form);
    fetch(urlMoveUnit, {
      method: "POST",
      redirect: "manual",
      body: form
    });
  }
}
