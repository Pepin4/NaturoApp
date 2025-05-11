// Fonction de validation du formulaire
function validateForm(event) {
    let isValid = true;
    let errorMessage = document.getElementById("error-message");

    // Vérification des champs requis
    let fields = document.querySelectorAll('.required-field');
    fields.forEach(field => {
        // Si le champ est vide, il est marqué comme invalide
        if (field.value.trim() === "") {
            field.classList.add("invalid");
            isValid = false;
        } else {
            field.classList.remove("invalid");
        }
    });

    // Affichage ou masquage du message d'erreur en fonction de la validité du formulaire
    if (!isValid) {
        errorMessage.style.display = "block";
        event.preventDefault();
    } else {
        errorMessage.style.display = "none";
    }

    return isValid;
}

// Appliquer la validation lors de la soumission du formulaire
let form = document.querySelector('form');
form.addEventListener('submit', validateForm);
