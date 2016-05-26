# Render all Grails i18n messages to Javascript

## This plugin includes

- i18nService to get all messages from messageSource bean
- i18nJs taglib to render in GSP
- I18nJsController to get JSON object in Javascript app


### Install

Add to build.gradle

    dependencies {
         compile "org.grails.plugins:i18n-javascript:0.4.1"
    }

### Config

In application.groovy add config with desired including and excluding prefixes

    i18nJs {
        prefixInclude = [
            'i18nJs'
        ]

        prefixExclude = [
            'default', 'other'
        ]
    }

So 'i18nJs.page1.header' will be rendered whereas 'default.home.label' will not.
Without this all items from message.properties wiil be in JS

### Get messages

In GSP use

    <g:i18nJs/>

to render all messages in output HTML

Or make request to /I18nJs/getMessages within AJAX call.

### Usage

    alert(I18N.getMessage('my.message.code'));
