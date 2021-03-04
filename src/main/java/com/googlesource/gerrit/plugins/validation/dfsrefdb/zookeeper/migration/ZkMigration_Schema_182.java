// Copyright (C) 2021 The Android Open Source Project
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

package com.googlesource.gerrit.plugins.validation.dfsrefdb.zookeeper.migration;

import com.google.gerrit.server.config.AllUsersName;
import com.google.inject.Inject;
import com.googlesource.gerrit.plugins.validation.dfsrefdb.zookeeper.ZkSharedRefDatabase;
import org.apache.curator.framework.CuratorFramework;
import org.apache.zookeeper.KeeperException.NoNodeException;

class ZkMigration_Schema_182 implements ZkMigration {
  private final AllUsersName allUsers;

  @Inject
  ZkMigration_Schema_182(AllUsersName allUsers) {
    this.allUsers = allUsers;
  }

  @Override
  public void run(CuratorFramework curator) throws Exception {
    try {
      curator
          .delete()
          .deletingChildrenIfNeeded()
          .forPath(ZkSharedRefDatabase.pathFor(allUsers, "refs/draft-comments"));
    } catch (NoNodeException e) {
      // Nothing to do: the node did not exist
    }
  }
}
