/*===== SHOW NAVBAR  =====*/ 
const showNavbar = (toggleId, navId, bodyId, headerId) =>{
    const toggle = document.getElementById(toggleId),
    nav = document.getElementById(navId),
    bodypd = document.getElementById(bodyId),
    headerpd = document.getElementById(headerId)

    // Validate that all variables exist
    if(toggle && nav && bodypd && headerpd){
        toggle.addEventListener('click', ()=>{
            // show navbar
            nav.classList.toggle('show')
            // change icon
            toggle.classList.toggle('bx-x')
            // add padding to body
            bodypd.classList.toggle('body-pd')
            // add padding to header
            headerpd.classList.toggle('body-pd')
        })
    }
}

showNavbar('header-toggle','nav-bar','body-pd','header')

/*===== LINK ACTIVE  =====*/ 
const linkColor = document.querySelectorAll('.nav__link')

function colorLink(){
    if(linkColor){
        linkColor.forEach(l=> l.classList.remove('active'))
        this.classList.add('active')
    }
}
linkColor.forEach(l=> l.addEventListener('click', colorLink))


//graphs js

const ctx = document.getElementById('lineChartDashboard').getContext('2d');
const myChart = new Chart(ctx, {
    type: 'bar',
    data: {
        labels: ['Lun', 'Mar', 'Mier', 'Jue', 'Vie', 'Sab', 'Dom'],
        datasets: [{
            label: 'Consultas realizadas',
            data: [12, 19, 3, 5, 2, 40, 50],
            borderRadius: 10,
            borderSkipped: false,
            backgroundColor: [
                'rgba(24, 150, 255, 1)',
                'rgba(24, 150, 255, 1)',
                'rgba(24, 150, 255, 1)',
                'rgba(24, 150, 255, 1)',
                'rgba(24, 150, 255, 1)',
                'rgba(24, 150, 255, 1)',
            ],
            borderColor: [
                'rgba(24, 150, 255, 1)',
                'rgba(24, 150, 255, 1)',
                'rgba(24, 150, 255, 1)',
                'rgba(24, 150, 255, 1)',
                'rgba(24, 150, 255, 1)',
                'rgba(24, 150, 255, 1)',
            ],
            borderWidth: 1
        }]
    },
    options: {
        responsive: true,
        scales: {
            
            y: {
                beginAtZero: true,
                grid: {
                    display: false,
                }
            },
            x: {
                grid: {
                    drawBorder: false,
                  }
            }
        }
    }
});

const ctxPie = document.getElementById('pieChartDashboard').getContext('2d');

const myChartPie = new Chart(ctxPie, {
    type: 'pie',
    data: {
        labels: [
            'Red',
            'Blue',
            'Yellow'
          ],
          datasets: [{
            label: 'My First Dataset',
            data: [300, 50, 100],
            backgroundColor: [
              'rgb(255, 99, 132)',
              'rgb(54, 162, 235)',
              'rgb(255, 205, 86)'
            ],
            hoverOffset: 4
          }]
    },
    options: {
        responsive: true,
        scales: {
            
            y: {
                beginAtZero: true,
                grid: {
                    display: false,
                    drawBorder: false,
                }
            },
            x: {
                grid: {
                    drawBorder: false,
                    display: false,
                  }
            }
        }
    }
});






