package com.github.robsonbittencourt;

def downloadSource(String url=null, String branch=master) {
    stage ("Download source") {
        if (url == null) {
            checkout scm
        } else {
            git branch: branch, url: url
        }
    }
}