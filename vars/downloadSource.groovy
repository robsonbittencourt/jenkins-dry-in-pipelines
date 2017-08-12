import com.github.robsonbittencourt.Git

def call(String url=null, String branch="master") {
    new Git().downloadSource(url, branch)
}