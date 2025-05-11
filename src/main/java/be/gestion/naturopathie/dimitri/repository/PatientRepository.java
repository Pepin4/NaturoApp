package be.gestion.naturopathie.dimitri.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import be.gestion.naturopathie.dimitri.model.Patient;


/**                                                                                                  

        ██████╗  █████╗ ████████╗██╗███████╗███╗   ██╗████████╗    ██████╗ ███████╗██████╗ 
        ██╔══██╗██╔══██╗╚══██╔══╝██║██╔════╝████╗  ██║╚══██╔══╝    ██╔══██╗██╔════╝██╔══██╗
        ██████╔╝███████║   ██║   ██║█████╗  ██╔██╗ ██║   ██║       ██████╔╝█████╗  ██████╔╝
        ██╔═══╝ ██╔══██║   ██║   ██║██╔══╝  ██║╚██╗██║   ██║       ██╔══██╗██╔══╝  ██╔═══╝ 
        ██║     ██║  ██║   ██║   ██║███████╗██║ ╚████║   ██║       ██║  ██║███████╗██║     
        ╚═╝     ╚═╝  ╚═╝   ╚═╝   ╚═╝╚══════╝╚═╝  ╚═══╝   ╚═╝       ╚═╝  ╚═╝╚══════╝╚═╝     
                                                                                                                                                                                                           
**/



/**
 * Repository pour gérer les opérations de persistance de l'entité Patient.
 * Cette interface étend JpaRepository, qui fournit des méthodes CRUD (Create, Read, Update, Delete)
 */
@Repository
public interface PatientRepository extends JpaRepository<Patient, Integer> {

	/**
     * Recherche des patients dont le prénom ou le nom contient la chaîne de caractères passée en paramètre.
     * Cette méthode permet de faire une recherche partielle sur les prénoms et noms des patients.
     * 
     * @param query le prénom ou nom à rechercher dans le prénom des patients.
     * @param query2 l'autre prénom ou nom à rechercher dans le nom des patients.
     * @return une liste de patients dont le prénom ou le nom contient les chaînes de caractères spécifiées.
     */
    List<Patient> findByFirstNameContainingOrLastNameContaining(String query, String query2);
}