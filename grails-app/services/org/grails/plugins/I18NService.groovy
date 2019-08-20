package org.grails.plugins

import grails.converters.JSON

class I18NService {
	/** Dependency Injection for ExposedKeysMessageSource */
	ExposedKeysMessageSource jsMessageSource

	/**
	 * Filter a key according to plugin settings
	 */
	private boolean keyAllowed(String key) {
		String keyPart = key
		int firstDotIndex = key.indexOf(".")
		if (firstDotIndex > 0) {
			keyPart = key.substring(0, firstDotIndex)
		}
		if (jsMessageSource.prefixInclude && !(keyPart in jsMessageSource.prefixInclude)) {
			return false
		}

		if (jsMessageSource.prefixExclude && (keyPart in jsMessageSource.prefixExclude)) {
			return false
		}

		return true
	}

	/**
	 * Gets all messages for the Locale and encodes to a JSON String.
	 */
	String messagesToJavaScript(Locale locale) {

		def messages = [:]

		for(holder in jsMessageSource.getAllProperties(locale)) {
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
