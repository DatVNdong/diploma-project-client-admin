package springboot.centralizedsystem.admin.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Service;

import com.mongodb.WriteResult;

import springboot.centralizedsystem.admin.domains.FormControl;
import springboot.centralizedsystem.admin.repository.FormControlRepository;

@Service
public class FormControlServiceImpl implements FormControlService {

    @Autowired
    private FormControlRepository formControlRepository;

    @Autowired
    MongoTemplate mongoTemplate;

    @Override
    public List<FormControl> findAll() {
        try {
            return this.formControlRepository.findAll();
        } catch (Exception e) {
            System.err.println("[ERROR] Find list FormControl: " + e.getMessage());
            return null;
        }
    }

    @Override
    public boolean insert(FormControl formControl) {
        try {
            this.formControlRepository.insert(formControl);
            return true;
        } catch (Exception e) {
            System.err.println("[ERROR] Insert FormControl: " + e.getMessage());
            return false;
        }
    }

    @Override
    public boolean deleteAll() {
        try {
            this.formControlRepository.deleteAll();
            return true;
        } catch (Exception e) {
            System.err.println("[ERROR] Delete all FormControl: " + e.getMessage());
            return false;
        }
    }

    @Override
    public FormControl findByPathForm(String pathForm) {
        try {
            return this.formControlRepository.findByPathForm(pathForm);
        } catch (Exception e) {
            System.err.println("[ERROR] Find one FormControl: " + e.getMessage());
            return null;
        }
    }

    @Override
    public boolean deleteByPathForm(String pathForm) {
        try {
            FormControl formControl = formControlRepository.findByPathForm(pathForm);
            this.formControlRepository.delete(formControl);
            return true;
        } catch (Exception e) {
            System.err.println("[ERROR] Delete one FormControl: " + e.getMessage());
            return false;
        }
    }

    @Override
    public int update(FormControl formControl, String oldPath) {
        try {
            Query query = new Query(Criteria.where("pathForm").is(oldPath));

            Update update = new Update();
            update.set("pathForm", formControl.getPathForm());
            update.set("assign", formControl.getAssign());
            update.set("start", formControl.getStart());
            update.set("expired", formControl.getExpired());

            WriteResult result = this.mongoTemplate.updateFirst(query, update, FormControl.class);
            return result.getN();
        } catch (Exception e) {
            System.err.println("[ERROR] Update one FormControl: " + e.getMessage());
            return -1;
        }
    }
}