const checkBoxes = document.querySelectorAll(".checkSel");
const btnOrcamento = document.querySelector(".btnOrcamento");
const divOrcamento = document.querySelector(".orcamento");


btnOrcamento.addEventListener("click", e => {
    const checkedBoxes = document.querySelectorAll('input[class=checkSel]:checked');
    
    if (checkedBoxes.length === 0) {
        e.preventDefault();
        divOrcamento.classList.add('hidden');
        return;
    }
    
    let contemQtde;
    let valorTotal = 0;
    let valorPeca = 0;
    let prazo = 0;
    checkedBoxes.forEach( box => {
        const checked = box.closest(".row-tbody").querySelector("input[name=quantidade]");
        if (checked.value === '') {
            contemQtde = false;
            divOrcamento.classList.add('hidden');
            return false;
        }  else {
            contemQtde = true;
            valorPeca = Number(box.closest(".row-tbody").querySelector(".field-valor").textContent.slice(2));
            valorPeca =  valorPeca * Number(box.closest(".row-tbody").querySelector(".inpQtde").value);
            valorTotal += valorPeca;
            
            const prazoPeca = Number(box.closest(".row-tbody").querySelector(".field-prazo").textContent.slice(0,1));
            if (prazo < prazoPeca) {
                prazo = prazoPeca;
            }
        } 
    });
    
    if (contemQtde) {
        e.preventDefault();
        divOrcamento.classList.remove('hidden');
        document.querySelector('.orcamentoValor').textContent = valorTotal;
        document.querySelector('.orcamentoPrazo').textContent = prazo;
        
    }
    
});

checkBoxes.forEach( box => {
    box.addEventListener('change', e => {
        const target = e.target;
        const checked = target.closest(".row-tbody").querySelector("input[name=quantidade]");
       if (target.checked) {
           checked.removeAttribute('disabled');
           checked.required = true;
       } else {
           checked.setAttribute('disabled', '');
           checked.value = '';
           checked.required = false;
       }
    }); 
});