package tn.esprit.spring.kaddem.controllers;

import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import tn.esprit.spring.kaddem.entities.Departement;
import tn.esprit.spring.kaddem.entities.Universite;
import tn.esprit.spring.kaddem.services.IUniversiteService;

import java.util.List;
import java.util.Set;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

@RestController
@AllArgsConstructor
@RequestMapping("/universite")
public class UniversiteRestController {

	private static final Logger logger = LoggerFactory.getLogger(UniversiteRestController.class);

	@Autowired
	IUniversiteService universiteService;
	// http://localhost:8089/Kaddem/universite/retrieve-all-universites
	@GetMapping("/retrieve-all-universites")
	public List<Universite> getUniversites() {
		logger.debug("Requête GET pour récupérer toutes les universités (DEBUG)");
		logger.info("Requête GET pour récupérer toutes les universités (INFO)");
		logger.warn("Requête GET pour récupérer toutes les universités (WARN)");
		logger.error("Requête GET pour récupérer toutes les universités (ERROR)");
		List<Universite> listUniversites = universiteService.retrieveAllUniversites();
		return listUniversites;
	}
	// http://localhost:8089/Kaddem/universite/retrieve-universite/8
	@GetMapping("/retrieve-universite/{universite-id}")
	public Universite retrieveUniversite(@PathVariable("universite-id") Integer universiteId) {
		try {
			Universite universite = universiteService.retrieveUniversite(universiteId);
			if (universite == null) {
				logger.error("L'université avec l'id {} n'a pas été trouvée (ERROR)", universiteId);
			} else {
				logger.info("Requête GET pour récupérer l'université avec l'id : {} (INFO)", universiteId);
			}
			return universite;
		} catch (Exception e) {
			logger.error("Une erreur s'est produite lors de la récupération de l'université avec l'id {} (ERROR) : {}", universiteId, e.getMessage());
			// Vous pouvez également logger l'exception complète si nécessaire : logger.error("Erreur complète : ", e);
			return null;
		}
	}

	// http://localhost:8089/Kaddem/universite/add-universite
	@PostMapping("/add-universite")
	public Universite addUniversite(@RequestBody Universite u) {
		logger.debug("Requête POST pour ajouter une université : {} (DEBUG)", u.getNomUniv());
		logger.info("Requête POST pour ajouter une université : {} (INFO)", u.getNomUniv());
		logger.error("Requête POST pour ajouter une université : {} (ERROR)", u.getNomUniv());
		Universite universite = universiteService.addUniversite(u);
		return universite;
	}

	// http://localhost:8089/Kaddem/universite/remove-universite/1
	@DeleteMapping("/remove-universite/{universite-id}")
	public void removeUniversite(@PathVariable("universite-id") Integer universiteId) {
		logger.debug("Requête DELETE pour supprimer l'université avec l'id : {} (DEBUG)", universiteId);
		logger.info("Requête DELETE pour supprimer l'université avec l'id : {} (INFO)", universiteId);
		logger.warn("Requête DELETE pour supprimer l'université avec l'id : {} (WARN)", universiteId);
		logger.error("Requête DELETE pour supprimer l'université avec l'id : {} (ERROR)", universiteId);
		universiteService.deleteUniversite(universiteId);
	}

	// http://localhost:8089/Kaddem/universite/update-universite
	@PutMapping("/update-universite")
	public Universite updateUniversite(@RequestBody Universite u) {
		logger.debug("Requête PUT pour modifier l'université avec l'id : {} (DEBUG)", u.getNomUniv());
		logger.info("Requête PUT pour modifier l'université avec l'id : {} (INFO)", u.getNomUniv());
		logger.error("Requête PUT pour modifier l'université avec l'id : {} (ERROR)", u.getNomUniv());
		Universite u1= universiteService.updateUniversite(u);
		return u1;
	}

	//@PutMapping("/affecter-etudiant-departement")
	@PutMapping(value="/affecter-universite-departement/{universiteId}/{departementId}")
	public void affectertUniversiteToDepartement(@PathVariable("universiteId") Integer universiteId, @PathVariable("departementId")Integer departementId){
		try {
			logger.debug("Requête PUT pour affecter l'université avec l'id : {} au département dont l'id est : {} (DEBUG)", universiteId, departementId);
			logger.info("Requête PUT pour affecter l'université avec l'id : {} au département dont l'id est : {} (INFO)", universiteId, departementId);
			logger.error("Requête PUT pour affecter l'université avec l'id : {} au département dont l'id est : {} (ERROR)", universiteId, departementId);
			universiteService.assignUniversiteToDepartement(universiteId, departementId);
		} catch (Exception e) {
			logger.error("Une erreur s'est produite lors de l'affectation de l'université avec l'id {} au département avec l'id {} (ERROR) : {}", universiteId, departementId, e.getMessage());
			// Vous pouvez également logger l'exception complète si nécessaire : logger.error("Erreur complète : ", e);
		}
	}

	@GetMapping(value = "/listerDepartementsUniversite/{idUniversite}")
	public Set<Departement> listerDepartementsUniversite(@PathVariable("idUniversite") Integer idUniversite) {
		logger.debug("Requête GET pour récupérer la liste des départements de l'université avec l'id : {} (DEBUG)", idUniversite);
		logger.info("Requête GET pour récupérer la liste des départements de l'université avec l'id : {} (INFO)", idUniversite);
		logger.error("Requête GET pour récupérer la liste des départements de l'université avec l'id : {} (ERROR)", idUniversite);
		return universiteService.retrieveDepartementsByUniversite(idUniversite);
	}

}


