let btnEditar = document.querySelectorAll(".acao-editar");
let btnExcluir = document.querySelectorAll(".acao-excluir");
const fechaModal = document.querySelector(".close-modal");
const fechaModalNova = document.querySelector(".close-modal-nova");
const btnNovaPeca = document.querySelector(".nova-peca");
const btnNovaSalvar = document.querySelector(".nova-salvar");
const tableBody = document.querySelector("tbody");


function atualizaBtnEditarEExcluir() {
    btnEditar.forEach( btn => {
        btn.addEventListener("click", e => {
            const target = e.target;
            document.querySelector('.modal-editar').classList.remove('hidden');
            const fNome = target.closest('.row-tbody').querySelector(".field-nome").textContent;

            document.querySelector('.peca-nome').textContent = fNome;

            //Outras alterações após ter o bd configurado
        });
    });
    
    btnExcluir.forEach( btn => {
        btn.addEventListener("click", e => {
            const target = e.target.closest('.row-tbody');
            target.remove();

            //Outras alterações após ter o bd configurado
        });
    });
}

atualizaBtnEditarEExcluir();


fechaModal.addEventListener("click", () => {
    document.querySelector('.modal-editar').classList.add('hidden');
});


btnExcluir.forEach( btn => {
    btn.addEventListener("click", e => {
        const target = e.target.closest('.row-tbody');
        target.remove();
        
        //Outras alterações após ter o bd configurado
    });
});


btnNovaPeca.addEventListener("click", () => {
    document.querySelector('.modal-nova-peca').classList.remove('hidden');  
});

 btnNovaSalvar.addEventListener("click", e => {
        e.preventDefault();
        const pecaNome = document.querySelector(".form-nome").value;
        const pecaPrazo = document.querySelector(".form-prazo").value;
        const pecaValor = document.querySelector(".form-valor").value;
        
        const html = `<tr class="row-tbody"> 
                        <td class="field-nome">${pecaNome}</td>
                        <td class="field-prazo">${pecaPrazo} dias úteis</td>
                        <td class="field-valor">R$${pecaValor}</td>
                        <td class="text-center field-acao">
                            <button class="btn btn-info w-25 acao-editar">Editar</button>
                            <button class="btn btn-danger w-25 acao-excluir">Excluir</button>
                        </td>
                    </tr>`;
        tableBody.insertAdjacentHTML("beforebegin", html);
        document.querySelector(".form-nome").value = "";
        document.querySelector(".form-prazo").value = "";
        document.querySelector(".form-valor").value = "";
        document.querySelector('.modal-nova-peca').classList.add('hidden');
        btnEditar = document.querySelectorAll(".acao-editar");
        btnExcluir = document.querySelectorAll(".acao-excluir");
        atualizaBtnEditarEExcluir();
    });


fechaModalNova.addEventListener("click", () => {
    document.querySelector('.modal-nova-peca').classList.add('hidden');
});








