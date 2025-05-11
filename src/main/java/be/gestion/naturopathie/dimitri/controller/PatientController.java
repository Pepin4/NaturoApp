package be.gestion.naturopathie.dimitri.controller;

import be.gestion.naturopathie.dimitri.model.Address;
import be.gestion.naturopathie.dimitri.model.City;
import be.gestion.naturopathie.dimitri.model.Country;
import be.gestion.naturopathie.dimitri.model.Patient;
import be.gestion.naturopathie.dimitri.repository.CountryRepository;
import be.gestion.naturopathie.dimitri.repository.PatientRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


/**

        ██████╗  █████╗ ████████╗██╗███████╗███╗   ██╗████████╗     ██████╗████████╗██████╗ ██╗     
        ██╔══██╗██╔══██╗╚══██╔══╝██║██╔════╝████╗  ██║╚══██╔══╝    ██╔════╝╚══██╔══╝██╔══██╗██║     
        ██████╔╝███████║   ██║   ██║█████╗  ██╔██╗ ██║   ██║       ██║        ██║   ██████╔╝██║     
        ██╔═══╝ ██╔══██║   ██║   ██║██╔══╝  ██║╚██╗██║   ██║       ██║        ██║   ██╔══██╗██║     
        ██║     ██║  ██║   ██║   ██║███████╗██║ ╚████║   ██║       ╚██████╗   ██║   ██║  ██║███████╗
        ╚═╝     ╚═╝  ╚═╝   ╚═╝   ╚═╝╚══════╝╚═╝  ╚═══╝   ╚═╝        ╚═════╝   ╚═╝   ╚═╝  ╚═╝╚══════╝
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                     
**/



/**
 * Contrôleur pour gérer les patients.
 * Permet l'affichage, la création, la modification et la suppression des patients.
 */
@Controller
@RequestMapping("/patients")
public class PatientController {

    @Autowired
    private PatientRepository patientRepository;
    
    @Autowired
    private CountryRepository countryRepository;

    /**
     * Récupère tous les patients enregistrés dans la base de données.
     *
     * @return Une liste de tous les patients.
     */
    @GetMapping
    @ResponseBody
    public List<Patient> getAllPatients() {
        return patientRepository.findAll();
    }

    /**
     * Crée un nouveau patient à partir des données reçues en JSON.
     *
     * @param patient Les détails du patient à créer.
     * @return Le patient créé et sauvegardé dans la base de données.
     */
    @PostMapping
    @ResponseBody
    public Patient createPatient(@RequestBody Patient patient) {
        return patientRepository.save(patient);
    }

    /**
     * Affiche la page d'édition du patient avec ses informations préremplies.
     *
     * @param id    L'identifiant du patient à modifier.
     * @param model Le modèle Spring utilisé pour passer les données à la vue.
     * @return Le nom de la vue Thymeleaf à afficher (edit-patient.html).
     */
    @GetMapping("/edit/{id}")
    public String editPatient(@PathVariable int id, Model model) {
        Optional<Patient> optionalPatient = patientRepository.findById(id);

        // Si le patient n'existe pas, rediriger vers une page d'erreur ou afficher un message
        if (optionalPatient.isEmpty()) {
            model.addAttribute("error", "Patient non trouvé avec l'ID : " + id);
            return "error"; // Affiche une vue d'erreur (error.html)
        }

        Patient patient = optionalPatient.get();
        List<Country> countries = countryRepository.findAll();

        model.addAttribute("patient", patient);
        model.addAttribute("countries", countries);
        return "edit-patient";
    }

    /**
     * Met à jour ou supprime un patient selon l'action spécifiée.
     *
     * @param id      L'identifiant du patient ciblé.
     * @param action  Chaîne précisant l'action à effectuer : "update" pour modifier, "delete" pour supprimer.
     * @param patient Objet contenant les nouvelles informations du patient (utilisé uniquement si action = update).
     * @return Le nom de la vue de confirmation ("valid") une fois l'action effectuée.
     */
    @PostMapping("/edit/{id}")
    public String updateOrDeletePatient(@PathVariable int id,
                                        @RequestParam("action") String action,
                                        @ModelAttribute Patient patient) {

        Optional<Patient> optionalPatient = patientRepository.findById(id);

        // Si le patient n'existe pas, rediriger vers une page d'erreur ou afficher un message
        if (optionalPatient.isEmpty()) {
            return "redirect:/error?message=Patient%20non%20trouvé%20avec%20l'ID%20" + id;
        }

        Patient existingPatient = optionalPatient.get();

        if ("update".equals(action)) {
            // Mise à jour des infos principales
            existingPatient.setFirstName(patient.getFirstName());
            existingPatient.setLastName(patient.getLastName());
            existingPatient.setGender(patient.getGender());
            existingPatient.setDateOfBirth(patient.getDateOfBirth());

            // Mise à jour du contact
            if (patient.getContact() != null) {
                if (patient.getContact().getPhoneNumber() != null) {
                    existingPatient.getContact().setPhoneNumber(patient.getContact().getPhoneNumber());
                }
                if (patient.getContact().getEmail() != null) {
                    existingPatient.getContact().setEmail(patient.getContact().getEmail());
                }
            }

            // Mise à jour de l'adresse
            if (patient.getAddress() != null) {
                Address existingAddress = existingPatient.getAddress();

                if (patient.getAddress().getNumber() != null) {
                    existingAddress.setNumber(patient.getAddress().getNumber());
                }
                if (patient.getAddress().getStreet() != null) {
                    existingAddress.setStreet(patient.getAddress().getStreet());
                }

                // Mise à jour de la ville
                if (patient.getAddress().getCity() != null) {
                    City existingCity = existingAddress.getCity();

                    if (patient.getAddress().getCity().getCityName() != null) {
                        existingCity.setCityName(patient.getAddress().getCity().getCityName());
                    }
                    if (patient.getAddress().getCity().getCityCode() != null) {
                        existingCity.setCityCode(patient.getAddress().getCity().getCityCode());
                    }

                    // Mise à jour du pays
                    if (patient.getAddress().getCity().getCountry() != null) {
                        Country existingCountry = existingCity.getCountry();

                        if (patient.getAddress().getCity().getCountry().getIsoCode() != null) {
                            existingCountry.setIsoCode(patient.getAddress().getCity().getCountry().getIsoCode());
                        }
                        if (patient.getAddress().getCity().getCountry().getCountryName() != null) {
                            existingCountry.setCountryName(patient.getAddress().getCity().getCountry().getCountryName());
                        }
                    }
                }
            }

            // Mise à jour des situations
            existingPatient.setFamilySituation(patient.getFamilySituation());
            existingPatient.setProfessionalSituation(patient.getProfessionalSituation());

            // Sauvegarde finale
            patientRepository.save(existingPatient);

        } else if ("delete".equals(action)) {
            patientRepository.delete(existingPatient);
        }

        return "valid";
    }
}