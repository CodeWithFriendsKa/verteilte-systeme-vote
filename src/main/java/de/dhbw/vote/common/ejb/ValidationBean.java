package de.dhbw.vote.common.ejb;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import javax.annotation.Resource;
import javax.ejb.Stateless;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;

/***
 * Beanklasse für Validierung
 * 
 * Trippleprogramming
 * @author Rouven Brost
 * @author Christopher Pschibila
 * @author codekeks (Tamino Fischer)
 */
@Stateless
public class ValidationBean {
    
    @Resource
    Validator validator;
    
    /**
     * Wertet die "Bean Validation" Annotationen des übergebenen Objekts aus
     * (@Min, @Max, @Size, @NotNull, …) und gib eine Liste der dabei gefundenen
     * Fehler zurück.
     * 
     * @param <T>
     * @param object Zu überprüfendes Objekt
     * @return Liste mit Fehlermeldungen
     */
    public <T> List<String> validate(T object) {
        List<String> messages = new ArrayList<>();
        return this.validate(object, messages);
    }
    
    /**
     * Wertet die "Bean Validation" Annotationes des übergebenen Objekts aus
     * und stellt die gefundenen Meldungen in messages.
     * 
     * @param <T>
     * @param object Zu überprüfendes Objekt
     * @param messages List mit Fehlermeldungen
     * @return Selbe Liste wie messages
     */
    public <T> List<String> validate(T object, List<String> messages) {
        Set<ConstraintViolation<T>> violations = this.validator.validate(object);
        
        violations.forEach((ConstraintViolation<T> violation) -> {
            messages.add(violation.getMessage());
        });
        
        return messages;
    }
}
