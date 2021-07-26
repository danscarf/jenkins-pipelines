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
        sh "sed -i '/vcenter_username/s/\".*\"/\"$VC_USERNAME\"/' vsphere.pkrvars.hcl"
        sh "sed -i '/vcenter_password/s/\".*\"/\"$VC_PASSWORD\"/' vsphere.pkrvars.hcl"
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