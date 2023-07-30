node {
    // define the secrets and the env variables
    // engine version can be defined on secret, job, folder or global.
    // the default is engine version 2 unless otherwise specified globally.
    def secrets = [
            [path: 'dgtic-secrets/sc-secret-test', engineVersion: 1, secretValues: [
                    [envVar: 'testing', vaultKey: 'secret'],
                    [envVar: 'testing_again', vaultKey: 'clientid']]]
    ]

    // optional configuration, if you do not provide this the next higher configuration
    // (e.g. folder or global) will be used
    def configuration = [vaultUrl: 'http://my-very-other-vault-url.com',
                         vaultCredentialId: 'my-vault-cred-id',
                         engineVersion: 1]
    // inside this block your credentials will be available as env variables
    withVault([ vaultSecrets: secrets]) {
        sh 'echo $testing'
        sh 'echo $testing_again'
        sh 'echo $another_test'
    }
}