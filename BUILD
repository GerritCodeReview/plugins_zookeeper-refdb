load("//tools/bzl:junit.bzl", "junit_tests")
load(
    "//tools/bzl:plugin.bzl",
    "PLUGIN_DEPS",
    "PLUGIN_TEST_DEPS",
    "gerrit_plugin",
)

gerrit_plugin(
    name = "zookeeper-refdb",
    srcs = glob(["src/main/java/**/*.java"]),
    manifest_entries = [
        "Gerrit-PluginName: zookeeper-refdb",
        "Gerrit-Module: com.googlesource.gerrit.plugins.validation.dfsrefdb.zookeeper.ZkValidationModule",
        "Gerrit-InitStep: com.googlesource.gerrit.plugins.validation.dfsrefdb.zookeeper.ZkInit",
        "Implementation-Title: zookeeper ref-db plugin",
        "Implementation-URL: https://review.gerrithub.io/admin/repos/GerritForge/plugins_zookeeper",
    ],
    resources = glob(["src/main/resources/**/*"]),
    deps = [
        "@curator-client//jar",
        "@curator-framework//jar",
        "@curator-recipes//jar",
        "@global-refdb//jar",
        "@zookeeper//jar",
    ],
)

junit_tests(
    name = "zookeeper-refdb_tests",
    srcs = glob(["src/test/java/**/*.java"]),
    resources = glob(["src/test/resources/**/*"]),
    tags = [
        "local",
        "zookeeper",
    ],
    deps = [
        ":zookeeper-refdb__plugin_test_deps",
    ],
)

java_library(
    name = "zookeeper-refdb__plugin_test_deps",
    testonly = 1,
    visibility = ["//visibility:public"],
    exports = PLUGIN_DEPS + PLUGIN_TEST_DEPS + [
        ":zookeeper-refdb__plugin",
        "@curator-framework//jar",
        "@curator-recipes//jar",
        "@curator-test//jar",
        "@curator-client//jar",
        "//lib/testcontainers",
    ],
)
