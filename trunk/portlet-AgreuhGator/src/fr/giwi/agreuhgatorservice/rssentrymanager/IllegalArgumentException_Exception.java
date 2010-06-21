
package fr.giwi.agreuhgatorservice.rssentrymanager;

import javax.xml.ws.WebFault;


/**
 * This class was generated by Apache CXF 2.2.5
 * Wed Apr 07 21:33:35 CEST 2010
 * Generated source version: 2.2.5
 * 
 */

@WebFault(name = "IllegalArgumentException", targetNamespace = "http://giwi.fr/AgreuhGatorservice/RssEntryManager")
public class IllegalArgumentException_Exception extends Exception {
    public static final long serialVersionUID = 20100403135223L;
    
    private fr.giwi.agreuhgatorservice.rssentrymanager.IllegalArgumentException illegalArgumentException;

    public IllegalArgumentException_Exception() {
        super();
    }
    
    public IllegalArgumentException_Exception(String message) {
        super(message);
    }
    
    public IllegalArgumentException_Exception(String message, Throwable cause) {
        super(message, cause);
    }

    public IllegalArgumentException_Exception(String message, fr.giwi.agreuhgatorservice.rssentrymanager.IllegalArgumentException illegalArgumentException) {
        super(message);
        this.illegalArgumentException = illegalArgumentException;
    }

    public IllegalArgumentException_Exception(String message, fr.giwi.agreuhgatorservice.rssentrymanager.IllegalArgumentException illegalArgumentException, Throwable cause) {
        super(message, cause);
        this.illegalArgumentException = illegalArgumentException;
    }

    public fr.giwi.agreuhgatorservice.rssentrymanager.IllegalArgumentException getFaultInfo() {
        return this.illegalArgumentException;
    }
}
