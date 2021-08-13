/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;
import java.sql.Date;


/**
 *
 * @author eliot
 */
public class Employee extends User {

    private int employeenb;

    public Employee(int id, String firstname, String lastname, Date birthdate, String adress, String mail) {
        super(id, firstname, lastname, birthdate, adress, mail);
    }

    public void setEmployeeNb(int nb) {
        this.employeenb = nb;
    }

    public int getEmployeeNb() {
        return this.employeenb;
    }

    @Override
    public void showInfos() {
        System.out.println("firstname : " + this.firstname);
        System.out.println("lastname : " + this.lastname);
        System.out.println("adress : " + this.adress);
        System.out.println("email : " + this.mail);
        System.out.println("birthdate : " + this.birthdate.getDate() + "/" + this.birthdate.getMonth() + "/" + this.birthdate.getYear());
        System.out.println("employee number : " + this.employeenb);
        System.out.println("password :"+this.password);
    }

}
