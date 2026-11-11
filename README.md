JRebirth
========

JRebirth Application Framework for JavaFX graphical toolkit

A set of Showcase Applications are provided for code-centric documentation and they are used as integration test

More information is available on our website : [www.jrebirth.org](http://www.jrebirth.org)

Live feed is available on our [Blog](http://blog.jrebirth.org)

[![Join the chat at https://gitter.im/JRebirth/JRebirth](https://badges.gitter.im/Join%20Chat.svg)](https://gitter.im/JRebirth/JRebirth?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)

## Download & Build

[![Maven Central](https://img.shields.io/maven-central/v/org.jrebirth.af/core.svg?label=Maven%20Central)](https://central.sonatype.com/artifact/org.jrebirth.af/core/12.0.0)
[![Build Status](https://github.com/JRebirth/JRebirth/actions/workflows/snapshot.yml/badge.svg)](https://github.com/JRebirth/JRebirth/actions/workflows/snapshot.yml)

## Demo

Several Demos are available online :
- Apps
  - [Showcase Demo](http://www.jrebirth.org/apps/Demo.html)
  - [Mastering Tables Game](http://www.jrebirth.org/apps/MasteringTables.html)
  - [Analyzer App](http://www.jrebirth.org/apps/Analyzer.html)
- Presentations
  - [EclipseDay Prez](http://www.jrebirth.org/apps/EclipseDay.html)
  - [Breizhcamp Prez](http://www.jrebirth.org/apps/Breizhcamp.html)
  - [LightningTalk](http://www.jrebirth.org/apps/LightningTalk.html)
  - [EclipseCON](http://www.jrebirth.org/apps/EclipseCON.html)
  - [JRebirth Tour](http://www.jrebirth.org/apps/JRebirthTour.html)

## Use it

**Latest release on Maven Central** is **12.0.0** ([artifact](https://central.sonatype.com/artifact/org.jrebirth.af/core/12.0.0) · [all modules](https://central.sonatype.com/search?q=g:org.jrebirth.af+AND+v:12.0.0)).

It targets **Java 25** and **JavaFX 26**.

    <dependency>
        <groupId>org.jrebirth.af</groupId>
        <artifactId>core</artifactId>
        <version>12.0.0</version>
    </dependency>

Optional companion modules use the same version, for example `preloader` and `processor` (`provided` scope for annotation processing).

This repository’s development line may still be `12.0.0-SNAPSHOT` in `org.jrebirth.af/pom.xml`; for day-to-day work prefer the Central release above. To hack on unreleased code, build and install from source (`mvn clean install`), then depend on `12.0.0-SNAPSHOT`.

## Documentation

Documentation is available [here](http://www.jrebirth.org/doc/Toc.html).

## Build it

Requires [Git](https://git-scm.com/), **JDK 25** or newer (bytecode release 25), [Apache Maven](https://maven.apache.org/) **3.9+** (validated against Maven 3.9.11), and a JDK that can resolve **OpenJFX 26** dependencies from Maven Central.

    git clone https://github.com/JRebirth/JRebirth.git
    cd JRebirth/org.jrebirth.af
    mvn clean install

To skip test compilation and execution for the full reactor (for example if a tooling module fails test compile), use:

    mvn clean install -Dmaven.test.skip=true
