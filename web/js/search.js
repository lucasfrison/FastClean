let trText = '';
const semFiltro = "todos";
let radioFilter = semFiltro;
let val = '';
let text = '';

$(function () {
    $('.form-check').change(function() {
        
        radioFilter = $('input[name="radioState"]:checked').parent().text().toLowerCase();
        radioFilter = radioFilter.trim();
        
        $("tbody tr").hide();
        
        $("tbody tr").each(function(){
            
            text = $(this).text().toLowerCase();
            trText = $(this).text().toLowerCase();
            console.log(radioFilter === trText);
            
            if(trText.indexOf(radioFilter) != -1 || radioFilter === semFiltro) {
                if(text.indexOf(val) != -1)
                    $(this).show();
            }
            
        });
    });
});

$(function(){
  $('#search').keyup(function(){
    val = $(this).val().toLowerCase();
  
    $("tbody tr").hide();
    
    $("tbody tr").each(function(){
      
      text = $(this).text().toLowerCase();
      trText = $(this).find('td:last-child').text().toLowerCase();
      if(text.indexOf(val) != -1) {
          if(trText === radioFilter || radioFilter === semFiltro)
            $(this).show();
      }
    });
  });
});



