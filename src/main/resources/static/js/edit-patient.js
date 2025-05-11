// Attente que le DOM soit complètement chargé avant d'exécuter le script
document.addEventListener("DOMContentLoaded", function () {

    // Écouteur d'événement pour la soumission du formulaire de mise à jour du patient
    document.getElementById('updatePatientForm').addEventListener('submit', function(event) {
        let isValid = true;

        // Réinitialiser les erreurs précédentes
        const errors = document.querySelectorAll('.error');
        errors.forEach(error => error.textContent = '');

        // Liste des champs à valider avec leurs messages d'erreur associés
        const fields = [
            {name: 'firstName', errorId: 'firstNameError'},
            {name: 'lastName', errorId: 'lastNameError'},
            {name: 'dateOfBirth', errorId: 'dateOfBirthError'},
            {name: 'gender', errorId: 'genderError'},
            {name: 'number', errorId: 'numberError'},
            {name: 'street', errorId: 'streetError'},
            {name: 'cityCode', errorId: 'cityCodeError'},
            {name: 'cityName', errorId: 'cityNameError'},
            {name: 'countryName', errorId: 'countryError'},
            {name: 'user', errorId: 'userError'}
        ];

        // Validation de chaque champ requis
        fields.forEach(field => {
            const input = document.querySelector(`[name=${field.name}]`);
            const errorSpan = document.getElementById(field.errorId);
            if (input && input.value.trim() === '') {
                isValid = false;
                errorSpan.textContent = 'Ce champ est requis';
            }
        });

        // Vérifie si au moins un contact est présent (téléphone ou email)
        const phoneNumber = document.getElementById('phoneNumber').value.trim();
        const email = document.getElementById('email').value.trim();
        const contactErrorSpan = document.getElementById('contactError');

        // Affiche une erreur si aucun moyen de contact n'est fourni
        if (!phoneNumber && !email) {
            isValid = false;
            contactErrorSpan.style.display = 'block';
            contactErrorSpan.textContent = 'Au moins un moyen de contact (téléphone ou email) est requis';
        } else {
            contactErrorSpan.style.display = 'none';
        }

        // Si des erreurs sont présentes, empêcher l'envoi du formulaire et afficher un message d'alerte
        if (!isValid) {
            event.preventDefault();
            alert('Veuillez corriger les erreurs avant de soumettre.');
        }
    });

});
