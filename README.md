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

To depend on this **12.x** line, build and install from source (`mvn clean install`), then use:

    <dependency>
        <groupId>org.jrebirth.af</groupId>
        <artifactId>core</artifactId>
        <version>12.0.0-SNAPSHOT</version>
    </dependency>

## Documentation

Documentation is available [here](http://www.jrebirth.org/doc/Toc.html).

## Build it

Requires [Git](http://git-scm.com/), **JDK 25** or newer, [Apache Maven](http://maven.apache.org/) **3.9+** (the build is validated against Maven 3.9.11).

    git clone https://github.com/JRebirth/JRebirth.git
    cd JRebirth/org.jrebirth.af
    mvn clean install

To skip test compilation and execution for the full reactor (for example if a tooling module fails test compile), use:

    mvn clean install -Dmaven.test.skip=true

## Publish to Maven Central

The build changes live on branch **`cursor/maven-central-pom-only`** (pull request against `12.x-dev`). After that PR is merged, the following applies.

Releases are uploaded with the [Sonatype Central Publisher Portal](https://central.sonatype.org/publish/publish-portal-maven/) using `central-publishing-maven-plugin`. Activate the `central-publish` profile, use a **release** version (not `-SNAPSHOT`), and configure `~/.m2/settings.xml` with a server id **`central`** and your [portal user token](https://central.sonatype.org/publish/generate-portal-token/). You also need a [GPG key](https://central.sonatype.org/publish/gpg/) for signing.

    cd org.jrebirth.af
    mvn -Pcentral-publish -Dmaven.test.skip=true clean deploy

When you publish a new `org.jrebirth:organization` parent, run the same profile from `org.jrebirth`:

    cd org.jrebirth
    mvn -Pcentral-publish clean deploy

### Dry run (inspect the deployment bundle)

To build the same artifacts and **Central bundle** without uploading to the portal, set **`skipPublishing`** (see [Sonatype: skipPublishing](https://central.sonatype.org/publish/publish-portal-maven/#skipPublishing)):

    cd org.jrebirth.af
    mvn -Pcentral-publish -DskipPublishing=true -Dmaven.test.skip=true clean deploy

Then review:

- **`org.jrebirth.af/target/central-publishing/central-bundle.zip`** — unzip to inspect the full deployment
- **`*/target/central-staging/`** under each module — staged files before bundling
