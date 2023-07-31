node {
    stage('vault demo') {


        // optional configuration, if you do not provide this the next higher configuration
        // (e.g. folder or global) will be used
        def configuration = [vaultUrl         : 'http://vaultdgtic.sep.gob.mx',
                             vaultCredentialId: 'vault-jenkins-role',
                             engineVersion    : 2]
        // inside this block your credentials will be available as env variables
        // define the secrets and the env variables
        // engine version can be defined on secret, job, folder or global.
        // the default is engine version 2 unless otherwise specified globally.
        def secrets = [
                [path        : 'secrets/creds/my-secret-text', engineVersion: 2,
                 secretValues: [
                         [envVar: 'testing', vaultKey: 'secret'],
                         [envVar: 'testing_again', vaultKey: 'clientid']
                 ]
                ]
        ]

        try {
            withVault([ vaultSecrets: secrets]) {
                sh 'echo $testing'
                sh 'echo $testing_again'
                sh 'echo $another_test'
            }
        }
        catch (e) {
            e.printStackTrace()
        }

    }
}