node {
    stage('vault demo') {

        try {
            // optional configuration, if you do not provide this the next higher configuration
            // (e.g. folder or global) will be used
            def configuration = [vaultUrl         : 'http://vaultdgtic.sep.gob.mx',
                                 vaultCredentialId: 'e9301595-9c01-4777-aa13-7b088257b1b4',
                                 engineVersion    : 2]
            // inside this block your credentials will be available as env variables
            // define the secrets and the env variables
            // engine version can be defined on secret, job, folder or global.
            // the default is engine version 2 unless otherwise specified globally.
            def secrets = [
                    [path        : 'secrets/creds/my-secret-text', engineVersion: 1,
                     secretValues: [
                             [envVar: 'testing', vaultKey: 'secret_id'],
                             [envVar: 'testing_again', vaultKey: 'role_id']
                     ]
                    ]
            ]
            withVault([configuration: configuration, vaultSecrets: secrets]) {
                sh 'echo $testing'
                sh 'echo $testing_again'
                sh 'echo $another_test'
            }
        }
        catch (e) {
            sh('echo e')
        }

    }
}