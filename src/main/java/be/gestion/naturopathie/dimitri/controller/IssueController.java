package be.gestion.naturopathie.dimitri.controller;

import be.gestion.naturopathie.dimitri.model.Issue;
import be.gestion.naturopathie.dimitri.model.Solution;
import be.gestion.naturopathie.dimitri.repository.IssueRepository;
import be.gestion.naturopathie.dimitri.repository.SolutionRepository;
import be.gestion.naturopathie.dimitri.repository.SupplierRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


/**
		
		██╗███████╗███████╗██╗   ██╗███████╗     ██████╗████████╗██████╗ ██╗     
		██║██╔════╝██╔════╝██║   ██║██╔════╝    ██╔════╝╚══██╔══╝██╔══██╗██║     
		██║███████╗███████╗██║   ██║█████╗      ██║        ██║   ██████╔╝██║     
		██║╚════██║╚════██║██║   ██║██╔══╝      ██║        ██║   ██╔══██╗██║     
		██║███████║███████║╚██████╔╝███████╗    ╚██████╗   ██║   ██║  ██║███████╗
		╚═╝╚══════╝╚══════╝ ╚═════╝ ╚══════╝     ╚═════╝   ╚═╝   ╚═╝  ╚═╝╚══════╝
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    
**/



/**
 * Contrôleur pour gérer les problèmes.
 * Permet l'affichage, la création, la modification et la suppression des problèmes.
 */
@Controller
@RequestMapping("/issues")
public class IssueController {

    @Autowired
    private IssueRepository issueRepository;

    @Autowired
    private SolutionRepository solutionRepository;

    @Autowired
    private SupplierRepository supplierRepository;

    /**
     * Affiche la page listant les problèmes.
     *
     * @param model Le modèle Spring pour passer les données à la vue.
     * @return La vue Thymeleaf affichant les problèmes et les solutions.
     */
    @GetMapping
    public String getIssuesPage(Model model) {
        model.addAttribute("issues", issueRepository.findAll());
        model.addAttribute("solutions", solutionRepository.findAll());
        model.addAttribute("suppliers", supplierRepository.findAll());
        model.addAttribute("solution", new Solution());
        return "issues";
    }

    /**
     * Crée un nouveau problème.
     *
     * @param issue Le problème à enregistrer.
     * @return Redirige vers la page des problèmes après création.
     */
    @PostMapping
    public String createIssue(@ModelAttribute Issue issue) {
        issueRepository.save(issue);
        return "redirect:/issues";
    }

    /**
     * Met à jour un problème existant.
     *
     * @param id            L'identifiant du problème à mettre à jour.
     * @param issueDetails  Les nouvelles données à appliquer.
     * @param model         Le modèle pour afficher un message d'erreur si nécessaire.
     * @return Redirige vers la page des problèmes, ou une page d'erreur si non trouvé.
     */
    @PostMapping("/edit/{id}")
    public String updateIssue(@PathVariable int id, @ModelAttribute Issue issueDetails, Model model) {
        Optional<Issue> optionalIssue = issueRepository.findById(id);

        if (optionalIssue.isEmpty()) {
            model.addAttribute("error", "Problème introuvable avec l'id " + id);
            return "error";
        }

        Issue issue = optionalIssue.get();
        issue.setName(issueDetails.getName());
        issue.setDescription(issueDetails.getDescription());
        issue.setConstraint(issueDetails.getConstraint());
        issue.setOrigin(issueDetails.getOrigin());

        issueRepository.save(issue);
        return "redirect:/issues";
    }

    /**
     * Supprime un problème existant.
     *
     * @param id    L'identifiant du problème à supprimer.
     * @param model Le modèle pour afficher un message d'erreur si nécessaire.
     * @return Redirige vers la page des problèmes, ou une page d'erreur si non trouvé.
     */
    @PostMapping("/delete/{id}")
    public String deleteIssue(@PathVariable int id, Model model) {
        Optional<Issue> optionalIssue = issueRepository.findById(id);

        if (optionalIssue.isEmpty()) {
            model.addAttribute("error", "Problème introuvable avec l'id " + id);
            return "error";
        }

        issueRepository.delete(optionalIssue.get());
        return "redirect:/issues";
    }
}