package tn.esprit.spring.kaddem;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.entities.Universite;
import tn.esprit.spring.kaddem.repositories.DepartementRepository;
import tn.esprit.spring.kaddem.repositories.UniversiteRepository;
import tn.esprit.spring.kaddem.services.UniversiteServiceImpl;

import java.util.*;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

public class UniversiteServiceImplTest {

    @InjectMocks
    private UniversiteServiceImpl universiteService;

    @Mock
    private UniversiteRepository universiteRepository;

    @Mock
    private DepartementRepository departementRepository;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void testAddUniversite() {
        Universite universite = new Universite(1, "New Universite");

        when(universiteRepository.save(any(Universite.class))).thenReturn(universite);

        Universite result = universiteService.addUniversite(universite);

        assertNotNull(result);
        assertEquals(1, result.getIdUniv());
        assertEquals("New Universite", result.getNomUniv());

        verify(universiteRepository).save(any(Universite.class));
    }

    @Test
    void testRetrieveAllUniversites() {
        Universite universite1 = new Universite(1, "Universite 1");
        Universite universite2 = new Universite(2, "Universite 2");
        List<Universite> universiteList = Arrays.asList(universite1, universite2);

        when(universiteRepository.findAll()).thenReturn(universiteList);

        List<Universite> result = universiteService.retrieveAllUniversites();

        assertNotNull(result);
        assertEquals(2, result.size());
        assertEquals("Universite 1", result.get(0).getNomUniv());
        assertEquals("Universite 2", result.get(1).getNomUniv());

        verify(universiteRepository).findAll();
        System.out.println("Initial List of Universites: " + universiteList.get(1).getNomUniv());
        System.out.println("Result List of Universites: " + result.get(1).getNomUniv());
        System.out.println("testRetrieveAllUniversites: PASSED");
    }

    @Test
    void testRetrieveUniversite() {
        Universite universite = new Universite(1, "Universite 1");

        when(universiteRepository.findById(1)).thenReturn(Optional.of(universite));

        Universite result = universiteService.retrieveUniversite(1);

        assertNotNull(result);
        assertEquals(1, result.getIdUniv());
        assertEquals("Universite 1", result.getNomUniv());

        verify(universiteRepository).findById(1);
        System.out.println("Universites: " + result.getNomUniv());

        System.out.println("testRetrieveUniversite: PASSED");
    }

    @Test
    void testUpdateUniversite() {
        Universite universite = new Universite(1, "Updated Universite");

        when(universiteRepository.save(any(Universite.class))).thenReturn(universite);

        Universite result = universiteService.updateUniversite(universite);

        assertNotNull(result);
        assertEquals(1, result.getIdUniv());
        assertEquals("Updated Universite", result.getNomUniv());

        verify(universiteRepository).save(any(Universite.class));

    }

    @Test
    void testDeleteUniversite() {
        Universite universite = new Universite(1, "Universite to delete");

        when(universiteRepository.findById(1)).thenReturn(Optional.of(universite));

        universiteService.deleteUniversite(1);

        verify(universiteRepository).delete(universite);
    }

    @Test
    void testRetrieveDepartementsByUniversite() {
        Universite universite = new Universite(1, "Universite 1");
        Departement departement1 = new Departement(1, "Departement 1");
        Departement departement2 = new Departement(2, "Departement 2");
        Set<Departement> universityDepartments = new HashSet<>();
        universityDepartments.add(departement1);
        universityDepartments.add(departement2);
        universite.setDepartements(universityDepartments);

        when(universiteRepository.findById(1)).thenReturn(Optional.of(universite));

        Set<Departement> result = universiteService.retrieveDepartementsByUniversite(1);

        assertNotNull(result);
        assertEquals(2, result.size());
        assertTrue(result.contains(departement1));
        assertTrue(result.contains(departement2));

        verify(universiteRepository).findById(1);
    }

    @Test
    void testAssignUniversiteToDepartement() {
        Universite universite = new Universite(1, "Universite 1");
        Departement departement = new Departement(1, "Departement 1");

        when(universiteRepository.findById(1)).thenReturn(Optional.of(universite));
        when(departementRepository.findById(1)).thenReturn(Optional.empty());

        assertThrows(NullPointerException.class, () -> universiteService.assignUniversiteToDepartement(1, 1));

        verify(universiteRepository).findById(1);
        verify(departementRepository).findById(1);
    }
}
