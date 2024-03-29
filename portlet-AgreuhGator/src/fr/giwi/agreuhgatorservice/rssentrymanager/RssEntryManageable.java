package fr.giwi.agreuhgatorservice.rssentrymanager;

import javax.jws.WebMethod;
import javax.jws.WebParam;
import javax.jws.WebResult;
import javax.jws.WebService;
import javax.xml.bind.annotation.XmlSeeAlso;
import javax.xml.ws.RequestWrapper;
import javax.xml.ws.ResponseWrapper;

/**
 * This class was generated by Apache CXF 2.2.5
 * Wed Apr 07 21:33:35 CEST 2010
 * Generated source version: 2.2.5
 * 
 */
 
@WebService(targetNamespace = "http://giwi.fr/AgreuhGatorservice/RssEntryManager", name = "RssEntryManageable")
@XmlSeeAlso({ObjectFactory.class})
public interface RssEntryManageable {

    @WebResult(name = "entry", targetNamespace = "")
    @RequestWrapper(localName = "getEntry", targetNamespace = "http://giwi.fr/AgreuhGatorservice/RssEntryManager", className = "fr.giwi.agreuhgatorservice.rssentrymanager.GetEntry")
    @ResponseWrapper(localName = "getEntryResponse", targetNamespace = "http://giwi.fr/AgreuhGatorservice/RssEntryManager", className = "fr.giwi.agreuhgatorservice.rssentrymanager.GetEntryResponse")
    @WebMethod
    public fr.giwi.agreuhgatorservice.rssentrymanager.RssEntry getEntry(
        @WebParam(name = "id", targetNamespace = "")
        int id
    );

    @RequestWrapper(localName = "updateRssEntry", targetNamespace = "http://giwi.fr/AgreuhGatorservice/RssEntryManager", className = "fr.giwi.agreuhgatorservice.rssentrymanager.UpdateRssEntry")
    @ResponseWrapper(localName = "updateRssEntryResponse", targetNamespace = "http://giwi.fr/AgreuhGatorservice/RssEntryManager", className = "fr.giwi.agreuhgatorservice.rssentrymanager.UpdateRssEntryResponse")
    @WebMethod
    public void updateRssEntry(
        @WebParam(name = "rssEntry", targetNamespace = "")
        fr.giwi.agreuhgatorservice.rssentrymanager.RssEntry rssEntry
    );

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "isValidFeed", targetNamespace = "http://giwi.fr/AgreuhGatorservice/RssEntryManager", className = "fr.giwi.agreuhgatorservice.rssentrymanager.IsValidFeed")
    @ResponseWrapper(localName = "isValidFeedResponse", targetNamespace = "http://giwi.fr/AgreuhGatorservice/RssEntryManager", className = "fr.giwi.agreuhgatorservice.rssentrymanager.IsValidFeedResponse")
    @WebMethod
    public boolean isValidFeed(
        @WebParam(name = "url", targetNamespace = "")
        java.lang.String url
    ) throws IOException_Exception, ClientProtocolException_Exception;

    @RequestWrapper(localName = "deleteRssEntry", targetNamespace = "http://giwi.fr/AgreuhGatorservice/RssEntryManager", className = "fr.giwi.agreuhgatorservice.rssentrymanager.DeleteRssEntry")
    @ResponseWrapper(localName = "deleteRssEntryResponse", targetNamespace = "http://giwi.fr/AgreuhGatorservice/RssEntryManager", className = "fr.giwi.agreuhgatorservice.rssentrymanager.DeleteRssEntryResponse")
    @WebMethod
    public void deleteRssEntry(
        @WebParam(name = "id", targetNamespace = "")
        int id
    );

    @WebResult(name = "return", targetNamespace = "")
    @RequestWrapper(localName = "isExistRssFeed", targetNamespace = "http://giwi.fr/AgreuhGatorservice/RssEntryManager", className = "fr.giwi.agreuhgatorservice.rssentrymanager.IsExistRssFeed")
    @ResponseWrapper(localName = "isExistRssFeedResponse", targetNamespace = "http://giwi.fr/AgreuhGatorservice/RssEntryManager", className = "fr.giwi.agreuhgatorservice.rssentrymanager.IsExistRssFeedResponse")
    @WebMethod
    public boolean isExistRssFeed(
        @WebParam(name = "url", targetNamespace = "")
        java.lang.String url
    );

    @RequestWrapper(localName = "addRssEntry", targetNamespace = "http://giwi.fr/AgreuhGatorservice/RssEntryManager", className = "fr.giwi.agreuhgatorservice.rssentrymanager.AddRssEntry")
    @ResponseWrapper(localName = "addRssEntryResponse", targetNamespace = "http://giwi.fr/AgreuhGatorservice/RssEntryManager", className = "fr.giwi.agreuhgatorservice.rssentrymanager.AddRssEntryResponse")
    @WebMethod
    public void addRssEntry(
        @WebParam(name = "rssEntry", targetNamespace = "")
        fr.giwi.agreuhgatorservice.rssentrymanager.RssEntry rssEntry
    ) throws IllegalArgumentException_Exception, IOException_Exception, FeedException_Exception;

    @WebResult(name = "listOfEntries", targetNamespace = "")
    @RequestWrapper(localName = "getRssEntries", targetNamespace = "http://giwi.fr/AgreuhGatorservice/RssEntryManager", className = "fr.giwi.agreuhgatorservice.rssentrymanager.GetRssEntries")
    @ResponseWrapper(localName = "getRssEntriesResponse", targetNamespace = "http://giwi.fr/AgreuhGatorservice/RssEntryManager", className = "fr.giwi.agreuhgatorservice.rssentrymanager.GetRssEntriesResponse")
    @WebMethod
    public java.util.List<fr.giwi.agreuhgatorservice.rssentrymanager.RssEntry> getRssEntries();
}
