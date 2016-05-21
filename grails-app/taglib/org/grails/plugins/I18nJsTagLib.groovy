package org.grails.plugins

import org.springframework.web.servlet.support.RequestContextUtils

class I18nJsTagLib {

	static defaultEncodeAs = [taglib: 'none']

	def i18NService

	def i18nJs = { attrs, body ->

		Locale locale = RequestContextUtils.getLocale(request)
		String messages = i18NService.messagesToJavaScript(locale)


		String script = """
		<script type=text/javascript>
			function I18N() {
				this.messages = {};
				this.setMessages = function (messages) {
					this.messages = messages;
				}
				this.getMessage = function (name) {
					return this.messages[name];
				}
			}
			window.I18N = new I18N();
			window.I18N.setMessages(JSON.parse('$messages'));
		</script>
		"""

		out  << script
	}
}
