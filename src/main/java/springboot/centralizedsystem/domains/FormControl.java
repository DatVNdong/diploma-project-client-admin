package springboot.centralizedsystem.domains;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import springboot.centralizedsystem.resources.Collections;

@Document(collection = Collections.FORMS_CONTROL)
public class FormControl {

    @Id
    private String id;

    @Indexed(unique = true)
    @Field(value = "pathForm")
    private String pathForm;

    @Field(value = "owner")
    private String owner;

    @Field(value = "assign")
    private String assign;

    @Field(value = "start")
    private String start;

    @Field(value = "expired")
    private String expired;

    public String getPathForm() {
        return pathForm;
    }

    public void setPathForm(String pathForm) {
        this.pathForm = pathForm;
    }

    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    public String getAssign() {
        return assign;
    }

    public void setAssign(String assign) {
        this.assign = assign;
    }

    public String getStart() {
        return start;
    }

    public void setStart(String start) {
        this.start = start;
    }

    public String getExpired() {
        return expired;
    }

    public void setExpired(String expired) {
        this.expired = expired;
    }

    public FormControl(String pathForm, String owner, String assign, String start, String expired) {
        super();
        this.pathForm = pathForm;
        this.owner = owner;
        this.assign = assign;
        this.start = start;
        this.expired = expired;
    }

    public FormControl() {
        super();
    }
}
