/*
 * Copyright 2016 VMware, Inc. All Rights Reserved.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not
 * use this file except in compliance with the License.  You may obtain a copy of
 * the License at http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed
 * under the License is distributed on an "AS IS" BASIS, without warranties or
 * conditions of any kind, EITHER EXPRESS OR IMPLIED.  See the License for the
 * specific language governing permissions and limitations under the License.
 */

package com.vmware.photon.controller.apife.lib;

import com.vmware.photon.controller.apife.config.ImageConfig;
import com.vmware.photon.controller.common.clients.HostClientFactory;

import com.google.inject.Inject;
import com.google.inject.Singleton;

/**
 * Factory class to create ImageStore instances.
 */
@Singleton
public class ImageStoreFactory {

  private final ImageConfig config;
  private final HostClientFactory hostClientFactory;

  @Inject
  public ImageStoreFactory(
      ImageConfig config,
      HostClientFactory hostClientFactory) {
    this.config = config;
    this.hostClientFactory = hostClientFactory;
  }

  public ImageStore create() {
    if (config.useEsxStore()) {
      return new VsphereImageStore(
          hostClientFactory,
          config);
    }

    return new LocalImageStore(config.getLocalStore(), config.getDatastore());
  }
}