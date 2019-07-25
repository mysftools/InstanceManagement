package com.instance.management.backup;

import com.sforce.ws.ConnectionException;

public class SfMetadataThread implements Runnable {

	private CustomerSfInfo csfi = null;

	public SfMetadataThread(CustomerSfInfo csfi) {
		this.csfi = csfi;
	}

	@Override
	public void run() {
		FileBasedDeployAndRetrieve fbdr;
		try {
			fbdr = new FileBasedDeployAndRetrieve(csfi.user, csfi.pwd, csfi.token, csfi.isSandbox, csfi.customerName,
					csfi.backupPath, csfi.fileLocation);
			fbdr.retrieveZip();
		} catch (ConnectionException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
