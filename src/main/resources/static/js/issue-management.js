// Attente que le DOM soit complètement chargé avant d'exécuter le script
document.addEventListener("DOMContentLoaded", function () {

    // Sélection des éléments HTML à manipuler pour la recherche et l'affichage des solutions
    const issueSearchInput = document.getElementById('issueSearch');
    const issueSelect = document.getElementById('issueSelect');
    const issueIdInput = document.getElementById('issueId');
    const solutionsTableBody = document.querySelector("#solutionsTable tbody");

    // Fonction pour remplir les champs qui concerne un problème dans le formulaire
    function fillIssueFields(issue) {
        document.querySelector('[name="issueId"]').value = issue.issueId || '';
        document.querySelector('[name="name"]').value = issue.name || '';
        document.querySelector('[name="description"]').value = issue.description || '';
        document.querySelector('[name="origin"]').value = issue.origin || '';
        document.querySelector('[name="constraint"]').value = issue.constraint || '';
    }

    // Fonction pour mettre à jour le tableau des solutions en fonction des solutions trouvées
    function updateSolutionsTable(solutions) {
        solutionsTableBody.innerHTML = '';

        // Si aucune solution n'est trouvée, affiche un message
        if (!Array.isArray(solutions) || solutions.length === 0) {
            const row = document.createElement('tr');
            const cell = document.createElement('td');
            cell.colSpan = 3;
            cell.textContent = "Aucune solution disponible pour ce problème.";
            row.appendChild(cell);
            solutionsTableBody.appendChild(row);
        } else {
            // Pour chaque solution, crée une ligne dans le tableau
            solutions.forEach(solution => {
                const row = document.createElement('tr');

                const checkboxCell = document.createElement('td');
                const checkbox = document.createElement('input');
                checkbox.type = 'checkbox';
                checkbox.name = 'solutions';
                checkbox.value = solution.solution.solutionId;
                checkboxCell.appendChild(checkbox);
                row.appendChild(checkboxCell);

                const remedyCell = document.createElement('td');
                remedyCell.textContent = solution.solution ? solution.solution.remedy : "Non spécifié";
                row.appendChild(remedyCell);

                const benefitsCell = document.createElement('td');
                benefitsCell.textContent = solution.solution ? solution.solution.benefits : "Non spécifié";
                row.appendChild(benefitsCell);

                solutionsTableBody.appendChild(row);
            });
        }
    }

    // Fonction pour charger les solutions d'un problème spécifique en fonction de son ID
    function loadSolutionsForIssue(issueId) {
        if (!issueId) return;

        fetch(`/issue-solutions/for-issue/${issueId}`)
            .then(response => {
                if (!response.ok) {
                    throw new Error("Erreur HTTP : " + response.status);
                }
                return response.json();
            })
            .then(data => {
                if (!Array.isArray(data)) {
                    console.error("Réponse inattendue :", data);
                    return;
                }
                updateSolutionsTable(data);
            })
            .catch(error => {
                console.error('Erreur lors du chargement des solutions:', error);
                alert("Une erreur est survenue lors de l'affichage des solutions.");
            });
    }

    // Initialisation de la valeur de l'ID du problème si elle est vide
    if (!issueIdInput.value) {
        issueIdInput.value = '0'; 
    }

    // Écouteur d'événement pour la saisie dans le champ de recherche des problèmes
    issueSearchInput.addEventListener('input', function () {
        const query = this.value.trim();

        // Vérifie si la chaîne est assez longue pour effectuer une recherche
        if (query.length >= 2) {
            fetch(`/issue-solutions/search-issues?query=${query}`)
                .then(response => response.json())
                .then(issues => {
                    issueSelect.innerHTML = '';

                    // Si aucun problème n'est trouvé, affiche une option indiquant qu'il n'y a aucun problème
                    if (issues.length === 0) {
                        const noIssueOption = document.createElement('option');
                        noIssueOption.textContent = 'Aucun problème trouvé';
                        issueSelect.appendChild(noIssueOption);
                        issueSelect.style.display = 'block';
                    } else {
                        // Affiche tous les problèmes trouvés dans la liste déroulante
                        issues.forEach(issue => {
                            const option = document.createElement('option');
                            option.value = issue.issueId;
                            option.textContent = issue.name;
                            option.dataset.issueId = issue.issueId;
                            option.dataset.name = issue.name;
                            option.dataset.description = issue.description;
                            option.dataset.origin = issue.origin;
                            option.dataset.constraint = issue.constraint;
                            issueSelect.appendChild(option);
                        });
                        issueSelect.style.display = 'block';

                        // Si un seul problème est trouvé, remplit les champs et charge les solutions pour ce problème
                        if (issues.length === 1) {
                            const issue = issues[0];
                            fillIssueFields(issue);
                            loadSolutionsForIssue(issue.issueId);
                        }
                    }
                })
                .catch(err => {
                    console.error('Erreur lors de la recherche des problèmes', err);
                    alert("Une erreur est survenue lors de la recherche des problèmes.");
                });
        } else {
            issueSelect.style.display = 'none';
        }
    });

    // Écouteur d'événement pour la sélection d'un problème dans la liste déroulante
    issueSelect.addEventListener('change', function () {
        const selectedOption = issueSelect.selectedOptions[0];

        if (selectedOption) {
            const issue = {
                issueId: selectedOption.dataset.issueId,
                name: selectedOption.dataset.name,
                description: selectedOption.dataset.description,
                origin: selectedOption.dataset.origin,
                constraint: selectedOption.dataset.constraint,
            };

            fillIssueFields(issue);
            loadSolutionsForIssue(issue.issueId);
        }
    });

    // Fonction pour ajouter une nouvelle solution à un problème
    window.addNewSolution = function () {
        const issueId = document.querySelector('[name="issueId"]').value;
        const appointmentId = document.querySelector('[name="appointmentId"]').value;
		const issueError = document.getElementById('issueError');

		issueError.textContent = '';
        // Vérifie si un problème est sélectionné avant d'ajouter une solution
        if (!issueId || issueId === '0') {
			issueError.textContent = 'Veuillez sélectionner un problème avant d\'ajouter une solution.';
            return;
        }

        // Redirige vers la page de création de solution avec les paramètres appropriés
        if (issueId && appointmentId) {
            window.location.href = `/solutions/new?issueId=${issueId}&appointmentId=${appointmentId}`;
        } else if (issueId) {
            window.location.href = `/solutions/new?issueId=${issueId}`;
        }
    };

    // Validation du formulaire avant soumission
    document.getElementById("issueForm").addEventListener("submit", function(event) {
        const issueId = document.querySelector('[name="issueId"]').value;
        const selectedSolutions = document.querySelectorAll('input[name="solutions"]:checked');

    });

    // Initialisation de la table des solutions avec DataTables pour la pagination et la recherche
    $('#solutionsTable').DataTable({
        paging: true,
        searching: true,
        ordering: false,
        columnDefs: [
            { width: "5%", targets: 0 },   
            { width: "45%", targets: 1 },  
            { width: "50%", targets: 2 }   
        ]
    });
});
