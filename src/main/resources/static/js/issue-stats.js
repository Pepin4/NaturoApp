// Attente que le DOM soit complètement chargé avant d'exécuter le script
document.addEventListener("DOMContentLoaded", function() {

    // Récupérer les données depuis l'URL /patient-issue-statistics-data
    fetch('/patient-issue-statistics-data')
        .then(response => response.json())
        .then(issueStats => {

            // Vérifie si les données récupérées sont un tableau
            if (Array.isArray(issueStats)) {

                // Transformation des données pour le graphique
                var issueNames = issueStats.map(function(stat) {
                    return stat.issue_name;
                });
                var patientCounts = issueStats.map(function(stat) {
                    return stat.patient_count;
                });

                // Créer le graphique avec Chart.js
                var ctx = document.getElementById('issueChart').getContext('2d');
                new Chart(ctx, {
                    type: 'bar',
                    data: {
                        labels: issueNames, // Labels pour l'axe des X
                        datasets: [{
                            label: 'Nombre de patients',
                            data: patientCounts, // Données pour l'axe des Y
                            backgroundColor: 'rgba(54, 162, 235, 0.2)', // Couleur de fond des barres
                            borderColor: 'rgba(54, 162, 235, 1)', // Couleur des bordures des barres
                            borderWidth: 1
                        }]
                    },
                    options: {
                        responsive: true,
                        scales: {
                            y: {
                                beginAtZero: true
                            }
                        }
                    }
                });
            }
        })
        .catch(error => {
            console.error('Error fetching data:', error);
        });
});
