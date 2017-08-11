package com.github.robsonbittencourt;

def parseConfig(Closure body) {
    def config = [:]
    
    if (body != null) {
        body.resolveStrategy = Closure.DELEGATE_FIRST
        body.delegate = config
        body()
    } 

    return config
}