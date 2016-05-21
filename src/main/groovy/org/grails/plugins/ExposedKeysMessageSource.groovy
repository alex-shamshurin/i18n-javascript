package org.grails.plugins

import org.grails.spring.context.support.PluginAwareResourceBundleMessageSource

class ExposedKeysMessageSource extends PluginAwareResourceBundleMessageSource
{

	List prefixInclude
	List prefixExclude

	public Set getAllKeys(Locale locale)
	{

		org.grails.spring.context.support.ReloadableResourceBundleMessageSource.PropertiesHolder props = getMergedProperties(locale)
		Set propsSet = props.getProperties().keySet()

		Set returnSet = [] as Set
		returnSet.addAll(propsSet)

		return returnSet
	}

	public List<org.grails.spring.context.support.ReloadableResourceBundleMessageSource.PropertiesHolder> getAllProperties(Locale locale)
	{
		List<org.grails.spring.context.support.ReloadableResourceBundleMessageSource.PropertiesHolder> holders = []
		holders << getMergedProperties(locale)

		holders

	}

}
