package be.gestion.naturopathie.dimitri.controller;

import be.gestion.naturopathie.dimitri.model.Appointment;
import be.gestion.naturopathie.dimitri.model.AppointmentIssueSolution;
import be.gestion.naturopathie.dimitri.model.Issue;
import be.gestion.naturopathie.dimitri.model.IssueSolution;
import be.gestion.naturopathie.dimitri.repository.AppointmentIssueSolutionRepository;
import be.gestion.naturopathie.dimitri.repository.AppointmentRepository;
import be.gestion.naturopathie.dimitri.repository.IssueRepository;
import be.gestion.naturopathie.dimitri.repository.IssueSolutionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/**

        ██╗███████╗███████╗██╗   ██╗███████╗███████╗ ██████╗ ██╗     ██╗   ██╗████████╗██╗ ██████╗ ███╗   ██╗     ██████╗████████╗██████╗ ██╗     
        ██║██╔════╝██╔════╝██║   ██║██╔════╝██╔════╝██╔═══██╗██║     ██║   ██║╚══██╔══╝██║██╔═══██╗████╗  ██║    ██╔════╝╚══██╔══╝██╔══██╗██║     
        ██║███████╗███████╗██║   ██║█████╗  ███████╗██║   ██║██║     ██║   ██║   ██║   ██║██║   ██║██╔██╗ ██║    ██║        ██║   ██████╔╝██║     
        ██║╚════██║╚════██║██║   ██║██╔══╝  ╚════██║██║   ██║██║     ██║   ██║   ██║   ██║██║   ██║██║╚██╗██║    ██║        ██║   ██╔══██╗██║     
        ██║███████║███████║╚██████╔╝███████╗███████║╚██████╔╝███████╗╚██████╔╝   ██║   ██║╚██████╔╝██║ ╚████║    ╚██████╗   ██║   ██║  ██║███████╗
        ╚═╝╚══════╝╚══════╝ ╚═════╝ ╚══════╝╚══════╝ ╚═════╝ ╚══════╝ ╚═════╝    ╚═╝   ╚═╝ ╚═════╝ ╚═╝  ╚═══╝     ╚═════╝   ╚═╝   ╚═╝  ╚═╝╚══════╝
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                            
**/



/**
 * Contrôleur pour gérer les solutions des problèmes.
 * Permet l'affichage, la création, la modification et la suppression des solutions de problèmes.
 */
@Controller
@RequestMapping("/issue-solutions")
public class IssueSolutionController {

    @Autowired
    private IssueSolutionRepository issueSolutionRepository;

    @Autowired
    private IssueRepository issueRepository;

    @Autowired
    private AppointmentIssueSolutionRepository appointmentIssueSolutionRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    /**
     * Affiche toutes les solutions pour un problème donné.
     * 
     * @param issueId L'ID du problème
     * @param model le modèle Thymeleaf
     * @return la vue contenant les solutions de problème.
     */
    @GetMapping
    public String getAllIssueSolutions(
        @RequestParam(value = "issueId", defaultValue = "0") int issueId,
        @RequestParam(value = "appointmentId", required = false) Integer appointmentId,
        Model model
    ) {
        if (issueId != 0) {
            Optional<Issue> issueOptional = issueRepository.findById(issueId);
            if (issueOptional.isPresent()) {
                Issue issue = issueOptional.get();
                model.addAttribute("issue", issue); 

                List<IssueSolution> issueSolutions = issueSolutionRepository.findByIssueId(issueId);
                model.addAttribute("issueSolutions", issueSolutions);
            } else {
                model.addAttribute("error", "Problème introuvable avec l'id " + issueId);
                return "error";
            }
        }

        if (appointmentId != null) {
            model.addAttribute("appointmentId", appointmentId);
        }

        return "issue-solutions";
    }

    /**
     * Affiche une solution par son identifiant.
     * 
     * @param id l'identifiant de la solution.
     * @param model le modèle Thymeleaf.
     * @return la vue pour afficher les détails d'une solution de problème.
     */
    @GetMapping("/{id}")
    public String getIssueSolutionById(@PathVariable int id, Model model) {
        Optional<IssueSolution> issueSolutionOptional = issueSolutionRepository.findById(id);

        if (issueSolutionOptional.isEmpty()) {
            model.addAttribute("error", "Solution introuvable avec l'id " + id);
            return "error";
        }

        IssueSolution issueSolution = issueSolutionOptional.get();
        model.addAttribute("issueSolution", issueSolution);
        return "issue-solutions";
    }

    @PostMapping
    public String saveIssueSolutions(
    	@RequestParam(value = "issueId", defaultValue = "0") int issueId,
        @RequestParam("name") String issueName,
        @RequestParam("description") String issueDescription,
        @RequestParam("origin") String issueOrigin,
        @RequestParam("constraint") String issueConstraint,
        @RequestParam(value = "solutions", required = false) List<String> selectedSolutions,
        @RequestParam(value = "appointmentId", required = false, defaultValue = "0") int appointmentId,
        Model model
    ) {
        try {
            Issue issue;    
            Optional<Issue> issueOptional = issueRepository.findById(issueId);

            if (issueId == 0 || issueOptional.isEmpty()) {
                issue = new Issue();
            } else {
                issue = issueOptional.get();
            }

            // Mise à jour des champs
            issue.setName(issueName);
            issue.setDescription(issueDescription);
            issue.setOrigin(issueOrigin);
            issue.setConstraint(issueConstraint);

            // Sauvegarde ou mise à jour du problème
            issue = issueRepository.save(issue);

            // Si un appointmentId est fourni, récupérer le rendez-vous dans la DB
            if (appointmentId != 0) {
                Optional<Appointment> appointmentOptional = appointmentRepository.findById(appointmentId);
                if (appointmentOptional.isEmpty()) {
                    model.addAttribute("error", "Rendez-vous introuvable avec l'id " + appointmentId);
                    return "error";
                }

                Appointment appointment = appointmentOptional.get();

                // Gestion des solutions sélectionnées
                if (selectedSolutions != null && !selectedSolutions.isEmpty()) {
                    List<IssueSolution> issueSolutions = new ArrayList<>();

                    // Convertir les solutions sélectionnées de String à int et récupérer les associations IssueSolution
                    for (String solutionIdStr : selectedSolutions) {
                        int solutionId = Integer.parseInt(solutionIdStr);

                        List<IssueSolution> found = issueSolutionRepository.findByIssueAndSolution(issue.getIssueId(), solutionId);

                        if (found.isEmpty()) {
                            model.addAttribute("error", "Aucune association entre ce problème et la solution " + solutionId);
                            return "error";
                        }

                        issueSolutions.addAll(found);
                    }

                    // Associer les IssueSolution au rendez-vous via la table AppointmentIssueSolution
                    for (IssueSolution issueSolution : issueSolutions) {
                        AppointmentIssueSolution appointmentIssueSolution = new AppointmentIssueSolution();
                        appointmentIssueSolution.setAppointment(appointment);
                        appointmentIssueSolution.setIssueSolution(issueSolution);
                        appointmentIssueSolutionRepository.save(appointmentIssueSolution);
                    }
                }
            }

            // Redirige vers la page d'édition du rendez-vous
            return "redirect:/appointments/edit/" + appointmentId;

        } catch (Exception e) {
            // Gestion de l'erreur : message et conservation des données
            model.addAttribute("issueError", "Échec de la sauvegarde : " + e.getMessage());

            // Remettre dans le modèle les données du formulaire pour réaffichage
            model.addAttribute("issueId", issueId);
            model.addAttribute("name", issueName);
            model.addAttribute("description", issueDescription);
            model.addAttribute("origin", issueOrigin);
            model.addAttribute("constraint", issueConstraint);
            model.addAttribute("selectedSolutions", selectedSolutions);
            model.addAttribute("appointmentId", appointmentId);

            // Retourner la vue du formulaire d'édition (à adapter selon ton nom de vue)
            return "issue-solutions";  
            // <-- Remplace "issueForm" par la vue qui affiche le formulaire d'édition du problème
        }
    }

    /**
     * Supprime une solution par son identifiant.
     * 
     * @param id l'identifiant de la solution à supprimer.
     * @return une redirection vers la liste des solutions après suppression.
     */
    @PostMapping("/delete/{id}")
    public String deleteIssueSolution(@PathVariable int id) {
        Optional<IssueSolution> issueSolutionOptional = issueSolutionRepository.findById(id);

        if (issueSolutionOptional.isEmpty()) {
            return "redirect:/error?message=Solution%20introuvable%20avec%20l'id%20" + id;
        }

        IssueSolution issueSolution = issueSolutionOptional.get();
        issueSolutionRepository.delete(issueSolution);
        return "redirect:/issue-solutions?issueId=" + issueSolution.getIssue().getIssueId();
    }

    /**
     * Recherche des problèmes en fonction d'une chaîne de caractères.
     *
     * @param query la chaîne de recherche.
     * @param model le modèle Thymeleaf.
     * @return la liste des problèmes correspondant à la recherche.
     */
    @GetMapping("/search-issues")
    @ResponseBody
    public List<Issue> searchIssues(@RequestParam("query") String query, Model model) {
        if (query != null && !query.trim().isEmpty()) {
            List<Issue> issues = issueRepository.findByNameContainingIgnoreCase(query);
            return issues;
        }
        return new ArrayList<>();
    }

    /**
     * Récupère toutes les solutions pour un problème donné
     * @param issueId L'ID du problème
     * @return la liste des solutions en format JSON
     */
    @GetMapping("/for-issue/{issueId}")
    @ResponseBody
    public List<IssueSolution> getSolutionsForIssue(@PathVariable int issueId) {
        List<IssueSolution> solutions = issueSolutionRepository.findByIssueId(issueId);
        return solutions;
    }
}