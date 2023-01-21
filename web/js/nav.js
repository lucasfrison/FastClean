/* 
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/JSP_Servlet/JavaScript.js to edit this template
 */

const navSlide = () => {
    const navMobile = document.querySelector('.mobile');
    const nav = document.querySelector('.diferente');
    const navLinks = document.querySelectorAll(".diferente li");
    
    navMobile.addEventListener('click', () => {
        nav.classList.toggle('nav-active');

        navLinks.forEach((link,i) => {
            if (link.style.animation) {
                link.style.animation = "";
            } else {
                link.style.animation = `navLinkFade .5s ease forwards ${i / 7 + .4}s`;
            }
        })
        
        

        navMobile.classList.toggle('mover');
    });

    
}

navSlide();