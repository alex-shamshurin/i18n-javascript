package org.grails.plugins

import grails.converters.JSON

class I18NService {
	/** Dependency Injection for ExposedKeysMessageSource */
	ExposedKeysMessageSource messageSource

	/**
	 * Filter a key according to plugin settings
	 * @param key
	 * @return
	 */
	private boolean keyAllowed(String key) {
		String keyPart = key
		int firstDotIndex = key.indexOf(".")
		if (firstDotIndex > 0) {
			keyPart = key.substring(0, firstDotIndex)
		}
		if (!messageSource.prefixInclude.isEmpty() && !(keyPart in messageSource.prefixInclude)) {
			return false
		}

		if (!messageSource.prefixExclude.isEmpty() && (keyPart in messageSource.prefixExclude)) {
			return false
		}

		return true
	}

	/**
	 * Get's all messages for the Locale and  encode to a JSON String.
	 */
	String messagesToJavaScript(Locale locale) {

		def messages = [:]

		def holders = messageSource.getAllProperties(locale)

		for(def holder : holders) {
			Map props = holder.getProperties()
			Set keys = props.keySet()
			for(String key : keys) {
				if (keyAllowed(key)) {
					messages[key] = props.get(key)
				}
			}
		}

		return ((messages as JSON).encodeAsJavaScript())

	}

}
