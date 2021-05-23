function filterNeedies(responseDisplayMode) {
debugger;
    var optionId = $("#optionFilter option:selected").val();
    if (optionId) {
        sendResponceForFilter('/filterByOption/' + optionId, responseDisplayMode);
    } else {
        sendResponceForFilter('/getAllNeedies', responseDisplayMode);
    }
}


function sendResponceForFilter(url, responseDisplayMode) {
    $.ajax({
        type: "GET",
        url: url,
        success: function (response) {
            displayNeedies(responseDisplayMode, response);
        }
    });
}

function displayNeedies(displayMode, response) {
    switch (displayMode) {
        case "MANAGING_NEEDIES":
            managingNeediesDisplay(response);
            break;
        case "CATALOGUE":
            displayNeediesForCatalogue(response);
            break;
    }
}
//исправить
//function managingNeediesDisplay(response) {
//    $("#productsTable tr").remove();
//    response.forEach(function (value) {
//        $("#productsTblBody").append(buildProductRow(value));
//    });
//}
//исправить
function displayNeediesForCatalogue(response) {
    $('#needyGrid').empty();
    response.forEach(function (value) {
        $('#needyGrid').append(buildCatalogueCard(value));
    });
    if (response.length == 0) {
        $('#needyGrid').append(buildEmptyResultCard());
    }
}

//исправить
//function buildProductRow(product) {
//    return '<tr><th scope="row" class="text-center" width="15%"><span>' + product.name + '<span/></th>\n' +
//        '            <td class="text-center" width="20%"><img class="product-img" src=' + product.photoPath +
//        '               alt="No photo ¯\_(ツ)_/¯" /></td>\n' +
//        '            <td class="text-center" width="10%"><span>' + product.price.toLocaleString("en", {minimumFractionDigits: 2}) +
//        '               <span/></td>\n' +
//        '            <td width="30%"><span>' + product.description + '<span/></td>\n' +
//        '            <td class="text-center" width="15%"><span>' + (product.available ? 'Available' : 'On Order') + '<span/></td>\n' +
//        '            <td class="text-center" width="20%">' +
//        '            <a href="editProductForm/' + product.productId + '">' +
//        '                <button class="btn btn-primary btn-xs" type="button">Edit</button>' +
//        '            </a>' +
//        '                <button class="btn btn-primary btn-xs" type="button">Delete</button>\n' +
//        '            </td>\n' +
//        '        </tr>'
//}

function buildCatalogueCard(needy) {
    return '        <div class="overDiv">\n' +
        '                    <div>\n' +
        '                        <img  src=' + needy.photoPath + '>\n' +
        '                    </div>\n' +
        '                    <h4>' + needy.nameNeedy + '</h4>\n' +
        '                     <p>' + needy.option.nameOfOption + '</p>\n' +
        '                    <span>' +
        '                     ' + needy.option.price.toLocaleString("en", {minimumFractionDigits: 2}) + '</span>\n' +
        '                    <p><a class="btn" href="' + '/getNeedyDetails/' + needy.needyId + '">Details</a></p>\n' +
        '        </div>'
}

function buildEmptyResultCard() {
    return '        <div class="text-center m-auto">\n' +
        '            <h3 style="color: red;">O_ops! Products not found &#9785;</h3>\n' +
        '            <img src="/img/notfound.png">\n' +
        '        </div>';
}

function changeNeediesAmount(needyId, currNeedyRow) {
    var neediesAmount = currNeedyRow.value;
    $.ajax({
        type: "POST",
        url: "/basket/changeNeediesAmount",
        dataType: 'json',
        data: {
            "needyId": needyId,
            "neediesAmount": neediesAmount
        }
    });
    recalculateTotalPrice();
}

function recalculateTotalPrice() {
    var resultPrice = 0;
    $("#basketTblBody tr").each(function () {
        var price = parseFloat(this.children[2].children[0].textContent);
        var amount = parseInt(this.children[3].children[0].value);
        resultPrice += price * amount;
    });
    $("#totalPrice")[0].textContent = resultPrice.toLocaleString("en", {minimumFractionDigits: 2});
}

function deleteNeedyFromBasket(needyId, currentRow) {
    $.ajax({
        type: "POST",
        url: "/basket/deleteFromBasket",
        dataType: 'json',
        data: {
            "needyId": needyId
        }
    });
    currentRow.parentElement.parentElement.remove();
    if ($("#basketTblBody")[0].children.length == 0) {
        $("#basketForm").remove();
        $("#basketContent").append(buildEmptyBasketDiv());
    } else {
        recalculateTotalPrice();
    }
    updateBasketItemsCount();
}


function buildEmptyBasketDiv() {
    return '<div id="emptyBasket" th:if="${selectedNeediesMap == null or selectedNeediesMap.isEmpty()}">' +
        '<div class="text-center m-auto">' +
        '<h3 style="color: red;">O_ops! The basket is empty again &#9785;</h3>' +
        '<img src="/img/notfound.png">' +
        '</div>' +
        '</div>';
}

function updateBasketItemsCount() {
    $.ajax({
        type: "GET",
        url: "/basket/basketAmount",
        dataType: 'json',
        success: function (response) {
            if (response) {
                $('#itemCount').html(response).css('display', '');
            } else {
                $('#itemCount').html(response).css('display', 'none');
            }
        }
    });
}

$(document)
    .on('click', 'form button[type=submit]', function (e) {
        switch (e.target.id) {
            case "saveOption":
                validateOptionForm(e);
                break;
            case "saveNeedy":
                validateNeedyForm(e);
                break;
        }
    });

function validateOptionForm(e) {
//maybe ERROR : same name
    var optionNameText = $.trim($("#optionName").val());
    var isValid = optionNameText.length > 0 && optionNameText.length < 257;
    if (!isValid) {
        e.preventDefault();
        $("#nameEmptyError").text("Please, enter valid name: not empty and no more than 256 chars!");
        $("#optionName").css("border-color", "red");
    }
}

function validateNeedyForm(e) {
    var formIsValid = true;
    //maybe error
    $(".product-field").each(function (i, field) {
        var fieldValue = field.children[0].value;
        if (!fieldValue) {
            fillErrorMessage(field);
            formIsValid = false;
        }
    });
    if (!formIsValid) {
        e.preventDefault();
    }
}

function fillErrorMessage(field) {
    var classList = field.parentElement.children[2].classList;
    if (classList.contains("error-hidden")) {
        field.parentElement.children[2].classList.remove("error-hidden");
        field.children[0].classList.add("error-border");
    }
}
