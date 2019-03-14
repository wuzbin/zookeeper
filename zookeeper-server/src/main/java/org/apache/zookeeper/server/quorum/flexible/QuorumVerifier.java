/**
 * Licensed to the Apache Software Foundation (ASF) under one
 * or more contributor license agreements.  See the NOTICE file
 * distributed with this work for additional information
 * regarding copyright ownership.  The ASF licenses this file
 * to you under the Apache License, Version 2.0 (the
 * "License"); you may not use this file except in compliance
 * with the License.  You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.apache.zookeeper.server.quorum.flexible;

import java.util.Set;
import java.util.Map;

import org.apache.zookeeper.server.quorum.QuorumPeer.QuorumServer;

/**
 * All quorum validators have to implement a method called
 * containsQuorum, which verifies if a HashSet of server 
 * identifiers constitutes a quorum.
 * 法定人数验证器，containsQuorum的功能是判断传入的set是否属于大多数。
 * 目前有两个具体实现：
 *  QuorumMaj: 单纯的数个数。
 *  QuorumHierarchical：基于server分组和权重。是否有大多数的分组都投了赞成票。
 *  分组投赞成票的逻辑是判断分组中投票的服务器的权重加起来是否超过改组所有服务器权重之和的一半。
 */

public interface QuorumVerifier {
    long getWeight(long id);
    boolean containsQuorum(Set<Long> set);

    /**
     * 开启动态配置时，动态配置文件的版本号, 版本号在文件名中，每次生成一个动态版本文件，版本号就加1
     * @return
     */
    long getVersion();
    void setVersion(long ver);
    Map<Long, QuorumServer> getAllMembers();
    Map<Long, QuorumServer> getVotingMembers();
    Map<Long, QuorumServer> getObservingMembers();
    boolean equals(Object o);
    String toString();
}
