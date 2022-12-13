package app.boton.parametrizacionboton.models;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.bson.types.Binary;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import javax.validation.constraints.Size;
import java.util.Date;

@Document(collection = "reportType")
@Data
@NoArgsConstructor
public class ReportType {

    @Id
    @JsonIgnore
    private String id;

    @Size(max = 30)
    private String name; // file name
    private Date createdTime; // upload time
    private Binary content; // file content
    private String contentType; // file type
    private long size; // file size
    private String suffix;
    private String image;
    private String color;

    public ReportType(String name, Date createdTime, Binary content, String contentType, long size, String suffix, String image, String color) {
        this.name = name;
        this.createdTime = createdTime;
        this.content = content;
        this.contentType = contentType;
        this.size = size;
        this.suffix = suffix;
        this.image = image;
        this.color = color;
    }
}
