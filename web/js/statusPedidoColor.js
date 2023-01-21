$(function() {  
    const statusPossiveis = [
        "Aberto", "Cancelado", "Aguardando Pagamento",
        "Recolhido", "Pago", "Finalizado"
    ];
    
    const colors = [
        "goldenrod", "red", "blue", "grey", "orange", "green"
    ];
    
    $("tbody tr").each(function(){

        text = $(this).text().toLowerCase();
        td = $(this).find('td:last-child'); 
        trText = td.text();
        
        for (i = 0; i < colors.length; i++) {
            if(trText.indexOf(statusPossiveis[i]) != -1) {
                $(td).css("color", colors[i]);
            }
        }
    });
});      


