package be.gestion.naturopathie.dimitri.controller;

import be.gestion.naturopathie.dimitri.model.Contact;
import be.gestion.naturopathie.dimitri.repository.ContactRepository;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


/**

         ██████╗ ██████╗ ███╗   ██╗████████╗ █████╗  ██████╗████████╗     ██████╗████████╗██████╗ ██╗     
        ██╔════╝██╔═══██╗████╗  ██║╚══██╔══╝██╔══██╗██╔════╝╚══██╔══╝    ██╔════╝╚══██╔══╝██╔══██╗██║     
        ██║     ██║   ██║██╔██╗ ██║   ██║   ███████║██║        ██║       ██║        ██║   ██████╔╝██║     
        ██║     ██║   ██║██║╚██╗██║   ██║   ██╔══██║██║        ██║       ██║        ██║   ██╔══██╗██║     
        ╚██████╗╚██████╔╝██║ ╚████║   ██║   ██║  ██║╚██████╗   ██║       ╚██████╗   ██║   ██║  ██║███████╗
         ╚═════╝ ╚═════╝ ╚═╝  ╚═══╝   ╚═╝   ╚═╝  ╚═╝ ╚═════╝   ╚═╝        ╚═════╝   ╚═╝   ╚═╝  ╚═╝╚══════╝
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                    
**/



/**
 * Contrôleur pour gérer les contacts.
 * Permet l'affichage, la création, la modification et la suppression des contacts.
 */
@Controller
@RequestMapping("/contact")
public class ContactController {

    @Autowired
    private ContactRepository contactRepository;

    /**
     * Affiche la liste des contacts.
     *
     * @param model Le modèle Spring utilisé pour passer les données à la vue.
     * @return La vue affichant la liste des contacts.
     */
    @GetMapping
    public String listContacts(Model model) {
        List<Contact> contacts = contactRepository.findAll();
        model.addAttribute("contacts", contacts);
        return "contact/list"; // templates/contact/list.html
    }

    /**
     * Affiche les détails d'un contact.
     *
     * @param id    L'identifiant du contact à afficher.
     * @param model Le modèle Spring utilisé pour passer les données à la vue.
     * @return La vue affichant les détails du contact.
     */
    @GetMapping("/{id}")
    public String showContact(@PathVariable int id, Model model) {
        Optional<Contact> optionalContact = contactRepository.findById(id);

        // Gestion du cas où le contact n'existe pas
        if (optionalContact.isEmpty()) {
            model.addAttribute("error", "Contact introuvable avec l'id : " + id);
            return "error"; // Redirige vers une page d'erreur ou affiche un message d'erreur
        }

        Contact contact = optionalContact.get();
        model.addAttribute("contact", contact);
        return "contact/detail"; // templates/contact/detail.html
    }

    /**
     * Affiche le formulaire pour créer un nouveau contact.
     *
     * @param model Le modèle Spring utilisé pour passer les données à la vue.
     * @return La vue contenant le formulaire de création.
     */
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("contact", new Contact());
        return "contact/new"; // templates/contact/new.html
    }

    /**
     * Crée un nouveau contact.
     *
     * @param contact Les détails du contact à créer.
     * @return La redirection vers la liste des contacts après la création.
     */
    @PostMapping
    public String createContact(@ModelAttribute Contact contact) {
        contactRepository.save(contact);
        return "redirect:/contact";
    }

    /**
     * Affiche le formulaire pour éditer un contact existant.
     *
     * @param id    L'identifiant du contact à éditer.
     * @param model Le modèle Spring utilisé pour passer les données à la vue.
     * @return La vue contenant le formulaire d'édition.
     */
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        Optional<Contact> optionalContact = contactRepository.findById(id);

        // Gestion du cas où le contact n'existe pas
        if (optionalContact.isEmpty()) {
            model.addAttribute("error", "Contact introuvable avec l'id : " + id);
            return "error"; // Redirige vers une page d'erreur ou affiche un message d'erreur
        }

        Contact contact = optionalContact.get();
        model.addAttribute("contact", contact);
        return "contact/edit"; // templates/contact/edit.html
    }

    /**
     * Met à jour un contact existant.
     *
     * @param id           L'identifiant du contact à modifier.
     * @param contactDetails Les nouvelles informations du contact.
     * @return La redirection vers la liste des contacts après la mise à jour.
     */
    @PostMapping("/edit/{id}")
    public String updateContact(@PathVariable int id, @ModelAttribute Contact contactDetails) {
        Optional<Contact> optionalContact = contactRepository.findById(id);

        // Gestion du cas où le contact n'existe pas
        if (optionalContact.isEmpty()) {
            return "redirect:/error?message=Contact%20introuvable%20avec%20l'id%20" + id;
        }

        Contact contact = optionalContact.get();
        contact.setPhoneNumber(contactDetails.getPhoneNumber());
        contact.setEmail(contactDetails.getEmail());
        contactRepository.save(contact);

        return "redirect:/contact";
    }

    /**
     * Supprime un contact.
     *
     * @param id L'identifiant du contact à supprimer.
     * @return La redirection vers la liste des contacts après la suppression.
     */
    @PostMapping("/delete/{id}")
    public String deleteContact(@PathVariable int id) {
        Optional<Contact> optionalContact = contactRepository.findById(id);

        // Gestion du cas où le contact n'existe pas
        if (optionalContact.isEmpty()) {
            return "redirect:/error?message=Contact%20introuvable%20avec%20l'id%20" + id;
        }

        Contact contact = optionalContact.get();
        contactRepository.delete(contact);

        return "redirect:/contact";
    }
}