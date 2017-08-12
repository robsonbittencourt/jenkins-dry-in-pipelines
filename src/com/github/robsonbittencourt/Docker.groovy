package com.github.robsonbittencourt;

def login() {
    withCredentials([[$class: 'UsernamePasswordMultiBinding', credentialsId: 'DOCKER', usernameVariable: 'USERNAME', passwordVariable: 'PASSWORD']]) {
        sh "docker login -u=${env.USERNAME} -p=${env.PASSWORD}"
    }
}

def build(image, List<String> tags=["latest"]) {
    tags.each { tag ->
        sh "docker build -t ${image}:${tag} ."
    }
}

def push(image, List<String> tags=["latest"]) {
    login()
    tags.each { tag ->
        sh "docker push docker.io/${image}:${tag}"
    }
}