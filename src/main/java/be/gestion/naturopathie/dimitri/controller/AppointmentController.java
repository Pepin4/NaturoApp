package be.gestion.naturopathie.dimitri.controller;

import be.gestion.naturopathie.dimitri.model.Address;
import be.gestion.naturopathie.dimitri.model.Appointment;
import be.gestion.naturopathie.dimitri.model.AppointmentIssueSolution;
import be.gestion.naturopathie.dimitri.model.City;
import be.gestion.naturopathie.dimitri.model.Contact;
import be.gestion.naturopathie.dimitri.model.Country;
import be.gestion.naturopathie.dimitri.model.Issue;
import be.gestion.naturopathie.dimitri.model.MedicalInfo;
import be.gestion.naturopathie.dimitri.model.Patient;
import be.gestion.naturopathie.dimitri.model.Solution;
import be.gestion.naturopathie.dimitri.model.User;
import be.gestion.naturopathie.dimitri.repository.AddressRepository;
import be.gestion.naturopathie.dimitri.repository.AppointmentIssueSolutionRepository;
import be.gestion.naturopathie.dimitri.repository.AppointmentRepository;
import be.gestion.naturopathie.dimitri.repository.CityRepository;
import be.gestion.naturopathie.dimitri.repository.ContactRepository;
import be.gestion.naturopathie.dimitri.repository.CountryRepository;
import be.gestion.naturopathie.dimitri.repository.MedicalInfoRepository;
import be.gestion.naturopathie.dimitri.repository.PatientRepository;
import be.gestion.naturopathie.dimitri.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.security.Principal;
import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.Date;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;


/**

         █████╗ ██████╗ ██████╗  ██████╗ ██╗███╗   ██╗████████╗███╗   ███╗███████╗███╗   ██╗████████╗     ██████╗████████╗██████╗ ██╗     
        ██╔══██╗██╔══██╗██╔══██╗██╔═══██╗██║████╗  ██║╚══██╔══╝████╗ ████║██╔════╝████╗  ██║╚══██╔══╝    ██╔════╝╚══██╔══╝██╔══██╗██║     
        ███████║██████╔╝██████╔╝██║   ██║██║██╔██╗ ██║   ██║   ██╔████╔██║█████╗  ██╔██╗ ██║   ██║       ██║        ██║   ██████╔╝██║     
        ██╔══██║██╔═══╝ ██╔═══╝ ██║   ██║██║██║╚██╗██║   ██║   ██║╚██╔╝██║██╔══╝  ██║╚██╗██║   ██║       ██║        ██║   ██╔══██╗██║     
        ██║  ██║██║     ██║     ╚██████╔╝██║██║ ╚████║   ██║   ██║ ╚═╝ ██║███████╗██║ ╚████║   ██║       ╚██████╗   ██║   ██║  ██║███████╗
        ╚═╝  ╚═╝╚═╝     ╚═╝      ╚═════╝ ╚═╝╚═╝  ╚═══╝   ╚═╝   ╚═╝     ╚═╝╚══════╝╚═╝  ╚═══╝   ╚═╝        ╚═════╝   ╚═╝   ╚═╝  ╚═╝╚══════╝
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                
**/



/**
 * Contrôleur pour gérer les rendez-vous.
 * Permet l'affichage, la création, la modification et la suppression des rendez-vous.
 */
@Controller
@RequestMapping("/appointments")
public class AppointmentController {
    
    @Autowired
    private AppointmentRepository appointmentRepository;
    
    @Autowired
    private AppointmentIssueSolutionRepository appointmentIssueSolutionRepository;
    
    @Autowired
    private PatientRepository patientRepository;
    
    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private AddressRepository addressRepository;
    
    @Autowired
    private CityRepository cityRepository;
    
    @Autowired
    private CountryRepository countryRepository;
    
    @Autowired
    private ContactRepository contactRepository;
    
    @Autowired
    private MedicalInfoRepository medicalInfoRepository;

    // Récupérer l'utilisateur connecté
    private User getAuthenticatedUser(Principal principal) {
        return userRepository.findByUserName(principal.getName()).get();
    }

    /**
     * Affiche la page des rendez-vous avec la liste des rendez-vous de l'utilisateur.
     * 
     * @param model L'objet Model pour ajouter des attributs à la vue.
     * @param principal L'utilisateur connecté, fourni par Spring Security.
     * @return La vue "appointments" contenant la liste des rendez-vous.
     */
    @GetMapping
    public String getAppointmentsPage(Model model, Principal principal) {
        User user = getAuthenticatedUser(principal);
        List<Appointment> appointments = appointmentRepository.findByUserUserId(user.getUserId());
        model.addAttribute("appointments", appointments);
        return "appointments";
    }

    /**
     * Affiche le formulaire pour créer un nouveau rendez-vous, un patient et une adresse.
     * 
     * @param model L'objet Model pour ajouter des attributs à la vue.
     * @return La vue "new-appointment" avec les formulaires de création.
     */
    @GetMapping("/new")
    public String showNewAppointmentForm(Model model) {
        model.addAttribute("patients", patientRepository.findAll());
        model.addAttribute("users", userRepository.findAll());
        model.addAttribute("countries", getSortedCountries());
        model.addAttribute("cities", cityRepository.findAll());
        model.addAttribute("contacts", contactRepository.findAll());
        model.addAttribute("appointment", new Appointment());
        model.addAttribute("patient", new Patient());
        model.addAttribute("address", new Address());
        return "new-appointment";
    }

    /**
     * Crée un nouveau rendez-vous, un patient, une adresse, une ville et un contact.
     * 
     * @param appointment L'objet Appointment contenant les informations du rendez-vous.
     * @param patient L'objet Patient contenant les informations du patient.
     * @param address L'objet Address contenant les informations de l'adresse.
     * @param city L'objet City représentant la ville du patient.
     * @param country L'objet Country représentant le pays du patient.
     * @param contact L'objet Contact représentant les informations de contact du patient.
     * @param patientId L'ID du patient existant (si applicable).
     * @param principal L'utilisateur connecté.
     * @param model L'objet Model pour ajouter des attributs à la vue.
     * @return Redirige vers la liste des rendez-vous après la création.
     */
    @PostMapping("/new")
    public String createAppointment(
        @ModelAttribute Appointment appointment,
        @ModelAttribute Patient patient,
        @ModelAttribute Address address,
        @ModelAttribute City city,
        @ModelAttribute Country country,
        @ModelAttribute Contact contact,
        @RequestParam(name = "patientId", required = false, defaultValue = "0") int patientId,
        Principal principal,
        Model model
    ) {
        // Normalisation de la date
        if (appointment.getDateTime() != null) {
            appointment.setDateTime(new java.sql.Timestamp(appointment.getDateTime().getTime()));
        }

        if (patient.getDateOfBirth() != null) {
            patient.setDateOfBirth(java.sql.Date.valueOf(new java.text.SimpleDateFormat("yyyy-MM-dd").format(patient.getDateOfBirth())));
        }

        // Gérer le patient
        if (patientId != 0) {
            patient = patientRepository.findById(patientId).get(); // Aucune gestion d'erreur ici
        } else {
            country = countryRepository.findById(country.getCountryId()).get(); // Aucune gestion d'erreur ici
        }

        // Vérification ou création de la ville, de l'adresse et du contact
        city = getOrCreateCity(city, country);
        address = getOrCreateAddress(address, city);
        contactRepository.save(contact);

        // Sauvegarder le patient si nouveau
        if (patientId == 0) {
            patient.setAddress(address);
            patient.setContact(contact);
            patientRepository.save(patient);
        }

        // Vérification de l'existence du rendez-vous
        if (checkIfAppointmentExists(appointment, patient, model)) {
            return "new-appointment";
        }

        // Récupérer l'utilisateur connecté
        User user = getAuthenticatedUser(principal);

        // Enregistrement du rendez-vous
        appointment.setUser(user);
        appointment.setPatient(patient);
        appointmentRepository.save(appointment);

        return "redirect:/appointments";
    }

    // Méthodes d'assistance

    /**
     * Récupère la liste des pays triée par nom.
     * 
     * @return Liste des pays triés.
     */
    private List<Country> getSortedCountries() {
        List<Country> countries = countryRepository.findAll();
        countries.sort(Comparator.comparing(c -> c.getCountryName().toLowerCase()));
        return countries;
    }

    /**
     * Récupère ou crée une ville en fonction des informations données.
     * 
     * @param city L'objet City à vérifier ou créer.
     * @param country L'objet Country représentant le pays de la ville.
     * @return La ville existante ou créée.
     */
    private City getOrCreateCity(City city, Country country) {
        // Recherche simple, pas de gestion d'erreur complexe
        City existingCity = cityRepository.findByCityNameAndCityCodeAndCountry(city.getCityName(), city.getCityCode(), country).orElse(null);
        if (existingCity == null) {
            city.setCountry(country);
            cityRepository.save(city);
            return city;
        } else {
            return existingCity;
        }
    }

    /**
     * Récupère ou crée une adresse en fonction des informations données.
     * 
     * @param address L'objet Address à vérifier ou créer.
     * @param city L'objet City représentant la ville de l'adresse.
     * @return L'adresse existante ou créée.
     */
    private Address getOrCreateAddress(Address address, City city) {
        // Recherche simple, pas de gestion d'erreur complexe
        Address existingAddress = addressRepository.findByStreetAndCity(address.getStreet(), city).orElse(null);
        if (existingAddress == null) {
            address.setCity(city);
            addressRepository.save(address);
            return address;
        } else {
            return existingAddress;
        }
    }

    /**
     * Vérifie si un rendez-vous existe déjà pour un patient à une date donnée.
     * 
     * @param appointment L'objet Appointment contenant les informations du rendez-vous à vérifier.
     * @param patient L'objet Patient pour lequel vérifier la disponibilité.
     * @param model L'objet Model pour ajouter des attributs à la vue.
     * @return true si un rendez-vous existe déjà, sinon false.
     */
    private boolean checkIfAppointmentExists(Appointment appointment, Patient patient, Model model) {
        LocalDate date = ((Timestamp) appointment.getDateTime()).toLocalDateTime().toLocalDate();
        List<Appointment> existingAppointments = appointmentRepository.findByPatientAndDate(patient, date);
        if (!existingAppointments.isEmpty()) {
            model.addAttribute("errorMessage", "Un rendez-vous existe déjà à cette date !");
            return true;
        }
        return false;
    }

    /**
     * Affiche le formulaire de modification d'un rendez-vous existant.
     * 
     * @param id L'ID du rendez-vous à éditer.
     * @param model L'objet Model pour ajouter des attributs à la vue.
     * @return La vue "edit-appointment" avec les données du rendez-vous à modifier.
     */
    @GetMapping("/edit/{id}")
    public String editAppointmentForm(@PathVariable("id") int id, Model model) {
        Appointment appointment = appointmentRepository.findById(id).get(); // Aucune gestion d'erreur ici
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm");
        String formattedDate = dateFormat.format(appointment.getDateTime());

        List<AppointmentIssueSolution> appointmentIssueSolutions = appointmentIssueSolutionRepository.findByAppointment_AppointmentId(id);

        Map<Issue, List<Solution>> issueSolutionMap = new LinkedHashMap<>();
        for (AppointmentIssueSolution ais : appointmentIssueSolutions) {
            Issue issue = ais.getIssueSolution().getIssue();
            Solution solution = ais.getIssueSolution().getSolution();
            issueSolutionMap.computeIfAbsent(issue, k -> new ArrayList<>()).add(solution);
        }

        model.addAttribute("appointment", appointment);
        model.addAttribute("formattedDate", formattedDate);
        model.addAttribute("issueSolutionMap", issueSolutionMap);

        return "edit-appointment";
    }

    /**
     * Gère la soumission du formulaire d'édition d'un rendez-vous.
     * 
     * @param id L'ID du rendez-vous à modifier.
     * @param updatedAppointment L'objet Appointment avec les nouvelles données.
     * @return La redirection vers la liste des rendez-vous après la mise à jour.
     */
    @PostMapping("/edit/{id}")
    public String updateAppointmentForm(@PathVariable("id") int id, @ModelAttribute Appointment updatedAppointment) {
        Appointment existingAppointment = appointmentRepository.findById(id).get();  // Aucune gestion d'erreur ici
        Date dateTime = updatedAppointment.getDateTime();
        if (dateTime != null) {
            existingAppointment.setDateTime(new java.sql.Timestamp(dateTime.getTime()));
        }

        existingAppointment.setSubject(updatedAppointment.getSubject());
        existingAppointment.setTheExcesses(updatedAppointment.getTheExcesses());
        existingAppointment.setEatingHabits(updatedAppointment.getEatingHabits());
        existingAppointment.setSleepQuality(updatedAppointment.getSleepQuality());
        existingAppointment.setPhysicalActivity(updatedAppointment.getPhysicalActivity());
        existingAppointment.setBloodCirculation(updatedAppointment.getBloodCirculation());
        existingAppointment.setRespiratoryCapacity(updatedAppointment.getRespiratoryCapacity());
        existingAppointment.setTransit(updatedAppointment.getTransit());
        existingAppointment.setStressReaction(updatedAppointment.getStressReaction());
        existingAppointment.setNote(updatedAppointment.getNote());

        appointmentRepository.save(existingAppointment);

        return "redirect:/appointments";
    }

    /**
     * Supprime un rendez-vous de manière sécurisée.
     * 
     * @param id L'ID du rendez-vous à supprimer.
     * @return La redirection vers la liste des rendez-vous après suppression.
     */
    @PostMapping("/delete/{id}")
    public String deleteAppointmentForm(@PathVariable("id") int id) {
        appointmentRepository.deleteById(id);
        return "redirect:/appointments";
    }

    /**
     * Génére un rapport pour un rendez-vous donné.
     * 
     * @param appointmentId L'ID du rendez-vous pour lequel générer le rapport.
     * @param model L'objet Model pour ajouter des attributs à la vue.
     * @return La vue "report" avec les informations du rendez-vous.
     */
    @GetMapping("/report/{id}")
    public String generateReport(@PathVariable("id") int appointmentId, Model model) {
        Appointment appointment = appointmentRepository.findById(appointmentId).get();  // Aucune gestion d'erreur ici
        Patient patient = appointment.getPatient();

        List<AppointmentIssueSolution> appointmentIssuesSolutions = appointmentIssueSolutionRepository.findByAppointment_AppointmentId(appointmentId);
        List<MedicalInfo> medicalInfos = medicalInfoRepository.findByPatient_PatientId(patient.getPatientId());

        model.addAttribute("appointment", appointment);
        model.addAttribute("patient", patient);
        model.addAttribute("appointmentIssuesSolutions", appointmentIssuesSolutions);
        model.addAttribute("medicalInfos", medicalInfos);

        return "report";
    }

    /**
     * Recherche de patients par prénom ou nom.
     * 
     * @param query Le texte de recherche pour les patients.
     * @return Une réponse avec la liste des patients correspondants.
     */
    @GetMapping("/patients/search")
    public ResponseEntity<List<Patient>> searchPatients(@RequestParam("query") String query) {
        List<Patient> patients = patientRepository.findByFirstNameContainingOrLastNameContaining(query, query);
        return ResponseEntity.ok(patients);
    }
}
