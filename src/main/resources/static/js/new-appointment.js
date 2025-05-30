// Attente que le DOM soit complètement chargé avant d'exécuter le script
document.addEventListener("DOMContentLoaded", function () {

    // Sélection des éléments nécessaires pour la recherche et le formulaire
    const patientSearchInput = document.getElementById('patientSearch');
    const patientSelect = document.getElementById('patientSelect');
	const patientErrorSpan = document.getElementById('patientError');
	
	patientSelect.style.display = 'none';

    // Gestion de la soumission du formulaire de rendez-vous
    document.getElementById('appointmentForm').addEventListener('submit', function(event) {
        event.preventDefault();

        let isValid = true;
        const errors = document.querySelectorAll('.error');
        errors.forEach(error => error.textContent = '');

        // Liste des champs à valider
        const fields = [
            {name: 'dateTime', errorId: 'dateTimeError'},
            {name: 'subject', errorId: 'subjectError'},
            {name: 'firstName', errorId: 'firstNameError'},
            {name: 'lastName', errorId: 'lastNameError'},
            {name: 'dateOfBirth', errorId: 'dateOfBirthError'},
            {name: 'gender', errorId: 'genderError'},
            {name: 'number', errorId: 'numberError'},
            {name: 'street', errorId: 'streetError'},
            {name: 'cityCode', errorId: 'cityCodeError'},
            {name: 'cityName', errorId: 'cityNameError'},
            {name: 'country', errorId: 'countryError'},
            {name: 'user', errorId: 'userError'}
        ];

        // Validation de chaque champ requis dans le formulaire
        fields.forEach(field => {
            const input = document.querySelector(`[name=${field.name}]`);
            const errorSpan = document.getElementById(field.errorId);
            if (input && input.value === '') {
                isValid = false;
                errorSpan.textContent = 'Ce champ est requis';
            }
        });

        // Validation des moyens de contact (téléphone ou email)
        const phoneNumber = document.getElementById('phoneNumber').value;
        const email = document.getElementById('email').value;
        const contactErrorSpan = document.getElementById('contactError');

        if (!phoneNumber && !email) {
            isValid = false;
            contactErrorSpan.style.display = 'block';
            contactErrorSpan.textContent = 'Au moins un moyen de contact (téléphone ou email) est requis';
        }

        // Soumission du formulaire si tout est valide
        if (isValid) {
            this.submit();
        }
    });

    // Fonction pour remplir les champs du formulaire avec les informations du patient
    function fillPatientFields(patient) {
        document.querySelector('[name="patientId"]').value = patient.patientId || '';
        document.querySelector('[name="firstName"]').value = patient.firstName || '';
        document.querySelector('[name="lastName"]').value = patient.lastName || '';
        document.querySelector('[name="gender"]').value = patient.gender || '';
        document.querySelector('[name="dateOfBirth"]').value = patient.dateOfBirth || '';
        document.querySelector('[name="familySituation"]').value = patient.familySituation || '';
        document.querySelector('[name="professionalSituation"]').value = patient.professionalSituation || '';
        document.querySelector('[name="phoneNumber"]').value = patient.contact?.phoneNumber || '';
        document.querySelector('[name="email"]').value = patient.contact?.email || '';
        document.querySelector('[name="street"]').value = patient.address?.street || '';
        document.querySelector('[name="number"]').value = patient.address?.number || '';
        document.querySelector('[name="cityCode"]').value = patient.address?.city?.cityCode || '';
        document.querySelector('[name="cityName"]').value = patient.address?.city?.cityName || '';
        document.querySelector('[name="country"]').value = patient.address?.city?.country?.countryId || '';
    }

    // Recherche des patients en fonction de la saisie de l'utilisateur
    patientSearchInput.addEventListener('input', function () {
        const query = this.value.trim();

        // Si la saisie est suffisamment longue, effectuer la recherche
        if (query.length >= 2) {
            fetch(`/appointments/patients/search?query=${query}`)
                .then(response => response.json())
                .then(patients => {
                    patientSelect.innerHTML = '';

                    // Si aucun patient n'est trouvé, afficher un message
                    if (patients.length === 0) {
						patientSelect.style.display = "none";
						patientErrorSpan.textContent = 'Aucun patient ne correspond à votre recherche.';
                    } else if (patients.length === 1) {
						patientSelect.style.display = "block";
                        // Si un seul patient est trouvé, le sélectionner directement et remplir les champs
                        const option = document.createElement('option');
                        option.value = `${patients[0].firstName} ${patients[0].lastName}`;
                        option.textContent = `${patients[0].firstName} ${patients[0].lastName}`;
                        option.dataset.patientId = patients[0].patientId;
                        option.dataset.firstName = patients[0].firstName;
                        option.dataset.lastName = patients[0].lastName;
                        option.dataset.gender = patients[0].gender;
                        option.dataset.dateOfBirth = patients[0].dateOfBirth;
                        option.dataset.familySituation = patients[0].familySituation;
                        option.dataset.professionalSituation = patients[0].professionalSituation;
                        option.dataset.phoneNumber = patients[0].contact?.phoneNumber;
                        option.dataset.email = patients[0].contact?.email;
                        option.dataset.street = patients[0].address?.street;
                        option.dataset.number = patients[0].address?.number;
                        option.dataset.cityCode = patients[0].address?.city?.cityCode;
                        option.dataset.cityName = patients[0].address?.city?.cityName;
                        option.dataset.countryId = patients[0].address?.city?.country?.countryId;
                        patientSelect.appendChild(option);

                        // Remplir directement les champs avec les informations du patient
                        fillPatientFields(patients[0]);
						patientErrorSpan.textContent = '';
                    } else {
						// Afficher la liste des patients trouvés
						patientSelect.style.display = 'block';

                        // Si plusieurs patients sont trouvés, afficher la liste des options
                        patients.forEach(patient => {
                            const option = document.createElement('option');
                            option.value = `${patient.firstName} ${patient.lastName}`;
                            option.textContent = `${patient.firstName} ${patient.lastName}`;
                            option.dataset.patientId = patient.patientId;
                            option.dataset.firstName = patient.firstName;
                            option.dataset.lastName = patient.lastName;
                            option.dataset.gender = patient.gender;
                            option.dataset.dateOfBirth = patient.dateOfBirth;
                            option.dataset.familySituation = patient.familySituation;
                            option.dataset.professionalSituation = patient.professionalSituation;
                            option.dataset.phoneNumber = patient.contact?.phoneNumber;
                            option.dataset.email = patient.contact?.email;
                            option.dataset.street = patient.address?.street;
                            option.dataset.number = patient.address?.number;
                            option.dataset.cityCode = patient.address?.city?.cityCode;
                            option.dataset.cityName = patient.address?.city?.cityName;
                            option.dataset.countryId = patient.address?.city?.country?.countryId;
                            patientSelect.appendChild(option);
							patientErrorSpan.textContent = '';
                        });
                    }
                })
                .catch(err => {
					patientSelect.style.display = "none";
					patientErrorSpan.textContent = "Une erreur est survenue lors de la recherche.";
                });
        } else {
            // Si la recherche est trop courte, masquer la liste déroulante
            patientSelect.style.display = 'none';
        }
    });

    // Remplir les champs si l'utilisateur change la sélection dans le champ select
    patientSelect.addEventListener('change', function () {
        const selectedOption = patientSelect.selectedOptions[0];

        if (selectedOption) {
            const patientId = selectedOption.dataset.patientId;

            // Assigner l'ID du patient au champ caché
            document.getElementById('patientId').value = patientId;

            const patient = {
                patientId: selectedOption.dataset.patientId,
                firstName: selectedOption.dataset.firstName,
                lastName: selectedOption.dataset.lastName,
                gender: selectedOption.dataset.gender,
                dateOfBirth: selectedOption.dataset.dateOfBirth,
                familySituation: selectedOption.dataset.familySituation,
                professionalSituation: selectedOption.dataset.professionalSituation,
                contact: {
                    phoneNumber: selectedOption.dataset.phoneNumber,
                    email: selectedOption.dataset.email,
                },
                address: {
                    street: selectedOption.dataset.street,
                    number: selectedOption.dataset.number,
                    city: {
                        cityCode: selectedOption.dataset.cityCode,
                        cityName: selectedOption.dataset.cityName,
                        country: {
                            countryId: selectedOption.dataset.countryId
                        }
                    }
                }
            };

            // Remplir les champs du formulaire avec les informations du patient sélectionné
            fillPatientFields(patient);
        }
    });
});
