package nl.idfocus.nam.authentication;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

// com.novell.nidp.NIDPException: urn:oasis:names:tc:SAML:2.0:status:Responder->urn:oasis:names:tc:SAML:2.0:status:AuthnFailed
class IDPStatusMessage
{
	private static final String format = "([A-Za-z\\.]+):\\s([A-Za-z0-9:\\.]+)->([A-Za-z0-9:\\.]+).*";

	private final Matcher matcher;
	private final boolean matches;

	public IDPStatusMessage(String rawMsg)
	{
		Pattern pattern = Pattern.compile(format);
	    matcher = pattern.matcher(rawMsg);
	    matches = matcher.matches();
	}

	public boolean isValid()
	{
		return matches;
	}

	public String getPrimaryMessage()
	{
		return matcher.group(2);
	}

	public String getSecondaryMessage()
	{
		return matcher.group(3);
	}
}