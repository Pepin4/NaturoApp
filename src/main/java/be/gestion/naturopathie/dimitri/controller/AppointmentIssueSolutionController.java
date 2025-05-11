package be.gestion.naturopathie.dimitri.controller;

import be.gestion.naturopathie.dimitri.model.AppointmentIssueSolution;
import be.gestion.naturopathie.dimitri.repository.AppointmentIssueSolutionRepository;
import be.gestion.naturopathie.dimitri.repository.AppointmentRepository;
import be.gestion.naturopathie.dimitri.repository.IssueSolutionRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


/**

         █████╗ ██████╗ ██████╗  ██████╗ ██╗███╗   ██╗████████╗███╗   ███╗███████╗███╗   ██╗████████╗██╗███████╗███████╗██╗   ██╗███████╗    
        ██╔══██╗██╔══██╗██╔══██╗██╔═══██╗██║████╗  ██║╚══██╔══╝████╗ ████║██╔════╝████╗  ██║╚══██╔══╝██║██╔════╝██╔════╝██║   ██║██╔════╝    
        ███████║██████╔╝██████╔╝██║   ██║██║██╔██╗ ██║   ██║   ██╔████╔██║█████╗  ██╔██╗ ██║   ██║   ██║███████╗███████╗██║   ██║█████╗█████╗
        ██╔══██║██╔═══╝ ██╔═══╝ ██║   ██║██║██║╚██╗██║   ██║   ██║╚██╔╝██║██╔══╝  ██║╚██╗██║   ██║   ██║╚════██║╚════██║██║   ██║██╔══╝╚════╝
        ██║  ██║██║     ██║     ╚██████╔╝██║██║ ╚████║   ██║   ██║ ╚═╝ ██║███████╗██║ ╚████║   ██║   ██║███████║███████║╚██████╔╝███████╗    
        ╚═╝  ╚═╝╚═╝     ╚═╝      ╚═════╝ ╚═╝╚═╝  ╚═══╝   ╚═╝   ╚═╝     ╚═╝╚══════╝╚═╝  ╚═══╝   ╚═╝   ╚═╝╚══════╝╚══════╝ ╚═════╝ ╚══════╝    
                                                                                                                                             
        ███████╗ ██████╗ ██╗     ██╗   ██╗████████╗██╗ ██████╗ ███╗   ██╗     ██████╗████████╗██████╗ ██╗                                    
        ██╔════╝██╔═══██╗██║     ██║   ██║╚══██╔══╝██║██╔═══██╗████╗  ██║    ██╔════╝╚══██╔══╝██╔══██╗██║                                    
        ███████╗██║   ██║██║     ██║   ██║   ██║   ██║██║   ██║██╔██╗ ██║    ██║        ██║   ██████╔╝██║                                    
        ╚════██║██║   ██║██║     ██║   ██║   ██║   ██║██║   ██║██║╚██╗██║    ██║        ██║   ██╔══██╗██║                                    
        ███████║╚██████╔╝███████╗╚██████╔╝   ██║   ██║╚██████╔╝██║ ╚████║    ╚██████╗   ██║   ██║  ██║███████╗                               
        ╚══════╝ ╚═════╝ ╚══════╝ ╚═════╝    ╚═╝   ╚═╝ ╚═════╝ ╚═╝  ╚═══╝     ╚═════╝   ╚═╝   ╚═╝  ╚═╝╚══════╝                               
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                
**/



/**
 * Contrôleur pour gérer les solutions aux problèmes liés aux rendez-vous.
 * Permet l'affichage, la création, la modification et la suppression des AppointmentIssueSolution.
 */
@Controller
@RequestMapping("/appointment-issue-solution")
public class AppointmentIssueSolutionController {

    @Autowired
    private AppointmentIssueSolutionRepository appointmentIssueSolutionRepository;

    @Autowired
    private AppointmentRepository appointmentRepository;

    @Autowired
    private IssueSolutionRepository issueSolutionRepository;

    /**
     * Affiche la liste de toutes les solutions aux problèmes durant un rendez-vous.
     *
     * @param model le modèle utilisé pour transmettre les données à la vue.
     * @return le nom de la vue qui affiche la liste.
     */
    @GetMapping
    public String list(Model model) {
        model.addAttribute("appointmentIssueSolutions", appointmentIssueSolutionRepository.findAll());
        return "appointment_issue_solution/list";
    }

    /**
     * Affiche les détails d'une solution à un problème durant d'un rendez-vous.
     *
     * @param id    l'identifiant de l'entité à afficher.
     * @param model le modèle pour passer les données à la vue.
     * @return la vue de détail si trouvée, sinon une vue d'erreur.
     */
    @GetMapping("/{id}")
    public String detail(@PathVariable int id, Model model) {
        Optional<AppointmentIssueSolution> optionalEntity = appointmentIssueSolutionRepository.findById(id);
        if (optionalEntity.isEmpty()) {
            model.addAttribute("error", "Introuvable avec l'id : " + id);
            return "error";
        }

        model.addAttribute("appointmentIssueSolution", optionalEntity.get());
        return "appointment_issue_solution/detail";
    }

    /**
     * Affiche le formulaire de création pour une nouvelle association entre un rendez-vous et une solution à un problème.
     *
     * @param model le modèle contenant les listes de rendez-vous et de solutions possibles.
     * @return la vue du formulaire de création.
     */
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("appointmentIssueSolution", new AppointmentIssueSolution());
        model.addAttribute("appointments", appointmentRepository.findAll());
        model.addAttribute("issueSolutions", issueSolutionRepository.findAll());
        return "appointment_issue_solution/new";
    }

    /**
     * Enregistre une nouvelle association entre un rendez-vous et une solution de problème.
     *
     * @param entity l'entité à enregistrer.
     * @return redirection vers la liste des associations après enregistrement.
     */
    @PostMapping
    public String create(@ModelAttribute AppointmentIssueSolution entity) {
        appointmentIssueSolutionRepository.save(entity);
        return "redirect:/appointment-issue-solution";
    }

    /**
     * Supprime une association existante entre un rendez-vous et une solution à un problème.
     *
     * @param id l'identifiant de l'entité à supprimer.
     * @return redirection vers la liste après suppression ou vue d'erreur si l'entité n'existe pas.
     */
    @PostMapping("/delete/{id}")
    public String delete(@PathVariable int id, Model model) {
        Optional<AppointmentIssueSolution> optionalEntity = appointmentIssueSolutionRepository.findById(id);
        if (optionalEntity.isEmpty()) {
            model.addAttribute("error", "Introuvable avec l'id : " + id);
            return "error";
        }

        appointmentIssueSolutionRepository.delete(optionalEntity.get());
        return "redirect:/appointment-issue-solution";
    }
}