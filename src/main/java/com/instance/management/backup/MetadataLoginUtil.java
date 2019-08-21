package com.instance.management.backup;

import com.sforce.soap.metadata.MetadataConnection;
import com.sforce.soap.partner.LoginResult;
import com.sforce.soap.partner.PartnerConnection;
import com.sforce.ws.ConnectionException;
import com.sforce.ws.ConnectorConfig;

public class MetadataLoginUtil {

	public static MetadataConnection login(String user, String pwd, String token, boolean isSandbox)
			throws ConnectionException {
		final String USERNAME = user;
		// This is only a sample. Hard coding passwords in source files is a bad
		// practice.
		final String PASSWORD = pwd + token;
		String URL = "";
		if (isSandbox) {
			URL = "https://test.salesforce.com/services/Soap/u/46.0";
		} else {
			URL = "https://login.salesforce.com/services/Soap/u/46.0";
		}
		final LoginResult loginResult = loginToSalesforce(USERNAME, PASSWORD, URL);
		return createMetadataConnection(loginResult);
	}

	private static LoginResult loginToSalesforce(final String username, final String password, final String loginUrl)
			throws ConnectionException {
		final ConnectorConfig config = new ConnectorConfig();
		config.setAuthEndpoint(loginUrl);
		config.setServiceEndpoint(loginUrl);
		config.setManualLogin(true);
		// return null;
		return (new PartnerConnection(config)).login(username, password);
	}

	private static MetadataConnection createMetadataConnection(final LoginResult loginResult)
			throws ConnectionException {
		final ConnectorConfig config = new ConnectorConfig();
		config.setServiceEndpoint(loginResult.getMetadataServerUrl());
		config.setSessionId(loginResult.getSessionId());
		return new MetadataConnection(config);
	}

}
