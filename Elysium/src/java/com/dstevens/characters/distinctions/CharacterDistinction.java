package com.dstevens.characters.distinctions;

import java.util.Comparator;
import java.util.function.Function;
import java.util.function.Predicate;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.lang3.builder.EqualsBuilder;
import org.apache.commons.lang3.builder.HashCodeBuilder;

import com.dstevens.characters.PlayerCharacter;
import com.dstevens.suppliers.IdSupplier;
import com.dstevens.utilities.ObjectExtensions;

import javax.persistence.Column;
import javax.persistence.DiscriminatorColumn;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.Table;

@Entity
@Inheritance
@DiscriminatorColumn(name="distinction_type")
@Table(name="Distinctions")
public abstract class CharacterDistinction implements Comparable<CharacterDistinction> {

    @Id
    private final String id;
    
    @Column(name="ordinal")
    private int ordinal;
    
    @Column(name="type")
    private String type;
    
    @Column(name="details")
    private String details;
    
    public final Predicate<? super CharacterDistinction> matches() {
		return (CharacterDistinction t) -> t.type().equals(this.type()) && t.ordinal() == this.ordinal() && StringUtils.equalsIgnoreCase(t.getDetails(), this.getDetails());
	}
    
    protected CharacterDistinction(Distinction<?> distinction) {
        this(distinction, null);
    }
    
    protected CharacterDistinction(Distinction<?> distinction, String details) {
        this(distinction.ordinal(), distinction.getType(), details);
    }
    
    protected CharacterDistinction(int ordinal, String type, String details) {
        this.id = new IdSupplier().get();
        this.ordinal = ordinal;
        this.type = type;
        this.details = details;
    }
    
    public abstract Distinction<?> getDistinction();
    public abstract PlayerCharacter applyTo(PlayerCharacter character);
	public abstract PlayerCharacter removeFrom(PlayerCharacter character);
    
    public final String getDetails() {
        return details;
    }
    
    public final int ordinal() {
        return ordinal;
    }
    
    public final String type() {
        return type;
    }
    
    @Override
    public boolean equals(Object that) {
    	return EqualsBuilder.reflectionEquals(this, that, "id");
    }
    
    @Override
    public int hashCode() {
    	return HashCodeBuilder.reflectionHashCode(this, "id");
    }
    
    @Override
    public String toString() {
        return ObjectExtensions.toStringFor(this);
    }

    @Override
    public int compareTo(CharacterDistinction that) {
        Function<CharacterDistinction, String> byType = ((CharacterDistinction cm) -> cm.type);
        Function<CharacterDistinction, Integer> byOrdinal = ((CharacterDistinction cm) -> cm.ordinal);
        Function<CharacterDistinction, String> byDetails = ((CharacterDistinction cm) -> cm.details);
        return Comparator.comparing(byType).thenComparing(byOrdinal).thenComparing(byDetails).compare(this, that);
    }
}
