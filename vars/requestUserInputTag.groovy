def call() {
    timeout(time:10, unit:'MINUTES') {
        def tag = input ( message : 'Please select the tag that will be used:',
                      parameters: [[
                          $class: 'ChoiceParameterDefinition',
                          choices:sh(script: 'git fetch --tags && git tag', returnStdout: true),
                          description: 'The tag that will be used',
                          name: 'TAG'
                      ]]
                    )
        return tag
    }
}