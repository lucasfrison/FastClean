let btnExcluir = document.querySelectorAll("#acao-excluir");

btnExcluir.forEach(btn => {
    btn.addEventListener("click", e => {
        Swal.fire({
            title: 'Deseja mesmo excluir o funcionário?',
            text: "Não será possível reverter a ação!",
            icon: 'warning',
            showCancelButton: true,
            confirmButtonColor: '#35427a',
            cancelButtonColor: '#d33',
            confirmButtonText: 'Sim',
            cancelButtonText: 'Cancelar'
        }).then((result) => {
            if (result.isConfirmed) {
                window.location.href = btn.getAttribute('href');
            }
        })
        e.preventDefault();
    })
});


