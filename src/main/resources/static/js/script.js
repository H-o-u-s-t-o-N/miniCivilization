let SIDE = 5;
console.log("it's working")

window.onload = function loadScript() {
    let element = document.querySelector('#table');

    function creatTable(elem) {
        let table = document.createElement("table");

        for (let i = 0; i < SIDE; i++) {
            let tr = document.createElement("tr");

            for (let j = 0; j < SIDE; j++) {
                let td = document.createElement("td");
                td.innerText = "some text"
                tr.appendChild(td);
            }
            table.appendChild(tr);
        }
        elem.appendChild(table);
    }

    creatTable(element);
}


