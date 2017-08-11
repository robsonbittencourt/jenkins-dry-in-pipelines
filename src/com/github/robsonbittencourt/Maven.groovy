package com.github.robsonbittencourt;

def executeGoal(String goal, boolean addStage=true, Closure body=null) {
    if (addStage) {
        stage("Maven ${goal}") {
            executeCommand("clean ${goal}", body)
        }
    } else {
        executeCommand("clean ${goal}", body)
    }  
}

def executeCommand(String command, Closure body=null) {
    def config = new Utils().parseConfig(body)    

    def jdkName = config?.jdkName ?: "jdk"
    def mavenName = config?.mavenName ?: "maven"

    withEnv(["JAVA_HOME=${ getToolName(jdkName) }", "PATH+MAVEN=${ getToolName(mavenName) }/bin:${env.JAVA_HOME}/bin"]) {
        sh "mvn ${command}"
    }
}

def getToolName(String toolName) {
    try {
        return tool(toolName)
    } catch (Exception ex) {
        error "The tool ${toolName} doesn't exist"
    }
}