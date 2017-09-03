package org.phoenixctms.ctsms.web;

import java.io.IOException;
import java.util.Iterator;

import javax.faces.FacesException;
import javax.faces.application.ViewExpiredException;
import javax.faces.context.ExceptionHandlerWrapper;
import javax.faces.context.FacesContext;
import javax.faces.event.ExceptionQueuedEvent;
import javax.faces.event.ExceptionQueuedEventContext;
import javax.servlet.http.HttpServletRequest;

import org.phoenixctms.ctsms.exception.AuthenticationException;
import org.phoenixctms.ctsms.js.JsUtil;
import org.phoenixctms.ctsms.web.model.SessionScopeBean;
import org.phoenixctms.ctsms.web.util.DefaultSettings;
import org.phoenixctms.ctsms.web.util.GetParamNames;
import org.phoenixctms.ctsms.web.util.MessageCodes;
import org.phoenixctms.ctsms.web.util.Messages;
import org.phoenixctms.ctsms.web.util.SettingCodes;
import org.phoenixctms.ctsms.web.util.Settings;
import org.phoenixctms.ctsms.web.util.Settings.Bundle;
import org.phoenixctms.ctsms.web.util.Urls;
import org.phoenixctms.ctsms.web.util.WebUtil;

public class ExceptionHandler extends ExceptionHandlerWrapper {

	private javax.faces.context.ExceptionHandler wrapped;

	public ExceptionHandler(javax.faces.context.ExceptionHandler wrapped) {
		this.wrapped = wrapped;
	}

	@Override
	public javax.faces.context.ExceptionHandler getWrapped() {
		return this.wrapped;
	}

	@Override
	public void handle() throws FacesException {
		boolean redirected = false;
		Iterator<ExceptionQueuedEvent> it = getUnhandledExceptionQueuedEvents().iterator();
		while (it.hasNext()) {
			ExceptionQueuedEvent event = it.next();
			ExceptionQueuedEventContext eventContext = (ExceptionQueuedEventContext) event.getSource();
			Throwable e = eventContext.getException();
			if (e instanceof ViewExpiredException) {
				ViewExpiredException viewExpiredException = (ViewExpiredException) e;
				try {
					if (!redirected) {
						if (!Urls.LOGIN.value().equals(viewExpiredException.getViewId())) {
							FacesContext context = FacesContext.getCurrentInstance();
							HttpServletRequest request = (HttpServletRequest) eventContext.getContext().getExternalContext().getRequest();
							StringBuilder url = new StringBuilder(Urls.LOGIN.toString(request));
							url.append("?");
							url.append(GetParamNames.EXPIRED);
							url.append("=true");
							WebUtil.appendRefererParameter(url, request, "&");
							if (!context.getExternalContext().isResponseCommitted()) {
								context.getExternalContext().redirect(url.toString());
								redirected = true;
							}
						}
					}
				} catch (IOException ioException) {
					// System.out.println(ioException.getClass().toString() + ": " + ioException.getMessage());
					// ioException.printStackTrace();
				} finally {
					it.remove();
				}
			} else if (e instanceof AuthenticationException) {
				AuthenticationException authenticationException = (AuthenticationException) e;
				try {
					if (!redirected) {
						if (!Urls.LOGIN.value().equals(eventContext.getAttributes().get(WebUtil.EVENT_CONTEXT_VIEW_ID))) {
							FacesContext context = FacesContext.getCurrentInstance();
							HttpServletRequest request = (HttpServletRequest) eventContext.getContext().getExternalContext().getRequest();
							StringBuilder url = new StringBuilder(Urls.LOGIN.toString(request));
							url.append("?");
							url.append(GetParamNames.AUTHENTICATION_FAILED);
							url.append("=true&");
							url.append(GetParamNames.AUTHENTICATION_FAILED_MESSAGE);
							url.append("=");
							url.append(JsUtil.encodeBase64(Settings.getBoolean(SettingCodes.HIDE_DETAILED_AUTHENTICATION_ERROR, Bundle.SETTINGS,
									DefaultSettings.HIDE_DETAILED_AUTHENTICATION_ERROR) ? Messages.getMessage(MessageCodes.OPAQUE_AUTHENTICATION_ERROR_MESSAGE)
											: authenticationException.getMessage(), true));
							WebUtil.appendRefererParameter(url, request, "&");
							if (!context.getExternalContext().isResponseCommitted()) {
								SessionScopeBean sessionScopeBean = WebUtil.getSessionScopeBean();
								if (sessionScopeBean != null && WebUtil.getSessionScopeBean().isLoggedIn()) {
									context.getExternalContext().invalidateSession(); // invalidate before.... see http://jforum.icesoft.org/JForum/posts/list/3111.page
								}
								context.getExternalContext().redirect(url.toString());
								redirected = true;
							}
						}
					}
				} catch (IOException ioException) {
					// System.out.println(ioException.getClass().toString() + ": " + ioException.getMessage());
					// ioException.printStackTrace();
				} finally {
					it.remove();
				}
			} else {
				// System.out.println(e.getClass().toString() + ": " + e.getMessage());
				// e.printStackTrace();
			}
		}
		getWrapped().handle();
	}
}