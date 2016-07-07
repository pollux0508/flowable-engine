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
package com.activiti.model.editor.decisiontable;

import java.util.Date;

import com.activiti.domain.editor.AbstractModel;
import com.activiti.model.common.AbstractRepresentation;

/**
 * Created by yvoswillens on 14/08/15.
 */
public class DecisionTableRepresentation extends AbstractRepresentation {

    protected Long id;
    protected String name;
    protected String description;
    protected Integer version;
    protected Long lastUpdatedBy;
	protected String lastUpdatedByFullName;
	protected Date lastUpdated;
	protected Long referenceId;
    protected DecisionTableDefinitionRepresentation decisionTableDefinition;

    public DecisionTableRepresentation(AbstractModel model) {
        this.id = model.getId();
        this.name = model.getName();
        this.description = model.getDescription();
        this.version = model.getVersion();
        this.lastUpdated = model.getLastUpdated();
        this.lastUpdatedBy = model.getLastUpdatedBy().getId();
        this.referenceId = model.getReferenceId();
        this.lastUpdatedByFullName = model.getLastUpdatedBy().getFullName();
        this.referenceId = model.getReferenceId();
    }

    public DecisionTableRepresentation() {}

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getVersion() {
        return version;
    }

    public void setVersion(Integer version) {
        this.version = version;
    }

    public Long getLastUpdatedBy() {
		return lastUpdatedBy;
	}

	public void setLastUpdatedBy(Long lastUpdatedBy) {
		this.lastUpdatedBy = lastUpdatedBy;
	}

	public String getLastUpdatedByFullName() {
		return lastUpdatedByFullName;
	}

	public void setLastUpdatedByFullName(String lastUpdatedByFullName) {
		this.lastUpdatedByFullName = lastUpdatedByFullName;
	}

    public Date getLastUpdated() {
		return lastUpdated;
	}

	public void setLastUpdated(Date lastUpdated) {
		this.lastUpdated = lastUpdated;
	}

    public Long getReferenceId() {
        return referenceId;
    }

    public void setReferenceId(Long referenceId) {
        this.referenceId = referenceId;
    }

    public DecisionTableDefinitionRepresentation getDecisionTableDefinition() {
        return decisionTableDefinition;
    }

    public void setDecisionTableDefinition(DecisionTableDefinitionRepresentation decisionTableDefinition) {
        this.decisionTableDefinition = decisionTableDefinition;
    }
}