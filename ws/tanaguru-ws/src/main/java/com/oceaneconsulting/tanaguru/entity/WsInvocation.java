package com.oceaneconsulting.tanaguru.entity;
import org.opens.tanaguru.sdk.entity.Entity;
import com.oceaneconsulting.tanaguru.entity.impl.WsUserImpl;
import java.util.Date;

public interface WsInvocation extends Entity {
	
	String getHostName();
	void setHostName(String hostName);
	String getHostIp();
	void setHostIp(String hostIp);
	Date getDateInvocation();
	void setDateInvocation();
	Integer getAuditType();
	void setAuditType(Integer auditType);
	WsUserImpl getUser();
	void setUser(WsUserImpl user);
	
}