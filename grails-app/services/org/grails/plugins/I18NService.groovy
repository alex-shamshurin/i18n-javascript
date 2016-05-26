package org.grails.plugins

import grails.converters.JSON

class I18NService {
	/** Dependency Injection for ExposedKeysMessageSource */
	ExposedKeysMessageSource messageSource

	/**
	 * Filter a key according to plugin settings
	 */
	private boolean keyAllowed(String key) {
		String keyPart = key
		int firstDotIndex = key.indexOf(".")
		if (firstDotIndex > 0) {
			keyPart = key.substring(0, firstDotIndex)
		}
		if (messageSource.prefixInclude && !(keyPart in messageSource.prefixInclude)) {
			return false
		}

		if (messageSource.prefixExclude && (keyPart in messageSource.prefixExclude)) {
			return false
		}

		return true
	}

	/**
	 * Gets all messages for the Locale and encodes to a JSON String.
	 */
	String messagesToJavaScript(Locale locale) {

		def messages = [:]

		for(holder in messageSource.getAllProperties(locale)) {
			Map props = holder.getProperties()
			for(String key in props.keySet()) {
				if (keyAllowed(key)) {
					messages[key] = props.get(key)
				}
			}
		}

		return ((messages as JSON).encodeAsJavaScript())
	}
}
