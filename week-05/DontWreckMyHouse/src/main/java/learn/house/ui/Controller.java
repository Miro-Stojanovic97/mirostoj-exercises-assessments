//Miro Stojanovic
//Module 5 Assessment: Don't Break My House

package learn.house.ui;

//imports for spring annotations
import learn.house.data.DataAccessException;
import org.springframework.stereotype.Component;

import javax.xml.crypto.Data;

@Component
public class Controller {

        //---initiate constructor---


        //---begin run() method---
    public void run() {
        System.out.println("Welcome to Don't Wreck My House!");
        try {
            main();
        } catch (DataAccessException ex) {
            System.out.println("Error");
        }
    }

    public void main() throws DataAccessException {

    }
}
