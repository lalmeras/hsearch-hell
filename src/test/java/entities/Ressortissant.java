package entities;

import java.util.HashSet;
import java.util.Set;

import javax.persistence.Access;
import javax.persistence.AccessType;
import javax.persistence.Basic;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import org.hibernate.search.annotations.ContainedIn;
import org.hibernate.search.annotations.DocumentId;
import org.hibernate.search.annotations.Field;
import org.hibernate.search.annotations.Indexed;

@Entity
@Indexed
@Access(AccessType.FIELD)
public class Ressortissant {

	@DocumentId
	@Id
	public Long id;

	@Field(name = "label")
	@Basic
	public String label;

	@OneToMany(mappedBy = "ressortissant")
	@ContainedIn
	public Set<Stagiaire> stagiaires = new HashSet<>();

}
