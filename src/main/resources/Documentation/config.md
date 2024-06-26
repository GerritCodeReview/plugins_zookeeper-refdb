
@PLUGIN@ Configuration
=========================

Global configuration of the @PLUGIN@ plugin is done in the @PLUGIN@.config file in the site's etc directory.

File '@PLUGIN@.config'
--------------------

## Sample configuration.

```
[ref-database "zookeeper"]
  connectString = "zookeeperhost:2181"
  rootNode = "/gerrit/multi-site"
  transactionLockTimeoutMs = 1000
```

## Sample SSL configuration
```
[ref-database "zookeeper"]
  connectString = "zookeeperhost:2281"
  sslConnection = true
  rootNode = "/gerrit/multi-site"
  transactionLockTimeoutMs = 1000
```

## Configuration parameters

```ref-database.zookeeper.connectString```
:   Connection string to Zookeeper

```ref-database.zookeeper.rootNode```
:   Root node to use in Zookeeper to store/retrieve information

    Defaults: "/gerrit/multi-site"


```ref-database.zookeeper.sessionTimeoutMs```
:   Zookeeper session timeout in milliseconds

    Defaults: 1000

```ref-database.zookeeper.connectionTimeoutMs```
:   Zookeeper connection timeout in milliseconds

    Defaults: 1000

```ref-database.zookeeper.retryPolicyBaseSleepTimeMs```
:   Configuration for the base sleep timeout in milliseconds of the
    [BoundedExponentialBackoffRetry](https://curator.apache.org/apidocs/org/apache/curator/retry/BoundedExponentialBackoffRetry.html) [policy](https://curator.apache.org/curator-client/index.html) used for the Zookeeper connection

    Defaults: 1000

```ref-database.zookeeper.retryPolicyMaxSleepTimeMs```
:   Configuration for the maximum sleep timeout in milliseconds of the
    [BoundedExponentialBackoffRetry](https://curator.apache.org/apidocs/org/apache/curator/retry/BoundedExponentialBackoffRetry.html) [policy](https://curator.apache.org/curator-client/index.html) used for the Zookeeper connection

    Defaults: 3000

```ref-database.zookeeper.retryPolicyMaxRetries```
:   Configuration for the maximum number of retries of the
    [BoundedExponentialBackoffRetry](https://curator.apache.org/apidocs/org/apache/curator/retry/BoundedExponentialBackoffRetry.html) [policy](https://curator.apache.org/curator-client/index.html) used for the Zookeeper connection

    Defaults: 3

```ref-database.zookeeper.casRetryPolicyBaseSleepTimeMs```
:   Configuration for the base sleep timeout in milliseconds of the
    [BoundedExponentialBackoffRetry](https://curator.apache.org/apidocs/org/apache/curator/retry/BoundedExponentialBackoffRetry.html) [policy](https://curator.apache.org/curator-client/index.html) used for the Compare and Swap
    operations on Zookeeper

    Defaults: 1000

```ref-database.zookeeper.casRetryPolicyMaxSleepTimeMs```
:   Configuration for the maximum sleep timeout in milliseconds of the
    [BoundedExponentialBackoffRetry](https://curator.apache.org/apidocs/org/apache/curator/retry/BoundedExponentialBackoffRetry.html) [policy](https://curator.apache.org/curator-client/index.html) used for the Compare and Swap
    operations on Zookeeper

    Defaults: 3000

```ref-database.zookeeper.casRetryPolicyMaxRetries```
:   Configuration for the maximum number of retries of the
    [BoundedExponentialBackoffRetry](https://curator.apache.org/apidocs/org/apache/curator/retry/BoundedExponentialBackoffRetry.html) [policy](https://curator.apache.org/curator-client/index.html) used for the Compare and Swap
    operations on Zookeeper

    Defaults: 3

```ref-database.zookeeper.transactionLockTimeoutMs```
:   Configuration for the Zookeeper Lock timeout (in milliseconds) used when
    acquires the exclusive lock for a reference.

    Defaults: 1000

```ref-database.zookeeper.sslConnection```
:   Enable ssl for Zookeeper connection.

    Defaults: false

```ref-database.zookeeper.sslKeyStoreLocation```
:   Optional configuration of the path to the ssl key store location.

```ref-database.zookeeper.sslTrustStoreLocation```
:   Optional configuration of the path to the ssl trust store for server-side X.509 certificate validation.

File '@PLUGIN@.secure.config'
--------------------

## Configuration parameters

```ref-database.zookeeper.sslKeyStorePassword```
:   Optional configuration for the password to the ssl key store.

```ref-database.zookeeper.sslTrustStorePassword```
:   Optional configuration for the password to the ssl trust store.

```ref-database.zookeeper.username```
:   Optional, if authentication is required, configuration for the username to the zookeeper node.

```ref-database.zookeeper.password```
:   Optional, if authentication is required, configuration for the password to the zookeeper node.


