# Thundr CI (Codeship) Integration

The current codeship deployment status (click on image for more details)

[ ![Codeship Status for marcschregardus/thundr-contrib-ci](https://codeship.com/projects/ba4edb60-e4a3-0132-ac5f-324f5bb7e2d2/status?branch=master)](https://codeship.com/projects/81876)

## Overview

This project provides a simple hook into a standard Thundr project deployed via codeship that allows a Consultant (or whoever) to know
exactly what version of the project is currently deployed on any given server. The user can view this information by either 
calling a standard html page containing the info, or by obtaining the data in JSON format (for use in a custom Admin terminal,
for example).

## Project Setup

### Maven

Include this library in your pom file (currently requires Thundr 2.0.0 or above). Include in `<dependencies>` element.

    <dependency>
        <groupId>com.schregardus.thundr.ci</groupId>
        <artifactId>thundr-contrib-ci</artifactId>
        <version>1.0</version>
        <scope>compile</scope>
    </dependency>
    
This project is not currently on Maven Central, so you will also need to include the repository location (include in `<repositories>` element). I.e.

    <repository>
        <snapshots>
            <enabled>false</enabled>
        </snapshots>
        <id>bintray-marcschregardus-3wks</id>
        <name>bintray</name>
        <url>http://dl.bintray.com/marcschregardus/3wks</url>
    </repository>
    
The resources for the project need to be filtered, so that the values to display are injected by the build server (include in `<resources>` element). I.e.

    <resource>
        <directory>src/main/resources</directory>
        <filtering>true</filtering>
    </resource>
    
### Thundr (your project)

The follow properties need to be added to your `application.properties` file. These properties are replaced with the values from the provided by codeship, and 
injected into the `CIController` that is used to display this information to the user.

    # CI (Codeship) environment variables - replaced by build server
    ciBuildNumber=@ci.build.number@
    ciBuildUrl=@ci.build.url@
    ciBranch=@ci.branch@
    ciCommitId=@ci.commit.id@
    ciCommitterName=@ci.committer.name@
    ciCommitterEmail=@ci.committer.email@
    ciCommitterUsername=@ci.committer.username@
    ciMessage=@ci.message@

The path `/ci/`, along with all url's underneath should be publicly accessible. For example, if you are hosting your project in the dev environment on the server
`http://my-project-dev.appspot.com` then the CI information will be under `http://my-project-dev.appspot.com/ci/`. The routes that are used are as follows:

* `/ci/` - The main information page. If the request contains the mime type `application/json` then the response will be in JSON - otherwise the response is returned in HTML
* `/ci/buildNumber` - The Codeship build number
* `/ci/buildUrl` - The link to the Codeship Build
* `/ci/branch` - The Git/Bitbucket branch used for the deploy
* `/ci/commitId` - The Commit Hash for the deploy
* `/ci/committerName` -  The Name of the person who committed (and triggered) the build
* `/ci/committerEmail` - The Email Address of the person who committed (and triggered) the build
* `/ci/committerUsername` - The Username of the person who committed (and triggered) the build
* `/ci/message` - The last Commit Message for that build

### Codeship Setup

Codeship exposes all the above variables, but these need to be passed into maven before the build occurs. This is achieved using command line parameters that reference 
each of these variables. Therefore, if deploying to the dev enviroment (say), the deployment pipeline would look as follows:

    mvn package -P dev -DskipTests \
     -Dci.build.number="$CI_BUILD_NUMBER" \
     -Dci.build.url="$CI_BUILD_URL" \
     -Dci.branch="$CI_BRANCH" \
     -Dci.commit.id="$CI_COMMIT_ID" \
     -Dci.committer.name="$CI_COMMITTER_NAME" \
     -Dci.committer.email="$CI_COMMITTER_EMAIL" \
     -Dci.committer.username="$CI_COMMITTER_USERNAME" \
     -Dci.message="$CI_MESSAGE"

## Notes

This is currently residing in my repo to see if it works for 3wks - and is also hosted using Bintray.

https://bintray.com/marcschregardus/3wks/thundr-contrib-ci/1.0/view

If it works out, we'll consider moving into the main Thundr space.