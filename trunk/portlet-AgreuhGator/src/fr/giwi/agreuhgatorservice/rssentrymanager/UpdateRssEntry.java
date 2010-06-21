
package fr.giwi.agreuhgatorservice.rssentrymanager;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>Java class for updateRssEntry complex type.
 * 
 * <p>The following schema fragment specifies the expected content contained within this class.
 * 
 * <pre>
 * &lt;complexType name="updateRssEntry">
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element name="rssEntry" type="{http://giwi.fr/AgreuhGatorservice/RssEntryManager}rssEntry" minOccurs="0"/>
 *       &lt;/sequence>
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 * 
 * 
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "updateRssEntry", propOrder = {
    "rssEntry"
})
public class UpdateRssEntry {

    protected RssEntry rssEntry;

    /**
     * Gets the value of the rssEntry property.
     * 
     * @return
     *     possible object is
     *     {@link RssEntry }
     *     
     */
    public RssEntry getRssEntry() {
        return rssEntry;
    }

    /**
     * Sets the value of the rssEntry property.
     * 
     * @param value
     *     allowed object is
     *     {@link RssEntry }
     *     
     */
    public void setRssEntry(RssEntry value) {
        this.rssEntry = value;
    }

}
