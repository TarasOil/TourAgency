$(document).ready(function() {
    
    $.ajax({
        url: serverUrl + 'city/country?name=Україна',
        method: 'GET',
        dataType: 'json',
        contentType: 'application/json',
        success: function(response) {
            $.each(response, function(key, value) {
                $('#form-city').append(
                    `
                    <option value="${value.name}">${value.name}</option>
                    `
                );
            });
        }
    });
    
    let adults = $("#form-adults");
    let children = $("#form-children");

    adults.on("change", function(e) {
        if((adults[0].valueAsNumber + children[0].valueAsNumber) > 4) {
            adults[0].valueAsNumber = adults[0].valueAsNumber - 1;
        }
    });

    children.on("change", function(e) {
        if((adults[0].valueAsNumber + children[0].valueAsNumber) > 4) {
            children[0].valueAsNumber = children[0].valueAsNumber - 1;
        }
    });

    let modal = new bootstrap.Modal($("#tours-modal"));

    $('#search-form').submit(function(e) {
        e.preventDefault();

        $("#tour-list").hide();
        $("#tour-list").html("");
        $("#spinner").show();

        modal.show();

        let name = $("#form-name")[0].value;
        let nightsFrom = $("#form-nights-from")[0].value;
        let nightsTo = $("#form-nights-to")[0].value;
        let dateFrom = $("#form-date-from")[0].value;
        let dateTo = $("#form-date-to")[0].value;
        let city = $("#form-city")[0].value;
        let adults = $("#form-adults")[0].valueAsNumber
        let children = $("#form-children")[0].valueAsNumber

        $.ajax({
            url: serverUrl + `tour/prices?name=${name}&nightsFrom=${nightsFrom}&nightsTo=${nightsTo}&dateFrom=${dateFrom}&dateTo=${dateTo}&city=${city}&adults=${adults}&children=${children}`,
            method: 'GET',
            dataType: 'json',
            contentType: 'application/json',
            success: function(response) {
                console.log(response.length);
                if(response.length > 0) {
                    $.each(response, function(key, value) {
                        let date = new Date(value.departureDate);
                        $('#tour-list').append(
                            `
                            <div class="row tour-list-item">
                                <div class="col-4 tour-list-img" style="background-image: url(${value.hotel.images[0].imageUrl});">
                                </div>
                                <div class="col-6 tour-list-info">  
                                    <p class="tour-list-hotel-name">${value.hotel.name} ${value.hotel.starCategory}<i class="bi-star-fill"></i></p>
                                    <p class="tour-list-hotel-location">${value.hotel.city.name}, ${value.hotel.city.country.name}</p>
                                    <p class="tour-list-departure">Виліт: <span class="tour-list-span">${date.toLocaleDateString()}</span> з <span class="tour-list-span">${value.departureCity.name}</span> на <span class="tour-list-span">${value.nights} ${nights(value.nights)}</span></p>
                                    <p class="tour-list-room">Тип номера: <span class="tour-list-span">${value.roomType.name}</span></p>
                                    <p class="tour-list-feeding">Харчування: <span class="tour-list-span">${value.feedingType.name}</span></p>
                                </div>
                                <div class="col-2 tour-list-details">
                                    <div class="tour-list-price-wrapper">
                                        <p class="tour-list-price">${value.price}$</p>
                                        <p class="tour-list-people">за ${people(adults, children)}</p>
                                    </div>
                                    <button class="btn btn-warning tour-list-button">Готель</button>
                                </div>
                            </div>
                            `
                        );
                    });
                } else {

                }

                $("#spinner").hide();
                $("#tour-list").show();
            },
            error: function(response) {

            }
        });
        
    })
});

function nights(nights) {
    let n = nights%10;
    if(n == 1) {
        return "ніч";
    } else if (n > 1 && n < 5) {
        return "ночі";
    } else {
        return "ночей";
    }
}

function people(adults, children) {
    let s = adults;
    
    if(adults == 1) {
        s += "-го дорослого";
    } else {
        s += "-ох дорослих";
    }

    if(children > 0) {
        s += "<br/>та " + children;
        if(children == 1) {
            s += "-ну дитину";
        } else {
            s += "-ох дітей";
        }
    }

    return s;
}