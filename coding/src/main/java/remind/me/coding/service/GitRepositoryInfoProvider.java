package remind.me.coding.service;

import org.apache.tomcat.util.codec.binary.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;
import remind.me.coding.config.GitApiClientProperties;
import remind.me.coding.dto.GithubRepositoryMinimal;

import java.nio.charset.Charset;
import java.util.*;

@Component
public class GitRepositoryInfoProvider {

    private RestTemplate restTemplate;

    @Autowired
    private  GitApiClientProperties gitApiClientProperties;

    @Autowired
    public GitRepositoryInfoProvider(GitApiClientProperties gitApiClientProperties){
        this.gitApiClientProperties = gitApiClientProperties;
        restTemplate = new RestTemplate();
    }

    public List<GithubRepositoryMinimal> getRepositoriesInfo(String profileUrl){

        var gitUserName = profileUrl.split("/")[3];

        var headers = createHeaders(gitApiClientProperties.getGitUser(), gitApiClientProperties.getGitToken());

        var repositoriesUrl = gitApiClientProperties.getEndpointBaseUrl() + "/users/" + gitUserName + "/repos";

        ResponseEntity<GithubRepositoryMinimal[]> repositoriesResult = restTemplate.exchange(repositoriesUrl, HttpMethod.GET,
                new HttpEntity<>(headers),
                GithubRepositoryMinimal[].class);

        if(repositoriesResult.getStatusCode() == HttpStatus.OK) {

            var repositories = List.of(repositoriesResult.getBody());

            for (var repo: repositories){
                repo.setLanguagesDescription(getLanguagesFromUrl(repo.getLanguages_url()));
            }

            return new ArrayList<GithubRepositoryMinimal>(List.of(repositoriesResult.getBody()));
        }
        else return new ArrayList<GithubRepositoryMinimal>(0);
    }

    public String getLanguagesFromUrl(String languagesUrl){
        var headers = createHeaders(gitApiClientProperties.getGitUser(), gitApiClientProperties.getGitToken());

        ResponseEntity<? extends HashMap> getLanguagesResult = restTemplate.exchange(languagesUrl, HttpMethod.GET, new HttpEntity<>(headers),new HashMap<String,Object>().getClass());

        if(getLanguagesResult.getStatusCode() == HttpStatus.OK){
            String result = "";
            var resultDictionary = getLanguagesResult.getBody();

            for (Object entry: resultDictionary.keySet()){
                result += entry + ":" + resultDictionary.get(entry).toString();
            }
            return result;
        }
        return null;
    }

    HttpHeaders createHeaders(String username, String password){
        String auth = username + ":" + password;
        byte[] encodedAuth = Base64.encodeBase64(auth.getBytes(Charset.forName("US-ASCII")) );
        String authHeader = "Basic " + new String( encodedAuth );

        HttpHeaders headers = new HttpHeaders();
        headers.set("Authorization", authHeader);

        return headers;
    }
}
