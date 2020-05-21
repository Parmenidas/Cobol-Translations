       IDENTIFICATION DIVISION.
           PROGRAM-ID. WriteData.
      *> How to write a file     
       
       ENVIRONMENT DIVISION.
           INPUT-OUTPUT SECTION.
           FILE-CONTROL.
               SELECT FileUsed ASSIGN TO "data.cobol.dat"
                   ORGANIZATION IS SEQUENTIAL.
       
       DATA DIVISION.
           FILE SECTION.
           FD FileUsed.
      *> Some complex data type, notice the "02", "03" levels
           01  Employee.
               02 fname     PIC X(10).
               02 lname     PIC X(10).
               02 SSN.
                   03 A     PIC 999.
                   03 B     PIC 99.
                   03 C     PIC 9(4).
               02 city      PIC X(10).
           
           WORKING-STORAGE SECTION.
           01  N            PIC 9 VALUE 3.

       PROCEDURE DIVISION.                 
      *> Set values
           MOVE "Michael" TO fname
           MOVE "Smith" TO lname
           MOVE "123456789" TO SSN
           MOVE "Portland" TO city
           DISPLAY fname "," SPACE lname " SSN: "A "-" B "-" C
           
      *> open file
           OPEN OUTPUT FileUsed

      *> Write the record N times 
      *> NOTE: Old file is deleted and replaced with new data
           PERFORM N TIMES 
             WRITE Employee
           END-PERFORM
       
      *> close file
           CLOSE FileUsed
           STOP RUN.

