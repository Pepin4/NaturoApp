package be.gestion.naturopathie.dimitri.controller;

import be.gestion.naturopathie.dimitri.model.Supplier;
import be.gestion.naturopathie.dimitri.repository.SupplierRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.Optional;


/**

        ███████╗██╗   ██╗██████╗ ██████╗ ██╗     ██╗███████╗██████╗      ██████╗████████╗██████╗ ██╗     
        ██╔════╝██║   ██║██╔══██╗██╔══██╗██║     ██║██╔════╝██╔══██╗    ██╔════╝╚══██╔══╝██╔══██╗██║     
        ███████╗██║   ██║██████╔╝██████╔╝██║     ██║█████╗  ██████╔╝    ██║        ██║   ██████╔╝██║     
        ╚════██║██║   ██║██╔═══╝ ██╔═══╝ ██║     ██║██╔══╝  ██╔══██╗    ██║        ██║   ██╔══██╗██║     
        ███████║╚██████╔╝██║     ██║     ███████╗██║███████╗██║  ██║    ╚██████╗   ██║   ██║  ██║███████╗
        ╚══════╝ ╚═════╝ ╚═╝     ╚═╝     ╚══════╝╚═╝╚══════╝╚═╝  ╚═╝     ╚═════╝   ╚═╝   ╚═╝  ╚═╝╚══════╝
                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                           
**/



/**
 * Contrôleur pour la gestion des fournisseurs.
 * Permet l'affichage, la création, la modification et la suppression des fournisseurs.
 */
@Controller
@RequestMapping("/suppliers")
public class SupplierController {

    @Autowired
    private SupplierRepository supplierRepository;

    /**
     * Affiche la liste de tous les fournisseurs existants.
     *
     * @param model Model pour injecter la liste dans la vue.
     * @return La vue "list" contenant tous les fournisseurs.
     */
    @GetMapping
    public String listSuppliers(Model model) {
        List<Supplier> suppliers = supplierRepository.findAll();
        model.addAttribute("suppliers", suppliers);
        return "suppliers/list";
    }

    /**
     * Affiche les détails d’un fournisseur spécifique.
     *
     * @param id    L’identifiant du fournisseur.
     * @param model Model pour injecter le fournisseur ou un message d’erreur.
     * @return La vue des détails si trouvé, sinon une page d’erreur.
     */
    @GetMapping("/{id}")
    public String showSupplier(@PathVariable int id, Model model) {
        Optional<Supplier> optionalSupplier = supplierRepository.findById(id);
        if (optionalSupplier.isPresent()) {
            model.addAttribute("supplier", optionalSupplier.get());
            return "suppliers/detail";
        }
        model.addAttribute("error", "Fournisseur introuvable avec l'ID : " + id);
        return "suppliers/error";
    }

    /**
     * Affiche le formulaire de création d’un nouveau fournisseur.
     *
     * @param model Model pour injecter un objet Supplier vide dans le formulaire.
     * @return La vue du formulaire de création.
     */
    @GetMapping("/new")
    public String showCreateForm(Model model) {
        model.addAttribute("supplier", new Supplier());
        return "suppliers/new";
    }

    /**
     * Traite la création d’un nouveau fournisseur après soumission du formulaire.
     *
     * @param supplier L’objet Supplier à enregistrer.
     * @return Redirection vers la liste des fournisseurs.
     */
    @PostMapping
    public String createSupplier(@ModelAttribute Supplier supplier) {
        supplierRepository.save(supplier);
        return "redirect:/suppliers";
    }

    /**
     * Affiche le formulaire de modification pour un fournisseur existant.
     *
     * @param id    L’ID du fournisseur à modifier.
     * @param model Model pour injecter les données dans le formulaire.
     * @return La vue d’édition si trouvé, sinon une page d’erreur.
     */
    @GetMapping("/edit/{id}")
    public String showEditForm(@PathVariable int id, Model model) {
        Optional<Supplier> optionalSupplier = supplierRepository.findById(id);
        if (optionalSupplier.isPresent()) {
            model.addAttribute("supplier", optionalSupplier.get());
            return "suppliers/edit";
        }
        model.addAttribute("error", "Fournisseur introuvable pour modification.");
        return "suppliers/error";
    }

    /**
     * Met à jour les informations d’un fournisseur existant.
     *
     * @param id        L’ID du fournisseur à mettre à jour.
     * @param sDetails  Les nouvelles données à appliquer.
     * @param model     Model pour afficher un message d’erreur si besoin.
     * @return Redirection vers la liste après mise à jour, ou page d’erreur.
     */
    @PostMapping("/edit/{id}")
    public String updateSupplier(@PathVariable int id, @ModelAttribute Supplier sDetails, Model model) {
        Optional<Supplier> optionalSupplier = supplierRepository.findById(id);
        if (optionalSupplier.isPresent()) {
            Supplier existing = optionalSupplier.get();
            existing.setSupplierName(sDetails.getSupplierName());
            existing.setAddress(sDetails.getAddress());
            existing.setContact(sDetails.getContact());
            supplierRepository.save(existing);
            return "redirect:/suppliers";
        }
        model.addAttribute("error", "Impossible de mettre à jour : fournisseur introuvable.");
        return "suppliers/error";
    }
    
    /**
     * Supprime un fournisseur par son identifiant.
     *
     * @param id    L’ID du fournisseur à supprimer.
     * @param model Model pour afficher un message de confirmation ou d’erreur.
     * @return Redirection vers la liste des fournisseurs après suppression.
     */
    @PostMapping("/delete/{id}")
    public String deleteSupplier(@PathVariable int id, Model model) {
        Optional<Supplier> optionalSupplier = supplierRepository.findById(id);
        if (optionalSupplier.isPresent()) {
            supplierRepository.deleteById(id);
            return "redirect:/suppliers";
        }
        model.addAttribute("error", "Impossible de supprimer : fournisseur introuvable.");
        return "suppliers/error";
    }
}