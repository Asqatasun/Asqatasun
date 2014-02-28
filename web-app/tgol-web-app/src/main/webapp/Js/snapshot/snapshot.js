$(document).ready(function() {
    var service = document.getElementById("snapshot-service-url").textContent.trim();

    console.log(service);
    if (service.length == 0) {
//        console.log("return cause service is empty");
        return;
    }
    var status = askSnapshotStatus(service);
    console.log(status);
    if (status === 'CREATED') {
        updateImageSrc(service);
//        console.log("return after display snapshot");
        return;
    }

    if(status === 'NOT_EXIST') {
        askSnapshot(service); // the snapshot is not created, send the get Request
    }
    var timeout=0;
    var intervalID = setInterval(function() {
//        console.log("wait a second before reinterogating");
        if (askSnapshotStatus(service) === 'CREATED') {
            updateImageSrc(service);
            clearInterval(intervalID);
        }
        timeout++;
        if (timeout == 8) {
            updateImageSrc(document.getElementById("fail-snapshot-url").textContent.trim());
            clearInterval(intervalID);
        }
    }, 1000);
});

function updateImageSrc(srcValue) {
    var snapshot = document.getElementById("snapshot");
    snapshot.src = srcValue;
    snapshot.removeAttribute('style');
    removeHiddenData();
}

function askSnapshotStatus(service) {
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.open( "GET", service+"&status=true", false );
    xmlHttp.send(null);
    var status = JSON.parse(xmlHttp.responseText).status;
//    console.log(status);
    return status;
}

function askSnapshot(service) {
    var xmlHttp = new XMLHttpRequest();
    xmlHttp.open( "POST", service, false );
//    var e = new Date().getTime();
    xmlHttp.send(null);
//    var f = new Date().getTime();
//    console.log("Execution : "+ (f-e)  + "ms");
//    console.log("Received "  +xmlHttp.responseText + " response");
}

function removeHiddenData () {
    removeElement(document.getElementById("snapshot-service-url"));
    removeElement(document.getElementById("fail-snapshot-url"));
}

function removeElement (element) {
    element.parentNode.removeChild(element);
}