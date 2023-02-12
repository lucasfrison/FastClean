const sitBtns = document.querySelectorAll('.sit');

sitBtns.forEach(btn => {
    btn.addEventListener('click', () => {
        if (btn.classList.contains('sit-aberto')) {
            Swal.fire({
                title: 'Confirmar Recolhimento?',
                text: "Não será possível reverter a ação!",
                icon: 'question',
                showCancelButton: true,
                confirmButtonColor: '#35427a',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Sim',
                cancelButtonText: 'Cancelar'
            }).then((result) => {
                if (result.isConfirmed) {
                    const pedidoId = btn.dataset.code;
                    window.location.href = `PedidoServlet?action=change&sit=aberto&id=${pedidoId}`;
                }
            })
        } else if (btn.classList.contains('sit-recolhido')) {
            Swal.fire({
                title: 'Confirmar Lavagem?',
                text: "Não será possível reverter a ação!",
                icon: 'question',
                showCancelButton: true,
                confirmButtonColor: '#35427a',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Sim',
                cancelButtonText: 'Cancelar'
            }).then((result) => {
                if (result.isConfirmed) {
                    const pedidoId = btn.dataset.code;
                    window.location.href = `PedidoServlet?action=change&sit=recolhido&id=${pedidoId}`;
                }
            })
        } else if (btn.classList.contains('sit-pago')) {
            Swal.fire({
                title: 'Finalizar Pedido?',
                text: "Não será possível reverter a ação!",
                icon: 'question',
                showCancelButton: true,
                confirmButtonColor: '#35427a',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Sim',
                cancelButtonText: 'Cancelar'
            }).then((result) => {
                if (result.isConfirmed) {
                    const pedidoId = btn.dataset.code;
                    window.location.href = `PedidoServlet?action=change&sit=pago&id=${pedidoId}`;
                }
            })
        }
    })
})



