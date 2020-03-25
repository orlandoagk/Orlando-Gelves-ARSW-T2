var apiclient = ( function () {

    var getCovid19AllCases = (function (callback) {

        axios({
            method: 'GET',
            url: '/covid19/getAllCases',

        })
            .then(response => callback(response))
            .catch(error => console.log(error));
    });

    var getCovid19ByCountry = (function (callback,country) {
        axios({
            method: 'GET',
            url: '/covid19//getCasesByCountry/'+country,

        })
            .then(response => callback(response))
            .catch(error => console.log(error));
    })

    return{
        getCovid19AllCases:getCovid19AllCases,
        getCovid19ByCountry:getCovid19ByCountry
    }
})();