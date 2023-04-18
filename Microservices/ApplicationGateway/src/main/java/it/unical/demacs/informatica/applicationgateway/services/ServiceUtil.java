package it.unical.demacs.informatica.applicationgateway.services;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import org.springframework.stereotype.Service;

import java.util.Map;

@Service
class ServiceUtil {

    private final EurekaClient eurekaClient;

    ServiceUtil(EurekaClient eurekaClient) {
        this.eurekaClient = eurekaClient;
    }

    String getServer(String serverName) {
        Application application = eurekaClient.getApplication(serverName);
        if(application != null) {
            InstanceInfo service = application
                    .getInstances()
                    .get(0);
            return "http://" + service.getHostName() + ":" + service.getPort();
        }
        else {
            return "http://localhost:8080/"+serverName;
        }
    }

    String createGETUri(String serverName, String uri, Map<String, Object> parameters) {
        StringBuilder builder = new StringBuilder();
        for(String param : parameters.keySet()) {
            builder.append("&").append(param).append("=").append(parameters.get(param));
        }
        return getServer(serverName) + uri + builder.toString().replaceFirst("&", "?");
    }

}
