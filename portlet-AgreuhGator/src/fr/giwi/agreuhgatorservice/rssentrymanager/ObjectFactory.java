
package fr.giwi.agreuhgatorservice.rssentrymanager;

import javax.xml.bind.JAXBElement;
import javax.xml.bind.annotation.XmlElementDecl;
import javax.xml.bind.annotation.XmlRegistry;
import javax.xml.namespace.QName;


/**
 * This object contains factory methods for each 
 * Java content interface and Java element interface 
 * generated in the fr.giwi.agreuhgatorservice.rssentrymanager package. 
 * <p>An ObjectFactory allows you to programatically 
 * construct new instances of the Java representation 
 * for XML content. The Java representation of XML 
 * content can consist of schema derived interfaces 
 * and classes representing the binding of schema 
 * type definitions, element declarations and model 
 * groups.  Factory methods for each of these are 
 * provided in this class.
 * 
 */
@XmlRegistry
public class ObjectFactory {

    private final static QName _AddRssEntryResponse_QNAME = new QName("http://giwi.fr/AgreuhGatorservice/RssEntryManager", "addRssEntryResponse");
    private final static QName _IsValidFeed_QNAME = new QName("http://giwi.fr/AgreuhGatorservice/RssEntryManager", "isValidFeed");
    private final static QName _GetRssEntriesResponse_QNAME = new QName("http://giwi.fr/AgreuhGatorservice/RssEntryManager", "getRssEntriesResponse");
    private final static QName _GetEntry_QNAME = new QName("http://giwi.fr/AgreuhGatorservice/RssEntryManager", "getEntry");
    private final static QName _GetRssEntries_QNAME = new QName("http://giwi.fr/AgreuhGatorservice/RssEntryManager", "getRssEntries");
    private final static QName _IOException_QNAME = new QName("http://giwi.fr/AgreuhGatorservice/RssEntryManager", "IOException");
    private final static QName _UpdateRssEntry_QNAME = new QName("http://giwi.fr/AgreuhGatorservice/RssEntryManager", "updateRssEntry");
    private final static QName _UpdateRssEntryResponse_QNAME = new QName("http://giwi.fr/AgreuhGatorservice/RssEntryManager", "updateRssEntryResponse");
    private final static QName _IllegalArgumentException_QNAME = new QName("http://giwi.fr/AgreuhGatorservice/RssEntryManager", "IllegalArgumentException");
    private final static QName _IsValidFeedResponse_QNAME = new QName("http://giwi.fr/AgreuhGatorservice/RssEntryManager", "isValidFeedResponse");
    private final static QName _IsExistRssFeed_QNAME = new QName("http://giwi.fr/AgreuhGatorservice/RssEntryManager", "isExistRssFeed");
    private final static QName _FeedException_QNAME = new QName("http://giwi.fr/AgreuhGatorservice/RssEntryManager", "FeedException");
    private final static QName _DeleteRssEntryResponse_QNAME = new QName("http://giwi.fr/AgreuhGatorservice/RssEntryManager", "deleteRssEntryResponse");
    private final static QName _GetEntryResponse_QNAME = new QName("http://giwi.fr/AgreuhGatorservice/RssEntryManager", "getEntryResponse");
    private final static QName _DeleteRssEntry_QNAME = new QName("http://giwi.fr/AgreuhGatorservice/RssEntryManager", "deleteRssEntry");
    private final static QName _AddRssEntry_QNAME = new QName("http://giwi.fr/AgreuhGatorservice/RssEntryManager", "addRssEntry");
    private final static QName _IsExistRssFeedResponse_QNAME = new QName("http://giwi.fr/AgreuhGatorservice/RssEntryManager", "isExistRssFeedResponse");
    private final static QName _ClientProtocolException_QNAME = new QName("http://giwi.fr/AgreuhGatorservice/RssEntryManager", "ClientProtocolException");

    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: fr.giwi.agreuhgatorservice.rssentrymanager
     * 
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link AddRssEntryResponse }
     * 
     */
    public AddRssEntryResponse createAddRssEntryResponse() {
        return new AddRssEntryResponse();
    }

    /**
     * Create an instance of {@link IsValidFeed }
     * 
     */
    public IsValidFeed createIsValidFeed() {
        return new IsValidFeed();
    }

    /**
     * Create an instance of {@link GetRssEntriesResponse }
     * 
     */
    public GetRssEntriesResponse createGetRssEntriesResponse() {
        return new GetRssEntriesResponse();
    }

    /**
     * Create an instance of {@link UpdateRssEntryResponse }
     * 
     */
    public UpdateRssEntryResponse createUpdateRssEntryResponse() {
        return new UpdateRssEntryResponse();
    }

    /**
     * Create an instance of {@link GetRssEntries }
     * 
     */
    public GetRssEntries createGetRssEntries() {
        return new GetRssEntries();
    }

    /**
     * Create an instance of {@link ClientProtocolException }
     * 
     */
    public ClientProtocolException createClientProtocolException() {
        return new ClientProtocolException();
    }

    /**
     * Create an instance of {@link DeleteRssEntryResponse }
     * 
     */
    public DeleteRssEntryResponse createDeleteRssEntryResponse() {
        return new DeleteRssEntryResponse();
    }

    /**
     * Create an instance of {@link IsValidFeedResponse }
     * 
     */
    public IsValidFeedResponse createIsValidFeedResponse() {
        return new IsValidFeedResponse();
    }

    /**
     * Create an instance of {@link IllegalArgumentException }
     * 
     */
    public IllegalArgumentException createIllegalArgumentException() {
        return new IllegalArgumentException();
    }

    /**
     * Create an instance of {@link GetEntry }
     * 
     */
    public GetEntry createGetEntry() {
        return new GetEntry();
    }

    /**
     * Create an instance of {@link RssEntry }
     * 
     */
    public RssEntry createRssEntry() {
        return new RssEntry();
    }

    /**
     * Create an instance of {@link IOException }
     * 
     */
    public IOException createIOException() {
        return new IOException();
    }

    /**
     * Create an instance of {@link DeleteRssEntry }
     * 
     */
    public DeleteRssEntry createDeleteRssEntry() {
        return new DeleteRssEntry();
    }

    /**
     * Create an instance of {@link FeedException }
     * 
     */
    public FeedException createFeedException() {
        return new FeedException();
    }

    /**
     * Create an instance of {@link IsExistRssFeed }
     * 
     */
    public IsExistRssFeed createIsExistRssFeed() {
        return new IsExistRssFeed();
    }

    /**
     * Create an instance of {@link IsExistRssFeedResponse }
     * 
     */
    public IsExistRssFeedResponse createIsExistRssFeedResponse() {
        return new IsExistRssFeedResponse();
    }

    /**
     * Create an instance of {@link UpdateRssEntry }
     * 
     */
    public UpdateRssEntry createUpdateRssEntry() {
        return new UpdateRssEntry();
    }

    /**
     * Create an instance of {@link AddRssEntry }
     * 
     */
    public AddRssEntry createAddRssEntry() {
        return new AddRssEntry();
    }

    /**
     * Create an instance of {@link GetEntryResponse }
     * 
     */
    public GetEntryResponse createGetEntryResponse() {
        return new GetEntryResponse();
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddRssEntryResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://giwi.fr/AgreuhGatorservice/RssEntryManager", name = "addRssEntryResponse")
    public JAXBElement<AddRssEntryResponse> createAddRssEntryResponse(AddRssEntryResponse value) {
        return new JAXBElement<AddRssEntryResponse>(_AddRssEntryResponse_QNAME, AddRssEntryResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IsValidFeed }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://giwi.fr/AgreuhGatorservice/RssEntryManager", name = "isValidFeed")
    public JAXBElement<IsValidFeed> createIsValidFeed(IsValidFeed value) {
        return new JAXBElement<IsValidFeed>(_IsValidFeed_QNAME, IsValidFeed.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetRssEntriesResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://giwi.fr/AgreuhGatorservice/RssEntryManager", name = "getRssEntriesResponse")
    public JAXBElement<GetRssEntriesResponse> createGetRssEntriesResponse(GetRssEntriesResponse value) {
        return new JAXBElement<GetRssEntriesResponse>(_GetRssEntriesResponse_QNAME, GetRssEntriesResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetEntry }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://giwi.fr/AgreuhGatorservice/RssEntryManager", name = "getEntry")
    public JAXBElement<GetEntry> createGetEntry(GetEntry value) {
        return new JAXBElement<GetEntry>(_GetEntry_QNAME, GetEntry.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetRssEntries }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://giwi.fr/AgreuhGatorservice/RssEntryManager", name = "getRssEntries")
    public JAXBElement<GetRssEntries> createGetRssEntries(GetRssEntries value) {
        return new JAXBElement<GetRssEntries>(_GetRssEntries_QNAME, GetRssEntries.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IOException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://giwi.fr/AgreuhGatorservice/RssEntryManager", name = "IOException")
    public JAXBElement<IOException> createIOException(IOException value) {
        return new JAXBElement<IOException>(_IOException_QNAME, IOException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateRssEntry }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://giwi.fr/AgreuhGatorservice/RssEntryManager", name = "updateRssEntry")
    public JAXBElement<UpdateRssEntry> createUpdateRssEntry(UpdateRssEntry value) {
        return new JAXBElement<UpdateRssEntry>(_UpdateRssEntry_QNAME, UpdateRssEntry.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link UpdateRssEntryResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://giwi.fr/AgreuhGatorservice/RssEntryManager", name = "updateRssEntryResponse")
    public JAXBElement<UpdateRssEntryResponse> createUpdateRssEntryResponse(UpdateRssEntryResponse value) {
        return new JAXBElement<UpdateRssEntryResponse>(_UpdateRssEntryResponse_QNAME, UpdateRssEntryResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IllegalArgumentException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://giwi.fr/AgreuhGatorservice/RssEntryManager", name = "IllegalArgumentException")
    public JAXBElement<IllegalArgumentException> createIllegalArgumentException(IllegalArgumentException value) {
        return new JAXBElement<IllegalArgumentException>(_IllegalArgumentException_QNAME, IllegalArgumentException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IsValidFeedResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://giwi.fr/AgreuhGatorservice/RssEntryManager", name = "isValidFeedResponse")
    public JAXBElement<IsValidFeedResponse> createIsValidFeedResponse(IsValidFeedResponse value) {
        return new JAXBElement<IsValidFeedResponse>(_IsValidFeedResponse_QNAME, IsValidFeedResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IsExistRssFeed }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://giwi.fr/AgreuhGatorservice/RssEntryManager", name = "isExistRssFeed")
    public JAXBElement<IsExistRssFeed> createIsExistRssFeed(IsExistRssFeed value) {
        return new JAXBElement<IsExistRssFeed>(_IsExistRssFeed_QNAME, IsExistRssFeed.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link FeedException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://giwi.fr/AgreuhGatorservice/RssEntryManager", name = "FeedException")
    public JAXBElement<FeedException> createFeedException(FeedException value) {
        return new JAXBElement<FeedException>(_FeedException_QNAME, FeedException.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteRssEntryResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://giwi.fr/AgreuhGatorservice/RssEntryManager", name = "deleteRssEntryResponse")
    public JAXBElement<DeleteRssEntryResponse> createDeleteRssEntryResponse(DeleteRssEntryResponse value) {
        return new JAXBElement<DeleteRssEntryResponse>(_DeleteRssEntryResponse_QNAME, DeleteRssEntryResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link GetEntryResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://giwi.fr/AgreuhGatorservice/RssEntryManager", name = "getEntryResponse")
    public JAXBElement<GetEntryResponse> createGetEntryResponse(GetEntryResponse value) {
        return new JAXBElement<GetEntryResponse>(_GetEntryResponse_QNAME, GetEntryResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link DeleteRssEntry }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://giwi.fr/AgreuhGatorservice/RssEntryManager", name = "deleteRssEntry")
    public JAXBElement<DeleteRssEntry> createDeleteRssEntry(DeleteRssEntry value) {
        return new JAXBElement<DeleteRssEntry>(_DeleteRssEntry_QNAME, DeleteRssEntry.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link AddRssEntry }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://giwi.fr/AgreuhGatorservice/RssEntryManager", name = "addRssEntry")
    public JAXBElement<AddRssEntry> createAddRssEntry(AddRssEntry value) {
        return new JAXBElement<AddRssEntry>(_AddRssEntry_QNAME, AddRssEntry.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link IsExistRssFeedResponse }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://giwi.fr/AgreuhGatorservice/RssEntryManager", name = "isExistRssFeedResponse")
    public JAXBElement<IsExistRssFeedResponse> createIsExistRssFeedResponse(IsExistRssFeedResponse value) {
        return new JAXBElement<IsExistRssFeedResponse>(_IsExistRssFeedResponse_QNAME, IsExistRssFeedResponse.class, null, value);
    }

    /**
     * Create an instance of {@link JAXBElement }{@code <}{@link ClientProtocolException }{@code >}}
     * 
     */
    @XmlElementDecl(namespace = "http://giwi.fr/AgreuhGatorservice/RssEntryManager", name = "ClientProtocolException")
    public JAXBElement<ClientProtocolException> createClientProtocolException(ClientProtocolException value) {
        return new JAXBElement<ClientProtocolException>(_ClientProtocolException_QNAME, ClientProtocolException.class, null, value);
    }

}
