// Copyright (C) 2019 The Android Open Source Project
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
// http://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

package com.googlesource.gerrit.plugins.validation.dfsrefdb.zookeeper;

import static com.googlesource.gerrit.plugins.validation.dfsrefdb.zookeeper.ZkSharedRefDatabase.pathFor;
import static com.googlesource.gerrit.plugins.validation.dfsrefdb.zookeeper.ZkSharedRefDatabase.writeObjectId;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

import com.google.gerrit.entities.Project;
import com.google.gerrit.server.config.PluginConfigFactory;
import org.apache.curator.framework.CuratorFramework;
import org.eclipse.jgit.lib.Config;
import org.eclipse.jgit.lib.ObjectId;
import org.eclipse.jgit.lib.Ref;
import org.junit.Ignore;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.wait.strategy.Wait;

@Ignore
public class ZookeeperTestContainerSupport {

  static class ZookeeperContainer extends GenericContainer<ZookeeperContainer> {
    public static String ZOOKEEPER_VERSION = "3.8.2";

    public ZookeeperContainer() {
      super("zookeeper:" + ZOOKEEPER_VERSION);
    }
  }

  private ZookeeperContainer container;
  private ZookeeperConfig configuration;
  private CuratorFramework curator;

  public CuratorFramework getCurator() {
    return curator;
  }

  public ZookeeperContainer getContainer() {
    return container;
  }

  @SuppressWarnings("resource")
  public ZookeeperTestContainerSupport() {
    container = new ZookeeperContainer().withExposedPorts(2181).waitingFor(Wait.forListeningPort());
    container.start();
    Integer zkHostPort = container.getMappedPort(2181);
    Config sharedRefDbConfig = new Config();
    String connectString = container.getContainerIpAddress() + ":" + zkHostPort;
    sharedRefDbConfig.setBoolean("ref-database", null, "enabled", true);
    sharedRefDbConfig.setString("ref-database", "zookeeper", "connectString", connectString);
    sharedRefDbConfig.setString(
        "ref-database",
        ZookeeperConfig.SUBSECTION,
        ZookeeperConfig.KEY_CONNECT_STRING,
        connectString);

    PluginConfigFactory cfgFactory = mock(PluginConfigFactory.class);
    when(cfgFactory.getGlobalPluginConfig("zookeeper")).thenReturn(sharedRefDbConfig);
    configuration = new ZookeeperConfig(cfgFactory, "zookeeper");

    this.curator = configuration.startCurator();
  }

  public void cleanup() {
    this.curator.delete();
    this.container.stop();
  }

  public ObjectId readRefValueFromZk(Project.NameKey projectName, Ref ref) throws Exception {
    final byte[] bytes = curator.getData().forPath(pathFor(projectName, ref.getName()));
    return ZkSharedRefDatabase.readObjectId(bytes);
  }

  public void createRefInZk(Project.NameKey projectName, Ref ref) throws Exception {
    curator
        .create()
        .creatingParentContainersIfNeeded()
        .forPath(pathFor(projectName, ref.getName()), writeObjectId(ref.getObjectId()));
  }
}
