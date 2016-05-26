/*
 * Copyright 2016 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package org.springframework.cloud.spinnaker;

import org.cloudfoundry.client.CloudFoundryClient;
import org.cloudfoundry.operations.CloudFoundryOperations;

import org.springframework.cloud.deployer.spi.cloudfoundry.CloudFoundryAppDeployer;
import org.springframework.cloud.deployer.spi.cloudfoundry.CloudFoundryDeployerProperties;

/**
 * @author Greg Turnquist
 */
public class DefaultAppDeployerFactoryBean implements CloudFoundryAppDeployerFactoryBean {

	private final CloudFoundryDeployerProperties cloudFoundryDeployerProperties;
	private final CloudFoundryOperations operations;
	private final CloudFoundryClient client;

	public DefaultAppDeployerFactoryBean(CloudFoundryDeployerProperties cloudFoundryDeployerProperties, CloudFoundryOperations operations,
										 CloudFoundryClient client) {
		this.cloudFoundryDeployerProperties = cloudFoundryDeployerProperties;
		this.operations = operations;
		this.client = client;
	}

	@Override
	public CloudFoundryAppDeployer getObject() throws Exception {
		return new CloudFoundryAppDeployer(cloudFoundryDeployerProperties, operations, client);
	}

	@Override
	public Class<?> getObjectType() {
		return CloudFoundryAppDeployer.class;
	}

	@Override
	public boolean isSingleton() {
		return false;
	}

	@Override
	public CloudFoundryAppDeployer getObject(CloudFoundryDeployerProperties props) {
		return new CloudFoundryAppDeployer(props, operations, client);
	}

	@Override
	public CloudFoundryDeployerProperties getCloudFoundryDeployerProperties() {
		return cloudFoundryDeployerProperties;
	}

	public CloudFoundryOperations getOperations() {
		return operations;
	}

	public CloudFoundryClient getClient() {
		return client;
	}
}
