package be.gestion.naturopathie.dimitri.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import be.gestion.naturopathie.dimitri.model.IssueSolution;


/**                                                                                                  

        ██╗███████╗███████╗██╗   ██╗███████╗███████╗ ██████╗ ██╗     ██╗   ██╗████████╗██╗ ██████╗ ███╗   ██╗    ██████╗ ███████╗██████╗ 
        ██║██╔════╝██╔════╝██║   ██║██╔════╝██╔════╝██╔═══██╗██║     ██║   ██║╚══██╔══╝██║██╔═══██╗████╗  ██║    ██╔══██╗██╔════╝██╔══██╗
        ██║███████╗███████╗██║   ██║█████╗  ███████╗██║   ██║██║     ██║   ██║   ██║   ██║██║   ██║██╔██╗ ██║    ██████╔╝█████╗  ██████╔╝
        ██║╚════██║╚════██║██║   ██║██╔══╝  ╚════██║██║   ██║██║     ██║   ██║   ██║   ██║██║   ██║██║╚██╗██║    ██╔══██╗██╔══╝  ██╔═══╝ 
        ██║███████║███████║╚██████╔╝███████╗███████║╚██████╔╝███████╗╚██████╔╝   ██║   ██║╚██████╔╝██║ ╚████║    ██║  ██║███████╗██║     
        ╚═╝╚══════╝╚══════╝ ╚═════╝ ╚══════╝╚══════╝ ╚═════╝ ╚══════╝ ╚═════╝    ╚═╝   ╚═╝ ╚═════╝ ╚═╝  ╚═══╝    ╚═╝  ╚═╝╚══════╝╚═╝     
                                                                                                                                                                                                                                                         
**/



/**
 * Repository pour gérer les opérations de persistance de l'entité IssueSolution.
 * Cette interface étend JpaRepository, qui fournit des méthodes CRUD (Create, Read, Update, Delete)
 */
@Repository
public interface IssueSolutionRepository extends JpaRepository<IssueSolution, Integer> {

	/**
     * Récupère toutes les associations IssueSolution liées à un identifiant de problème spécifique.
     * Utilise une requête SQL native pour accéder directement à la table "issue_solution".
     *
     * @param issueId l'identifiant du problème (Issue).
     * @return une liste d'associations (IssueSolution) correspondant à ce problème.
     */
    @Query(value = "SELECT * FROM issue_solution WHERE fk_issue = :issueId", nativeQuery = true)
    List<IssueSolution> findByIssueId(@Param("issueId") int issueId);

    /**
     * Recherche une association spécifique entre un problème et une solution via leurs IDs respectifs.
     * Utilise une requête SQL native pour une recherche directe dans la table "issue_solution".
     *
     * @param issueId l'identifiant du problème (Issue).
     * @param solutionId l'identifiant de la solution (Solution).
     * @return une liste contenant l'association correspondante, ou vide si non trouvée.
     */
    @Query(value = "SELECT * FROM issue_solution WHERE fk_issue = :issueId AND fk_solution = :solutionId", nativeQuery = true)
    List<IssueSolution> findByIssueAndSolution(@Param("issueId") Integer issueId, @Param("solutionId") int solutionId);
}