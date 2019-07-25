package com.instance.management.backup;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import com.sforce.soap.metadata.AsyncResult;
import com.sforce.soap.metadata.MetadataConnection;
import com.sforce.soap.metadata.PackageTypeMembers;
import com.sforce.soap.metadata.RetrieveMessage;
import com.sforce.soap.metadata.RetrieveRequest;
import com.sforce.soap.metadata.RetrieveResult;
import com.sforce.soap.metadata.RetrieveStatus;
import com.sforce.ws.ConnectionException;

public class FileBasedDeployAndRetrieve {

	private MetadataConnection metadataConnection;

	private static final double API_VERSION = 37.0;

	// one second in milliseconds
	private static final long ONE_SECOND = 1000;

	// maximum number of attempts to deploy the zip file
	private static final int MAX_NUM_POLL_REQUESTS = 50;

	private String location = null;

	// manifest file that controls which components get retrieved
	private static String MANIFEST_FILE = "D:\\SfBackup\\src\\com\\package.xml";

	public FileBasedDeployAndRetrieve(String user, String pwd, String token, boolean isToken, String customerName,
			String location, String fileLocation) throws ConnectionException {
		if (fileLocation != null) {
			MANIFEST_FILE = fileLocation;
		}
		try {
			this.metadataConnection = MetadataLoginUtil.login(user, pwd, token, isToken);
		} catch (Exception e) {
			e.printStackTrace();
			System.out.println(user + " " + e.getMessage());
			throw e;
		}
		this.location = location;
	}

	public void retrieveZip() throws Exception {
		RetrieveRequest retrieveRequest = new RetrieveRequest();
		// The version in package.xml overrides the version in RetrieveRequest
		retrieveRequest.setApiVersion(API_VERSION);
		setUnpackaged(retrieveRequest);

		AsyncResult asyncResult = metadataConnection.retrieve(retrieveRequest);
		RetrieveResult result = waitForRetrieveCompletion(asyncResult);

		if (result.getStatus() == RetrieveStatus.Failed) {
			throw new Exception(result.getErrorStatusCode() + " msg: " + result.getErrorMessage());
		} else if (result.getStatus() == RetrieveStatus.Succeeded) {
			// Print out any warning messages
			StringBuilder stringBuilder = new StringBuilder();
			if (result.getMessages() != null) {
				for (RetrieveMessage rm : result.getMessages()) {
					stringBuilder.append(rm.getFileName() + " - " + rm.getProblem() + "\n");
				}
			}
			if (stringBuilder.length() > 0) {
				System.out.println("Retrieve warnings:\n" + stringBuilder);
			}

			System.out.println(Thread.currentThread().getName() + " Writing results to zip file");
			Calendar cal = new GregorianCalendar();
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy_MM_dd_HH_mm_ss");
			String dt = sdf.format(cal.getTime());
			File f = new File(location);
			if (!f.exists()) {
				f.mkdirs();
			}
			File resultsFile = new File(location + "\\" + dt + ".zip");
			FileOutputStream os = new FileOutputStream(resultsFile);

			try {
				os.write(result.getZipFile());
			} finally {
				os.close();
			}
		}
	}

	private void setUnpackaged(RetrieveRequest request) throws Exception {
		// Edit the path, if necessary, if your package.xml file is located elsewhere
		System.out.println(MANIFEST_FILE);
		File unpackedManifest = new File(MANIFEST_FILE);
		System.out.println(Thread.currentThread().getName() + " Manifest file: " + unpackedManifest.getAbsolutePath());
		System.out.println(unpackedManifest.exists());
		if (!unpackedManifest.exists() || !unpackedManifest.isFile()) {
			throw new Exception("Should provide a valid retrieve manifest " + "for unpackaged content. Looking for "
					+ unpackedManifest.getAbsolutePath());
		}

		// Note that we use the fully quualified class name because
		// of a collision with the java.lang.Package class
		com.sforce.soap.metadata.Package p = parsePackageManifest(unpackedManifest);
		request.setUnpackaged(p);
	}

	private RetrieveResult waitForRetrieveCompletion(AsyncResult asyncResult) throws Exception {
		// Wait for the retrieve to complete
		int poll = 0;
		long waitTimeMilliSecs = ONE_SECOND;
		String asyncResultId = asyncResult.getId();
		RetrieveResult result = null;
		do {
			Thread.sleep(waitTimeMilliSecs);
			// Double the wait time for the next iteration
			waitTimeMilliSecs *= 2;
			if (poll++ > MAX_NUM_POLL_REQUESTS) {
				throw new Exception("Request timed out.  If this is a large set "
						+ "of metadata components, check that the time allowed "
						+ "by MAX_NUM_POLL_REQUESTS is sufficient.");
			}
			result = metadataConnection.checkRetrieveStatus(asyncResultId, true);
			System.out.println(Thread.currentThread().getName() + " Retrieve Status: " + result.getStatus());
		} while (!result.isDone());

		return result;
	}

	private com.sforce.soap.metadata.Package parsePackageManifest(File file)
			throws ParserConfigurationException, IOException, SAXException {
		com.sforce.soap.metadata.Package packageManifest = null;
		List<PackageTypeMembers> listPackageTypes = new ArrayList<PackageTypeMembers>();
		DocumentBuilder db = DocumentBuilderFactory.newInstance().newDocumentBuilder();
		InputStream inputStream = new FileInputStream(file);
		Element d = db.parse(inputStream).getDocumentElement();
		for (Node c = d.getFirstChild(); c != null; c = c.getNextSibling()) {
			if (c instanceof Element) {
				Element ce = (Element) c;
				NodeList nodeList = ce.getElementsByTagName("name");
				if (nodeList.getLength() == 0) {
					continue;
				}
				String name = nodeList.item(0).getTextContent();
				NodeList m = ce.getElementsByTagName("members");
				List<String> members = new ArrayList<String>();
				for (int i = 0; i < m.getLength(); i++) {
					Node mm = m.item(i);
					members.add(mm.getTextContent());
				}
				PackageTypeMembers packageTypes = new PackageTypeMembers();
				packageTypes.setName(name);
				packageTypes.setMembers(members.toArray(new String[members.size()]));
				listPackageTypes.add(packageTypes);
			}
		}
		packageManifest = new com.sforce.soap.metadata.Package();
		PackageTypeMembers[] packageTypesArray = new PackageTypeMembers[listPackageTypes.size()];
		packageManifest.setTypes(listPackageTypes.toArray(packageTypesArray));
		packageManifest.setVersion(API_VERSION + "");
		return packageManifest;
	}
}
