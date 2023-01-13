package ca.mcgill.ecse321.museumsystem.service;

import static org.mockito.Mockito.lenient;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import org.mockito.invocation.InvocationOnMock;

import ca.mcgill.ecse321.museumsystem.model.EntryPass;
import ca.mcgill.ecse321.museumsystem.model.EntryPassRepository;

import ca.mcgill.ecse321.museumsystem.model.Visitor;
import ca.mcgill.ecse321.museumsystem.model.VisitorRepository;

/**
 * 
 * @author Anthony Boustany
 * 
 * EntryPass Service Tests.
 *
 */

@ExtendWith(MockitoExtension.class)
public class TestEntryPassService {
    
    @Mock
    EntryPassRepository entryPassRepository;

    @Mock
    VisitorRepository visitorRepository;

    @InjectMocks
    private EntryPassService entryPassService;

    private static final int ENTRYPASS_KEY = 333;
    private static final int VIS_ID = 7;
    private static final String PERSON_KEY = "JOHN CENA";

    @BeforeEach
    public void setMockOutput(){
        Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) -> {
			return invocation.getArgument(0);
		};
		lenient().when(entryPassRepository.save(any(EntryPass.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(entryPassRepository.findEntryPassByPassID(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			if(invocation.getArgument(0).equals(ENTRYPASS_KEY)){
                EntryPass entryPass = new EntryPass();
                Visitor visitor = new Visitor();
                String date = "1/1/2002";
                visitor.setAccountID(VIS_ID);
                visitor.setUserName(PERSON_KEY);

				entryPass.setPassID(ENTRYPASS_KEY);
                entryPass.setVisitor(visitor);
                entryPass.setDate(date);

				return entryPass;		
            }	
            else return null;
		});
    }

    @AfterEach
    public void deleteEntryPasses(){
        entryPassRepository.deleteAll();
    }

    @Test
    public void testCreateEntryPass(){


        Visitor kafi = new Visitor();

        String date2 = "1/1/2055";

        EntryPass testPass2 = new EntryPass();

        testPass2.setVisitor(kafi);
        testPass2.setDate(date2);
        testPass2.setPassID(ENTRYPASS_KEY);

        testPass2 = entryPassService.createEntryPass(testPass2);

        assertNotNull(testPass2);
        assertNotNull(testPass2.getPassID());
       
    }

    @Test
    public void testCreateEntryPassNull(){

        EntryPass entryPass = null;
		entryPass = entryPassService.createEntryPass(entryPass);
		assertNull(entryPass);
        
    }

    @Test
    public void testGetEntryPassById(){

        EntryPass entryPass = new EntryPass();
        entryPass.setPassID(ENTRYPASS_KEY);

        String date = "1/1/2002";
        
        Visitor tmpVisitor = new Visitor();
        tmpVisitor.setAccountID(VIS_ID);
        tmpVisitor.setUserName(PERSON_KEY);

        entryPass.setDate(date);
        entryPass.setVisitor(tmpVisitor);
        
        entryPass = entryPassRepository.findEntryPassByPassID(entryPass.getPassID());

        try{
            EntryPass pass = entryPassRepository.findEntryPassByPassID(entryPass.getPassID());
            assertEquals(entryPass.getPassID(), pass.getPassID());
        }
        catch(Exception e){
            System.out.print("EntryPass not found");
            fail();
        }
   
    }

    @Test
    public void testGetNullEntryPassById(){
        int passId = 999;
		EntryPass entryPass = entryPassService.getEntryPassById(passId);
		assertNull(entryPass);
    }
}