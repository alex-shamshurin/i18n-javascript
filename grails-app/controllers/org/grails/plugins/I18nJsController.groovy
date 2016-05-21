package org.grails.plugins

import org.springframework.web.servlet.support.RequestContextUtils

class I18nJsController
{
	I18NService i18NService

	def getMessages()
	{

		if (request.xhr) {

			Locale locale = RequestContextUtils.getLocale(request)
			String messages = i18NService.messagesToJavaScript(locale)

			render(contentType: "application/json") { messages }
		}

	}
}
