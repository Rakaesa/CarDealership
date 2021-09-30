$(document).ready(function() {
    searchButtonListener();
})

function searchButtonListener() {
    $('#searchButton').click(function() {
        //$('#errorMessagesMain').empty();
        const user = $('#searchUser').val();
        const fromDate = $('#fromDate').val();
        const toDate = $('#toDate').val();
        // if ((category == null) || (keyword == "")) {
        //     $('#errorMessagesMain').append($('<li>').attr({class: 'list-group-item list-group-item-danger'}).text("Both Search Category and Search Term are required."));
        //     return false;
        // }
        searchSales(user, fromDate, toDate)
    });
}

function searchSales(user, fromDate, toDate) {

    $('#contentRowsMain').empty();
    const contentRows = $('#contentRowsMain');

    $.ajax({
        type: 'GET',
        url: 'http://localhost:8080/admin/reports/sales',
        success: function(SalesArray) {
            $.each(SalesArray, function(index, sale){
                const name = sale.name;
                const totalSales = sale.totalsales;
                const totalVehicles = sale.totalvehicles;

                let row = '<tr>';
                row += '<td class="text-center">' + name+ '</td>';
                row += '<td class="text-center">' + totalSales + '</td>';
                row += '<td class="text-center">' + totalVehicles+ '</td>';
                row += '</tr>';

                contentRows.append(row);
            })

        },
        error: function() {
            $('#errorMessages')
                .append($('<li>')
                    .attr({class: 'list-group-item list-group-item-danger'})
                    .text('Error calling web service. Please try again later.'));

        }
    })
}
