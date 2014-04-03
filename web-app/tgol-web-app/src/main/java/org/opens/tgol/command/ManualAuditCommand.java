package org.opens.tgol.command;

import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

public class ManualAuditCommand implements Serializable{

	private List<String> statusList= Arrays.asList("passed", "failed", "na");
	
	public List<String> getStatusList() {
		return statusList;
	}

	public ManualAuditCommand() {
	}
}
