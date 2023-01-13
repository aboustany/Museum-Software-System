package ca.mcgill.ecse321.museumsystem.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.fail;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyInt;
import static org.mockito.Mockito.lenient;

import java.util.ArrayList;
import java.util.List;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.invocation.InvocationOnMock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.mockito.stubbing.Answer;

import ca.mcgill.ecse321.museumsystem.model.ArtworkLoan;
import ca.mcgill.ecse321.museumsystem.model.ArtworkLoanRepository;
import ca.mcgill.ecse321.museumsystem.model.Visitor;

@ExtendWith(MockitoExtension.class)
public class TestArtworkLoanService {

	@Mock
	ArtworkLoanRepository artworkLoanRepository;
	
	@InjectMocks
	ArtworkLoanService artworkLoanService;
	
	private static final int LOAN_Id = 12345;
	
	@BeforeEach
	public void setMockOutput() {
		Answer<?> returnParameterAsAnswer = (InvocationOnMock invocation) ->{
			return invocation.getArgument(0);
		};
		lenient().when(artworkLoanRepository.save(any(ArtworkLoan.class))).thenAnswer(returnParameterAsAnswer);
		lenient().when(artworkLoanRepository.findArtworkLoanByLoanID(anyInt())).thenAnswer((InvocationOnMock invocation) -> {
			if (invocation.getArgument(0).equals(LOAN_Id)) {
				ArtworkLoan loan = new ArtworkLoan();
				loan.setLoanID(LOAN_Id);
				return loan;
			} else {
				return null;
			}
		});
		
	}
	
	@Test
	public void testCreateLoan() {
		String startDay = "01/01/2001";
		ArtworkLoan loan = new ArtworkLoan();
		loan.setStartDay(startDay);
		
		loan = artworkLoanService.createArtworkLoan(loan);
		
		assertNotNull(loan.getStartDay());
		
	}
	
	@Test
	public void testGetArtworkLoanByID() {
	
		ArtworkLoan foundLoan = artworkLoanService.getArtworkLoanByID(LOAN_Id);
		
		assertNotNull(foundLoan);
		
	}
	
	@Test
	public void testGetNullLoan() {
		
		ArtworkLoan loan = artworkLoanService.getArtworkLoanByID(444);
		assertNull(loan);
	}
	
	
	@Test void testApproveLoan() {
		
		ArtworkLoan loan = new ArtworkLoan();
		artworkLoanService.approveLoan(loan);
		
		assertEquals(loan.getIsApproved(),true);
	}

	

}