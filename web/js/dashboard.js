const btnAberto = document.querySelectorAll('.sit-aberto');
const btnRejeitado = document.querySelectorAll('.sit-rejeitado');
const btnRecolhido = document.querySelectorAll('.sit-recolhido');
const btnAguardando = document.querySelectorAll('.sit-aguardando');
const btnPago = document.querySelectorAll('.sit-pago');
const btnFinalizado = document.querySelectorAll('.sit-finalizado');

const sitBtns = document.querySelectorAll('.sit');

console.log(sitBtns);

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

/*
 btnAberto.addEventListener('click', e => {
 Swal.fire({
 title: 'Deseja mesmo excluir a roupa?',
 text: "Não será possível reverter a ação!",
 icon: 'warning',
 showCancelButton: true,
 confirmButtonColor: '#35427a',
 cancelButtonColor: '#d33',
 confirmButtonText: 'Sim',
 cancelButtonText: 'Cancelar'
 }).then((result) => {
 if (result.isConfirmed) {
 // window.location.href = btn.getAttribute('href');
 }
 })
 e.preventDefault();
 })
 * 
 */


