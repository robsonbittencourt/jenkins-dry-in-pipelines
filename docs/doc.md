[Back to Home](index.md)

## Documentation

- [GIT](#git)
  - [downloadSource](#download-source)
  - [requestUserInputTag](#request-user-input-tag)
- [Maven](#maven)
  - [mvn](#execute-maven-command)
  - [mvnPackage](#maven-package)
  - [mvnTest](#maven-test)
  - [mvnInstall](#maven-install)
- [Docker](#docker)
  - [publishDockerImage](#publish-docker-image)

### GIT

#### Download Source

| Function        | downloadSource |
|:----------------|:--------------------|
| Description     | Download source from repository |    
| Parameters      | url (optional), branch(optional) |
| Return          | None |
| Details         | This function downloads the source code from the repository. If you do not know any parameters will be downloaded from the repository and branch where Jenkinsfile is located. If you need to specify the repository or branch use the respective parameters.  | 

Example of use:

```
/*Without parameters*/
node {
  downloadSource()
}
```

```
/*With parameters*/
node {
  downloadSource("https://github.com/hubot-js/hubot.js", "development")
}
```

#### Request User Input Tag

| Function        | requestUserInputTag |
|:----------------|:--------------------|
| Description     | Stop the pipeline execution and request to user inform a tag |    
| Parameters      | None |
| Return          | The selected tag |
| Details         | This function hapicks a 10 minutes timeout. If a tag is not selected the pipeline is canceled | 

Example of use:

```
node {
  stage("Print tag") {
    def tag = requestUserInputTag()

    sh("echo ${tag}")
  }
}
```

-------------------------------------------------------------------------------------------------------------------

### Maven

#### Execute Maven Command

| Function        | mvn                 |
|:----------------|:--------------------|
| Description     | Execute a maven command |    
| Parameters      | mavenCommand, jdkName (optional), mavenName(optional) |
| Return          | None |
| Details         | In command parameter *mvn* isn't necessary. *jdkName* and *mavenName* has *jdk* and *maven* as default value. If your Jenkins use another values, you should let them know. | 

Example of use:

```
/*With default values*/
node {
  stage("Maven package") {
      mvn "clean package"
  }
}

/*Without default values*/
node {
  stage("Maven package") {
    mvn("clean package") {
      jdkName = "jdk8"
      mavenName = "maven3.5"
    }
  }
}
```

-------------------------------------------------------------------------------------------------------------------

#### Maven Package   

| Function        | mvnPackage          |
|:----------------|:--------------------|
| Description     | Execute maven package goal |    
| Parameters      | addStage(optional), jdkName (optional), mavenName(optional) |
| Return          | None |
| Details         | This function by default adds a stage called Maven package. Set addStage as false if you use this function inside another stage. This function *jdkName* and *mavenName* has *jdk* and *maven* as default value. If your Jenkins use another values, you should let them know. | 

Example of use:

```
/*Use default stage*/
node {
  mvnPackage()
}
```

```
/*Inside another stage*/
node {
  stage("Maven package") {
    mvnPackage(false)
  }
}
```

-------------------------------------------------------------------------------------------------------------------

#### Maven Test   

| Function        | mvnTest             |
|:----------------|:--------------------|
| Description     | Execute maven test goal |    
| Parameters      | addStage(optional), jdkName (optional), mavenName(optional) |
| Return          | None |
| Details         | This function by default adds a stage called Maven test. Set addStage as false if you use this function inside another stage. This function *jdkName* and *mavenName* has *jdk* and *maven* as default value. If your Jenkins use another values, you should let them know. | 

Example of use:

```
/*Use default stage*/
node {
  mvnTest()
}
```

```
/*Inside another stage*/
node {
  stage("Maven test") {
    mvnTest(false)
  }
}
```

-------------------------------------------------------------------------------------------------------------------

#### Maven Install   

| Function        | mvnInstall          |
|:----------------|:--------------------|
| Description     | Execute maven install goal |    
| Parameters      | addStage(optional), jdkName (optional), mavenName(optional) |
| Return          | None |
| Details         | This function by default adds a stage called Maven install. Set addStage as false if you use this function inside another stage. This function *jdkName* and *mavenName* has *jdk* and *maven* as default value. If your Jenkins use another values, you should let them know. | 

Example of use:

```
/*Use default stage*/
node {
  mvnInstall()
}
```

```
/*Inside another stage*/
node {
  stage("Maven install") {
      mvnInstall(false)
  }
}
```

-------------------------------------------------------------------------------------------------------------------

### Docker

#### Publish Docker Image   

| Function        | publishDockerImage          |
|:----------------|:--------------------|
| Description     | Does the process necessary to build the publication of a docker image in DockerHub |    
| Parameters      | image, tags(optional) |
| Return          | None |
| Details         | This function creates a stage that builds the image, logs in and publishes it in Dockerhub. It has as parameter the name of the image. Optionally you can inform a list of tags and all will be published on the same stage. If no tag is entered, the latest tag will be used. In order for the Dockerhub login to work correctly you must have a credential named DOCKER configured on Jenkins. | 

Example of use:

```
/*With latest tag*/
node {
  publishDockerImage "robsonbittencourt/payara-micro-test"
}
```

```
/*Publish other tags*/
node {
  publishDockerImage "robsonbittencourt/payara-micro-test", ["latest", "1.0", "development"]
}
```

-------------------------------------------------------------------------------------------------------------------