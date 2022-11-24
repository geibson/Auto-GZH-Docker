package Automatizado.Config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.EnableConfigurationProperties;
import org.springframework.context.annotation.Configuration;

//
@Configuration
@ConfigurationProperties
@EnableConfigurationProperties
public class AutomatedProperties {

    @Value("${site}")
    private String site;
    @Value("${edit}")
    private String edit;
    @Value("${browser}")
    private String browser;
    @Value("${context}")
    private String context;
    @Value("${ambiente}")
    private String ambiente;
    //</editor-fold>

    @SuppressWarnings("all")
    public AutomatedProperties() {
    }

    @SuppressWarnings("all")
    public String getSite() {
        return this.site;
    }

    public String getEdit() {
        return this.edit;
    }

    @SuppressWarnings("all")
    public String getBrowser() {
        return this.browser;
    }

    //<editor-fold defaultstate="collapsed" desc="delombok">
    @SuppressWarnings("all")
    public String getContext() {
        return this.context;
    }

    public String getAmbiente() {
        return this.ambiente;
    }

    @SuppressWarnings("all")
    public void setSite(final String site) {
        this.site = site;
    }

    public void setEdit(final String edit) {
        this.edit = edit;
    }

    @SuppressWarnings("all")
    public void setBrowser(final String browser) {
        this.browser = browser;
    }

    @SuppressWarnings("all")
    public void setContext(final String context) {
        this.context = context;
    }

    public void setAmbiente(final String ambiente) {
        this.ambiente = ambiente;
    }
    @Override
    @SuppressWarnings("all")
    public boolean equals(final Object o) {
        if (o == this) return true;
        if (!(o instanceof AutomatedProperties)) return false;
        final AutomatedProperties other = (AutomatedProperties) o;
        if (!other.canEqual((Object) this)) return false;
        final Object this$host = this.getSite();
        final Object other$host = other.getSite();
        if (this$host == null ? other$host != null : !this$host.equals(other$host)) return false;
        final Object this$browser = this.getBrowser();
        final Object other$browser = other.getBrowser();
        if (this$browser == null ? other$browser != null : !this$browser.equals(other$browser)) return false;
        final Object this$context = this.getContext();
        final Object other$context = other.getContext();
        if (this$context == null ? other$context != null : !this$context.equals(other$context)) return false;
        return true;
    }

    @SuppressWarnings("all")
    protected boolean canEqual(final Object other) {
        return other instanceof AutomatedProperties;
    }

    @Override
    @SuppressWarnings("all")
    public int hashCode() {
        final int PRIME = 59;
        int result = 1;
        final Object $site = this.getSite();
        result = result * PRIME + ($site == null ? 43 : $site.hashCode());
        final Object $browser = this.getBrowser();
        result = result * PRIME + ($browser == null ? 43 : $browser.hashCode());
        final Object $context = this.getContext();
        result = result * PRIME + ($context == null ? 43 : $context.hashCode());
        return result;
    }

    @Override
    @SuppressWarnings("all")
    public String toString() {
        return "AutomatedProperties(edit=" + this.getEdit() +", site=" + this.getSite() + ", browser=" + this.getBrowser() + ", context=" + this.getContext() + ", ambiente=" + this.getAmbiente() + ")";
    }
}
    //</editor-fold>
