console.log("CategoriaVendida JS cargado");

let categoriaChart; // Variable global para almacenar la instancia

fetch('/api/dashboard/categoria-mas-vendida')
  .then(response => response.json())
  .then(data => {
    const labels = data.map(e => e.nombreCategoria);
    const cantidades = data.map(e => e.totalVendidos);

    const ctx = document.getElementById('categoriaVendidaChart').getContext('2d');

    // 🔴 Si ya existe un gráfico, destrúyelo antes de crear otro
    if (categoriaChart) {
      categoriaChart.destroy();
    }

    categoriaChart = new Chart(ctx, {
      type: 'bar',
      data: {
        labels: labels,
        datasets: [{
          data: cantidades,
          backgroundColor: 'rgba(75, 192, 192, 0.5)',
          borderColor: 'rgba(75, 192, 192, 1)',
          borderWidth: 1
        }]
      },
      options: {
        responsive: true,
        maintainAspectRatio: true,
        plugins: {
          legend: { display: false }
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
            ticks: {
              beginAtZero: true,
              color: '#888',
              font: { size: 13 }
            }
          }
        }
      }
    });
  })
  .catch(error => console.error("Error al obtener datos de categorías:", error));
