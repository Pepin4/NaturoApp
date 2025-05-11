package be.gestion.naturopathie.dimitri.controller;

import be.gestion.naturopathie.dimitri.model.MedicalInfo;
import be.gestion.naturopathie.dimitri.repository.MedicalInfoRepository;
import be.gestion.naturopathie.dimitri.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


/**

        ███╗   ███╗███████╗██████╗ ██╗ ██████╗ █████╗ ██╗     ██╗███╗   ██╗███████╗ ██████╗      ██████╗████████╗██████╗ ██╗     
        ████╗ ████║██╔════╝██╔══██╗██║██╔════╝██╔══██╗██║     ██║████╗  ██║██╔════╝██╔═══██╗    ██╔════╝╚══██╔══╝██╔══██╗██║     
        ██╔████╔██║█████╗  ██║  ██║██║██║     ███████║██║     ██║██╔██╗ ██║█████╗  ██║   ██║    ██║        ██║   ██████╔╝██║     
        ██║╚██╔╝██║██╔══╝  ██║  ██║██║██║     ██╔══██║██║     ██║██║╚██╗██║██╔══╝  ██║   ██║    ██║        ██║   ██╔══██╗██║     
        ██║ ╚═╝ ██║███████╗██████╔╝██║╚██████╗██║  ██║███████╗██║██║ ╚████║██║     ╚██████╔╝    ╚██████╗   ██║   ██║  ██║███████╗
        ╚═╝     ╚═╝╚══════╝╚═════╝ ╚═╝ ╚═════╝╚═╝  ╚═╝╚══════╝╚═╝╚═╝  ╚═══╝╚═╝      ╚═════╝      ╚═════╝   ╚═╝   ╚═╝  ╚═╝╚══════╝
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               
**/



/**
 * Contrôleur pour gérer les informations médicales sur les patients.
 * Permet l'affichage, la création, la modification et la suppression des informations médicales.
 */
@Controller
@RequestMapping("/medical-info")
public class MedicalInfoController {

    @Autowired
    private MedicalInfoRepository medicalInfoRepository;
    
    @Autowired
    private PatientRepository patientRepository;

    /**
     * Affiche la liste des informations médicales.
     *
     * @param model Le modèle Spring utilisé pour passer les données à la vue.
     * @return La vue affichant la liste des informations médicales.
     */
    @GetMapping
    public String listMedicalInfos(Model model) {
        List<MedicalInfo> medicalInfos = medicalInfoRepository.findAll();
        model.addAttribute("medicalInfos", medicalInfos);
        return "medical-info/list"; // templates/medical-info/list.html
    }

    /**
     * Affiche les détails d'une information médicale.
     *
     * @param id    L'identifiant de l'information médicale à afficher.
     * @param model Le modèle Spring utilisé pour passer les données à la vue.
     * @return La vue affichant les détails de l'information médicale.
     */
    @GetMapping("/{id}")
    public String showMedicalInfo(@PathVariable int id, Model model) {
        Optional<MedicalInfo> optionalMedicalInfo = medicalInfoRepository.findById(id);

        // Gestion du cas où l'information médicale n'existe pas
        if (optionalMedicalInfo.isEmpty()) {
            model.addAttribute("error", "Info médicale introuvable avec l'id : " + id);
            return "error"; // Rediriger vers une page d'erreur ou afficher un message d'erreur
        }

        MedicalInfo mi = optionalMedicalInfo.get();
        model.addAttribute("medicalInfo", mi);
        return "medical-info/detail"; // templates/medical-info/detail.html
    }

    /**
     * Affiche le formulaire pour créer une nouvelle information médicale.
     *
     * @param model Le modèle Spring utilisé pour passer les données à la vue.
     * @return La vue contenant le formulaire de création.
     */
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("medicalInfo", new MedicalInfo());
        model.addAttribute("patients", patientRepository.findAll()); // pour sélectionner un patient
        return "medical-info/new"; // templates/medical-info/new.html
    }

    /**
     * Crée une nouvelle information médicale.
     *
     * @param medicalInfo Les détails de l'information médicale à créer.
     * @return La redirection vers la liste des informations médicales après la création.
     */
    @PostMapping
    public String createMedicalInfo(@ModelAttribute MedicalInfo medicalInfo) {
        medicalInfoRepository.save(medicalInfo);
        return "redirect:/medical-info";
    }

    /**
     * Affiche le formulaire pour éditer une information médicale existante.
     *
     * @param id    L'identifiant de l'information médicale à éditer.
     * @param model Le modèle Spring utilisé pour passer les données à la vue.
     * @return La vue contenant le formulaire d'édition.
     */
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        Optional<MedicalInfo> optionalMedicalInfo = medicalInfoRepository.findById(id);

        // Gestion du cas où l'information médicale n'existe pas
        if (optionalMedicalInfo.isEmpty()) {
            model.addAttribute("error", "Info médicale introuvable avec l'id : " + id);
            return "error"; // Rediriger vers une page d'erreur ou afficher un message d'erreur
        }

        MedicalInfo mi = optionalMedicalInfo.get();
        model.addAttribute("medicalInfo", mi);
        model.addAttribute("patients", patientRepository.findAll());
        return "medical-info/edit"; // templates/medical-info/edit.html
    }

    /**
     * Met à jour une information médicale existante.
     *
     * @param id      L'identifiant de l'information médicale à modifier.
     * @param details Les nouvelles informations de l'information médicale.
     * @return La redirection vers la liste des informations médicales après la mise à jour.
     */
    @PostMapping("/edit/{id}")
    public String updateMedicalInfo(@PathVariable int id, @ModelAttribute MedicalInfo details) {
        Optional<MedicalInfo> optionalMedicalInfo = medicalInfoRepository.findById(id);

        // Gestion du cas où l'information médicale n'existe pas
        if (optionalMedicalInfo.isEmpty()) {
            return "redirect:/error?message=Info%20médicale%20introuvable%20avec%20l'id%20" + id;
        }

        MedicalInfo mi = optionalMedicalInfo.get();
        mi.setType(details.getType());
        mi.setDescription(details.getDescription());
        mi.setTreatment(details.getTreatment());
        mi.setDateAdded(details.getDateAdded());
        mi.setPatient(details.getPatient());
        medicalInfoRepository.save(mi);

        return "redirect:/medical-info";
    }

    /**
     * Supprime une information médicale.
     *
     * @param id L'identifiant de l'information médicale à supprimer.
     * @return La redirection vers la liste des informations médicales après la suppression.
     */
    @PostMapping("/delete/{id}")
    public String deleteMedicalInfo(@PathVariable int id) {
        Optional<MedicalInfo> optionalMedicalInfo = medicalInfoRepository.findById(id);

        // Gestion du cas où l'information médicale n'existe pas
        if (optionalMedicalInfo.isEmpty()) {
            return "redirect:/error?message=Info%20médicale%20introuvable%20avec%20l'id%20" + id;
        }

        MedicalInfo mi = optionalMedicalInfo.get();
        medicalInfoRepository.delete(mi);

        return "redirect:/medical-info";
    }
}