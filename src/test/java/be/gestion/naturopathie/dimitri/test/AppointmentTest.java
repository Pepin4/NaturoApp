package be.gestion.naturopathie.dimitri.test;

import be.gestion.naturopathie.dimitri.model.*;
import org.junit.jupiter.api.*;

import static org.junit.jupiter.api.Assertions.*;

import java.util.Date;

/**
 * Test unitaire CRUD pour la classe Appointment
 */
public class AppointmentTest {

    private Appointment appointment;
    private Patient patient;
    private User user;
    private Address address;
    private Contact contact;
    private City city;
    private Country country;

    @BeforeEach
    public void setUp() {
        // Création d'un objet Country
        country = new Country("Belgique", "BE");

        // Création d'un objet City
        city = new City("5000", "Namur", country);

        // Création d'une adresse
        address = new Address("42", "Rue des Lilas", city);

        // Création d'un contact
        contact = new Contact("0123456789", "contact@exemple.com");

        // Création d'un patient avec une adresse et un contact
        patient = new Patient(
                new Date(), // date de naissance
                "M",        // genre
                "Jean",
                "Dupont",
                "Marié",
                "Employé",
                address,
                contact
        );

        // Création d'un utilisateur
        user = new User(
                "therapeute1",
                "motdepasse",
                address,
                contact
        );

        // Création du rendez-vous
        appointment = new Appointment();
        appointment.setDateTime(new Date());
        appointment.setSubject("Consultation nutrition");
        appointment.setNote("Patient suit déjà un régime spécifique.");
        appointment.setEatingHabits("3 repas/jour, peu de légumes.");
        appointment.setTheExcesses("Café, sucre");
        appointment.setPhysicalActivity("Marche 2x/semaine");
        appointment.setSleepQuality("Sommeil léger");
        appointment.setTransit("Constipation");
        appointment.setBloodCirculation("Normale");
        appointment.setRespiratoryCapacity("Bonne");
        appointment.setStressReaction("Irritabilité");
        appointment.setPatient(patient);
        appointment.setUser(user);
    }

    @AfterEach
    public void tearDown() {
        // Libération des objets créés pour le test
        appointment = null;
        patient = null;
        user = null;
        address = null;
        contact = null;
        city = null;
        country = null;
    }

    @Test
    @DisplayName("CREATE - Création d'un rendez-vous")
    public void testCreateAppointment() {
        // Vérifie que l'objet rendez-vous est créé correctement
        assertNotNull(appointment);
        assertNotNull(appointment.getDateTime());
        assertEquals("Consultation nutrition", appointment.getSubject());
        assertEquals("Patient suit déjà un régime spécifique.", appointment.getNote());
        assertEquals("Café, sucre", appointment.getTheExcesses());
        assertEquals(patient, appointment.getPatient());
        assertEquals(user, appointment.getUser());

        // Vérification des données liées à l'adresse et au contact du patient
        assertEquals("Rue des Lilas", appointment.getPatient().getAddress().getStreet());
        assertEquals("Namur", appointment.getPatient().getAddress().getCity().getCityName());
        assertEquals("Belgique", appointment.getPatient().getAddress().getCity().getCountry().getCountryName());
    }

    @Test
    @DisplayName("READ - Lecture des champs du rendez-vous")
    public void testReadAppointmentFields() {
        // Vérifie si les valeurs des champs sont lues correctement
        assertEquals("3 repas/jour, peu de légumes.", appointment.getEatingHabits());
        assertEquals("Bonne", appointment.getRespiratoryCapacity());
        assertEquals("Marche 2x/semaine", appointment.getPhysicalActivity());
        assertEquals("Dupont", appointment.getPatient().getLastName());
        assertEquals("contact@exemple.com", appointment.getUser().getContact().getEmail());
    }

    @Test
    @DisplayName("UPDATE - Mise à jour des champs du rendez-vous")
    public void testUpdateAppointment() {
        // Mise à jour de certains champs du rendez-vous
        appointment.setSubject("Suivi naturopathique");
        appointment.setNote("Le patient a amélioré son alimentation.");
        appointment.setSleepQuality("Très bon");

        // Vérification de la mise à jour des champs
        assertEquals("Suivi naturopathique", appointment.getSubject());
        assertEquals("Le patient a amélioré son alimentation.", appointment.getNote());
        assertEquals("Très bon", appointment.getSleepQuality());
    }

    @Test
    @DisplayName("DELETE - Suppression logique des données")
    public void testDeleteAppointmentFields() {
        // Suppression logique des champs du rendez-vous (mettre à null)
        appointment.setSubject(null);
        appointment.setNote(null);
        appointment.setPatient(null);
        appointment.setUser(null);

        // Vérification que les champs sont bien supprimés (mis à null)
        assertNull(appointment.getSubject());
        assertNull(appointment.getNote());
        assertNull(appointment.getPatient());
        assertNull(appointment.getUser());
    }
}
