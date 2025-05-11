package be.gestion.naturopathie.dimitri.repository;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import be.gestion.naturopathie.dimitri.model.City;
import be.gestion.naturopathie.dimitri.model.Country;


/**                                                                                                  

         ██████╗██╗████████╗██╗   ██╗    ██████╗ ███████╗██████╗ 
        ██╔════╝██║╚══██╔══╝╚██╗ ██╔╝    ██╔══██╗██╔════╝██╔══██╗
        ██║     ██║   ██║    ╚████╔╝     ██████╔╝█████╗  ██████╔╝
        ██║     ██║   ██║     ╚██╔╝      ██╔══██╗██╔══╝  ██╔═══╝ 
        ╚██████╗██║   ██║      ██║       ██║  ██║███████╗██║     
         ╚═════╝╚═╝   ╚═╝      ╚═╝       ╚═╝  ╚═╝╚══════╝╚═╝     
                                                                                                                                                                                   
**/



/**
 * Repository pour gérer les opérations de persistance de l'entité City.
 * Cette interface étend JpaRepository, qui fournit des méthodes CRUD (Create, Read, Update, Delete)
 */
@Repository
public interface CityRepository extends JpaRepository<City, Integer> {

	/**
     * Recherche une ville par son code et son nom.
     *
     * @param cityCode le code postal ou administratif de la ville.
     * @param cityName le nom de la ville.
     * @return la ville correspondante si elle existe, sinon null.
     */
    @Query("SELECT c FROM City c WHERE c.cityCode = :cityCode AND c.cityName = :cityName")
    City findByCityCodeAndCityName(@Param("cityCode") String cityCode, 
                                   @Param("cityName") String cityName);

    /**
     * Recherche une ville par nom, code et pays associé.
     * Utile pour s'assurer qu'une ville est bien unique dans un contexte de pays.
     *
     * @param cityName le nom de la ville.
     * @param cityCode le code de la ville.
     * @param country le pays auquel la ville appartient.
     * @return un Optional contenant la ville si elle existe, ou vide sinon.
     */
    Optional<City> findByCityNameAndCityCodeAndCountry(String cityName, String cityCode, Country country);
}