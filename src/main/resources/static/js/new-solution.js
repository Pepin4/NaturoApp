// Attente que le DOM soit complètement chargé avant d'exécuter le script
document.addEventListener("DOMContentLoaded", function () {

    // Sélection des éléments HTML à manipuler pour la recherche et le formulaire
    const searchInput = document.getElementById("supplierNameSearch");
    const selectList = document.getElementById("supplierNameSelect");
    const nameField = document.getElementById("supplierName");
    const phoneField = document.getElementById("contactNumber");
    const emailField = document.getElementById("contactEmail");
    const addressNumberField = document.getElementById("addressNumber");
    const addressStreetField = document.getElementById("addressStreet");
    const cityCodeField = document.getElementById("cityCode");
    const cityNameField = document.getElementById("cityName");
    const countryField = document.getElementById("country");
	const supplierErrorSpan = document.getElementById('supplierError');
	
	selectList.style.display = "none";

    // Variable pour gérer le "debounce" de la recherche (délai entre les entrées utilisateur)
    let debounceTimer;

    // Écouteur d'événement pour la saisie dans le champ de recherche des fournisseurs
    searchInput.addEventListener("input", function () {
        const query = this.value.trim();
        clearTimeout(debounceTimer);

        // Vérifie si la chaîne est assez longue pour effectuer une recherche
        if (query.length >= 2) {
            debounceTimer = setTimeout(() => {
                // Effectue la requête pour récupérer les fournisseurs correspondant à la recherche
                fetch(`/solutions/search-suppliers?query=${encodeURIComponent(query)}`)
                    .then(response => response.json())
                    .then(suppliers => {
                        selectList.innerHTML = '';

                        // Si des fournisseurs sont trouvés, affiche la liste
                        if (suppliers.length > 0) {
							supplierErrorSpan.textContent = '';
                            selectList.style.display = "block";
                            suppliers.forEach(supplier => {
                                // Crée une option dans la liste pour chaque fournisseur
                                const option = document.createElement("option");
                                option.value = supplier.supplierId;
                                option.textContent = supplier.supplierName;

                                // Ajoute des données supplémentaires à chaque option pour préremplir le formulaire
                                option.dataset.contactNumber = supplier.contact?.phoneNumber || '';
                                option.dataset.contactEmail = supplier.contact?.email || '';
                                option.dataset.addressNumber = supplier.address?.number;
                                option.dataset.addressStreet = supplier.address?.street;
                                option.dataset.cityCode = supplier.address?.city?.cityCode;
                                option.dataset.cityName = supplier.address?.city?.cityName;
                                option.dataset.countryId = supplier.address?.city?.country?.countryId;

                                selectList.appendChild(option);
                            });

                            // Si un seul fournisseur est trouvé, remplir directement le formulaire
                            if (suppliers.length === 1) {
								selectList.style.display = "block";
                                const supplier = suppliers[0];
                                nameField.value = supplier.supplierName;
                                phoneField.value = supplier.contact?.phoneNumber || '';
                                emailField.value = supplier.contact?.email || '';
                                addressNumberField.value = supplier.address?.number;
                                addressStreetField.value = supplier.address?.street;
                                cityCodeField.value = supplier.address?.city?.cityCode;
                                cityNameField.value = supplier.address?.city?.cityName;
                                countryField.value = supplier.address?.city?.country?.countryId;
                            }
                        } else {
							supplierErrorSpan.textContent = 'Aucun patient ne correspond à votre recherche.';
                            selectList.style.display = "none";
							
                        }
                    })
                    .catch(error => {
                        selectList.style.display = "none";
                    });
            }, 300); 
        } else {
            selectList.style.display = "none";
			supplierErrorSpan.textContent = '';
        }
    });

    // Écouteur d'événement pour la sélection d'un fournisseur dans la liste déroulante
    selectList.addEventListener("change", function () {
        const selectedOption = this.selectedOptions[0];
        if (selectedOption && selectedOption.value !== "") {
            nameField.value = selectedOption.textContent;
            phoneField.value = selectedOption.dataset.contactNumber || '';
            emailField.value = selectedOption.dataset.contactEmail || '';
            addressNumberField.value = selectedOption.dataset.addressNumber;
            addressStreetField.value = selectedOption.dataset.addressStreet;
            cityCodeField.value = selectedOption.dataset.cityCode;
            cityNameField.value = selectedOption.dataset.cityName;
            countryField.value = selectedOption.dataset.countryId;
        }
    });

    // Validation du formulaire avant soumission
    const form = document.getElementById("solutionForm");

    form.addEventListener('submit', function(event) {
        event.preventDefault();

        let isValid = true;
        const errors = document.querySelectorAll('.error');
        errors.forEach(error => error.textContent = '');

        // Liste des champs à valider avec leurs messages d'erreur associés
        const fields = [
            {name: 'solutionRemedy', errorId: 'solutionRemedyError'},
            {name: 'solutionBenefit', errorId: 'solutionBenefitError'},
            {name: 'supplierName', errorId: 'supplierNameError'},
            {name: 'addressNumber', errorId: 'addressNumberError'},
            {name: 'addressStreet', errorId: 'addressStreetError'},
            {name: 'cityCode', errorId: 'cityCodeError'},
            {name: 'cityName', errorId: 'cityNameError'},
            {name: 'countryId', errorId: 'countryError'}
        ];

        // Validation de chaque champ requis
        fields.forEach(field => {
            const input = document.querySelector(`[name=${field.name}]`);
            const errorSpan = document.getElementById(field.errorId);
            if (!input.value || (input.tagName === 'SELECT' && input.value === '')) {
                isValid = false;
                errorSpan.textContent = 'Ce champ est requis';
            }
        });

        // Validation des moyens de contact (téléphone ou email)
        const phoneNumber = document.getElementById('contactNumber').value;
        const email = document.getElementById('contactEmail').value;
        const contactErrorSpan = document.getElementById('contactError');

        if (!phoneNumber && !email) {
            isValid = false;
            contactErrorSpan.textContent = 'Au moins un moyen de contact (téléphone ou email) est requis';
        }

        // Soumission du formulaire si toutes les validations sont réussies
        if (isValid) {
            this.submit();
        }
    });
});
