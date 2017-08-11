import com.github.robsonbittencourt.Maven

def call(boolean addStage=true, Closure body=null) {
    new Maven().executeGoal("package", addStage, body) 
}