/* Licensed under the Apache License, Version 2.0 (the "License");
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

package org.flowable.form.spring.autodeployment;

import java.io.IOException;

import org.flowable.common.engine.api.FlowableException;
import org.flowable.form.api.FormDeploymentBuilder;
import org.flowable.form.api.FormRepositoryService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.io.Resource;

/**
 * Implementation of {@link AutoDeploymentStrategy} that performs a separate deployment for each resource by name.
 * 
 * @author Tiese Barrell
 * @author Joram Barrez
 */
public class SingleResourceAutoDeploymentStrategy extends AbstractAutoDeploymentStrategy {

    private static final Logger LOGGER = LoggerFactory.getLogger(SingleResourceAutoDeploymentStrategy.class);

    /**
     * The deployment mode this strategy handles.
     */
    public static final String DEPLOYMENT_MODE = "single-resource";

    @Override
    protected String getDeploymentMode() {
        return DEPLOYMENT_MODE;
    }

    @Override
    public void deployResources(final String deploymentNameHint, final Resource[] resources, final FormRepositoryService repositoryService) {

        // Create a separate deployment for each resource using the resource name

        for (final Resource resource : resources) {

            try {
                final String resourceName = determineResourceName(resource);
                final FormDeploymentBuilder deploymentBuilder = repositoryService.createDeployment().enableDuplicateFiltering().name(resourceName);
                deploymentBuilder.addInputStream(resourceName, resource.getInputStream());

                deploymentBuilder.deploy();

            } catch (Exception e) {

                // Any exception should not stop the bootup of the engine
                String resourceName = null;
                if (resource != null) {
                    try {
                        resourceName = resource.getURL().toString();
                    } catch (IOException ioe) {
                        resourceName = resource.toString();
                    }
                }
                LOGGER.warn("Exception while autodeploying process definitions for resource " + resourceName + ". "
                    + "This exception can be ignored if the root cause indicates a unique constraint violation, "
                    + "which is typically caused by two (or more) servers booting up at the exact same time and deploying the same definitions. ", e);
            }
        }
    }

}
