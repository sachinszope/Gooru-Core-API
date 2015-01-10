package org.ednovo.gooru.domain.service.eventlogs;

import org.ednovo.gooru.core.api.model.Identity;
import org.ednovo.gooru.core.api.model.SessionContextSupport;
import org.ednovo.gooru.core.api.model.UserToken;
import org.ednovo.gooru.core.constant.ConstantProperties;
import org.ednovo.gooru.core.constant.ParameterProperties;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.stereotype.Component;

@Component
public class AccountEventLog implements ParameterProperties, ConstantProperties {

	public void getEventLogs(Identity identity, UserToken userToken, boolean login, String apiKey) throws JSONException {
		SessionContextSupport.putLogParameter(EVENT_NAME, login ? USER_LOGIN : USER_LOG_OUT);
		final JSONObject context = SessionContextSupport.getLog().get(CONTEXT) != null ? new JSONObject(SessionContextSupport.getLog().get(CONTEXT).toString()) : new JSONObject();
		
		if (identity != null && identity.getLoginType() != null) {
			context.put(login ? LOGIN_TYPE : LOG_OUT_TYPE , identity.getLoginType().equalsIgnoreCase(CREDENTIAL) ? GOORU : identity.getLoginType());			
		}

		SessionContextSupport.putLogParameter(CONTEXT, context.toString());
		final JSONObject payLoadObject = SessionContextSupport.getLog().get(PAY_LOAD_OBJECT) != null ? new JSONObject(SessionContextSupport.getLog().get(PAY_LOAD_OBJECT).toString()) : new JSONObject();
		SessionContextSupport.putLogParameter(PAY_LOAD_OBJECT, payLoadObject.toString());
		final JSONObject session = SessionContextSupport.getLog().get(SESSION) != null ? new JSONObject(SessionContextSupport.getLog().get(SESSION).toString()) : new JSONObject();
		session.put(SESSIONTOKEN, userToken.getToken());
		SessionContextSupport.putLogParameter(API_KEY, apiKey);
        SessionContextSupport.putLogParameter(SESSION, session.toString());
		final JSONObject user = SessionContextSupport.getLog().get(USER) != null ? new JSONObject(SessionContextSupport.getLog().get(USER).toString()) : new JSONObject();
		if(login){
			user.put(GOORU_UID, identity != null && identity.getUser() != null ? identity.getUser().getPartyUid() : null );
		} else {
			user.put(GOORU_UID, userToken != null && userToken.getUser() != null ? userToken.getUser().getPartyUid() : null );
		}
		SessionContextSupport.putLogParameter(USER, user.toString());
	}
	
}
