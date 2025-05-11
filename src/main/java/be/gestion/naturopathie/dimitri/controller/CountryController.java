package be.gestion.naturopathie.dimitri.controller;

import be.gestion.naturopathie.dimitri.model.Country;
import be.gestion.naturopathie.dimitri.repository.CountryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


/**
		
		 ██████╗ ██████╗ ██╗   ██╗███╗   ██╗████████╗██████╗ ██╗   ██╗     ██████╗████████╗██████╗ ██╗     
		██╔════╝██╔═══██╗██║   ██║████╗  ██║╚══██╔══╝██╔══██╗╚██╗ ██╔╝    ██╔════╝╚══██╔══╝██╔══██╗██║     
		██║     ██║   ██║██║   ██║██╔██╗ ██║   ██║   ██████╔╝ ╚████╔╝     ██║        ██║   ██████╔╝██║     
		██║     ██║   ██║██║   ██║██║╚██╗██║   ██║   ██╔══██╗  ╚██╔╝      ██║        ██║   ██╔══██╗██║     
		╚██████╗╚██████╔╝╚██████╔╝██║ ╚████║   ██║   ██║  ██║   ██║       ╚██████╗   ██║   ██║  ██║███████╗
		 ╚═════╝ ╚═════╝  ╚═════╝ ╚═╝  ╚═══╝   ╚═╝   ╚═╝  ╚═╝   ╚═╝        ╚═════╝   ╚═╝   ╚═╝  ╚═╝╚══════╝
		                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                             
**/



/**
 * Contrôleur pour gérer les pays.
 * Permet l'affichage, la création, la modification et la suppression des pays.
 */
@Controller
@RequestMapping("/country")
public class CountryController {

    @Autowired
    private CountryRepository countryRepository;

    /**
     * Affiche la liste des pays.
     *
     * @param model Le modèle Spring utilisé pour passer les données à la vue.
     * @return La vue affichant la liste des pays.
     */
    @GetMapping
    public String listCountries(Model model) {
        List<Country> countries = countryRepository.findAll();
        model.addAttribute("countries", countries);
        return "country/list"; // templates/country/list.html
    }

    /**
     * Affiche les détails d'un pays.
     *
     * @param id    L'identifiant du pays à afficher.
     * @param model Le modèle Spring utilisé pour passer les données à la vue.
     * @return La vue affichant les détails du pays.
     */
    @GetMapping("/{id}")
    public String showCountry(@PathVariable int id, Model model) {
        Optional<Country> optionalCountry = countryRepository.findById(id);

        // Gestion du cas où le pays n'existe pas
        if (optionalCountry.isEmpty()) {
            model.addAttribute("error", "Pays introuvable avec l'id : " + id);
            return "error"; // Rediriger vers une page d'erreur ou afficher un message d'erreur
        }

        Country country = optionalCountry.get();
        model.addAttribute("country", country);
        return "country/detail"; // templates/country/detail.html
    }

    /**
     * Affiche le formulaire pour créer un nouveau pays.
     *
     * @param model Le modèle Spring utilisé pour passer les données à la vue.
     * @return La vue contenant le formulaire de création.
     */
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("country", new Country());
        return "country/new"; // templates/country/new.html
    }

    /**
     * Crée un nouveau pays.
     *
     * @param country Les détails du pays à créer.
     * @return La redirection vers la liste des pays après la création.
     */
    @PostMapping
    public String createCountry(@ModelAttribute Country country) {
        countryRepository.save(country);
        return "redirect:/country";
    }

    /**
     * Affiche le formulaire pour éditer un pays existant.
     *
     * @param id    L'identifiant du pays à éditer.
     * @param model Le modèle Spring utilisé pour passer les données à la vue.
     * @return La vue contenant le formulaire d'édition.
     */
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        Optional<Country> optionalCountry = countryRepository.findById(id);

        // Gestion du cas où le pays n'existe pas
        if (optionalCountry.isEmpty()) {
            model.addAttribute("error", "Pays introuvable avec l'id : " + id);
            return "error"; // Rediriger vers une page d'erreur ou afficher un message d'erreur
        }

        Country country = optionalCountry.get();
        model.addAttribute("country", country);
        return "country/edit"; // templates/country/edit.html
    }

    /**
     * Met à jour un pays existant.
     *
     * @param id            L'identifiant du pays à modifier.
     * @param countryDetails Les nouvelles informations du pays à modifier.
     * @return La redirection vers la liste des pays après la mise à jour.
     */
    @PostMapping("/edit/{id}")
    public String updateCountry(@PathVariable int id, @ModelAttribute Country countryDetails) {
        Optional<Country> optionalCountry = countryRepository.findById(id);

        // Gestion du cas où le pays n'existe pas
        if (optionalCountry.isEmpty()) {
            return "redirect:/error?message=Pays%20introuvable%20avec%20l'id%20" + id;
        }

        Country country = optionalCountry.get();
        country.setIsoCode(countryDetails.getIsoCode());
        country.setCountryName(countryDetails.getCountryName());
        countryRepository.save(country);

        return "redirect:/country";
    }

    /**
     * Supprime un pays.
     *
     * @param id L'identifiant du pays à supprimer.
     * @return La redirection vers la liste des pays après la suppression.
     */
    @PostMapping("/delete/{id}")
    public String deleteCountry(@PathVariable int id) {
        Optional<Country> optionalCountry = countryRepository.findById(id);

        // Gestion du cas où le pays n'existe pas
        if (optionalCountry.isEmpty()) {
            return "redirect:/error?message=Pays%20introuvable%20avec%20l'id%20" + id;
        }

        Country country = optionalCountry.get();
        countryRepository.delete(country);
        return "redirect:/country";
    }
}