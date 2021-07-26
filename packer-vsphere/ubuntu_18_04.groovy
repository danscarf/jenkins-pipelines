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
          submoduleCfg: [
          ],
          userRemoteConfigs: [
            [
              url: 'https://github.com/rainpole/packer-vsphere.git'
            ]
          ]
        ]
      )
    }
    stage('Configure source for build'){
      withCredentials([usernamePassword(credentialsId: 'vcenter_packer_creds', passwordVariable: 'VC_PASSWORD', usernameVariable: 'VC_USERNAME')]) {
        sh 'echo $VC_USERNAME > out.txt'
      }
    }
  }
}
catch (e) {
  echo "Exception thrown: $e"
}
finally {
  echo 'This will always run'
}