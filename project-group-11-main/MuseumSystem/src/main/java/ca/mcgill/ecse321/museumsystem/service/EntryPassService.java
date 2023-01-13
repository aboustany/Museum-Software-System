package ca.mcgill.ecse321.museumsystem.service;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import ca.mcgill.ecse321.museumsystem.model.Employee;
import ca.mcgill.ecse321.museumsystem.model.EntryPass;
import ca.mcgill.ecse321.museumsystem.model.EntryPassRepository;
import ca.mcgill.ecse321.museumsystem.model.Visitor;
import ca.mcgill.ecse321.museumsystem.model.VisitorRepository;
import ca.mcgill.ecse321.museumsystem.exception.ArtworkException;;

/**
 * 
 * @author Anthony Boustany
 * 
 * EntryPass Service class.
 *
 */

@Service
public class EntryPassService {

    @Autowired
    EntryPassRepository entryPassRepository;

    @Autowired 
    VisitorRepository visitorRepository;



    //Helper method validating date input
    //Is this how we want to implement the date?
    private String checkDate(String Date){
        
        //***Date Validation TO BE WRITTEN(?)***

        return Date;
    }
    
    @Transactional
	public void deleteEntryPass(EntryPass entryPass) {
    	entryPassRepository.delete(entryPass);
	}


    private Visitor checkVisitor(int accountId){
        Visitor visitor = visitorRepository.findVisitorByAccountID(accountId);
        if(visitor == null){
            throw new ArtworkException(HttpStatus.NOT_FOUND, "The input AccountID is invalid.");
        }
        return visitor;
    }

    @Transactional
    public EntryPass createEntryPass(int accountId, String date){
        EntryPass entryPass = new EntryPass();
        entryPass.setVisitor(checkVisitor(accountId));

        entryPass.setDate(checkDate(date));

        entryPassRepository.save(entryPass);
        return entryPass;
    }

    @Transactional
    public EntryPass createEntryPass(EntryPass entryPass){

        // if(entryPass == null){
        //     throw new ArtworkException(HttpStatus.NOT_FOUND, "EntryPass not found.");
        // }

        // if(entryPass.getVisitor() == null){
        //     throw new ArtworkException(HttpStatus.NOT_FOUND, "EntryPass is not associated with a visitor.");
        // }

        // if(entryPass.getDate() == null){
        //     throw new ArtworkException(HttpStatus.NOT_FOUND, "EntryPass has no date.");
        // }

        entryPass = entryPassRepository.save(entryPass);
        return entryPass;
    }

    @Transactional
    public EntryPass getEntryPassById(int entryPassId){
        EntryPass entryPass = entryPassRepository.findEntryPassByPassID(entryPassId);
        
        // if(entryPass == null){
        //     throw new ArtworkException(HttpStatus.NOT_FOUND, "EntryPass not found.");
        // }

        return entryPass;
    }

    @Transactional
    public List<EntryPass> getAllEntryPasses(){
        return toList(entryPassRepository.findAll());
    }

    @Transactional
    public Visitor getVisitorByEntryPassId(int passId){
        EntryPass entryPass = entryPassRepository.findEntryPassByPassID(passId);

        // if(entryPass == null){
        //     throw new ArtworkException(HttpStatus.NOT_FOUND, "EntryPass not found, invalid PassID.");
        // }

        return entryPass.getVisitor();
    }

    @Transactional 
    public List<EntryPass> getAllEntryPassesByAccountId(int accountId){
        List<EntryPass> allPassesForAccount = new ArrayList<EntryPass>();
        List <EntryPass> allPasses = toList(entryPassRepository.findAll());

        for (int i = 0; i < allPasses.size(); i++){

            EntryPass currentPass = allPasses.get(i);

            if (currentPass.getVisitor().getAccountID() == accountId) {
                allPassesForAccount.add(currentPass);
            }
        }
        return allPassesForAccount;
    }

    @Transactional
    public List<EntryPass> getAllEntryPassesByDate(String date){
        List<EntryPass> allPassesByDate = new ArrayList<EntryPass>();
        List<EntryPass> allPasses = toList(entryPassRepository.findAll());

        date = checkDate(date);

        for (int i = 0; i < allPasses.size(); i++){

            EntryPass currentPass = allPasses.get(i);

            if (currentPass.getDate() == date) {
                allPassesByDate.add(currentPass);
            }
        }
        return allPassesByDate;
    }

 
    //helper toList method
    private <T> List<T> toList(Iterable<T> iterable){
		List<T> resultList = new ArrayList<T>();
		for (T t : iterable) {
			resultList.add(t);
		}
		return resultList;
	}
}