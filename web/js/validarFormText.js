$(document).ready(function(){
    $('form input').submit(function(){
        $(this).removeClass("error");
        let errorName = "erro"+$(this).attr("name");
        if(!$(this).val()){
            console.log(errorName);
            $(this).addClass("error");
            var span = "<span id="+errorName+" style='color: red; margin-top: 0; \n\
                            margin-bottom: 10px' >Campo obrigatório!</span>";
            $(this).after(span);
        } else{
            $(this).removeClass("error");
            $("#"+errorName).remove();
        }
    });
});


