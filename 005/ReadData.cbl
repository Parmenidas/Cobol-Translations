       IDENTIFICATION DIVISION.
           PROGRAM-ID. ReadData.
      *> How to write a file     
       
       ENVIRONMENT DIVISION.
           INPUT-OUTPUT SECTION.
           FILE-CONTROL.
               SELECT FileUsed ASSIGN TO "data.cobol.dat"
                   ORGANIZATION IS SEQUENTIAL
                   FILE STATUS FileStatus.
       
       DATA DIVISION.
           FILE SECTION.
           FD FileUsed.
      *> Some complex data type, notice the "02", "03" levels
           01  Employee.
               88 EndOfFile  VALUE ALL '*'.
               02 fname     PIC X(10).
               02 lname     PIC X(10).
               02 SSN.
                   03 A     PIC 999.
                   03 B     PIC 99.
                   03 C     PIC 9(4).
               02 city      PIC X(10).
           
           WORKING-STORAGE SECTION.
           01  FileStatus   PIC X(2).
               88 FileNotFound      VALUE '35'.
               88 ReachEndOfFile    VALUE '10'.
               88 EverythingOK      VALUE '00'.

       PROCEDURE DIVISION.                 
           
      *> open file
           OPEN INPUT FileUsed
           
      *> Check for errors
           DISPLAY "ERROR CODE : " FileStatus           
           IF FileNotFound
               DISPLAY "ERROR : File NOT found"
               STOP RUN
           END-IF

           IF ReachEndOfFile
               DISPLAY "File Empty"
           END-IF

           IF EverythingOK
               DISPLAY "No errors. Reading..."
           END-IF

           IF FileStatus <> '00'
               DISPLAY "You are not catching this error!"
           END-IF

      *>  Read the file until you reach the end:
           PERFORM UNTIL FileStatus IS EQUAL TO '10'
             READ FileUsed 
               AT END SET EndOfFile TO TRUE
               NOT AT END DISPLAY fname "," SPACE lname 
                   " SSN: "A "-" B "-" C
           END-PERFORM
        
           DISPLAY "ERROR CODE : " FileStatus      
           DISPLAY fname "," SPACE lname " SSN: "A "-" B "-" C

           IF ReachEndOfFile
               DISPLAY "End of File Reached"
           END-IF
              
      *> close file
           CLOSE FileUsed
           STOP RUN.

