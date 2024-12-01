package com.strive.adminapi.admin_api.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import com.azure.data.tables.TableClient;
import com.azure.data.tables.TableClientBuilder;
import com.azure.data.tables.models.TableEntity;
import java.util.HashMap;
import java.util.Map;

@RestController
public class EnvironmentController {
    @GetMapping("/environment")
    public Map<String, String> getEnvironmentVariables() {
        String connectionString = "DefaultEndpointsProtocol=https;AccountName=striveai;AccountKey=9VaMU46O1WnvrTKO4QnFUnbny1Lkngl3RxKl4I8q2oZNPOq87gHdmZQklnwML5Tg2TXXKbNXfWDA+ASt/g2Urg==;EndpointSuffix=core.windows.net";
        String tableName = "environmentVariables";
        System.out.println(connectionString);
        TableClient tableClient = new TableClientBuilder()
            .connectionString(connectionString)
            .tableName(tableName)
            .buildClient();
        Iterable<TableEntity> entities = tableClient.listEntities();
        Map<String, String> keyValuePairs = new HashMap<>();
        for (TableEntity entity : entities) {
            String key = (String) entity.getProperties().get("Key");
            String value = (String) entity.getProperties().get("Value");
            if (key != null && value != null) {
                keyValuePairs.put(key, value);
            }
        }
        return keyValuePairs;
    }
}
