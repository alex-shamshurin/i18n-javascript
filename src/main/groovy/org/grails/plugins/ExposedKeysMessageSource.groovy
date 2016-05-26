package org.grails.plugins

import org.grails.spring.context.support.PluginAwareResourceBundleMessageSource
import org.grails.spring.context.support.ReloadableResourceBundleMessageSource.PropertiesHolder

class ExposedKeysMessageSource extends PluginAwareResourceBundleMessageSource
{

	List prefixInclude
	List prefixExclude

	Set getAllKeys(Locale locale)
	{
		[] + getMergedProperties(locale).properties.keySet()
	}

	List<PropertiesHolder> getAllProperties(Locale locale)
	{
		[getMergedProperties(locale)]
	}
}
