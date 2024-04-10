//create function for get service ajax request
//url come from function parameters
const getServiceAjaxRequest = (url) => {

    //create empty variable for catch data
    let getServiceResponse;

    //send ajax request to get data from db
    //contentType : 'application/json' - send json format
    //no nees of asyncronus
    $.ajax(url, {
            contentType: 'json',
            type: "GET",
            async: false,
            success: function(data) {
                console.log("Success " + data);
                getServiceResponse = data;
            },

            error: function(resData) {
                console.log("Error " + resData);
                getServiceResponse = [];
            }
        })
        //return data back to function called file
    return getServiceResponse;
}


//this function used to fill dynamic data come from db to dropdown
const fillDataIntoSelect = (fieldId, message, dataList, propertyName, selectedValue) => {
    //empty static content of dropdown
    fieldId.inerHTML = '';

    //check message is empty or not
    if (message != "") {
        //create option element
        const optionMsg = document.createElement('option');
        //assign message for option
        optionMsg.innerText = message;
        //this option need to be selected & disabled
        optionMsg.selected = 'selected';
        optionMsg.disabled = 'disabled';
        //append the option into select
        fieldId.appendChild(optionMsg);
    }

    //get each element of data in dataList Array
    dataList.forEach(element => {
        //create option element
        const option = document.createElement('option');

        //assign value for option
        //normal string object convert into json format
        //advantage of json is we can found real object
        option.value = JSON.stringify(element);
        //assign text to option that key=propertyName in element object
        //in ex : dataList[i].propertyName or dataLidtI[propertyName]
        //in ex: students[1].name--> studentOb[name]
        option.innerText = element[propertyName];

        //if selectedValue equal with current options value then make it as selected
        if (selectedValue = element[propertyName]) {
            //option is need to selected
            option.selected = true;
        }

        //append the option element into select
        fieldId.appendChild(option);
    });
}