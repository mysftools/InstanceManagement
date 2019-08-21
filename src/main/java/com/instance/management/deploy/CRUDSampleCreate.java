package com.instance.management.deploy;

import com.instance.management.backup.MetadataLoginUtil;
import com.sforce.soap.metadata.CustomField;
import com.sforce.soap.metadata.CustomObject;
import com.sforce.soap.metadata.DeploymentStatus;
import com.sforce.soap.metadata.Error;
import com.sforce.soap.metadata.FieldType;
import com.sforce.soap.metadata.Metadata;
import com.sforce.soap.metadata.MetadataConnection;
import com.sforce.soap.metadata.Profile;
import com.sforce.soap.metadata.ReadResult;
import com.sforce.soap.metadata.SaveResult;
import com.sforce.soap.metadata.SharingModel;
import com.sforce.soap.metadata.UpsertResult;

/**
 * Sample that logs in and creates a custom object through the metadata API
 */
public class CRUDSampleCreate {
	private MetadataConnection metadataConnection;

	// one second in milliseconds
	// private static final long ONE_SECOND = 1000;

	public CRUDSampleCreate() {
	}

	public static void main(String[] args) throws Exception {
		CRUDSampleCreate crudSample = new CRUDSampleCreate();
		crudSample.runCreate();
	}

	/**
	 * Create a custom object. This method demonstrates usage of the create() and
	 * checkStatus() calls.
	 *
	 * @param uniqueName Custom object name should be unique.
	 */
	public void createCustomObjectSync() {
		try {
			CustomObject co = new CustomObject();
			String name = "MyCustomObject1";
			co.setFullName(name + "__c");
			co.setDeploymentStatus(DeploymentStatus.Deployed);
			co.setDescription("Created by the Metadata API");
			co.setEnableActivities(true);
			co.setLabel(name + " Object");
			co.setPluralLabel(co.getLabel() + "s");
			co.setSharingModel(SharingModel.ReadWrite);

			CustomField nf = new CustomField();
			nf.setType(FieldType.Text);
			nf.setLabel(co.getFullName() + " Name");
			co.setNameField(nf);

			SaveResult[] results = metadataConnection.createMetadata(new Metadata[] { co });

			for (SaveResult r : results) {
				if (r.isSuccess()) {
					System.out.println("Created component: " + r.getFullName());
				} else {
					System.out.println("Errors were encountered while creating " + r.getFullName());
					for (Error e : r.getErrors()) {
						System.out.println("Error message: " + e.getMessage());
						System.out.println("Status code: " + e.getStatusCode());
					}
				}
			}
		} catch (Exception ce) {
			ce.printStackTrace();
		}
	}

	public void upsertMetadataSample() {
		try {
			// Create custom object to upsert
			CustomObject co = new CustomObject();
			String name = "MyCustomObject2";
			co.setFullName(name + "__c");
			co.setDeploymentStatus(DeploymentStatus.Deployed);
			co.setDescription("Upserted by the Metadata API");
			co.setEnableActivities(true);
			co.setLabel(name + " Object");
			co.setPluralLabel(co.getLabel() + "s");
			co.setSharingModel(SharingModel.ReadWrite);

			CustomField nf = new CustomField();
			nf.setType(FieldType.Text);
			nf.setLabel("CustomFiel");
			co.setNameField(nf);

			// Upsert the custom object
			UpsertResult[] results = metadataConnection.upsertMetadata(new Metadata[] { co });

			for (UpsertResult r : results) {
				if (r.isSuccess()) {
					System.out.println("Success!");
					if (r.isCreated()) {
						System.out.println("Created component: " + r.getFullName());
					} else {
						System.out.println("Updated component: " + r.getFullName());
					}
				} else {
					System.out.println("Errors were encountered while upserting " + r.getFullName());
					for (Error e : r.getErrors()) {
						System.out.println("Error message: " + e.getMessage());
						System.out.println("Status code: " + e.getStatusCode());
					}
				}
			}
		} catch (Exception ce) {
			ce.printStackTrace();
		}
	}

	public void readCustomObjectSync() {
		try {
			ReadResult readResult = metadataConnection.readMetadata("User", new String[] { "Jack Rogers" });
			Metadata[] mdInfo = readResult.getRecords();
			System.out.println("Number of component info returned: " + mdInfo.length);
			for (Metadata md : mdInfo) {
				if (md != null) {
					com.sforce.soap.metadata.
					Profile obj = (Profile) md;
					System.out.println("Custom object full name: " + obj.getFullName());
					System.out.println(obj.getUserLicense());
					System.out.println(obj.getLoginHours());
					/*
					 * System.out.println("Label: " + obj.getLabel());
					 * System.out.println("Number of custom fields: " + obj.getFields().length);
					 * System.out.println("Sharing model: " + obj.getSharingModel());
					 */
				} else {
					System.out.println("Empty metadata.");
				}
			}
		} catch (Exception ce) {
			ce.printStackTrace();
		}
	}

	private void runCreate() throws Exception {
		metadataConnection = MetadataLoginUtil.login("javaapp@asc.com", "ri89fiu2", "cfH5MrYn2fz6a4h0H6CL4Hp0b", false);
		// Custom objects and fields must have __c suffix in the full name.
		// upsertMetadataSample();
		readCustomObjectSync();
	}
}