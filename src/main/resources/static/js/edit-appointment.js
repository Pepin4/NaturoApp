// Attente que le DOM soit complètement chargé avant d'exécuter le script
document.addEventListener("DOMContentLoaded", function () {
    // Sélectionne le formulaire de rendez-vous
    const appointmentForm = document.getElementById('appointmentForm');

    // Écouteur d'événement pour la soumission du formulaire
    appointmentForm.addEventListener('submit', function(event) {
        event.preventDefault(); 

        let isValid = true;
        const errors = document.querySelectorAll('.error');
        errors.forEach(error => error.textContent = ''); 

        // Liste des champs à valider avec leurs messages d'erreur associés
        const fields = [
            {name: 'dateTime', errorId: 'dateTimeError'},
            {name: 'subject', errorId: 'subjectError'}
        ];

        // Validation de chaque champ requis
        fields.forEach(field => {
            const input = document.querySelector(`[name=${field.name}]`);
            const errorSpan = document.getElementById(field.errorId);
            if (input && input.value === '') {
                isValid = false;
                errorSpan.textContent = 'Ce champ est requis';

                // Fait défiler la page vers le haut si une erreur est trouvée
                window.scrollTo({
                    top: 0,
                    behavior: 'smooth'
                });
            }
        });

        // Soumet le formulaire si toutes les validations sont réussies
        if (isValid) {
            appointmentForm.submit();
        }
    });

    // Fonction pour ouvrir une nouvelle fenêtre avec le formulaire du patient
    const openButton = document.querySelector("button[onclick^='openPatientWindow']");
    if (openButton) {
        openButton.addEventListener("click", function() {
            // Récupère l'ID du patient soit depuis un champ de saisie, soit à partir de l'URL
            const patientId = document.querySelector("input[name='patientId']")?.value || 
                              window.location.href.match(/appointments\/edit\/(\d+)/)?.[1];

            // Ouvre une nouvelle fenêtre avec le formulaire du patient
            if (patientId) {
                const patientWindow = window.open(`/patients/edit/${patientId}`, 'Modifier Détails du Patient', 'width=1200,height=800');
                if (!patientWindow) {
                    alert('Veuillez autoriser les fenêtres contextuelles dans votre navigateur.');
                }
            }
        });
    }
});
