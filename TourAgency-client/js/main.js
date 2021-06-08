$(document).ready(function() {
    
    $.ajax({
        url: serverUrl + "city/country?name=Україна",
        method: "GET",
        dataType: "json",
        contentType: "application/json",
        success: function(response) {
            $.each(response, function(key, value) {
                $("#form-city").append(
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

    let toursModal = new bootstrap.Modal($("#tours-modal"));
    let hotelModal = new bootstrap.Modal($("#hotel-modal"));

    $("#hotel-modal-button").on("click", function() {
        hotelModal.hide();
        toursModal.show();
    });

    let serviceTypeArr;

    $.ajax({
        url: serverUrl + `service_type`,
        method: "GET",
        dataType: "json",
        contentType: "application/json",
        success: function(response) {
            serviceTypeArr = response;
        }
    });

    $("#search-form").submit(function(e) {
        e.preventDefault();

        $("#tour-list").hide();
        $("#tour-list").html("");
        $("#spinner").show();

        toursModal.show();

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
            method: "GET",
            dataType: "json",
            contentType: "application/json",
            success: function(response) {
                if(response.length > 0) {
                    $.each(response, function(key, value) {
                        let date = new Date(value.departureDate);
                        $("#tour-list").append(
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
                                    <button class="btn btn-warning tour-list-button" id="tour-button-${key}">Готель</button>
                                </div>
                            </div>
                            `
                        );
                    });

                    $(".tour-list-button").on("click", function(e) {
                        let index = e.target.id.split("-")[2];
                        let hotel = response[index].hotel;

                        $("#hotel-info-container").html("");
                        $("#hotel-info-container").append(
                            `
                            <div class="row">
                                <div class="col-12 d-flex justify-content-center align-items-center" id="hotel-modal-name-wrapper" style="background-image: linear-gradient(rgba(0, 0, 0, 0.5), rgba(0, 0, 0, 0.5)), url(${hotel.images[0].imageUrl});">
                                    <div class="text-center">
                                        <p id="hotel-modal-name">${hotel.name}</p>
                                        <p id="hotel-modal-location">${hotel.city.name}, ${hotel.city.country.name}</p>
                                    </div>
                                </div>
                            </div>
                            <div class="row" id="hotel-modal-service-wrapper">
                            </div>
                            <div class="row" id="hotel-modal-image-wrapper">
                                <div id="hotel-carousel" class="carousel slide col-12" data-ride="carousel">
                                    <div id="carousel-item-wrapper" class="carousel-inner">
                                        <div class="carousel-item active">
                                            <img class="d-block w-100" src="${hotel.images[0].imageUrl}" alt="${index+1} slide">
                                        </div>
                                    </div>
                                    <button class="carousel-control-prev" type="button" data-bs-target="#hotel-carousel" data-bs-slide="prev">
                                        <span class="carousel-control-prev-icon" aria-hidden="true"></span>
                                        <span class="visually-hidden">Previous</span>
                                    </button>
                                    <button class="carousel-control-next" type="button" data-bs-target="#hotel-carousel" data-bs-slide="next">
                                        <span class="carousel-control-next-icon" aria-hidden="true"></span>
                                        <span class="visually-hidden">Next</span>
                                    </button>
                                </div>
                            </div>
                            `
                        );

                        $.each(hotel.images, function(i, v){
                            if(i == 0) {
                                return true;
                            }
                            
                            $("#carousel-item-wrapper").append(
                                `
                                <div class="carousel-item">
                                    <img class="d-block w-100" src="${v.imageUrl}" alt="${i+1} slide">
                                </div>
                                `
                            );
                        });

                        $.each(serviceTypeArr.sort(compare), function(index, value) {
                            $("#hotel-modal-service-wrapper").append(
                                `
                                <div class="col-4 hotel-modal-service-type-wrapper">
                                    <p class="hotel-modal-service-type-name">${value.name}</p>
                                    <div id="hotel-modal-service-type-${value.id}">
                                `);

                            $.each(hotel.services, function(i, v) {
                                if(v.service.serviceType.name.localeCompare(value.name) == 0){
                                    $("#hotel-modal-service-type-" + value.id).append(
                                        `
                                        <p class="hotel-modal-service-name">${v.service.name}</p>
                                        `
                                    );
                                };
                            });

                            $("#hotel-modal-service-wrapper").append(
                                `
                                    </div>
                                </div>
                                `
                            );
                        });

                        toursModal.hide();
                        hotelModal.show();
                    });
                } else {
                    $("#tour-list").append(
                        `
                        <div class="d-flex justify-content-center">
                            <p>Не знайдено турів за запитом</p>
                        </div>
                        `
                    );
                }

                $("#spinner").hide();
                $("#tour-list").show();
            },
            error: function(response) {
                $("#tour-list").append(
                    `
                    <div class="d-flex justify-content-center">
                        <p>Помилка при надсиланні запиту</p>
                    </div>
                    `
                );
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

function compare(a, b) {
    if (a.id < b.id){
      return -1;
    }
    if ( a.id > b.id ){
      return 1;
    }
    return 0;
}