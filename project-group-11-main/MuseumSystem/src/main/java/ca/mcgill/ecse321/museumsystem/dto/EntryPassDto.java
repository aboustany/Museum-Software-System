package ca.mcgill.ecse321.museumsystem.dto;

import ca.mcgill.ecse321.museumsystem.model.EntryPass;
//import ca.mcgill.ecse321.museumsystem.dto.VisitorDto;

/**
 * 
 * @author Anthony Boustany
 * 
 * EntryPass DTO class.
 *
 */

public class EntryPassDto {

        private int passID;
        private String date;

        private VisitorDto visitor;

        public EntryPassDto(){};

        public EntryPassDto(EntryPass entryPass){
               
                if (entryPass == null) {
                        throw new IllegalArgumentException("There is no such EntryPass.");
                }

                this.passID = entryPass.getPassID();
                this.date = entryPass.getDate();
                this.visitor = new VisitorDto(entryPass.getVisitor());             

        }

        public int getPassId(){
                return passID;
        }


        public String getDate(){
                return date;
        }


        public void setDate(String date){
                this.date = date;
        }
        

        public VisitorDto getVisitor(){
                return visitor;
        }


}














    