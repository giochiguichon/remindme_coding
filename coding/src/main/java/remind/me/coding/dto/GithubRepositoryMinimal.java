package remind.me.coding.dto;

public class GithubRepositoryMinimal {
    private long id;
    private String name;
    private String full_name;
    private String languages_url;
    private String ssh_url;
    private String clone_url;
    private String languagesDescription;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getFull_name() {
        return full_name;
    }

    public void setFull_name(String full_name) {
        this.full_name = full_name;
    }

    public String getLanguages_url() {
        return languages_url;
    }

    public void setLanguages_url(String language_url) {
        this.languages_url = language_url;
    }

    public String getSsh_url() {
        return ssh_url;
    }

    public void setSsh_url(String ssh_url) {
        this.ssh_url = ssh_url;
    }

    public String getClone_url() {
        return clone_url;
    }

    public void setClone_url(String clone_url) {
        this.clone_url = clone_url;
    }

    public String getLanguagesDescription() {
        return languagesDescription;
    }

    public void setLanguagesDescription(String languagesDescription) {
        this.languagesDescription = languagesDescription;
    }
}
