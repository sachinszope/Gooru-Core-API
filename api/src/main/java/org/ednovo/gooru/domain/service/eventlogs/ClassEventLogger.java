package org.ednovo.gooru.domain.service.eventlogs;

import org.ednovo.gooru.core.api.model.SessionContextSupport;
import org.ednovo.gooru.core.constant.ConstantProperties;
import org.ednovo.gooru.core.constant.ParameterProperties;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

@Component
public class ClassEventLogger extends EventLog{

	public static final Logger LOGGER = LoggerFactory.getLogger(ClassEventLogger.class);
	
	public void memberRemoveLog(String organizationUid, String classUid, String studentUid) {
		
		try {
			SessionContextSupport.getLog().put(ConstantProperties.EVENT_NAME, ParameterProperties.CLASS_USER_REMOVE);
			insertValue(ParameterProperties.CONTEXT,ParameterProperties.CONTENT_GOORU_ID,classUid);
			insertValue(ConstantProperties.PAY_LOAD_OBJECT,ConstantProperties.REMOVE_GOORU_UID,studentUid);
		} catch (Exception e) {
			LOGGER.error(ParameterProperties._ERROR, e);
		}
	}
	
	public void memberJoinLog(String classUid) {
		
		try {
			SessionContextSupport.getLog().put(ConstantProperties.EVENT_NAME,ParameterProperties.CLASS_USER_ADD);
			insertValue(ParameterProperties.CONTEXT,ParameterProperties.CONTENT_GOORU_ID,classUid);
		} catch (Exception e) {
			LOGGER.error(ParameterProperties._ERROR, e);
		}
	}
}
