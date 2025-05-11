package be.gestion.naturopathie.dimitri.controller;

import be.gestion.naturopathie.dimitri.model.Address;
import be.gestion.naturopathie.dimitri.repository.AddressRepository;
import be.gestion.naturopathie.dimitri.repository.CityRepository;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


/**

         █████╗ ██████╗ ██████╗ ██████╗ ███████╗███████╗███████╗     ██████╗████████╗██████╗ ██╗     
        ██╔══██╗██╔══██╗██╔══██╗██╔══██╗██╔════╝██╔════╝██╔════╝    ██╔════╝╚══██╔══╝██╔══██╗██║     
        ███████║██║  ██║██║  ██║██████╔╝█████╗  ███████╗███████╗    ██║        ██║   ██████╔╝██║     
        ██╔══██║██║  ██║██║  ██║██╔══██╗██╔══╝  ╚════██║╚════██║    ██║        ██║   ██╔══██╗██║     
        ██║  ██║██████╔╝██████╔╝██║  ██║███████╗███████║███████║    ╚██████╗   ██║   ██║  ██║███████╗
        ╚═╝  ╚═╝╚═════╝ ╚═════╝ ╚═╝  ╚═╝╚══════╝╚══════╝╚══════╝     ╚═════╝   ╚═╝   ╚═╝  ╚═╝╚══════╝
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 
**/



/**
 * Contrôleur pour gérer les adresses.
 * Permet l'affichage, la création, la modification et la suppression des adresses.
 */
@Controller
@RequestMapping("/address")
public class AddressController {

    @Autowired
    private AddressRepository addressRepository;

    @Autowired
    private CityRepository cityRepository;

    /**
     * Affiche la liste de toutes les adresses.
     * 
     * @param model Objet Model pour envoyer la liste des adresses à la vue.
     * @return La vue affichant la liste des adresses.
     */
    @GetMapping
    public String listAddresses(Model model) {
        model.addAttribute("addresses", addressRepository.findAll());
        return "address/list"; // templates/address/list.html
    }

    /**
     * Affiche les détails d'une adresse spécifique.
     * 
     * @param id    L'identifiant de l'adresse à afficher.
     * @param model Objet Model pour injecter l'adresse dans la vue.
     * @return La vue affichant les détails de l'adresse.
     * @throws IllegalArgumentException Si l'adresse n'est pas trouvée avec l'id spécifié.
     */
    @GetMapping("/{id}")
    public String showAddress(@PathVariable int id, Model model) {
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (optionalAddress.isPresent()) {
            model.addAttribute("address", optionalAddress.get());
            return "address/detail"; // templates/address/detail.html
        }
        model.addAttribute("error", "Adresse introuvable avec l'id : " + id);
        return "address/error"; // templates/address/error.html (à créer si nécessaire)
    }

    /**
     * Affiche le formulaire pour créer une nouvelle adresse.
     * 
     * @param model Objet Model pour injecter un nouvel objet Address dans la vue.
     * @return La vue contenant le formulaire de création d'une adresse.
     */
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("address", new Address());
        model.addAttribute("cities", cityRepository.findAll()); // Pour peupler le select des villes
        return "address/new"; // templates/address/new.html
    }

    /**
     * Traite la soumission du formulaire pour créer une nouvelle adresse.
     * 
     * @param address L'objet Address contenant les informations à enregistrer.
     * @return La redirection vers la liste des adresses après la création.
     */
    @PostMapping
    public String createAddress(@ModelAttribute Address address) {
        addressRepository.save(address);
        return "redirect:/address"; // Redirection vers la liste des adresses
    }

    /**
     * Affiche le formulaire pour éditer une adresse existante.
     * 
     * @param id    L'identifiant de l'adresse à modifier.
     * @param model Objet Model pour injecter l'adresse existante dans la vue.
     * @return La vue contenant le formulaire de modification d'adresse.
     * @throws IllegalArgumentException Si l'adresse n'est pas trouvée avec l'id spécifié.
     */
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (optionalAddress.isPresent()) {
            model.addAttribute("address", optionalAddress.get());
            model.addAttribute("cities", cityRepository.findAll());
            return "address/edit"; // templates/address/edit.html
        }
        model.addAttribute("error", "Adresse introuvable avec l'id : " + id);
        return "address/error"; // templates/address/error.html (à créer si nécessaire)
    }

    /**
     * Traite la soumission du formulaire pour mettre à jour une adresse existante.
     * 
     * @param id             L'identifiant de l'adresse à mettre à jour.
     * @param addressDetails L'objet Address contenant les nouveaux détails à mettre à jour.
     * @return La redirection vers la liste des adresses après la mise à jour.
     * @throws IllegalArgumentException Si l'adresse n'est pas trouvée avec l'id spécifié.
     */
    @PostMapping("/edit/{id}")
    public String updateAddress(@PathVariable int id, @ModelAttribute Address addressDetails, Model model) {
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (optionalAddress.isPresent()) {
            Address existingAddress = optionalAddress.get();
            existingAddress.setStreet(addressDetails.getStreet());
            existingAddress.setNumber(addressDetails.getNumber());
            existingAddress.setCity(addressDetails.getCity());
            addressRepository.save(existingAddress);
            return "redirect:/address"; // Redirection vers la liste des adresses
        }
        model.addAttribute("error", "Adresse introuvable avec l'id : " + id);
        return "address/error"; // templates/address/error.html (à créer si nécessaire)
    }

    /**
     * Supprime une adresse existante.
     * 
     * @param id L'identifiant de l'adresse à supprimer.
     * @return La redirection vers la liste des adresses après la suppression.
     * @throws IllegalArgumentException Si l'adresse n'est pas trouvée avec l'id spécifié.
     */
    @PostMapping("/delete/{id}")
    public String deleteAddress(@PathVariable int id, Model model) {
        Optional<Address> optionalAddress = addressRepository.findById(id);
        if (optionalAddress.isPresent()) {
            Address address = optionalAddress.get();
            addressRepository.delete(address);
            return "redirect:/address"; // Redirection vers la liste des adresses
        }
        model.addAttribute("error", "Adresse introuvable avec l'id : " + id);
        return "address/error"; // templates/address/error.html (à créer si nécessaire)
    }
}