package be.gestion.naturopathie.dimitri.repository;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import be.gestion.naturopathie.dimitri.model.Supplier;


/**                                                                                                  

        ███████╗██╗   ██╗██████╗ ██████╗ ██╗     ██╗███████╗██████╗     ██████╗ ███████╗██████╗ 
        ██╔════╝██║   ██║██╔══██╗██╔══██╗██║     ██║██╔════╝██╔══██╗    ██╔══██╗██╔════╝██╔══██╗
        ███████╗██║   ██║██████╔╝██████╔╝██║     ██║█████╗  ██████╔╝    ██████╔╝█████╗  ██████╔╝
        ╚════██║██║   ██║██╔═══╝ ██╔═══╝ ██║     ██║██╔══╝  ██╔══██╗    ██╔══██╗██╔══╝  ██╔═══╝ 
        ███████║╚██████╔╝██║     ██║     ███████╗██║███████╗██║  ██║    ██║  ██║███████╗██║     
        ╚══════╝ ╚═════╝ ╚═╝     ╚═╝     ╚══════╝╚═╝╚══════╝╚═╝  ╚═╝    ╚═╝  ╚═╝╚══════╝╚═╝     
                                                                                                                                                                                                             
**/



/**
 * Repository pour gérer les opérations de persistance de l'entité Supplier.
 * Cette interface étend JpaRepository, qui fournit des méthodes CRUD (Create, Read, Update, Delete)
 */
@Repository
public interface SupplierRepository extends JpaRepository<Supplier, Integer> {

	/**
     * Recherche un fournisseur en fonction du nom exact du fournisseur.
     *
     * @param supplierName le nom du fournisseur à rechercher.
     * @return le fournisseur correspondant si trouvé, sinon null.
     */
    @Query("SELECT s FROM Supplier s WHERE s.supplierName = :supplierName")
    Supplier findByName(@Param("supplierName") String supplierName);

    /**
     * Recherche une liste de fournisseurs dont le nom contient la chaîne spécifiée, 
     * en ignorant la casse.
     *
     * @param supplierName le nom partiel du fournisseur à rechercher.
     * @return une liste de fournisseurs dont le nom contient la chaîne spécifiée.
     */
    List<Supplier> findBySupplierNameContainingIgnoreCase(String supplierName);
}