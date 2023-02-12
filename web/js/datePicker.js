    $(document).ready(function() {
      $("#filter").click(function() {
        let startDate = new Date($("#startDate").val());
        let endDate = new Date($("#endDate").val());
        let rowDate;
        if ($("#checkHoje").is(":checked")) 
            rowDate = new Date();
        $("table tbody tr").each(function() {
          if (!$("#checkHoje").is(":checked")) {
            rowDate = new Date($(this).find("td:eq(2) input").val());
          }
          console.log(rowDate);
          if (rowDate.getTime() >= startDate.getTime() && rowDate.getTime() <= endDate.getTime()) {
            $(this).show();
          } else {
            $(this).hide();
          }
        });
      });
    });
