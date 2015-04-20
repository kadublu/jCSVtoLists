/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package jcsvtolists;

/**
 *
 * @author carlos
 */
public class Contact {

    /**
     * Full name.
     */
    private String name;

    /**
     * E-mail address 1.
     */
    private String emailAdd1;

    /**
     * E-mail address 2.
     */
    private String emailAdd2;

    /**
     * E-mail address 3.
     */
    private String emailAdd3;

    public Contact() {
    }

    public String getName() {
        return name;
    }

    public void setName(String firstName, String middleName, String lastName) {
        this.name = "";
        this.name += firstName;
        if (this.name.length() > 1) {
            this.name += " ";
        }
        this.name += middleName;
        if (middleName.length() > 1) {
            this.name += " ";
        }
        this.name += lastName;
        this.name = this.name.trim();
    }

    public String getEmailAdd1() {
        return emailAdd1;
    }

    public void setEmailAdd1(String emailAdd1) {
        this.emailAdd1 = emailAdd1;
    }

    public String getEmailAdd2() {
        return emailAdd2;
    }

    public void setEmailAdd2(String emailAdd2) {
        this.emailAdd2 = emailAdd2;
    }

    public String getEmailAdd3() {
        return emailAdd3;
    }

    public void setEmailAdd3(String emailAdd3) {
        this.emailAdd3 = emailAdd3;
    }

    @Override
    public String toString() {
        String ret = "";
        if (emailAdd1 != null) {
            if (emailAdd1.length() > 2) {
                if (name.length() < 1) {
                    this.name = emailAdd1;
                }
                ret += emailAdd1 + ";" + name + "\n";
            }
        }
        if (emailAdd2 != null) {
            if (emailAdd2.length() > 2) {
                if (name.length() < 1) {
                    this.name = emailAdd2;
                }
                ret += emailAdd2 + ";" + name + "\n";
            }
        }
        if (emailAdd3 != null) {
            if (emailAdd3.length() > 2) {
                if (name.length() < 1) {
                    this.name = emailAdd3;
                }
                ret += emailAdd3 + ";" + name + "\n";
            }
        }
        return ret;
    }

}
