package ma.enset.client.entities;

import org.springframework.data.rest.core.config.Projection;

@Projection(name = "fullClient",types = Client.class)
public interface ClientProjection {
    public Long getCode();
    public String getNom();
    public String getMail();
}
