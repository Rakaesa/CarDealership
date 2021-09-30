// $(document).ready(function() {
//     searchButtonListener();
// })
//
// function searchButtonListener() {
//     $('#searchButton').click(function() {
//         //$('#errorMessagesMain').empty();
//         const name = $('#user').val();
//         const fromDate = $('#fromDate').val();
//         const toDate = $('#toDate').val();
//         // if ((category == null) || (keyword == "")) {
//         //     $('#errorMessagesMain').append($('<li>').attr({class: 'list-group-item list-group-item-danger'}).text("Both Search Category and Search Term are required."));
//         //     return false;
//         // }
//         searchSales(name, fromDate, toDate)
//     });
// }
//
// function searchSales(name, fromDate, toDate) {
//
//     $('#contentRowsMain').empty();
//     const contentRows = $('#contentRowsMain');
//
//     $.ajax({
//         type: 'GET',
//         url: 'http://localhost:8080/admin/reports/sales',
//         success: function(SalesArray) {
//             $.each(SalesArray, function(index, sale){
//                 const name = sale.name;
//                 const totalSales = sale.totalsales;
//                 const totalVehicles = sale.totalvehicles;
//
//                 let row = '<tr>';
//                 row += '<td class="text-center">' + name+ '</td>';
//                 row += '<td class="text-center">' + totalSales + '</td>';
//                 row += '<td class="text-center">' + totalVehicles+ '</td>';
//                 row += '</tr>';
//
//                 let curValue;
//                 if(sale.name.includes(name) && sale.){
//                     curValue = true;
//                 }
//                 // switch(category) {
//                 //     case "title":
//                 //         var curValue = DVD.title.includes(keyword);
//                 //         break;
//                 //     case "releaseYear":
//                 //         var curValue = DVD.releaseYear == keyword;
//                 //         break;
//                 //     case "directorName":
//                 //         var curValue = DVD.director.includes(keyword);
//                 //         break;
//                 //     case "rating":
//                 //         var curValue = DVD.rating == keyword;
//                 //         break;
//                 //     default:
//                 //         var curValue = false;
//                 // }
//
//                 if ( curValue ) {
//                     contentRows.append(row);
//                 }
//
//             })
//
//         },
//         error: function() {
//             $('#errorMessages')
//                 .append($('<li>')
//                     .attr({class: 'list-group-item list-group-item-danger'})
//                     .text('Error calling web service. Please try again later.'));
//
//         }
//     })
// }
