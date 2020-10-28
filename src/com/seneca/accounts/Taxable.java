
/*******************************
 * @author: Shervin Tafreshipour
 * Student ID: 155199169
 * Email: stafreshipour@myseneca.ca
 *******************************/

package com.seneca.accounts;

public interface Taxable {

    // constant taxRate
    final double taxRate = 15.00;

    // caculate Tax
    void calculateTax();

    // get Tax Amount
    double getTaxAmount();

    // create a Tax Statement
    String createTaxStatement();
}
