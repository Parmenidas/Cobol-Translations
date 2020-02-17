       IDENTIFICATION DIVISION.
           PROGRAM-ID. NewtonRaphsonReciprocalFunc.
      *> Newton-Raphson method for computing 1/a     

       DATA DIVISION.
           WORKING-STORAGE SECTION.
           01  A     PIC S99      VALUE ZEROS.
           01  XNEW  PIC S9V9(5)  VALUE ZEROS.
           01  XOLD  PIC S9V9(5)  VALUE -0.0100.
           01  N     PIC 99       VALUE 10.
           01  INV   PIC S9V9(5)  VALUE ZEROS.
      
       PROCEDURE DIVISION.           
      *> Get number 
           DISPLAY "Insert Number (-10<n<10)"
           ACCEPT A
              
      *> Check input     
           IF A<=-10 OR A >+10 THEN
           DISPLAY "Error: number x must be -10 < x < +10"      
           STOP RUN
           END-IF

           IF A = 0 THEN
           DISPLAY "Result: Infinite"           
           STOP RUN
           END-IF

      *> Initialize the algorithm 
           IF A < 0 THEN
           MOVE -0.01 TO XOLD
           ELSE
           MOVE +0.01 TO XOLD
           END-IF  

      *> Use the algorithm 
           PERFORM N TIMES
               CALL "NRinv" USING XOLD,A
           END-PERFORM
           DIVIDE  A INTO 1 GIVING INV
           DISPLAY "Exact Result:" INV
           STOP RUN.

       IDENTIFICATION DIVISION.
           PROGRAM-ID. NRinv.
       DATA DIVISION.
           WORKING-STORAGE SECTION.
           01  XNEW  PIC S9V9(5)  VALUE ZEROS.
           LINKAGE SECTION.
           01  A     PIC S99      VALUE ZEROS.
           01  XOLD  PIC S9V9(5)  VALUE -0.0100.                        
       PROCEDURE DIVISION USING XOLD,A.
           COMPUTE XNEW ROUNDED = XOLD *( 2 - A * XOLD)
           DISPLAY "Result: ",XNEW
           MOVE XNEW TO XOLD.
       END PROGRAM NRinv.
       END PROGRAM NewtonRaphsonReciprocalFunc.
