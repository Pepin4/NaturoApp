package be.gestion.naturopathie.dimitri.test;

import be.gestion.naturopathie.dimitri.model.Address;
import be.gestion.naturopathie.dimitri.model.City;
import be.gestion.naturopathie.dimitri.model.Country;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

/**
 * Test unitaire CRUD pour la classe Address
 */
public class AddressTest {

    private Address address;
    private City city;
    private Country country;

    // Initialisation des objets avant chaque test
    @BeforeEach
    public void setUp() {
        country = new Country("Belgique", "BE");
        city = new City("5000", "Namur", country);
        address = new Address("42", "Rue des Lilas", city);
    }

    // Nettoyage des objets après chaque test
    @AfterEach
    public void tearDown() {
        address = null;
        city = null;
        country = null;
    }

    /**
     * Test de la création d'une adresse (CREATE)
     * Vérifie que l'objet address est correctement initialisé avec les bonnes valeurs.
     */
    @Test
    @DisplayName("CREATE - Vérifie la création d'une adresse")
    public void testCreateAddress() {
        // Vérifie que l'objet address a bien été créé
        assertNotNull(address);

        // Vérifie les attributs de l'adresse
        assertEquals("42", address.getNumber()); // Le numéro de la rue
        assertEquals("Rue des Lilas", address.getStreet()); // Le nom de la rue
        assertEquals("Namur", address.getCity().getCityName()); // Le nom de la ville
        assertEquals("5000", address.getCity().getCityCode()); // Le code postal de la ville
        assertEquals("Belgique", address.getCity().getCountry().getCountryName()); // Le nom du pays
    }

    /**
     * Test de la lecture des valeurs de l'adresse (READ)
     * Vérifie que les valeurs des champs de l'adresse sont correctement retournées.
     */
    @Test
    @DisplayName("READ - Vérifie la lecture des valeurs de l'adresse")
    public void testReadAddressFields() {
        // Attribue un ID à l'adresse pour simuler une lecture d'une adresse existante
        address.setId(101);

        // Vérifie que l'ID est bien retourné
        assertEquals(101, address.getId());

        // Vérifie les autres attributs de l'adresse
        assertEquals("Rue des Lilas", address.getStreet());
        assertEquals("Namur", address.getCity().getCityName());
        assertEquals("BE", address.getCity().getCountry().getIsoCode()); // Vérifie le code ISO du pays
    }

    /**
     * Test de la mise à jour des valeurs de l'adresse (UPDATE)
     * Vérifie que les valeurs de l'adresse peuvent être mises à jour correctement.
     */
    @Test
    @DisplayName("UPDATE - Vérifie la mise à jour des valeurs de l'adresse")
    public void testUpdateAddress() {
        // Crée une nouvelle ville et un nouveau pays pour la mise à jour
        Country newCountry = new Country("France", "FR");
        City newCity = new City("4000", "Liège", newCountry);

        // Met à jour les champs de l'adresse
        address.setNumber("99");
        address.setStreet("Avenue du Roi");
        address.setCity(newCity);

        // Vérifie que les valeurs mises à jour sont correctes
        assertEquals("99", address.getNumber());
        assertEquals("Avenue du Roi", address.getStreet());
        assertEquals("Liège", address.getCity().getCityName());
        assertEquals("FR", address.getCity().getCountry().getIsoCode()); // Vérifie le nouveau code ISO du pays
    }

    /**
     * Test de la suppression (mise à null) des champs de l'adresse (DELETE)
     * Vérifie que les champs de l'adresse peuvent être supprimés (réinitialisés à null).
     */
    @Test
    @DisplayName("DELETE - Vérifie la suppression (remise à null) des champs de l'adresse")
    public void testDeleteAddress() {
        // Remise à null des champs de l'adresse
        address.setNumber(null);
        address.setStreet(null);
        address.setCity(null);

        // Vérifie que les champs sont bien réinitialisés à null
        assertNull(address.getNumber());
        assertNull(address.getStreet());
        assertNull(address.getCity());
    }
}
