fetch('/api/dashboard/metodo-pago')
  .then(response => response.json())
  .then(data => {
    const labels = data.map(e => e.metodoPago);
    const cantidades = data.map(e => e.cantidad);

    const ctx = document.getElementById('metodoPagoChart').getContext('2d');
    new Chart(ctx, {
      type: 'pie', 
      data: {
        labels: labels,
        datasets: [{
          label: 'Métodos de Pago',
          data: cantidades,
          backgroundColor: ['#75a545', '#36A2EB', '#650f77'], 
          borderWidth: 1
        }]
      },
      options: {
        responsive: true,
        plugins: {
          legend: {
            position: 'bottom'
          }
        }
      }
    });
  });
