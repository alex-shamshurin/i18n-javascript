package org.grails.plugins

import grails.plugins.*
import org.grails.spring.context.support.PluginAwareResourceBundleMessageSource
import org.slf4j.Logger
import org.slf4j.LoggerFactory

class I18nJavascriptGrailsPlugin extends Plugin
{
	Logger logger         = LoggerFactory.getLogger(getClass())
	// the version or versions of Grails the plugin is designed for
	def    grailsVersion  = "3.1.1 > *"
	// resources that are excluded from plugin packaging
	def    pluginExcludes = [
		"grails-app/views/**",
		"grails-app/i18n/**"
	]

	// TODO Fill in these fields
	def title           = "i18n source to JavaScript"
	def author          = "Alexander Shamshurin"
	def authorEmail     = "shamshurin.alexander@gmail.com"
	def description     = "This plugin make available in JavaScript all your i18n messages."
	// URL to the plugin's documentation
	def documentation   = "https://sergiosmind.wordpress.com/2013/07/25/getting-all-i18n-messages-in-javascript/"
	def scm             = [url: "https://github.com/salex772/i18n-javascript"]
	def issueManagement = [system: "Github Issues", url: "https://github.com/salex772/i18n-javascript/issues"]

	def loadAfter = ['i18n']

	Closure doWithSpring()
	{
		{ ->

			def application = grailsApplication
			def config = application.config
			List prefixInclude = config.getProperty('i18nJs.prefixInclude', List, [])
			List prefixExclude = config.getProperty('i18nJs.prefixExclude', List, [])

			logger.debug("i18nJs.prefixInclude: {}", prefixInclude.join(","))
			logger.debug("i18nJs.prefixExclude: {}", prefixExclude.join(","))

			def beanconf = springConfig.getBeanConfig('messageSource')

			def beandef = beanconf ? beanconf.beanDefinition : springConfig.getBeanDefinition('messageSource')

			if (beandef?.beanClassName == PluginAwareResourceBundleMessageSource.class.canonicalName) {
				beandef.beanClassName = ExposedKeysMessageSource.class.canonicalName
				beandef.propertyValues.add("prefixInclude", prefixInclude)
				beandef.propertyValues.add("prefixExclude", prefixExclude)
			}
		}
	}

	void doWithDynamicMethods()
	{
		// TODO Implement registering dynamic methods to classes (optional)
	}

	void doWithApplicationContext()
	{
		// TODO Implement post initialization spring config (optional)
	}

	void onChange(Map<String, Object> event)
	{
		// TODO Implement code that is executed when any artefact that this plugin is
		// watching is modified and reloaded. The event contains: event.source,
		// event.application, event.manager, event.ctx, and event.plugin.
	}

	void onConfigChange(Map<String, Object> event)
	{
		// TODO Implement code that is executed when the project configuration changes.
		// The event is the same as for 'onChange'.
	}

	void onShutdown(Map<String, Object> event)
	{
		// TODO Implement code that is executed when the application shuts down (optional)
	}
}
