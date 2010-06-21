
package fr.giwi.agreuhgatorservice.rssentrymanager;

import java.util.ArrayList;
import java.util.List;
import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getRssEntriesResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getRssEntriesResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="listOfEntries" type="{http://giwi.fr/AgreuhGatorservice/RssEntryManager}rssEntry" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getRssEntriesResponse", propOrder = {
    "listOfEntries"
})
public class GetRssEntriesResponse {

    protected List<RssEntry> listOfEntries;

    /**
     * Gets the value of the listOfEntries property.
     * 
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the listOfEntries property.
     * 
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getListOfEntries().add(newItem);
     * </pre>
     * 
     * 
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link RssEntry }
     * 
     * 
     */
    public List<RssEntry> getListOfEntries() {
        if (listOfEntries == null) {
            listOfEntries = new ArrayList<RssEntry>();
        }
        return this.listOfEntries;
    }

}
