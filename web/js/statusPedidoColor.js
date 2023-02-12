$(function() {  
    const statusPossiveis = [
        "EM ABERTO", "CANCELADO", "AGUARDANDO PAGAMENTO",
        "RECOLHIDO", "PAGO", "FINALIZADO"
    ];
    
    const colors = [
        "goldenrod", "red", "blue", "grey", "orange", "green"
    ];
    
    $("tbody tr").each(function(){

        text = $(this).text().toLowerCase();
        td = $(this).find('td:last-child'); 
        trText = td.text();
        console.log(trText);
        
        for (i = 0; i < colors.length; i++) {
            if(trText.indexOf(statusPossiveis[i]) != -1) {
                $(td).css("color", colors[i]);
            }
        }
    });
});      


