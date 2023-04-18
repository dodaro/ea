package it.unical.demacs.informatica.newsletterhandlerservice;


import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.discovery.shared.Application;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;

@Service
public class SendEmailService {

    private final EurekaClient eurekaClient;
    private final List<String> emails = new ArrayList<>();
    private long elapsedTime = 0L;

    public SendEmailService(EurekaClient eurekaClient) {
        this.eurekaClient = eurekaClient;
    }

    private String getServer(String serverName) {
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

    private void fetchEmailAddresses() {
        try {
            URL url = new URL(getServer("user-handler-service") + "/api/v1/contactable_users");
            HttpURLConnection con = (HttpURLConnection) url.openConnection();
            con.setRequestMethod("GET");
            con.setConnectTimeout(5000);
            con.setReadTimeout(5000);
            int status = con.getResponseCode();
            if(status == 200) {
                BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));
                StringBuilder builder = new StringBuilder();
                while(in.ready()) {
                    builder.append(in.readLine());
                }
                JSONObject json = new JSONObject(builder.toString());
                if(json.has("contactable_users")) {
                    emails.clear();
                    JSONArray arr = json.getJSONArray("contactable_users");
                    for(int i = 0; i < arr.length(); i++) {
                        JSONObject user = arr.getJSONObject(i);
                        if(user.has("email"))
                            emails.add(user.getString("email"));
                    }
                }
                in.close();
            }
            con.disconnect();
        } catch (IOException ignored) {
        }
    }

    public void sendEmail(String content) {
        if(System.currentTimeMillis()-elapsedTime >= 30000) {
            fetchEmailAddresses();
            elapsedTime = System.currentTimeMillis();
        }
        for(String email : emails)
            System.out.println("Sending email to " + email + " -> " + content);
    }
}
