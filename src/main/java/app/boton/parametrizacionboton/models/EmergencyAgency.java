package app.boton.parametrizacionboton.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Size;
import java.util.Date;

@Document(collection = "emergencyAgency")
@Data
@NoArgsConstructor
public class EmergencyAgency {

    @Id
    @JsonIgnore
    private String id;

    @Size(max = 30)
    private String name; // file name
    private Date createdtime; // upload time
    private Binary content; // file content
    private String contentType; // file type
    private long size; // file size
    private String suffix;
    private String image;
    private String phone;

    public EmergencyAgency(String name, Date createdtime, Binary content, String contentType, long size, String suffix, String image, String phone) {
        this.name = name;
        this.createdtime = createdtime;
        this.content = content;
        this.contentType = contentType;
        this.size = size;
        this.suffix = suffix;
        this.image = image;
        this.phone = phone;
    }
}
