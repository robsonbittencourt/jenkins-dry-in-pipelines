import com.github.robsonbittencourt.Maven

def call(String command, Closure body=null) {
    new Maven().executeCommand(command, body)
}