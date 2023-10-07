// script.js
document.addEventListener("DOMContentLoaded", function() {
    // JavaScript pour basculer la barre de navigation latérale
    document.getElementById('sidebarToggle').addEventListener('click', function() {
        const sidebar = document.getElementById('sidebar');
        const pageContent = document.querySelector('.page-content');

        if (sidebar.style.marginLeft === '0px') {
            sidebar.style.marginLeft = '-250px';
            pageContent.style.marginLeft = '0';
        } else {
            sidebar.style.marginLeft = '0';
            pageContent.style.marginLeft = '250px';
        }
    });
});
