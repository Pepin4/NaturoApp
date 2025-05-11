package be.gestion.naturopathie.dimitri.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import be.gestion.naturopathie.dimitri.model.Solution;
import be.gestion.naturopathie.dimitri.model.Supplier;


/**                                                                                                  

        ███████╗ ██████╗ ██╗     ██╗   ██╗████████╗██╗ ██████╗ ███╗   ██╗    ██████╗ ███████╗██████╗ 
        ██╔════╝██╔═══██╗██║     ██║   ██║╚══██╔══╝██║██╔═══██╗████╗  ██║    ██╔══██╗██╔════╝██╔══██╗
        ███████╗██║   ██║██║     ██║   ██║   ██║   ██║██║   ██║██╔██╗ ██║    ██████╔╝█████╗  ██████╔╝
        ╚════██║██║   ██║██║     ██║   ██║   ██║   ██║██║   ██║██║╚██╗██║    ██╔══██╗██╔══╝  ██╔═══╝ 
        ███████║╚██████╔╝███████╗╚██████╔╝   ██║   ██║╚██████╔╝██║ ╚████║    ██║  ██║███████╗██║     
        ╚══════╝ ╚═════╝ ╚══════╝ ╚═════╝    ╚═╝   ╚═╝ ╚═════╝ ╚═╝  ╚═══╝    ╚═╝  ╚═╝╚══════╝╚═╝     
                                                                                                                                                                                                                  
**/



/**
 * Repository pour gérer les opérations de persistance de l'entité Solution.
 * Cette interface étend JpaRepository, qui fournit des méthodes CRUD (Create, Read, Update, Delete)
 */
@Repository
public interface SolutionRepository extends JpaRepository<Solution, Integer> {

    /**
     * Recherche une solution en fonction de son "remedy" et du fournisseur associé.
     * Cette méthode utilise une requête JPQL personnalisée pour récupérer la solution.
     *
     * @param remedy le nom du remède (ou solution) recherché.
     * @param supplier le fournisseur de la solution.
     * @return la solution trouvée correspondant à la combinaison des deux critères.
     */
    @Query("SELECT s FROM Solution s WHERE s.remedy = :remedy AND s.supplier = :supplier")
    Solution findByRemedyAndSupplier(@Param("remedy") String remedy, @Param("supplier") Supplier supplier);
}
