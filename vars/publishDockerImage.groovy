import com.github.robsonbittencourt.Docker

def call(String image, List<String> tags=["latest"]) {
    stage("Publish Docker image") {
        def docker = new Docker()
        
        docker.build(image, tags)
        docker.push(image, tags)
    }
}

