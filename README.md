JRebirth
========

JRebirth Application Framework for JavaFX graphical toolkit

A set of Showcase Applications are provided for code-centric documentation and they are used as integration test

More information is available on our website : [www.jrebirth.org](http://www.jrebirth.org)

Live feed is available on our [Blog](http://blog.jrebirth.org)

[![Join the chat at https://gitter.im/JRebirth/JRebirth](https://badges.gitter.im/Join%20Chat.svg)](https://gitter.im/JRebirth/JRebirth?utm_source=badge&utm_medium=badge&utm_campaign=pr-badge&utm_content=badge)

## Download & Build

[![Maven Central](https://img.shields.io/maven-central/v/org.jrebirth.af/core.svg?label=Maven%20Central)](https://central.sonatype.com/artifact/org.jrebirth.af/core)
[![Build Status](http://ci.jrebirth.org/job/JRebirth-8x/badge/icon)](http://ci.jrebirth.org/job/JRebirth-8x/)

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

This repository targets **Java 25**, **JavaFX 26**, and **JRebirth AF 12.0.0-SNAPSHOT** (see `org.jrebirth.af/pom.xml`).

**Latest release on Maven Central** is **8.6.0** ([artifact search](https://central.sonatype.com/search?q=org.jrebirth.af)):

    <dependency>
        <groupId>org.jrebirth.af</groupId>
        <artifactId>core</artifactId>
        <version>8.6.0</version>
    </dependency>

To depend on this **12.x** line, build and install from source (`./mvnw clean install` from `org.jrebirth.af`), then use:

    <dependency>
        <groupId>org.jrebirth.af</groupId>
        <artifactId>core</artifactId>
        <version>12.0.0-SNAPSHOT</version>
    </dependency>

## Documentation

Documentation is available [here](http://www.jrebirth.org/doc/Toc.html).

## Build it

Requires [Git](http://git-scm.com/), **JDK 25** or newer, and **Apache Maven 3.9.11** or newer.

The reactor under `org.jrebirth.af` ships a **Maven Wrapper** (`mvnw` / `mvnw.cmd`) that downloads and uses Maven **3.9.11**, so you do not need a global Maven install for day-to-day builds:

    git clone https://github.com/JRebirth/JRebirth.git
    cd JRebirth/org.jrebirth.af
    ./mvnw clean install

`maven-enforcer-plugin` runs on the build and fails fast if **Java** is older than 25 or **Maven** is older than 3.9.11 (when you invoke a system `mvn` instead of the wrapper).

To skip test compilation and execution for the full reactor:

    ./mvnw clean install -Dmaven.test.skip=true

To regenerate the wrapper after changing the pinned Maven version, run from `org.jrebirth.af` (use `-Denforcer.skip=true` if your JDK is below 25 and you only need to refresh wrapper files):

    mvn -Denforcer.skip=true -N wrapper:wrapper -Dmaven=3.9.11
