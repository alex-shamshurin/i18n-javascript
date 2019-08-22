package org.grails.plugins

import grails.plugins.Plugin
import groovy.util.logging.Slf4j
import org.grails.spring.context.support.PluginAwareResourceBundleMessageSource

@Slf4j
class I18nJavascriptGrailsPlugin extends Plugin
{
	def grailsVersion   = "3.0.0 > *"
	def title           = "i18n source to JavaScript"
	def author          = "Alexander Shamshurin"
	def authorEmail     = "shamshurin.alexander@gmail.com"
	def description     = "This plugin make available in JavaScript all your i18n messages."
	def documentation   = "https://sergiosmind.wordpress.com/2013/07/25/getting-all-i18n-messages-in-javascript/"
	def scm             = [url: "https://github.com/salex772/i18n-javascript"]
	def issueManagement = [system: "Github Issues", url: "https://github.com/salex772/i18n-javascript/issues"]
	def loadAfter = ['i18n']

	Closure doWithSpring()
	{
		{ ->
			jsMessageSource(ExposedKeysMessageSource) { bean ->
				bean.autowire = true
			}
		}
	}
}
