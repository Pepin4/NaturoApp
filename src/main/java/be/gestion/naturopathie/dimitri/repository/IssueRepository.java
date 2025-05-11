package be.gestion.naturopathie.dimitri.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import be.gestion.naturopathie.dimitri.model.Issue;


/**                                                                                                  

        ██╗███████╗███████╗██╗   ██╗███████╗    ██████╗ ███████╗██████╗ 
        ██║██╔════╝██╔════╝██║   ██║██╔════╝    ██╔══██╗██╔════╝██╔══██╗
        ██║███████╗███████╗██║   ██║█████╗      ██████╔╝█████╗  ██████╔╝
        ██║╚════██║╚════██║██║   ██║██╔══╝      ██╔══██╗██╔══╝  ██╔═══╝ 
        ██║███████║███████║╚██████╔╝███████╗    ██║  ██║███████╗██║     
        ╚═╝╚══════╝╚══════╝ ╚═════╝ ╚══════╝    ╚═╝  ╚═╝╚══════╝╚═╝     
                                                                                                                                                                                            
**/



/**
 * Repository pour gérer les opérations de persistance de l'entité Issue.
 * Cette interface étend JpaRepository, qui fournit des méthodes CRUD (Create, Read, Update, Delete)
 */
@Repository
public interface IssueRepository extends JpaRepository<Issue, Integer> {

	/**
     * Recherche toutes les entités Issue dont le nom contient la chaîne de caractères passée,
     * sans tenir compte de la casse (majuscules/minuscules).
     *
     * @param query la chaîne de caractères à rechercher dans le nom des problèmes.
     * @return une liste de problèmes correspondant à la recherche.
     */
    List<Issue> findByNameContainingIgnoreCase(String query);

}
