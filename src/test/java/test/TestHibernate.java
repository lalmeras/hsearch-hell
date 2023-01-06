package test;

import java.util.Arrays;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.hibernate.cfg.AvailableSettings;
import org.igloo.jpa.test.EntityManagerFactoryExtension;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.RegisterExtension;

import entities.DossierFormation;
import entities.Entreprise;
import entities.Participation;
import entities.Ressortissant;
import entities.Stagiaire;

public class TestHibernate {

	@RegisterExtension
	EntityManagerFactoryExtension extension = new EntityManagerFactoryExtension(
			AvailableSettings.DIALECT, org.hibernate.dialect.H2Dialect.class.getName(),
			AvailableSettings.HBM2DDL_AUTO, "create",
			AvailableSettings.JPA_JDBC_DRIVER, org.h2.Driver.class.getName(),
			AvailableSettings.JPA_JDBC_URL, "jdbc:h2:mem:jpa_patterns;INIT=create schema if not exists jpa_patterns",
			AvailableSettings.LOADED_CLASSES, Arrays.asList(
					Participation.class,
					Stagiaire.class,
					Entreprise.class,
					Ressortissant.class,
					DossierFormation.class),
			AvailableSettings.XML_MAPPING_ENABLED, Boolean.FALSE.toString()
	);

	@Test
	public void testIndexation(EntityManager entityManager, EntityTransaction transaction) {
		Participation p1 = new Participation();
		p1.id = 1l;
		Stagiaire s2 = new Stagiaire();
		s2.id = 2l;
		s2.participations.add(p1);
		p1.stagiaire = s2;
		Ressortissant r3 = new Ressortissant();
		r3.id = 3l;
		s2.ressortissant = r3;
		r3.stagiaires.add(s2);
		Entreprise e4 = new Entreprise();
		e4.id = 4l;
		s2.entreprise = e4;
		e4.stagiaires.add(s2);
		
		Participation p5 = new Participation();
		p5.id = 5l;
		Stagiaire s6 = new Stagiaire();
		s6.id = 6l;
		s6.entreprise = e4;
		s6.ressortissant = r3;
		p5.stagiaire = s6;
		s6.participations.add(p5);
		r3.stagiaires.add(s6);
		e4.stagiaires.add(s6);
		
		Participation p7 = new Participation();
		p7.id = 7l;
		Stagiaire s8 = new Stagiaire();
		s8.id = 8l;
		Entreprise e9 = new Entreprise();
		e9.id = 9l;
		s8.entreprise = e9;
		s8.ressortissant = r3;
		p7.stagiaire = s8;
		s8.participations.add(p7);
		r3.stagiaires.add(s8);
		e9.stagiaires.add(s8);
		
		Participation p10 = new Participation();
		p10.id = 10l;
		Stagiaire s11 = new Stagiaire();
		s11.id = 11l;
		Entreprise e12 = new Entreprise();
		e12.id = 12l;
		Ressortissant r13 = new Ressortissant();
		r13.id = 13l;
		s11.entreprise = e12;
		s11.ressortissant = r13;
		p10.stagiaire = s11;
		s11.participations.add(p10);
		r13.stagiaires.add(s11);
		e12.stagiaires.add(s11);
		
		Participation p14 = new Participation();
		p14.id = 14l;
		p14.stagiaire = s2;
		s2.participations.add(p14);
		
		DossierFormation d15 = new DossierFormation();
		d15.id = 15l;
		d15.participations.add(p1);
		p1.dossierFormation = d15;
		DossierFormation d16 = new DossierFormation();
		d16.id = 16l;
		d16.participations.add(p5);
		p5.dossierFormation = d16;
		DossierFormation d17 = new DossierFormation();
		d17.id = 17l;
		d17.participations.add(p7);
		p7.dossierFormation = d17;
		DossierFormation d18 = new DossierFormation();
		d18.id = 18l;
		d18.participations.add(p10);
		p10.dossierFormation = d18;
		DossierFormation d19 = new DossierFormation();
		d19.id = 19l;
		d19.participations.add(p14);
		p14.dossierFormation = d19;
		
		entityManager.persist(p1);
		entityManager.persist(s2);
		entityManager.persist(r3);
		entityManager.persist(e4);
		entityManager.persist(p5);
		entityManager.persist(s6);
		entityManager.persist(p7);
		entityManager.persist(s8);
		entityManager.persist(e9);
		entityManager.persist(p10);
		entityManager.persist(s11);
		entityManager.persist(e12);
		entityManager.persist(r13);
		entityManager.persist(p14);
		entityManager.persist(d15);
		entityManager.persist(d16);
		entityManager.persist(d17);
		entityManager.persist(d18);
		entityManager.persist(d19);
		transaction.commit();
		
		transaction.begin();
		d15.label = "test";
		transaction.commit();
		
		transaction.begin();
		d16.label = "test";
		transaction.commit();
		
		transaction.begin();
		d18.label = "test";
		transaction.commit();
	}

}
