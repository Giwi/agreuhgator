package fr.giwi.agreugator.helpers;

import org.apache.http.HttpVersion;
import org.apache.http.conn.ClientConnectionManager;
import org.apache.http.conn.scheme.PlainSocketFactory;
import org.apache.http.conn.scheme.Scheme;
import org.apache.http.conn.scheme.SchemeRegistry;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.impl.conn.tsccm.ThreadSafeClientConnManager;
import org.apache.http.params.BasicHttpParams;
import org.apache.http.params.HttpParams;
import org.apache.http.params.HttpProtocolParams;

public class HttPhelper {

	private static DefaultHttpClient httpclient;

	public static DefaultHttpClient getHttpClient() {
		if (httpclient == null) {
			final SchemeRegistry supportedSchemes = new SchemeRegistry();

			// Register the "http" protocol scheme, it is required
			// by the default operator to look up socket factories.
			supportedSchemes.register(new Scheme("http", PlainSocketFactory.getSocketFactory(), 80));
			final HttpParams params = new BasicHttpParams();
			HttpProtocolParams.setVersion(params, HttpVersion.HTTP_1_1);
			HttpProtocolParams.setContentCharset(params, "UTF-8");
			HttpProtocolParams.setUseExpectContinue(params, true);

			final ClientConnectionManager connMgr = new ThreadSafeClientConnManager(params, supportedSchemes);
			httpclient = new DefaultHttpClient(connMgr, params);

		}
		return httpclient;
	}
}
