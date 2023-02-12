$(document).on("change", "#datepicker .created_on", function() {
  var dataVal = $(this).datepicker('getDate');//get date from datepicker
  dataVal= $.datepicker.formatDate("d/m/y", dataVal);//set format date like in the rows
  //console.log(dataVal, typeof dataVal);
  if (dataVal != '') {
    $("tr:not('#table-header')").hide();//hide all rows
    //show rows with the same date selected
    $("label:contains('" +  dataVal + "')").each(function(){
      $(this).closest('tr').show();
    });
  }  
});

(function($) {
  $('.datepicker').each(function() {
    $(this).datepicker({
      changeMonth: true,
      changeYear: true,
      dateFormat: 'dd-mm-yy',
      onClose: function() {
        //triggerFocus();
      }
    });
  });
}(jQuery));
