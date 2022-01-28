package remind.me.coding.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Configuration
@ConfigurationProperties("gitapiclient")
public class GitApiClientProperties {
    private String endpointBaseUrl;
    private String gitToken;
    private String gitUser;

    public String getEndpointBaseUrl() {
        return endpointBaseUrl;
    }

    public void setEndpointBaseUrl(String endpointBaseUrl) {
        this.endpointBaseUrl = endpointBaseUrl;
    }

    public String getGitToken() {
        return gitToken;
    }

    public void setGitToken(String gitToken) {
        this.gitToken = gitToken;
    }

    public String getGitUser() {
        return gitUser;
    }

    public void setGitUser(String gitUser) {
        this.gitUser = gitUser;
    }

}
