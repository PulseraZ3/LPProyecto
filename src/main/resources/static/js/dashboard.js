console.log("Dashboard JS cargado");

fetch('/api/dashboard/ventas-dia')
  .then(response => response.json())
  .then(data => {
    const labels = data.map(e => e.fechaPedido.substring(0, 10));
    const ventas = data.map(e => e.ventaTotal);

    const ctx = document.getElementById('ventasChart').getContext('2d');

    new Chart(ctx, {
      type: 'line',
      data: {
        labels: labels,
        datasets: [{
          data: ventas,
          backgroundColor: 'rgba(54, 162, 235, 0.10)',
          borderColor: 'rgba(54, 162, 235, 1)',
          borderWidth: 2,
          fill: true,
          tension: 0.3,
          pointRadius: 4,
          pointHoverRadius: 6,
          pointHitRadius: 10
        }]
      },
      options: {
        responsive: true,
        maintainAspectRatio: true,
        plugins: {
          legend: { display: false },
          title: { display: false }
        },
        scales: {
          x: {
            grid: { display: false },
            ticks: {
              color: '#888',
              font: { size: 12 },
              autoSkip: true,
              maxTicksLimit: 3
            }
          },
          y: {
            grid: { display: true, color: 'rgba(200,200,200,0.2)' },
            ticks: { display: true, color: '#888', font: { size: 13 } },
            beginAtZero: true
          }
        }
      }
    });
  })
  .catch(error => console.error("Error al obtener datos:", error));