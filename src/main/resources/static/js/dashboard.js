const deviceListField = $("#device-list");
const deviceAddForm = $("#device-add-form");

const dashboard = {
    init: () => {
        $.get("/api/auth", response => {
            if (response.details === null || response.details.devices === null) {
                deviceListField.html("");
                return;
            }
            let html = "";
            for (let i = 0; i < response.details.devices.length; i++) {
                const name = `<tr><td>${response.details.devices[i].id}</td><td>${response.details.devices[i].name}</td>`;
                if (response.details.devices[i].pins.length <= 0) {
                    html += name + `<td>Please connect device first</td></tr>`;
                } else {
                    html += name + updateDeviceList(response.details.devices[i]);
                }
            }

            deviceListField.html(html);
        });
    }
}

const updateDeviceList = device => {
    let info = '';
    for (let i = 0; i < device.pins.length; i++) {
        let value = '';
        let addon = '';
        switch (device.pins[i].pinType) {
            case "OUTPUT_PUSH":
                addon = `<button id="switch-button" data_id="${device.pins[i].id}">SWITCH</button>`;
                value = addon + `<br/>` + device.pins[i].switchStatus;
                break;
            case "OUTPUT_SWITCH":
                addon = `<button id="on-button" data_id="${device.pins[i].id}">ON</button><button id="off-button" data_id="${device.pins[i].id}">OFF</button>`;
                value = addon + `<br/>` + device.pins[i].switchStatus;
                break;
            default:
                value = device.pins[i].sensorValue;
                break;
        }
        info += `<td>${device.pins[i].switchName}` + `<br/>` + value + `<br/>${device.pins[i].pinType}</td>`;
    }
    return info + `</tr>`;
};


dashboard.init();