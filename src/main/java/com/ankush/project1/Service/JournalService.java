package com.ankush.project1.Service;

import com.ankush.project1.Entity.JournalEntry;
import com.ankush.project1.Entity.User;
import com.ankush.project1.Repo.JournalRepo;
import com.ankush.project1.Repo.UserRepo;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Component
public class JournalService {

     @Autowired
     private JournalRepo journalRepo;
     @Autowired
     private UserService userService;
    @Autowired
    private UserRepo userRepo;

    @Transactional
     public void saveAll(JournalEntry journalEntry,String username){
         User user = userService.findByUsername(username);
         journalEntry.setDate(LocalDate.now());
         JournalEntry saved = journalRepo.save(journalEntry);
         user.getJournalEntries().add(saved);
         userService.saveOldUser(user);
     }
       public JournalEntry saveAll(JournalEntry journalEntry){
        journalEntry.setDate(LocalDate.now());
        return journalRepo.save(journalEntry);
    }


     public List<JournalEntry> getAll(){
         return journalRepo.findAll();
     }

     public Optional<JournalEntry> getById(ObjectId id){
         return journalRepo.findById(id);
     }
     @Transactional
     public void deleteById(ObjectId id, String username){
         try{
             User user = userService.findByUsername(username);
             boolean removed = user.getJournalEntries().removeIf(x -> x.getId().equals(id));
             if (removed) {
                 userService.saveOldUser(user);
                 journalRepo.deleteById(id);
             }
         }catch (Exception e){
             e.printStackTrace();
            throw new RuntimeException("Error deleting journal entry");
         }
     }
     public User findByUsername(String username){
         return userRepo.findByUsername(username);
     }

}
