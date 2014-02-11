JRebirth Plugin for Forge
=========================

This project is a effort to create a plugin for Forge to work easily with JRebirth JavaFX Framework. 
This Plugin makes life easy to work with Maven + JavaFX with JRebirth Framework. 

    This project is currently in progress. You may not get full features currenlty.
    Stay tuned.

Prerequisite
=============
* [JDK 7](http://www.oracle.com/technetwork/java/javase/downloads/jdk7-downloads-1880260.html)
* [JBoss Forge](http://forge.jboss.org)
* IDE that supports Maven (Eclipse, NetBeans, IntelliJ etc.)

Installing
==========
* Run the forge
* Type following command in forge shell to install the plugin
      `forge git-plugin https://github.com/JRebirth/forge-jrebirth-plugin.git`
* Some simple flow using the jrebirth 
      > new-project --named jrebirthsample
      > jrebirth setup
      > jrebirth ui-create --name Intro
      > jrebirth resource-create --all
      > jrebirth color-add-web --name window_background --hex CCCCCC
      > jrebirth app-config --key developerMode --value true
      > jrebirth app-config --key developerMode
      > jrebirth service-create --name RestCall

Commands
========
JRebirth executible command `jrebirth`. Add following sub commands. 

* `setup` - Instals basic dependency for JRebirth.(core, JAVA_HOME javafx runtime, slf4j).
    + Setup also creates jrebirth.properties, MainApp, and resource folders for fonts, images and styles. 
    + `setup --module presentation` - adds _Presentation_ module to your project
* `ui-create` - Creates Model, View and Controller calsses for given name. Use `--name` to provide name.
     `--controllerGenerate` - default _true_ - Creates Controller
     `--beanGenerate` - default _true_ - Created Bean
     `--fxmlGenerate` - default _false_ - Create FXML
    
NOTE: For best practice, above commad creates sub-package and classes inside `ui.[name]` or `ui.fxml.[name]` package in top level package.


* `command-create` - Creates Command calss for given name. Use `--name` to provide name.
* `service-create` - Creates Service class for given name. Use `--name` to provide name.
* `resource-create` - Creates Resource class for given name. Use `--name` to provide name.
    * `--all` - default _true_ - Creates all resource for the application. 
    * `--colorGenerate` - default _false_ - flag - Creates Color 
    * `--fontGenerate` - default _false_ - flag - Creates Font 
    * `--imageGenerate` - default _false_ - flag -Creates Image 

NOTE: For best practice, above commad creates the classes inside `command`, `service` or `resource` package in top level package.




* `color-add-web` - Creates a constant in Color resource interface using colorname/value
     `--name` - Color constant name
     `--hex` - Color indicated by hexa decimal value

    
* `app-config` - updates the jrebirth.properties file 
    + `--key` - use \*tab\* to see all the keys you can update.
    + `--value` - sets value to a key
    + `--showAll` - default _false_ - flag -Displays all the proeprties value of the app.

NOTE: If you provide only the key then it will display the value of the key. If key is new it sets a new key.

