
package fr.giwi.agreuhgatorservice.rssentrymanager;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for getEntryResponse complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="getEntryResponse">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="entry" type="{http://giwi.fr/AgreuhGatorservice/RssEntryManager}rssEntry" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "getEntryResponse", propOrder = {
    "entry"
})
public class GetEntryResponse {

    protected RssEntry entry;

    /**
     * Gets the value of the entry property.
     * 
     * @return
     *     possible object is
     *     {@link RssEntry }
     *     
     */
    public RssEntry getEntry() {
        return entry;
    }

    /**
     * Sets the value of the entry property.
     * 
     * @param value
     *     allowed object is
     *     {@link RssEntry }
     *     
     */
    public void setEntry(RssEntry value) {
        this.entry = value;
    }

}
