try{
  node('linux'){
    stage('Code Checkout') {
        checkout(
        [
          $class: 'GitSCM',
          branches: [[name: '*/main']],
          doGenerateSubmoduleConfigurations: false,
          extensions: [
          ],
          submoduleCfg: [],
          userRemoteConfigs: [
            [
              url: 'https://github.com/rainpole/packer-vsphere.git'
            ]
          ]
        ]
      )
    }
  }

}
catch (e) {
  echo "Exception thrown: $e"
}
finally {
  echo 'This will always run'
}