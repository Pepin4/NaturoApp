package be.gestion.naturopathie.dimitri.controller;

import be.gestion.naturopathie.dimitri.model.City;
import be.gestion.naturopathie.dimitri.repository.CityRepository;
import be.gestion.naturopathie.dimitri.repository.CountryRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


/**
		
		 ██████╗██╗████████╗██╗   ██╗     ██████╗████████╗██████╗ ██╗     
		██╔════╝██║╚══██╔══╝╚██╗ ██╔╝    ██╔════╝╚══██╔══╝██╔══██╗██║     
		██║     ██║   ██║    ╚████╔╝     ██║        ██║   ██████╔╝██║     
		██║     ██║   ██║     ╚██╔╝      ██║        ██║   ██╔══██╗██║     
		╚██████╗██║   ██║      ██║       ╚██████╗   ██║   ██║  ██║███████╗
		 ╚═════╝╚═╝   ╚═╝      ╚═╝        ╚═════╝   ╚═╝   ╚═╝  ╚═╝╚══════╝
		                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                               
**/



/**
 * Contrôleur pour gérer les villes.
 * Permet l'affichage, la création, la modification et la suppression des villes.
 */
@Controller
@RequestMapping("/city")
public class CityController {

    @Autowired
    private CityRepository cityRepository;

    @Autowired
    private CountryRepository countryRepository;

    /**
     * Affiche la liste de toutes les villes.
     *
     * @param model le modèle Spring utilisé pour transmettre les données à la vue.
     * @return le nom de la vue Thymeleaf qui affiche la liste des villes.
     */
    @GetMapping
    public String listCities(Model model) {
        model.addAttribute("cities", cityRepository.findAll());
        return "city/list";
    }

    /**
     * Affiche le détail d'une ville spécifique.
     *
     * @param id    l'identifiant de la ville à afficher.
     * @param model le modèle utilisé pour envoyer la ville à la vue.
     * @return la vue détail si la ville est trouvée, sinon la page d'erreur.
     */
    @GetMapping("/{id}")
    public String showCity(@PathVariable int id, Model model) {
        Optional<City> optionalCity = cityRepository.findById(id);
        if (optionalCity.isEmpty()) {
            model.addAttribute("error", "Ville introuvable avec l'id : " + id);
            return "error";
        }

        model.addAttribute("city", optionalCity.get());
        return "city/detail";
    }

    /**
     * Affiche le formulaire de création d'une nouvelle ville.
     *
     * @param model le modèle contenant les objets nécessaires à la vue (ville vide, pays).
     * @return la vue du formulaire de création de ville.
     */
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("city", new City());
        model.addAttribute("countries", countryRepository.findAll());
        return "city/new";
    }

    /**
     * Enregistre une nouvelle ville dans la base de données.
     *
     * @param city l'objet City à enregistrer.
     * @return une redirection vers la liste des villes.
     */
    @PostMapping
    public String createCity(@ModelAttribute City city) {
        cityRepository.save(city);
        return "redirect:/city";
    }

    /**
     * Affiche le formulaire de modification d'une ville existante.
     *
     * @param id    l'identifiant de la ville à modifier.
     * @param model le modèle contenant la ville et la liste des pays.
     * @return la vue du formulaire de modification ou une vue d'erreur si la ville n'est pas trouvée.
     */
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        Optional<City> optionalCity = cityRepository.findById(id);
        if (optionalCity.isEmpty()) {
            model.addAttribute("error", "Ville introuvable avec l'id : " + id);
            return "error";
        }

        model.addAttribute("city", optionalCity.get());
        model.addAttribute("countries", countryRepository.findAll());
        return "city/edit";
    }

    /**
     * Met à jour les informations d'une ville existante.
     *
     * @param id          l'identifiant de la ville à mettre à jour.
     * @param cityDetails les nouvelles informations de la ville.
     * @return une redirection vers la liste des villes ou une vue d'erreur si la ville n'existe pas.
     */
    @PostMapping("/edit/{id}")
    public String updateCity(@PathVariable int id, @ModelAttribute City cityDetails) {
        Optional<City> optionalCity = cityRepository.findById(id);
        if (optionalCity.isEmpty()) {
            return "redirect:/error?message=Ville%20introuvable%20avec%20l'id%20" + id;
        }

        City city = optionalCity.get();
        city.setCityCode(cityDetails.getCityCode());
        city.setCityName(cityDetails.getCityName());
        city.setCountry(cityDetails.getCountry());
        cityRepository.save(city);

        return "redirect:/city";
    }

    /**
     * Supprime une ville de la base de données.
     *
     * @param id l'identifiant de la ville à supprimer.
     * @return une redirection vers la liste des villes ou une vue d'erreur si la ville est introuvable.
     */
    @PostMapping("/delete/{id}")
    public String deleteCity(@PathVariable int id) {
        Optional<City> optionalCity = cityRepository.findById(id);
        if (optionalCity.isEmpty()) {
            return "redirect:/error?message=Ville%20introuvable%20avec%20l'id%20" + id;
        }

        cityRepository.delete(optionalCity.get());
        return "redirect:/city";
    }
}