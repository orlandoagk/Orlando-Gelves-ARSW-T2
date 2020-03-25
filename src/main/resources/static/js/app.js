var app = (function(){
    var generarTabla = function(){
        apiclient.getCovid19AllCases(crearTabla);
    }

    var crearTabla = (function (paises) {
        var superPaises = JSON.stringify(paises.data);
        var paisesCompletos = JSON.parse(superPaises)
        $("#todosLosPaises").empty();
        paisesCompletos.map(function(element){
            var markup = "<tr> <td>"+ element.country +"</td> <td>"+element.deaths+"</td> <td>"+ element.confirmed +"</td> <td>"+ element.recovered +"</td> </tr>";
            $("#todosLosPaises").append(markup);
        })

    });

    var generarTablaByCountry = (function (country) {
        apiclient.getCovid19ByCountry(crearTablaCountry,country);
    });

    var crearTablaCountry = (function (regiones) {

        var superRegiones = JSON.stringify(regiones.data);
        var probando = JSON.parse(superRegiones);
        var deaths=0;
        var recovered = 0;
        var confirmed = 0;

        $("#regiones").empty();
        probando.map(function(element){
            deaths+=element.deaths;
            recovered+=element.recovered;
            confirmed+=element.confirmed;
            var markup = "<tr> <td>"+ element.province +"</td> <td>"+element.deaths+"</td> <td>"+ element.confirmed +"</td> <td>"+ element.recovered +"</td> </tr>";
            $("#regiones").append(markup);
        })
        $("#numeros").append("<tr> <td>Num Deaths</td> <td>"+deaths+"</td> </tr>");
        $("#numeros").append("<tr> <td>Num Infected</td> <td>"+confirmed+"</td> </tr>");
        $("#numeros").append("<tr> <td>Num Cured</td> <td>"+recovered+"</td> </tr>");

        plotMarkers(probando);
    });

    return{
        generarTabla:generarTabla,
        generarTablaByCountry:generarTablaByCountry
    }
})();

window.onload=app.generarTabla();