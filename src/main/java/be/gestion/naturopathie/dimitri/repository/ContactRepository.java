package be.gestion.naturopathie.dimitri.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import be.gestion.naturopathie.dimitri.model.Contact;


/**                                                                                                  

         ██████╗ ██████╗ ███╗   ██╗████████╗ █████╗  ██████╗████████╗    ██████╗ ███████╗██████╗ 
        ██╔════╝██╔═══██╗████╗  ██║╚══██╔══╝██╔══██╗██╔════╝╚══██╔══╝    ██╔══██╗██╔════╝██╔══██╗
        ██║     ██║   ██║██╔██╗ ██║   ██║   ███████║██║        ██║       ██████╔╝█████╗  ██████╔╝
        ██║     ██║   ██║██║╚██╗██║   ██║   ██╔══██║██║        ██║       ██╔══██╗██╔══╝  ██╔═══╝ 
        ╚██████╗╚██████╔╝██║ ╚████║   ██║   ██║  ██║╚██████╗   ██║       ██║  ██║███████╗██║     
         ╚═════╝ ╚═════╝ ╚═╝  ╚═══╝   ╚═╝   ╚═╝  ╚═╝ ╚═════╝   ╚═╝       ╚═╝  ╚═╝╚══════╝╚═╝     
                                                                                                                                                                                                                 
**/



/**
 * Repository pour gérer les opérations de persistance de l'entité Contact.
 * Cette interface étend JpaRepository, qui fournit des méthodes CRUD (Create, Read, Update, Delete)
 */
@Repository
public interface ContactRepository extends JpaRepository<Contact, Integer> {

	/**
     * Recherche un contact par numéro de téléphone et, si présent, par email.
     * La clause OR permet de rendre le champ email optionnel dans la recherche.
     *
     * @param contactNumber le numéro de téléphone à rechercher.
     * @param contactEmail l'adresse email à rechercher (peut être null).
     * @return le contact correspondant s'il existe, sinon null.
     */
    @Query("SELECT c FROM Contact c WHERE c.phoneNumber = :contactNumber AND (:contactEmail IS NULL OR c.email = :contactEmail)")
    Contact findByPhoneNumberOrEmail(@Param("contactNumber") String contactNumber, 
                                     @Param("contactEmail") String contactEmail);

    /**
     * Recherche un contact correspondant exactement au numéro de téléphone et à l'adresse email.
     *
     * @param phoneNumber le numéro de téléphone du contact.
     * @param email l'adresse email du contact.
     * @return le contact correspondant s'il existe, sinon null.
     */
    Contact findByPhoneNumberAndEmail(String phoneNumber, String email);
}