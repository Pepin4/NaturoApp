package be.gestion.naturopathie.dimitri.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import be.gestion.naturopathie.dimitri.model.Address;
import be.gestion.naturopathie.dimitri.model.City;


/**                                                                                                  

         █████╗ ██████╗ ██████╗ ██████╗ ███████╗███████╗███████╗    ██████╗ ███████╗██████╗ 
        ██╔══██╗██╔══██╗██╔══██╗██╔══██╗██╔════╝██╔════╝██╔════╝    ██╔══██╗██╔════╝██╔══██╗
        ███████║██║  ██║██║  ██║██████╔╝█████╗  ███████╗███████╗    ██████╔╝█████╗  ██████╔╝
        ██╔══██║██║  ██║██║  ██║██╔══██╗██╔══╝  ╚════██║╚════██║    ██╔══██╗██╔══╝  ██╔═══╝ 
        ██║  ██║██████╔╝██████╔╝██║  ██║███████╗███████║███████║    ██║  ██║███████╗██║     
        ╚═╝  ╚═╝╚═════╝ ╚═════╝ ╚═╝  ╚═╝╚══════╝╚══════╝╚══════╝    ╚═╝  ╚═╝╚══════╝╚═╝     
                                                                                                                                                                                                          
**/



/**
 * Repository pour gérer les opérations de persistance de l'entité Address.
 * Cette interface étend JpaRepository, qui fournit des méthodes CRUD (Create, Read, Update, Delete)
 */
@Repository
public interface AddressRepository extends JpaRepository<Address, Integer> {

	/**
     * Recherche une adresse en fonction du numéro et de la rue.
     *
     * @param addressNumber le numéro de l'adresse à rechercher.
     * @param addressStreet le nom de la rue de l'adresse à rechercher.
     * @return l'adresse correspondante si elle est trouvée, sinon null.
     */
    @Query("SELECT a FROM Address a WHERE a.number = :addressNumber AND a.street = :addressStreet")
    Address findByNumberAndStreet(@Param("addressNumber") String addressNumber, 
                                  @Param("addressStreet") String addressStreet);

    /**
     * Recherche une adresse par la combinaison exacte de la rue, du numéro et de la ville.
     *
     * @param street le nom de la rue.
     * @param number le numéro de la maison.
     * @param city la ville associée à l'adresse.
     * @return l'adresse trouvée ou null si aucune correspondance.
     */
    Address findByStreetAndNumberAndCity(String street, String number, City city);

    /**
     * Recherche une adresse par la rue et la ville (sans tenir compte du numéro).
     *
     * @param street le nom de la rue.
     * @param city la ville associée à l'adresse.
     * @return un Optional contenant l'adresse si trouvée, ou vide sinon.
     */
    Optional<Address> findByStreetAndCity(String street, City city);

}
