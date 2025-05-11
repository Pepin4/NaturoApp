package be.gestion.naturopathie.dimitri.controller;

import be.gestion.naturopathie.dimitri.model.Address;
import be.gestion.naturopathie.dimitri.model.City;
import be.gestion.naturopathie.dimitri.model.Contact;
import be.gestion.naturopathie.dimitri.model.Country;
import be.gestion.naturopathie.dimitri.model.Issue;
import be.gestion.naturopathie.dimitri.model.IssueSolution;
import be.gestion.naturopathie.dimitri.model.Solution;
import be.gestion.naturopathie.dimitri.model.Supplier;
import be.gestion.naturopathie.dimitri.repository.AddressRepository;
import be.gestion.naturopathie.dimitri.repository.CityRepository;
import be.gestion.naturopathie.dimitri.repository.ContactRepository;
import be.gestion.naturopathie.dimitri.repository.CountryRepository;
import be.gestion.naturopathie.dimitri.repository.IssueRepository;
import be.gestion.naturopathie.dimitri.repository.IssueSolutionRepository;
import be.gestion.naturopathie.dimitri.repository.SolutionRepository;
import be.gestion.naturopathie.dimitri.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;


/**

        ███████╗ ██████╗ ██╗     ██╗   ██╗████████╗██╗ ██████╗ ███╗   ██╗     ██████╗████████╗██████╗ ██╗     
        ██╔════╝██╔═══██╗██║     ██║   ██║╚══██╔══╝██║██╔═══██╗████╗  ██║    ██╔════╝╚══██╔══╝██╔══██╗██║     
        ███████╗██║   ██║██║     ██║   ██║   ██║   ██║██║   ██║██╔██╗ ██║    ██║        ██║   ██████╔╝██║     
        ╚════██║██║   ██║██║     ██║   ██║   ██║   ██║██║   ██║██║╚██╗██║    ██║        ██║   ██╔══██╗██║     
        ███████║╚██████╔╝███████╗╚██████╔╝   ██║   ██║╚██████╔╝██║ ╚████║    ╚██████╗   ██║   ██║  ██║███████╗
        ╚══════╝ ╚═════╝ ╚══════╝ ╚═════╝    ╚═╝   ╚═╝ ╚═════╝ ╚═╝  ╚═══╝     ╚═════╝   ╚═╝   ╚═╝  ╚═╝╚══════╝
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                              
**/



/**
 * Contrôleur pour la gestion des solutions.
 * Permet l'affichage, la création, la modification et la suppression des solutions.
 */
@Controller
@RequestMapping("/solutions")
public class SolutionController {

    @Autowired
    private SolutionRepository solutionRepository;
    
    @Autowired
    private SupplierRepository supplierRepository;
    
    @Autowired
    private ContactRepository contactRepository;
    
    @Autowired
    private AddressRepository addressRepository;
    
    @Autowired
    private CityRepository cityRepository;
    
    @Autowired
    private CountryRepository countryRepository;
    
    @Autowired
    private IssueRepository issueRepository;
    
    @Autowired
    private IssueSolutionRepository issueSolutionRepository;

    /**
     * Affiche la liste complète des solutions.
     *
     * @param model Le modèle pour injecter les données dans la vue.
     * @return La vue Thymeleaf affichant la liste des solutions.
     */
    @GetMapping
    public String listSolutions(Model model) {
        List<Solution> solutions = solutionRepository.findAll();
        model.addAttribute("solutions", solutions);
        return "solutions/list";
    }

    /**
     * Affiche les détails d'une solution spécifique.
     *
     * @param id    L'identifiant de la solution à afficher.
     * @param model Le modèle pour injecter les données dans la vue.
     * @return La vue des détails ou une page d'erreur.
     */
    @GetMapping("/{id}")
    public String showSolution(@PathVariable int id, Model model) {
        Optional<Solution> optionalSolution = solutionRepository.findById(id);
        if (optionalSolution.isPresent()) {
            model.addAttribute("solution", optionalSolution.get());
            return "solutions/detail";
        }
        model.addAttribute("error", "Solution introuvable avec l'ID : " + id);
        return "solutions/error";
    }

    /**
     * Affiche le formulaire de création d'une solution pour un problème donné.
     *
     * @param issueId       ID du problème concerné.
     * @param appointmentId (optionnel) ID du rendez-vous lié.
     * @param model         Le modèle pour injecter les données dans la vue.
     * @return La vue du formulaire de création.
     */
    @GetMapping("/new")
    public String showCreateForm(@RequestParam("issueId") int issueId,
                                 @RequestParam(value = "appointmentId", required = false) Integer appointmentId,
                                 Model model) {

        Optional<Issue> optionalIssue = issueRepository.findById(issueId);
        if (optionalIssue.isEmpty()) {
            model.addAttribute("error", "Problème introuvable avec l'ID : " + issueId);
            return "solutions/error";
        }

        model.addAttribute("issue", optionalIssue.get());
        model.addAttribute("issueId", issueId);
        model.addAttribute("solution", new Solution());
        model.addAttribute("suppliers", new Supplier());
        model.addAttribute("countries", countryRepository.findAll());
        model.addAttribute("cities", new City());

        if (appointmentId != null) {
            model.addAttribute("appointmentId", appointmentId);
        }

        return "new-solution";
    }

    /**
     * Crée une nouvelle solution et ses dépendances si nécessaire.
     *
     * @param solutionRemedy   Le remède proposé.
     * @param solutionBenefit  Les bénéfices du remède.
     * @param issueId          L'ID du problème lié.
     * @param countryId        L'ID du pays.
     * @param cityCode         Le code de la ville.
     * @param cityName         Le nom de la ville.
     * @param addressNumber    Le numéro de rue.
     * @param addressStreet    Le nom de rue.
     * @param contactNumber    Le numéro de téléphone du contact.
     * @param contactEmail     L'adresse email du contact.
     * @param supplierName     Le nom du fournisseur.
     * @param appointmentId    (Optionnel) L'ID du rendez-vous.
     * @param model            Le modèle pour gérer les erreurs.
     * @return Redirection vers la page des solutions liées à un problème.
     */
    @PostMapping
    public String createSolution(@RequestParam("solutionRemedy") String solutionRemedy,
                                 @RequestParam("solutionBenefit") String solutionBenefit,
                                 @RequestParam("issueId") int issueId,
                                 @RequestParam("countryId") int countryId,
                                 @RequestParam("cityCode") String cityCode,
                                 @RequestParam("cityName") String cityName,
                                 @RequestParam("addressNumber") String addressNumber,
                                 @RequestParam("addressStreet") String addressStreet,
                                 @RequestParam("contactNumber") String contactNumber,
                                 @RequestParam("contactEmail") String contactEmail,
                                 @RequestParam("supplierName") String supplierName,
                                 @RequestParam(value = "appointmentId", required = false) Integer appointmentId,
                                 Model model) {

        Optional<Issue> optionalIssue = issueRepository.findById(issueId);
        if (optionalIssue.isEmpty()) {
            model.addAttribute("error", "Problème introuvable avec l'ID : " + issueId);
            return "solutions/error";
        }

        Optional<Country> optionalCountry = countryRepository.findById(countryId);
        if (optionalCountry.isEmpty()) {
            model.addAttribute("error", "Pays introuvable avec l'ID : " + countryId);
            return "solutions/error";
        }
        Country country = optionalCountry.get();

        City city = cityRepository.findByCityCodeAndCityName(cityCode, cityName);
        if (city == null) {
            city = new City(cityCode, cityName, country);
            cityRepository.save(city);
        }

        Address address = addressRepository.findByNumberAndStreet(addressNumber, addressStreet);
        if (address == null) {
            address = new Address(addressNumber, addressStreet, city);
            addressRepository.save(address);
        }

        Contact contact = contactRepository.findByPhoneNumberOrEmail(contactNumber, contactEmail);
        if (contact == null) {
            contact = new Contact(contactNumber, contactEmail);
            contactRepository.save(contact);
        }

        Supplier supplier = supplierRepository.findByName(supplierName);
        if (supplier == null) {
            supplier = new Supplier(supplierName, address, contact);
            supplierRepository.save(supplier);
        }

        Solution solution = solutionRepository.findByRemedyAndSupplier(solutionRemedy, supplier);
        if (solution == null) {
            solution = new Solution(solutionRemedy, solutionBenefit, supplier);
            solutionRepository.save(solution);
        }

        IssueSolution issueSolution = new IssueSolution(optionalIssue.get(), solution);
        issueSolutionRepository.save(issueSolution);

        if (appointmentId != null) {
            return "redirect:/issue-solutions?issueId=" + issueId + "&appointmentId=" + appointmentId;
        }

        return "redirect:/issue-solutions?issueId=" + issueId;
    }

    /**
     * Met à jour une solution existante.
     *
     * @param id              L'ID de la solution à modifier.
     * @param updatedSolution L'objet contenant les nouvelles données.
     * @param model           Le modèle pour les messages d'erreur.
     * @return Redirection vers la liste des solutions.
     */
    @PostMapping("/edit/{id}")
    public String updateSolution(@PathVariable int id, @ModelAttribute Solution updatedSolution, Model model) {
        Optional<Solution> optionalSolution = solutionRepository.findById(id);
        if (optionalSolution.isEmpty()) {
            model.addAttribute("error", "Impossible de modifier : solution introuvable.");
            return "solutions/error";
        }

        Solution existing = optionalSolution.get();
        existing.setRemedy(updatedSolution.getRemedy());
        existing.setBenefits(updatedSolution.getBenefits());
        existing.setSupplier(updatedSolution.getSupplier());
        solutionRepository.save(existing);

        return "redirect:/solutions";
    }

    /**
     * Supprime une solution selon son ID.
     *
     * @param id    L'ID de la solution à supprimer.
     * @param model Le modèle pour les messages d'erreur.
     * @return Redirection vers la liste des solutions.
     */
    @PostMapping("/delete/{id}")
    public String deleteSolution(@PathVariable int id, Model model) {
        Optional<Solution> optionalSolution = solutionRepository.findById(id);
        if (optionalSolution.isEmpty()) {
            model.addAttribute("error", "Impossible de supprimer : solution introuvable.");
            return "solutions/error";
        }

        solutionRepository.delete(optionalSolution.get());
        return "redirect:/solutions";
    }

    /**
     * Effectue une recherche de fournisseurs par nom (auto-complétion).
     *
     * @param query Le mot-clé à rechercher.
     * @return Une liste de fournisseurs dont le nom correspond au mot-clé.
     */
    @GetMapping("/search-suppliers")
    @ResponseBody
    public List<Supplier> searchSuppliers(@RequestParam("query") String query) {
        if (query != null && !query.trim().isEmpty()) {
            return supplierRepository.findBySupplierNameContainingIgnoreCase(query);
        }
        return new ArrayList<>();
    }
}