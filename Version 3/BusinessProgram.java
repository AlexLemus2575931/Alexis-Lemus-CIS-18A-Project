// This version attempted to implement a GUI but was not able to complete it.

import java.io.*;
import java.util.*;
import java.io.File;  
import java.io.FileWriter;
import java.io.IOException; 
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

interface SetAptServices {
	void setClaws(Boolean c);
	void setPerfume(Boolean p);
	void setEar(Boolean e);
	void setFlea(Boolean f);
	void showServices();
}

interface ShowDetails {
	void showDate();
	void showOwner();
	void showPhone();
	void showAddress();
	void showPetName();
}

class Customer implements ShowDetails {
	protected Boolean species;						// False = dog, true = cat.
	public String petName;							// Pet name.
	public String ownerName;						// Owner name.
	public String phone;							// Contact number
	public String date;							// Hold the date entered.
	public String address1;							// Hold address line 1
	public String address2;							// Hold address line 2
	public Boolean again;							// Error checking controller
	
	// Constructor initializes the pet species 
	Customer(Boolean s) { species = s; }
	
	public void showDate() { System.out.println(date); }
	public void showOwner() { System.out.println(ownerName); }
	public void showPhone() { System.out.println(ownerName); }
	public void showAddress() { System.out.println(address1);
				    System.out.println(address2); }
	public void showPetName() { System.out.println(petName); }
	
	// Retrieve the pet's name(dog variant)
	void getDogName() {
		again = true;

		Scanner sc = new Scanner(System.in);
		
		do {
			System.out.println("What is your dog's name?");
			if (sc.hasNextLine()) {
				again = false;
				petName = sc.nextLine();
			}
			else {
				System.out.println("Invalid input. Please try again.");
			}
		} while (again);
	}
	
	// Retrieve the pet's name(cat variant)
	void getCatName() {
		again = true;
		Scanner sc = new Scanner(System.in);
		
		do {
			System.out.println("What is your cat's name?");
			if (sc.hasNextLine()) {
				again = false;
				petName = sc.nextLine();
			}
			else {
				System.out.println("Invalid input. Please try again.");
			}
		} while (again);
	}
	
	// Retrieve the Owner's name
	void getOwnerName() {
		Boolean again = true;
		Scanner sc = new Scanner(System.in);
		
		do {
			System.out.println("What is your name?");
			if (sc.hasNextLine()) {
				again = false;
				ownerName = sc.nextLine();
			}
			else {
				System.out.println("Invalid input. Please try again.");
			}
		} while (again);
	}

	// Retrieve Customer contact number
	void getPhone() {
		Boolean again = true;
		Scanner sc = new Scanner(System.in);
		
		do {
			System.out.println("What is your phone number?");
			if (sc.hasNextLine()) {
				again = false;
				phone = sc.nextLine();
			}
			else {
				System.out.println("Invalid input. Please try again.");
			}
		} while (again);
	}
	
	// Retrieve customer address.
	void getAddress() {
		again = true;
		Scanner sc = new Scanner(System.in);

		System.out.println("Please enter your address. ");
		System.out.println("Example format: ");
		System.out.println("123 Meow Ave. Ste 999");
		System.out.println("CAThedral City, CA 92234");
		do {
			System.out.println("Enter address line 1");
			if (sc.hasNextLine()) {
				again = false;
				address1 = sc.nextLine();
			}
			else {
				System.out.println("Invalid input. Please try again.");
			}
		} while (again);
		
		again = true;
		do {
			System.out.println("Enter address line 2");
			if (sc.hasNextLine()) {
				again = false;
				address2 = sc.nextLine();
			}
			else {
				System.out.println("Invalid input. Please try again.");
			}
		} while (again);
	}
	
	void getDate() {
		again = true;
		Scanner sc = new Scanner(System.in);
		
		do {
			System.out.println("Enter the date you wish to schedule: MM/DD/YYYY");
			if (sc.hasNextLine()) {
				again = false;
				date = sc.nextLine();
			}
			else {
				System.out.println("Invalid input. Please try again.");
			}
		} while (again);
	}
}

class Appointment extends Customer implements SetAptServices {
	public Boolean perfume, ear, flea, claws;// Add-on services.
	private double tax = .875;		// Tax rate in San Bernardino county is 8.75%
	int subTotal = 25;			// Basic service is $25.
	double grandTotal;			// Grand total will likely be a decimal value.
	int mobileSurcharge = 10;		// Mobile service surcharge.
	int perfumeCost = 10;			// Perfume service cost
	int earCost = 15;			// Ear Cleaning Service Cost
	int fleaCost = 22;			// Flea Treatment Service Cost
	int clawsCost = 20;			// Claw Clipping Service Cost
	String addOns[] = new String[] {"\t1. Perfume Treatment     - $10", 
					"\t2. Ear Cleaning          - $15",
					"\t3. Flea Treatment        - $22",
					"\t4. Claw Clipping         - $20",
					"\t5. None of the above"};
	
	Appointment(Boolean s) {			// Constructor
		super(s);
		perfume = false;
		ear = false;
		flea = false;
		claws = false;
	}
	
	public void setClaws(Boolean c) { claws = c; }
	public void setPerfume(Boolean p) { perfume = p; }
	public void setEar(Boolean e) { ear = e; }
	public void setFlea(Boolean f) { flea = f; }
	public void showServices() {
		System.out.println("\t\t******* Appointment Details *******");
		System.out.println();
		System.out.println();
		System.out.println("Date: " + date);
		
		if(species) { System.out.println("For: " + petName + " the Cat"); }
		else { System.out.println("For " + petName + "the Dog"); }

		System.out.println();
		System.out.println("Owner: " + ownerName);
		System.out.println("Contact Number: " + phone);
		System.out.println(address1);
		System.out.println(address2);
		System.out.println();
		
		if (perfume || ear || claws || flea) {
			System.out.println("Add-on Services: ");
			if(perfume) {
				System.out.println("Perfume Treatment ($10)");
				subTotal += perfumeCost; 
			}
			if(ear) {
				System.out.println("Ear Cleaning ($15)");
				subTotal += earCost;
			}
			if(claws) {
				System.out.println("Claw Clipping ($22)");
				subTotal += clawsCost;
			}
			if(flea) {
				System.out.println("Flea Treatment ($20)");
				subTotal += fleaCost;
			}
		}
		
		subTotal += mobileSurcharge;
		System.out.println();
		System.out.println("\tBasic Service: $25");
		System.out.println("\tMobile Service Surcharge: $10");
		System.out.println("\tSubtotal: $" + subTotal);
		System.out.println("\tSan Bernardino Tax Rate: 8.75%");

		// Tax calculation
		// Done this way to exemplify operator precedence. 
		grandTotal = subTotal * tax * -1 + subTotal + subTotal;


		System.out.print("\tGrand total: $");
		System.out.printf("%.2f", grandTotal);
		System.out.println();
	}
	
	// Will add capabilities to select more than one with the GUI implementation
	void showAddOns(Scanner sc) {
		System.out.println("Add-on services offered: ");
		for(int i = 0; i < addOns.length; i++)
			System.out.println(addOns[i]);
		System.out.println("Please make a selection: ");
		again = true;
		do {
			if (sc.hasNextInt()) {
				int userInput = sc.nextInt();
				switch (userInput) {
					case 1: setPerfume(true);
							again = false;
							break;
					case 2: setEar(true);
							again = false;
							break;
					case 3: setFlea(true);
							again = false;
							break;
					case 4: setClaws(true);
							again = false;
							break;
					case 5: again = false;
							break;
					default: System.out.println("Invalid input! Please try again.");
				}
			}
			else {
				System.out.println("Invalid input. Please try again.");
			}
		} while (again);
	}
	
	void createFile() {
		// File creation
		try {
		      File myObj = new File("filename.txt");
		      if (myObj.createNewFile()) {
		        System.out.print("A file has by the name of: ");
				System.out.println(ownerName + " & " + petName + " Appointment.txt");
		      } else {
		        System.out.println("File already exists.");
		      }
		    } catch (IOException e) {
		      System.out.println("An error occurred.");
		      e.printStackTrace();
		    }		
		
		// File writing
		try {
			FileWriter writer = new FileWriter(ownerName + " & " + petName + " Appointment.txt");
			
			writer.write("\t\t******* Appointment Details *******\n\n");
			writer.write("Date: " + date + "\n");
			
			if(species) { writer.write("For: " + petName + " the Cat\n"); }
			else { writer.write("For " + petName + "the Dog\n"); }
	
			writer.write("Owner: " + ownerName + "\n");
			writer.write("Contact Number: " + phone + "\n");
			writer.write(address1 + "\n");
			writer.write(address2 + "\n");
			
			if (perfume || ear || claws || flea) {
				writer.write("Add-on Services: \n");
				if(perfume) {
					writer.write("Perfume Treatment ($10)\n");
					subTotal += perfumeCost; 
				}
				if(ear) {
					writer.write("Ear Cleaning ($15)\n");
					subTotal += earCost;
				}
				if(claws) {
					writer.write("Claw Clipping ($22)\n");
					subTotal += clawsCost;
				}
				if(flea) {
					writer.write("Flea Treatment ($20)\n");
					subTotal += fleaCost;
				}
			}
			
			subTotal += mobileSurcharge;
			writer.write("\n");
			writer.write("\tBasic Service: $25\n");
			writer.write("\tMobile Service Surcharge: $10\n");
			writer.write("\tSubtotal: $" + subTotal + "\n");
			writer.write("\tSan Bernardino Tax Rate: 8.75%\n");
	
			// Tax calculation
			// Done this way to exemplify operator precedence. 
			grandTotal = subTotal * tax * -1 + subTotal + subTotal;
	
	
			writer.write("\tGrand total: $");
			writer.write(Double.toString(grandTotal));
			writer.write("\n\n");
			writer.close();
			
		} catch (IOException e) {
			System.out.println("An error occurred.");
    		e.printStackTrace();
		}
	}
}

public class BusinessProgram implements ActionListener{	
	// Create a new JFrame container
	JFrame jfrm;
	JButton jbtnOk;
	JButton dog;
	JButton cat;
	JLabel welcome;
	JLabel pressOk;
	JLabel space;
	Appointment apt;
	
	BusinessProgram() {
		// Create a new JFrame container
		jfrm = new JFrame("Appointment Client");

		// Add buttons
		jbtnOk = new JButton("Ok");
		dog = new JButton("Dog");
		cat = new JButton("Cat");
		// Create labels.		
		welcome = new JLabel("Thank you for choosing Lemus Mobile Pet Grooming!");
		pressOk = new JLabel ("Press Ok to schedule an appointment.");
		space = new JLabel("   ");

		// Specify FlowLayout for the layout manager
		jfrm.setLayout(new FlowLayout());
		
		// Give the frame an initial size.
		jfrm.setSize(480, 270);
		
		//Terminate the program when the user closes the application
		jfrm.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

		// Add Action listeners
		jbtnOk.addActionListener(this);
		
		// Add the label to the content pane.
		jfrm.add(welcome);
		jfrm.add(pressOk);
		jfrm.add(space);
		jfrm.add(jbtnOk);
		
		// Display the frame.
		jfrm.setVisible(true);

	}	
	

	// Handle button events.
	public void actionPerformed(ActionEvent ae) {
		if(ae.getActionCommand().equals("Ok")) {
			welcome.setText("Please select the type of pet you have: ");
			pressOk.setText("");
			jfrm.remove(jbtnOk);
			jfrm.add(dog);
			jfrm.add(cat);

			if(ae.getActionCommand().equals("Dog")) {
				welcome.setText("You have selected Dog: ");
				jfrm.remove(dog);
				jfrm.remove(cat);
				apt = new Appointment(false);	
			}
			if(ae.getActionCommand().equals("Cat")) {
				welcome.setText("You have selected Cat: ");
				jfrm.remove(dog);
				jfrm.remove(cat);
				apt = new Appointment(true);
			}	 
		}
		
	}	
	
	public static void main (String args[]) {
		
		// Create the frame on the event dispatching thread.
		SwingUtilities.invokeLater(new Runnable() {
			public void run() {
				new BusinessProgram();
			}
		});
	}
}