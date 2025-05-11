package be.gestion.naturopathie.dimitri.controller;

import be.gestion.naturopathie.dimitri.model.Address;
import be.gestion.naturopathie.dimitri.model.City;
import be.gestion.naturopathie.dimitri.model.Contact;
import be.gestion.naturopathie.dimitri.model.Country;
import be.gestion.naturopathie.dimitri.model.User;
import be.gestion.naturopathie.dimitri.repository.AddressRepository;
import be.gestion.naturopathie.dimitri.repository.CityRepository;
import be.gestion.naturopathie.dimitri.repository.ContactRepository;
import be.gestion.naturopathie.dimitri.repository.CountryRepository;
import be.gestion.naturopathie.dimitri.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


/**
		
		██╗   ██╗███████╗███████╗██████╗      ██████╗████████╗██████╗ ██╗     
		██║   ██║██╔════╝██╔════╝██╔══██╗    ██╔════╝╚══██╔══╝██╔══██╗██║     
		██║   ██║███████╗█████╗  ██████╔╝    ██║        ██║   ██████╔╝██║     
		██║   ██║╚════██║██╔══╝  ██╔══██╗    ██║        ██║   ██╔══██╗██║     
		╚██████╔╝███████║███████╗██║  ██║    ╚██████╗   ██║   ██║  ██║███████╗
		 ╚═════╝ ╚══════╝╚══════╝╚═╝  ╚═╝     ╚═════╝   ╚═╝   ╚═╝  ╚═╝╚══════╝
	                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 
**/



/**
 * Contrôleur pour la gestion des utilisateurs.
 * Permet l'affichage, la création, la modification et la suppression des  utilisateurs.
 */
@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserRepository userRepository;
    
    @Autowired
    private CountryRepository countryRepository;
    
    @Autowired
    private CityRepository cityRepository;
    
    @Autowired
    private AddressRepository addressRepository;
    
    @Autowired
    private ContactRepository contactRepository;

    /**
     * Affiche la page de connexion.
     * Vérifie si l'utilisateur est déjà authentifié.
     *
     * @return La vue login.
     */
    @GetMapping("/login")
    public String loginPage() {
        if (SecurityContextHolder.getContext().getAuthentication() != null &&
            SecurityContextHolder.getContext().getAuthentication().isAuthenticated() &&
            !SecurityContextHolder.getContext().getAuthentication().getPrincipal().equals("anonymousUser")) {
            return "login";
        }

        return "login";
    }

    /**
     * Redirige vers la page des rendez-vous après une tentative de connexion.
     * Utilisé par Spring Security pour rediriger après POST login.
     *
     * @return Redirection vers /appointments.
     */
    @PostMapping("/login")
    public String Register() {
        return "redirect:/appointments";
    }

    /**
     * Affiche le formulaire d'inscription.
     *
     * @param model Objet Model pour injecter les données dans la vue (liste des pays).
     * @return La vue signup.
     */
    @GetMapping("/signup")
    public String signupPage(Model model) {
        model.addAttribute("countries", countryRepository.findAll());
        return "signup";
    }

    /**
     * Traite la soumission du formulaire d'inscription.
     *
     * @param username     Nom d'utilisateur choisi par l'utilisateur.
     * @param password     Mot de passe brut, à encoder.
     * @param street       Rue de l'utilisateur.
     * @param number       Numéro de la rue.
     * @param cityCode     Code postal de la ville.
     * @param cityName     Nom de la ville.
     * @param countryName  Nom du pays (doit exister).
     * @param phoneNumber  Numéro de téléphone.
     * @param email        Adresse e-mail.
     * @param model        Objet Model pour envoyer des messages d'erreur ou données à la vue.
     * @return Redirection vers la page de connexion si succès, sinon retour au formulaire.
     */
    @PostMapping("/signup")
    public String signupUser(
            @RequestParam String username,
            @RequestParam String password,
            @RequestParam String street,
            @RequestParam String number,
            @RequestParam String cityCode,
            @RequestParam String cityName,
            @RequestParam String countryName,
            @RequestParam String phoneNumber,
            @RequestParam String email,
            Model model) {

        // 1. Vérifie si le nom d'utilisateur est déjà utilisé
        if (userRepository.existsByUserName(username)) {
            model.addAttribute("error", "Le nom d'utilisateur est déjà pris.");
            return "signup";
        }

        // 2. Récupération du pays (pas de création possible ici)
        Country country = countryRepository.findByCountryName(countryName);
        if (country == null) {
            model.addAttribute("error", "Pays invalide.");
            model.addAttribute("countries", countryRepository.findAll());
            return "signup";
        }

        // 3. Récupération ou création de la ville
        City city = cityRepository.findByCityCodeAndCityName(cityCode, cityName);
        if (city == null) {
            city = new City();
            city.setCityCode(cityCode);
            city.setCityName(cityName);
            city.setCountry(country);
            city = cityRepository.save(city);
        }

        // 4. Récupération ou création de l'adresse
        Address address = addressRepository.findByStreetAndNumberAndCity(street, number, city);
        if (address == null) {
            address = new Address();
            address.setStreet(street);
            address.setNumber(number);
            address.setCity(city);
            address = addressRepository.save(address);
        }

        // 5. Récupération ou création du contact
        Contact contact = contactRepository.findByPhoneNumberAndEmail(phoneNumber, email);
        if (contact == null) {
            contact = new Contact();
            contact.setPhoneNumber(phoneNumber);
            contact.setEmail(email);
            contact = contactRepository.save(contact);
        }

        // 6. Encodage du mot de passe avec BCrypt
        BCryptPasswordEncoder encoder = new BCryptPasswordEncoder();
        String hashedPassword = encoder.encode(password);

        // 7. Création et sauvegarde du nouvel utilisateur
        User user = new User(username, hashedPassword, address, contact);
        userRepository.save(user);

        // Redirection vers login avec un message de succès
        return "redirect:/login?signup=success";
    }
}