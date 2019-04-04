package de.dhbw.vote.common.web;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/***
 * Trippleprogramming
 * @author Rouven Brost
 * @author Christopher Pschibila
 * @author codekeks (Tamino Fischer)
 */
public class FormValues {
    private Map<String, String[]> values = new HashMap<>();
    private List<String> errors = new ArrayList<>();

    /***
     * 
     * @return 
     */
    public Map<String, String[]> getValues() {
        return values;
    }
    
    /***
     * 
     * @param values 
     */
    public void setValues(Map<String, String[]> values) {
        this.values = new HashMap<>();
        
        for (String key : values.keySet()) {
            this.values.put(key, values.get(key));
        }
    }
    
    /***
     * 
     * @return 
     */
    public List<String> getErrors() {
        return errors;
    }
    
    /***
     * 
     * @param errors 
     */
    public void setErrors(List<String> errors) {
        this.errors = errors;
    }    
}
