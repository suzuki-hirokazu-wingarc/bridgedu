package wat.f.bridgedu.domain.entity;

import java.io.IOException;
import java.sql.Blob;
import java.sql.SQLException;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import org.apache.tomcat.util.codec.binary.Base64;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Entity
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@ToString
@Table(name = "icons")
public class UserIconEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;
    private Blob icon;
    public String base64() throws SQLException, IOException {
        StringBuffer data  = new StringBuffer();
		String base64 = new String(Base64.encodeBase64(getIcon().getBinaryStream().readAllBytes()));
		data.append("data:image/png;base64,");
		data.append(base64);
        return data.toString();
    }
}
