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
package org.flowable.idm.engine.impl;

import java.sql.Connection;
import java.util.Map;

import org.flowable.engine.common.api.management.TableMetaData;
import org.flowable.engine.common.api.management.TablePageQuery;
import org.flowable.engine.common.impl.cmd.CustomSqlExecution;
import org.flowable.engine.common.impl.interceptor.Command;
import org.flowable.engine.common.impl.interceptor.CommandConfig;
import org.flowable.engine.common.impl.interceptor.CommandContext;
import org.flowable.idm.api.IdmManagementService;
import org.flowable.idm.engine.impl.cmd.ExecuteCustomSqlCmd;
import org.flowable.idm.engine.impl.cmd.GetPropertiesCmd;
import org.flowable.idm.engine.impl.cmd.GetTableCountCmd;
import org.flowable.idm.engine.impl.cmd.GetTableMetaDataCmd;
import org.flowable.idm.engine.impl.cmd.GetTableNameCmd;
import org.flowable.idm.engine.impl.util.CommandContextUtil;

/**
 * @author Tijs Rademakers
 */
public class IdmManagementServiceImpl extends ServiceImpl implements IdmManagementService {

    public Map<String, Long> getTableCount() {
        return commandExecutor.execute(new GetTableCountCmd());
    }

    public String getTableName(Class<?> entityClass) {
        return commandExecutor.execute(new GetTableNameCmd(entityClass));
    }

    public TableMetaData getTableMetaData(String tableName) {
        return commandExecutor.execute(new GetTableMetaDataCmd(tableName));
    }

    public TablePageQuery createTablePageQuery() {
        return new TablePageQueryImpl(commandExecutor);
    }

    public Map<String, String> getProperties() {
        return commandExecutor.execute(new GetPropertiesCmd());
    }

    public String databaseSchemaUpgrade(final Connection connection, final String catalog, final String schema) {
        CommandConfig config = commandExecutor.getDefaultConfig().transactionNotSupported();
        return commandExecutor.execute(config, new Command<String>() {
            public String execute(CommandContext commandContext) {
                return CommandContextUtil.getIdmEngineConfiguration().getDbSchemaManager().dbSchemaUpdate();
            }
        });
    }

    public <MapperType, ResultType> ResultType executeCustomSql(CustomSqlExecution<MapperType, ResultType> customSqlExecution) {
        Class<MapperType> mapperClass = customSqlExecution.getMapperClass();
        return commandExecutor.execute(new ExecuteCustomSqlCmd<MapperType, ResultType>(mapperClass, customSqlExecution));
    }

}
