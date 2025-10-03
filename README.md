# DEPRECATION NOTICE

GerritForge has decided to [change the license to BSL](https://gitenterprise.me/2025/09/30/re-licensing-gerritforge-plugins-welcome-to-gerrit-enterprise/)
therefore the Apache 2.0 version of this plugin is deprecated.
The recommended version of the zookeeper-refdb plugin is on [GitHub](https://github.com/GerritForge/zookeeper-refdb)
and the development continues on [GerritHub.io](https://review.gerrithub.io/admin/repos/GerritForge/zookeeper-refdb,general).

# Gerrit Zookeeper ref-db (DEPRECATED)

This plugin provides an implementation of the Gerrit global ref-db backed by
[Apache Zookeeper](https://zookeeper.apache.org/).

Requirements for using this plugin are:

- Gerrit v3.2 or later
- Apache Zookeeper v3.4 or later

## Typical use-case

The global ref-db is a typical use-case of a Gerrit multi-master scenario
in a multi-site setup. Refer to the
[Gerrit multi-site plugin](https://gerrit.googlesource.com/plugins/multi-site/+/master/DESIGN.md)
for more details on the high level architecture.
