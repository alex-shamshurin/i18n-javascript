package org.grails.plugins

import grails.config.Config
import grails.core.support.GrailsConfigurationAware
import groovy.util.logging.Slf4j
import org.grails.spring.context.support.PluginAwareResourceBundleMessageSource
import org.grails.spring.context.support.ReloadableResourceBundleMessageSource.PropertiesHolder

@Slf4j
class ExposedKeysMessageSource extends PluginAwareResourceBundleMessageSource implements GrailsConfigurationAware
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

	@Override
	void setConfiguration(Config co) {
		prefixInclude = co.getProperty('i18nJs.prefixInclude', List, [])
		prefixExclude = co.getProperty('i18nJs.prefixExclude', List, [])

		log.debug("i18nJs.prefixInclude: {}", prefixInclude.join(","))
		log.debug("i18nJs.prefixExclude: {}", prefixExclude.join(","))
	}
}
